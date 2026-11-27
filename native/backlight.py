#!/usr/bin/env python3
"""检查背光 CSV，并离线回放 BSC500 V1.04.003 的原始亮度计算。"""

import argparse
from hashlib import sha256
import json
from pathlib import Path
import re
import struct


ORIGINAL_SHA256 = "d7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933"
IMAGE_BASE = 0x60000000
CONFIG = 0x20009A4C
AMBIENT_TABLE = 0x2007E984
SLIDER_MAX = 254
DEFAULT_LEVEL = "0,10\n127,50\n254,100\n"
DEFAULT_LIGHT = "0,10,5\n100,50,20\n1000,100,40\n"


def parse_table(text, kind):
    if kind not in ("level", "light"):
        raise ValueError("表类型必须是 level 或 light")
    columns, limit = (2, 20) if kind == "level" else (3, 50)
    if not text.isascii():
        raise ValueError("CSV 只能包含 ASCII 字符")
    rows = []
    for number, line in enumerate(text.replace("\r\n", "\n").replace("\r", "\n").split("\n"), 1):
        if not line:
            continue
        if len(line) > 63:
            raise ValueError(f"第 {number} 行超过固件的 63 字节行缓冲区")
        fields = line.split(",")
        if len(fields) != columns or any(not re.fullmatch(r"[0-9]+", field) for field in fields):
            raise ValueError(f"第 {number} 行需要 {columns} 个逗号分隔的非负十进制整数")
        row = tuple(map(int, fields))
        maximum = 65535 if kind == "level" else 0x7FFFFFFF
        if row[0] > maximum or any(value > 255 for value in row[1:]):
            raise ValueError(f"第 {number} 行超出研究工具范围：首列最多 {maximum}，亮度最多 255")
        rows.append(row)
    if not 1 <= len(rows) <= limit:
        raise ValueError(f"{kind} 表需要 1–{limit} 行")
    if any(right[0] <= left[0] for left, right in zip(rows, rows[1:])):
        raise ValueError("首列必须严格递增；固件不会为输入排序")
    if kind == "light":
        if rows[0][0] != 0:
            raise ValueError("照度表必须从 0 开始，避免低照度外推发生无符号下溢")
        for left, right in zip(rows, rows[1:]):
            for column in (1, 2):
                delta = right[column] - left[column]
                if delta < 0:
                    raise ValueError("白天和夜间亮度必须分别非递减，避免无符号插值溢出")
                if (right[0] - left[0]) * delta > 0xFFFFFFFF:
                    raise ValueError("照度间距与亮度差的乘积超过固件的 32 位插值范围")
    return rows


def slider_brightness(rows, position):
    if not rows or not 0 <= position <= SLIDER_MAX:
        raise ValueError("需要非空滑块表，位置范围为 0–254")
    index = (2 * position * (len(rows) - 1) + SLIDER_MAX) // (2 * SLIDER_MAX)
    return rows[index][1]


def ambient_brightness(rows, lux, dark=False):
    if not rows or not 0 <= lux <= 0x7FFFFFFF:
        raise ValueError("需要非空照度表，照度范围为 0–2147483647")
    column = 2 if dark else 1
    if lux == 0:
        return rows[0][column]
    if lux >= rows[-1][0]:
        return rows[-1][column]
    for left, right in zip(rows, rows[1:]):
        if lux < right[0]:
            return left[column] + (lux - left[0]) * (right[column] - left[column]) // (right[0] - left[0])
    raise ValueError("照度表没有可用区间")


