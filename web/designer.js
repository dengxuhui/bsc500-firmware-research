"use strict";

(() => {
  const limit = 12;
  const descriptors = [
    ["0x0101", "速度", "28.4", "km/h", "Speed", "Current Speed"],
    ["0x0102", "平均速度", "25.8", "km/h", "Avg Speed", "Average Speed"],
    ["0x0104", "最大速度", "45.2", "km/h", "Max Speed"],
    ["0x0105", "本圈平均速度", "26.1", "km/h", "Lap Speed"],
    ["0x0201", "踏频", "86", "rpm", "Cadence"],
    ["0x0202", "平均踏频", "82", "rpm", "Avg Cad", "Average Cadence"],
    ["0x0301", "心率", "142", "bpm", "Heart Rate", "HR"],
    ["0x0302", "平均心率", "137", "bpm", "Avg HR", "Average Heart Rate"],
    ["0x0308", "心率区间", "3", "区间", "HR Zone", "Heart Rate Zone"],
    ["0x0401", "功率", "193", "W", "Power", "Current Power"],
    ["0x0402", "平均功率", "177", "W", "Avg Power", "Average Power"],
    ["0x0404", "本圈平均功率", "188", "W", "Lap Power", "Lap Pwr"],
    ["0x0409", "3s功率", "191", "W", "3s Power", "3s Pwr", "3 Sec Power", "3 秒功率", "Power 3s"],
    ["0x040F", "TSS", "42", "", "Training Stress Score"],
    ["0x0501", "骑行距离", "18.6", "km", "Distance", "Total Distance"],
    ["0x0504", "本圈距离", "4.6", "km", "Lap Distance", "Lap Dist."],
    ["0x0601", "高度", "396", "m", "Elevation", "Altitude", "海拔"],
    ["0x0602", "坡度", "6.3", "%", "Grade", "Gradient"],
    ["0x0603", "垂直速度", "452", "m/h", "VAM", "Vertical Speed"],
    ["0x0605", "总升高度", "312", "m", "Total Ascent", "Elevation Gain", "累计爬升"],
    ["0x0703", "记录时间", "43:20", "", "Record Time", "Timer"],
    ["0x0706", "本圈时间", "08:18", "", "Lap Time"],
    ["0x0801", "当前时间", "09:32", "", "Time of Day"],
  ];
  const catalog = new Map(descriptors.map(([id, label, sample, unit, ...aliases]) => [id, { label, sample, unit, aliases }]));
  const customBar = { label: "强度条", sample: "68", unit: "%", aliases: ["Intensity Bar"] };
  const presets = {
    navigation: { name: "导航仪表盘", layout: "navigation", palette: "indigo", fields: ["0x0101", "0x0301", "0x0401", "0x0501"] },
    road: { name: "公路巡航", layout: "hero", palette: "forest", fields: ["0x0101", "0x0501", "0x0301", "0x0201", "0x0703"] },
    training: { name: "功率训练", layout: "hero", palette: "night", fields: ["0x0409", "0x0404", "0x0706", "0x0301", "0x0201", "intensity"] },
    climb: { name: "爬坡观察", layout: "grid", palette: "indigo", fields: ["0x0602", "0x0601", "0x0605", "0x0603", "0x0409", "0x0301"] },
  };
  const layouts = ["navigation", "hero", "grid", "list"];
  const palettes = ["forest", "night", "indigo"];
  const nameInput = document.querySelector("#design-name");
  const layoutInput = document.querySelector("#design-layout");
  const paletteInput = document.querySelector("#design-palette");
  const picker = document.querySelector("#design-picker");
  const fieldsList = document.querySelector("#design-fields");
  const cards = document.querySelector("#design-cards");
  const replaceDialog = document.querySelector("#design-replace");
  const replacement = document.querySelector("#design-replacement");
  const canvas = document.querySelector("#design-canvas");
  const navigation = document.querySelector("#design-navigation");
  const compatible = document.querySelector("#design-compat");
  const add = document.querySelector("#design-add");
  const apply = document.querySelector("#design-apply");
  const paste = document.querySelector("#design-paste");
  const normalize = (label) => label.normalize("NFKC").toLowerCase().replace(/[\s()._-]/g, "");
  const aliases = new Map();
  let activeFields = [];
  let replacementIndex = 0;

  for (const [id, item] of catalog) {
    picker.add(new Option(item.label, id));
    for (const alias of [item.label, ...item.aliases]) aliases.set(normalize(alias), id);
  }
  picker.add(new Option("强度条（网页示例）", "intensity"));
  replacement.replaceChildren(...Array.from(picker.options, (option) => option.cloneNode(true)));
  for (const alias of [customBar.label, ...customBar.aliases]) aliases.set(normalize(alias), "intensity");

  function describe(field) {
    if (field.id) return catalog.get(field.id);
    if (field.kind === "intensity") return customBar;
    return { label: field.label, sample: "—", unit: "" };
  }

  function fromKey(key) {
    return catalog.has(key) ? { id: key } : { kind: "intensity" };
  }

  function createControl(title, action, index, disabled) {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "secondary";
    button.textContent = title;
    button.dataset.action = action;
    button.dataset.index = index;
    button.setAttribute("aria-label", `${title}第 ${index + 1} 项`);
    button.disabled = disabled;
    return button;
  }

  function renderCanvas() {
    canvas.dataset.layout = layoutInput.value;
    canvas.dataset.palette = paletteInput.value;
    canvas.dataset.compact = String(activeFields.length > (layoutInput.value === "navigation" ? 6 : 8));
    canvas.style.setProperty("--metric-rows", String(Math.max(1, Math.ceil(activeFields.length / 2))));
    navigation.hidden = layoutInput.value !== "navigation";
    cards.hidden = !navigation.hidden && activeFields.length === 0;
    document.querySelector("#design-preview-title").textContent = nameInput.value.trim() || "骑行数据页";
    cards.replaceChildren();
    activeFields.forEach((field, index) => {
      const item = describe(field);
      const card = document.createElement("button");
      card.type = "button";
      card.className = "design-metric";
      card.dataset.index = index;
      card.setAttribute("aria-label", `更换第 ${index + 1} 格 · ${item.label}`);
      const title = document.createElement("span");
      title.textContent = item.label;
      const metric = document.createElement("span");
      metric.className = "design-reading";
      const value = document.createElement("strong");
      value.textContent = item.sample;
      const unit = document.createElement("small");
      unit.textContent = item.unit;
      metric.append(value, unit);
      card.append(title, metric);
      if (field.kind === "intensity") {
        const bar = document.createElement("progress");
        bar.max = 100;
        bar.value = Number(item.sample);
        bar.setAttribute("aria-label", "示例强度");
        card.append(bar);
      }
      cards.append(card);
    });
  }

  function renderFields() {
    fieldsList.replaceChildren();
    activeFields.forEach((field, index) => {
      const desc = describe(field);
      const row = document.createElement("li");
      const label = document.createElement("span");
      label.textContent = desc.label;
      const classification = document.createElement("small");
      classification.textContent = field.id || (field.kind === "intensity" ? "仅网页" : "未匹配");
      const controls = document.createElement("div");
      controls.className = "reorder";
      controls.append(
        createControl("上移", "up", index, index === 0),
        createControl("下移", "down", index, index === activeFields.length - 1),
        createControl("移除", "delete", index, false),
      );
      row.append(label, classification, controls);
      fieldsList.append(row);
    });
    const count = activeFields.filter((field) => field.id).length;
    compatible.textContent = `匹配 ${count} / ${activeFields.length} 项。字段已在固件中找到引用，设备实际选项以 App 为准。`;
    apply.disabled = !count;
    add.disabled = activeFields.length >= limit;
    renderCanvas();
  }

  function loadDesign(design) {
    nameInput.value = design.name;
    layoutInput.value = design.layout;
    paletteInput.value = design.palette;
    activeFields = design.fields;
    renderFields();
  }

  function loadPreset(key) {
    const preset = presets[key];
    loadDesign({ ...preset, fields: preset.fields.map(fromKey) });
    for (const button of document.querySelectorAll("[data-preset]")) {
      button.setAttribute("aria-pressed", String(button.dataset.preset === key));
    }
  }

  function clearPresetHighlight() {
    for (const button of document.querySelectorAll("[data-preset]")) button.setAttribute("aria-pressed", "false");
  }

  function refreshFields() {
    clearPresetHighlight();
    renderFields();
  }

  cards.addEventListener("click", (event) => {
    const cell = event.target.closest(".design-metric");
    if (!cell) return;
    replacementIndex = Number(cell.dataset.index);
    const field = activeFields[replacementIndex];
    document.querySelector("#design-replace-title").textContent =
      `更换第 ${replacementIndex + 1} 格 · ${describe(field).label}`;
    replacement.value = field.id ?? (field.kind === "intensity" ? "intensity" : picker.value);
    replaceDialog.returnValue = "";
    replaceDialog.showModal();
    replacement.focus();
  });

  replaceDialog.addEventListener("close", () => {
    if (replaceDialog.returnValue === "replace") {
      activeFields[replacementIndex] = fromKey(replacement.value);
      refreshFields();
    }
    cards.children[replacementIndex]?.focus({ preventScroll: true });
  });

  for (const button of document.querySelectorAll("[data-preset]")) {
    button.addEventListener("click", () => {
      loadPreset(button.dataset.preset);
      showStatus("");
    });
  }
  for (const control of [nameInput, layoutInput, paletteInput]) {
    control.addEventListener("input", () => {
      clearPresetHighlight();
      renderCanvas();
    });
  }

  fieldsList.addEventListener("click", (event) => {
    const button = event.target.closest("button[data-action]");
    if (!button || button.disabled) return;
    const index = Number(button.dataset.index);
    const action = button.dataset.action;
    const [field] = activeFields.splice(index, 1);
    const next = action === "up" ? index - 1 : index + 1;
    if (action !== "delete") activeFields.splice(next, 0, field);
    refreshFields();
    const focusIndex = action === "delete" ? Math.min(index, activeFields.length - 1) : next;
    const row = fieldsList.children[focusIndex];
    (row?.querySelector(`[data-action="${action}"]:not(:disabled)`) ||
      row?.querySelector("button:not(:disabled)") || picker).focus();
  });

  add.addEventListener("click", () => {
    if (activeFields.length >= limit) return;
    activeFields.push(fromKey(picker.value));
    refreshFields();
  });

  document.querySelector("#design-match").addEventListener("click", () => {
    const lines = paste.value.split(/\r\n?|\n|[;；]/).map((line) => line.trim()).filter(Boolean);
    if (!lines.length || lines.length > limit || lines.some((line) => line.length > 45)) {
      showStatus("请填写 1–12 个字段，每项最多 45 个字符。", true);
      return;
    }
    activeFields = lines.map((label) => {
      const key = aliases.get(normalize(label));
      return key ? fromKey(key) : { kind: "unknown", label };
    });
    refreshFields();
    showStatus("参考字段已匹配。请查看字段列表和移植提示。");
  });

  apply.addEventListener("click", () => {
    const matches = activeFields.filter((field) => field.id);
    if (!matches.length) return;
    settings.elements.page.value = nameInput.value.trim() || "骑行数据页";
    fields.fill("");
    matches.forEach((field, index) => { fields[index] = describe(field).label; });
    fieldCount.value = String(matches.length);
    createFieldEditors();
    document.querySelector('.tabs a[href="#settings"]').click();
    showStatus(`已填入 ${matches.length} 个字段。请根据 App 的可选项完成同步。`);
  });

  document.querySelector("#design-save").addEventListener("click", () => {
    const name = nameInput.value.trim();
    if (!name || !activeFields.length) {
      showStatus("请填写页面名称并至少添加一个字段。", true);
      return;
    }
    const payload = {
      format: "bsc500-page-design",
      version: 1,
      name,
      layout: layoutInput.value,
      palette: paletteInput.value,
      fields: activeFields,
    };
    download("BSC500-page-design.json", "application/json", JSON.stringify(payload, null, 2) + "\n");
    showStatus("已下载数据页设计，可在设计器中再次打开。");
  });

  bindImport("design-import", 65536, (text) => {
    let design;
    try {
      design = JSON.parse(text.replace(/^\uFEFF/, ""));
    } catch {
      throw new Error("这份文件不是有效的 JSON。");
    }
    const validName = (label) => typeof label === "string" && label.length <= 45 && Boolean(label.trim());
    if (design?.format !== "bsc500-page-design" || design.version !== 1 || !validName(design.name) ||
        !layouts.includes(design.layout) || !palettes.includes(design.palette) ||
        !Array.isArray(design.fields) || !design.fields.length || design.fields.length > limit) {
      throw new Error("请选择有效的 BSC500 数据页设计。");
    }
    const imported = design.fields.map((field) => {
      if (catalog.has(field?.id)) return { id: field.id };
      if (!field?.id && field?.kind === "intensity") return { kind: "intensity" };
      if (!field?.id && field?.kind === "unknown" && validName(field.label)) return { kind: "unknown", label: field.label };
      throw new Error("设计中有无法识别的字段。");
    });
    loadDesign({ ...design, fields: imported });
    clearPresetHighlight();
    return "已打开数据页设计。";
  });

  loadPreset("navigation");
})();
