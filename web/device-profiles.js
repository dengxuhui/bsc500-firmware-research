"use strict";

(function (root) {
  const D =
    typeof module !== "undefined" && module.exports
      ? require("./editor-data.js")
      : root.BSCEditorData;
  const source = {
    apkVersion: "8.11.00",
    apkSha256:
      "4cc5cd29a7e3a48f2e17db885beaa96fad5af7b64558cceb08e8ccf06d396c3d",
    packageName: "com.igpsport.igpsportandroid",
    dex: "classes12.dex",
    classPackage:
      "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style",
    dimensions:
      "Bg3PageConfigUtil.getContainerWidthWidthAndHeight（dp，非真机分辨率）",
  };
  const compact = (rows) =>
    rows.map(([count, pageMode, lineWidth], i, all) => ({
      section: "普通数据页",
      name: `${count} 项`,
      count,
      pageMode,
      lineWidth,
      variant: all.slice(0, i).filter((r) => r[0] === count).length + 1,
    }));
  const fc08 = compact([
    [1, 1, 5],
    [2, 9, 26],
    [3, 73, 145],
    [3, 73, 82],
    [4, 585, 593],
    [4, 81, 82],
    [5, 4681, 4681],
    [5, 1097, 593],
    [6, 1161, 593],
    [7, 1169, 593],
    [7, 9289, 4681],
    [8, 1170, 593],
    [9, 9361, 4681],
    [9, 9298, 4681],
    [10, 9362, 4681],
  ]);
  for (const [name, entries] of [
    [
      "海拔",
      [
        [1, 1, 1],
        [2, 2, 1],
        [4, 18, 9],
      ],
    ],
    [
      "地图",
      [
        [0, 0, 0],
        [1, 1, 1],
        [2, 2, 1],
        [3, 17, 9],
        [4, 18, 9],
      ],
    ],
    [
      "骑行台",
      [
        [2, 9, 9],
        [3, 17, 9],
      ],
    ],
    [
      "训练课程",
      [
        [1, 1, 1],
        [2, 2, 1],
      ],
    ],
  ])
    entries.forEach(([count, pageMode, lineWidth], i) =>
      fc08.push({
        section: "专项页",
        name,
        variant: i + 1,
        count,
        pageMode,
        lineWidth,
      }),
    );
  const igs630 = compact([
    [1, 1, 5],
    [2, 9, 19],
    [3, 73, 82],
    [4, 137, 82],
    [4, 585, 593],
    [5, 1097, 593],
    [5, 4681, 4681],
    [6, 1161, 593],
    [6, 8777, 4681],
    [7, 1162, 593],
    [7, 9289, 4681],
    [8, 9290, 4681],
    [8, 9353, 4681],
    [9, 9361, 4681],
    [10, 9362, 4681],
  ]);
  const igs800 = D.layouts.map((l) => {
    const replacement = {
      "5:2": [1097, 657],
      "7:2": [1162, 657],
      "9:1": [9298, 4745],
    }[`${l.count}:${l.variant}`];
    return l.section === "普通数据页" && replacement
      ? { ...l, pageMode: replacement[0], lineWidth: replacement[1] }
      : { ...l };
  });
  const binavi = D.layouts.map((l) =>
    l.section === "专项页" &&
    ["爬坡", "爬坡地图"].includes(l.name) &&
    l.variant <= 2
      ? { ...l, count: 1, pageMode: 1, lineWidth: l.variant }
      : { ...l },
  );
  const frameCatalog = {
    endpoint: "/service/sportg/device4j/device/getDeviceDetailsInfo",
    imageType: "BACKGROUND_IN_PAGE_SETTING",
    imageTypeValue: 3,
    queryDevAppVer: "0",
    retrievedDate: "2026-11-27",
    scaleType: "FIT_CENTER",
  };
  const frameSources = {
    BSC500: {
      filename: "bsc500.png",
      imageSize: [840, 1332],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/78ed8f5b-ba72-49fb-95f6-ab1108124eae",
      sha256:
        "14515b23ccd9f986ce47121b4fa22fff36a22a0ca72d775d214083032bbf0091",
      verifiedDevAppVer: "108005",
    },
    iGS800: {
      filename: "igs800.png",
      imageSize: [1548, 2560],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/5557e358-4312-4975-8967-41b9325379c4",
      sha256:
        "2e27bab1bc08e3dd13420ad459769928381052596a4e24983071f06de4135525",
    },
    BiNavi: {
      filename: "binavi.png",
      imageSize: [801, 1332],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/2ddea2f3-fb5a-4f59-9e1e-561e84f40ce7",
      sha256:
        "517b7ad99c4e7b3d82067370f80401abd13f1b8dd2f7cb23ff837f8bf163df9d",
    },
    "BiNavi Air": {
      filename: "binavi-air.png",
      imageSize: [840, 1476],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/9b70994f-8b3e-47a2-972c-ede943197105",
      sha256:
        "49966d8ac30c4d177fbc8929a89e28dacc1f39a529a3e44da4a3edf60c398a99",
    },
    "BiNavi Ultra": {
      filename: "binavi-ultra.png",
      imageSize: [280, 492],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/06945d09-1c43-4100-98ee-659090c4f118",
      sha256:
        "b18fcbe5703daa01848b8bf1eb8a8017120d464d270929bd2d93a9d9a4b08533",
    },
    iGS630S: {
      filename: "igs630s.png",
      imageSize: [560, 888],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/82d75ec8-2bc5-4b46-97d7-91ae8dc92f0e",
      sha256:
        "87137d0654339456e89f995ec5950c0a8f9b2d2c18d9eed50533ba80c1d552ac",
    },
    "BSC200 Pro": {
      filename: "bsc200-pro.png",
      imageSize: [840, 1332],
      sourceURL:
        "https://igp-zh.oss-cn-hangzhou.aliyuncs.com/b87a3d83-2c4d-4fdd-bbc0-8f4afb0d3748",
      sha256:
        "c0d4c6c692dc6d7d7fb7bb80fc2ce1c1a3b905a21b31e52a26d15ea544082228",
    },
  };
  const all = [
    {
      id: "BSC500",
      screen: [198, 295],
      rows: 6,
      maxFields: 12,
      layouts: D.layouts,
      layoutClass: "Bg3PageStyleConfig",
      bluetooth: true,
    },
    {
      id: "iGS800",
      screen: [230, 346],
      rows: 6,
      maxFields: 12,
      layouts: igs800,
      layoutClass: "Bg3PageStyleConfig",
    },
    {
      id: "BiNavi",
      screen: [220, 346],
      rows: 6,
      maxFields: 12,
      layouts: binavi,
      layoutClass: "Bg3PageStyleConfig",
    },
    {
      id: "BiNavi Air",
      screen: [186, 306],
      rows: 6,
      maxFields: 12,
      layouts: D.layouts,
      layoutClass: "Bg3PageStyleConfig",
    },
    {
      id: "BiNavi Ultra",
      screen: [180, 315],
      rows: 6,
      maxFields: 12,
      layouts: D.layouts,
      layoutClass: "Bg3PageStyleConfig",
    },
    {
      id: "iGS630S",
      screen: [185, 286],
      rows: 5,
      maxFields: 10,
      layouts: igs630,
      layoutClass: "PageStyleConfig630s",
    },
    {
      id: "BSC200 Pro",
      screen: [204, 272],
      rows: 5,
      maxFields: 10,
      layouts: fc08,
      layoutClass: "PageStyleConfigFC08",
    },
  ];
  const frameDimensions = {
    BSC500: [280, 444, 74],
    iGS800: [274, 453, 29],
    BiNavi: [274, 453, 29],
    "BiNavi Air": [280, 444, 66],
    "BiNavi Ultra": [280, 453, 68],
    iGS630S: [320, 450, 77],
    "BSC200 Pro": [280, 444, 74],
  };
  for (const p of all) {
    const [width, height, top] = frameDimensions[p.id];
    p.frame = {
      width,
      height,
      top,
      left: (width - p.screen[0]) / 2,
      asset: `assets/igpsport-cn-8.11.00/device-frames/${frameSources[p.id].filename}`,
    };
  }
  function get(id) {
    const profile = all.find((p) => p.id === id);
    if (!profile) throw new Error(`未支持的码表机型：${id}`);
    return profile;
  }
  function layoutsFor(id, type, name) {
    return get(id).layouts.filter((l) =>
      type === 1
        ? l.section === "普通数据页"
        : l.section === "专项页" && l.name === name,
    );
  }
  const api = { all, get, layoutsFor, source, frameSources, frameCatalog };
  if (typeof module !== "undefined" && module.exports) module.exports = api;
  else root.BSCDeviceProfiles = api;
})(globalThis);
