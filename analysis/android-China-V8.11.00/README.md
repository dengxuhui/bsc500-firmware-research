# Android 国内版 8.11.00 布局研究

BSC500 的 Android 布局研究以这一版为准。样本保存于 `.local/companion/iGPSPORT-China-V8.11.00.apk`，包名为 `com.igpsport.igpsportandroid`，versionCode 为 `28110000`，SHA-256 为 `4cc5cd29a7e3a48f2e17db885beaa96fad5af7b64558cceb08e8ccf06d396c3d`。 [应用宝下载页](https://a.app.qq.com/o/simple.jsp?pkgname=com.igpsport.igpsportandroid)

| 证据 | 结果 |
| --- | --- |
| `classes12.dex` 的 `Bg3PageConfigUtil` | BSC500 路由到 `Bg3PageStyleConfig` 的普通数据页和专项页样式。普通页上限为 12 个字段、6 行。 |
| `Bg3PageStyleConfig.getBg1PageStyleForGrids` | 5、7、9 项时，BSC500 选用对应的 BG2 表，其余项数选用 BG1 表。共 22 条内置普通页布局。 |
| `Bg3PageStyleConfig.getBg1PicturePageStyleForGrids` | 按页面类型选取地图、海拔、爬坡、爬坡地图、指南针、计圈、骑行台、赛段、训练课程、训练效果、组队骑行、音乐、相机 13 类列表，共 54 条内置专项页布局。 |
| `resources.arsc` 与 drawable selector | 从布局引用追到未选中状态的 PNG。76 条布局中 72 条有预览图，共 45 个不同的 PNG。普通页 1、2、11、12 项各有一个布局，未引用独立预览图。 |

## 仓库内的查阅入口

| 要查什么 | 仓库文件 |
| --- | --- |
| 查 BSC500 选用的布局、图表和预览尺寸 | [Bg3PageConfigUtil](sources/Bg3PageConfigUtil.java) |
| 查每个字段数和页面类型的样式、图标及行权重 | [Bg3PageStyleConfig](sources/Bg3PageStyleConfig.java) · [样式数据结构](sources/Bg3PageShowTypeBean.java) |
| 查 App 的设备页读取、编辑和写入流程 | [Bg3PageSettingViewModel](sources/Bg3PageSettingViewModel.java) · [电池字段列表](sources/Bg3PageField.java) |
| 查蓝牙页面读写与 Protobuf 字段号 | [ConfigurationDelegate](sources/ConfigurationDelegate.java#L320) · [`page_msg`](sources/Config.java#L7512) · [`config_msg`](sources/Config.java#L10432) |
| 查 JADX 无法还原的机型分支 | [classes12.dex 目标类反汇编](dex/page-style-dexdump.txt) |
| 查每个模板的参数和原图 | [布局目录与图片](../../web/assets/igpsport-cn-8.11.00/bsc500-layout-catalog.json) |

`page_mode` 和 `line_width` 从最低位起每行各占 3 位，分别表示该行字段数和相对高度配置。普通页模型的主要参数为 `page_type`、`data`、`page_mode`、`line_width` 和 `graphic_display`。页面列表放在 `config_msg` 的字段 `5`，骑行模式为字段 `19`。布局目录列出了每项资源的 APK 内路径、资源 ID 和哈希，目录同级包含网页使用的原始 PNG。

研究以上范围可以直接查阅仓库中的导出和目录。原始 APK 保存在本机作为来源样本。归档文字仅去掉了导出工具产生的行尾空白；JADX 标出的反编译缺口可对照所附 DEX 反汇编。 [归档文件哈希](manifest.json)

这份目录覆盖 APK 内置分支。BSC500 同时列在 `remoteConfigDevices` 中，实际联网时是否提供其他模板、是否替换内置配置仍待运行时验证。全球版 8.06.28 的分析单独保留在[历史归档](../android-V8.06.28/members.tsv)。