class FirmwareBacklight:
    def __init__(self, image, files):
        if sha256(image).hexdigest() != ORIGINAL_SHA256:
            raise ValueError("只接受原厂 BSC500 V1.04.003 BSC500_APP.bin，拒绝其他版本或修改镜像")
        from unicorn import Uc, UC_ARCH_ARM, UC_HOOK_CODE, UC_MODE_MCLASS, UC_MODE_THUMB
        from unicorn import arm_const

        self.registers = arm_const
        self.cpu = Uc(UC_ARCH_ARM, UC_MODE_THUMB | UC_MODE_MCLASS)
        self.cpu.ctl_set_cpu_model(arm_const.UC_CPU_ARM_CORTEX_M33)
        self.cpu.mem_map(IMAGE_BASE, 0x450000)
        self.cpu.mem_write(IMAGE_BASE, image)
        self.cpu.mem_map(0x20000000, 0x90000)
        self.cpu.reg_write(arm_const.UC_ARM_REG_C1_C0_2, 0xF00000)
        self.cpu.reg_write(arm_const.UC_ARM_REG_FPEXC, 0x40000000)
        self.files = files
        self.handles = {}
        for address in (0x60166758, 0x60161EF4, 0x601667BC, 0x60168578, 0x60152418):
            self.cpu.hook_add(UC_HOOK_CODE, self._dependency, begin=address, end=address)
        ctype = self.call(0x6004FE60, 0, 0) + 1
        self.cpu.mem_write(0x200089F0, struct.pack("<I", ctype))
        self.call(0x60004124)

    def _dependency(self, cpu, address, _size, _data):
        regs = self.registers
        r0, r1, r2, r3 = [cpu.reg_read(reg) for reg in (
            regs.UC_ARM_REG_R0, regs.UC_ARM_REG_R1, regs.UC_ARM_REG_R2, regs.UC_ARM_REG_R3)]
        result = 0
        if address == 0x60166758:
            name = bytes(cpu.mem_read(r1, 128)).split(b"\0", 1)[0].decode("ascii")
            if name not in self.files:
                result = 1
            else:
                handle = len(self.handles) + 1
                self.handles[handle] = [self.files[name], 0]
                cpu.mem_write(r0, struct.pack("<I", handle))
        elif address == 0x60161EF4:
            content, position = self.handles[r0]
            chunk = content[position:position + r2]
            if chunk:
                cpu.mem_write(r1, chunk)
            self.handles[r0][1] += len(chunk)
            cpu.mem_write(r3, struct.pack("<I", len(chunk)))
        elif address == 0x60152418:
            result = 0x20088000
        cpu.reg_write(regs.UC_ARM_REG_R0, result)
        cpu.reg_write(regs.UC_ARM_REG_PC, cpu.reg_read(regs.UC_ARM_REG_LR))

    def call(self, address, *arguments):
        cpu, regs = self.cpu, self.registers
        cpu.reg_write(regs.UC_ARM_REG_SP, 0x2008F000)
        cpu.reg_write(regs.UC_ARM_REG_LR, 0x6044F001)
        for reg, value in zip((regs.UC_ARM_REG_R0, regs.UC_ARM_REG_R1,
                               regs.UC_ARM_REG_R2, regs.UC_ARM_REG_R3), arguments):
            cpu.reg_write(reg, value & 0xFFFFFFFF)
        cpu.emu_start(address | 1, 0x6044F000, timeout=1000000, count=1000000)
        if cpu.reg_read(regs.UC_ARM_REG_PC) != 0x6044F000:
            raise ValueError(f"固件调用 {address:#x} 未在执行预算内正常返回")
        return cpu.reg_read(regs.UC_ARM_REG_R0)

    def tables(self):
        flags = bytes(self.cpu.mem_read(CONFIG, 5))
        return {
            "slider_enabled": flags[0] == 1,
            "ambient_enabled": flags[1] == 1,
            "csv_enabled": flags[2] == 1,
            "level": [struct.unpack("<HH", self.cpu.mem_read(CONFIG + 6 + i * 4, 4)) for i in range(flags[3])],
            "light": [struct.unpack("<IHH", self.cpu.mem_read(AMBIENT_TABLE + i * 8, 8)) for i in range(flags[4])],
        }


