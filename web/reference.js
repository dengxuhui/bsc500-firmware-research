"use strict";

// BSC500 built-in presets from iGPSPORT Android China 8.11.00.
const referenceCatalog = [
  ["普通数据页", [
    ["3 项 · 样式 1", "ic_bg1_3a_normal.png"],
    ["3 项 · 样式 2", "ic_bg1_3b_normal.png"],
    ["4 项 · 样式 1", "ic_bg1_4a_normal.png"],
    ["4 项 · 样式 2", "ic_bg1_4b_normal.png"],
    ["4 项 · 样式 3", "ic_bg1_4c_normal.png"],
    ["5 项 · 样式 1", "ic_bg1_5a_normal.png"],
    ["5 项 · 样式 2", "ic_bg2_5b_normal.png"],
    ["6 项 · 样式 1", "ic_bg1_6a_normal.png"],
    ["6 项 · 样式 2", "ic_bg1_6b_normal.png"],
    ["6 项 · 样式 3", "ic_bg1_6c_normal.png"],
    ["7 项 · 样式 1", "ic_bg1_7a_normal.png"],
    ["7 项 · 样式 2", "ic_bg2_7b_normal.png"],
    ["8 项 · 样式 1", "ic_bg1_8a_normal.png"],
    ["8 项 · 样式 2", "ic_bg1_8b_normal.png"],
    ["9 项 · 样式 1", "ic_bg2_9a_normal.png"],
    ["9 项 · 样式 2", "ic_bg1_9b_normal.png"],
    ["10 项 · 样式 1", "ic_bg1_10a_normal.png"],
    ["10 项 · 样式 2", "ic_bg1_10b_normal.png"],
  ]],
  ["专项页", [
    ["海拔 · 样式 1", "ic_bg1_altitude_a_normal.png"],
    ["海拔 · 样式 2", "ic_bg1_altitude_b_normal.png"],
    ["海拔 · 样式 3", "ic_bg1_altitude_c_normal.png"],
    ["海拔 · 样式 4", "ic_bg1_altitude_d_normal.png"],
    ["海拔 · 样式 5", "ic_bg1_altitude_e_normal.png"],
    ["海拔 · 样式 6", "ic_bg1_altitude_f_normal.png"],
    ["地图 · 样式 1", "ic_bg1_altitude_a_normal.png"],
    ["地图 · 样式 2", "ic_bg1_altitude_b_normal.png"],
    ["地图 · 样式 3", "ic_bg1_altitude_c_normal.png"],
    ["地图 · 样式 4", "ic_bg1_altitude_d_normal.png"],
    ["地图 · 样式 5", "ic_bg1_altitude_e_normal.png"],
    ["地图 · 样式 6", "ic_bg1_altitude_f_normal.png"],
    ["地图 · 样式 7", "ic_bg1_fec_d_normal.png"],
    ["地图 · 样式 8", "ic_bg1_fec_e_normal.png"],
    ["爬坡 · 样式 1", "ic_bg1_lap_a_normal.png"],
    ["爬坡 · 样式 2", "ic_bg1_climb_a_normal.png"],
    ["爬坡 · 样式 3", "ic_bg1_climb_b_normal.png"],
    ["爬坡 · 样式 4", "ic_bg1_altitude_e_normal.png"],
    ["爬坡 · 样式 5", "ic_bg1_altitude_f_normal.png"],
    ["爬坡地图 · 样式 1", "ic_bg1_lap_a_normal.png"],
    ["爬坡地图 · 样式 2", "ic_bg1_segment_a_normal.png"],
    ["爬坡地图 · 样式 3", "ic_bg1_segment_c_normal.png"],
    ["爬坡地图 · 样式 4", "ic_bg1_segment_d_normal.png"],
    ["爬坡地图 · 样式 5", "ic_bg1_segment_e_normal.png"],
    ["指南针 · 样式 1", "ic_bg1_altitude_a_normal.png"],
    ["指南针 · 样式 2", "ic_bg1_altitude_b_normal.png"],
    ["指南针 · 样式 3", "ic_bg1_altitude_c_normal.png"],
    ["指南针 · 样式 4", "ic_bg1_altitude_d_normal.png"],
    ["指南针 · 样式 5", "ic_bg1_altitude_e_normal.png"],
    ["指南针 · 样式 6", "ic_bg1_altitude_f_normal.png"],
    ["计圈 · 样式 1", "ic_bg1_lap_a_normal.png"],
    ["计圈 · 样式 2", "ic_bg1_lap_b_normal.png"],
    ["计圈 · 样式 3", "ic_bg1_lap_c_normal.png"],
    ["计圈 · 样式 4", "ic_bg1_lap_d_normal.png"],
    ["计圈 · 样式 5", "ic_bg1_lap_e_normal.png"],
    ["计圈 · 样式 6", "ic_bg1_lap_f_normal.png"],
    ["骑行台 · 样式 1", "ic_bg1_fec_a_normal.png"],
    ["骑行台 · 样式 2", "ic_bg1_fec_b_normal.png"],
    ["骑行台 · 样式 3", "ic_bg1_fec_c_normal.png"],
    ["骑行台 · 样式 4", "ic_bg1_fec_d_normal.png"],
    ["骑行台 · 样式 5", "ic_bg1_fec_e_normal.png"],
    ["赛段 · 样式 1", "ic_bg1_segment_a_normal.png"],
    ["赛段 · 样式 2", "ic_bg1_segment_b_normal.png"],
    ["赛段 · 样式 3", "ic_bg1_segment_c_normal.png"],
    ["赛段 · 样式 4", "ic_bg1_segment_d_normal.png"],
    ["赛段 · 样式 5", "ic_bg1_segment_e_normal.png"],
    ["训练课程 · 样式 1", "ic_bg1_training_courses_a_normal.png"],
    ["训练课程 · 样式 2", "ic_bg1_training_courses_b_normal.png"],
    ["训练课程 · 样式 3", "ic_bg1_training_courses_c_normal.png"],
    ["训练效果 · 样式 1", "ic_bg1_climb_a_normal.png"],
    ["训练效果 · 样式 2", "ic_bg1_climb_b_normal.png"],
    ["组队骑行 · 样式 1", "ic_bg1_lap_a_normal.png"],
    ["音乐控制 · 样式 1", "ic_bg1_lap_a_normal.png"],
    ["运动相机 · 样式 1", "ic_bg1_lap_a_normal.png"],
  ]],
];

