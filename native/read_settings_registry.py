#!/usr/bin/env python3
"""Recover the BSC500 V1.04.003 settings registry from its application image."""

import argparse
import json
from pathlib import Path
import struct


IMAGE_BASE = 0x60000000
SCATTER_TABLE_WORDS = 0x6006CB2C
DECOMPRESS_ROUTINE = 0x6006CB34
KEY_TABLE = 0x20007DC8
RECORD_TABLE = 0x20008114


def initialized_data(image, start, size):
    cursor = start
    result = bytearray()
    while len(result) < size:
        token = image[cursor]
        cursor += 1
        literals = token & 3
        if not literals:
            literals = image[cursor]
            cursor += 1
        repeats = token >> 4
        if not repeats:
            repeats = image[cursor]
            cursor += 1
        if not literals or cursor + literals - 1 > len(image):
            raise ValueError("Invalid literal run")
        result.extend(image[cursor:cursor + literals - 1])
        cursor += literals - 1
        if repeats:
            distance = image[cursor]
            cursor += 1
            distance_code = token & 12
            if distance_code == 12:
                distance += image[cursor] << 8
                cursor += 1
            else:
                distance += distance_code << 6
            if not 0 < distance <= len(result):
                raise ValueError("Invalid back-reference")
            for _ in range(repeats + 2):
                result.append(result[-distance])
    if len(result) != size:
        raise ValueError("Initialized data does not match the scatter-table length")
    return result, cursor


def read_registry(image):
    table_offset = SCATTER_TABLE_WORDS - IMAGE_BASE
    first, last = struct.unpack_from("<2i", image, table_offset)
    for offset in range(table_offset + first, table_offset + last, 16):
        source, destination, size, routine = struct.unpack_from("<4I", image, offset)
        if routine == DECOMPRESS_ROUTINE and destination <= KEY_TABLE < RECORD_TABLE < destination + size:
            ram, end = initialized_data(image, source - IMAGE_BASE, size)
            break
    else:
        raise ValueError("Firmware scatter entry not found")

    settings = []
    for index in range((RECORD_TABLE - KEY_TABLE) // 4):
        key_address, = struct.unpack_from("<I", ram, KEY_TABLE - destination + 4 * index)
        if not key_address:
            break
        key_offset = key_address - IMAGE_BASE
        if not 0 <= key_offset < len(image):
            raise ValueError("Settings key pointer is outside the flash image")
        string_end = image.index(b"\0", key_offset)
        key = image[key_offset:string_end].decode("ascii")
        code, record_size, data_address = struct.unpack_from("<3I", ram, RECORD_TABLE - destination + 12 * index)
        count, key_id = code >> 16, code & 0xFFFF
        if not key.startswith("cfg_") or key_id != index + 1 or not count or not record_size:
            raise ValueError(f"Unexpected registry entry {key!r} at index {index}")
        settings.append({"key": key, "id": key_id, "record_count": count,
                         "record_size": record_size, "total_bytes": count * record_size,
                         "ram_address": hex(data_address), "name_address": hex(key_address)})
    else:
        raise ValueError("Settings registry terminator not found")
    return {"scatter_source": hex(source), "scatter_source_end": hex(IMAGE_BASE + end),
            "scatter_destination": hex(destination), "scatter_decoded_size": size,
            "key_table": hex(KEY_TABLE), "record_table": hex(RECORD_TABLE), "settings": settings}


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("application", type=Path)
    args = parser.parse_args()
    print(json.dumps(read_registry(args.application.read_bytes()), ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
