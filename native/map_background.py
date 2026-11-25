#!/usr/bin/env python3
"""Patch and verify the BSC500 V1.04.003 map canvas background offline."""

import argparse
from hashlib import sha256
import json
import os
from pathlib import Path
import re
import struct
import tempfile


IMAGE_BASE = 0x60000000
ORIGINAL_SHA256 = "d7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933"
VARIANTS = {
    ("classic", "light"): (0x6014D960, 0xF6F6F6, 1),
    ("classic", "dark"): (0x6014D97C, 0x0F0F0F, None),
    ("colorful", "light"): (0x6014D96C, 0xF5F5F5, 0),
    ("colorful", "dark"): None,
}


def parse_color(value):
    if not re.fullmatch(r"#?[0-9A-Fa-f]{6}", value):
        raise argparse.ArgumentTypeError("expected a six-digit RGB color such as '#D5E8D4'")
    return int(value.lstrip("#"), 16)


def thumb_immediate(opcode, register, value):
    first = opcode | ((value >> 1) & 0x0400) | (value >> 12)
    second = ((value << 4) & 0x7000) | (register << 8) | (value & 0xFF)
    return struct.pack("<HH", first, second)


def color_bytes(color, register):
    if register is None:
        return struct.pack("<I", color)
    return thumb_immediate(0xF240, register, color & 0xFFFF) + thumb_immediate(
        0xF2C0, register, color >> 16
    )


def read_original(path):
    image = path.read_bytes()
    if sha256(image).hexdigest() != ORIGINAL_SHA256:
        raise ValueError("input must be the original BSC500 V1.04.003 BSC500_APP.bin")
    return image


def patched_image(original, variant, color):
    entry = VARIANTS[variant]
    if entry is None:
        raise ValueError("colorful dark returns zero without loading a separate color constant")
    address, old_color, register = entry
    offset = address - IMAGE_BASE
    old_bytes = color_bytes(old_color, register)
    end = offset + len(old_bytes)
    if original[offset:end] != old_bytes:
        raise ValueError(f"unexpected original bytes at {address:#010x}")
    new_bytes = color_bytes(color, register)
    if new_bytes == old_bytes:
        raise ValueError("choose a color different from the original")
    return original[:offset] + new_bytes + original[end:], address, old_bytes, new_bytes


def emulate_map_fill(image, theme, appearance):
    from unicorn import Uc, UC_ARCH_ARM, UC_HOOK_CODE, UC_MODE_MCLASS, UC_MODE_THUMB
    from unicorn.arm_const import UC_ARM_REG_R1, UC_ARM_REG_R2, UC_ARM_REG_R4, UC_ARM_REG_SP

    emulator = Uc(UC_ARCH_ARM, UC_MODE_THUMB | UC_MODE_MCLASS)
    emulator.mem_map(IMAGE_BASE, (len(image) + 0xFFF) & ~0xFFF)
    emulator.mem_write(IMAGE_BASE, image)
    emulator.mem_map(0x20000000, 0x90000)

    # Populate only the RAM needed by the firmware draw call.
    context, origin, handles, vtable, callback, bounds = range(0x20010000, 0x20010600, 0x100)
    emulator.mem_write(0x2000CC74, bytes([int(theme == "colorful")]))
    emulator.mem_write(0x20005A1C, bytes([int(appearance == "light")]))
    emulator.mem_write(0x2007B364, struct.pack("<I", context))
    emulator.mem_write(context + 0x18, struct.pack("<I", origin))
    emulator.mem_write(0x2000BC7C, struct.pack("<HH", 319, 479))
    emulator.mem_write(bounds, struct.pack("<hh", 7, 11))
    emulator.mem_write(0x20048BE8, struct.pack("<I", handles))
    emulator.mem_write(handles, struct.pack("<I", handles + 4))
    emulator.mem_write(handles + 0x40, struct.pack("<I", vtable))
    emulator.mem_write(vtable + 0x0C, struct.pack("<I", callback | 1))
    emulator.reg_write(UC_ARM_REG_R4, bounds)
    emulator.reg_write(UC_ARM_REG_SP, 0x2008FFF0)

    fills = []

    def record_fill(cpu, address, _size, _data):
        # The original dispatcher has validated the rectangle and called its backend.
        draw = bytes(cpu.mem_read(cpu.reg_read(UC_ARM_REG_R1), 8))
        rect = bytes(cpu.mem_read(cpu.reg_read(UC_ARM_REG_R2), 8))
        rgb565, = struct.unpack_from("<H", draw, 4)
        fills.append((rgb565, struct.unpack("<hhhh", rect)))
        cpu.emu_stop()

    emulator.hook_add(UC_HOOK_CODE, record_fill, begin=callback, end=callback)
    emulator.emu_start(0x60258528 | 1, IMAGE_BASE + len(image), count=10000)
    if len(fills) != 1:
        raise ValueError("firmware did not dispatch the expected map fill")
    return fills[0]


