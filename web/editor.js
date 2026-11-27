"use strict";

(() => {
  const P = window.BSCProtocol,
    M = window.BSCEditorModel,
    D = window.BSCEditorData,
    G = window.BSCDeviceProfiles;
  const $ = (id) => document.getElementById(id),
    clone = (value) => structuredClone(value);
  const fieldMap = new Map(D.fields.map((field) => [field.id, field]));
  const bluetooth = new window.BSCBluetooth();
  let device = "BSC500";
  const storageKey = () =>
    device === "BSC500"
      ? "bsc500-layout-workspace-v1"
      : `igpsport-layout-workspace-v1:${device}`;
  const backupKey = "bsc500-layout-backups-v1",
    logStorageKey = "bsc500-ble-diagnostics-v2";
  const samples = {
    257: "28.6",
    258: "25.8",
    260: "46.2",
    261: "27.3",
    513: "86",
    514: "82",
    515: "112",
    769: "142",
    770: "137",
    771: "168",
    772: "141",
    1025: "218",
    1026: "196",
    1028: "205",
    1033: "221",
    1035: "210",
    1039: "42",
    1037: "0.82",
    1040: "3.2",
    1281: "32.48",
    1282: "127.8",
    1284: "4.62",
    1537: "386",
    1538: "3.6",
    1539: "452",
    1541: "628",
    1793: "01:24:36",
    1795: "01:08:24",
    1798: "05:05",
    2049: "09:42",
    2052: "24",
    1797: "6",
    776: "3",
    1032: "4",
    0x0208: "3",
    0x0808: "82",
    0x0411: "824",
    2305: "672",
  };
  const categoryNames = {
    1: "速度",
    2: "踏频",
    3: "心率",
    4: "功率",
    5: "距离",
    6: "高度",
    7: "时间",
    8: "其他",
    9: "其他",
    10: "电子变速",
    11: "图形",
    12: "其他",
    13: "其他",
    14: "其他",
    15: "其他",
  };
  let mode = 0,
    pages = M.sample(device),
    selectedKey = pages[0].key,
    activeField = 0,
    baseline = null;
  let past = [],
    future = [],
    busy = false,
    theme = "light",
    fieldCategory = "常用",
    logs = loadDiagnosticLogs(),
    lastSynced = "";
  let savedModes = {},
    dragState = null,
    storageAvailable = true,
    syncPhase = "idle",
    diagnosticStorageFailed = false;

  function icon(name, className = "icon") {
    const node = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    node.setAttribute("class", className);
    node.setAttribute("aria-hidden", "true");
    const use = document.createElementNS("http://www.w3.org/2000/svg", "use");
    use.setAttribute("href", `#i-${name}`);
    node.append(use);
    return node;
  }
  function element(tag, className, text) {
    const node = document.createElement(tag);
    if (className) node.className = className;
    if (text !== undefined) node.textContent = text;
    return node;
  }
  function loadDiagnosticLogs() {
    try {
      const saved = JSON.parse(localStorage.getItem(logStorageKey) || "[]");
      return Array.isArray(saved)
        ? saved
            .filter((line) => typeof line === "string")
            .slice(-400)
            .map((line) => line.slice(0, 2048))
        : [];
    } catch {
      return [];
    }
  }
  function renderDiagnosticLogs() {
    $("session-log").textContent = logs.join("\n") || "尚无蓝牙操作";
    $("diagnostic-log-status").textContent = diagnosticStorageFailed
      ? "本机存储失败，请在刷新前导出日志。"
      : `最近 ${logs.length} 条诊断日志 · 仅保存在当前浏览器，刷新后仍可查看`;
  }
  function recordDiagnosticLog(message) {
    const timestamp = new Date().toLocaleString("zh-CN", {
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      hour12: false,
      fractionalSecondDigits: 3,
    });
    const line = `${timestamp} ${message}`;
    logs.push(line);
    logs = logs.slice(-400);
    let length = logs.reduce((total, value) => total + value.length, 0);
    while (length > 180000 && logs.length > 1) length -= logs.shift().length;
    try {
      localStorage.setItem(logStorageKey, JSON.stringify(logs));
      diagnosticStorageFailed = false;
    } catch {
      diagnosticStorageFailed = true;
    }
    renderDiagnosticLogs();
  }
  function notice(message, error = false) {
    $("notice").textContent = message;
    $("notice").dataset.error = String(error);
    $("notice").hidden = !message;
  }
  function current() {
    return pages.find((p) => p.key === selectedKey) || pages[0];
  }
  function dataField(id) {
    return (
      fieldMap.get(id) || {
        id,
        label: `未知字段 0x${id.toString(16).toUpperCase()}`,
        english: "Unknown field",
        verified: false,
      }
    );
  }
  const unitFor = M.unitFor;
  function sampleFor(id) {
    if (Object.hasOwn(samples, id)) return samples[id];
    if (id >= 0x0427 && id <= 0x042d) return "05:05";
    if (unitFor(id) === "区间") return "3";
    if (unitFor(id) === "%") return "75";
    return (
      {
        1: "28.6",
        2: "86",
        3: "142",
        4: "218",
        5: "32.48",
        6: "386",
        7: "05:05",
        8: "09:42",
      }[id >> 8] || "—"
    );
  }
  const profile = () => G.get(device);
  function layoutsFor(page) {
    return G.layoutsFor(device, page.type, M.typeNames[page.type]);
  }
  function matchingLayout(page) {
    return layoutsFor(page).find(
      (l) =>
        l.pageMode === page.pageMode &&
        l.lineWidth === page.lineWidth &&
        l.count === page.fields.length,
    );
  }
  function snapshot() {
    return JSON.stringify({ pages, selectedKey });
  }
  function changed() {
    return Boolean(baseline && P.differences(baseline.pages, pages).length);
  }
  function isDifferentBeforeRead() {
    return (
      JSON.stringify(pages) !== JSON.stringify(M.sample(device)) && !lastSynced
    );
  }
  function persist() {
    savedModes[mode] = {
      format: device === "BSC500" ? "bsc500-page-plan" : "igpsport-page-plan",
      version: 1,
      device,
      mode,
      pages,
      selectedKey,
    };
    try {
      localStorage.setItem(
        storageKey(),
        JSON.stringify({ modes: savedModes, mode }),
      );
    } catch {
      storageAvailable = false;
    }
    $("save-status").textContent = storageAvailable
      ? `草稿已保存 · ${new Date().toLocaleTimeString("zh-CN", { hour: "2-digit", minute: "2-digit" })}`
      : "本机存储不可用，请导出方案";
  }
  function restoreDraft() {
    try {
      const saved = JSON.parse(localStorage.getItem(storageKey()) || "null");
      if (
        saved?.modes &&
        Number.isInteger(saved.mode) &&
        saved.mode >= 0 &&
        saved.mode <= 9
      ) {
        savedModes = saved.modes;
        mode = saved.mode;
        const plan = M.validatePlan(savedModes[mode], device);
        pages = plan.pages;
        selectedKey = pages.some((p) => p.key === savedModes[mode].selectedKey)
          ? savedModes[mode].selectedKey
          : pages[0].key;
        $("ride-mode").value = String(mode);
        notice(
          profile().bluetooth
            ? "已恢复本机草稿。要写入码表，请先导出方案，读取设备当前配置后再导入；不会自动发送保存的草稿。"
            : `已恢复 ${device} 本机草稿；仅离线预览，未连接设备。`,
        );
      }
    } catch {
      notice("本机草稿无法读取，已打开示例；未操作设备。", true);
    }
  }
  // 真机与官方写入报文一致：普通数据页的 graphic_display 位 i 表示第 i 个字段按图形绘制。
  function syncGraphic(page) {
    if (page.type === 1) page.graphic = M.graphicMask(page.fields);
  }
  function commit(change) {
    if (busy) return;
    const before = snapshot();
    change();
    if (snapshot() !== before) {
      past.push(before);
      if (past.length > 60) past.shift();
      future = [];
      lastSynced = "";
      persist();
    }
    render();
  }
  function history(direction) {
    if (busy) return;
    const from = direction === "undo" ? past : future,
      to = direction === "undo" ? future : past;
    if (!from.length) return;
    to.push(snapshot());
    const value = JSON.parse(from.pop());
    pages = value.pages;
    selectedKey = value.selectedKey;
    activeField = 0;
    lastSynced = "";
    persist();
    render();
  }
  function selectPage(key) {
    if (busy) return;
    selectedKey = key;
    activeField = 0;
    render();
    persist();
  }
  function normalisedRows(page) {
    const rows = M.rows(page);
    return rows.length
      ? rows
      : page.fields.map(() => ({
          count: 1,
          weight: Math.max(1, profile().rows / page.fields.length),
        }));
  }
  function miniature(page) {
    const miniature = element("span", "mini-layout");
    miniature.setAttribute("aria-hidden", "true");
    const rows = normalisedRows(page),
      total = rows.reduce((sum, row) => sum + row.weight, 0);
    if (page.type !== 1) {
      const special = element("span", "mini-layout-special");
      special.style.flex = String(Math.max(1, profile().rows - total));
      miniature.append(special);
    }
    for (const row of rows) {
      const line = element("span", "mini-layout-row");
      line.style.flex = String(row.weight);
      for (let n = 0; n < row.count; n++)
        line.append(element("span", "mini-layout-cell"));
      miniature.append(line);
    }
    return miniature;
  }
  function bindDrag(node, kind, value, drop) {
    node.draggable = true;
    node.addEventListener("dragstart", (event) => {
      if (busy || event.target.closest("[draggable='true']") !== node) {
        if (busy) event.preventDefault();
        return;
      }
      dragState = { kind, value, page: selectedKey };
      event.dataTransfer.setData("text/plain", `bsc500-${kind}`);
      event.dataTransfer.effectAllowed = "move";
      node.classList.add("dragging");
      event.stopPropagation();
    });
    node.addEventListener("dragover", (event) => {
      if (dragState?.kind !== kind || dragState.page !== selectedKey) return;
      event.preventDefault();
      event.dataTransfer.dropEffect = "move";
      node.classList.add("drag-over");
      event.stopPropagation();
    });
    node.addEventListener("dragleave", () =>
      node.classList.remove("drag-over"),
    );
    node.addEventListener("drop", (event) => {
      event.preventDefault();
      event.stopPropagation();
      node.classList.remove("drag-over");
      if (!busy && dragState?.kind === kind && dragState.page === selectedKey)
        drop(dragState.value, value);
      dragState = null;
    });
    node.addEventListener("dragend", () => {
      dragState = null;
      document
        .querySelectorAll(".dragging,.drag-over")
        .forEach((n) => n.classList.remove("dragging", "drag-over"));
    });
  }
  function swapFields(from, to) {
    if (
      from === to ||
      from < 0 ||
      to < 0 ||
      from >= current().fields.length ||
      to >= current().fields.length
    )
      return;
    commit(() => {
      const p = current();
      [p.fields[from], p.fields[to]] = [p.fields[to], p.fields[from]];
      activeField = to;
      syncGraphic(p);
    });
  }
  function renderPages() {
    const list = $("page-list");
    list.replaceChildren();
    $("page-count").textContent =
      `${pages.filter((p) => p.status).length} / ${pages.length}`;
    for (const page of pages) {
      const wrapper = element(
        "div",
        `page-item${page.key === selectedKey ? " selected" : ""}${page.status ? "" : " disabled-page"}`,
      );
      wrapper.dataset.pageKey = page.key;
      const button = element("button", "page-select");
      button.type = "button";
      button.setAttribute("aria-pressed", String(page.key === selectedKey));
      button.setAttribute(
        "aria-label",
        `${M.title(page)}，${page.status ? "已启用" : "未启用"}`,
      );
      button.append(miniature(page));
      const info = element("span", "page-info"),
        name = element("strong", "", M.title(page));
      if (page.main) {
        name.append(icon("home", "page-home"));
        name.title = "当前主页";
      }
      info.append(
        name,
        element(
          "small",
          "",
          `${M.typeNames[page.type] || "原生页"} · ${page.fields.length ? `${page.fields.length} 项` : "整页"}${page.status ? "" : " · 未启用"}`,
        ),
      );
      button.append(info);
      button.addEventListener("click", () => selectPage(page.key));
      wrapper.append(button, icon("grip", "page-grip"));
      bindDrag(wrapper, "page", page.key, (from, to) => {
        if (from === to) return;
        commit(() => {
          const start = pages.findIndex((p) => p.key === from),
            end = pages.findIndex((p) => p.key === to);
          const [item] = pages.splice(start, 1);
          pages.splice(end, 0, item);
        });
      });
      wrapper.addEventListener("keydown", (event) => {
        if (!event.altKey || !["ArrowUp", "ArrowDown"].includes(event.key))
          return;
        event.preventDefault();
        const index = pages.indexOf(page),
          next = index + (event.key === "ArrowUp" ? -1 : 1);
        if (next >= 0 && next < pages.length) {
          commit(() => {
            [pages[index], pages[next]] = [pages[next], pages[index]];
          });
          list.querySelector(`[data-page-key="${page.key}"] button`)?.focus();
        }
      });
      list.append(wrapper);
    }
    const dots = $("page-dots");
    dots.replaceChildren();
    pages.forEach((p) => {
      const button = element("button", "page-dot");
      button.title = M.title(p);
      button.setAttribute("aria-label", `切换到${M.title(p)}`);
      if (p.key === selectedKey) button.setAttribute("aria-current", "page");
      button.addEventListener("click", () => selectPage(p.key));
      dots.append(button);
    });
  }
  function specialPreview(page, weight) {
    const special = element("div", "special-preview");
    special.style.flex = String(weight);
    if ([3, 14].includes(page.type)) {
      // 仅为构图占位的示例地图，不调用地图服务、不展示真实位置。
      special.innerHTML =
        '<svg class="sample-map" viewBox="0 0 246 246" preserveAspectRatio="xMidYMid slice" aria-hidden="true"><rect width="246" height="246" fill="#e9e9e4"/><path d="M0 0h89l-9 65L0 93zm152 0h94v142l-71-14zM0 166l86-14 8 94H0zm160-1 86-12v93h-83z" fill="#c3deb4"/><path d="M18 0 37 47 32 99 0 127v34l50-38 8-57L43 0" fill="#a4d6e3"/><path d="m76-10 25 68-3 84 29 104M-5 105l77 14 63 4 116-14M158-5l-15 89 26 69 5 94M-5 206l85-23 81 8 90-25" stroke="#fff" stroke-width="9" fill="none"/><path d="m120-10-5 93 7 58 16 105" stroke="#c2bba3" stroke-width="12" fill="none"/><path d="m120-10-5 93 7 58 16 105" stroke="#ffed9a" stroke-width="8" fill="none"/><path d="m120-10-5 93 7 58 16 105" stroke="#28884f" stroke-width="4" fill="none"/><circle cx="127" cy="176" r="11" fill="#fff"/><circle cx="127" cy="176" r="9" fill="#268cec"/><path d="m127 170-5 11 5-2 5 2Z" fill="#fff"/></svg><span class="map-north">N</span><span class="map-caption">示例地图 · 路线由码表绘制</span>';
    } else {
      const symbols = {
        2: "⌁",
        5: "◎",
        6: "◉",
        7: "N",
        8: "◷",
        10: "△",
        12: "⚑",
        13: "◎",
        15: "⋈",
        16: "♫",
        17: "▣",
      };
      special.append(
        element("span", "special-symbol", symbols[page.type] || "◇"),
        element("strong", "", M.typeNames[page.type] || "码表原生内容"),
        element("small", "", "原生内容区 · 实际画面由码表绘制"),
      );
    }
    return special;
  }
  function renderPreview() {
    const p = current(),
      screen = $("device-screen");
    screen.replaceChildren();
    screen.classList.toggle("dark", theme === "dark");
    $("preview-heading").textContent = M.title(p);
    const rows = normalisedRows(p),
      total = rows.reduce((sum, r) => sum + r.weight, 0);
    if (p.type !== 1)
      screen.append(specialPreview(p, Math.max(1, profile().rows - total)));
    let index = 0;
    for (const row of rows) {
      const line = element(
        "div",
        `screen-row${row.weight === 1 ? " compact" : ""}`,
      );
      line.style.flex = String(row.weight);
      for (let col = 0; col < row.count; col++) {
        const position = index++,
          id = p.fields[position],
          field = dataField(id),
          tile = element(
            "button",
            `screen-tile${position === activeField ? " active" : ""}`,
          );
        tile.type = "button";
        tile.dataset.fieldIndex = String(position);
        tile.setAttribute(
          "aria-label",
          `第 ${position + 1} 项：${field.label}，点击修改`,
        );
        // 设备按 graphic_display 位决定该格是否画图，普通数据页的位序与字段序号一致。
        const graphicActive =
          p.type !== 1 || ((p.graphic >> position) & 1) === 1;
        const graphic = graphicActive ? M.graphPreview(id, row) : null;
        const graphBase = graphic
          ? null
          : M.isGraphField(id)
            ? M.graphBaseField(id)
            : null;
        const shown = graphBase === null ? id : graphBase;
        tile.title = graphic
          ? `${field.label}：APK 原厂固定示例图（保留素材原色），非实时数据；点击修改`
          : graphBase !== null
            ? `${field.label}：此格过窄放不下图形，APK 改显示${dataField(graphBase).label}读数；点击修改`
            : `修改${field.label}`;
        const preferredSize =
          row.count >= 3
            ? 11
            : row.weight <= 1
              ? 14
              : row.count === 2
                ? 19
                : 22;
        const fittingSize =
          (100 / row.count - 5) / (sampleFor(shown).length * 0.58);
        tile.style.setProperty(
          "--value-size",
          `${Math.min(preferredSize, fittingSize)}cqw`,
        );
        tile.append(element("span", "tile-index", position + 1));
        if (graphic) {
          tile.classList.add("graphic-tile");
          const image = element("img", "screen-graphic");
          image.alt = `${field.label}：APK 原厂固定示例图，非实时读数`;
          image.draggable = false;
          image.addEventListener(
            "error",
            () => {
              image.remove();
              tile.append(
                element("span", "screen-label", field.label),
                element("small", "graphic-unavailable", "图形素材未加载"),
              );
            },
            { once: true },
          );
          image.src = graphic;
          tile.append(image);
        } else {
          tile.append(
            element(
              "span",
              "screen-label",
              graphBase === null ? field.label : dataField(graphBase).label,
            ),
            element("span", "screen-value", sampleFor(shown)),
            element("span", "screen-unit", unitFor(shown)),
          );
        }
        tile.addEventListener("click", () => openField(position));
        bindDrag(tile, "field", position, swapFields);
        line.append(tile);
      }
      screen.append(line);
    }
  }
  function renderInspector() {
    const p = current();
    $("page-index").textContent =
      `PAGE ${String(pages.indexOf(p) + 1).padStart(2, "0")}`;
    if (document.activeElement !== $("page-name"))
      $("page-name").value = M.title(p);
    $("page-enabled").setAttribute("aria-checked", String(Boolean(p.status)));
    $("page-enabled").disabled = busy || Boolean(p.forbidden || p.main);
    $("page-enabled").title = p.forbidden
      ? "设备限制此页启用状态，保留原值"
      : p.main
        ? "主页需保持启用，请先设置其他主页"
        : "切换页面启用状态";
    $("page-main").textContent = p.main ? "当前主页" : "设为主页";
    $("page-main").classList.toggle("accent", Boolean(p.main));
    $("page-main").disabled = busy || !p.status || Boolean(p.main);
    const layouts = layoutsFor(p),
      countInput = $("field-count");
    countInput.replaceChildren();
    const counts = [
      ...new Set([...layouts.map((l) => l.count), p.fields.length]),
    ].sort((a, b) => a - b);
    counts.forEach((count) => countInput.add(new Option(count, count)));
    countInput.value = String(p.fields.length);
    countInput.disabled = busy || !layouts.length;
    const options = $("layout-options");
    options.replaceChildren();
    const choices = layouts.filter((l) => l.count === p.fields.length);
    choices.forEach((layout, n) => {
      const selected =
        layout.pageMode === p.pageMode && layout.lineWidth === p.lineWidth;
      const button = element(
        "button",
        `layout-choice${selected ? " selected" : ""}`,
      );
      button.type = "button";
      button.setAttribute("aria-pressed", String(selected));
      button.setAttribute(
        "aria-label",
        `${p.fields.length} 项数据，样式 ${n + 1}`,
      );
      button.title = `page_mode ${layout.pageMode} · line_width ${layout.lineWidth}`;
      button.disabled = busy;
      button.append(
        miniature({
          ...p,
          pageMode: layout.pageMode,
          lineWidth: layout.lineWidth,
        }),
        element("span", "", `样式 ${n + 1}`),
      );
      button.addEventListener("click", () => applyLayout(layout));
      options.append(button);
    });
    if (!choices.length || !matchingLayout(p))
      options.append(element("span", "layout-hint", "保留设备当前布局"));
    $("layout-hint").textContent = matchingLayout(p)
      ? profile().bluetooth
        ? "APK 内置版式 · 同步实际布局参数"
        : "APK 内置版式 · 仅离线预览，未验证真机"
      : "此布局未收录于目录，保留原参数；可替换已有字段";
    $("field-total").textContent = ` / ${p.fields.length}`;
    const list = $("field-list");
    list.replaceChildren();
    p.fields.forEach((id, index) => {
      const item = element(
        "div",
        `field-item${activeField === index ? " selected" : ""}`,
      );
      item.dataset.fieldIndex = String(index);
      const button = element("button", "field-pick");
      button.type = "button";
      button.setAttribute(
        "aria-label",
        `修改第 ${index + 1} 项：${dataField(id).label}`,
      );
      button.disabled = busy;
      button.append(
        element("strong", "", dataField(id).label),
        element("small", "", M.isGraphField(id) ? "图形" : unitFor(id)),
        icon("arrow"),
      );
      button.addEventListener("click", () => openField(index));
      const moves = element("span", "field-move");
      for (const [name, target, label] of [
        ["up", index - 1, "上移"],
        ["down", index + 1, "下移"],
      ]) {
        const move = element("button", "icon-button");
        move.setAttribute("aria-label", `${label}第 ${index + 1} 项`);
        move.title = label;
        move.disabled = busy || target < 0 || target >= p.fields.length;
        move.append(icon(name));
        move.addEventListener("click", () => swapFields(index, target));
        moves.append(move);
      }
      item.append(
        element("span", "field-position", String(index + 1).padStart(2, "0")),
        button,
        moves,
        icon("grip", "field-grip"),
      );
      bindDrag(item, "field", index, swapFields);
      list.append(item);
    });
    $("no-fields").hidden = Boolean(p.fields.length);
  }
  function renderDevice() {
    const p = profile();
    $("device-model").value = device;
    $("device-model").disabled = busy;
    $("show-all-fields").disabled = !p.bluetooth;
    if (!p.bluetooth) $("show-all-fields").checked = true;
    $("breadcrumb-device").textContent = device;
    document.title = `${device} 数据页配置 · 骑行工作台`;
    $("device-capability").textContent =
      (p.bluetooth
        ? "BSC500 · 实验性蓝牙同步；写入仍待真机验证。"
        : `${p.rows} 行 / 最多 ${p.maxFields} 项 · APK 内置版式，仅离线展示，未验证真机。图形借用公共素材。`) +
      "外壳为官方设备目录的机型专属图片，已缓存到本地；目录后续可能更新。";
    const body = $("device-body"),
      frame = p.frame;
    for (const [name, value] of Object.entries({
      "--device-width": `${frame.width}px`,
      "--device-ratio": `${frame.width} / ${frame.height}`,
      "--screen-left": `${(100 * frame.left) / frame.width}%`,
      "--screen-top": `${(100 * frame.top) / frame.height}%`,
      "--screen-width": `${(100 * p.screen[0]) / frame.width}%`,
      "--screen-height": `${(100 * p.screen[1]) / frame.height}%`,
    }))
      body.style.setProperty(name, value);
    $("device-frame").src = frame.asset;
    $("device-frame").alt = `${device} 的官方页面设置外壳图`;
    $("mini-device-frame").src = frame.asset;
    $("resolution-label").textContent =
      `App 内容区 ${p.screen[0]}:${p.screen[1]} · 官方机型外壳`;
    $("resolution-label").title =
      "尺寸、位置与 FIT_CENTER 缩放取自 APK；外壳来自官方设备目录的 BACKGROUND_IN_PAGE_SETTING 图片，网页仅加载本地副本。";
    $("sidebar-guidance").textContent = p.bluetooth
      ? "先读取，再编辑、同步。草稿按机型和模式保存在当前浏览器。"
      : "仅离线展示与编辑，不连接或写入设备。草稿按机型和模式独立保存。";
  }
  function renderConnection() {
    const connected = bluetooth.connected;
    $("connection-status").replaceChildren(
      element("i"),
      document.createTextNode(
        connected
          ? `已连接${bluetooth.version ? ` · ${bluetooth.version}` : ""}`
          : profile().bluetooth
            ? "未连接"
            : "仅离线预览",
      ),
    );
    $("connection-status").classList.toggle("connected", connected);
    $("connect").title = !profile().bluetooth
      ? "此机型仅支持离线预览"
      : connected
        ? "断开蓝牙连接"
        : "连接蓝牙码表";
    $("connect").setAttribute("aria-label", $("connect").title);
  }
  function render() {
    if (!pages.some((p) => p.key === selectedKey)) selectedKey = pages[0].key;
    activeField = Math.min(
      activeField,
      Math.max(0, current().fields.length - 1),
    );
    renderDevice();
    renderPages();
    renderPreview();
    renderInspector();
    renderConnection();
    $("undo").disabled = busy || !past.length;
    $("redo").disabled = busy || !future.length;
    $("ride-mode").disabled = busy;
    $("page-name").disabled = busy;
    for (const id of [
      "read-device",
      "connect",
      "import-plan",
      "add-page",
      "open-sync",
    ])
      $(id).disabled = busy;
    for (const id of ["read-device", "connect", "open-sync"])
      $(id).disabled = busy || !profile().bluetooth;
    $("draft-badge").classList.toggle("live", Boolean(baseline));
    $("draft-badge").textContent = busy
      ? "正在与设备通信"
      : baseline
        ? changed()
          ? "设备草稿 · 待同步"
          : lastSynced || "已读取设备"
        : profile().bluetooth
          ? "本地草稿 · 未读取"
          : "离线预览 · 本地草稿";
  }
  function applyLayout(layout) {
    commit(() => {
      const p = current(),
        defaults = [
          257, 769, 1025, 1281, 513, 1795, 1537, 1538, 1541, 258, 1033, 2305,
        ];
      while (p.fields.length < layout.count)
        p.fields.push(defaults[p.fields.length % defaults.length]);
      p.fields = p.fields.slice(0, layout.count);
      p.pageMode = layout.pageMode;
      p.lineWidth = layout.lineWidth;
      syncGraphic(p);
    });
  }
  function openField(index) {
    if (busy) return;
    activeField = index;
    renderPreview();
    renderInspector();
    $("field-dialog-label").textContent =
      `${M.title(current())} · 第 ${index + 1} 项 · 当前为${dataField(current().fields[index]).label}`;
    $("field-search").value = "";
    fieldCategory = "常用";
    renderLibrary();
    $("field-dialog").showModal();
    $("field-search").focus();
  }
  function renderLibrary() {
    const categories = [
      "常用",
      "全部",
      "速度",
      "时间",
      "距离",
      "高度",
      "踏频",
      "心率",
      "功率",
      "电子变速",
      "图形",
      "其他",
    ];
    const holder = $("field-categories");
    holder.replaceChildren();
    categories.forEach((name) => {
      const b = element(
        "button",
        `category-button${fieldCategory === name ? " selected" : ""}`,
        name,
      );
      b.setAttribute("aria-pressed", String(fieldCategory === name));
      b.addEventListener("click", () => {
        fieldCategory = name;
        renderLibrary();
      });
      holder.append(b);
    });
    const query = $("field-search").value.trim().toLowerCase(),
      includeAll = $("show-all-fields").checked;
    const common = new Set([
      257, 258, 260, 513, 769, 1025, 1033, 1281, 1537, 1538, 1541, 1795, 1798,
    ]);
    const choices = D.fields.filter(
      (f) =>
        (f.verified ||
          !profile().bluetooth ||
          includeAll ||
          M.isGraphField(f.id) ||
          f.id === current().fields[activeField]) &&
        (query ||
          fieldCategory === "全部" ||
          (fieldCategory === "常用" && common.has(f.id)) ||
          categoryNames[f.id >> 8] === fieldCategory) &&
        (!query ||
          `${f.label} ${f.english} ${f.id} 0x${f.id.toString(16).padStart(4, "0")}`
            .toLowerCase()
            .includes(query)),
    );
    $("library-count").textContent =
      `${choices.length} 个项目${query ? " · 搜索全部分类" : ""}`;
    const list = $("field-library");
    list.replaceChildren();
    for (const field of choices) {
      const selected = field.id === current().fields[activeField],
        button = element(
          "button",
          `library-item${selected ? " selected" : ""}`,
        );
      button.setAttribute("aria-pressed", String(selected));
      const text = element("span");
      text.append(
        element("strong", "", field.label),
        element(
          "small",
          "",
          `${field.english} · 0x${field.id.toString(16).padStart(4, "0").toUpperCase()}${
            M.isGraphField(field.id) ? " · 图形" : ""
          }`,
        ),
      );
      button.append(
        text,
        element(
          "span",
          `field-evidence${profile().bluetooth && field.verified ? "" : " unverified"}`,
          profile().bluetooth && field.verified
            ? "BSC500 固件引用"
            : "App 候选 · 未验证",
        ),
      );
      if (selected) button.append(icon("check"));
      button.addEventListener("click", () => {
        if (
          profile().bluetooth &&
          !field.verified &&
          !selected &&
          !confirm(
            `「${field.label}」尚无 BSC500 固件引用证据，可能不能在码表显示。仍要加入草稿吗？`,
          )
        )
          return;
        commit(() => {
          const p = current();
          p.fields[activeField] = field.id;
          syncGraphic(p);
        });
        $("field-dialog").close();
      });
      list.append(button);
    }
    if (!choices.length)
      list.append(
        element(
          "p",
          "empty-fields",
          "没有找到项目。试试其他名称，或勾选包含待验证项目。",
        ),
      );
  }
  function download(name, value) {
    const url = URL.createObjectURL(
        new Blob([JSON.stringify(value, null, 2) + "\n"], {
          type: "application/json",
        }),
      ),
      link = element("a");
    link.href = url;
    link.download = name;
    document.body.append(link);
    link.click();
    link.remove();
    setTimeout(() => URL.revokeObjectURL(url), 60000);
  }
  function planFor(value, extras = {}) {
    return {
      format: device === "BSC500" ? "bsc500-page-plan" : "igpsport-page-plan",
      version: 1,
      device,
      mode,
      exportedAt: new Date().toISOString(),
      pages: clone(value),
      ...extras,
    };
  }
  function validateLayouts(imported, originals) {
    for (const page of imported) {
      const old = originals.find((p) => p.key === page.key);
      if (!old) continue;
      const layoutChanged =
        page.fields.length !== old.fields.length ||
        page.pageMode !== old.pageMode ||
        page.lineWidth !== old.lineWidth;
      if (layoutChanged && !matchingLayout(page))
        throw new Error(
          `「${M.title(page)}」的版式不在 ${device} APK 目录中，未应用方案。`,
        );
    }
  }
  async function importFile(file) {
    if (!file) return;
    try {
      if (file.size > 512 * 1024)
        throw new Error("方案文件过大（上限 512 KB）。");
      const plan = M.validatePlan(JSON.parse(await file.text()), device);
      if (plan.mode !== mode)
        throw new Error(
          `此方案属于模式 ${plan.mode}，请先在左侧切换到该模式再导入。`,
        );
      if (baseline) {
        const merged = M.mergePlan(plan, pages);
        validateLayouts(merged, pages);
        commit(() => {
          pages = merged;
        });
        notice(
          "已将方案应用到设备草稿。设备未知参数保持原样；检查预览后可手动同步。",
        );
      } else {
        if (!confirm("导入将替换当前本地草稿，不会写入设备。是否继续？"))
          return;
        commit(() => {
          pages = plan.pages;
          selectedKey = pages[0].key;
          activeField = 0;
        });
        notice(
          profile().bluetooth
            ? "已导入本地方案。要同步，请先读取设备，再重新导入此文件；这样不会覆盖设备未知参数。"
            : `已导入 ${device} 离线方案；不会连接或写入设备。`,
        );
      }
    } catch (error) {
      notice(error.message || "无法读取方案文件。", true);
    }
  }
  function setBusy(value) {
    busy = value;
    render();
  }
  async function connect() {
    if (busy || !profile().bluetooth) return;
    if (bluetooth.connected) {
      bluetooth.disconnect();
      baseline = null;
      render();
      return;
    }
    setBusy(true);
    notice("请选择你的 BSC500；连接本身不会修改页面。");
    try {
      await bluetooth.connect();
      notice(
        "蓝牙已连接。请读取码表当前配置，再进行编辑。浏览器直连通道尚需真机验证。",
      );
    } catch (error) {
      notice(
        error.name === "NotFoundError"
          ? "未选择设备。请保持码表开机，并先断开手机 App 的蓝牙连接。"
          : error.message,
        true,
      );
    } finally {
      setBusy(false);
    }
  }
  async function readDevice() {
    if (busy || !profile().bluetooth) return;
    if (
      (changed() || isDifferentBeforeRead()) &&
      !confirm(
        "读取会替换当前草稿。请先导出想保留的方案，读取后可重新导入。继续读取吗？",
      )
    )
      return;
    setBusy(true);
    notice("正在读取页面配置，不会修改码表……");
    try {
      if (!bluetooth.connected) await bluetooth.connect();
      const config = await bluetooth.readPages(mode);
      const next = M.withKeys(config.pages),
        titles = new Map(pages.map((p) => [p.key, p.title]));
      next.forEach((p) => {
        if (titles.get(p.key)) p.title = titles.get(p.key);
      });
      pages = next;
      selectedKey = pages[0].key;
      activeField = 0;
      baseline = {
        pages: clone(pages),
        raw: config.raw,
        deviceId: bluetooth.device.id,
        mode,
      };
      past = [];
      future = [];
      lastSynced = "";
      persist();
      notice(
        `已读取模式 ${mode} 的 ${pages.length} 条页面记录。现在可以编辑；尚未向码表写入任何修改。`,
      );
    } catch (error) {
      baseline = null;
      notice(error.message, true);
    } finally {
      setBusy(false);
    }
  }
  function describeChanges() {
    if (!baseline)
      return ["尚未读取设备。为保护现有配置，请先读取，再编辑或重新导入方案。"];
    const changes = [],
      originals = new Map(baseline.pages.map((p) => [p.key, p]));
    if (
      pages.map((p) => p.key).join() !== baseline.pages.map((p) => p.key).join()
    )
      changes.push("调整页面显示顺序");
    for (const p of pages) {
      const old = originals.get(p.key);
      if (!old) continue;
      const parts = [];
      if (JSON.stringify(p.fields) !== JSON.stringify(old.fields))
        parts.push("数据项目");
      if (p.pageMode !== old.pageMode || p.lineWidth !== old.lineWidth)
        parts.push("布局样式");
      if (p.status !== old.status)
        parts.push(p.status ? "启用页面" : "隐藏页面");
      if (p.main !== old.main) parts.push(p.main ? "设为主页" : "取消主页");
      if (parts.length) changes.push(`${M.title(p)}：${parts.join("、")}`);
    }
    return changes.length
      ? changes
      : ["没有需要写入的设备参数。页面名称与深浅色仅保存在本地。"];
  }
  function refreshSyncDialog() {
    const ready = Boolean(
      baseline &&
      bluetooth.connected &&
      changed() &&
      baseline.mode === mode &&
      baseline.deviceId === bluetooth.device?.id,
    );
    $("sync-subtitle").textContent =
      `骑行模式 ${mode} · ${pages.filter((p) => p.status).length} 个启用页面 · 不修改固件`;
    $("sync-changes").replaceChildren(
      ...describeChanges().map((text) => element("p", "", text)),
    );
    $("sync-connection-hint").hidden = Boolean(baseline && bluetooth.connected);
    if (!baseline || !bluetooth.connected) {
      const hint = $("sync-connection-hint");
      hint.replaceChildren(
        element(
          "p",
          "",
          "首次同步前需读取码表。当前草稿可先导出，读取后重新导入应用。",
        ),
      );
      const button = element(
        "button",
        "button",
        bluetooth.connected ? "读取设备配置" : "连接并读取设备",
      );
      button.addEventListener("click", async () => {
        $("sync-dialog").close();
        await readDevice();
      });
      hint.append(button);
    }
    $("sync-consent-text").textContent =
      `我已保存骑行记录，确认将模式 ${mode} 的本次修改写入码表。`;
    $("sync-consent").disabled = !ready || busy;
    $("sync-confirm").disabled = !ready || !$("sync-consent").checked || busy;
  }
  function openSync() {
    if (busy || !profile().bluetooth) return;
    syncPhase = "idle";
    $("sync-consent").checked = false;
    $("sync-result").hidden = true;
    document
      .querySelectorAll(".sync-steps li")
      .forEach((li) => li.classList.remove("active", "done"));
    document.querySelectorAll("#sync-dialog [data-close]").forEach((button) => {
      button.disabled = false;
    });
    refreshSyncDialog();
    $("sync-dialog").showModal();
  }
  function step(id, done = false) {
    const li = $(`step-${id}`);
    li.classList.toggle("active", !done);
    li.classList.toggle("done", done);
  }
  function retainBackup(backup) {
    // 备份失败就不写入，避免覆盖唯一的设备基线。用户仍可在导出后解决存储限制。
    const previous = JSON.parse(localStorage.getItem(backupKey) || "[]");
    localStorage.setItem(
      backupKey,
      JSON.stringify([backup, ...previous].slice(0, 5)),
    );
  }
  async function sync() {
    if (
      busy ||
      !profile().bluetooth ||
      !baseline ||
      !bluetooth.connected ||
      !changed() ||
      !$("sync-consent").checked ||
      baseline.deviceId !== bluetooth.device.id ||
      baseline.mode !== mode
    )
      return;
    const original = clone(baseline),
      intended = clone(pages);
    syncPhase = "checking";
    setBusy(true);
    $("sync-confirm").disabled = true;
    $("sync-consent").disabled = true;
    document.querySelectorAll("#sync-dialog [data-close]").forEach((button) => {
      button.disabled = true;
    });
    $("sync-result").hidden = true;
    try {
      step("read");
      const fresh = await bluetooth.readPages(mode);
      if (P.differences(original.pages, fresh.pages).length)
        throw new Error(
          "设备配置已被其他入口修改。已停止，未写入；请导出草稿，重新读取设备后再导入。若页面顺序被固件规范化，也需先核对。 ",
        );
      step("read", true);
      step("backup");
      const backup = planFor(M.withKeys(fresh.pages), {
        kind: "device-backup",
        rawConfig: fresh.raw,
        note: "同步前设备原始页面配置；不包含设备标识",
      });
      retainBackup(backup);
      download(`BSC500-mode-${mode}-backup-${Date.now()}.json`, backup);
      step("backup", true);
      step("write");
      syncPhase = "writing";
      await bluetooth.writePages(mode, intended);
      step("write", true);
      step("verify");
      syncPhase = "verifying";
      const readback = await bluetooth.readPages(mode),
        differences = P.differences(intended, readback.pages);
      if (differences.length)
        throw new Error(
          `设备回执成功，但读回核对不一致：${differences.slice(0, 6).join("、")}。没有标记为同步成功。请重新读取并检查设备屏幕，备份已下载。`,
        );
      step("verify", true);
      baseline = {
        pages: clone(intended),
        raw: readback.raw,
        deviceId: bluetooth.device.id,
        mode,
      };
      // 用读回的原始字节刷新基线，保留本地名称；下次修改不依赖旧序列化。
      pages = M.withKeys(readback.pages).map((p, i) => ({
        ...p,
        key: intended[i].key,
        title: intended[i].title,
      }));
      baseline.pages = clone(pages);
      past = [];
      future = [];
      lastSynced = "已同步并读回核对";
      syncPhase = "done";
      persist();
      $("sync-result").textContent =
        "页面配置已写入，读回核对一致。请在码表屏幕上确认显示效果；图形字段与特殊页的外观仍以实机为准。";
      $("sync-result").dataset.error = "false";
      $("sync-result").hidden = false;
      notice("同步完成：设备回执成功，页面配置读回一致。");
    } catch (error) {
      if (syncPhase === "writing" || syncPhase === "verifying") baseline = null;
      const text = `${error.message}${syncPhase === "writing" ? " 写入可能已部分发生，必须重新读取核对，不会自动重试。" : ""}`;
      $("sync-result").textContent = text;
      $("sync-result").dataset.error = "true";
      $("sync-result").hidden = false;
      notice(text, true);
      syncPhase = "failed";
      document
        .querySelectorAll(".sync-steps .active")
        .forEach((li) => li.classList.remove("active"));
    } finally {
      setBusy(false);
      $("sync-consent").checked = false;
      document
        .querySelectorAll("#sync-dialog [data-close]")
        .forEach((button) => {
          button.disabled = false;
        });
      refreshSyncDialog();
    }
  }
  function showDetails() {
    const p = current();
    $("protocol-details").textContent = JSON.stringify(
      {
        device,
        layoutSource: profile().layoutClass,
        apkVersion: G.source.apkVersion,
        frame: {
          ...profile().frame,
          source: { ...G.frameCatalog, ...G.frameSources[device] },
        },
        previewOnly: !profile().bluetooth,
        mode,
        page_index: p.index,
        page_type: p.type,
        status: p.status,
        fields: p.fields.map(
          (id) => `0x${id.toString(16).padStart(4, "0").toUpperCase()}`,
        ),
        page_mode: p.pageMode,
        line_width: p.lineWidth,
        graphic_display: p.graphic,
        forbidden_close: p.forbidden,
        note: profile().bluetooth
          ? "保留原始未知字段；名称与预览配色不写入设备"
          : "APK 内置版式的离线示例；字段与图形未验证真机，不提供蓝牙读写",
      },
      null,
      2,
    );
    const backups = $("backup-list");
    backups.replaceChildren();
    try {
      const items = JSON.parse(localStorage.getItem(backupKey) || "[]");
      for (const backup of Array.isArray(items) ? items.slice(0, 5) : []) {
        M.validatePlan(backup);
        const row = element("p", "backup-row"),
          label = element(
            "span",
            "",
            `BSC500 · 模式 ${backup.mode} · ${new Date(backup.exportedAt).toLocaleString("zh-CN")}`,
          );
        const button = element("button", "text-button accent", "下载备份");
        button.addEventListener("click", () =>
          download(`BSC500-mode-${backup.mode}-backup.json`, backup),
        );
        row.append(label, button);
        backups.append(row);
      }
    } catch {
      backups.append(
        element("p", "", "本机备份无法读取，请使用同步时下载的 JSON 备份。"),
      );
    }
    if (!backups.children.length)
      backups.append(
        element(
          "p",
          "",
          "尚无备份。每次同步前会自动下载设备配置，并在本机保留最近五份。",
        ),
      );
    renderDiagnosticLogs();
    $("details-dialog").showModal();
  }

  $("undo").addEventListener("click", () => history("undo"));
  $("redo").addEventListener("click", () => history("redo"));
  $("page-name").addEventListener("input", () =>
    commit(() => {
      current().title = $("page-name").value.slice(0, 30);
    }),
  );
  $("page-name").addEventListener("blur", () => {
    if (!$("page-name").value.trim()) {
      current().title = "";
      persist();
      render();
    }
  });
  $("page-enabled").addEventListener("click", () => {
    const p = current();
    if (p.forbidden || p.main) return;
    if (p.status && pages.filter((page) => page.status).length === 1) {
      notice("请至少保留一个启用页面。", true);
      return;
    }
    commit(() => {
      p.status = p.status ? 0 : 1;
    });
  });
  $("page-main").addEventListener("click", () =>
    commit(() => {
      const p = current();
      if (!p.status) return;
      pages.forEach((page) => {
        page.main = page === p ? 1 : 0;
      });
    }),
  );
  $("field-count").addEventListener("change", () => {
    const layout = layoutsFor(current()).find(
      (l) => l.count === Number($("field-count").value),
    );
    if (!layout) return;
    if (
      layout.count < current().fields.length &&
      !confirm(
        `改为 ${layout.count} 项会从草稿中移除末尾 ${current().fields.length - layout.count} 个字段，可以撤销。是否继续？`,
      )
    ) {
      renderInspector();
      return;
    }
    applyLayout(layout);
  });
  for (const value of ["light", "dark"])
    $(`theme-${value}`).addEventListener("click", () => {
      theme = value;
      for (const name of ["light", "dark"]) {
        $(`theme-${name}`).classList.toggle("selected", name === value);
        $(`theme-${name}`).setAttribute("aria-pressed", String(name === value));
      }
      renderPreview();
    });
  for (const [id, delta] of [
    ["prev-page", -1],
    ["next-page", 1],
  ])
    $(id).addEventListener("click", () =>
      selectPage(
        pages[(pages.indexOf(current()) + delta + pages.length) % pages.length]
          .key,
      ),
    );
  $("field-search").addEventListener("input", renderLibrary);
  $("show-all-fields").addEventListener("change", renderLibrary);
  $("export-plan").addEventListener("click", () => {
    download(`${device}-mode-${mode}-layout.json`, planFor(pages));
    notice("已导出页面方案。文件包含原厂协议参数；页面名称只用于本地管理。");
  });
  $("import-plan").addEventListener("click", () => $("import-file").click());
  $("import-file").addEventListener("change", async () => {
    await importFile($("import-file").files[0]);
    $("import-file").value = "";
  });
  $("connect").addEventListener("click", connect);
  $("read-device").addEventListener("click", readDevice);
  $("open-sync").addEventListener("click", openSync);
  $("sync-consent").addEventListener("change", refreshSyncDialog);
  $("sync-confirm").addEventListener("click", sync);
  $("add-page").addEventListener("click", () => {
    const holder = $("available-pages");
    holder.replaceChildren();
    const choices = pages.filter((p) => !p.status && !p.forbidden);
    for (const p of choices) {
      const button = element("button", "available-page"),
        text = element("span");
      text.append(
        element("strong", "", M.title(p)),
        element(
          "small",
          "",
          profile().bluetooth
            ? `已有页面槽位 ${p.index} · ${p.fields.length} 项数据`
            : `离线示例页 · ${p.fields.length} 项数据`,
        ),
      );
      button.append(miniature(p), text, icon("plus"));
      button.addEventListener("click", () => {
        commit(() => {
          p.status = 1;
          selectedKey = p.key;
          activeField = 0;
        });
        $("pages-dialog").close();
      });
      holder.append(button);
    }
    if (!choices.length)
      holder.append(
        element(
          "p",
          "empty-fields",
          "没有可启用的空闲页面。受设备限制的原生页面保持原状态。",
        ),
      );
    $("pages-dialog").showModal();
  });
  function switchDevice(next) {
    if (busy || next === device) return;
    G.get(next);
    persist();
    if (bluetooth.connected) bluetooth.disconnect();
    baseline = null;
    device = next;
    mode = 0;
    savedModes = {};
    pages = M.sample(device);
    selectedKey = pages[0].key;
    past = [];
    future = [];
    activeField = 0;
    dragState = null;
    lastSynced = "";
    $("ride-mode").value = "0";
    $("show-all-fields").checked = false;
    notice(
      `已切换到 ${device}。草稿独立保存；${profile().bluetooth ? "同步前请重新读取设备。" : "仅离线展示，不连接或写入设备。"}`,
    );
    restoreDraft();
    try {
      localStorage.setItem("igpsport-preview-device-v1", device);
    } catch {
      storageAvailable = false;
    }
    render();
    persist();
  }
  $("device-model").addEventListener("change", () =>
    switchDevice($("device-model").value),
  );
  $("ride-mode").addEventListener("change", () => {
    persist();
    mode = Number($("ride-mode").value);
    baseline = null;
    past = [];
    future = [];
    activeField = 0;
    lastSynced = "";
    try {
      pages = savedModes[mode]
        ? M.validatePlan(savedModes[mode], device).pages
        : M.sample(device);
    } catch {
      pages = M.sample(device);
    }
    selectedKey = pages[0].key;
    render();
    notice(
      profile().bluetooth
        ? `已切换到模式 ${mode} 的本地草稿。请读取该模式的设备配置，不能把其他模式的基线直接写入。`
        : `已切换到 ${device} 模式 ${mode} 的离线草稿；不会操作设备。`,
    );
    persist();
  });
  $("show-details").addEventListener("click", showDetails);
  $("open-help").addEventListener("click", showDetails);
  document
    .querySelectorAll("[data-close]")
    .forEach((button) =>
      button.addEventListener("click", () => $(button.dataset.close).close()),
    );
  $("sync-dialog").addEventListener("cancel", (event) => {
    if (busy) event.preventDefault();
  });
  document.addEventListener("keydown", (event) => {
    if (event.target.closest("input,textarea,select,[contenteditable]")) return;
    if ((event.metaKey || event.ctrlKey) && event.key.toLowerCase() === "z") {
      event.preventDefault();
      history(event.shiftKey ? "redo" : "undo");
    }
  });
  bluetooth.addEventListener("connection", (event) => {
    if (!event.detail) baseline = null;
    renderConnection();
    if (!busy) render();
  });
  bluetooth.addEventListener("log", (event) =>
    recordDiagnosticLog(event.detail),
  );
  $("export-ble-log").addEventListener("click", () => {
    download(`BSC500-bluetooth-diagnostics-${Date.now()}.json`, {
      format: "bsc500-bluetooth-diagnostics",
      version: 2,
      transport: window.BSCBluetooth.diagnosticsVersion,
      exportedAt: new Date().toISOString(),
      note: "仅在本地导出，可能包含设备页面配置收发报文，不含浏览器蓝牙设备标识。",
      entries: [...logs],
    });
  });
  $("clear-ble-log").addEventListener("click", () => {
    if (!confirm("清空当前浏览器保存的蓝牙诊断日志？不会修改码表配置。"))
      return;
    logs = [];
    try {
      localStorage.removeItem(logStorageKey);
      diagnosticStorageFailed = false;
    } catch {
      diagnosticStorageFailed = true;
    }
    renderDiagnosticLogs();
  });
  recordDiagnosticLog(
    `诊断模块已加载 · ${window.BSCBluetooth.diagnosticsVersion} · 历史日志仅供参考，未自动连接或发送任何配置`,
  );
  for (const p of G.all) $("device-model").add(new Option(p.id, p.id));
  try {
    const savedDevice = localStorage.getItem("igpsport-preview-device-v1");
    if (G.all.some((p) => p.id === savedDevice)) device = savedDevice;
  } catch {
    storageAvailable = false;
  }
  pages = M.sample(device);
  selectedKey = pages[0].key;
  restoreDraft();
  render();
})();
