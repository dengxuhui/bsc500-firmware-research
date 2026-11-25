# BSC500 离线反编译档案

固件伪代码、Android 反编译文本和查阅索引已纳入项目 `analysis/`。从项目检出这些文件即可离线查阅。原厂 RSC、APK、USB 快照和分析工具置于 `.local/`。结构、调用地址及实验结果见[开发交接](BSC500-development-reference.md)。

## 如何查阅

从仓库根目录查询。函数索引中，`suggested_pseudocode` 的第一段是分析配置目录，其余部分是相应压缩包内的成员路径。

```bash
archive=analysis/BSC500-V1.04.003
rg -ni '60037b94|formatridefield|selectmapbackground' "$archive/function-index.tsv"
rg -ni '60165b4c' "$archive/native-full/calls.tsv"
tar -xOzf "$archive/native-mcp-bulk/pseudocode.tar.gz" pseudocode/0x60037b94.c
tar -xOzf analysis/android-V8.06.28/sources.tar.gz \
  sources/com/igpsport/blelib/bean/Config.java
```

需要全文搜索时，可将压缩包解到 Git 忽略的本地目录。

```bash
mkdir -p .local/analysis-view
tar -xzf analysis/android-V8.06.28/sources.tar.gz -C .local/analysis-view
rg -n 'requestWritePageList|writePage\(' .local/analysis-view/sources -g '*.java'
```

开发中引用旧版 Flash 地址时，用 `地址 - 0x60000000` 定位原厂 `BSC500_APP.bin` 的文件偏移。Ghidra 的导入载体 `bsc500-analysis.elf` 在文件偏移 `0x1000` 放入完整固件镜像，因此同一地址的 ELF 文件偏移多 `0x1000`。生成脚本 `.local/rea-archive/make-analysis-elf.py` 会校验原厂 SHA-256 和载体内的字节一致性。载体的 ELF 节、入口和权限用于反编译导入，Flash 内容来自原厂样本。

REA 对 headerless ARM 的通用 raw 模式固定采用 ARMv7 语言，项目使用小端 Cortex Thumb 配置分析上述 ELF。载体仍按 `0x60000000` 加载，保留低位为 1 的复位向量 `0x6006CBD1`。分析种子见 `analysis/BSC500-V1.04.003/ghidra-seeds.tsv`，其中命名为人工标注。全局名称、自动创建的函数边界、推导类型和间接调用均需结合原始字节判断。

## V1.04.003 主程序

REA MCP 已对其函数清单中的 **13,045 个镜像内函数**逐个请求伪代码，13,045 项均返回可保存文本。另有 15 个外部符号列在清单里。21 个重点函数另有完整 REA 档案。直接用 Ghidra 默认配置补扫得到 15,463 份伪代码和 65,805 条静态调用引用。按 Rizin 候选入口约束再扫，Ghidra 创建或保留的函数集合中有 15,176 份伪代码，11 项反编译失败。三组结果按地址去重后共有 18,814 个索引项，其中 18,804 个有至少一份伪代码。不同配置可能把代码边界划在不同位置，尤其是只有 Rizin 候选扫描报告的地址；合并数量用于查档。

以下路径相对于 `analysis/BSC500-V1.04.003/`。

| 文件 | 用途 |
| --- | --- |
| `function-index.tsv` | 按 Flash 地址合并三种函数扫描结果，标出来源与伪代码路径 |
| `native-entrypoints/procedures.json` | REA MCP 对整个镜像的函数清单，保留外部符号、thunk 和分析配置 |
| `native-entrypoints/functions/*.json` | 21 个有地址依据的函数档案，包含伪代码、汇编、基本块、调用者、被调用者及未解析调用 |
| `native-mcp-bulk/functions.tsv` | 逐个调用 REA MCP 后的状态与证据 ID |
| `native-mcp-bulk/pseudocode.tar.gz` | REA MCP 返回的函数文本 |
| `native-full/functions.tsv`、`calls.tsv`、`pseudocode.tar.gz` | 默认分析配置的 Ghidra 批量导出，包含每个函数的体长、指令数、伪代码状态及静态调用边 |
| `native-candidates/functions.tsv`、`calls.tsv`、`pseudocode.tar.gz` | 用 Rizin 发现的候选入口补查，保存入口状态、静态调用指令和 Ghidra 伪代码 |

在默认 REA 启动配置达到十分钟时限后，批量 MCP 使用以下 Cortex Thumb 配置。这会影响入口发现、间接跳转和交叉引用覆盖。对该配置识别出的函数均逐一请求了伪代码。