def to_rgb565(rgb):
    return ((rgb >> 8) & 0xF800) | ((rgb >> 5) & 0x07E0) | ((rgb >> 3) & 0x001F)


def verify(original, modified, variant, color):
    results = []
    for (theme, appearance), entry in VARIANTS.items():
        old_color = entry[1] if entry else 0
        new_color = color if (theme, appearance) == variant else old_color
        old_fill = emulate_map_fill(original, theme, appearance)
        new_fill = emulate_map_fill(modified, theme, appearance)
        expected_rectangle = (7, 11, 326, 490)
        if old_fill != (to_rgb565(old_color), expected_rectangle) or new_fill != (
            to_rgb565(new_color), expected_rectangle
        ):
            raise ValueError(f"unexpected draw output for {theme}/{appearance}: {old_fill}, {new_fill}")
        results.append({
            "theme": theme,
            "appearance": appearance,
            "original_rgb": f"#{old_color:06X}",
            "modified_rgb": f"#{new_color:06X}",
            "original_fill_rgb565": f"0x{old_fill[0]:04X}",
            "modified_fill_rgb565": f"0x{new_fill[0]:04X}",
            "rectangle": list(new_fill[1]),
        })
    return results


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("action", choices=("patch", "verify"))
    parser.add_argument("original", type=Path)
    parser.add_argument("edited", type=Path)
    parser.add_argument("--theme", choices=("classic", "colorful"), default="classic")
    parser.add_argument("--appearance", choices=("light", "dark"), default="light")
    parser.add_argument("--color", type=parse_color, required=True)
    args = parser.parse_args()
    variant = (args.theme, args.appearance)
    try:
        original = read_original(args.original)
        expected, address, old_bytes, new_bytes = patched_image(original, variant, args.color)
        results = {
            "original_sha256": ORIGINAL_SHA256,
            "modified_sha256": sha256(expected).hexdigest(),
            "flash_address": f"{address:#010x}",
            "file_offset": f"{address - IMAGE_BASE:#x}",
            "original_bytes": old_bytes.hex(" "),
            "modified_bytes": new_bytes.hex(" "),
            "changed_offsets": [
                f"{address - IMAGE_BASE + index:#x}"
                for index, (old, new) in enumerate(zip(old_bytes, new_bytes)) if old != new
            ],
        }
        if args.action == "patch":
            with tempfile.NamedTemporaryFile(dir=args.edited.parent) as output:
                output.write(expected)
                output.flush()
                os.link(output.name, args.edited)
        else:
            modified = args.edited.read_bytes()
            if modified != expected:
                raise ValueError("edited image does not match the requested patch byte for byte")
            results["emulated_map_fills"] = verify(original, modified, variant, args.color)
    except (ImportError, OSError, ValueError) as error:
        parser.exit(1, f"{error}\n")
    print(json.dumps(results, indent=2))


if __name__ == "__main__":
    main()
