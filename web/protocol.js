"use strict";

// 与 Android BaseFactory、Config.page_msg 及 2026-11-26 抓包交叉核对。
// 不实现配对绕过、固件写入或未知配置服务。
(function (root) {
  const hex = (bytes) =>
    Array.from(bytes, (v) => v.toString(16).padStart(2, "0")).join("");
  function unhex(value) {
    if (
      typeof value !== "string" ||
      value.length % 2 ||
      !/^[\da-f]*$/i.test(value)
    )
      throw new Error("无效的十六进制报文。");
    return Uint8Array.from(value.match(/../g) || [], (v) => parseInt(v, 16));
  }
  function concat(...parts) {
    const out = new Uint8Array(parts.reduce((n, p) => n + p.length, 0));
    let offset = 0;
    for (const part of parts) {
      out.set(part, offset);
      offset += part.length;
    }
    return out;
  }
  function crc8(bytes) {
    let crc = 0;
    for (const byte of bytes) {
      crc ^= byte;
      for (let bit = 0; bit < 8; bit++)
        crc = (crc >>> 1) ^ (crc & 1 ? 0x8c : 0);
    }
    return crc;
  }
  function varint(value) {
    if (!Number.isSafeInteger(value) || value < 0 || value > 0xffffffff)
      throw new Error("协议整数超出范围。");
    const out = [];
    do {
      const b = value % 128;
      value = Math.floor(value / 128);
      out.push(b | (value ? 128 : 0));
    } while (value);
    return Uint8Array.from(out);
  }
  function readVarint(bytes, state) {
    let value = 0;
    for (let n = 0; n < 10; n++) {
      if (state.at >= bytes.length) throw new Error("Protobuf 数据不完整。");
      const byte = bytes[state.at++];
      value += (byte & 127) * 2 ** (7 * n);
      if (!Number.isSafeInteger(value)) throw new Error("Protobuf 整数过大。");
      if (!(byte & 128)) return value;
    }
    throw new Error("无效的 Protobuf 整数。");
  }
  function parse(bytes) {
    const state = { at: 0 },
      items = [];
    while (state.at < bytes.length) {
      const start = state.at,
        key = readVarint(bytes, state),
        tag = Math.floor(key / 8),
        wire = key & 7;
      if (!tag || tag > 0x1fffffff) throw new Error("无效的 Protobuf 字段。");
      let value;
      if (wire === 0) value = readVarint(bytes, state);
      else if (wire === 2) {
        const size = readVarint(bytes, state);
        if (size > bytes.length - state.at)
          throw new Error("Protobuf 字段长度错误。");
        value = bytes.slice(state.at, state.at + size);
        state.at += size;
      } else if (wire === 1 || wire === 5) {
        const size = wire === 1 ? 8 : 4;
        if (state.at + size > bytes.length)
          throw new Error("Protobuf 固定字段不完整。");
        value = bytes.slice(state.at, state.at + size);
        state.at += size;
      } else throw new Error("暂不支持此 Protobuf 编码，已停止处理。");
      items.push({ tag, wire, value, raw: bytes.slice(start, state.at) });
    }
    return items;
  }
  const scalar = (tag, value) => concat(varint(tag * 8), varint(value));
  const blob = (tag, value) =>
    concat(varint(tag * 8 + 2), varint(value.length), value);
  const propertyTags = {
    index: 1,
    status: 2,
    dataSite: 4,
    name: 5,
    type: 6,
    pageMode: 7,
    main: 8,
    lineWidth: 9,
    graphic: 10,
    forbidden: 11,
  };
  const defaults = {
    status: 0,
    dataSite: 0,
    name: "",
    type: 0,
    pageMode: 0,
    main: 0,
    lineWidth: 0,
    graphic: 0,
    forbidden: 0,
  };
  function decodePage(bytes) {
    const page = { ...defaults, fields: [], raw: hex(bytes) };
    const names = Object.fromEntries(
      Object.entries(propertyTags).map(([name, tag]) => [tag, name]),
    );
    for (const item of parse(bytes)) {
      if (item.tag === 3 && item.wire === 0) page.fields.push(item.value);
      else if (item.tag === 3 && item.wire === 2) {
        const state = { at: 0 };
        while (state.at < item.value.length)
          page.fields.push(readVarint(item.value, state));
      } else if (item.tag === 5 && item.wire === 2)
        page.name = new TextDecoder("utf-8", { fatal: true }).decode(
          item.value,
        );
      else if (names[item.tag] && item.wire === 0 && item.tag !== 5)
        page[names[item.tag]] = item.value;
    }
    return page;
  }
  function encodePage(page) {
    const bytes = unhex(page.raw || ""),
      original = decodePage(bytes),
      changes = new Map();
    for (const [name, tag] of Object.entries(propertyTags)) {
      if (page[name] !== undefined && page[name] !== original[name]) {
        changes.set(
          tag,
          name === "name"
            ? blob(tag, new TextEncoder().encode(page[name]))
            : scalar(tag, page[name]),
        );
      }
    }
    if (JSON.stringify(page.fields) !== JSON.stringify(original.fields))
      changes.set(3, concat(...page.fields.map((id) => scalar(3, id))));
    // 未编辑字段（包括未知扩展和显式零值）保持原始字节；只替换实际变化的字段。
    const parts = [],
      written = new Set();
    for (const item of parse(bytes)) {
      if (!changes.has(item.tag)) parts.push(item.raw);
      else if (!written.has(item.tag)) {
        parts.push(changes.get(item.tag));
        written.add(item.tag);
      }
    }
    for (const [tag, value] of changes)
      if (!written.has(tag)) parts.push(value);
    return concat(...parts);
  }
  function decodeConfig(bytes) {
    const items = parse(bytes),
      value = (tag) =>
        items.filter((i) => i.tag === tag && i.wire === 0).at(-1)?.value;
    if (value(1) !== 12 || value(2) !== 2 || value(3) !== 2)
      throw new Error("收到的不是页面读取回复。");
    const pages = items
      .filter((i) => i.tag === 5 && i.wire === 2)
      .map((i) => decodePage(i.value));
    if (!pages.some((p) => p.index !== undefined))
      throw new Error("设备未返回可编辑页面。");
    if (pages.length > 64) throw new Error("页面数量异常，已停止处理。");
    if (
      pages.some(
        (p) =>
          p.fields.length > 12 || p.fields.some((id) => id < 1 || id > 65535),
      )
    )
      throw new Error("设备字段超出已知范围，已停止处理以保留原配置。");
    return { mode: value(19), pages, raw: hex(bytes) };
  }
  function config(operation, mode, pages = []) {
    if (
      ![1, 2].includes(operation) ||
      !Number.isInteger(mode) ||
      mode < 0 ||
      mode > 9
    )
      throw new Error("无效的页面操作或骑行模式。");
    const items = [scalar(1, 12), scalar(2, 2), scalar(3, operation)];
    if (operation === 1)
      for (const page of pages)
        if (page.index !== undefined) items.push(blob(5, encodePage(page)));
    items.push(scalar(19, mode));
    return concat(...items);
  }
  function frame(payload, operation) {
    if (payload.length > 0xffff) throw new Error("报文过大。");
    const header = new Uint8Array(20).fill(255);
    header[0] = 1;
    header[1] = 12;
    header[2] = 2;
    header[4] = operation;
    header[7] = payload.length >>> 8;
    header[8] = payload.length & 255;
    header[9] = crc8(payload);
    header[10] = 1;
    header[19] = crc8(header.slice(0, 19));
    return concat(header, payload);
  }
  class FrameStream {
    constructor() {
      this.buffer = new Uint8Array();
    }
    push(bytes) {
      this.buffer = concat(this.buffer, bytes);
      if (this.buffer.length > 131072) {
        this.buffer = new Uint8Array();
        throw new Error("蓝牙接收缓冲区超限。");
      }
      const out = [];
      while (this.buffer.length >= 20) {
        const header = this.buffer.slice(0, 20);
        if (
          ![1, 2, 3].includes(header[0]) ||
          crc8(header.slice(0, 19)) !== header[19]
        ) {
          this.buffer = new Uint8Array();
          throw new Error("蓝牙消息头校验失败，请重新读取。");
        }
        const size = header[0] === 2 ? 0 : header[7] * 256 + header[8];
        if (this.buffer.length < size + 20) break;
        const payload = this.buffer.slice(20, size + 20);
        this.buffer = this.buffer.slice(size + 20);
        if (header[0] !== 2 && crc8(payload) !== header[9])
          throw new Error("蓝牙载荷校验失败，请重新读取。");
        out.push({
          header,
          payload,
          kind: header[0],
          service: header[1],
          subservice: header[2],
          operation: header[4],
          status: header[7],
        });
      }
      return out;
    }
  }
  function fingerprint(pages) {
    return JSON.stringify(
      pages.filter((p) => p.index !== undefined).map((p) => hex(encodePage(p))),
    );
  }
  function differences(expected, actual) {
    const a = expected.filter((p) => p.index !== undefined),
      b = actual.filter((p) => p.index !== undefined),
      differences = [];
    if (a.length !== b.length) differences.push("页面数量");
    for (let n = 0; n < Math.min(a.length, b.length); n++) {
      for (const key of [...Object.keys(propertyTags), "fields"]) {
        if (JSON.stringify(a[n][key]) !== JSON.stringify(b[n][key]))
          differences.push(`第 ${n + 1} 页 ${key}`);
      }
      // 固件可能规范化序列化，已知值按语义比较，未知字段按原始字节比较。
      const unknown = (p) =>
        parse(unhex(p.raw || ""))
          .filter((i) => ![...Object.values(propertyTags), 3].includes(i.tag))
          .map((i) => hex(i.raw));
      if (JSON.stringify(unknown(a[n])) !== JSON.stringify(unknown(b[n])))
        differences.push(`第 ${n + 1} 页未知字段`);
    }
    return differences;
  }
  const api = {
    hex,
    unhex,
    concat,
    crc8,
    varint,
    parse,
    scalar,
    blob,
    decodePage,
    encodePage,
    decodeConfig,
    config,
    frame,
    FrameStream,
    fingerprint,
    differences,
  };
  if (typeof module !== "undefined" && module.exports) module.exports = api;
  else root.BSCProtocol = api;
})(globalThis);
