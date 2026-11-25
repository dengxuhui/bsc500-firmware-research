#!/usr/bin/env python3
"""Inspect the iGPSPORT RSC directory and optionally extract its files locally."""

import argparse
import hashlib
import json
from pathlib import Path, PurePosixPath
import struct


def parse(raw):
    if raw[:2] != b"HD" or raw[4:6] != b"CK" or raw[10:12] != b"OS":
        raise ValueError("RSC signatures missing")
    end_of_directory = struct.unpack_from("<H", raw, 2)[0]
    stored_checksum = struct.unpack_from("<I", raw, 6)[0]
    if stored_checksum != sum(raw[10:]) & 0xFFFFFFFF:
        raise ValueError("RSC checksum mismatch")

    os_length = struct.unpack_from("<H", raw, 12)[0]
    sc_offset = 14 + os_length
    if raw[sc_offset:sc_offset + 2] != b"SC":
        raise ValueError("SC signature missing")
    sc_length = struct.unpack_from("<H", raw, sc_offset + 2)[0]
    sc_start = sc_offset + 4
    start = sc_start + sc_length
    if not os_length or not sc_length or not start < end_of_directory <= len(raw):
        raise ValueError("RSC boundaries are invalid")

    mask = [value ^ (0x30 if offset % 6 == 2 else 0x32)
            for offset, value in enumerate(raw[14:sc_offset])]
    rotations = [(value ^ (0x30 if offset % 6 == 2 else 0x32)) & 7
                 for offset, value in enumerate(raw[sc_start:start], sc_start)]
    rotate_right = [bytes(((value >> shift) | (value << (8 - shift))) & 255
                          for value in range(256)) for shift in range(8)]
    payload = bytes(rotate_right[rotations[index % sc_length]][value] ^ mask[index % os_length]
                    for index, value in enumerate(raw[start:]))

    directory_length = end_of_directory - start
    directory = payload[:directory_length]
    if not directory.startswith(b"PT\0\0"):
        raise ValueError("PT signature missing after decode")
    cursor = 4
    previous_end = end_of_directory
    entries = []
    names = set()
    while cursor < directory_length:
        if directory[cursor:cursor + 2] != b"NM" or cursor + 4 > directory_length:
            raise ValueError("Invalid NM entry")
        name_length = struct.unpack_from("<H", directory, cursor + 2)[0]
        name_start = cursor + 4
        address_tag = name_start + name_length
        if directory[address_tag:address_tag + 2] != b"AD" or address_tag + 6 > directory_length:
            raise ValueError("Invalid AD entry")
        name = directory[name_start:address_tag].decode("utf-8")
        path = PurePosixPath(name)
        if path.is_absolute() or len(path.parts) != 1 or path.name != name or "\\" in name or name in (".", ".."):
            raise ValueError(f"Invalid local filename {name!r}")
        if name.casefold() in names:
            raise ValueError(f"Duplicate filename {name!r}")
        names.add(name.casefold())
        end = end_of_directory + struct.unpack_from("<I", directory, address_tag + 2)[0]
        if not previous_end <= end <= len(raw):
            raise ValueError(f"Invalid file range for {name!r}")
        extracted = payload[previous_end - start:end - start]
        entries.append((name, previous_end, end, extracted))
        cursor = address_tag + 6
        previous_end = end

    if not entries or previous_end != len(raw):
        raise ValueError("RSC entries do not account for the entire payload")
    return (start, end_of_directory, stored_checksum, os_length, sc_length), entries


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("rsc", type=Path)
    parser.add_argument("--extract-to", type=Path, help="new directory for decoded entries")
    args = parser.parse_args()
    raw = args.rsc.read_bytes()
    (start, end, checksum, os_length, sc_length), entries = parse(raw)
    if args.extract_to:
        args.extract_to.mkdir(mode=0o700)
    output = []
    for name, first, last, data in entries:
        if args.extract_to:
            path = args.extract_to / name
            with path.open("xb") as file:
                file.write(data)
            path.chmod(0o400)
        output.append({"name": name, "offset": first, "end_exclusive": last,
                       "size": len(data), "sha256": hashlib.sha256(data).hexdigest()})
    print(json.dumps({"source": args.rsc.name, "sha256": hashlib.sha256(raw).hexdigest(),
                      "checksum": checksum, "os_length": os_length, "sc_length": sc_length,
                      "directory_offset": start, "entries_offset": end, "files": output},
                     ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
