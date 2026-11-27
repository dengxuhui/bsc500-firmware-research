"use strict";

// 用真实浏览器驱动 UI；假 GATT 仅在测试上下文注入，不会请求或修改实体码表。
// PLAYWRIGHT_MODULE 可指向隔离安装的 playwright 目录，生产网页无依赖。
const { chromium } = require(process.env.PLAYWRIGHT_MODULE || "playwright");
const assert = require("node:assert/strict");
const fixture = require("./fixtures/page-messages.json");
const fs = require("node:fs/promises");
const url = process.env.BASE_URL || "http://127.0.0.1:8765";

async function mockBluetooth(page) {
  await page.addInitScript(
    ({ fixture }) => {
      const state = {
        pages: null,
        reads: 0,
        writes: 0,
        maxChunk: 0,
        txChunks: [],
        readPackets: [],
        requireSingleReadPacket: false,
        failReadback: false,
        rejectWrite: false,
        disconnectOnWrite: false,
        timeout: false,
        mutateBeforeSync: false,
        writeError: false,
        unmatchedReply: false,
        partialReply: false,
        methods: [],
        authenticationError: false,
        attErrorAfterReply: false,
        holdAttResponse: false,
        withoutResponseOnly: false,
        subscribedChannels: [],
        missingChannels: [],
        failingSubscription: null,
        requireAllNotifications: false,
        auxiliaryNoiseDuringRead: false,
        requestOptions: null,
      };
      const device = new EventTarget();
      device.name = "BSC500";
      device.id = "test-device";
      const receivers = new Map();
      for (const key of ["9e", "8e", "7e", "6e"]) {
        const reader = new EventTarget();
        reader.uuid = "6e400003-b5a3-f393-e0a9-e50e24dcca" + key;
        reader.properties = { notify: true };
        reader.startNotifications = async () => {
          if (state.failingSubscription === key)
            throw new DOMException("测试通知订阅失败", "NetworkError");
          state.subscribedChannels.push(key);
          return reader;
        };
        receivers.set(key, reader);
      }
      const receiver = receivers.get("9e");
      state.emitAuxiliary = (key, bytes) => {
        const reader = receivers.get(key);
        reader.value = new DataView(Uint8Array.from(bytes).buffer);
        reader.dispatchEvent(new Event("characteristicvaluechanged"));
      };
      function emit(bytes) {
        // 刻意不用标准 ATT 边界，验证跨通知消息头和载荷重组。
        for (let at = 0; at < bytes.length; at += 17) {
          receiver.value = new DataView(bytes.slice(at, at + 17).buffer);
          receiver.dispatchEvent(new Event("characteristicvaluechanged"));
        }
      }
      let stream;
      async function sendBytes(part) {
        state.maxChunk = Math.max(state.maxChunk, part.length);
        state.txChunks.push({
          size: part.length,
          operation: stream?.buffer[4] ?? part[4],
        });
        if (state.writeError)
          throw new DOMException("测试浏览器写入失败", "NetworkError");
        const P = window.BSCProtocol;
        stream ||= new P.FrameStream();
        for (const message of stream.push(part)) {
          state.pages ||= P.parse(P.unhex(fixture.readResponse).slice(20))
            .filter((item) => item.tag === 5)
            .map((item) => P.decodePage(item.value));
          if (state.timeout) continue;
          const modeItem = P.parse(message.payload).find(
              (item) => item.tag === 19,
            ),
            mode = modeItem?.value ?? 0;
          if (message.operation === 2) {
            if (
              state.requireAllNotifications &&
              !["9e", "8e", "7e", "6e"].every((key) =>
                state.subscribedChannels.includes(key),
              )
            )
              continue;
            if (state.auxiliaryNoiseDuringRead)
              state.emitAuxiliary(
                "8e",
                [
                  170, 85, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                  16, 17,
                ],
              );
            // 模拟按 ATT 包边界接收读取命令的设备；旧版 20+9 会被此回归拒绝。
            if (
              state.requireSingleReadPacket &&
              part.length !== message.payload.length + 20
            )
              continue;
            state.readPackets.push(P.hex(part));
            state.reads++;
            if (state.mutateBeforeSync && state.reads === 2)
              state.pages[0].fields[0] = 257;
            const pages = structuredClone(state.pages);
            if (state.failReadback && state.writes) pages[0].fields[0] = 257;
            let response = P.frame(
              P.concat(
                P.scalar(1, 12),
                P.scalar(2, 2),
                P.scalar(3, 2),
                ...pages.map((p) => P.blob(5, P.encodePage(p))),
                P.scalar(19, mode),
              ),
              2,
            );
            if (state.unmatchedReply) {
              response[1] = 13;
              response[19] = P.crc8(response.slice(0, 19));
            }
            if (state.partialReply) response = response.slice(0, 27);
            emit(response);
          } else {
            state.writes++;
            if (state.disconnectOnWrite) {
              device.gatt.disconnect();
              continue;
            }
            if (!state.rejectWrite)
              state.pages = P.parse(message.payload)
                .filter((item) => item.tag === 5)
                .map((item) => P.decodePage(item.value));
            const ack = P.unhex(fixture.ackSpeed);
            if (state.rejectWrite) {
              ack[7] = 3;
              ack[19] = P.crc8(ack.slice(0, 19));
            }
            emit(ack);
          }
        }
      }
      const writer = {
        properties: {
          get write() {
            return !state.withoutResponseOnly;
          },
          writeWithoutResponse: true,
        },
        writeValueWithoutResponse: async (part) => {
          state.methods.push("withoutResponse");
          await sendBytes(part);
        },
        writeValueWithResponse: async (part) => {
          state.methods.push("withResponse");
          if (state.authenticationError)
            throw new DOMException(
              "GATT Error: Insufficient Authentication",
              "NetworkError",
            );
          await sendBytes(part);
          if (state.holdAttResponse)
            await new Promise((resolve) => {
              state.releaseAtt = resolve;
            });
          if (state.attErrorAfterReply)
            throw new DOMException("测试 ATT 确认失败", "NetworkError");
        },
      };
      const services = new Map(
        ["9e", "8e", "7e", "6e"].map((key) => [
          "6e400001-b5a3-f393-e0a9-e50e24dcca" + key,
          {
            getCharacteristic: async (uuid) =>
              uuid.includes("0002-") ? writer : receivers.get(key),
          },
        ]),
      );
      device.gatt = {
        connected: false,
        connect: async () => {
          device.gatt.connected = true;
          return device.gatt;
        },
        disconnect: () => {
          device.gatt.connected = false;
          device.dispatchEvent(new Event("gattserverdisconnected"));
        },
        getPrimaryService: async (name) => {
          if (name === "device_information")
            return {
              getCharacteristic: async () => ({
                readValue: async () =>
                  new DataView(new TextEncoder().encode("1.08.005").buffer),
              }),
            };
          if (state.missingChannels.includes(name.slice(-2)))
            throw new DOMException("测试辅助服务不存在", "NotFoundError");
          return services.get(name);
        },
      };
      Object.defineProperty(navigator, "bluetooth", {
        configurable: true,
        value: {
          requestDevice: async (options) => {
            state.requestOptions = options;
            return device;
          },
        },
      });
      window.__bleTest = state;
    },
    { fixture },
  );
}

