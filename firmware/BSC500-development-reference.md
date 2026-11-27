# BSC500 固件研究与开发交接

复核日期 2026-11-26。主程序地址、默认页结构和地图底色实验以 **V1.04.003** 为基准。V1.08.005 的证据来自升级日志、USB 设置库快照和 iPhone 蓝牙抓包。本文引用的 Android 反编译证据来自全球版 8.06.28 的历史归档。当前 BSC500 的 Android 布局研究见[国内版 8.11.00 专档](../analysis/android-China-V8.11.00/README.md)。

## 研究覆盖

| 模块 | 已有证据 | 仍需闭合的环节 |
| --- | --- | --- |
| RSC 容器 | 4 个原厂样本可重复解包，BSC500 旧版 6 个条目的范围和哈希均可重算 | V1.08.005 原厂包及新版条目、设备安装和恢复条件 |
| 主程序启动 | 初始栈、复位指令、Flash 映射、设置表所在的启动数据块 | 具体 SoC、完整内存映射、其他核和 Boot 镜像 |
| USB KV 文件 | 37 项注册表、两份快照中的 32 个匹配键、值的跨块读取和 CRC | B-tree 索引与空闲块管理、旧记录选择规则、写入事务、分片语义 |
| 骑行数据页 | 10 条默认记录的 Thumb 回放、两类槽结构、App 字段字典、显示对象与格式化器之间的调用、V1.08.005 的蓝牙读写和回执、模式 0 的页序与行布局对照 | 写后落盘和设置到显示对象的调用链、真机字段支持范围、图形显示参数的屏幕效果、其他骑行模式 |
| 地图与导航 | 旧版地图画布底色执行到填充后端；新版导航设置值的逐字节差分 | 新版路线颜色字段与实际绘制之间的端到端证据、地图／路书格式 |
| 背光 | 两个 CSV 的读入分支与列含义在局部仿真中观察到 | USB 盘符对应关系、插值与实际亮度范围、真机效果 |
| Android 与升级 | APK 包目录、字段映射、通用文件传输方法、一次新版设备接收及重启日志 | BSC500 专属升级分支及能力协商、运行时传输和错误恢复 |
| 其他设备功能 | 用户卷目录、部分路径或日志线索 | 传感器、GNSS、FIT 记录、训练、通知、音频和各页面尚无完整实现级研究 |

历史 REA 留档是**按功能选点分析**。可见 8 份 Rizin 固件命令结果、3 份 JADX 方法或类结果，以及一份含 3 条记录的 APK 证据包。APK 包中运行时场景、捕获和未决项均为 0。此次的 MCP 未决项列表也为空。这些计数描述的是留档内容，无法证明覆盖率。反汇编窗口结束、返回码为 0、工具结果标为 `complete` 分别只说明所请求的操作已返回。

首次覆盖复核时，REA MCP 能打开旧版 raw 镜像并确认哈希，但 `read_bytes` 和 `analyze_function` 返回 `provider_unavailable`。该服务未配置 Ghidra 路径，其他候选原生提供器也不可用。MCP 的 Rizin 入口解析本机 `rizin` 时返回 `access_denied`。当时复用留档中定位到的本机 Rizin 0.9.1，通过 Python 和 Unicorn 2.1.4 验证。后续独立配置了 REA MCP 的 Ghidra 和 JADX 提供器，反编译结果和查询方法另见[离线反编译档案](BSC500-offline-analysis-archive.md)。

## 样本、地址与证据

| 样本 | 字节数或身份 | SHA-256 或参照 |
| --- | ---: | --- |
| 原厂旧版 RSC | 4,514,804 | `bfbf186494487f71d501ba6f0bafeba54fc9758879e43ad74d4c2f14203d7a0c` |
| 旧版 `BSC500_APP.bin` | 4,482,760 | `d7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933` |
| Android APK | 全球版 V8.06.28 | `8e2b447a1a840710b040695169bc3b6dde7a743ffae7d22b9910b8000587fae8` |
| V1.04.003 与 V1.08.005 KV | 各 38,912 字节 | [双版本快照哈希和日志行号](BSC500-V1.08.005-investigation.md) |

