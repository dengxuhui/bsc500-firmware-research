import importlib.util
import json
import os
from pathlib import Path
import unittest


spec = importlib.util.spec_from_file_location("probe_layouts", Path(__file__).with_name("probe_layouts.py"))
probe = importlib.util.module_from_spec(spec)
spec.loader.exec_module(probe)


def header_for(rows):
    header = bytearray(probe.HEADER_SIZE)
    header[0x18] = len(rows)
    for index, (weight, columns) in enumerate(rows):
        header[index * 4:index * 4 + 2] = bytes((weight, columns - 1))
    return header


class HeaderTests(unittest.TestCase):
    def test_single_full_width_row(self):
        self.assertEqual(probe.decode_header(header_for([(6, 1)])), {
            "rows": [{"weight": 6, "columns": 1}],
            "field_count": 1, "page_mode": 1, "line_width": 6,
        })

    def test_rows_use_low_bits_first(self):
        layout = probe.decode_header(header_for([(3, 1), (2, 2), (1, 1)]))
        self.assertEqual(layout["page_mode"], 81)
        self.assertEqual(layout["line_width"], 83)
        self.assertEqual(layout["field_count"], 4)

    def test_six_rows_and_twelve_fields(self):
        layout = probe.decode_header(header_for([(1, 2)] * 6))
        self.assertEqual(layout["field_count"], 12)
        self.assertEqual(layout["page_mode"], sum(2 << (3 * row) for row in range(6)))

    def test_other_header_bytes_do_not_affect_rows(self):
        header = header_for([(6, 1)])
        header[2:4] = b"\xff\xff"
        header[0x19:] = b"\x01\x02\x03"
        self.assertEqual(probe.decode_header(header), probe.decode_header(header_for([(6, 1)])))

    def test_invalid_headers_are_rejected(self):
        for header in (bytes(27), bytes(29), header_for([]), header_for([(0, 1)]),
                       header_for([(8, 1)]), header_for([(1, 3)])):
            with self.subTest(header=header), self.assertRaises(ValueError):
                probe.decode_header(header)
        header = bytearray(28)
        header[0x18] = 7
        with self.assertRaises(ValueError):
            probe.decode_header(header)

    def test_unrecognized_firmware_is_rejected(self):
        with self.assertRaisesRegex(ValueError, "V1.04.003"):
            probe.read_layouts(b"not the original firmware")


class CatalogTests(unittest.TestCase):
    def test_matching_ignores_order_and_duplicates(self):
        layouts = [{"page_mode": 1, "line_width": 6}, {"page_mode": 9, "line_width": 27}]
        catalog = {"sections": [{"name": "普通数据页", "layouts": list(reversed(layouts)) + layouts}]}
        result = probe.compare_catalog(layouts, catalog)
        self.assertTrue(result["same_parameter_pairs"])
        self.assertEqual(result["catalog_unique_layouts"], 2)

    def test_reports_both_directions(self):
        layouts = [{"page_mode": 1, "line_width": 6}]
        catalog = {"sections": [{"name": "普通数据页", "layouts": [{"page_mode": 9, "line_width": 27}]}]}
        result = probe.compare_catalog(layouts, catalog)
        self.assertFalse(result["same_parameter_pairs"])
        self.assertEqual(result["firmware_only"], layouts)
        self.assertEqual(result["catalog_only"], [{"page_mode": 9, "line_width": 27}])

    def test_requires_unique_normal_section(self):
        for sections in ([], [{"name": "专项页", "layouts": []}],
                         [{"name": "普通数据页", "layouts": []}] * 2):
            with self.subTest(sections=sections), self.assertRaises(ValueError):
                probe.compare_catalog([], {"sections": sections})


@unittest.skipUnless(os.environ.get("BSC500_APPLICATION"), "未指定私有原厂主程序路径")
class FirmwareTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.image = Path(os.environ["BSC500_APPLICATION"]).read_bytes()

    def test_actual_table_matches_apk_catalog(self):
        layouts = probe.read_layouts(self.image)
        catalog = json.loads(probe.DEFAULT_CATALOG.read_text())
        self.assertEqual(len(layouts), 22)
        self.assertTrue(probe.compare_catalog(layouts, catalog)["same_parameter_pairs"])

    def test_original_functions_match_and_fallback(self):
        result = probe.verify_layouts(self.image)
        self.assertEqual(len(result["stock_matches"]), 22)
        self.assertEqual(len(result["non_catalog_fallbacks"]), 2)
        for case in result["non_catalog_fallbacks"]:
            self.assertEqual(case["selected_index"], 5)
            self.assertNotEqual(case["requested"]["rows"], case["render_configuration"]["rows"])


if __name__ == "__main__":
    unittest.main()
