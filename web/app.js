"use strict";

const statusBox = document.querySelector("#status");
const links = Array.from(document.querySelectorAll(".tabs a"));

function showStatus(message, isError = false) {
  statusBox.textContent = message;
  statusBox.dataset.error = String(isError);
  statusBox.hidden = !message;
}

function selectPanel() {
  const selected = links.find((tab) => tab.hash === location.hash) || links[0];
  for (const tab of links) {
    if (tab === selected) tab.setAttribute("aria-current", "page");
    else tab.removeAttribute("aria-current");
    document.getElementById(tab.hash.slice(1)).hidden = tab !== selected;
  }
  showStatus("");
}

for (const link of [...links, document.querySelector(".brand"), document.querySelector("#designer a[href='#reference']")]) {
  link.addEventListener("click", (event) => {
    if (event.metaKey || event.ctrlKey || event.altKey || event.shiftKey) return;
    event.preventDefault();
    if (location.hash !== link.hash) history.pushState(null, "", link.hash);
    selectPanel();
  });
}
window.addEventListener("popstate", selectPanel);
window.addEventListener("hashchange", selectPanel);
selectPanel();

function download(filename, type, content) {
  const url = URL.createObjectURL(new Blob([content], { type }));
  const anchor = document.createElement("a");
  anchor.href = url;
  anchor.download = filename;
  document.body.append(anchor);
  anchor.click();
  anchor.remove();
  setTimeout(() => URL.revokeObjectURL(url), 60000);
}

function bindImport(id, maxBytes, read) {
  const input = document.getElementById(id);
  input.addEventListener("change", async () => {
    const [file] = input.files;
    if (!file) return;
    try {
      if (file.size > maxBytes) throw new Error("文件过大，请选择较小的文件。");
      const message = read(await file.text(), file.name);
      showStatus(message);
    } catch (error) {
      showStatus(error.message, true);
    } finally {
      input.value = "";
    }
  });
}

const settings = document.querySelector("#settings-form");
const fieldCount = document.querySelector("#field-count");
const fieldEditors = document.querySelector("#field-editors");
const fields = ["速度", "骑行距离", "心率", "踏频", "", "", "", "", "", "", "", ""];
const preview = document.querySelector("#screen");
const previewFields = document.querySelector("#preview-fields");
const previewSamples = {
  速度: "28.4", 平均速度: "25.8", 最大速度: "45.2", 骑行距离: "18.6", 骑行时间: "43:20",
  心率: "142", 平均心率: "137", 踏频: "86", 功率: "193", "3 秒功率": "191", "3s功率": "191",
  海拔: "96", 高度: "96", 坡度: "3%", 记录时间: "43:20",
};
const names = ["appearance", "units", "brightness", "screenOff", "pause", "lap", "north", "zoom", "roads", "reroute"];

for (let index = 0; index < fields.length; index += 1) {
  fieldCount.add(new Option(String(index + 1), String(index + 1)));
}
fieldCount.value = "4";

function updatePreview() {
  document.querySelector("#preview-mode").textContent = settings.elements.mode.value.trim() || "骑行模式";
  document.querySelector("#preview-title").textContent = settings.elements.page.value.trim() || "数据页";
  preview.dataset.appearance = settings.elements.appearance.value;
  previewFields.replaceChildren();
  fields.slice(0, Number(fieldCount.value)).forEach((field) => {
    const tile = document.createElement("div");
    tile.className = "metric";
    const name = document.createElement("span");
    const sample = document.createElement("b");
    name.textContent = field.trim() || "未设置";
    sample.textContent = Object.hasOwn(previewSamples, field.trim()) ? previewSamples[field.trim()] : "—";
    tile.append(name, sample);
    previewFields.append(tile);
  });
}

function createFieldEditors() {
  fieldEditors.replaceChildren();
  for (let index = 0; index < Number(fieldCount.value); index += 1) {
    const label = document.createElement("label");
    label.textContent = `字段 ${index + 1}`;
    const input = document.createElement("input");
    input.setAttribute("list", "field-suggestions");
    input.maxLength = 45;
    input.value = fields[index];
    input.required = true;
    input.addEventListener("input", () => { fields[index] = input.value; });
    label.append(input);
    fieldEditors.append(label);
  }
  updatePreview();
}

settings.addEventListener("input", updatePreview);
fieldCount.addEventListener("change", createFieldEditors);
createFieldEditors();

settings.addEventListener("submit", (event) => {
  event.preventDefault();
  const plan = {
    format: "bsc500-settings-plan",
    version: 1,
    mode: settings.elements.mode.value.trim(),
    page: settings.elements.page.value.trim(),
    fields: fields.slice(0, Number(fieldCount.value)).map((field) => field.trim()),
    settings: Object.fromEntries(names.map((name) => [name, settings.elements.namedItem(name).value])),
  };
  if (!plan.mode || !plan.page || plan.fields.some((field) => !field)) {
    showStatus("请填写模式、页面名称与每个字段。", true);
    return;
  }
  download("BSC500-settings-plan.json", "application/json", JSON.stringify(plan, null, 2) + "\n");
  showStatus("已生成设置方案。请按方案在 App 和码表中设置。");
});