旧版主程序为小端 ARM Thumb，Flash 映射基址为 `0x60000000`。应用镜像中的文件偏移为 Flash 地址减该基址。第一个向量词是初始 SP `0x200042C8`，第二个是 `0x6006CBD1`，实际复位指令位于 `0x6006CBD0`，低位 1 表示 Thumb 状态。复位附近可见 `0x60075AB8` 和 `0x600007C0` 两个目标。设置 RAM 地址的初值来自启动数据重建，计算文件偏移时不能直接套用 Flash 地址公式。旧版镜像内有 `Hello RT-Thread!`、`SiFli Corporation`、`lcpu2hcpu_notification_callback`、TouchGFX 与 LV 路径，真实启动日志有 `touchx` 和 `Init LCD axs15231`。这些线索支持平台及组件定位，尚未确定具体 SoC 和所有 UI 页面所属的框架。[入口与字符串地址](../findings.json)

| 留档范围 | 本机 `.local/` 下的证据文件 | 地址或静态符号 |
| --- | --- | --- |
| 复位和启动表 | `native/rea-reset.json`、`native/rea-settings-registry.json` | `0x6006CBD0`、`0x6006CB2C`、`0x601517F4` |
| KV 装载路径 | `native/rea-kvlist.json` | `0x60132E80`、`0x6023A780` 附近的窗口 |
| 数据页默认值与分派 | `native/rea-page-defaults.json`、`native/rea-field-dispatch.json` | `0x60037B94`、`0x60165B4C`、`0x601C7EA4` |
| UI 对象线索 | `native/rea-ui.json`、`native/rea-ui-functions.json` | `0x600CC1A0`、`0x600C5638`，以及 `RideDataFields` 和 `DataPageConfig` |
| 地图底色调用 | `native/rea-map-background.json` | `0x6014D954`、`0x60258528` |
| APK 字段与固件传输 | `native/rea-android-field-ids.json`、`companion/rea-apk-evidence.json`、`companion/rea-firmware-transfer-{class,method}.json` | `PageConfigUtil.getGraphicalDataReplacedTextData`、`FirmwareUpdateDelegate.sendFirmwareFileToDevice` |
| 本次默认页回放 | `native/recheck-page-defaults.py`、`native/page-defaults-recheck.bin` | 从 `0x60037B94` 运行至返回，包含原固件的内存复制与清零逻辑 |

旧 REA 单项记录用 `data.subject` 核对目标哈希，`data.parameters` 和 `data.evidence_id` 追溯调用，Rizin 文本在 `data.raw_result.stdout`。路径可能指向先前工作区，应以 SHA-256 对照当前样本。历史 Rizin 用 ARM 16 位反汇编模式，以 `-m 0x60000000` 映射镜像，命令内使用 `e asm.cpu=cortexm`。这些地址应先按各自版本的镜像哈希校验。

## RSC 容器与资源

| 位置或字段 | 解析规则 |
| --- | --- |
| 原文件 `0x00` | `HD`，后接 little-endian `u16`，指向目录末端，也就是首个条目的物理起点 |
| 原文件 `0x04` | `CK`，后接 `u32`，等于文件从 `0x0A` 到 EOF 的字节和模 `2^32` |
| 原文件 `0x0A` | `OS` 加 `u16` 长度，BSC500 为 110；数据从 `0x0E` 开始 |
| OS 数据之后 | `SC` 加 `u16` 长度，BSC500 为 113；其数据从 `0x80` 开始 |
| SC 数据之后 | 编码载荷，BSC500 从 `0xF1` 起；名单和文件内容连续解码 |
| 解码后目录 | `PT 00 00`，反复读取 `NM` + `u16` 文件名长度 + UTF-8 名称 + `AD` + `u32` 累计结束偏移 |
| 文件范围 | 首条起始于 HD 记录的位置 `0x195`，每条结束于 `HD 值 + 本条 AD 值`，下一条从上一条结束处起，最终结束需等于 EOF |

载荷索引从 SC 数据之后的第一字节计为 0，并贯穿目录和全部条目。OS 循环表以表内索引计算相位，SC 循环表以其数据的原文件偏移计算相位。相位 `offset % 6 == 2` 用 `0x30` 异或，其余用 `0x32`。每个载荷字节先按 SC 结果的低三位循环右移，再异或对应 OS 掩码。旧版的共同周期为 12,430 字节。三个参考包 BSC300T V1.24、BiNavi Air V1.09.001、iGS800 V1.67 的清单也已逐项重算。