- `ARM:LE:32:Cortex`，编译器配置 `default`
- 禁用 `Reference`、`ARM Constant Reference Analyzer` 和 `Decompiler Switch Analysis`
- 包含 23 个函数入口和 2 个表标签的种子文件，实际配置摘要和哈希可见函数档案
- Rizin 补充扫描使用 ARM Thumb 的 `aac` 候选表。Ghidra 对这些入口逐项标记 `created`、`existing`、`overlap`、`undecodable` 或 `not-created`，该记录位于 `native-candidates/seeds.tsv`

重点入口可按地址查看。Ghidra 的 `InitializePageDefaults` 在 `0x60037B94`，其函数体地址集合区分指令和嵌入的跳转表。地图底色入口 `0x6014D954` 的伪代码确认为两个 RAM 标记和四个固定色值，能与前一次 Unicorn 回放交叉检查。显示格式化入口 `0x60165B4C` 可查各类型的分派和输出，动态跳转、间接调用及各种字段的真机语义仍需实验确认。

### 骑行页消费和渲染的定位

从原始镜像读取 `0x60027ECC`、`0x600464F0`、`0x6015BD20`、`0x6015C51C` 和 `0x6024CA94` 处的小端指针，结果都是设置结构基址 `0x2004BB98`。结合各函数的指针运算，可缩小继续研究的范围。

| 旧版地址 | 静态观察 |
| --- | --- |
| `0x600272DC` | 某一分支以记录索引和 `0x5A4` 步长，从结构 `+0xD60` 一侧复制整条页配置；相邻代码还复制步长为 `0x2C` 的模式记录 |
| `0x600461AC` | 根据上下文索引选 `0x5A4` 字节页记录，遍历 `+0x586` 的 26 个索引。索引小于 5 时用 `0x38` 步长，并读取槽内 `+0x1C` 的 `u16` ID；另一分支使用 `0x36` 步长。代码会对一个以 `0x0B01` 起始的 ID 区间设置图形字段标记 |
| `0x6015BB8C` | 同样从设置基址按 `0x5A4`、`0x38` 和 `0x36` 的组合定位配置，是后续追显示对象写入点的候选 |
| `0x60165B4C` | 默认分析调用表找到 38 个对格式化器的静态调用位置，归属 16 个函数。此表是静态解析结果，仍需对照已知显示对象 `+0x9C` 的加载位置和实测数据 |

`0x60165B4C` 的完整 REA 函数档案超过单条 MCP 响应上限，已从同一会话的证据快照提取。默认分析的静态调用表和 REA 精简配置的调用者集合可能不同，分析时应保留各自来源。现有证据还没有闭合从 App 消息接收、`cfg_pages` 保存到活动显示对象的整条链路。

## Android V8.06.28

以下路径相对于 `analysis/android-V8.06.28/`。

| 文件 | 用途 |
| --- | --- |
| `evidence/package.json` | REA MCP 包摘要，标识 `com.qiwu.worldwide.ride`、versionCode `28062800`、min SDK 26、target SDK 35 |
| `evidence/classes-*.json` | REA MCP 的类名搜索，`pagesetting` 包前缀命中 214 项，`Firmware` 命中 44 项 |
| `evidence/*.class.json` | 5 个重点类的静态成员清单 |
| `evidence/*.0.json`、`evidence/*.1.json` | 7 个重点方法或重载的 REA MCP 输出，包含 DEX 签名、Java 伪代码和静态引用证据 |
| `sources.tar.gz` | 使用同一 JADX JAR 批量导出的 Java 文本 |

数据页的 App 路径从 `GenericPageSettingViewModel` 开始。`requestPageList` 取当前骑行模式的 `modeIndex` 传给 `IGPDeviceManager.readPage`，返回的静态类型含 `List<Config.page_msg>` 和 `Config.cur_page_status_msg`。`requestWritePageList` 取当前选中的页面方案，为每页设置 `pageIndex`、`pageName`、`dataSite=0`、开关 `status`、`pageType`、`pageMode`、`lineWidth`、`graphicDisplay` 和可选的 `mainPageStatus`。它按顺序取出 `CommonDataFieldBean.value` 填入重复字段 `data`，再连同模式索引交给 `IGPDeviceManager.writePage`。这给出了 App 模型到协议对象的静态映射。协议对象到旧版 `cfg_pages` 或实时渲染对象的固件接收与写入仍待关联。`IGPDeviceManager` 的两方法进一步转给 `ConfigurationDelegate`，由它产生 `Config.config_msg`，设置 CONFIG 服务、PAGE 子类型和 GET／SET 操作，再由 `ComputerConfigureServiceFactory.getMessage` 生成传输对象。设备代际分支会影响发送内容的组合方式，所以不能仅凭通用方法推定当前型号使用的分支。两类的 JADX SIMPLE 输出在 `simple/`。