(async () => {
  const browser = await chromium.launch({ channel: "chrome", headless: true });
  const errors = [],
    failures = [];
  let passed = 0;
  async function scenario(name, fn, mocked = false) {
    const context = await browser.newContext({
      viewport: { width: 1440, height: 1000 },
      acceptDownloads: true,
    });
    const page = await context.newPage();
    page.on("pageerror", (error) => errors.push(`${name}: ${error.message}`));
    page.on("dialog", (dialog) => dialog.accept());
    try {
      if (mocked) await mockBluetooth(page);
      await page.goto(url);
      await fn(page, context);
      passed++;
      console.log(`通过：${name}`);
    } catch (error) {
      failures.push(`${name}: ${error.stack}`);
      console.error(`失败：${name}: ${error.message}`);
    } finally {
      await context.close();
    }
  }
  async function read(page) {
    await page.locator("#read-device").click();
    await page.waitForFunction(
      () => document.querySelector("#draft-badge").textContent === "已读取设备",
    );
  }
  async function editFirst(page) {
    await page.locator(".screen-tile").first().click();
    await page.locator("#field-search").fill("0x0301");
    await page.locator(".library-item").click();
  }
  async function confirmSync(page) {
    await page.locator("#open-sync").click();
    await page.locator("#sync-consent").check();
    await page.locator("#sync-confirm").click();
  }
  try {
    await scenario("项目品牌、研究工具入口与返回导航", async (page) => {
      assert.equal(await page.title(), "BSC500 数据页配置 · iGPSPORT Web Studio");
      assert.equal(await page.locator(".wordmark").textContent(), "iGPSPORT Web Studio");
      assert.equal(await page.locator(".brand-caption").textContent(), "码表定制工坊");
      await page.locator(".main-nav").getByRole("link", { name: "研究工具", exact: true }).click();
      assert.equal(await page.title(), "研究工具 · iGPSPORT Web Studio");
      assert.match(await page.locator(".brand").textContent(), /iGPSPORT Web Studio/);
      await page.getByRole("link", { name: "返回设备配置", exact: true }).click();
      await page.waitForFunction(() => document.title === "BSC500 数据页配置 · iGPSPORT Web Studio");
      assert.equal(await page.title(), "BSC500 数据页配置 · iGPSPORT Web Studio");
    });
    await scenario("多机型切换、独立草稿、模式隔离与刷新恢复", async (page) => {
      await page.locator("#page-name").fill("BSC500 独立草稿");
      for (const device of [
        "iGS800",
        "BiNavi",
        "BiNavi Air",
        "BiNavi Ultra",
        "iGS630S",
        "BSC200 Pro",
      ]) {
        await page.locator("#device-model").selectOption(device);
        assert.equal(
          await page.locator("#breadcrumb-device").textContent(),
          device,
        );
        for (const id of ["connect", "read-device", "open-sync"])
          assert.ok(await page.locator(`#${id}`).isDisabled());
        assert.match(
          await page.locator("#device-capability").textContent(),
          /仅离线展示/,
        );
        const max = await page
          .locator("#field-count option")
          .evaluateAll((nodes) =>
            Math.max(...nodes.map((n) => Number(n.value))),
          );
        assert.equal(max, ["iGS630S", "BSC200 Pro"].includes(device) ? 10 : 12);
        await page.locator("#page-name").fill(`${device} 独立草稿`);
        await page.locator("#field-count").selectOption(String(max));
        assert.equal(await page.locator(".screen-tile").count(), max);
        await page.locator("#undo").click();
        await editFirst(page);
        await page.locator("#ride-mode").selectOption("1");
        assert.equal(await page.locator("#page-name").inputValue(), "骑行总览");
        await page.locator("#ride-mode").selectOption("0");
        assert.equal(
          await page.locator("#page-name").inputValue(),
          `${device} 独立草稿`,
        );
      }
      await page.reload();
      assert.equal(
        await page.locator("#device-model").inputValue(),
        "BSC200 Pro",
      );
      assert.equal(
        await page.locator("#page-name").inputValue(),
        "BSC200 Pro 独立草稿",
      );
      for (const device of [
        "iGS800",
        "BiNavi",
        "BiNavi Air",
        "BiNavi Ultra",
        "iGS630S",
        "BSC200 Pro",
        "BSC500",
      ]) {
        await page.locator("#device-model").selectOption(device);
        assert.equal(
          await page.locator("#page-name").inputValue(),
          `${device} 独立草稿`,
        );
      }
      assert.ok(await page.locator("#connect").isEnabled());
    });
    await scenario("各机型屏幕比例与预览边界保持一致", async (page) => {
      for (const device of [
        "BSC500",
        "iGS800",
        "BiNavi",
        "BiNavi Air",
        "BiNavi Ultra",
        "iGS630S",
        "BSC200 Pro",
      ]) {
        await page.locator("#device-model").selectOption(device);
        await page.waitForFunction(() => {
          const frame = document.querySelector("#device-frame");
          return frame.complete && frame.naturalWidth > 0;
        });
        assert.match(
          await page.locator("#device-capability").textContent(),
          /官方设备目录的机型专属图片/,
        );
        for (const width of [390, 768, 1024, 1440, 1920]) {
          await page.setViewportSize({ width, height: 1000 });
          const geometry = await page.evaluate(() => {
            const screen = document
              .querySelector("#device-screen")
              .getBoundingClientRect();
            const profile = window.BSCDeviceProfiles.get(
              document.querySelector("#device-model").value,
            );
            const ratio = profile.screen;
            const body = document
              .querySelector("#device-body")
              .getBoundingClientRect();
            const frame = document.querySelector("#device-frame");
            return {
              frameSource: frame.getAttribute("src"),
              expectedSource: profile.frame.asset,
              left: (screen.left - body.left) / body.width,
              expectedLeft: profile.frame.left / profile.frame.width,
              top: (screen.top - body.top) / body.height,
              expectedTop: profile.frame.top / profile.frame.height,
              width: screen.width / body.width,
              expectedWidth: profile.screen[0] / profile.frame.width,
              height: screen.height / body.height,
              expectedHeight: profile.screen[1] / profile.frame.height,
              actual: screen.width / screen.height,
              expected: ratio[0] / ratio[1],
              labelBottom: document
                .querySelector(".resolution-label")
                .getBoundingClientRect().bottom,
              bodyTop: document
                .querySelector(".device-body")
                .getBoundingClientRect().top,
              captionBottom: document
                .querySelector(".preview-caption")
                .getBoundingClientRect().bottom,
              paginationTop: document
                .querySelector(".preview-pagination")
                .getBoundingClientRect().top,
              overflow: document.documentElement.scrollWidth > innerWidth,
            };
          });
          assert.equal(
            geometry.frameSource,
            geometry.expectedSource,
            `${device} 外壳素材`,
          );
          for (const key of ["left", "top", "width", "height"]) {
            const expected = `expected${key[0].toUpperCase()}${key.slice(1)}`;
            assert.ok(
              Math.abs(geometry[key] - geometry[expected]) < 0.005,
              `${device} ${width} 屏幕${key}`,
            );
          }
          assert.ok(
            Math.abs(geometry.actual - geometry.expected) < 0.005,
            `${device} ${width} 屏幕比例`,
          );
          assert.ok(
            geometry.labelBottom <= geometry.bodyTop,
            `${device} ${width} 顶部说明重叠`,
          );
          assert.ok(
            geometry.captionBottom <= geometry.paginationTop,
            `${device} ${width} 分页重叠`,
          );
          assert.equal(geometry.overflow, false, `${device} ${width} 横向溢出`);
        }
      }
    });
    await scenario(
      "新机型导入导出带机型信息，拒绝跨机型和缺少机型的文件",
      async (page) => {
        await page.locator("#device-model").selectOption("iGS630S");
        const event = page.waitForEvent("download");
        await page.locator("#export-plan").click();
        const download = await event;
        assert.equal(
          download.suggestedFilename(),
          "iGS630S-mode-0-layout.json",
        );
        const plan = JSON.parse(
          await fs.readFile(await download.path(), "utf8"),
        );
        assert.equal(plan.device, "iGS630S");
        assert.equal(plan.format, "igpsport-page-plan");
        const upload = async (value) =>
          page.locator("#import-file").setInputFiles({
            name: "plan.json",
            mimeType: "application/json",
            buffer: Buffer.from(JSON.stringify(value)),
          });
        plan.pages[0].title = "导入的 iGS630S 方案";
        await upload(plan);
        await page.waitForFunction(
          () =>
            document.querySelector("#page-name").value ===
            "导入的 iGS630S 方案",
        );
        await page.locator("#device-model").selectOption("BSC500");
        await upload(plan);
        await page.waitForFunction(() =>
          document
            .querySelector("#notice")
            .textContent.includes("不能导入 BSC500"),
        );
        assert.equal(await page.locator("#page-name").inputValue(), "骑行总览");
        await page.locator("#device-model").selectOption("iGS630S");
        delete plan.device;
        await upload(plan);
        await page.waitForFunction(() =>
          document.querySelector("#notice").textContent.includes("未指定机型"),
        );
      },
    );
    await scenario(
      "切换机型断开 BSC500 并清除设备基线，新机型不触发蓝牙",
      async (page) => {
        await read(page);
        const reads = await page.evaluate(() => window.__bleTest.reads);
        await page.locator("#device-model").selectOption("BiNavi");
        assert.equal(await page.evaluate(() => window.__bleTest.writes), 0);
        assert.equal(await page.evaluate(() => window.__bleTest.reads), reads);
        await page.locator("#device-model").selectOption("BSC500");
        assert.match(
          await page.locator("#connection-status").textContent(),
          /未连接/,
        );
        assert.match(
          await page.locator("#draft-badge").textContent(),
          /本地草稿/,
        );
        await page.locator("#open-sync").click();
        assert.ok(await page.locator("#sync-confirm").isDisabled());
      },
      true,
    );
    await scenario("桌面编辑、字段搜索、撤销重做与持久化", async (page) => {
      assert.equal(await page.locator(".screen-tile").count(), 6);
      await editFirst(page);
      assert.equal(
        await page.locator(".screen-label").first().textContent(),
        "心率",
      );
      await page.locator("#undo").click();
      assert.equal(
        await page.locator(".screen-label").first().textContent(),
        "速度",
      );
      await page.locator("#redo").click();
      assert.equal(
        await page.locator(".screen-label").first().textContent(),
        "心率",
      );
      await page.reload();
      assert.equal(
        await page.locator(".screen-label").first().textContent(),
        "心率",
      );
      assert.match(await page.locator("#notice").textContent(), /恢复本机草稿/);
      await page.locator("#field-count").selectOption("12");
      assert.equal(await page.locator(".screen-tile").count(), 12);
      await page.locator("#undo").click();
      assert.equal(await page.locator(".screen-tile").count(), 6);
      await page.locator("#theme-dark").click();
      assert.equal(await page.locator(".device-screen.dark").count(), 1);
      await page.getByRole("button", { name: "下移第 1 项" }).click();
      assert.equal(
        await page.locator(".screen-label").nth(1).textContent(),
        "心率",
      );
    });
    await scenario("数据格与页面拖拽排序，键盘辅助页面排序", async (page) => {
      await page
        .locator(".screen-tile")
        .first()
        .dragTo(page.locator(".screen-tile").nth(2));
      assert.equal(
        await page.locator(".screen-label").nth(2).textContent(),
        "速度",
      );
      const keys = await page
        .locator(".page-item")
        .evaluateAll((nodes) => nodes.map((n) => n.dataset.pageKey));
      await page
        .locator(".page-item")
        .first()
        .dragTo(page.locator(".page-item").nth(1));
      const moved = await page
        .locator(".page-item")
        .evaluateAll((nodes) => nodes.map((n) => n.dataset.pageKey));
      assert.equal(moved[1], keys[0]);
      assert.equal(moved[0], keys[1]);
      await page.locator(".page-select").first().focus();
      await page.keyboard.press("Alt+ArrowDown");
      assert.equal(
        await page.locator(".page-item").first().getAttribute("data-page-key"),
        keys[0],
      );
    });
    await scenario("原厂专项页比例、启用页面与无字段页面", async (page) => {
      await page.getByRole("button", { name: "地图导航，已启用" }).click();
      assert.equal(await page.locator(".sample-map").count(), 1);
      assert.equal(await page.locator(".screen-tile").count(), 4);
      const map = await page.locator(".special-preview").boundingBox(),
        screen = await page.locator(".device-screen").boundingBox();
      assert.ok(Math.abs(map.height / screen.height - 2 / 3) < 0.02);
      await page.getByRole("button", { name: "计圈数据，已启用" }).click();
      assert.equal(await page.locator(".screen-tile").count(), 0);
      await page.locator("#add-page").click();
      assert.equal(await page.locator(".available-page").count(), 3);
      await page.locator(".available-page").first().click();
      assert.match(await page.locator("#page-count").textContent(), /6 \/ 8/);
    });
    await scenario(
      "导出导入、恶意标题作为文本处理及坏文件不覆盖草稿",
      async (page) => {
        const event = page.waitForEvent("download");
        await page.locator("#export-plan").click();
        const download = await event;
        const plan = JSON.parse(
          await fs.readFile(await download.path(), "utf8"),
        );
        assert.equal(plan.pages.length, 8);
        plan.pages[0].title = '<img src=x onerror="alert(1)">';
        await page.locator("#import-file").setInputFiles({
          name: "plan.json",
          mimeType: "application/json",
          buffer: Buffer.from(JSON.stringify(plan)),
        });
        await page.waitForFunction(() =>
          document
            .querySelector("#preview-heading")
            .textContent.startsWith("<img"),
        );
        assert.equal(await page.locator("#preview-heading img").count(), 0);
        await page.locator("#import-file").setInputFiles({
          name: "broken.json",
          mimeType: "application/json",
          buffer: Buffer.from('{"format":"bad"}'),
        });
        await page.waitForFunction(
          () => document.querySelector("#notice").dataset.error === "true",
        );
        assert.match(
          await page.locator("#preview-heading").textContent(),
          /^<img/,
        );
      },
    );
    await scenario("未读取设备时同步不可写；各模式草稿独立", async (page) => {
      await page.locator("#open-sync").click();
      assert.equal(await page.locator("#sync-confirm").isDisabled(), true);
      assert.equal(await page.locator("#sync-consent").isDisabled(), true);
      await page.locator("#sync-dialog [data-close]").first().click();
      await page.locator("#page-name").fill("模式零测试");
      await page.locator("#ride-mode").selectOption("1");
      assert.equal(
        await page.locator("#preview-heading").textContent(),
        "骑行总览",
      );
      await page.locator("#page-name").fill("模式一测试");
      await page.locator("#ride-mode").selectOption("0");
      assert.equal(
        await page.locator("#preview-heading").textContent(),
        "模式零测试",
      );
    });
    await scenario(
      "图形字段使用原厂素材渲染，窄格回退为基础指标读数",
      async (page) => {
        await page.locator(".screen-tile").first().click();
        await page.locator("#field-search").fill("0x0B09");
        await page.locator(".library-item").click();
        const graphic = page.locator(".screen-graphic");
        assert.equal(await graphic.count(), 1);
        assert.match(
          await graphic.getAttribute("src"),
          /icon_hr_range_2x2_bg1\.png$/,
        );
        assert.equal(await page.locator(".graphic-tile").count(), 1);
        // 图形格显示原厂素材，不再显示数值占位。
        assert.equal(
          await page.locator(".graphic-tile .screen-value").count(),
          0,
        );
        assert.match(await graphic.getAttribute("alt"), /原厂固定示例图/);
        // 换成窄格版式后按 APK 规则回退到基础心率读数。
        await page.locator("#field-count").selectOption("12");
        await page.locator(".screen-tile").first().click();
        await page.locator("#field-search").fill("0x0B09");
        await page.locator(".library-item").click();
        assert.equal(await page.locator(".screen-graphic").count(), 0);
        assert.match(
          await page
            .locator(".screen-tile")
            .first()
            .locator(".screen-label")
            .textContent(),
          /心率/,
        );
      },
    );
    await scenario(
      "Chrome 响应式：390、768、1024、1440、1920 均无页面横向溢出",
      async (page) => {
        for (const width of [390, 768, 1024, 1440, 1920]) {
          await page.setViewportSize({ width, height: 1000 });
          assert.equal(
            await page.evaluate(
              () => document.documentElement.scrollWidth > innerWidth,
            ),
            false,
            `宽度 ${width}`,
          );
          const screen = await page.locator(".device-screen").boundingBox();
          assert.ok(Math.abs(screen.width / screen.height - 2 / 3) < 0.01);
        }
      },
    );
    await scenario(
      "读取设备后按 graphic_display 位渲染原厂图形素材",
      async (page) => {
        await read(page);
        // 真机第一页 graphic=33：第 0、5 位是心率分布与速度环形图。
        await page.locator(".page-item").first().click();
        const graphics = page.locator(".screen-graphic");
        assert.equal(await graphics.count(), 2);
        assert.match(
          await graphics.nth(0).getAttribute("src"),
          /icon_hr_range_1x2_bg1\.png$/,
        );
        assert.match(
          await graphics.nth(1).getAttribute("src"),
          /icon_spd_dash_board_1x2_bg1\.png$/,
        );
        assert.equal(
          await page.locator(".graphic-tile .screen-value").count(),
          0,
        );
        // 图形格不再显示数字占位，其余格仍显示读数。
        assert.ok((await page.locator(".screen-value").count()) > 0);
      },
      true,
    );
    await scenario(
      "读取实际抓包后，备份、分片写入与读回核对成功",
      async (page) => {
        await read(page);
        assert.equal(await page.locator(".page-item").count(), 16);
        await editFirst(page);
        const download = page.waitForEvent("download");
        await confirmSync(page);
        const backup = JSON.parse(
          await fs.readFile(await (await download).path(), "utf8"),
        );
        await page.waitForFunction(() =>
          document
            .querySelector("#draft-badge")
            .textContent.includes("读回核对"),
        );
        assert.equal(backup.kind, "device-backup");
        assert.equal(backup.pages[0].fields[0], 2825);
        const counts = await page.evaluate(() => ({
          reads: __bleTest.reads,
          writes: __bleTest.writes,
          maxChunk: __bleTest.maxChunk,
          backups: JSON.parse(localStorage.getItem("bsc500-layout-backups-v1"))
            .length,
        }));
        assert.equal(counts.reads, 3);
        assert.equal(counts.writes, 1);
        assert.equal(counts.maxChunk, 29);
        const writeChunks = await page.evaluate(() =>
          __bleTest.txChunks.filter((chunk) => chunk.operation === 1),
        );
        assert.ok(writeChunks.length > 1);
        assert.ok(writeChunks.every((chunk) => chunk.size <= 20));
        assert.equal(counts.backups, 1);
        assert.equal(
          await page.locator("#sync-result").getAttribute("data-error"),
          "false",
        );
      },
      true,
    );
    await scenario(
      "手机端已改配置时，写入前阻止覆盖",
      async (page) => {
        await read(page);
        await editFirst(page);
        await page.evaluate(() => {
          __bleTest.mutateBeforeSync = true;
        });
        await confirmSync(page);
        await page.waitForFunction(
          () => !document.querySelector("#sync-result").hidden,
        );
        assert.match(
          await page.locator("#sync-result").textContent(),
          /其他入口修改/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "回执成功但读回不一致，不能显示同步成功",
      async (page) => {
        await read(page);
        await editFirst(page);
        await page.evaluate(() => {
          __bleTest.failReadback = true;
        });
        await confirmSync(page);
        await page.waitForFunction(
          () => !document.querySelector("#sync-result").hidden,
        );
        assert.match(
          await page.locator("#sync-result").textContent(),
          /读回核对不一致/,
        );
        assert.equal(
          await page.locator("#sync-result").getAttribute("data-error"),
          "true",
        );
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
      },
      true,
    );
    await scenario(
      "设备拒绝写入不会当作成功，也不会自动重试",
      async (page) => {
        await read(page);
        await editFirst(page);
        await page.evaluate(() => {
          __bleTest.rejectWrite = true;
        });
        await confirmSync(page);
        await page.waitForFunction(
          () => !document.querySelector("#sync-result").hidden,
        );
        assert.match(
          await page.locator("#sync-result").textContent(),
          /状态码 3/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 1);
      },
      true,
    );
    await scenario(
      "写入时断连保留草稿并要求重新读取",
      async (page) => {
        await read(page);
        await editFirst(page);
        await page.evaluate(() => {
          __bleTest.disconnectOnWrite = true;
        });
        await confirmSync(page);
        await page.waitForFunction(
          () => !document.querySelector("#sync-result").hidden,
        );
        assert.match(
          await page.locator("#sync-result").textContent(),
          /断开|部分发生/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 1);
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
      },
      true,
    );
    await scenario(
      "存储备份失败就不发送任何写入",
      async (page) => {
        await read(page);
        await editFirst(page);
        await page.evaluate(() => {
          const original = Storage.prototype.setItem;
          Storage.prototype.setItem = function (key, value) {
            if (key.includes("backups")) throw new Error("备份空间不足");
            return original.call(this, key, value);
          };
        });
        await confirmSync(page);
        await page.waitForFunction(
          () => !document.querySelector("#sync-result").hidden,
        );
        assert.match(
          await page.locator("#sync-result").textContent(),
          /备份空间不足/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "读取超时关闭会话，迟到回复不能成为新基线",
      async (page) => {
        await page.clock.install();
        await page.evaluate(() => {
          __bleTest.timeout = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() => __bleTest.maxChunk > 0);
        await page.clock.runFor(13000);
        assert.match(await page.locator("#notice").textContent(), /超时/);
        assert.match(
          await page.locator("#connection-status").textContent(),
          /未连接/,
        );
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "不同目标模式明确传输编号，不复用旧基线",
      async (page) => {
        await read(page);
        await page.locator("#ride-mode").selectOption("1");
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
        await read(page);
        await editFirst(page);
        await confirmSync(page);
        await page.waitForFunction(() =>
          document
            .querySelector("#draft-badge")
            .textContent.includes("读回核对"),
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 1);
      },
      true,
    );
    await scenario(
      "读取使用与抓包完全一致的 29 字节单包，不拆成 20+9",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.requireSingleReadPacket = true;
        });
        await read(page);
        const state = await page.evaluate(() => ({
          chunks: __bleTest.txChunks,
          packets: __bleTest.readPackets,
          reads: __bleTest.reads,
          writes: __bleTest.writes,
        }));
        assert.equal(state.reads, 1);
        assert.equal(state.writes, 0);
        assert.deepEqual(state.chunks, [{ size: 29, operation: 2 }]);
        assert.deepEqual(state.packets, [fixture.readRequest]);
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /读取请求单包发送（对齐 iPhone 抓包）/,
        );
      },
      true,
    );
    await scenario(
      "只读请求优先使用 ATT 有响应写入，不把它当作配置写入",
      async (page) => {
        await read(page);
        assert.deepEqual(await page.evaluate(() => __bleTest.methods), [
          "withResponse",
        ]);
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /ATT 有响应写入完成/,
        );
      },
      true,
    );
    await scenario(
      "ATT 认证错误原样记录，不重试或发送绑定命令",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.authenticationError = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() =>
          document
            .querySelector("#notice")
            .textContent.includes("Insufficient Authentication"),
        );
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /NetworkError: GATT Error: Insufficient Authentication/,
        );
        assert.deepEqual(await page.evaluate(() => __bleTest.methods), [
          "withResponse",
        ]);
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
      },
      true,
    );
    await scenario(
      "通知先到但 ATT 尚未确认时，不能提前标记读取完成",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.holdAttResponse = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(
          () => typeof __bleTest.releaseAtt === "function",
        );
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "正在与设备通信",
        );
        await page.evaluate(() => __bleTest.releaseAtt());
        await page.waitForFunction(
          () =>
            document.querySelector("#draft-badge").textContent === "已读取设备",
        );
        assert.equal(await page.locator(".page-item").count(), 16);
      },
      true,
    );
    await scenario(
      "即使先收到通知，ATT API 失败也不能建立可写基线",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.attErrorAfterReply = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() =>
          document
            .querySelector("#notice")
            .textContent.includes("测试 ATT 确认失败"),
        );
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /已收到应用层回复，继续等待本次写入 API 完成/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "系统配对等待阶段独立计时，不被原来的 12 秒提前中止",
      async (page) => {
        await page.clock.install();
        await page.evaluate(() => {
          __bleTest.holdAttResponse = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(
          () => typeof __bleTest.releaseAtt === "function",
        );
        await page.clock.runFor(13000);
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "正在与设备通信",
        );
        await page.clock.runFor(33000);
        await page.waitForFunction(() =>
          document
            .querySelector("#notice")
            .textContent.includes("ATT 有响应写入等待超时"),
        );
        assert.equal(
          await page.locator("#draft-badge").textContent(),
          "本地草稿 · 未读取",
        );
        await page.evaluate(() => __bleTest.releaseAtt());
      },
      true,
    );
    await scenario(
      "仅支持无响应的特征保留兼容读取，并明确日志限制",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.withoutResponseOnly = true;
        });
        await read(page);
        assert.deepEqual(await page.evaluate(() => __bleTest.methods), [
          "withoutResponse",
        ]);
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /无法获得 ATT 写入确认/,
        );
      },
      true,
    );
    await scenario(
      "连接按官方顺序启用四个通知通道，再发送唯一页面读取命令",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.requireAllNotifications = true;
        });
        await read(page);
        assert.deepEqual(
          await page.evaluate(() => __bleTest.subscribedChannels),
          ["9e", "8e", "7e", "6e"],
        );
        assert.equal(
          await page.evaluate(
            () => __bleTest.requestOptions.optionalServices.length,
          ),
          5,
        );
        assert.equal(await page.evaluate(() => __bleTest.txChunks.length), 1);
        assert.deepEqual(await page.evaluate(() => __bleTest.readPackets), [
          fixture.readRequest,
        ]);
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /已启用=9e,8e,7e,6e/,
        );
      },
      true,
    );
    await scenario(
      "辅助通道的另一种协议仅旁路记录，不污染页面帧解析",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.auxiliaryNoiseDuringRead = true;
        });
        await read(page);
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /RX 辅助通知.*通道=8e/);
        assert.match(log, /辅助通道 1 次通知/);
        assert.equal(log.includes("RX 解析失败"), false);
        assert.match(log, /读取完成/);
      },
      true,
    );
    await scenario(
      "可选服务不存在或订阅失败时如实记录，不伪装为完整初始化",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.missingChannels = ["7e"];
          __bleTest.failingSubscription = "6e";
        });
        await read(page);
        await page.locator("#show-details").click();
        assert.deepEqual(
          await page.evaluate(() => __bleTest.subscribedChannels),
          ["9e", "8e"],
        );
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /辅助通知通道 7e 未启用.*NotFoundError/);
        assert.match(log, /辅助通知通道 6e 未启用.*NetworkError/);
        assert.match(log, /已启用=9e,8e ·/);
      },
      true,
    );
    await scenario(
      "断开时移除全部通知监听，迟到的辅助通知不再进入日志",
      async (page) => {
        await read(page);
        await page.locator("#connect").click();
        const before = await page.evaluate(
          () =>
            JSON.parse(localStorage.getItem("bsc500-ble-diagnostics-v2"))
              .length,
        );
        await page.evaluate(() => __bleTest.emitAuxiliary("8e", [1, 2, 3]));
        assert.equal(
          await page.evaluate(
            () =>
              JSON.parse(localStorage.getItem("bsc500-ble-diagnostics-v2"))
                .length,
          ),
          before,
        );
      },
      true,
    );
    await scenario(
      "诊断日志包含实际 TX/RX、UUID、写入完成，并能导出与刷新保留",
      async (page) => {
        await read(page);
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /ble-notify-init-v5/);
        assert.match(log, /6e400002-b5a3-f393-e0a9-e50e24dcca9e/);
        assert.ok(log.includes(fixture.readRequest));
        assert.match(log, /分片 1\/1 浏览器写入完成/);
        assert.match(log, /RX 通知/);
        assert.match(log, /RX 完整帧.*service=12 subservice=2 operation=2/);
        assert.match(log, /\d{2}:\d{2}:\d{2}\.\d{3}/);
        assert.equal(log.includes("test-device"), false);
        const event = page.waitForEvent("download");
        await page.locator("#export-ble-log").click();
        const download = await event,
          exported = JSON.parse(
            await fs.readFile(await download.path(), "utf8"),
          );
        assert.equal(exported.format, "bsc500-bluetooth-diagnostics");
        assert.equal(exported.transport, "ble-notify-init-v5");
        assert.ok(exported.entries.some((line) => line.includes("RX 通知")));
        await page.reload();
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /RX 通知/,
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
        assert.equal(await page.evaluate(() => __bleTest.reads), 0);
      },
      true,
    );
    await scenario(
      "超时日志准确区分浏览器已完成发送但零通知，不自动重试",
      async (page) => {
        await page.clock.install();
        await page.evaluate(() => {
          __bleTest.timeout = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() => __bleTest.maxChunk > 0);
        await page.clock.runFor(13000);
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /浏览器写入完成 1\/1 片（29\/29 字节）/);
        assert.match(log, /收到 0 次通知\/0 字节/);
        assert.match(log, /请求超时，关闭以隔离迟到回复/);
        assert.match(
          await page.locator("#notice").textContent(),
          /29\/29 字节/,
        );
        await page.reload();
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#session-log").textContent(),
          /等待设备回复超时/,
        );
      },
      true,
    );
    await scenario(
      "浏览器分片写入抛错会记录异常名，不冒充已发送完成",
      async (page) => {
        await page.evaluate(() => {
          __bleTest.writeError = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() =>
          document
            .querySelector("#notice")
            .textContent.includes("测试浏览器写入失败"),
        );
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /TX 请求 #1 写入异常：NetworkError/);
        assert.match(log, /浏览器写入完成 0\/1 片（0\/29 字节）/);
        assert.equal(log.includes("全部 29 字节浏览器写入完成"), false);
        assert.deepEqual(
          await page.evaluate(() =>
            __bleTest.txChunks.map((chunk) => chunk.size),
          ),
          [29],
        );
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "有通知但服务不匹配时，日志保留原始帧和忽略原因",
      async (page) => {
        await page.clock.install();
        await page.evaluate(() => {
          __bleTest.unmatchedReply = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() =>
          document
            .querySelector("#session-log")
            .textContent.includes("RX 帧未匹配"),
        );
        await page.clock.runFor(13000);
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /RX 完整帧.*service=13/);
        assert.match(log, /RX 帧未匹配当前页面请求/);
        assert.match(log, /完整帧 1 个/);
        assert.match(await page.locator("#notice").textContent(), /超时/);
        assert.equal(await page.evaluate(() => __bleTest.writes), 0);
      },
      true,
    );
    await scenario(
      "只收到不完整回复时，超时日志显示缓存与期待长度",
      async (page) => {
        await page.clock.install();
        await page.evaluate(() => {
          __bleTest.partialReply = true;
        });
        await page.locator("#read-device").click();
        await page.waitForFunction(() =>
          document
            .querySelector("#session-log")
            .textContent.includes("RX 通知 #2"),
        );
        await page.clock.runFor(13000);
        await page.locator("#show-details").click();
        const log = await page.locator("#session-log").textContent();
        assert.match(log, /收到 2 次通知\/27 字节/);
        assert.match(log, /缓存 27 字节，期待=574/);
        assert.match(log, /完整帧 0 个/);
      },
      true,
    );
    await scenario(
      "日志存储失败不影响读取，也不错误宣称已持久化",
      async (page) => {
        await page.evaluate(() => {
          const original = Storage.prototype.setItem;
          Storage.prototype.setItem = function (key, value) {
            if (key.includes("diagnostics"))
              throw new Error("测试日志空间不足");
            return original.call(this, key, value);
          };
        });
        await read(page);
        await page.locator("#show-details").click();
        assert.match(
          await page.locator("#diagnostic-log-status").textContent(),
          /存储失败/,
        );
        assert.match(
          await page.locator("#session-log").textContent(),
          /读取完成/,
        );
        const event = page.waitForEvent("download");
        await page.locator("#export-ble-log").click();
        const download = await event,
          exported = JSON.parse(
            await fs.readFile(await download.path(), "utf8"),
          );
        assert.ok(exported.entries.some((line) => line.includes("读取完成")));
      },
      true,
    );
    await scenario("原有离线工作台与原厂图库入口保持可用", async (page) => {
      await page.goto(`${url}/tools.html#reference`);
      assert.equal(
        await page.locator("#reference-gallery .reference-item").count(),
        72,
      );
      await page
        .getByRole("link", { name: "数据页设计器", exact: true })
        .click();
      assert.equal(await page.locator("#designer").isVisible(), true);
    });
    assert.deepEqual(errors, [], `浏览器未捕获错误：${errors.join("\n")}`);
    if (failures.length) throw new Error(failures.join("\n\n"));
    console.log(
      `\n${passed} 个浏览器场景全部通过（假 GATT，不代表真机验证）。`,
    );
  } finally {
    await browser.close();
  }
})().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
