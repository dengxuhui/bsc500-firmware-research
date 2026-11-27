"use strict";

// Web Bluetooth 只负责已确认的 NUS 页面配置通道。所有写入由用户确认后发起。
(function (root) {
  const P = root.BSCProtocol;
  const DIAGNOSTICS_VERSION = "ble-notify-init-v5";
  const UUID = {
    service: "6e400001-b5a3-f393-e0a9-e50e24dcca9e",
    write: "6e400002-b5a3-f393-e0a9-e50e24dcca9e",
    notify: "6e400003-b5a3-f393-e0a9-e50e24dcca9e",
  };
  // APK 的 initCharacteristic 与原始抓包都启用这四个通知通道。
  // 附加通道只订阅、不写命令；未确认的 3e 服务不纳入初始化。
  const AUXILIARY_CHANNELS = ["8e", "7e", "6e"].map((key) => ({
    key,
    service: UUID.service.slice(0, -2) + key,
    notify: UUID.notify.slice(0, -2) + key,
  }));
  const errorText = (error) =>
    `${error.name || "Error"}: ${error.message || String(error)}`;
  const properties = (characteristic) =>
    ["read", "write", "writeWithoutResponse", "notify", "indicate"]
      .filter((name) => characteristic.properties?.[name])
      .join(", ") || "未报告";
  const dump = (bytes) =>
    `${P.hex(bytes.slice(0, 512))}${bytes.length > 512 ? `…（省略 ${bytes.length - 512} 字节）` : ""}`;
  class BSCBluetooth extends EventTarget {
    constructor() {
      super();
      this.device = null;
      this.writer = null;
      this.reader = null;
      this.pending = null;
      this.requestNumber = 0;
      this.notificationNumber = 0;
      this.auxiliaryNotificationNumber = 0;
      this.notificationListeners = [];
      this.stream = new P.FrameStream();
      this.onData = (event) =>
        this.receive(
          new Uint8Array(
            event.target.value.buffer,
            event.target.value.byteOffset,
            event.target.value.byteLength,
          ),
        );
      this.onDisconnect = () => {
        this.log(`GATT 连接意外断开；待处理请求=${this.pending?.id ?? "无"}`);
        this.writer = null;
        this.detachNotificationListeners();
        this.reader = null;
        this.pending?.reject(
          new Error(
            "蓝牙连接已断开。若刚才正在写入，请重新连接并读取设备核对，切勿直接重试。",
          ),
        );
        this.dispatchEvent(new CustomEvent("connection", { detail: false }));
      };
    }
    get connected() {
      return Boolean(this.device?.gatt?.connected && this.writer);
    }
    log(message) {
      this.dispatchEvent(new CustomEvent("log", { detail: message }));
    }
    detachNotificationListeners() {
      for (const { reader, handler } of this.notificationListeners)
        reader.removeEventListener("characteristicvaluechanged", handler);
      this.notificationListeners = [];
    }
    receiveAuxiliary(bytes, channel) {
      if (this.pending) this.pending.auxiliaryNotifications++;
      // 辅助通道可承载另一种协议，不能与 9e 的页面帧拼接或据此结束页面请求。
      this.log(
        `RX 辅助通知 #${++this.auxiliaryNotificationNumber} · 通道=${channel} · ${bytes.length} 字节 · ${dump(bytes)} · 仅旁路记录，不进入主通道解析`,
      );
    }
    async subscribeAuxiliaryNotifications(server) {
      for (const channel of AUXILIARY_CHANNELS) {
        let reader, handler;
        try {
          const service = await server.getPrimaryService(channel.service);
          reader = await service.getCharacteristic(channel.notify);
          handler = (event) =>
            this.receiveAuxiliary(
              new Uint8Array(
                event.target.value.buffer,
                event.target.value.byteOffset,
                event.target.value.byteLength,
              ),
              channel.key,
            );
          reader.addEventListener("characteristicvaluechanged", handler);
          this.notificationListeners.push({
            reader,
            handler,
            key: channel.key,
          });
          await reader.startNotifications();
          this.log(
            `辅助通知通道 ${channel.key} 已订阅 · ${channel.notify} · 属性=${properties(reader)} · 不发送配置或绑定命令`,
          );
        } catch (error) {
          if (reader && handler)
            reader.removeEventListener("characteristicvaluechanged", handler);
          this.notificationListeners = this.notificationListeners.filter(
            (entry) => entry.reader !== reader,
          );
          this.log(
            `辅助通知通道 ${channel.key} 未启用（保留主通道）：${errorText(error)}`,
          );
        }
      }
      this.log(
        `通知初始化完成 · 已启用=${this.notificationListeners.map((entry) => entry.key).join(",")} · 页面请求仅通过 9e 写入`,
      );
    }
    requestSummary(pending) {
      const buffered = this.stream.buffer.length;
      let expected = buffered ? "消息头尚不完整" : "无缓存数据";
      if (buffered >= 20)
        expected = String(
          this.stream.buffer[0] === 2
            ? 20
            : 20 + this.stream.buffer[7] * 256 + this.stream.buffer[8],
        );
      return `请求 #${pending.id} · 已发起 ${pending.startedParts}/${pending.totalParts} 片，浏览器写入完成 ${pending.completedParts}/${pending.totalParts} 片（${pending.completedBytes}/${pending.totalBytes} 字节）· 收到 ${pending.notifications} 次通知/${pending.receivedBytes} 字节 · 完整帧 ${pending.frames} 个 · 辅助通道 ${pending.auxiliaryNotifications} 次通知 · 缓存 ${buffered} 字节，期待=${expected} · 耗时 ${Date.now() - pending.startedAt} ms`;
    }
    async connect() {
      this.log(
        `连接诊断 ${DIAGNOSTICS_VERSION} · secureContext=${Boolean(root.isSecureContext)} · Web Bluetooth=${Boolean(navigator.bluetooth)}`,
      );
      if (!root.isSecureContext)
        throw new Error(
          "蓝牙需要 HTTPS 或 localhost。请通过本地服务器打开，不能使用 file://。",
        );
      if (!navigator.bluetooth)
        throw new Error(
          "当前浏览器不支持 Web Bluetooth，请使用桌面版 Chrome 或 Edge。",
        );
      if (this.device) this.disconnect("开始新的连接");
      const device = await navigator.bluetooth.requestDevice({
        filters: [{ namePrefix: "BSC500" }],
        optionalServices: [
          UUID.service,
          ...AUXILIARY_CHANNELS.map((channel) => channel.service),
          "device_information",
        ],
      });
      this.device = device;
      device.addEventListener("gattserverdisconnected", this.onDisconnect);
      try {
        this.log("已选中 BSC500，正在建立 GATT 连接");
        const server = await device.gatt.connect();
        this.log(`GATT 已连接；查找配置服务 ${UUID.service}`);
        const service = await server.getPrimaryService(UUID.service);
        this.writer = await service.getCharacteristic(UUID.write);
        this.reader = await service.getCharacteristic(UUID.notify);
        this.log(
          `写入特征 ${this.writer.uuid || UUID.write} · 属性=${properties(this.writer)}`,
        );
        this.log(
          `通知特征 ${this.reader.uuid || UUID.notify} · 属性=${properties(this.reader)}`,
        );
        this.stream = new P.FrameStream();
        this.notificationNumber = 0;
        this.auxiliaryNotificationNumber = 0;
        this.reader.addEventListener("characteristicvaluechanged", this.onData);
        this.notificationListeners.push({
          reader: this.reader,
          handler: this.onData,
          key: "9e",
        });
        await this.reader.startNotifications();
        this.log(
          "主通道 9e 通知订阅完成，已安装 characteristicvaluechanged 监听",
        );
        await this.subscribeAuxiliaryNotifications(server);
        this.version = "";
        try {
          const info = await server.getPrimaryService("device_information"),
            revision = await info.getCharacteristic("software_revision_string");
          this.version = new TextDecoder()
            .decode(await revision.readValue())
            .replace(/\0/g, "");
          this.log(`设备软件版本=${this.version || "空值"}`);
        } catch (error) {
          this.log(
            `可选设备信息读取失败（不阻断页面读取）：${errorText(error)}`,
          );
        }
        if (!this.connected)
          throw new Error("通知初始化期间连接已断开，请重新连接。");
        this.log(
          `已连接 ${device.name || "BSC500"}${this.version ? ` · ${this.version}` : ""}；尚未读取或修改页面。`,
        );
        this.dispatchEvent(new CustomEvent("connection", { detail: true }));
      } catch (error) {
        this.log(`连接初始化失败：${errorText(error)}`);
        this.disconnect("连接初始化失败");
        throw error;
      }
    }
    disconnect(reason = "主动断开") {
      this.log(`关闭 GATT 会话：${reason}`);
      this.pending?.reject(new Error("连接已关闭。"));
      this.detachNotificationListeners();
      this.device?.removeEventListener(
        "gattserverdisconnected",
        this.onDisconnect,
      );
      if (this.device?.gatt?.connected) this.device.gatt.disconnect();
      this.writer = null;
      this.reader = null;
      this.device = null;
      this.dispatchEvent(new CustomEvent("connection", { detail: false }));
    }
    receive(bytes) {
      const receiving = this.pending;
      if (receiving) {
        receiving.notifications++;
        receiving.receivedBytes += bytes.length;
      }
      this.log(
        `RX 通知 #${++this.notificationNumber} · ${bytes.length} 字节 · 请求=${receiving?.id ?? "无"} · ${dump(bytes)}`,
      );
      try {
        for (const message of this.stream.push(bytes)) {
          const pending = this.pending;
          if (pending) pending.frames++;
          this.log(
            `RX 完整帧 · kind=${message.kind} service=${message.service} subservice=${message.subservice} operation=${message.operation} · payload=${message.payload.length} 字节${message.kind === 2 ? ` · status=${message.status}` : ""}`,
          );
          if (
            !pending ||
            message.service !== 12 ||
            message.subservice !== 2 ||
            message.operation !== pending.operation
          ) {
            this.log(
              `RX 帧未匹配当前页面请求（期待 operation=${pending?.operation ?? "无"}），未作为读取成功`,
            );
            continue;
          }
          if (message.kind === 2) {
            if (message.status !== 0)
              pending.reject(
                new Error(
                  `码表拒绝操作，状态码 ${message.status}；没有确认同步成功。`,
                ),
              );
            else if (pending.operation === 1) pending.resolve(message);
            else this.log("RX 读取确认帧状态 0，继续等待带页面载荷的回复");
          } else if (message.kind === 1 && pending.operation === 2)
            pending.resolve(message);
          else this.log(`RX kind=${message.kind} 暂不满足当前请求的结束条件`);
        }
      } catch (error) {
        this.log(`RX 解析失败：${errorText(error)}`);
        this.pending?.reject(error);
      }
    }
    request(operation, payload) {
      if (!this.connected)
        return Promise.reject(new Error("请先连接 BSC500。"));
      if (this.pending)
        return Promise.reject(new Error("上一个蓝牙操作尚未结束。"));
      const message = P.frame(payload, operation),
        writer = this.writer;
      const partSize = operation === 2 ? message.length : 20;
      // 原抓包先有 EncryptionChange(status=0, enabled=1)，再发配置请求。
      // 用正常 ATT Write Request 获得底层错误／系统配对机会，不重放应用层绑定命令。
      const method =
        operation === 2 && writer.properties.write
          ? "writeValueWithResponse"
          : writer.properties.writeWithoutResponse
            ? "writeValueWithoutResponse"
            : "writeValueWithResponse";
      const confirmReadTransport =
        operation === 2 && method === "writeValueWithResponse";
      return new Promise((resolve, reject) => {
        let timer;
        const finish = (callback, value, success) => {
          if (this.pending !== pending) return;
          clearTimeout(timer);
          this.pending = null;
          this.log(
            `${success ? "请求收到匹配回复" : `请求失败：${errorText(value)}`} · ${this.requestSummary(pending)}`,
          );
          callback(value);
        };
        const pending = {
          id: ++this.requestNumber,
          operation,
          startedAt: Date.now(),
          totalBytes: message.length,
          totalParts: Math.ceil(message.length / partSize),
          startedParts: 0,
          completedParts: 0,
          completedBytes: 0,
          notifications: 0,
          auxiliaryNotifications: 0,
          receivedBytes: 0,
          frames: 0,
          response: null,
          resolve: (value) => {
            if (this.pending !== pending) return;
            pending.response = value;
            // 通知可能早于 ATT 写入 API 完成，二者都成功才能结束读取。
            if (pending.completedParts === pending.totalParts)
              finish(resolve, value, true);
            else
              this.log(
                `请求 #${pending.id} 已收到应用层回复，继续等待本次写入 API 完成`,
              );
          },
          reject: (error) => finish(reject, error, false),
        };
        const armTimeout = (milliseconds, stage) => {
          clearTimeout(timer);
          timer = setTimeout(() => {
            this.log(
              `等待设备回复超时 · 阶段=${stage} · ${this.requestSummary(pending)}`,
            );
            pending.reject(
              new Error(
                `${stage === "ATT有响应写入" ? "ATT 有响应写入等待超时；若系统弹出蓝牙配对提示，请确认正常配对。" : "等待码表回复超时。"}（浏览器写入完成 ${pending.completedBytes}/${pending.totalBytes} 字节，收到 ${pending.notifications} 次通知/${pending.receivedBytes} 字节）。详细记录见「调试信息 → 蓝牙诊断日志」，Web Bluetooth 无法直接报告链路加密状态。`,
              ),
            );
            this.disconnect("请求超时，关闭以隔离迟到回复");
          }, milliseconds);
        };
        this.pending = pending;
        // 正常系统配对可能需要用户读码确认；传输确认后另计 12 秒应用层回复。
        armTimeout(
          confirmReadTransport ? 45000 : 12000,
          confirmReadTransport ? "ATT有响应写入" : "发送或应用层回复",
        );
        this.log(
          `${operation === 2 ? "读取" : "写入"}页面配置 · ${message.length} 字节 · 请求 #${pending.id}`,
        );
        this.log(
          `TX 请求 #${pending.id} 发送策略：${operation === 2 ? "读取请求单包发送（对齐 iPhone 抓包）" : "配置写入保留 20 字节分片"} · 分片上限=${partSize} 字节 · 方法=${method}`,
        );
        if (confirmReadTransport)
          this.log(
            "只读请求启用 ATT 有响应写入：等待底层确认，必要时由系统提示蓝牙配对；不发送 App 账号绑定／解绑命令，也不能据此断言链路已加密",
          );
        else if (operation === 2)
          this.log(
            "当前特征未声明 write 属性，只能无响应发送；此模式无法获得 ATT 写入确认",
          );
        this.log(`TX 请求 #${pending.id} 原始报文 · ${dump(message)}`);
        (async () => {
          for (let start = 0; start < message.length; start += partSize) {
            if (this.pending !== pending) return;
            const part = message.slice(start, start + partSize);
            pending.startedParts++;
            this.log(
              `TX 请求 #${pending.id} 分片 ${pending.startedParts}/${pending.totalParts} · ${method} · ${part.length} 字节 · ${dump(part)}`,
            );
            await writer[method](part);
            pending.completedParts++;
            pending.completedBytes += part.length;
            this.log(
              `TX 请求 #${pending.id} 分片 ${pending.completedParts}/${pending.totalParts} 浏览器写入完成（不代表页面应用层回执）`,
            );
            if (method === "writeValueWithResponse")
              this.log(
                `ATT 有响应写入完成 · 请求 #${pending.id} 分片 ${pending.completedParts}/${pending.totalParts} · 仍需页面应用层回复`,
              );
          }
          if (this.pending !== pending) return;
          this.log(
            `TX 请求 #${pending.id} 全部 ${message.length} 字节浏览器写入完成`,
          );
          armTimeout(12000, "页面应用层回复");
          if (pending.response) pending.resolve(pending.response);
        })().catch((error) => {
          this.log(`TX 请求 #${pending.id} 写入异常：${errorText(error)}`);
          // 旧会话的写入可能在断连后才返回异常，不能关闭后来建立的新会话。
          if (this.pending !== pending) return;
          pending.reject(error);
          this.disconnect("发送失败，不自动重试");
        });
      });
    }
    async readPages(mode) {
      this.log(`请求读取骑行模式 ${mode}`);
      const message = await this.request(2, P.config(2, mode));
      let config;
      try {
        config = P.decodeConfig(message.payload);
        if ((config.mode ?? 0) !== mode)
          throw new Error("设备回复的骑行模式与请求不一致，已停止操作。");
      } catch (error) {
        this.log(`页面载荷解析失败：${errorText(error)}`);
        throw error;
      }
      this.log(
        `读取完成 · 模式 ${mode} · ${config.pages.filter((p) => p.index !== undefined).length} 条已编号页面记录`,
      );
      return config;
    }
    async writePages(mode, pages) {
      await this.request(1, P.config(1, mode, pages));
      this.log("设备已回执状态 0；还需读回配置核对。");
    }
  }
  BSCBluetooth.diagnosticsVersion = DIAGNOSTICS_VERSION;
  root.BSCBluetooth = BSCBluetooth;
})(globalThis);