const referenceFilter = document.querySelector("#reference-series");
const referenceGallery = document.querySelector("#reference-gallery");
const referenceCount = document.querySelector("#reference-count");
const referenceGroups = referenceCatalog.map(([series, images], index) => {
  referenceFilter.add(new Option(series, series));

  const group = document.createElement("section");
  group.className = "reference-group";
  group.setAttribute("aria-labelledby", `reference-group-${index}`);
  const title = document.createElement("h3");
  title.id = `reference-group-${index}`;
  title.textContent = series;
  const grid = document.createElement("div");
  grid.className = "reference-grid";

  for (const [label, filename] of images) {
    const link = document.createElement("a");
    link.className = "reference-item";
    link.href = `assets/igpsport-cn-8.11.00/${filename}`;
    link.title = `查看原图 · ${label}`;

    const preview = document.createElement("span");
    const img = document.createElement("img");
    img.src = link.href;
    img.alt = "";
    img.loading = "lazy";
    img.decoding = "async";
    preview.append(img);

    const caption = document.createElement("span");
    caption.textContent = label;
    link.append(preview, caption);
    grid.append(link);
  }

  group.append(title, grid);
  referenceGallery.append(group);
  return group;
});

function filterReferenceImages() {
  let count = 0;
  referenceGroups.forEach((group, index) => {
    group.hidden = Boolean(referenceFilter.value && referenceFilter.value !== referenceCatalog[index][0]);
    if (!group.hidden) count += referenceCatalog[index][1].length;
  });
  referenceCount.textContent = `${count} 个带图样式`;
}

referenceFilter.addEventListener("change", filterReferenceImages);
filterReferenceImages();
