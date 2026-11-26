"use strict";
const { test } = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const vm = require("node:vm");
const P = require("../protocol.js");
const M = require("../editor-model.js");
const fixture = require("./fixtures/page-messages.json");
const message = (name) => new P.FrameStream().push(P.unhex(fixture[name]))[0];
const capturePages = (name) =>
  P.parse(message(name).payload)
    .filter((i) => i.tag === 5)
    .map((i) => P.decodePage(i.value));

test("CRC-8/MAXIM 标准检查串", () => {
  assert.equal(P.crc8(new TextEncoder().encode("123456789")), 0xa1);
});
test("页面读取请求与真机抓包逐字节一致", () => {
  assert.equal(P.hex(P.frame(P.config(2, 0), 2)), fixture.readRequest);
});
test("实际写入的页记录无损重建；显式模式零与抓包省略默认值的差异单独核对", () => {
  for (const name of ["writeHeartRate", "writeSpeed"]) {
    const payload = P.config(1, 0, capturePages(name));
    // iPhone 抓包写入省略字段 19；Android 第四代设备分支明确设置目标模式。
    assert.deepEqual(Array.from(payload.slice(-3)), [0x98, 0x01, 0]);
    assert.equal(P.hex(P.frame(payload.slice(0, -3), 1)), fixture[name]);
  }
});
test("抓包解码为 26 条，保留 16 条编号页及重复的末尾记录", () => {
  const config = P.decodeConfig(message("readResponse").payload);
  assert.equal(config.mode, 0);
  assert.equal(config.pages.length, 26);
  const pages = M.withKeys(config.pages);
  assert.equal(pages.length, 16);
  assert.equal(new Set(pages.map((p) => p.key)).size, 16);
  assert.equal(pages.filter((p) => p.index === 16).length, 2);
});
test("仅改实际抓包中的心率槽位，即得到第二次写入的完整原始报文", () => {
  const pages = capturePages("writeHeartRate"),
    target = pages.find((p) => p.index === 8);
  assert.equal(target.fields[0], 0x0301);
  target.fields[0] = 0x0101;
  assert.equal(
    P.hex(P.frame(P.config(1, 0, pages).slice(0, -3), 1)),
    fixture.writeSpeed,
  );
});
test("输入读取报文的每一种分片边界都可重组；多帧合并也可解析", () => {
  const bytes = P.unhex(fixture.readResponse);
  for (let n = 1; n < bytes.length; n++) {
    const stream = new P.FrameStream();
    const output = [
      ...stream.push(bytes.slice(0, n)),
      ...stream.push(bytes.slice(n)),
    ];
    assert.equal(output.length, 1);
    assert.equal(P.hex(output[0].payload), P.hex(bytes.slice(20)));
  }
  for (const size of [1, 7, 20, 244]) {
    const stream = new P.FrameStream(),
      output = [];
    for (let n = 0; n < bytes.length; n += size)
      output.push(...stream.push(bytes.slice(n, n + size)));
    assert.equal(output.length, 1);
  }
  assert.equal(
    new P.FrameStream().push(P.concat(P.unhex(fixture.ackSpeed), bytes)).length,
    2,
  );
});
test("头与载荷损坏不能被当作成功；接收帧必须完整", () => {
  for (const offset of [4, 19, 24]) {
    const bytes = P.unhex(fixture.readResponse);
    bytes[offset] ^= 1;
    assert.throws(() => new P.FrameStream().push(bytes), /校验失败/);
  }
  assert.equal(
    new P.FrameStream().push(P.unhex(fixture.readResponse).slice(0, -1)).length,
    0,
  );
});
test("改字段时未知参数、显式零和图形显示标志逐字节保留", () => {
  const bytes = P.concat(
    P.scalar(1, 3),
    P.scalar(2, 0),
    P.scalar(3, 257),
    P.scalar(6, 1),
    P.scalar(10, 64),
    P.blob(88, new Uint8Array([1, 9, 0])),
  );
  const page = P.decodePage(bytes);
  page.fields = [769];
  const encoded = P.encodePage(page),
    items = P.parse(encoded);
  assert.equal(items.find((i) => i.tag === 2).value, 0);
  assert.equal(items.find((i) => i.tag === 10).value, 64);
  assert.deepEqual(
    items.find((i) => i.tag === 88).value,
    new Uint8Array([1, 9, 0]),
  );
});
test("兼容 packed repeated 字段；没有变化时不规范化原始字节", () => {
  const bytes = P.concat(
    P.scalar(1, 1),
    P.blob(3, P.concat(P.varint(257), P.varint(769))),
  );
  const page = P.decodePage(bytes);
  assert.deepEqual(page.fields, [257, 769]);
  assert.deepEqual(P.encodePage(page), bytes);
});
test("拒绝截断、超长和无效 Protobuf 或非法操作", () => {
  for (const bytes of [[0], [0x0a, 9, 1], [0x80], [0x09, 1], [0x0b]])
    assert.throws(() => P.parse(new Uint8Array(bytes)));
  assert.throws(() => P.config(7, 0));
  assert.throws(() => P.config(1, -1));
  assert.throws(() => P.unhex("z0"));
  assert.throws(() => P.decodeConfig(message("writeHeartRate").payload));
});
test("普通数据页的图形位等于图形字段位置掩码；真机与官方写入报文均一致", () => {
  const graphIds = new Set([
    0x0b01, 0x0b02, 0x0b03, 0x0b04, 0x0b05, 0x0b06, 0x0b07, 0x0b08, 0x0b09,
    0x0b0a, 0x0b0b, 0x0b0c, 0x0b0d, 0x0b0e, 0x0b0f, 0x0b10, 0x0b11, 0x0b12,
    0x0b13, 0x0b14, 0x0b1b,
  ]);
  const mask = (fields) =>
    fields.reduce((m, id, i) => (graphIds.has(id) ? m | (1 << i) : m), 0);
  for (const name of ["readResponse", "writeHeartRate", "writeSpeed"]) {
    const items = P.parse(message(name).payload)
      .filter((i) => i.tag === 5)
      .map((i) => P.decodePage(i.value));
    for (const page of items) {
      if (page.type !== 1) continue;
      assert.equal(
        page.graphic,
        mask(page.fields),
        `${name} 页面 ${page.index}`,
      );
    }
  }
});
test("字段变化时重算图形位，未变化的页面保持设备原始字节", () => {
  const pages = M.withKeys(
    P.decodeConfig(message("readResponse").payload).pages,
  );
  const target = pages.find((p) => p.index === 4);
  assert.equal(target.graphic, 33);
  const before = P.unhex(target.raw);
  target.fields[0] = 769;
  target.graphic = M.graphicMask(target.fields);
  const encoded = P.encodePage(target);
  assert.equal(P.parse(encoded).find((i) => i.tag === 10).value, 32);
  // 其余已知字段与未知扩展保持不变。
  assert.deepEqual(
    P.parse(encoded)
      .filter((i) => ![3, 10].includes(i.tag))
      .map((i) => P.hex(i.raw)),
    P.parse(before)
      .filter((i) => ![3, 10].includes(i.tag))
      .map((i) => P.hex(i.raw)),
  );
});
test("读回按字段值比较，能识别版式、顺序和未知扩展的变化", () => {
  const original = capturePages("writeSpeed"),
    next = structuredClone(original);
  assert.deepEqual(P.differences(original, next), []);
  next[0].fields[0] = 257;
  assert.ok(P.differences(original, next).some((s) => s.includes("fields")));
  const order = structuredClone(original);
  [order[0], order[1]] = [order[1], order[0]];
  assert.ok(P.differences(original, order).length);
  const unknown = structuredClone(original);
  unknown[0].raw += P.hex(P.scalar(88, 1));
  assert.ok(
    P.differences(original, unknown).some((s) => s.includes("未知字段")),
  );
});
test("76 个 APK 原厂版式的字段数和行高均一致", () => {
  const context = { window: {} };
  vm.runInNewContext(
    fs.readFileSync(require.resolve("../editor-data.js"), "utf8"),
    context,
  );
  const layouts = context.window.BSCEditorData.layouts;
  assert.equal(layouts.length, 76);
  for (const layout of layouts) {
    const rows = M.rows({ ...layout, fields: Array(layout.count).fill(257) });
    assert.equal(
      rows.reduce((n, r) => n + r.count, 0),
      layout.count,
    );
    assert.ok(rows.reduce((n, r) => n + r.weight, 0) <= 6);
  }
  assert.equal(
    context.window.BSCEditorData.fields.filter((f) => f.verified).length,
    47,
  );
});
test("方案校验阻止损坏及不匹配的文件", () => {
  const plan = {
    format: "bsc500-page-plan",
    version: 1,
    mode: 0,
    pages: M.sample(),
  };
  assert.equal(M.validatePlan(plan).pages.length, 8);
  for (const value of [
    { ...plan, mode: 10 },
    { ...plan, version: 2 },
    { ...plan, pages: [] },
    { ...plan, pages: [{ ...plan.pages[0], fields: [65536] }] },
  ])
    assert.throws(() => M.validatePlan(value));
});
test("导入只修改允许的属性，不信任文件的原始报文、图形位、禁止关闭或页类型", () => {
  const live = M.sample(),
    imported = structuredClone(live);
  imported[0].fields[0] = 769;
  imported[0].raw += P.hex(P.scalar(88, 255));
  imported[0].graphic = 64;
  const merged = M.mergePlan({ pages: imported }, live);
  assert.equal(merged[0].raw, live[0].raw);
  assert.equal(merged[0].graphic, live[0].graphic);
  assert.equal(merged[0].fields[0], 769);
  assert.throws(() =>
    M.mergePlan({ pages: [{ ...imported[0], key: "fake", index: 99 }] }, live),
  );
});
test("导入完整方案恢复顺序；部分方案不移动其他页面", () => {
  const live = M.sample(),
    imported = structuredClone(live);
  [imported[0], imported[1]] = [imported[1], imported[0]];
  assert.equal(M.mergePlan({ pages: imported }, live)[0].key, live[1].key);
  const partial = structuredClone(live[1]);
  partial.fields[0] = 257;
  const merged = M.mergePlan({ pages: [partial] }, live);
  assert.equal(merged[0].key, live[0].key);
  assert.equal(merged[1].fields[0], 257);
});
test("设备锁定的启用状态、唯一主页和至少一页可见不能被导入破坏", () => {
  const live = M.sample();
  live[1].forbidden = 1;
  const imported = structuredClone(live);
  imported[1].status = 0;
  assert.throws(() => M.mergePlan({ pages: imported }, live), /限制/);
  imported[1].status = 1;
  imported[1].main = 1;
  assert.throws(() => M.mergePlan({ pages: imported }, live), /多个主页/);
});

test("功率、心率百分比、训练分数与温度不误用同组单位", () => {
  assert.equal(M.unitFor(0x0401), "W");
  assert.equal(M.unitFor(0x040c), "%");
  assert.equal(M.unitFor(0x040d), "");
  assert.equal(M.unitFor(0x040f), "");
  assert.equal(M.unitFor(0x0410), "W/kg");
  assert.equal(M.unitFor(0x0411), "kJ");
  assert.equal(M.unitFor(0x0308), "区间");
  assert.equal(M.unitFor(0x0309), "%");
  assert.equal(M.unitFor(0x0804), "°C");
});
test("高度、垂直速度与图形字段的单位按实际指标区分", () => {
  assert.equal(M.unitFor(0x060d), "%");
  assert.equal(M.unitFor(0x0604), "m/h");
  assert.equal(M.unitFor(0x0605), "m");
  assert.equal(M.unitFor(0x0b09), "bpm");
  assert.equal(M.unitFor(0x0b0f), "m");
  assert.equal(M.unitFor(0x1a01), "°C");
});
