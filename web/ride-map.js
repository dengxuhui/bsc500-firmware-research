"use strict";

(() => {
  const context = document.querySelector("#design-map").getContext("2d");
  if (!context) return;
  context.scale(2, 2);
  context.lineJoin = "round";
  context.lineCap = "round";
  context.fillStyle = "#edf0e6";
  context.fillRect(0, 0, 320, 280);

  function shape(points, color) {
    context.beginPath();
    context.moveTo(...points[0]);
    for (const point of points.slice(1)) context.lineTo(...point);
    context.closePath();
    context.fillStyle = color;
    context.fill();
  }

  function stroke(points, color, width) {
    context.beginPath();
    context.moveTo(...points[0]);
    for (const point of points.slice(1)) context.lineTo(...point);
    context.strokeStyle = color;
    context.lineWidth = width;
    context.stroke();
  }

  shape([[0, 0], [122, 0], [107, 37], [76, 46], [37, 37], [0, 52]], "#cce2be");
  shape([[183, 0], [320, 0], [320, 168], [268, 162], [254, 126], [205, 102], [174, 57]], "#c3ddab");
  shape([[214, 11], [304, 8], [320, 56], [313, 115], [267, 101], [246, 65], [206, 53]], "#b7d59e");
  shape([[0, 153], [51, 161], [85, 198], [124, 210], [155, 280], [0, 280]], "#d6e6bf");
  shape([[227, 228], [270, 208], [320, 213], [320, 280], [245, 280]], "#cadaaf");
  shape([[36, 88], [93, 73], [124, 84], [137, 122], [121, 155], [75, 153], [40, 129]], "#e4e5d8");

  const waterways = [[132, -14], [131, 25], [148, 52], [164, 70], [161, 93], [146, 118],
    [151, 144], [181, 176], [192, 198], [183, 231], [197, 260], [207, 291]];
  stroke(waterways, "#accfd1", 38);
  stroke(waterways, "#b8dadd", 28);
  stroke([[290, 50], [269, 53], [251, 66], [246, 83], [268, 106], [279, 123]], "#a8c58e", 1);
  stroke([[304, 62], [282, 65], [265, 77], [265, 90], [288, 114]], "#a8c58e", 1);
  stroke([[318, 79], [295, 79], [284, 89], [291, 103], [318, 116]], "#a8c58e", 1);

  const localRoads = [
    [[-10, 74], [46, 72], [89, 55], [130, 59], [177, 51], [224, 34], [274, 36], [328, 52]],
    [[-10, 110], [42, 106], [78, 91], [107, 90], [143, 113], [197, 110], [239, 130], [329, 146]],
    [[-8, 187], [35, 184], [67, 166], [104, 172], [141, 200], [188, 216], [224, 212], [274, 193], [328, 199]],
    [[-5, 243], [49, 231], [87, 243], [131, 244], [158, 256], [204, 249], [235, 229], [273, 222], [321, 230]],
    [[51, 47], [51, 78], [62, 98], [61, 126], [75, 149], [66, 167], [73, 199], [88, 217], [98, 247]],
    [[112, 37], [99, 57], [95, 80], [103, 115], [100, 148], [118, 177], [113, 213], [137, 247], [139, 284]],
    [[208, 46], [201, 70], [217, 96], [213, 121], [227, 153], [226, 188], [223, 211], [233, 239], [232, 286]],
    [[273, 100], [264, 139], [274, 168], [273, 193], [284, 218], [300, 240], [309, 287]],
  ];
  for (const road of localRoads) {
    stroke(road, "#d0d5c8", 6);
    stroke(road, "#fffefa", 4);
  }

  const mainRoad = [[-8, 151], [40, 141], [76, 134], [105, 139], [129, 157],
    [160, 173], [209, 169], [244, 161], [279, 167], [330, 182]];
  stroke(mainRoad, "#d4cda4", 10);
  stroke(mainRoad, "#fff3c8", 7);

  const route = [[210, 285], [208, 256], [211, 225], [207, 198], [193, 179], [181, 174],
    [160, 173], [129, 157], [105, 139], [118, 117], [126, 90], [111, 63], [118, 42], [141, 21]];
  stroke(route, "#fbfffb", 10);
  stroke(route, "#167249", 6);

  context.fillStyle = "#465e53";
  context.font = '11px "PingFang SC", sans-serif';
  context.fillText("滨河绿道", 19, 203);
  context.fillText("松林公园", 233, 123);
  context.fillText("沿河路", 219, 158);

  shape([[175, 191], [192, 163], [203, 194], [189, 188]], "#fff");
  shape([[179, 188], [192, 168], [199, 190], [189, 184]], "#163c56");
  context.beginPath();
  context.arc(141, 21, 4, 0, Math.PI * 2);
  context.fillStyle = "#167249";
  context.fill();
})();