旧版包内是主程序、三个 ASCII 配置和两个图像相关二进制。`asset_version.cfg` 的完整内容是 `ASSET_BOOT_VER:112`。`patch_list.cfg` 具有 `名称:数字` 行，例子包括 `BSC500_APP:5`、`BSC500_BOOT:16`、`theme_cfg:4` 和 `extern_font:2`。这些名称可作为资源消费路径的搜索锚点，其数字枚举尚待确认；包目录无独立 Boot 图像。`road_book_cfg.cfg` 含 `roadbook_version:0`，随后列出五项带数值 ID、路线名称、距离和爬升的文本。两份图像资源符合 LVGL 4 位索引色格式，已解码为 PNG，细节见[离线反编译档案](BSC500-offline-analysis-archive.md#rsc-里的两个图像资源)。这些配置的消费位置仍待追踪。外层字节和是离线提取器检查容器完整性的条件，设备自身接收要求仍待验证。[解包器](extract_rsc.py) · [六个条目的范围和哈希](BSC500-V1.04.003-manifest.json)

## 设置注册表与 USB 值

启动表位于 `0x6006CB2C`。头两个有符号 `i32` 给出相对该地址的记录范围；每条 16 字节，顺序为源、目标、大小、处理函数，均为 little-endian `u32`。用于注册表的解压函数是 `0x6006CB34`，从 `0x604445C0` 读取，消耗到 `0x604466AC`（不含），向 `0x200042E0` 展开 17,632 字节。解压 token 低两位编码字面段长度加一的计数，高四位编码重复计数，为零时各取一个扩展字节。回溯长度为重复计数加 2，距离的短、长两式见脚本。重建 RAM 后，`0x20007DC8` 表存放以空指针结束的名称指针；`0x20008114` 表存放三个 `u32`，分别为高 16 位记录数与低 16 位 ID、单条大小、值的 RAM 地址。[解析实现](../native/read_settings_registry.py) · [完整注册表](../native/settings-registry.json)

| 键 | ID | RAM | 注册长度 | 用途或证据 |
| --- | ---: | --- | ---: | --- |
| `cfg_display` | 9 | `0x2004C5B0` | 76 | `0x0C` 起是 16 组 `[R,G,B,0]`，两份快照色板相同 |
| `cfg_navi` | 17 | `0x2004C6D0` | 16 | 值偏移 `0x08` 由 `DE` 变 `DF`；待建立选项语义 |
| `cfg_mode` | 27 | `0x2004C740` | 10 × 44 | 能见到 `outdoor` 和 `indoor`，两份快照重组结果相同 |
| `cfg_pages` | 28 | `0x2004C8F8` | 10 × 1,444 | 两份快照的逻辑值相同，详见下方 |

### 512 字节块与 CRC

两份数据库均为 76 个 512 字节块。文件第 0 块能读到 `<* KV database design by Btree *>`。以下局部格式已用快照复核：

1. 以带终止零的 ASCII 键名为起点 `K`，其后 12 字节可读为三个 little-endian `u32`。前两个分别表现为声明长度和实际存储长度。在已复核记录中，第三个值等于对实际存储值计算的 CRC-8/MAXIM-DOW，高 24 位为零。算法初值和最终异或均为 0，按最低位优先右移，反射多项式 `0x8C`，标准检查串 `123456789` 得 `0xA1`。
2. 这两份快照的已校验记录中，`K` 到内联区末尾容量为 108 字节，包含带终止零的键名和 12 字节头。短值在头后连续存储。长值填充内联区，`K + 108` 是 4 字节后续块 ID。
3. 后续块 ID 为 1 基，物理起点 `(ID - 1) × 512`。块首 4 字节是下一块 ID，余下 508 字节是值片段。零代表链结束。重组时按第二个长度截断末块，再验证 CRC。读取器应拒绝越界、环、被截断的链和无法确定身份的同名完整候选。
4. 两份快照各有 32 个键的声明长度与旧版注册表相符，其中 31 个实际长度与声明长度一致，另有两个 `cfg_user_info` 同名条目，声明均为 52，实际分别为 15 和 43 字节，对应 CRC `0x56` 和 `0xF9`。两个条目的选择及序列化关系尚未确定。注册表另有 5 个键在这两份快照中未找到匹配锚点。这里按已知键定位并校验值，完整索引遍历仍待实现。

| 键 | 值起点 | 长度 | 跨块指针位置 | 两份快照 |
| --- | --- | ---: | --- | --- |
| `cfg_pages` | `0x0F66` | 14,440 | `0x0FBC` | 先取 86 字节，首块 ID 从 31 变为 37；各 29 个溢出块，CRC 都是 `0xAD` |
| `cfg_mode` | `0x0E85` | 440 | `0x0EDC` | 先取 87 字节，后续块 ID 是 14；逻辑值一致 |
| `cfg_ant_ble_dev` | `0x08D8` | 2,304 | `0x0928` | 先取 80 字节，5 个溢出块；逻辑值一致 |
| `cfg_alert` | `0x0856` | 560 | `0x08AC` | 先取 86 字节，后续块 ID 是 48；逻辑值一致 |
| `cfg_display` | `0x0A2C` | 76 | — | 内联；CRC 从 `0x78` 到 `0x5B`，值偏移 `0x04` 从 `04` 到 `11` |
| `cfg_navi` | `0x0F31` | 16 | — | 内联；CRC 从 `0xDE` 到 `0x9D`，值偏移 `0x08` 从 `DE` 到 `DF` |
| `cfg_sound` | `0x5D1E` | 8 | — | 内联；CRC 从 `0x74` 到 `0xB1`，值偏移 `0x02` 和 `0x05` 变化 |

`cfg_statistics`、`cfg_save`、`cfg_inner_training` 也各有一个溢出块，两份快照的重组结果一致。31 个完整键中，仅 `cfg_display`、`cfg_navi`、`cfg_sound` 观察到值变化。`cfg_pages` 两版逻辑值的 SHA-256 均为 `d22f432bdae884efe814e6167e3fb24940f0ddd09c5d9f1ca644bf4da1047f2c`。过去直接从 `0x0F66` 连续截取的做法会把块链接、其他键和重排后的物理块混进页数据。开发差分工具时应显示物理偏移、逻辑值偏移和 CRC 三种信息。

下列最小示例复核这两份快照的 `cfg_pages`，从项目根目录运行。固定键偏移属于这两个样本。

```python
from hashlib import sha256
from pathlib import Path
from struct import unpack_from

def crc8(value):
    crc = 0
    for byte in value:
        crc ^= byte
        for _ in range(8):
            crc = (crc >> 1) ^ (0x8C if crc & 1 else 0)
    return crc

paths = [
    Path(".local/usb-copy/iGPSPORT/Settings/kvlist_fs.bin"),
    Path(".local/usb-v1.08.005.91zCom/kvlist_fs.bin"),
]
for path in paths:
    raw = path.read_bytes()
    assert len(raw) == 76 * 512
    assert raw[0x0F50:0x0F5A] == b"cfg_pages\x00"
    declared, stored, crc = unpack_from("<III", raw, 0x0F5A)
    assert declared == stored == 14440
    value = bytearray(raw[0x0F66:0x0FBC])
    block, = unpack_from("<I", raw, 0x0FBC)
    seen = set()
    while block:
        assert 1 < block <= len(raw) // 512 and block not in seen
        seen.add(block)
        start = (block - 1) * 512
        value.extend(raw[start + 4:start + 512])
        block, = unpack_from("<I", raw, start)
    assert len(seen) == 29 and 0 <= len(value) - stored < 508
    value = value[:stored]
    assert crc8(value) == crc == 0xAD
    print(path, sha256(value).hexdigest())
```

## 数据页默认结构与渲染

默认页回调 `0x602239B8` 以 `0x2004BB98` 为结构基址调用 `0x60037B94`。后者从基址 `+0xD60` 清零 `0x3868` 字节后填表，正好对应注册表中的页配置区域 `0x2004C8F8`。回调从注册表 `+0x14C` 取出值指针，经 `0x60065F48` 复制至调用方提供的缓冲区。此次回放使用原始镜像、映射 RAM、设置 SP 和返回断点，执行原始清零和复制例程。完整默认块 SHA-256 为 `eaa71e3a9f81b9ac5d8592237631346300ea15a8548d412acdd1cfa56813cbcc`。这份默认块与 USB 持久值是两个独立样本。

| 单条 `0x5A4` 记录偏移 | 长度或公式 | 原固件观察 |
| --- | --- | --- |
| `0x000` | 5 槽 × `0x38` | 初始化前 5 槽。槽头拷贝 `0x1C` 字节，随后写入 12 个 little-endian `u16` 字段值 |
| `0x118` | 21 槽 × `0x36` | 另外 21 槽。槽头也是 `0x1C` 字节，随后写入 11 个 `u16` 字段值 |
| `0x586` | 26 字节 | 默认初始化写入 `00 .. 19`，相同序列也出现在两份持久值的第 2 至第 10 条记录；具体业务含义待确认 |
| `0x5A0` | `u16` | 初始化显式置零；末端其余字节保持清零结果 |

第一类字段源 `0x602A6EA0` 起共 15 组，前 10 组分别用于记录 0、1，随后 5 组复用于记录 2 至 9。第二类统一使用 `0x602A7008` 的 21 组。两张表的源步长分别是 `0x18` 和 `0x16`。槽头从多张 `0x1C` 字节模板取值，随后有若干清除和设置低位的指令；低位的实际业务含义仍需在 App 和设备端做单项实验。以第一条记录第一槽为例，`+0x1C` 开始为 `0703 0501 0B0A 0101 0102 0605 0104 0101 0703 0501 0801 0901`，其中字典将 `0x0101` 对应速度、`0x0501` 对应骑行距离、`0x0703` 对应记录时间。初始化的槽数量与用户实际可见页面数量应分别验证。

可将下面的代码通过 `uv run --with unicorn==2.1.4 python -` 执行，复现默认块。`0x60450000` 仅用作仿真的返回哨兵，`0x2007FF00` 是本实验设置的栈位置。

```python
from hashlib import sha256
from pathlib import Path
from unicorn import Uc, UC_ARCH_ARM, UC_MODE_THUMB, UC_MODE_MCLASS
from unicorn.arm_const import UC_ARM_REG_LR, UC_ARM_REG_PC, UC_ARM_REG_R0, UC_ARM_REG_SP

image = Path('.local/firmware/BSC500-V1.04.003-unpacked/BSC500_APP.bin').read_bytes()
assert sha256(image).hexdigest() == 'd7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933'
cpu = Uc(UC_ARCH_ARM, UC_MODE_THUMB | UC_MODE_MCLASS)
cpu.mem_map(0x60000000, 0x451000)
cpu.mem_write(0x60000000, image)
cpu.mem_map(0x20000000, 0x80000)
cpu.mem_write(0x2004C8F8, b'\xAA' * 0x3868)
cpu.reg_write(UC_ARM_REG_R0, 0x2004BB98)
cpu.reg_write(UC_ARM_REG_SP, 0x2007FF00)
cpu.reg_write(UC_ARM_REG_LR, 0x60450001)
cpu.emu_start(0x60037B95, 0x60450000, count=300000)
assert cpu.reg_read(UC_ARM_REG_PC) == 0x60450000
value = cpu.mem_read(0x2004C8F8, 0x3868)
assert sha256(value).hexdigest() == 'eaa71e3a9f81b9ac5d8592237631346300ea15a8548d412acdd1cfa56813cbcc'
```

旧版显示路径在 `0x601C7EA4` 执行 `ldrh r1, [r4,#0x9C]`，随后以 `r0` 为输出对象、`r1` 为字段 ID、`r2=0` 调用 `0x60165B4C`。格式化器初始化 `0x50` 字节结果区，预填 `--` 字样，然后由 ID 高字节选择分派组。`0x60109E12` 给出圈指标的另一处分派地址。`0x600CC1A0` 的断言指向 `RideDataFields::create_view`，函数观察到对象 `+0x0C` 成员写入，以及通过对象表 `+0x30` 的间接跳转。这里只闭合了默认值生成和格式化两个局部调用链。

字段字典的 185 项源于 Android `PageField`、`Bg1PageField` 和 AAPT2 读取的资源标签。43 项在默认模板出现，47 项在模板或代码出现。APK 的 `getGraphicalDataReplacedTextData(int)` 还显示图形类 ID 范围 `0x0B01–0x0B03`、`0x0B04–0x0B06`、`0x0B07–0x0B0A`、`0x0B0B–0x0B0E` 分别回退到速度 `0x0101`、踏频 `0x0201`、心率 `0x0301`、功率 `0x0401`。这是 APK 方法的文本替换行为；固件支持情况仍按机型和界面核验。[字段字典和引用地址](../native/ride-field-ids.json)

## iPhone 蓝牙同步实测

2026-11-26 用 PacketLogger 记录官方 iGPSPORT iPhone App 与 BSC500 的通信。原始文件在 `.local/BSC500-iPhone-page-sync-2026-11-26.pklg`，共 5,792 条记录，其中 2,897 条为 HCI ACL 数据。SHA-256 为 `2ed89f8d5c665addcee534cf0adfc9f9d937c2f30e6cb3fc8649ecfa6e6ab056`。本次连接的 HCI 句柄为 `0x0056`，同一连接中读到了设备名 `BSC500` 和软件版本 `1.08.005`。

| 时间（UTC+8） | 操作 | 观察 |
| --- | --- | --- |
| 12:38:53.144–12:38:53.181 | 读取页面列表 | 请求指定骑行模式 `0`。码表返回 554 字节 Protobuf，包含 26 条页面记录，其中末尾 10 条未携带页面索引。编号 `8` 的爬升页首槽为速度 `0x0101`，`graphic_display` 为 `0`。 |
| 12:39:13.360–12:39:13.399 | 将首槽改成心率 | App 提交 16 条页面配置，首槽变成 `0x0301`，该页 `graphic_display` 为 `64`。码表返回状态 `0`。 |
| 12:39:44.200–12:39:44.239 | 将首槽改回速度 | App 再次提交 16 条记录，首槽恢复为 `0x0101`，提交的 `graphic_display` 仍为 `64`。码表返回状态 `0`。 |

复核时先按方向和连接句柄重组 HCI ACL 分片，取 L2CAP 通道 `0x0004` 的 ATT 数据。本次读写请求通过 ATT Write Command 写到属性句柄 `0x0010`，码表用属性句柄 `0x0012` 的 ATT Notification 返回数据。应用消息使用 20 字节头，主服务 `12` 为配置，子服务 `2` 为页面，操作 `2` 为读、`1` 为写。两次写入各有 531 字节 Protobuf，ATT 值分片为 244、244、63 字节，第一片包含消息头。页列表位于 `config_msg` 的字段 `5`，槽位 ID 位于 `page_msg` 的字段 `3`。比较两次写入的 Protobuf，唯一差异在零基偏移 `0x119`，字节从 `06` 变成 `02`，对应 varint `81 06`（心率）变成 `81 02`（速度）。成功状态的含义与 Android App 的回执解析一致。 [App 数据页读写](../analysis/android-V8.06.28/simple/ConfigurationDelegate.java#L603) · [App 回执解析](../analysis/android-V8.06.28/simple/IGPDeviceManager.java#L3963) · [字段字典](../native/ride-field-ids.json)

两次同步证实，App 用固件可以识别的字段 ID 配置设备页面。Android App 的同类消息还定义了页面类型、布局模式 `page_mode`、`line_width` 和图形显示 `graphic_display` 等参数。它们可以用于调查固件已有的显示选项。这次只主动改了数据字段，`graphic_display` 从读出的 `0` 变成两次提交的 `64`，没有在写入后再读回，也没有单独切换图形显示。因此，状态 `0` 只能确认命令成功，`64` 是否落盘、对应的屏幕效果，以及由 App 处理还是码表规则决定都待验证。现有页面同步没有观察到字体、任意坐标、自定义绘制程序或可加载主题。

验证图形样式时，在同一骑行模式和同一页保留字段 ID，只通过官方 App 切换一个可用的图形或布局选项。每次保存后重新读取，并用屏幕截图确认效果。真机能显示 Connect IQ 式原创页面的前提仍是找到设备端可加载和绘制这类页面的入口。

### 蓝牙布局与 USB 设置库对照

把 11 月 26 日蓝牙读取的模式 `0` 页面列表，与 11 月 25 日 V1.08.005 USB 快照中通过 CRC 校验的 `cfg_pages` 第 0 条记录对照。使用下表规则复原了 11 个有字段页面的全部 66 个字段 ID，以及每页的 `page_mode`、`line_width`。快照早于本次写入，所以下述对应关系来自蓝牙读取基线和已有存储值。

| 逻辑偏移或规则 | 对应含义 |
| --- | --- |
| 记录内 `0x586` 起的 26 字节 | USB 记录中的页序表。以蓝牙回复的列表位置为下标，取出的字节是零基物理页槽编号。例如回复中第 7 条记录的页面编号为 `8`，页序表第 7 个字节是 `7`，对应页槽 `7`。 |
| 槽 `0–4` 基址为 `槽号 × 0x38`，槽 `5–25` 基址为 `0x118 + (槽号 - 5) × 0x36` | 前五槽的协议字段 ID 从槽内 `+0x1C` 开始。其余已匹配的槽从 `+0x1E` 开始。ID 都按显示顺序存为 little-endian `u16`。后者 `+0x1C` 的字与协议字段 ID 单独存放，具体含义待确认。 |
| 槽内 `+0x00–0x17` 和 `+0x18` | 前 24 字节按每行 4 字节读取，`+0x18` 的低字节给出本样本的有效行描述数。每条用于数据字段的行描述，第 1 字节为行高权重，第 2 字节加 1 为字段数。已匹配的带字段特殊页首行，第 2 字节为 `2`，对照协议数据行时跳过该条。 |
| 蓝牙 `page_mode` 和 `line_width` | 两个整数分别按行打包字段数和行高权重，每行占 3 位，第一行用最低 3 位。Android App 的 `getPixelLcdPageDataStyleMap` 也按这个顺序拆分。 |

爬升页编号 `8` 对应逻辑槽首 `0x184`。三条行描述的前两字节依次为 `04 02`、`01 01`、`01 01`。后两条对应两行各两个字段、相同的行高权重，复原出 `page_mode=18` 和 `line_width=9`。字段从 `cfg_pages` 逻辑偏移 `0x1A2` 开始，依次为 `0101 0605 0602 0601`。V1.08.005 旧快照中首槽在文件物理偏移 `0x4950`，速度存为字节 `01 01`。如该结构在写入后保持相同，保存心率时对应的逻辑位置应是 `01 03`。物理偏移随数据库块分配变化。

本次尚未取得心率状态和改回速度后的 USB 设置快照。`graphic_display` 的存储位置、写入后的落盘值和其他骑行模式的对应关系待取样验证。 [Android 反编译归档](../analysis/README.md) · [两版 USB 快照](BSC500-V1.08.005-investigation.md)

## 地图、背光与升级

地图主题表 `0x20005F24` 对应 `map_classic` 和 `map_colorful`。旧版设置回调线索在 `0x600B95E6`，启动归一化在 `0x601E643A`，`0x60254DC4` 写入主题标记 `0x2000CC74`；外观标记是 `0x20005A1C`。实际验证从 `0x60258528` 开始，经过底色选择器 `0x6014D954`、RGB565 转换 `0x6014EC88`、矩形填充 `0x6014ECB0`，再到 `0x6015A944` 的后端分派。主题和外观分别注入，合成图形上下文用来读取后端参数。

| 画布状态 | 原色 | 原厂 RGB565 | 旧版镜像中的定位 |
| --- | --- | --- | --- |
| 经典浅色 | `#F6F6F6` | `0xF7BE` | Flash `0x6014D960`，向 `r1` 写入两条 Thumb 立即数指令 |
| 经典深色 | `#0F0F0F` | `0x0861` | Flash `0x6014D97C`，小端 32 位常量 |
| 彩色浅色 | `#F5F5F5` | `0xF7BE` | Flash `0x6014D96C`，向 `r0` 写入两条 Thumb 立即数指令 |
| 彩色深色 | `#000000` | `0x0000` | 分支直接返回零 |

经典浅色示例替换为 `#D5E8D4` 后，仿真的填充颜色为 `0xD75A`，后端收到的矩形为 `[7, 11, 326, 490]`。这组参数来自合成环境。后续地图要素绘制调用在 `0x6025855A`，路线、道路、文字和地图图层的配色仍待单独分析。[离线实现](../native/map_background.py) · [四状态验证](../native/map-background-verification.json)

背光初始化入口 `0x60004124`。打开滑块 `0:level.txt` 的调用在 `0x60004144`，打开照度 `0:light.txt` 的调用在 `0x60004168`，解析均经过分列函数 `0x600F8568`。局部仿真分别注入 `30,40\n` 和 `20,15,8\n`，观测到对应的两列、三列及 CSV 模式标志。设备日志的已有启动记录中两次打开都失败。已移除的旧版网页曾按滑块最多 20 行、照度最多 50 行做输入校验，生成器的数值边界是存储边界，实际显示取值及亮度曲线仍需验证。[背光仿真条件](../findings.json)

Android `FirmwareUpdateDelegate` 的通用文件重载读取原文件，为传输添加文件名、扩展名、总长、MD5 和续传位置等元数据，再把原文件字节交给传输命令。类清单还含 `updateFirmware` 与另一 `sendFirmwareFileToDevice` 重载；实际 BSC500 选择哪个分支尚未闭合。真机日志记录临时接收文件 `0:/BSC500.rsc.tmp`、包大小 5,565,852、`asset_decode_update_app` 返回 1，以及主程序升至 108005 后 Boot 从 112 到 114 的顺序。旧版应用在 `0x60227EBC` 附近的清理逻辑跳过两个 RSC 文件名。现有证据未确定新版包组成、直接由 USB 触发的条件、同版本重装或启动失败后的恢复行为。[新版日志证据](BSC500-V1.08.005-investigation.md) · [旧版文件名引用](../findings.json)

## 开发接口与验证

以下是已移除研究工作台的历史文件契约，供研究记录使用。

| 历史入口 | 历史文件契约 |
| --- | --- |
| 旧版数据页设计器 | `bsc500-page-design` v1；`name`、`layout`、`palette`、`fields`；字段是已知 `id`、`kind: intensity` 或带 `label` 的 `kind: unknown`；1–12 项 |
| 网页骑行设置 | `bsc500-settings-plan` v1；`mode`、`page`、有顺序的字段名称、`settings`；外观、单位、亮度、熄屏、自动暂停、计圈、北向上、缩放、道路名、重算的值取自表单选项 |
| GPX 导入导出 | 只接受一条路线或单段轨迹，至少两点，保留经纬度和可选海拔；导出 GPX 1.1 单段轨迹，由官方 App 导入 |
| 背光 | 导出 `level.txt` 或 `light.txt`，为逗号分隔整数并以换行结尾 |
| 地图底色 | 输入严格匹配原厂旧版 RSC 或应用镜像的 SHA-256；输出 `.local.bin` 供离线比对 |

将预览方案带入骑行设置时，目前按字段顺序转为人可读名称，用户再通过官方 App 同步；图形和样式留在设计 JSON。后续扩展前端时保留已有 v1 的回读路径，明确区分模板或代码中的字段引用、App 按机型提供的字段、以及真机实际可显示的字段。旧版研究工作台及其专用前端源码已移除；本节保留历史方案格式的研究记录，当前网页仅接受设备配置编辑器导出的页面方案或配置备份。

从项目根目录运行以下命令。四份 RSC 清单、注册表及地图示例报告均已重跑并与现有 JSON 逐字节一致。

```bash
firmware_dir=.local/firmware
original="$firmware_dir/BSC500-V1.04.003-unpacked/BSC500_APP.bin"
diff -u firmware/BSC500-V1.04.003-manifest.json \
  <(python3 firmware/extract_rsc.py "$firmware_dir/BSC500-V1.04.003.rsc")
diff -u native/settings-registry.json \
  <(python3 native/read_settings_registry.py "$original")
diff -u native/map-background-verification.json \
  <(uv run --with unicorn==2.1.4 native/map_background.py verify \
    "$original" "$firmware_dir/BSC500_APP-classic-light-D5E8D4.local.bin" --color '#D5E8D4')
for reference in "$firmware_dir"/reference/*.rsc; do
  diff -u "${reference%.rsc}-manifest.json" <(python3 firmware/extract_rsc.py "$reference")
done
```

| 后续工作 | 开发输入 | 验收证据 |
| --- | --- | --- |
| KV 只读解析器和差分工具 | 两份 USB 副本、旧版注册表、块链与 CRC 规则 | 校验并重组 31 个完整键，报告两个同名条目；`cfg_pages` 比较相同；清楚报告环、越界、歧义和 CRC 不符 |
| 打通数据页设置到渲染 | 同一固件，同一模式，仅替换一个字段，取 A、B、回到 A 三份快照；重点定位 `cfg_pages` 到对象 `+0x9C` 的写入 | 逻辑偏移可复原；找到持久值、活动配置、对象字段与格式化输入之间的地址或可回放调用链；屏幕字段随之改变 |
| 明确路线颜色字段 | V1.08.005 同一条路线，只变官方路线颜色，按 A、B、A 比对逻辑 KV，并保留屏幕观测 | 候选值随颜色改变并复原，能区分路线描线、画布底色和色板；拿到新版镜像后对应到读值与绘制分支 |
| 复核新版镜像 | 从厂商或既有合法渠道得到 V1.08.005 原厂包及哈希 | 输出条目差分，重新定位新版地址，对当前依赖的调用链给出证据 |
| 验证背光 CSV | 确认盘符和有效测试范围，以真机启动日志与光照条件作对照 | 两文件分别可达，合法单行被实际使用，空文件和异常行的处理可复核 |

后续新增结构或字段应同时记录固件版本、哈希、地址／偏移、字节宽度、枚举来源和验证输入。逻辑值比较以通过 CRC 的重组结果为准。当结论来自默认值、静态引用或真机控制实验时，在字段表中写明对应证据。