bindImport("settings-import", 65536, (text) => {
  let plan;
  try {
    plan = JSON.parse(text.replace(/^\uFEFF/, ""));
  } catch {
    throw new Error("这份文件不是有效的 JSON 设置方案。");
  }
  const isValidText = (value) => typeof value === "string" && value.trim().length > 0 && value.length <= 45;
  if (plan?.format !== "bsc500-settings-plan" || plan.version !== 1 || !isValidText(plan.mode) ||
      !isValidText(plan.page) || !Array.isArray(plan.fields) || plan.fields.length < 1 ||
      plan.fields.length > fields.length || !plan.fields.every(isValidText) ||
      !plan.settings || typeof plan.settings !== "object" || Array.isArray(plan.settings)) {
    throw new Error("这份文件与 BSC500 设置方案的格式不符。");
  }
  const selections = names.map((name) => {
    const select = settings.elements.namedItem(name);
    const value = plan.settings[name] ?? "";
    if (!Array.from(select.options).some((option) => option.value === value)) {
      throw new Error(`方案的「${select.labels[0].childNodes[0].textContent.trim()}」设置无法识别。`);
    }
    return [select, value];
  });
  settings.elements.mode.value = plan.mode;
  settings.elements.page.value = plan.page;
  fieldCount.value = String(plan.fields.length);
  fields.fill("");
  plan.fields.forEach((field, index) => { fields[index] = field; });
  for (const [select, value] of selections) select.value = value;
  createFieldEditors();
  return "已打开设置方案。";
});

const route = document.querySelector("#route-form");
bindImport("route-import", 20 * 1024 * 1024, (text, filename) => {
  const loaded = BscTools.readGpx(text);
  route.elements.namedItem("title").value = (loaded.title || filename.replace(/\.gpx$/i, "")).slice(0, 80);
  route.elements.points.value = loaded.rows.map((point) => point.join(",")).join("\n");
  return `已载入 ${loaded.rows.length} 个坐标点。`;
});

route.addEventListener("submit", (event) => {
  event.preventDefault();
  try {
    const name = route.elements.namedItem("title").value.trim();
    const safeName = name.replace(/[<>:"/\\|?*\x00-\x1f]/g, "_").replace(/^[. ]+|[. ]+$/g, "");
    const result = BscTools.makeGpx(name, route.elements.points.value);
    download(`${safeName || "BSC500-route"}.gpx`, "application/gpx+xml", result.content);
    showStatus(`已生成包含 ${result.count} 个坐标点的 GPX。可在 iGPSPORT App 导入。`);
  } catch (error) {
    showStatus(error.message, true);
  }
});

const backlight = document.querySelector("#backlight-form");
const filenames = { slider: "level.txt", ambient: "light.txt" };
for (const [id, filename] of Object.entries(filenames)) {
  bindImport(`${id}-import`, 65536, (text) => {
    backlight.querySelector(`textarea[name="${id}"]`).value = BscTools.backlightCsv(text, id);
    return `已载入 ${filename}。`;
  });
}
backlight.addEventListener("submit", (event) => {
  event.preventDefault();
  const type = event.submitter?.value;
  if (!Object.hasOwn(filenames, type)) return;
  try {
    const csv = BscTools.backlightCsv(backlight.querySelector(`textarea[name="${type}"]`).value, type);
    download(filenames[type], "text/plain", csv);
    showStatus(`已生成 ${filenames[type]} 离线验证样本。`);
  } catch (error) {
    showStatus(error.message, true);
  }
});

const map = document.querySelector("#map-form");
const color = map.elements.color;
color.addEventListener("input", () => {
  document.querySelector("#map-swatch").style.background = color.value;
  document.querySelector("#map-color").textContent = color.value.toUpperCase();
});
map.addEventListener("submit", async (event) => {
  event.preventDefault();
  const [file] = map.elements.original.files;
  if (!file) return;
  if (file.size !== 4482760 && file.size !== 4514804) {
    showStatus("文件大小与 BSC500 V1.04.003 的原厂文件不符。", true);
    return;
  }
  const variant = map.elements.variant.value;
  const hex = color.value;
  const button = map.querySelector("button[type=submit]");
  button.disabled = true;
  showStatus("正在校验原厂文件并生成离线镜像…");
  try {
    const result = await BscTools.patchMap(new Uint8Array(await file.arrayBuffer()), variant, hex);
    download(`BSC500_APP-${variant}-${hex.slice(1).toUpperCase()}.local.bin`, "application/octet-stream", result.image);
    showStatus(`原厂校验通过。已生成离线镜像，共修改 ${result.changed} 个字节。`);
  } catch (error) {
    showStatus(error.message, true);
  } finally {
    button.disabled = false;
  }
});
