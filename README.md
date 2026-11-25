# BSC500 固件研究

针对 iGPSPORT BSC500 V1.04.003 的离线研究。仓库提供 RSC 解包工具、设置表解析器，以及可修改并验证地图画布底色的脚本。所有命令都在电脑上处理文件。

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

- [结构与地址](findings.json) 记录 RSC 容器、启动入口、字段格式化、地图主题与绘制函数。
- [设置表](native/settings-registry.json) 记录从启动初始化数据恢复的 37 个配置键。可用 `python3 native/read_settings_registry.py "$original"` 重新生成。
- [骑行字段字典](native/ride-field-ids.json) 记录 Android App 中的字段 ID 及其固件引用。固件引用可辅助定位 BSC500 的现有字段。

把厂商固件、APK、设备导出、原始日志和分析工具的原始输出放在 `.local/`。该目录由 Git 忽略。项目代码及文档使用 [MIT 许可证](LICENSE)。