在 JADX 导出的 `com/igpsport/blelib/bean/Config.java` 中，`page_msg` 是生成的 Protobuf 消息。在这份 APK 中可以按下面的字段号检查一页的含义。字段号也可用于对照固件的消息描述表。

| 字段号 | 名称 | App 写入时的来源 |
| ---: | --- | --- |
| 1 | `pageIndex` | 页面索引 |
| 2 | `status` | 页面开关，0 或 1 |
| 3 | `data` | 按显示顺序传入的重复整数 ID |
| 4 | `dataSite` | 通用写入方法赋 0 |
| 5 | `pageName` | 页面名称 |
| 6、7 | `pageType`、`pageMode` | 页面类型和模式 |
| 8 | `mainPageStatus` | 非空时写入主页标志 |
| 9、10 | `lineWidth`、`graphicDisplay` | 缺省时写入 -1 |
| 11 | `forbiddenClose` | 生成的消息声明存在；上述通用写入方法未设置 |

原始 APK 的 ZIP 目录另存 `members.tsv`，包含 6,391 个条目，其中 10 个 DEX，26 个原生 `.so`，包括两个 ABI 各自的 `libapp.so` 和 `libflutter.so`。成员表记录条目路径、未压缩长度和 ZIP CRC32，仅描述包内容。

类名搜索 `BSC500` 没有命中，覆盖只包含 JADX 提供的类名。机型枚举、字符串、资源、通用能力分派及运行时选路仍需用对应证据判断。APK 均作静态分析。JADX 识别 40,679 个顶层类，默认批量导出进程以状态 1 退出，实际留有 38,064 个 Java 文件。两次 REA 整类查询超时，保留了错误结果，相关的大类已用 JADX SIMPLE 配置补导。遇到缺失、告警或伪代码跳转时，应回查原 APK 的 DEX。原生库、Flutter AOT 和运行时行为留在当前档案覆盖范围之外。

## RSC 里的两个图像资源

两份文件均符合 LVGL v8 的 4 位索引色图像布局。小端 `u32` 头的低 5 位为格式 9，位 10–20 为宽，位 21–31 为高。偏移 4 起有 16 项每项 4 字节的 BGRA 色板。像素从偏移 68 起，每行 `(width + 1) // 2` 字节。偶数 x 取高半字节，奇数 x 取低半字节。两份文件长度都等于 `68 + stride × height`。这说明文件布局与上游编码一致，固件内加载位置仍待关联。[LVGL 图像头与格式枚举](https://github.com/lvgl/lvgl/blob/v8.3.11/src/draw/lv_img_buf.h) · [LVGL 索引像素读法](https://github.com/lvgl/lvgl/blob/v8.3.11/src/draw/lv_img_buf.c)

| 固件文件 | 图像宽高 | 每行字节 | 解码结果（相对于固件档案目录） |
| --- | --- | ---: | --- |
| `anatel_image.bin` | 200 × 55 | 100 | `resources/anatel_image.png` |
| `im_about_auth_icon.bin` | 295 × 161 | 148 | `resources/im_about_auth_icon.png` |

解码后的 PNG 保留色板 alpha，另提供 `*-on-dark.png` 供查看浅色图案。

## 保存和校验

`analysis/archive-manifest.json` 记录归档文件和原始样本的 SHA-256。原工作区的 `.local/rea-archive/` 还保留了 Ghidra 工程、REA 会话快照和导出日志。Ghidra 工程含导入的固件字节，复核原始字节时可使用该工程或厂商镜像。

分析工具使用 REA 6.7.0、Ghidra 12.1.2、Temurin 21.0.13.1+2、Rizin 0.9.1 和 JADX headless MCP 0.7.1。Ghidra 与 Java 用项目内独立环境启动。反编译和快照中保留了自动分析的诊断，出现类型占位、解码警告或外部目标时，应核对汇编、镜像和回放证据。[Ghidra 12.1.2 发布说明](https://github.com/NationalSecurityAgency/ghidra/releases/tag/Ghidra_12.1.2_build) · [Eclipse Temurin 21 发布说明](https://github.com/adoptium/temurin21-binaries/releases/tag/jdk-21.0.13.1%2B2)

## 版本范围

本地具备 **V1.04.003 主程序**的可反编译样本。**V1.08.005** 当前只有设置快照及升级日志，所以新版路线颜色、Boot 和主程序的实现仍缺二进制证据。复查厂商公开接口返回的仍是 V1.04.003，大小 4,514,804 字节。后续获得新版主程序时应另存其哈希和函数索引。[V1.08.005 调查](BSC500-V1.08.005-investigation.md)
