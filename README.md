# BSC500 固件研究

针对 iGPSPORT BSC500 V1.04.003 的离线研究。仓库提供 RSC 解包工具、设置表解析器，以及可修改并验证地图画布底色的脚本。所有命令都在电脑上处理文件。

## 网页设置工作台

用浏览器打开 [web/index.html](web/index.html) 即可使用。页面无安装依赖，所选文件在浏览器本地处理。若浏览器限制本地文件的 SHA-256 校验，可在项目根目录运行 `python3 -m http.server 8765 --bind 127.0.0.1 --directory web`，然后打开 [本机网页](http://127.0.0.1:8765/)。

- **数据页设计器**提供原创预设、320 × 480 比例实时预览、字段排序、配色和版式。可粘贴参考数据页的中英文字段名，下载或重新打开设计 JSON。点「填入骑行设置」可按顺序带入有固件引用的候选字段，再通过官方 App 手动设置并同步。未识别的字段和示例强度条保留在设计文件中。网页布局与绘制样式保留在设计器中，当前尚无 BSC500 真机加载入口。
- **骑行设置**支持配置一张骑行数据页并实时预览，也可保存显示、骑行和导航选项。JSON 可再次打开编辑，应用到码表时按方案在官方 App 和设备菜单中设置。
- **导航路线**支持编辑坐标、载入一条连续的 GPX 并导出新 GPX。把 GPX 导入 iGPSPORT App 后由 App 发送至码表。导出保留坐标及海拔。
- **背光曲线**导出 `level.txt` 和 `light.txt` 供离线验证，实际 USB 导入位置与有效取值仍待确认。
- **地图底色**接受哈希匹配的 V1.04.003 原厂 RSC 或原始 `BSC500_APP.bin`，导出用于离线验证的 `.local.bin` 应用镜像。真机安装与恢复流程仍待验证。

## 可以修改的设置

| 类别 | 已确认的修改入口与范围 |
| --- | --- |
| 骑行数据页 | 码表「更多 → 骑行模式」可启用或隐藏页面、设定主页、调整提醒和自动功能。iGPSPORT App 可按骑行模式添加、编辑和启用数据页，选择数据字段。产品页标注单页最多 12 项。 |
| 显示和系统 | 码表「系统 → 显示」可调手动或自动亮度、自动熄屏，以及自动／浅色／深色模式。系统中还可配置声音、GPS 模式、语言、时区、时间格式和公英制。 |
| 地图与导航 | 可设北向上、自动缩放、显示道路名称、偏航重算和转弯提示。官方还支持通过 USB 更新地图与路由文件。 |
| 训练与传感器 | App 可设置最大或静息心率、FTP 和训练区间。码表可配对 ANT+ 或蓝牙传感器。 |

[厂商说明书镜像](https://device.report/m/2e3c0800a266d01d5e6c5724e7b538c66d06cb2d9677344efbd20e5f66c1ebb2) · [BSC500 产品页](https://www.igpsport.cn/product/bsc500) · [官方地图更新说明](https://www.igpsport.com/support/product/map?id=1076&name=BSC500)

固件中恢复了 37 个配置键。本机 USB 副本中的设置库有 32 个键能匹配固件记录长度，其中 `cfg_pages` 是 10 条 1,444 字节的记录，`cfg_display` 是 76 字节。设备日志也记录了数据页、显示和导航配置的保存。这些证据适合用来对比通过官方入口做单项修改前后的快照。直接编辑设置库所需的字段布局、写回校验和加载行为仍待验证。App 的 185 个字段 ID 中有 47 个在 BSC500 模板或代码中出现，完整支持清单仍待确认。[设置表](native/settings-registry.json) · [骑行字段字典](native/ride-field-ids.json)

背光初始化会尝试读取 `0:level.txt` 和 `0:light.txt`。离线执行固件的 CSV 读取和分列逻辑，确认前者接收「滑块位置、亮度」，后者接收「照度、白天亮度、夜间亮度」。模拟中任一文件载入有效数据即可开启 CSV 模式。现有真机启动日志显示两个文件均未打开，CSV 模式关闭。仿真使用模拟文件读写和十进制转换，真机文件位置、有效取值和实际显示效果仍待验证。[证据与地址](findings.json)

## 官方更新与恢复

1. 正常可开机时，先保存并同步骑行记录，保留关键设置截图。给码表充足电量，手机与码表靠近，并保持手机联网和蓝牙开启。
2. 打开 iGPSPORT App → 设备 → 固件更新。以 App 为这台设备提供的版本为准。说明书给出的蓝牙更新时间最长约 20 分钟。升级进行中保持连接，等 App 提示完成，再核对设备版本、数据页、地图和传感器。
3. 如果升级未完成但码表还能正常启动，在设备和 App 均已退出更新状态后，可按说明书正常重启，再通过 App 检查更新。如果持续循环重启、无法开机或无法连接 App，向官方售后索取适用于硬件版本和地区的有线恢复流程，并确认能否重装同版本以及需保留哪些数据。系统里的「重置」会恢复设置和骑行模式并保留历史，「删除并重置」会清除历史。两项的手册说明均涉及用户数据和设置，尚无重装固件的证据。

[厂商说明书镜像](https://device.report/m/2e3c0800a266d01d5e6c5724e7b538c66d06cb2d9677344efbd20e5f66c1ebb2) · [官方售后](https://support.zh.igpsport.com/aftersale/service/product/contact)

2026-11-25 复核厂商英文固件接口，其返回 BSC500 `V1.04.003`，4,514,804 字节。重取厂商包的 SHA-256 与项目清单一致。包中可见应用和资源，共 6 个条目，未看到独立的 Bootloader 镜像。现有 USB 副本覆盖文件层，可用于核对部分用户数据。完整分区备份、同版本重刷、降级和无法启动时的恢复仍未验证。[厂商元数据](https://prod.en.igpsport.com/service/sportg/device4j/soft-version/deviceFirmwareDetails?language=en&softTypeId=1076) · [RSC 清单](firmware/BSC500-V1.04.003-manifest.json)

应用代码在文件清理分支中比较并跳过 `0:/BSC500.rsc` 和 `BSC500.rsc`，说明固件会保留这个文件名。这个分支的行为无法确定安装触发条件、USB 是否为入口、升级校验或故障恢复能力。改过的地图镜像目前只通过离线绘制测试。真机试刷前，先在同型号、同硬件版本的备用机上验证厂商的原厂恢复流程，并确认设备不能启动主应用时仍可进入恢复。[证据与地址](findings.json)

## 在本地修改地图背景

将自行取得的厂商升级包放在 `.local/firmware/BSC500-V1.04.003.rsc`。下面的命令需要 Python 3，最后一步使用 uv 安装 Unicorn 并模拟执行绘制代码。第一次解包时，目标目录须为新目录。

```bash
mkdir -p .local/firmware
python3 firmware/extract_rsc.py .local/firmware/BSC500-V1.04.003.rsc \
  --extract-to .local/firmware/unpacked

original=.local/firmware/unpacked/BSC500_APP.bin
edited=.local/firmware/BSC500_APP-D5E8D4.local.bin

python3 native/map_background.py patch "$original" "$edited" --color '#D5E8D4'
uv run --with unicorn==2.1.4 native/map_background.py verify \
  "$original" "$edited" --color '#D5E8D4'
```

`--color` 接受六位 RGB 值。默认修改经典地图浅色背景。脚本也支持 `--theme classic --appearance dark` 和 `--theme colorful --appearance light`。彩色地图深色分支直接返回零，目前只纳入验证。脚本核对原始镜像的 SHA-256，并要求输出文件名可用。

工作区已有解包结果时，可将 `original` 指向现有的 `BSC500_APP.bin`。原始包与解包文件的哈希见 [RSC 清单](firmware/BSC500-V1.04.003-manifest.json)。

## 地图绘制与验证

应用镜像以 `0x60000000` 为基址，使用 ARM Thumb 指令。地图画布在 `0x60258528` 调用底色选择器 `0x6014D954`，随后将 RGB888 转成 RGB565，并将颜色交给矩形填充后端。

示例将经典地图浅色底色从 `#F6F6F6` 改成 `#D5E8D4`。两条配色指令从镜像偏移 `0x14D960` 开始，文件有 5 个字节发生变化。[离线验证记录](native/map-background-verification.json) 包含原始与修改镜像的哈希、字节差异和四种状态下的填充参数。验证使用构造的图形上下文运行固件绘制代码，记录后端收到的矩形和颜色。尚未执行真机测试或生成可刷写的升级包。

## 其他研究结果

- [V1.08.005 对比调查](firmware/BSC500-V1.08.005-investigation.md) 记录两版 USB 文件差异、路线颜色的设置线索，以及主程序和 Boot 版本变化。
- [结构与地址](findings.json) 记录 RSC 容器、启动入口、字段格式化、地图主题与绘制函数。
- [设置表](native/settings-registry.json) 记录从启动初始化数据恢复的 37 个配置键。可用 `python3 native/read_settings_registry.py "$original"` 重新生成。
- [骑行字段字典](native/ride-field-ids.json) 记录 Android App 中的字段 ID 及其固件引用。固件引用可辅助定位 BSC500 的现有字段。

把厂商固件、APK、设备导出、原始日志和分析工具的原始输出放在 `.local/`。该目录由 Git 忽略。项目代码及文档使用 [MIT 许可证](LICENSE)。
