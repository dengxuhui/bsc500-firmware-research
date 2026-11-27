import importlib.util
import os
from pathlib import Path
import unittest


spec = importlib.util.spec_from_file_location("backlight", Path(__file__).with_name("backlight.py"))
backlight = importlib.util.module_from_spec(spec)
spec.loader.exec_module(backlight)


class CsvTests(unittest.TestCase):
    def test_line_endings_and_final_line(self):
        for text in ("0,10\n254,100\n", "0,10\r\n254,100", "0,10\r254,100\r"):
            with self.subTest(text=text):
                self.assertEqual(backlight.parse_table(text, "level"), [(0, 10), (254, 100)])

    def test_empty_lines(self):
        self.assertEqual(backlight.parse_table("\n0,10\n\n", "level"), [(0, 10)])

    def test_maximum_rows(self):
        for kind, count, line in (("level", 20, "{},10\n"), ("light", 50, "{},10,5\n")):
            with self.subTest(kind=kind):
                text = "".join(line.format(i) for i in range(count))
                self.assertEqual(len(backlight.parse_table(text, kind)), count)
                with self.assertRaises(ValueError):
                    backlight.parse_table(text + line.format(count), kind)

    def test_reject_malformed_inputs(self):
        cases = ("", "\n", "0", "0,10,20", "x,10", "0,", "-1,10", "0,-1", "0,1.5",
                 "0,256", "65536,10", "０,10", "0,10\0", " 0,10", "0, 10", "0," + "1" * 62)
        for text in cases:
            with self.subTest(text=text), self.assertRaises(ValueError):
                backlight.parse_table(text, "level")

    def test_reject_invalid_kind(self):
        with self.assertRaises(ValueError):
            backlight.parse_table("0,10", "unknown")

    def test_reject_duplicates_and_unsorted_rows(self):
        for kind, text in (("level", "0,10\n0,20"), ("level", "10,10\n0,20"),
                           ("light", "0,10,5\n0,20,10"), ("light", "0,10,5\n20,20,10\n10,30,15")):
            with self.subTest(text=text), self.assertRaises(ValueError):
                backlight.parse_table(text, kind)

    def test_reject_unsafe_interpolation(self):
        for text in ("10,10,5\n100,20,10", "0,100,50\n100,10,5", "0,10,50\n100,20,5",
                     "0,0,0\n2147483647,255,255", "0,10,5\n2147483648,20,10"):
            with self.subTest(text=text), self.assertRaises(ValueError):
                backlight.parse_table(text, "light")

    def test_tool_range_is_not_percentage(self):
        self.assertEqual(backlight.parse_table("0,255,255", "light"), [(0, 255, 255)])
        self.assertEqual(backlight.parse_table("0,10,5\n2147483647,10,5", "light")[-1][0], 2147483647)


class LookupTests(unittest.TestCase):
    def test_slider_uses_row_index_not_first_column(self):
        uniform = backlight.parse_table(backlight.DEFAULT_LEVEL, "level")
        nonuniform = backlight.parse_table("100,10\n200,50\n300,100", "level")
        for position in range(255):
            self.assertEqual(backlight.slider_brightness(uniform, position),
                             backlight.slider_brightness(nonuniform, position))

    def test_slider_boundaries(self):
        rows = [(0, 10), (127, 50), (254, 100)]
        for position, expected in ((0, 10), (63, 10), (64, 50), (190, 50), (191, 100), (254, 100)):
            self.assertEqual(backlight.slider_brightness(rows, position), expected)
        self.assertEqual(backlight.slider_brightness([(0, 10), (254, 100)], 127), 100)

    def test_slider_single_row(self):
        self.assertEqual(backlight.slider_brightness([(100, 20)], 254), 20)

    def test_ambient_integer_interpolation_and_saturation(self):
        rows = backlight.parse_table(backlight.DEFAULT_LIGHT, "light")
        for lux, day, night in ((0, 10, 5), (1, 10, 5), (50, 30, 12), (100, 50, 20),
                                (550, 75, 30), (1000, 100, 40), (2147483647, 100, 40)):
            self.assertEqual(backlight.ambient_brightness(rows, lux), day)
            self.assertEqual(backlight.ambient_brightness(rows, lux, True), night)

    def test_ambient_single_row(self):
        self.assertEqual(backlight.ambient_brightness([(0, 20, 10)], 100), 20)

    def test_reject_invalid_queries(self):
        for position in (-1, 255):
            with self.assertRaises(ValueError):
                backlight.slider_brightness([(0, 10)], position)
        for lux in (-1, 2147483648):
            with self.assertRaises(ValueError):
                backlight.ambient_brightness([(0, 10, 5)], lux)
        with self.assertRaises(ValueError):
            backlight.slider_brightness([], 0)
        with self.assertRaises(ValueError):
            backlight.ambient_brightness([], 0)

    def test_reject_unrecognized_firmware_before_loading_emulator(self):
        with self.assertRaises(ValueError):
            backlight.FirmwareBacklight(b"not firmware", {})


@unittest.skipUnless(os.environ.get("BSC500_APPLICATION"), "需要 BSC500_APPLICATION 指向本机原厂镜像")
class FirmwareTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.image = Path(os.environ["BSC500_APPLICATION"]).read_bytes()

    def test_original_firmware_and_models(self):
        report = backlight.verification_report(self.image)
        self.assertGreater(report["checks"], 1000)
        self.assertFalse(report["hardware_tested"])
        self.assertEqual(report["cases"]["no_files"]["decoded"]["csv_enabled"], False)
        for hazard in report["rejected_hazards"]:
            self.assertGreater(hazard["firmware_day_u32"], 255)

    def test_every_slider_row_count_against_firmware(self):
        for count in range(1, 21):
            text = "".join(f"{i * i},{i * 10}\n" for i in range(count))
            with self.subTest(count=count):
                result = backlight.verify_tables(self.image, text, None)
                self.assertEqual(result["checks"], 256)

    def test_nonuniform_ambient_intervals_against_firmware(self):
        text = "".join(f"{i * i * 17},{i * 4},{i * 2}\n" for i in range(50))
        self.assertEqual(len(backlight.verify_tables(self.image, None, text)["decoded"]["light"]), 50)

    def test_parser_truncates_excess_rows(self):
        files = {"0:level.txt": "".join(f"{i},10\n" for i in range(21)).encode(),
                 "0:light.txt": "".join(f"{i},10,5\n" for i in range(51)).encode()}
        result = backlight.FirmwareBacklight(self.image, files).tables()
        self.assertEqual(len(result["level"]), 20)
        self.assertEqual(len(result["light"]), 50)

    def test_actual_parser_is_not_a_validator(self):
        result = backlight.FirmwareBacklight(self.image, {
            "0:level.txt": b"invalid,65536\n0,-1\n1,20,ignored\n"}).tables()
        self.assertEqual(result["level"], [(0, 0), (0, 65535), (1, 20)])


if __name__ == "__main__":
    unittest.main()
