"use strict";

(function (root) {
  const P =
    typeof module !== "undefined" && module.exports
      ? require("./protocol.js")
      : root.BSCProtocol;
  const typeNames = {
    1: "数据页",
    2: "海拔",
    3: "地图",
    4: "区域",
    5: "训练课程",
    6: "骑行台",
    7: "指南针",
    8: "计圈",
    9: "路书",
    10: "爬坡",
    11: "AI 伙伴",
    12: "赛段",
    13: "训练效果",
    14: "爬坡地图",
    15: "组队骑行",
    16: "音乐控制",
    17: "运动相机",
  };
  const editable = ["status", "fields", "pageMode", "lineWidth", "main"];
  const graphAssets = {
    0x0b01: "spd_text_chart",
    0x0b02: "spd_dash_board",
    0x0b03: "spd_line_chart",
    0x0b04: "cad_text_chart",
    0x0b05: "cad_dash_board",
    0x0b06: "cad_line_chart",
    0x0b07: "hr_text_chart",
    0x0b08: "hr_dash_board",
    0x0b09: "hr_range",
    0x0b0a: "hr_line_chart",
    0x0b0b: "pwr_text_chart",
    0x0b0c: "pwr_dash_board",
    0x0b0d: "pwr_range",
    0x0b0e: "pwr_line_chart",
    0x0b0f: "elevation_line_chart",
    0x0b10: "aerobic_effect_dash_board",
    0x0b11: "anaerobic_effect_dash_board",
    0x0b12: "stamina_dash_board",
    0x0b13: "power_group_dash_board",
    0x0b14: "power_group_zone_range",
    0x0b1b: "temp_line_chart",
  };
  // APK 素材命名为 <行数>x<列数>：1x2 全宽单行、2x1 半宽两行、2x2 全宽两行、3x2 全宽三行。
  // 一行内只有 1 项时占满 320px（2 列），多项时每项占 1 列。
  const graphSizes = ["1x2", "2x1", "2x2", "3x2"];
  function graphSize(row) {
    const rows = Math.max(1, Math.min(3, Math.round(row.weight)));
    const columns = row.count <= 1 ? 2 : 1;
    const exact = `${rows}x${columns}`;
    // APK 只提供 1x2、2x1、2x2、3x2 四种；1 行 1 列没有对应素材。
    return graphSizes.includes(exact) ? exact : null;
  }
  function isGraphField(id) {
    return Object.hasOwn(graphAssets, id);
  }
  // 真机 16 个页面的 graphic_display 与图形字段位置掩码逐一吻合，位序为字段序号（低位在前）。
  function graphicMask(fields) {
    let mask = 0;
    fields.forEach((id, index) => {
      if (isGraphField(id)) mask |= 1 << index;
    });
    return mask;
  }
  function graphPreview(id, row) {
    const asset = graphAssets[id];
    const size = graphSize(row);
    if (!asset || !size) return null;
    const ext = id === 0x0b1b ? "webp" : "png";
    return `assets/igpsport-cn-8.11.00/field-previews/icon_${asset}_${size}_bg1.${ext}`;
  }
  // 1 行 1 列的格子放不下图形时，APK 改显示对应基础指标的读数。
  function graphBaseField(id) {
    if (id >= 0x0b01 && id <= 0x0b03) return 257;
    if (id >= 0x0b04 && id <= 0x0b06) return 513;
    if (id >= 0x0b07 && id <= 0x0b0a) return 769;
    if (id >= 0x0b0b && id <= 0x0b0e) return 1025;
    return id >= 0x0b0f && id <= 0x0b1b ? 257 : null;
  }
  function unitFor(id) {
    // 单位按字段语义，不把同组中的 FTP%、IF、TSS 或区间一律当成瓦数／心率。
    if (id >= 0x0b01 && id <= 0x0b0e)
      return unitFor(
        id <= 0x0b03
          ? 0x0101
          : id <= 0x0b06
            ? 0x0201
            : id <= 0x0b0a
              ? 0x0301
              : 0x0401,
      );
    if ([0x0208, 0x0308, 0x0408].includes(id)) return "区间";
    if (
      (id >= 0x0309 && id <= 0x030f) ||
      id === 0x040c ||
      (id >= 0x0412 && id <= 0x041a) ||
      [
        0x041c, 0x041d, 0x041f, 0x0420, 0x0421, 0x0808, 0x0a04, 0x0a05, 0x0a06,
        0x0b12, 0x0c04, 0x0d01, 0x1004, 0x1101, 0x1104, 0x1106,
      ].includes(id)
    )
      return "%";
    if ([0x040d, 0x040f].includes(id) || (id >= 0x0427 && id <= 0x042d))
      return "";
    if ([0x0410, 0x0430].includes(id) || (id >= 0x0422 && id <= 0x0426))
      return "W/kg";
    if (id === 0x0411) return "kJ";
    if (id >> 8 === 6) {
      if ([0x0602, 0x060d, 0x060e, 0x060f, 0x0610, 0x0615, 0x0616].includes(id))
        return "%";
      return [
        0x0603, 0x0604, 0x0609, 0x060a, 0x060b, 0x060c, 0x0613, 0x0614,
      ].includes(id)
        ? "m/h"
        : "m";
    }
    if (id === 0x0804 || id === 0x0b1b || id >> 8 === 0x1a) return "°C";
    if (id === 0x0806 || id === 0x0b0f) return "m";
    if (id === 0x0807) return "°";
    if (id === 0x0705) return "圈";
    if ([0x0d02, 0x0e03, 0x0f04].includes(id)) return "km";
    return (
      { 1: "km/h", 2: "rpm", 3: "bpm", 4: "W", 5: "km", 9: "kcal" }[id >> 8] ||
      ""
    );
  }
  function rows(page) {
    let mode = page.pageMode,
      width = page.lineWidth;
    const result = [];
    for (let n = 0; n < 10 && (mode || width); n++) {
      const count = mode % 8,
        weight = width % 8;
      if (count < 1 || count > 3 || weight < 1) return [];
      result.push({ count, weight });
      mode = Math.floor(mode / 8);
      width = Math.floor(width / 8);
    }
    if (
      mode ||
      width ||
      result.reduce((sum, r) => sum + r.count, 0) !== page.fields.length
    )
      return [];
    return result;
  }
  function withKeys(pages) {
    const seen = new Map();
    return pages
      .filter((p) => p.index !== undefined)
      .map((p) => {
        const identity = `${p.index}:${p.type}`,
          occurrence = seen.get(identity) || 0;
        seen.set(identity, occurrence + 1);
        return { ...p, key: `${identity}:${occurrence}` };
      });
  }
  function title(page) {
    return (
      page.title ||
      page.name ||
      `${typeNames[page.type] || `类型 ${page.type}`}${page.type === 1 ? ` ${page.index}` : ""}`
    );
  }
  function makePage(index, type, layout, fields, title, status = 1) {
    const page = {
      index,
      type,
      fields,
      status,
      pageMode: layout[0],
      lineWidth: layout[1],
      main: 0,
      graphic: 0,
      forbidden: 0,
      dataSite: 0,
      name: "",
      title,
      raw: "",
    };
    page.raw = P.hex(P.encodePage(page));
    return page;
  }
  function sample() {
    const pages = [
      makePage(
        1,
        1,
        [1161, 594],
        [257, 1795, 1281, 769, 1025, 513],
        "骑行总览",
      ),
      makePage(2, 1, [145, 146], [1033, 1028, 1798, 769, 513], "功率训练"),
      makePage(6, 3, [18, 9], [257, 769, 1025, 1281], "地图导航"),
      makePage(8, 2, [18, 9], [257, 1541, 1538, 1537], "海拔与爬升"),
      makePage(11, 8, [0, 0], [], "计圈数据"),
      makePage(3, 1, [81, 83], [1798, 1284, 261, 1028], "单圈表现", 0),
      makePage(4, 1, [74, 146], [1538, 1537, 1541, 1539], "爬坡观察", 0),
      makePage(
        5,
        1,
        [74898, 37449],
        [1281, 1795, 260, 258, 771, 770, 515, 514, 1027, 1026, 2305, 257],
        "完整数据",
        0,
      ),
    ];
    pages[0].main = 1;
    return withKeys(pages);
  }
  function validatePlan(input) {
    if (!input || input.format !== "bsc500-page-plan" || input.version !== 1)
      throw new Error(
        "请选择本编辑器导出的页面方案或配置备份。旧版原创设计 JSON 请在研究工具中打开。",
      );
    if (!Number.isInteger(input.mode) || input.mode < 0 || input.mode > 9)
      throw new Error("方案的骑行模式编号无效。");
    if (
      !Array.isArray(input.pages) ||
      !input.pages.length ||
      input.pages.length > 64
    )
      throw new Error("方案页面数量无效。");
    const pages = input.pages.map((p) => {
      if (!p || typeof p !== "object") throw new Error("方案页面格式无效。");
      for (const name of [
        "index",
        "type",
        "pageMode",
        "lineWidth",
        "graphic",
        "dataSite",
      ]) {
        if (
          !Number.isInteger(p[name] ?? 0) ||
          (p[name] ?? 0) < 0 ||
          (p[name] ?? 0) >
            (name === "pageMode" || name === "lineWidth" ? 0x3fffffff : 65535)
        )
          throw new Error(`页面参数 ${name} 无效。`);
      }
      if (p.index === undefined || p.type === undefined)
        throw new Error("页面缺少索引或类型。");
      for (const name of ["status", "main", "forbidden"])
        if (![0, 1].includes(p[name] ?? 0))
          throw new Error(`页面状态 ${name} 无效。`);
      if (
        !Array.isArray(p.fields) ||
        p.fields.length > 12 ||
        p.fields.some((id) => !Number.isInteger(id) || id < 1 || id > 65535)
      )
        throw new Error("方案包含无效的数据字段。");
      const title = typeof p.title === "string" ? p.title.slice(0, 30) : "";
      const name = typeof p.name === "string" ? p.name.slice(0, 80) : "";
      // 导入的 raw 只用于离线保存。应用到设备时使用刚读取的原始页，绝不信任文件中的未知字段。
      if (
        p.raw !== undefined &&
        (typeof p.raw !== "string" ||
          p.raw.length > 8192 ||
          !/^(?:[\da-f]{2})*$/i.test(p.raw))
      )
        throw new Error("备份的原始报文无效。");
      return {
        index: p.index,
        type: p.type,
        status: p.status ?? 0,
        main: p.main ?? 0,
        forbidden: p.forbidden ?? 0,
        fields: [...p.fields],
        pageMode: p.pageMode ?? 0,
        lineWidth: p.lineWidth ?? 0,
        graphic: p.graphic ?? 0,
        dataSite: p.dataSite ?? 0,
        raw: p.raw || "",
        name,
        title,
      };
    });
    return { mode: input.mode, pages: withKeys(pages) };
  }
  const sameFields = (a, b) =>
    a.length === b.length && a.every((id, index) => id === b[index]);
  function mergePlan(plan, livePages) {
    const planMap = new Map(plan.pages.map((p) => [p.key, p])),
      liveMap = new Map(livePages.map((p) => [p.key, p]));
    for (const p of plan.pages)
      if (!liveMap.has(p.key))
        throw new Error(
          `设备没有方案中的页面槽位 ${p.index}（类型 ${p.type}），未应用方案。`,
        );
    let result = livePages.map((page) => {
      const imported = planMap.get(page.key);
      if (!imported) return structuredClone(page);
      if (page.forbidden && page.status !== imported.status)
        throw new Error(`「${title(page)}」的启用状态受设备限制，未应用方案。`);
      const next = {
        ...structuredClone(page),
        title: imported.title || title(page),
      };
      for (const key of editable) next[key] = structuredClone(imported[key]);
      // 普通数据页的 graphic_display 位由图形字段位置决定：字段变化时重算，
      // 未变化则保留设备原值。专项页的该字段语义未确认，一律保留原值。
      if (next.type === 1 && !sameFields(next.fields, page.fields))
        next.graphic = graphicMask(next.fields);
      if (
        next.fields.length !== page.fields.length ||
        next.pageMode !== page.pageMode ||
        next.lineWidth !== page.lineWidth
      ) {
        // 具体版式合法性由拥有 APK 目录的调用方进一步校验。
        if (next.fields.length && !rows(next).length)
          throw new Error("方案的字段数量与版式参数不匹配。");
      }
      return next;
    });
    // 完整备份恢复页面顺序；部分方案不移动未包含的设备页面。
    if (plan.pages.length === livePages.length)
      result = plan.pages.map((p) => result.find((r) => r.key === p.key));
    const importedMain = plan.pages.filter((p) => p.main === 1);
    if (importedMain.length > 1) throw new Error("方案包含多个主页。");
    if (importedMain.length === 1)
      for (const p of result) p.main = p.key === importedMain[0].key ? 1 : 0;
    if (
      result.filter((p) => p.main).length > 1 ||
      result.some((p) => p.main && !p.status) ||
      !result.some((p) => p.status)
    )
      throw new Error("方案需保留可见页面，主页不能关闭。");
    return result;
  }
  const api = {
    typeNames,
    graphicMask,
    graphPreview,
    graphSize,
    isGraphField,
    graphBaseField,
    unitFor,
    rows,
    withKeys,
    title,
    sample,
    validatePlan,
    mergePlan,
    editable,
  };
  if (typeof module !== "undefined" && module.exports) module.exports = api;
  else root.BSCEditorModel = api;
})(globalThis);
