#!/usr/bin/env python3

import argparse
from hashlib import sha256
import json
from pathlib import Path
import struct


IMAGE_BASE = 0x60000000
ORIGINAL_SHA256 = "d7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933"
TABLE_ADDRESS = 0x602A75E4
TABLE_POINTERS = (0x60161420, 0x6024A54C)
LAYOUT_COUNT = 22
HEADER_SIZE = 0x1C
MATCH_FUNCTION = 0x60161014
RENDER_CONFIG_FUNCTION = 0x6024A2E0
PAGE_SLOT = 0x2004C8F8
DEFAULT_CATALOG = (
    Path(__file__).resolve().parents[1]
    / "web/assets/igpsport-cn-8.11.00/bsc500-layout-catalog.json"
)


def validate_image(image):
    if sha256(image).hexdigest() != ORIGINAL_SHA256:
        raise ValueError("输入必须是原厂 BSC500 V1.04.003 BSC500_APP.bin")


def decode_header(header):
    if len(header) != HEADER_SIZE:
        raise ValueError("布局头必须为 28 字节")
    count = header[0x18]
    if not 1 <= count <= 6:
        raise ValueError("普通页必须有 1–6 行")
    rows = []
    for index in range(count):
        weight, columns_minus_one = header[index * 4:index * 4 + 2]
        if not 1 <= weight <= 7 or columns_minus_one not in (0, 1):
            raise ValueError("普通页的行权重或列数不符合已确认编码")
        rows.append({"weight": weight, "columns": columns_minus_one + 1})
    return {
        "rows": rows,
        "field_count": sum(row["columns"] for row in rows),
        "page_mode": sum(row["columns"] << (index * 3) for index, row in enumerate(rows)),
        "line_width": sum(row["weight"] << (index * 3) for index, row in enumerate(rows)),
    }


def read_layouts(image):
    validate_image(image)
    for address in TABLE_POINTERS:
        pointer, = struct.unpack_from("<I", image, address - IMAGE_BASE)
        if pointer != TABLE_ADDRESS:
            raise ValueError(f"模板表指针与研究依据不符：{address:#010x}")
    layouts = []
    offset = TABLE_ADDRESS - IMAGE_BASE
    for index in range(LAYOUT_COUNT):
        header = image[offset + index * HEADER_SIZE:offset + (index + 1) * HEADER_SIZE]
        layouts.append({"index": index, **decode_header(header)})
    return layouts


def compare_catalog(layouts, catalog):
    sections = [section for section in catalog["sections"] if section["name"] == "普通数据页"]
    if len(sections) != 1:
        raise ValueError("目录必须包含唯一的普通数据页分组")
    firmware_pairs = {(layout["page_mode"], layout["line_width"]) for layout in layouts}
    catalog_pairs = {
        (layout["page_mode"], layout["line_width"])
        for layout in sections[0]["layouts"]
    }

    def describe(pairs):
        return [{"page_mode": mode, "line_width": width} for mode, width in sorted(pairs)]

    return {
        "catalog_apk_version": catalog.get("apk_version"),
        "firmware_unique_layouts": len(firmware_pairs),
        "catalog_unique_layouts": len(catalog_pairs),
        "same_parameter_pairs": firmware_pairs == catalog_pairs,
        "firmware_only": describe(firmware_pairs - catalog_pairs),
        "catalog_only": describe(catalog_pairs - firmware_pairs),
    }