def inspect_tables(level, light):
    return {
        "level": level,
        "light": light,
        "slider_samples": [{"position": position, "brightness": slider_brightness(level, position)}
                           for position in (0, 63, 64, 127, 190, 191, 254)] if level else [],
        "ambient_samples": [{"lux": lux, "day": ambient_brightness(light, lux),
                             "night": ambient_brightness(light, lux, True)}
                            for lux in sorted({0, 1, *[row[0] for row in light],
                                               *[(a[0] + b[0]) // 2 for a, b in zip(light, light[1:])]})] if light else [],
        "limits": [
            "仅用于离线研究，不发送文件、不修改镜像、不连接设备。",
            "亮度是固件数值，未确认是百分比；0–255 是工具的保守字节范围，不代表硬件安全范围。",
            "滑块第一列被存储，但查询只按行号映射，不能用非均匀首列控制区间。",
            "文件的真机位置、V1.08.005 兼容性及实际屏幕效果仍未验证。",
        ],
    }


def verify_tables(image, level_text, light_text):
    level = parse_table(level_text, "level") if level_text is not None else []
    light = parse_table(light_text, "light") if light_text is not None else []
    files = {name: text.encode("ascii") for name, text in (
        ("0:level.txt", level_text), ("0:light.txt", light_text)) if text is not None}
    firmware = FirmwareBacklight(image, files)
    decoded = firmware.tables()
    expected = {"level": level, "light": light, "slider_enabled": bool(level),
                "ambient_enabled": bool(light), "csv_enabled": bool(level or light)}
    if decoded != expected:
        raise ValueError("固件解析结果与已检查的 CSV 不一致")
    checks = 1
    for position in range(SLIDER_MAX + 1) if level else ():
        if firmware.call(0x6021C0C0, position, SLIDER_MAX) != slider_brightness(level, position):
            raise ValueError(f"滑块位置 {position} 的原始固件输出与模型不同")
        checks += 1
    samples = {0, 1, 0x7FFFFFFF}
    for row in light:
        samples.update(value for value in (row[0] - 1, row[0], row[0] + 1) if 0 <= value <= 0x7FFFFFFF)
    samples.update((left[0] + right[0]) // 2 for left, right in zip(light, light[1:]))
    for lux in sorted(samples) if light else ():
        for dark in (False, True):
            if firmware.call(0x60008AAC, lux, int(dark)) != ambient_brightness(light, lux, dark):
                raise ValueError(f"照度 {lux}、夜间={dark} 的原始固件输出与模型不同")
            checks += 1
    return {"checks": checks, "decoded": decoded}


def verification_report(image):
    scenarios = {
        "no_files": (None, None),
        "slider_only": (DEFAULT_LEVEL, None),
        "ambient_only": (None, DEFAULT_LIGHT),
        "both": (DEFAULT_LEVEL, DEFAULT_LIGHT),
        "nonuniform_slider_positions": ("100,10\n200,50\n300,100\n", None),
        "crlf_and_unterminated_last_line": ("0,10\r\n254,100", "0,5,2\r\n100,20,10"),
        "one_row": ("0,10", "0,10,5"),
        "maximum_rows": ("".join(f"{i},{i * 10}\n" for i in range(20)),
                         "".join(f"{i * 100},{i * 4},{i * 2}\n" for i in range(50))),
    }
    cases = {}
    for name, texts in scenarios.items():
        result = verify_tables(image, *texts)
        cases[name] = {"checks": result["checks"], "decoded": result["decoded"]}
        for kind in ("level", "light"):
            cases[name]["decoded"][kind + "_rows"] = len(cases[name]["decoded"].pop(kind))
    hazards = []
    for name, text, lux in (
        ("descending_brightness", "0,100,50\n100,10,5\n", 50),
        ("missing_zero_lux_origin", "10,10,5\n100,50,25\n", 1),
    ):
        firmware = FirmwareBacklight(image, {"0:light.txt": text.encode("ascii")})
        try:
            parse_table(text, "light")
        except ValueError as error:
            rejection = str(error)
        else:
            raise ValueError("危险输入未被检查器拒绝")
        hazards.append({"case": name, "input": text, "lux": lux,
                        "firmware_day_u32": firmware.call(0x60008AAC, lux, 0),
                        "tool_rejection": rejection})
    return {
        "firmware": "BSC500 V1.04.003", "sha256": ORIGINAL_SHA256, "hardware_tested": False,
        "emulator": "Unicorn 2.1.4 / Cortex-M33",
        "executed": {"csv_initializer": "0x60004124", "csv_splitter": "0x600F8568",
                     "decimal_conversion": "0x6016811C", "slider_lookup": "0x6021C0C0",
                     "ambient_interpolation": "0x60008AAC"},
        "substituted_dependencies": ["文件打开／读取／关闭", "日志输出", "线程 errno 地址"],
        "libc_locale": "调用原始 0x6004FE60 取得字符表，按原启动逻辑填入 locale 指针。",
        "checks": sum(case["checks"] for case in cases.values()) + len(hazards),
        "cases": cases, "rejected_hazards": hazards,
        "limits": inspect_tables([], [])["limits"],
    }


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    commands = parser.add_subparsers(dest="action", required=True)
    inspect = commands.add_parser("inspect", help="检查 CSV 并输出离线计算样例，不需要固件或额外依赖")
    inspect.add_argument("--level", type=Path)
    inspect.add_argument("--light", type=Path)
    verify = commands.add_parser("verify", help="使用原始固件执行内置对照实验")
    verify.add_argument("application", type=Path)
    args = parser.parse_args()
    try:
        if args.action == "inspect":
            if not args.level and not args.light:
                parser.error("inspect 至少需要 --level 或 --light")
            level = parse_table(args.level.read_text(encoding="ascii"), "level") if args.level else []
            light = parse_table(args.light.read_text(encoding="ascii"), "light") if args.light else []
            result = inspect_tables(level, light)
        else:
            result = verification_report(args.application.read_bytes())
    except (ImportError, OSError, UnicodeError, ValueError) as error:
        parser.exit(1, f"{error}\n")
    print(json.dumps(result, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
