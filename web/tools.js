"use strict";

const BscTools = (() => {
  const gpxNamespace = "http://www.topografix.com/GPX/1/1";
  const originalSha = "d7363b8e9902268de124694e5f043ac49070c65ad3210cb2eb0f575e27f9a933";
  const packageSha = "bfbf186494487f71d501ba6f0bafeba54fc9758879e43ad74d4c2f14203d7a0c";
  const mapVariants = {
    "classic-light": { offset: 0x14d960, color: 0xf6f6f6, register: 1 },
    "classic-dark": { offset: 0x14d97c, color: 0x0f0f0f, register: null },
    "colorful-light": { offset: 0x14d96c, color: 0xf5f5f5, register: 0 },
  };

  function parseLines(text, parse) {
    const rows = text.replace(/^\uFEFF/, "").split(/\r\n?|\n/);
    return rows.flatMap((line, index) => {
      const trimmed = line.trim();
      return trimmed ? [parse(trimmed.split(/[,，]/).map((column) => column.trim()), index + 1)] : [];
    });
  }

  function routePoints(text) {
    const rows = parseLines(text, (columns, line) => {
      if (columns.length < 2 || columns.length > 3 ||
          columns.some((column) => !/^[+-]?(?:\d+(?:\.\d*)?|\.\d+)$/.test(column))) {
        throw new Error(`第 ${line} 行应为纬度,经度[,海拔米]。`);
      }
      const [lat, lon, elevation] = columns.map(Number);
      if (Math.abs(lat) > 90 || Math.abs(lon) > 180 || (elevation !== undefined && !Number.isFinite(elevation))) {
        throw new Error(`第 ${line} 行坐标或海拔超出有效范围。`);
      }
      const point = columns.map((column) => column.replace(/^\+/, ""));
      // GPX uses -180 as the inclusive end of the antimeridian interval.
      if (lon === 180) point[1] = "-180";
      return point;
    });
    if (rows.length < 2) throw new Error("路线至少需要两个坐标点。");
    return rows;
  }

  function backlightCsv(text, type) {
    const isSlider = type === "slider";
    const bounds = isSlider ? [65535, 65535] : [2147483647, 65535, 65535];
    const rows = parseLines(text, (columns, index) => {
      if (columns.length !== bounds.length || columns.some((value, col) => !/^\d+$/.test(value) || Number(value) > bounds[col])) {
        throw new Error(`第 ${index} 行需填写 ${bounds.length} 个逗号分隔的非负整数，数值不能超出固件字段的存储范围。`);
      }
      return columns.map(Number).join(",");
    });
    if (!rows.length) throw new Error("请至少填写一行背光数据。");
    const limit = isSlider ? 20 : 50;
    if (rows.length > limit) throw new Error(`固件最多读取 ${limit} 个档位。`);
    return rows.join("\n") + "\n";
  }

  function readGpx(text) {
    if (/<!DOCTYPE/i.test(text)) throw new Error("请选择不含文档类型声明的 GPX 文件。");
    const xml = new DOMParser().parseFromString(text, "application/xml");
    const root = xml.documentElement;
    if (xml.querySelector("parsererror") || root.localName !== "gpx") throw new Error("无法读取这份 GPX 文件。");
    const children = (parent, name) => Array.from(parent.children).filter(
      (element) => element.localName === name && element.namespaceURI === root.namespaceURI,
    );
    const [route, ...otherRoutes] = children(root, "rte");
    const [track, ...otherTracks] = children(root, "trk");
    if (otherRoutes.length || otherTracks.length || Boolean(route) === Boolean(track)) {
      throw new Error("请选择只包含一条路线或轨迹的 GPX 文件。");
    }
    let points;
    if (route) {
      points = children(route, "rtept");
    } else {
      const segments = children(track, "trkseg");
      if (segments.length !== 1) throw new Error("请选择单段 GPX，避免把不连续的轨迹连起来。");
      points = children(segments[0], "trkpt");
    }
    const title = children(route || track, "name")[0]?.textContent.trim() ?? "";
    const coords = points.map((point) => {
      const row = [point.getAttribute("lat"), point.getAttribute("lon")];
      const elevation = children(point, "ele")[0]?.textContent.trim();
      if (elevation) row.push(elevation);
      return row.join(",");
    }).join("\n");
    return { title, rows: routePoints(coords) };
  }

  function makeGpx(title, text) {
    if (!title || title.length > 80 || /[\u0000-\u0008\u000B\u000C\u000E-\u001F\uFFFE\uFFFF]/u.test(title)) {
      throw new Error("请输入 1–80 个字符的有效路线名称。");
    }
    const xml = document.implementation.createDocument(gpxNamespace, "gpx");
    const add = (parent, elementName) => parent.appendChild(xml.createElementNS(gpxNamespace, elementName));
    const root = xml.documentElement;
    root.setAttribute("version", "1.1");
    root.setAttribute("creator", "BSC500 Settings Workbench");
    const track = add(root, "trk");
    add(track, "name").textContent = title;
    const segment = add(track, "trkseg");
    const rows = routePoints(text);
    for (const [lat, lon, elevation] of rows) {
      const pt = add(segment, "trkpt");
      pt.setAttribute("lat", lat);
      pt.setAttribute("lon", lon);
      if (elevation !== undefined) add(pt, "ele").textContent = elevation;
    }
    const content = '<?xml version="1.0" encoding="UTF-8"?>\n' + new XMLSerializer().serializeToString(xml) + "\n";
    return { content, count: rows.length };
  }

  async function hash(bytes) {
    return Array.from(new Uint8Array(await crypto.subtle.digest("SHA-256", bytes)),
      (value) => value.toString(16).padStart(2, "0")).join("");
  }

  function encodeColor(color, register) {
    const bytes = new Uint8Array(register === null ? 4 : 8);
    const view = new DataView(bytes.buffer);
    if (register === null) {
      view.setUint32(0, color, true);
      return bytes;
    }
    for (const [offset, opcode, value] of [[0, 0xf240, color & 0xffff], [4, 0xf2c0, color >>> 16]]) {
      view.setUint16(offset, opcode | ((value >>> 1) & 0x0400) | (value >>> 12), true);
      view.setUint16(offset + 2, ((value << 4) & 0x7000) | (register << 8) | (value & 255), true);
    }
    return bytes;
  }

  function extractKnownImage(packageBytes) {
    // These offsets apply only to the exact stock package verified by SHA-256.
    const image = new Uint8Array(4482760);
    const payloadStart = 241;
    const imageStart = 5991;
    for (let offset = 0; offset < image.length; offset += 1) {
      const position = imageStart - payloadStart + offset;
      const maskIndex = position % 110;
      const rotationOffset = 128 + position % 113;
      const mask = packageBytes[14 + maskIndex] ^ (maskIndex % 6 === 2 ? 0x30 : 0x32);
      const shift = (packageBytes[rotationOffset] ^ (rotationOffset % 6 === 2 ? 0x30 : 0x32)) & 7;
      const encoded = packageBytes[imageStart + offset];
      image[offset] = (((encoded >>> shift) | (encoded << (8 - shift))) & 255) ^ mask;
    }
    return image;
  }

  async function patchMap(input, variant, hex) {
    const entry = mapVariants[variant];
    if (!entry || !/^#[\da-f]{6}$/i.test(hex)) throw new Error("请选择有效的地图显示状态和颜色。");
    if (!crypto.subtle) throw new Error("此浏览器环境无法校验 SHA-256。请从 localhost 打开网页。");
    const digest = await hash(input);
    const image = digest === packageSha ? extractKnownImage(input) : input.slice();
    const imageSha = digest === packageSha ? await hash(image) : digest;
    if (imageSha !== originalSha) throw new Error("文件校验不匹配。请选择 BSC500 V1.04.003 的原厂文件。");
    const original = encodeColor(entry.color, entry.register);
    const replacement = encodeColor(Number.parseInt(hex.slice(1), 16), entry.register);
    let changed = 0;
    original.forEach((byte, index) => {
      if (image[entry.offset + index] !== byte) throw new Error("原厂指令与预期不符。");
      changed += Number(byte !== replacement[index]);
    });
    if (changed === 0) throw new Error("颜色与原厂颜色相同，请另选一种颜色。");
    image.set(replacement, entry.offset);
    return { image, changed };
  }

  return { backlightCsv, makeGpx, readGpx, patchMap };
})();