def verify_layouts(image):
    from unicorn import Uc, UC_ARCH_ARM, UC_MODE_MCLASS, UC_MODE_THUMB
    from unicorn.arm_const import (
        UC_ARM_REG_LR, UC_ARM_REG_PC, UC_ARM_REG_R0, UC_ARM_REG_R1,
        UC_ARM_REG_R2, UC_ARM_REG_R3, UC_ARM_REG_SP, UC_CPU_ARM_CORTEX_M33,
    )

    layouts = read_layouts(image)
    cpu = Uc(UC_ARCH_ARM, UC_MODE_THUMB | UC_MODE_MCLASS)
    cpu.ctl_set_cpu_model(UC_CPU_ARM_CORTEX_M33)
    cpu.mem_map(IMAGE_BASE, (len(image) + 0xFFF) & ~0xFFF)
    cpu.mem_write(IMAGE_BASE, image)
    cpu.mem_map(0x20000000, 0x90000)
    stack = 0x2008F000
    sentinel = 0x2008E000
    output = 0x20080000
    registers = (UC_ARM_REG_R0, UC_ARM_REG_R1, UC_ARM_REG_R2, UC_ARM_REG_R3)

    def call(address, *arguments):
        cpu.reg_write(UC_ARM_REG_SP, stack)
        cpu.reg_write(UC_ARM_REG_LR, sentinel | 1)
        for register, value in zip(registers, arguments):
            cpu.reg_write(register, value)
        if len(arguments) > 4:
            cpu.mem_write(stack, struct.pack(f"<{len(arguments) - 4}I", *arguments[4:]))
        cpu.emu_start(address | 1, sentinel, count=100000)
        if cpu.reg_read(UC_ARM_REG_PC) != sentinel:
            raise ValueError(f"布局函数未在指令预算内返回：{address:#010x}")
        return cpu.reg_read(UC_ARM_REG_R0)

    def render_configuration(index):
        cpu.mem_write(output, bytes(32))
        status = call(RENDER_CONFIG_FUNCTION, 0, index, output, output + 1, output + 2, output + 8)
        if status != 0:
            raise ValueError(f"固件拒绝读取布局索引：{index}")
        field_count, row_count = cpu.mem_read(output, 2)
        rows = [
            {
                "weight": cpu.mem_read(output + 8 + row, 1)[0],
                "columns": cpu.mem_read(output + 2 + row, 1)[0] + 1,
            }
            for row in range(row_count)
        ]
        return {"field_count": field_count, "rows": rows}

    matches = []
    table_offset = TABLE_ADDRESS - IMAGE_BASE
    for layout in layouts:
        index = layout["index"]
        start = table_offset + index * HEADER_SIZE
        cpu.mem_write(PAGE_SLOT, image[start:start + HEADER_SIZE])
        selected = call(MATCH_FUNCTION, 0, 0)
        actual = render_configuration(selected)
        expected = {"field_count": layout["field_count"], "rows": layout["rows"]}
        if selected != index or actual != expected:
            raise ValueError(f"原厂布局 {index} 的匹配或渲染配置与表内容不符")
        matches.append({"input_index": index, "selected_index": selected, "render_configuration": actual})

    fallbacks = []
    for rows in (
        [{"weight": 3, "columns": 2}, {"weight": 3, "columns": 2}],
        [{"weight": 1, "columns": 1}, {"weight": 5, "columns": 1}],
    ):
        header = bytearray(HEADER_SIZE)
        header[0x18] = len(rows)
        for row, values in enumerate(rows):
            header[row * 4:row * 4 + 2] = bytes((values["weight"], values["columns"] - 1))
        requested = decode_header(header)
        if any(
            (layout["page_mode"], layout["line_width"]) == (requested["page_mode"], requested["line_width"])
            for layout in layouts
        ):
            raise ValueError("回退样例意外命中了原厂模板")
        cpu.mem_write(PAGE_SLOT, bytes(header))
        selected = call(MATCH_FUNCTION, 0, 0)
        actual = render_configuration(selected)
        expected = {"field_count": layouts[5]["field_count"], "rows": layouts[5]["rows"]}
        if selected != 5 or actual != expected or actual["rows"] == rows:
            raise ValueError("非目录样例未按已知路径回退到固定模板 5")
        fallbacks.append({"requested": requested, "selected_index": selected, "render_configuration": actual})

    return {
        "emulator": "Unicorn",
        "cpu_model": "Cortex-M33",
        "injected_state": "模式 0、物理普通页槽 0 的行描述；其他 RAM 为零，未运行完整启动流程",
        "hooks": [],
        "matching_function": f"{MATCH_FUNCTION:#010x}",
        "render_configuration_function": f"{RENDER_CONFIG_FUNCTION:#010x}",
        "stock_matches": matches,
        "non_catalog_fallbacks": fallbacks,
        "scope": "执行原始匹配和配置提取函数，不包含蓝牙接收、持久化、实际绘制或真机验证",
    }


def main():
    parser = argparse.ArgumentParser(description="离线提取并验证 BSC500 V1.04.003 的固定普通页布局")
    parser.add_argument("action", choices=("inspect", "verify"))
    parser.add_argument("application", type=Path)
    parser.add_argument("--catalog", type=Path, default=DEFAULT_CATALOG, help="用于对照的本地 APK 布局目录")
    args = parser.parse_args()
    try:
        image = args.application.read_bytes()
        layouts = read_layouts(image)
        catalog_bytes = args.catalog.read_bytes()
        comparison = compare_catalog(layouts, json.loads(catalog_bytes))
        report = {
            "device": "BSC500",
            "firmware_version": "V1.04.003",
            "application_sha256": ORIGINAL_SHA256,
            "table_address": f"{TABLE_ADDRESS:#010x}",
            "layout_count": len(layouts),
            "layouts": layouts,
            "catalog_sha256": sha256(catalog_bytes).hexdigest(),
            "catalog_comparison": comparison,
            "hardware_verified": False,
            "limitations": [
                "仅覆盖该哈希的旧版主程序，不代表 V1.08.005 或其他机型",
                "22 个普通页模板不等于任意布局能力，也不覆盖专项页",
                "目录集合一致只证明这张固定表中没有额外普通页布局",
            ],
        }
        if args.action == "verify":
            report["verification"] = verify_layouts(image)
    except (ImportError, OSError, ValueError, KeyError, TypeError) as error:
        parser.exit(1, f"离线布局分析失败：{error}\n")
    print(json.dumps(report, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
