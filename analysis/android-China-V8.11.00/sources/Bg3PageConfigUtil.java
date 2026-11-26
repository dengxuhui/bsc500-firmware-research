package com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils;

import android.util.Base64;
import androidx.core.view.InputDeviceCompat;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.garmin.fit.GarminProduct;
import com.garmin.fit.Manufacturer;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.igpsport.blelib.bean.Config;
import com.igpsport.blelib.utils.StringUtils;
import com.igpsport.globalapp.common.LogUtils;
import com.igpsport.globalapp.devicemodule.pagesetting.beans.PageData;
import com.igpsport.globalapp.devicemodule.pagesettingnew.model.CategoryItem;
import com.igpsport.globalapp.devicemodule.pagesettingnew.model.Field;
import com.igpsport.globalapp.devicemodule.pagesettingnew.model.Graph;
import com.igpsport.globalapp.devicemodule.pagesettingnew.model.GraphStyle;
import com.igpsport.globalapp.dialog.ConnectDeviceBottomDialog;
import com.igpsport.igpsportandroid.R;
import com.mapbox.search.result.SearchAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Bg3PageConfigUtil.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001DB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0005J\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u001e2\u0006\u0010\u001c\u001a\u00020\u0005J\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u001e2\u0006\u0010\u001c\u001a\u00020\u0005J\u000e\u0010 \u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0005J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00122\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0017J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u00122\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&J\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u00122\u0006\u0010\u001c\u001a\u00020\u0005J@\u0010(\u001a\u0004\u0018\u00010\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00172\b\b\u0002\u0010.\u001a\u00020/J*\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u001e2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u00101\u001a\u00020\u0017J\u000e\u00102\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0005J\u001c\u00103\u001a\b\u0012\u0004\u0012\u0002040\u00122\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0012H\u0002J\u001c\u00105\u001a\u00020/2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010+\u001a\u00020\u0017J\"\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00050\u00162\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0012H\u0002J\u001e\u00107\u001a\u0004\u0018\u00010\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010+\u001a\u00020\u0017J\u0016\u00108\u001a\n\u0012\u0004\u0012\u000209\u0018\u00010\u00122\u0006\u0010\u001c\u001a\u00020\u0005J\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00122\u0006\u0010%\u001a\u00020&J6\u0010<\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\u001e2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u0017J\u001e\u0010?\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u0017J\u0016\u0010@\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0017J\u001c\u0010A\u001a\u00020\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010+\u001a\u00020\u0017J\u001c\u0010B\u001a\u00020\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010+\u001a\u00020\u0017J(\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u001e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00122\u0006\u0010+\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/Bg3PageConfigUtil;", "", "<init>", "()V", "iGS630S", "", "iGSBG1", "iGSBG2", "iGSBG3", "FC07", "BSC500", "iGSBG4", "iGSBG5", "BiNaviUltra", "FC08", "BSC200Pro", "FC09", "remoteConfigDevices", "", "getRemoteConfigDevices", "()Ljava/util/List;", "pageModeNameMap", "", "", "getPageModeNameMap", "()Ljava/util/Map;", "DEVICE_TOTAL_ROWS_NUM_MAP", "getDeviceTotalRowsNum", ConnectDeviceBottomDialog.DEVICE_NAME_ARG, "getDeviceBgWidthAndHeight", "Lkotlin/Pair;", "getContainerWidthWidthAndHeight", "getContainerMarginTop", "getPageStyle", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageShowTypeBean;", "pageDataSize", "getPicturePageStyle", "pageType", "Lcom/igpsport/blelib/bean/Config$PAGE_TYPE;", "getAlwaysDisplayPageTypeList", "getGraphImgResId", "dataList", "Lcom/igpsport/globalapp/devicemodule/pagesettingnew/model/CategoryItem;", "value", "heightRatio", "spanSize", "isGraph", "", "getBG1PicturePageNameAndChartImgResId", "heightWeight", "getMaxDataFieldSize", "getGraphicalDataFieldList", "Lcom/igpsport/globalapp/devicemodule/pagesettingnew/model/Field;", "isGraphicalDataField", "getAllPageDataFieldList", "getDataFieldResId", "getDefaultPageMsgList", "Lcom/igpsport/blelib/bean/Config$page_msg;", "getDefaultPicturePageDataList", "Lcom/igpsport/globalapp/devicemodule/pagesetting/beans/PageData;", "getPageItemDataHeightListAndSpanSizeList", "pageMode", "lineWidth", "getDataFieldNum", "getDataHeightWeight", "getDefaultDataValue", "getDefaultDataName", "getGraphicalDataReplacedTextData", "GraphPageStyle", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Bg3PageConfigUtil {
    private static final String BSC200Pro = "BSC200 Pro";
    private static final String BSC500 = "BSC500";
    private static final String BiNaviUltra = "BiNavi Ultra";
    private static final String FC07 = "FC07";
    private static final String FC08 = "FC08";
    private static final String FC09 = "FC09";
    private static final String iGS630S = "iGS630S";
    private static final String iGSBG1 = "iGS800";
    private static final String iGSBG2 = "BiNavi";
    private static final String iGSBG3 = "BiNavi Air";
    private static final String iGSBG4 = "BG04";
    private static final String iGSBG5 = "BG05";
    public static final Bg3PageConfigUtil INSTANCE = new Bg3PageConfigUtil();
    private static final List<String> remoteConfigDevices = CollectionsKt.listOf((Object[]) new String[]{"iGS800", "BiNavi", "BiNavi Air", "FC07", "BSC500", "BG04", "BG05", "BiNavi Ultra", "FC08", "BSC200 Pro", "FC09"});
    private static final Map<String, Integer> pageModeNameMap = MapsKt.mapOf(TuplesKt.to("outdoor", Integer.valueOf(R.string.outdoor_ride)), TuplesKt.to("road", Integer.valueOf(R.string.rode_ride)), TuplesKt.to("commute", Integer.valueOf(R.string.page_commute)), TuplesKt.to("tour", Integer.valueOf(R.string.tour_ride)), TuplesKt.to("indoor", Integer.valueOf(R.string.indoor_ride)), TuplesKt.to("lev", Integer.valueOf(R.string.e_bike)), TuplesKt.to("large_font", Integer.valueOf(R.string.large_font_mode)));
    private static final Map<String, Integer> DEVICE_TOTAL_ROWS_NUM_MAP = MapsKt.mapOf(TuplesKt.to("iGS630S", 5), TuplesKt.to("iGS800", 6), TuplesKt.to("BiNavi", 6), TuplesKt.to("BiNavi Air", 6), TuplesKt.to("FC07", 6), TuplesKt.to("BSC500", 6), TuplesKt.to("BG04", 6), TuplesKt.to("BG05", 6), TuplesKt.to("BiNavi Ultra", 6), TuplesKt.to("FC08", 5), TuplesKt.to("BSC200 Pro", 5), TuplesKt.to("FC09", 5));
    public static final int $stable = 8;

    /* JADX INFO: compiled from: Bg3PageConfigUtil.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Config.PAGE_TYPE.values().length];
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_ELEVATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_MAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_COMPASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_LAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_ROADBOOK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_FEC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_COURSE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_SEGMENT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_FITNESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB_MAP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_GROUP_RIDE_INFOR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_MUSIC.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CAMERA.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private Bg3PageConfigUtil() {
    }

    public final List<String> getRemoteConfigDevices() {
        return remoteConfigDevices;
    }

    public final Map<String, Integer> getPageModeNameMap() {
        return pageModeNameMap;
    }

    public final int getDeviceTotalRowsNum(String deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Integer num = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        if (num != null) {
            return num.intValue();
        }
        return 5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:

        if (r4.equals("BSC500") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:

        if (r4.equals("BiNavi Air") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0067, code lost:

        if (r4.equals("FC09") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:

        if (r4.equals("FC08") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007b, code lost:

        if (r4.equals("FC07") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(280.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(444.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00bb, code lost:

        if (r4.equals("BG04") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d3, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(280.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(453.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00da, code lost:

        if (r4.equals("BSC200 Pro") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f2, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(280.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(444.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f9, code lost:

        if (r4.equals("iGS800") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0113, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(274.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(453.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:

        if (r4.equals("BiNavi Ultra") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:

        if (r4.equals("BiNavi") == false) goto L53;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getDeviceBgWidthAndHeight(java.lang.String r4) {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getDeviceBgWidthAndHeight(java.lang.String):kotlin.Pair");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:

        if (r3.equals("BSC500") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0097, code lost:

        if (r3.equals("FC09") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a1, code lost:

        if (r3.equals("FC08") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ab, code lost:

        if (r3.equals("FC07") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c9, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(198.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(295.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f4, code lost:

        if (r3.equals("BG04") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010e, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(180.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(315.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0115, code lost:

        if (r3.equals("BSC200 Pro") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0131, code lost:

        return new kotlin.Pair<>(java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(204.0f)), java.lang.Integer.valueOf(com.blankj.utilcode.util.SizeUtils.dp2px(272.0f)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:

        if (r3.equals("BiNavi Ultra") == false) goto L57;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getContainerWidthWidthAndHeight(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getContainerWidthWidthAndHeight(java.lang.String):kotlin.Pair");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:

        if (r2.equals("BSC500") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:

        if (r2.equals("FC09") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:

        if (r2.equals("FC08") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:

        if (r2.equals("FC07") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:

        return com.blankj.utilcode.util.SizeUtils.dp2px(74.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0085, code lost:

        if (r2.equals("BG04") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008e, code lost:

        return com.blankj.utilcode.util.SizeUtils.dp2px(68.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0095, code lost:

        if (r2.equals("BSC200 Pro") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x009c, code lost:

        return com.blankj.utilcode.util.SizeUtils.dp2px(74.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a3, code lost:

        if (r2.equals("iGS800") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ac, code lost:

        return com.blankj.utilcode.util.SizeUtils.dp2px(29.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:

        if (r2.equals("BiNavi Ultra") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:

        if (r2.equals("BiNavi") == false) goto L55;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int getContainerMarginTop(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getContainerMarginTop(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:

        if (r1.equals("BSC500") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:

        if (r1.equals("BiNavi Air") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:

        if (r1.equals("FC08") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:

        if (r1.equals("FC07") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006d, code lost:

        if (r1.equals("BG05") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:

        if (r1.equals("BG04") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007f, code lost:

        if (r1.equals("BSC200 Pro") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0088, code lost:

        return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.PageStyleConfigFC08.INSTANCE.getPageStyleForGrids(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008f, code lost:

        if (r1.equals("iGS800") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0098, code lost:

        return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.INSTANCE.getBg1PageStyleForGrids(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:

        if (r1.equals("BiNavi Ultra") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:

        if (r1.equals("BiNavi") == false) goto L49;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PageShowTypeBean> getPageStyle(java.lang.String r1, int r2) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getPageStyle(java.lang.String, int):java.util.List");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:

        if (r1.equals("BSC500") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:

        if (r1.equals("BiNavi Air") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:

        if (r1.equals("FC08") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:

        if (r1.equals("FC07") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0072, code lost:

        if (r1.equals("BG05") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:

        if (r1.equals("BG04") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0084, code lost:

        if (r1.equals("BSC200 Pro") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008d, code lost:

        return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.PageStyleConfigFC08.INSTANCE.getPicturePageStyleForGrids(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0094, code lost:

        if (r1.equals("iGS800") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009d, code lost:

        return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.INSTANCE.getBg1PicturePageStyleForGrids(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:

        if (r1.equals("BiNavi Ultra") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:

        if (r1.equals("BiNavi") == false) goto L49;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PageShowTypeBean> getPicturePageStyle(java.lang.String r1, com.igpsport.blelib.bean.Config.PAGE_TYPE r2) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getPicturePageStyle(java.lang.String, com.igpsport.blelib.bean.Config$PAGE_TYPE):java.util.List");
    }

    public final List<Config.PAGE_TYPE> getAlwaysDisplayPageTypeList(String deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        if (Intrinsics.areEqual(deviceName, "iGS800") || Intrinsics.areEqual(deviceName, "BiNavi")) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(CollectionsKt.listOf((Object[]) new Config.PAGE_TYPE[]{Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_FEC, Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_COURSE, Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB, Config.PAGE_TYPE.enum_PAGE_TYPE_GROUP_RIDE_INFOR, Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB_MAP, Config.PAGE_TYPE.enum_PAGE_TYPE_CAMERA, Config.PAGE_TYPE.enum_PAGE_TYPE_SEGMENT}));
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    /* JADX INFO: compiled from: Bg3PageConfigUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/Bg3PageConfigUtil$GraphPageStyle;", "", "styleString", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getStyleString", "()Ljava/lang/String;", "IMAGE_2X1", "IMAGE_2X2", "IMAGE_2X3", "IMAGE_1X2", "Companion", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum GraphPageStyle {
        IMAGE_2X1("2*1"),
        IMAGE_2X2("2*2"),
        IMAGE_2X3("2*3"),
        IMAGE_1X2("1*2");

        private final String styleString;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        public static EnumEntries<GraphPageStyle> getEntries() {
            return $ENTRIES;
        }

        GraphPageStyle(String str) {
            this.styleString = str;
        }

        public final String getStyleString() {
            return this.styleString;
        }

        /* JADX INFO: compiled from: Bg3PageConfigUtil.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/Bg3PageConfigUtil$GraphPageStyle$Companion;", "", "<init>", "()V", "from", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/Bg3PageConfigUtil$GraphPageStyle;", "styleString", "", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final GraphPageStyle from(String styleString) {
                GraphPageStyle next;
                Intrinsics.checkNotNullParameter(styleString, "styleString");
                Iterator<GraphPageStyle> it2 = GraphPageStyle.getEntries().iterator();
                while (it2.hasNext()) {
                    next = it2.next();
                    if (Intrinsics.areEqual(next.getStyleString(), styleString)) {
                        return next;
                    }
                }
                next = null;
                return next;
            }
        }
    }

    public static /* synthetic */ String getGraphImgResId$default(Bg3PageConfigUtil bg3PageConfigUtil, List list, String str, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            z = true;
        }
        return bg3PageConfigUtil.getGraphImgResId(list, str, i, i2, i3, z);
    }

    public final String getGraphImgResId(List<CategoryItem> dataList, String deviceName, int value, int heightRatio, int spanSize, boolean isGraph) {
        Object next;
        List<GraphStyle> graphStyleList;
        Object next2;
        List<Graph> graphList;
        Object next3;
        Graph graph;
        Object next4;
        Object next5;
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        LogUtils.d("getGraphImgResId deviceName = " + deviceName + SearchAddress.SEPARATOR + HexExtensionsKt.toHexString$default(value, (HexFormat) null, 1, (Object) null) + ", height = " + heightRatio + ", spanSize = " + spanSize + HexStringBuilder.DEFAULT_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        Iterator it3 = arrayList.iterator();
        do {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (((Field) next).getDecValue() != value);
        Field field = (Field) next;
        if (field == null || (graphStyleList = field.getGraphStyleList()) == null) {
            return null;
        }
        Iterator<T> it4 = graphStyleList.iterator();
        do {
            if (!it4.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it4.next();
        } while (((GraphStyle) next2).getStyle() != 0);
        GraphStyle graphStyle = (GraphStyle) next2;
        if (graphStyle == null || (graphList = graphStyle.getGraphList()) == null) {
            return null;
        }
        if (spanSize == 1 && heightRatio == 1) {
            Iterator<T> it5 = graphList.iterator();
            do {
                if (!it5.hasNext()) {
                    next5 = null;
                    break;
                }
                next5 = it5.next();
            } while (!Intrinsics.areEqual(((Graph) next5).getLocate(), spanSize + "*" + heightRatio));
            graph = (Graph) next5;
            if (graph == null) {
                return null;
            }
        } else {
            List<Graph> list = graphList;
            Iterator<T> it6 = list.iterator();
            do {
                if (!it6.hasNext()) {
                    next3 = null;
                    break;
                }
                next3 = it6.next();
            } while (!Intrinsics.areEqual(((Graph) next3).getLocate(), spanSize + "*" + heightRatio));
            graph = (Graph) next3;
            if (graph == null) {
                Iterator<T> it7 = list.iterator();
                do {
                    if (!it7.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it7.next();
                } while (!Intrinsics.areEqual(((Graph) next4).getLocate(), "2*3"));
                graph = (Graph) next4;
                if (graph == null) {
                    return null;
                }
            }
        }
        LogUtils.d("getGraphImgResId url = " + graph.getUrl());
        return graph.getUrl();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:33:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:38:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:557:0x0837  */
    /* JADX WARN: Code duplicated, block: B:71:0x0148 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x014a  */
    /* JADX WARN: Code duplicated, block: B:73:0x014e  */
    /* JADX WARN: Code duplicated, block: B:78:0x015e  */
    /* JADX WARN: Code duplicated, block: B:792:0x0b2f  */
    /* JADX WARN: Code duplicated, block: B:79:0x0162  */
    /* JADX WARN: Code duplicated, block: B:807:0x0b5e  */
    /* JADX WARN: Code duplicated, block: B:902:0x0c8e  */
    /* JADX WARN: Code duplicated, block: B:931:0x0ceb  */
    /* JADX WARN: Code duplicated, block: B:93:0x0196  */
    /* JADX WARN: Code duplicated, block: B:94:0x019a  */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01b2, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01ba, code lost:

        if (r22.equals("BiNavi") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01c2, code lost:

        if (r22.equals("BSC500") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ca, code lost:

        if (r22.equals("BiNavi Air") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01d2, code lost:

        if (r22.equals("FC09") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01da, code lost:

        if (r22.equals("FC08") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01e2, code lost:

        if (r22.equals("FC07") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01ea, code lost:

        if (r22.equals("BG05") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01f2, code lost:

        if (r22.equals("BG04") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01f6, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0203, code lost:

        if (r24 == 4) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0206, code lost:

        if (r24 == 5) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0208, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_6_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x020d, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_5_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0211, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_4_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x021b, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0220, code lost:

        if (r22.equals("BSC200 Pro") != false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0222, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x022f, code lost:

        if (r24 == 4) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0232, code lost:

        if (r24 == 5) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0234, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_6_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0239, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_5_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x023d, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_4_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0247, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x024c, code lost:

        if (r22.equals("iGS800") == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x024f, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x025c, code lost:

        if (r24 == 4) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x025e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0263, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_climb_weight_4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x026d, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0299, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02a0, code lost:

        if (r22.equals("BG05") == false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02a7, code lost:

        if (r22.equals("BG04") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x02a9, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.training_effect);
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02b6, code lost:

        if (r3 == false) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02b8, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_effect_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x02bc, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_effect_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x02c6, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x02f2, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x02fa, code lost:

        if (r22.equals("BiNavi") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0302, code lost:

        if (r22.equals("BSC500") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x030a, code lost:

        if (r22.equals("BiNavi Air") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x037b, code lost:

        if (r22.equals("FC08") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0383, code lost:

        if (r22.equals("FC07") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0387, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.segment);
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0395, code lost:

        if (r24 == 3) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0397, code lost:

        if (r24 == 4) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x0399, code lost:

        if (r3 == false) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x039b, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x039f, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x03a3, code lost:

        if (r3 == false) goto L242;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x03a5, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x03a9, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x03ad, code lost:

        if (r3 == false) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x03af, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_3_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x03b3, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_3_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x03bd, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x03c2, code lost:

        if (r22.equals("BG05") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x03ca, code lost:

        if (r22.equals("BG04") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x03ce, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.segment);
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x03db, code lost:

        if (r24 != 4) goto L257;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x03dd, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segmenrt_weight_4_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x03e1, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segmenrt_weight_5_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x03eb, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x03f0, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x03f4, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.segment);
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0402, code lost:

        if (r24 == 3) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x0404, code lost:

        if (r24 == 4) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x0406, code lost:

        if (r3 == false) goto L268;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x0408, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x040c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0410, code lost:

        if (r3 == false) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x0412, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x0416, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x041a, code lost:

        if (r3 == false) goto L274;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x041c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_3_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x0420, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_3_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x042a, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x042f, code lost:

        if (r22.equals("iGS800") == false) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0432, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.segment);
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x043f, code lost:

        if (r24 != 4) goto L285;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x0441, code lost:

        if (r3 == false) goto L284;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x0443, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x0447, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_4_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x044b, code lost:

        if (r3 == false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x044d, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x0451, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_segment_weight_5_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x045b, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0478, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x0480, code lost:

        if (r22.equals("BiNavi") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x0488, code lost:

        if (r22.equals("BSC500") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x04f3, code lost:

        if (r22.equals("FC08") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x04fb, code lost:

        if (r22.equals("FC07") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x04ff, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.training_course);
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x0509, code lost:

        if (r3 == false) goto L336;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x050b, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x050f, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x0519, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x051e, code lost:

        if (r22.equals("BG05") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0525, code lost:

        if (r22.equals("BG04") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x0528, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.training_course);
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x0532, code lost:

        if (r3 == false) goto L348;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x0534, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x0538, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0542, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x0547, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x054a, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.training_course);
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x0554, code lost:

        if (r3 == false) goto L357;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x0556, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_cn_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x055a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_en_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x0564, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x0569, code lost:

        if (r22.equals("iGS800") == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x056c, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.training_course);
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x0576, code lost:

        if (r3 == false) goto L366;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x0578, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x057c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_training_course_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x0586, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x059c, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x05a4, code lost:

        if (r22.equals("BiNavi") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:381:0x05ac, code lost:

        if (r22.equals("BSC500") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x0626, code lost:

        if (r22.equals("FC08") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x062e, code lost:

        if (r22.equals("FC07") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x0632, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.intelligent_cycling_platform);
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x063f, code lost:

        if (r3 == false) goto L419;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x0641, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x0645, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x064f, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0654, code lost:

        if (r22.equals("BG05") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x065c, code lost:

        if (r22.equals("BG04") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x065f, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.intelligent_cycling_platform);
     */
    /* JADX WARN: Code restructure failed: missing block: B:429:0x066c, code lost:

        if (r3 == false) goto L431;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x066e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x0672, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x067c, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x0681, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x0684, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.intelligent_cycling_platform);
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x0691, code lost:

        if (r3 == false) goto L440;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x0693, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_cn_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0697, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_en_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x06a1, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x06a6, code lost:

        if (r22.equals("iGS800") == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x06a9, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.intelligent_cycling_platform);
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x06b6, code lost:

        if (r3 == false) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x06b8, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x06bc, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_power_trainner_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x06c6, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x06dc, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:461:0x06e4, code lost:

        if (r22.equals("BiNavi") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:464:0x06ec, code lost:

        if (r22.equals("BSC500") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:467:0x06f4, code lost:

        if (r22.equals("BiNavi Air") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:484:0x0742, code lost:

        if (r22.equals("FC08") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x074a, code lost:

        if (r22.equals("FC07") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x074e, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.climbing_plan);
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x075b, code lost:

        if (r24 == 4) goto L503;
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x075e, code lost:

        if (r24 == 5) goto L500;
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x0761, code lost:

        if (r24 == 6) goto L497;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0763, code lost:

        if (r3 == false) goto L499;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0766, code lost:

        if (r3 == false) goto L499;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x0768, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_6_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x076c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_6_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x0770, code lost:

        if (r3 == false) goto L502;
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x0772, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_5_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x0776, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_5_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:0x077a, code lost:

        if (r3 == false) goto L505;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x077c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_4_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0780, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_weight_4_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x078a, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x078f, code lost:

        if (r22.equals("BG05") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x0796, code lost:

        if (r22.equals("BG04") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x079d, code lost:

        if (r22.equals("BSC200 Pro") != false) goto L520;
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x07a4, code lost:

        if (r22.equals("iGS800") == false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:520:0x07a7, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.climbing_plan);
     */
    /* JADX WARN: Code restructure failed: missing block: B:521:0x07b4, code lost:

        if (r3 == false) goto L523;
     */
    /* JADX WARN: Code restructure failed: missing block: B:522:0x07b6, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:523:0x07ba, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_climb_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x07c4, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:534:0x07f7, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:537:0x07ff, code lost:

        if (r22.equals("BiNavi") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0807, code lost:

        if (r22.equals("BSC500") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:543:0x080f, code lost:

        if (r22.equals("BiNavi Air") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:584:0x0890, code lost:

        if (r22.equals("FC08") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:587:0x0898, code lost:

        if (r22.equals("FC07") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:589:0x089c, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.lap);
     */
    /* JADX WARN: Code restructure failed: missing block: B:590:0x08a7, code lost:

        if (r24 == 3) goto L604;
     */
    /* JADX WARN: Code restructure failed: missing block: B:592:0x08aa, code lost:

        if (r24 == 4) goto L601;
     */
    /* JADX WARN: Code restructure failed: missing block: B:594:0x08ad, code lost:

        if (r24 == 5) goto L598;
     */
    /* JADX WARN: Code restructure failed: missing block: B:595:0x08af, code lost:

        if (r3 == false) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:596:0x08b1, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:597:0x08b5, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:598:0x08b9, code lost:

        if (r3 == false) goto L600;
     */
    /* JADX WARN: Code restructure failed: missing block: B:599:0x08bb, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:600:0x08bf, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:601:0x08c3, code lost:

        if (r3 == false) goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:602:0x08c5, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:603:0x08c9, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:604:0x08cd, code lost:

        if (r3 == false) goto L606;
     */
    /* JADX WARN: Code restructure failed: missing block: B:605:0x08cf, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_3_cn_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:606:0x08d3, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_3_en_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:608:0x08dd, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:610:0x08e2, code lost:

        if (r22.equals("BG05") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:613:0x08ea, code lost:

        if (r22.equals("BG04") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:615:0x08ee, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.lap);
     */
    /* JADX WARN: Code restructure failed: missing block: B:616:0x08f9, code lost:

        if (r24 == 4) goto L625;
     */
    /* JADX WARN: Code restructure failed: missing block: B:618:0x08fc, code lost:

        if (r24 == 5) goto L622;
     */
    /* JADX WARN: Code restructure failed: missing block: B:619:0x08fe, code lost:

        if (r3 == false) goto L621;
     */
    /* JADX WARN: Code restructure failed: missing block: B:620:0x0900, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:621:0x0904, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:622:0x0908, code lost:

        if (r3 == false) goto L624;
     */
    /* JADX WARN: Code restructure failed: missing block: B:623:0x090a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:624:0x090e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:625:0x0912, code lost:

        if (r3 == false) goto L627;
     */
    /* JADX WARN: Code restructure failed: missing block: B:626:0x0914, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:627:0x0918, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:629:0x0922, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:631:0x0927, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:633:0x092b, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.lap);
     */
    /* JADX WARN: Code restructure failed: missing block: B:634:0x0936, code lost:

        if (r24 == 3) goto L648;
     */
    /* JADX WARN: Code restructure failed: missing block: B:636:0x0939, code lost:

        if (r24 == 4) goto L645;
     */
    /* JADX WARN: Code restructure failed: missing block: B:638:0x093c, code lost:

        if (r24 == 5) goto L642;
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x093e, code lost:

        if (r3 == false) goto L641;
     */
    /* JADX WARN: Code restructure failed: missing block: B:640:0x0940, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:641:0x0944, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:642:0x0948, code lost:

        if (r3 == false) goto L644;
     */
    /* JADX WARN: Code restructure failed: missing block: B:643:0x094a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:644:0x094e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:645:0x0952, code lost:

        if (r3 == false) goto L647;
     */
    /* JADX WARN: Code restructure failed: missing block: B:646:0x0954, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:647:0x0958, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:648:0x095c, code lost:

        if (r3 == false) goto L650;
     */
    /* JADX WARN: Code restructure failed: missing block: B:649:0x095e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_3_cn_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:650:0x0962, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_3_en_bg3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:652:0x096c, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:654:0x0971, code lost:

        if (r22.equals("iGS800") == false) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:656:0x0974, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.lap);
     */
    /* JADX WARN: Code restructure failed: missing block: B:657:0x097f, code lost:

        if (r24 == 4) goto L666;
     */
    /* JADX WARN: Code restructure failed: missing block: B:659:0x0982, code lost:

        if (r24 == 5) goto L663;
     */
    /* JADX WARN: Code restructure failed: missing block: B:660:0x0984, code lost:

        if (r3 == false) goto L662;
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x0986, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:662:0x098a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_6_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x098e, code lost:

        if (r3 == false) goto L665;
     */
    /* JADX WARN: Code restructure failed: missing block: B:664:0x0990, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:665:0x0994, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_5_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:666:0x0998, code lost:

        if (r3 == false) goto L668;
     */
    /* JADX WARN: Code restructure failed: missing block: B:667:0x099a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:668:0x099e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_lap_weight_4_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:670:0x09a8, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:677:0x09c0, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:680:0x09c8, code lost:

        if (r22.equals("BiNavi") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:683:0x09d0, code lost:

        if (r22.equals("BSC500") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:686:0x09d8, code lost:

        if (r22.equals("BiNavi Air") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:701:0x0a0b, code lost:

        if (r22.equals("FC09") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:704:0x0a13, code lost:

        if (r22.equals("FC08") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x0a1b, code lost:

        if (r22.equals("FC07") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x0a23, code lost:

        if (r22.equals("BG05") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:713:0x0a2b, code lost:

        if (r22.equals("BG04") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:715:0x0a2f, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.e_compass);
     */
    /* JADX WARN: Code restructure failed: missing block: B:716:0x0a3d, code lost:

        if (r24 == 4) goto L725;
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x0a40, code lost:

        if (r24 == 5) goto L722;
     */
    /* JADX WARN: Code restructure failed: missing block: B:719:0x0a42, code lost:

        if (r3 == false) goto L721;
     */
    /* JADX WARN: Code restructure failed: missing block: B:720:0x0a44, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_6_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:721:0x0a48, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_6_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:722:0x0a4c, code lost:

        if (r3 == false) goto L724;
     */
    /* JADX WARN: Code restructure failed: missing block: B:723:0x0a4e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_5_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:724:0x0a52, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_5_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:725:0x0a56, code lost:

        if (r3 == false) goto L727;
     */
    /* JADX WARN: Code restructure failed: missing block: B:726:0x0a58, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_4_cn_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:727:0x0a5c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_4_en_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:729:0x0a66, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:731:0x0a6b, code lost:

        if (r22.equals("BSC200 Pro") != false) goto L736;
     */
    /* JADX WARN: Code restructure failed: missing block: B:734:0x0a72, code lost:

        if (r22.equals("iGS800") == false) goto L751;
     */
    /* JADX WARN: Code restructure failed: missing block: B:736:0x0a75, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.e_compass);
     */
    /* JADX WARN: Code restructure failed: missing block: B:737:0x0a83, code lost:

        if (r24 == 4) goto L746;
     */
    /* JADX WARN: Code restructure failed: missing block: B:739:0x0a86, code lost:

        if (r24 == 5) goto L743;
     */
    /* JADX WARN: Code restructure failed: missing block: B:740:0x0a88, code lost:

        if (r3 == false) goto L742;
     */
    /* JADX WARN: Code restructure failed: missing block: B:741:0x0a8a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_6_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:742:0x0a8e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_6_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:743:0x0a92, code lost:

        if (r3 == false) goto L745;
     */
    /* JADX WARN: Code restructure failed: missing block: B:744:0x0a94, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_5_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:745:0x0a98, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_5_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:746:0x0a9c, code lost:

        if (r3 == false) goto L748;
     */
    /* JADX WARN: Code restructure failed: missing block: B:747:0x0a9e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_4_cn_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:748:0x0aa2, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_electronic_compass_weight_4_en_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:750:0x0aac, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:757:0x0ac5, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:760:0x0acd, code lost:

        if (r22.equals("BiNavi") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:763:0x0ad5, code lost:

        if (r22.equals("BSC500") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:811:0x0b6d, code lost:

        if (r22.equals("FC08") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:814:0x0b75, code lost:

        if (r22.equals("FC07") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:816:0x0b79, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map);
     */
    /* JADX WARN: Code restructure failed: missing block: B:817:0x0b84, code lost:

        if (r24 == 3) goto L825;
     */
    /* JADX WARN: Code restructure failed: missing block: B:819:0x0b87, code lost:

        if (r24 == 4) goto L824;
     */
    /* JADX WARN: Code restructure failed: missing block: B:821:0x0b8a, code lost:

        if (r24 == 5) goto L823;
     */
    /* JADX WARN: Code restructure failed: missing block: B:822:0x0b8c, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_6_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:823:0x0b90, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_5_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:824:0x0b94, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_4_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:825:0x0b98, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_3_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:827:0x0ba2, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:829:0x0ba7, code lost:

        if (r22.equals("BG05") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:832:0x0baf, code lost:

        if (r22.equals("BG04") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:834:0x0bb3, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map);
     */
    /* JADX WARN: Code restructure failed: missing block: B:835:0x0bbe, code lost:

        if (r24 == 3) goto L843;
     */
    /* JADX WARN: Code restructure failed: missing block: B:837:0x0bc1, code lost:

        if (r24 == 4) goto L842;
     */
    /* JADX WARN: Code restructure failed: missing block: B:839:0x0bc4, code lost:

        if (r24 == 5) goto L841;
     */
    /* JADX WARN: Code restructure failed: missing block: B:840:0x0bc6, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_6_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:841:0x0bca, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_5_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:842:0x0bce, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_4_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:843:0x0bd2, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_3_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:845:0x0bdc, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:847:0x0be1, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:849:0x0be4, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map);
     */
    /* JADX WARN: Code restructure failed: missing block: B:850:0x0bef, code lost:

        if (r24 == 3) goto L858;
     */
    /* JADX WARN: Code restructure failed: missing block: B:852:0x0bf2, code lost:

        if (r24 == 4) goto L857;
     */
    /* JADX WARN: Code restructure failed: missing block: B:854:0x0bf5, code lost:

        if (r24 == 5) goto L856;
     */
    /* JADX WARN: Code restructure failed: missing block: B:856:0x0bf8, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_5_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:857:0x0bfc, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_4_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:858:0x0c00, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_3_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:860:0x0c0a, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:862:0x0c0f, code lost:

        if (r22.equals("iGS800") == false) goto L876;
     */
    /* JADX WARN: Code restructure failed: missing block: B:864:0x0c12, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.map);
     */
    /* JADX WARN: Code restructure failed: missing block: B:865:0x0c1d, code lost:

        if (r24 == 3) goto L873;
     */
    /* JADX WARN: Code restructure failed: missing block: B:867:0x0c20, code lost:

        if (r24 == 4) goto L872;
     */
    /* JADX WARN: Code restructure failed: missing block: B:869:0x0c23, code lost:

        if (r24 == 5) goto L871;
     */
    /* JADX WARN: Code restructure failed: missing block: B:870:0x0c25, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_6_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:871:0x0c29, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_5_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:872:0x0c2d, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_4_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:873:0x0c31, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_map_weight_3_bg2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:875:0x0c3b, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:882:0x0c56, code lost:

        if (r22.equals("BiNavi Ultra") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:885:0x0c5e, code lost:

        if (r22.equals("BiNavi") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:888:0x0c66, code lost:

        if (r22.equals("BSC500") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:935:0x0cfa, code lost:

        if (r22.equals("FC08") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:938:0x0d02, code lost:

        if (r22.equals("FC07") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:940:0x0d06, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.page_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:941:0x0d11, code lost:

        if (r24 == 4) goto L949;
     */
    /* JADX WARN: Code restructure failed: missing block: B:943:0x0d14, code lost:

        if (r24 == 5) goto L948;
     */
    /* JADX WARN: Code restructure failed: missing block: B:945:0x0d17, code lost:

        if (r24 == 6) goto L947;
     */
    /* JADX WARN: Code restructure failed: missing block: B:947:0x0d1a, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_6_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:948:0x0d1e, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_5_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:949:0x0d22, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_4_fc07;
     */
    /* JADX WARN: Code restructure failed: missing block: B:951:0x0d2c, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:953:0x0d31, code lost:

        if (r22.equals("BG05") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:956:0x0d39, code lost:

        if (r22.equals("BG04") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:958:0x0d3d, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.page_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:959:0x0d48, code lost:

        if (r24 == 4) goto L967;
     */
    /* JADX WARN: Code restructure failed: missing block: B:961:0x0d4b, code lost:

        if (r24 == 5) goto L966;
     */
    /* JADX WARN: Code restructure failed: missing block: B:963:0x0d4e, code lost:

        if (r24 == 6) goto L965;
     */
    /* JADX WARN: Code restructure failed: missing block: B:965:0x0d51, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_6_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:966:0x0d55, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_5_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:967:0x0d59, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_4_bg4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:969:0x0d63, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:971:0x0d68, code lost:

        if (r22.equals("BSC200 Pro") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:973:0x0d6b, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.page_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:974:0x0d76, code lost:

        if (r24 == 3) goto L982;
     */
    /* JADX WARN: Code restructure failed: missing block: B:976:0x0d79, code lost:

        if (r24 == 4) goto L981;
     */
    /* JADX WARN: Code restructure failed: missing block: B:978:0x0d7c, code lost:

        if (r24 == 5) goto L980;
     */
    /* JADX WARN: Code restructure failed: missing block: B:980:0x0d7f, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_5_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:981:0x0d83, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_4_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:982:0x0d87, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_3_fc08;
     */
    /* JADX WARN: Code restructure failed: missing block: B:984:0x0d91, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:986:0x0d96, code lost:

        if (r22.equals("iGS800") == false) goto L999;
     */
    /* JADX WARN: Code restructure failed: missing block: B:988:0x0d99, code lost:

        r1 = com.blankj.utilcode.util.Utils.getApp().getString(com.igpsport.igpsportandroid.R.string.page_climbing);
     */
    /* JADX WARN: Code restructure failed: missing block: B:989:0x0da4, code lost:

        if (r24 == 4) goto L996;
     */
    /* JADX WARN: Code restructure failed: missing block: B:991:0x0da7, code lost:

        if (r24 == 5) goto L995;
     */
    /* JADX WARN: Code restructure failed: missing block: B:993:0x0daa, code lost:

        if (r24 == 6) goto L995;
     */
    /* JADX WARN: Code restructure failed: missing block: B:995:0x0dad, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_5_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:996:0x0db1, code lost:

        r2 = com.igpsport.igpsportandroid.R.mipmap.icon_elevation_weight_4_bg1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:998:0x0dbb, code lost:

        return new kotlin.Pair<>(r1, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<java.lang.String, java.lang.Integer> getBG1PicturePageNameAndChartImgResId(java.lang.String r22, com.igpsport.blelib.bean.Config.PAGE_TYPE r23, int r24) {
        /*
            Method dump skipped, instruction units count: 4122
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil.getBG1PicturePageNameAndChartImgResId(java.lang.String, com.igpsport.blelib.bean.Config$PAGE_TYPE, int):kotlin.Pair");
    }

    public final int getMaxDataFieldSize(String deviceName) {
        String str;
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        switch (deviceName.hashCode()) {
            case -1220808445:
                return !deviceName.equals("iGS800") ? 10 : 12;
            case -425573171:
                str = "BSC200 Pro";
                break;
            case 2035977:
                return !deviceName.equals("BG04") ? 10 : 12;
            case 2035978:
                return !deviceName.equals("BG05") ? 10 : 12;
            case 2151300:
                return !deviceName.equals("FC07") ? 10 : 12;
            case 2151301:
                str = "FC08";
                break;
            case 2151302:
                str = "FC09";
                break;
            case 809587253:
                str = "iGS630S";
                break;
            case 1275845431:
                return !deviceName.equals("BiNavi Air") ? 10 : 12;
            case 1968224675:
                return !deviceName.equals("BSC500") ? 10 : 12;
            case 1988914349:
                return !deviceName.equals("BiNavi") ? 10 : 12;
            case 2040345177:
                return !deviceName.equals("BiNavi Ultra") ? 10 : 12;
            default:
                return 10;
        }
        deviceName.equals(str);
        return 10;
    }

    private final List<Field> getGraphicalDataFieldList(List<CategoryItem> dataList) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((Field) obj).isGraph()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public final boolean isGraphicalDataField(List<CategoryItem> dataList, int value) {
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        List<Field> graphicalDataFieldList = getGraphicalDataFieldList(dataList);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(graphicalDataFieldList, 10));
        Iterator<T> it2 = graphicalDataFieldList.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(((Field) it2.next()).getDecValue()));
        }
        return arrayList.contains(Integer.valueOf(value));
    }

    private final Map<Integer, String> getAllPageDataFieldList(List<CategoryItem> dataList) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        ArrayList<Field> arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Field field : arrayList2) {
            Pair pair = TuplesKt.to(Integer.valueOf(field.getDecValue()), field.getName());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public final String getDataFieldResId(List<CategoryItem> dataList, int value) {
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        return getAllPageDataFieldList(dataList).get(Integer.valueOf(value));
    }

    public final List<Config.page_msg> getDefaultPageMsgList(String deviceName) {
        Object objM20711constructorimpl;
        String str;
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        try {
            Result.Companion companion = Result.INSTANCE;
            if (Intrinsics.areEqual(deviceName, "iGS800")) {
                str = "CAEQARiDDhiBChiKFhiBAhiCAhiEIBiBEDABOIoJQAFIkQVQBA==,CAIQARiEChiEBhiFAhiECBiEBBiGDjABOIkJQABI0gRQAA==,CAMQARiIFhiCFhiJCBiQCBiBBBiBChiFDBiCDDABOJIJQABI0gRQAw==,CAQQARiBEBiJCBiPCBiBBhiIBhiSFhiBIBiCIBiBEhiDIDABOJHFBEAASMmkAlAg,CAUQABiBChiDDhiEAhiCAhiDBhiCBhiDBBiCBBiDCBiCCBiBEhiBCjABOJLJBEAASMmkAlAA,CAYQARiBAiAAMAM4AUAASAFQAA==,CAcQARiBAhiFDBiCDBiBDCAAMAI4EkAASAlQAA==,CAgQABiBAhiBBBiJCCAAMAY4SUAASElQAA==,CAkQABiBAiAAMAU4AUAASAJQAA==,CAoQARiBAiAAMAg4AUAASAFQAA==,CAsQABiHEBiBAiAAMAc4AkAASAFQAA==,CAwQABiBAiAAMAo4AUAASAFQAA==,CA0QABiEHhiDHiAAMAw4AkAASAFQAA==,CA4QARiDICAAMA04AUAASAFQAA==";
            } else {
                if (!Intrinsics.areEqual(deviceName, "BiNavi")) {
                    return null;
                }
                str = "CAEQARiDDhiBChiKFhiBAhiCAhiEIBiBEDABOMlIQAFIiSVQBA==,CAIQARiEChiEBhiFAhiECBiEBBiGDjABOMlEQABIiSVQAA==,CAMQARiIFhiCFhiJCBiQCBiBBBiBChiFDBiCDDABOJIJQABI0gRQAw==,CAQQARiBEBiJCBiPCBiBBhiIBhiSFhiBIBiCIBiBEhiDIDABOJHFBEAASMmkAlAg,CAUQABiBChiDDhiEAhiCAhiDBhiCBhiDBBiCBBiDCBiCCBiBEhiBCjABOJLJBEAASMmkAlAA,CAYQARiBAhiHECAAMAM4AkAASAFQAA==,CAcQACAAMA84AEAASABQAA==,CAgQARiBAhiFDBiCDBiBDCAAMAI4EkAASAlQAA==,CAkQABiBAhiBBBiJCCAAMAY4SUAASElQAA==,CAoQABiBAhiBBBiBChiDDiAAMAU4EkAASAlQAA==,CAsQARiBAiAAMAg4AUAASAFQAA==,CAwQABiHEBiBAiAAMAc4AkAASAFQAA==,CA0QABiCDBiBAiAAMA44AkAASAFQAA==,CA4QABiCDBiBAiAAMAo4AkAASAFQAA==,CA8QABiEHhiDHhiCAiAAMAw4EUAASAlQAA==,CBAQARiDIBiBDiAAMA04AkAASAFQAA==,CBEQASAAMBA4AEAASABQAA==";
            }
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it2 = listSplit$default.iterator();
            while (it2.hasNext()) {
                arrayList.add(Config.page_msg.parseFrom(Base64.decode((String) it2.next(), 2)));
            }
            objM20711constructorimpl = Result.m20711constructorimpl(arrayList);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM20711constructorimpl = Result.m20711constructorimpl(ResultKt.createFailure(th));
        }
        return (List) (Result.m20717isFailureimpl(objM20711constructorimpl) ? null : objM20711constructorimpl);
    }

    public final List<PageData> getDefaultPicturePageDataList(Config.PAGE_TYPE pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        switch (WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()]) {
            case 1:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.total_ascent, 1541), new PageData(R.string.grade, 1538), new PageData(R.string.elevation, GarminProduct.FR910XT_CHINA)});
            case 2:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.heading, GLMapStaticValue.MAP_PARAMETERNAME_POLYGON_FILL_CONTROL), new PageData(R.string.avg_speed, 258), new PageData(R.string.distance, 1281), new PageData(R.string.heart_rate1, 769), new PageData(R.string.elevation, GarminProduct.FR910XT_CHINA)});
            case 3:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.heading, GLMapStaticValue.MAP_PARAMETERNAME_POLYGON_FILL_CONTROL), new PageData(R.string.speed, 257), new PageData(R.string.distance, 1281), new PageData(R.string.record_time, 1795)});
            case 4:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.cadence, InputDeviceCompat.SOURCE_DPAD), new PageData(R.string.avg_speed, 258), new PageData(R.string.max_speed1, Manufacturer.ZWIFT)});
            case 5:
            default:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.heading, GLMapStaticValue.MAP_PARAMETERNAME_POLYGON_FILL_CONTROL), new PageData(R.string.avg_speed, 258), new PageData(R.string.distance, 1281), new PageData(R.string.heart_rate1, 769), new PageData(R.string.elevation, GarminProduct.FR910XT_CHINA)});
            case 6:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.grade, 1538), new PageData(R.string.speed, 257), new PageData(R.string.elevation, GarminProduct.FR910XT_CHINA), new PageData(R.string.vertical_spd, 1539)});
            case 7:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.cadence, InputDeviceCompat.SOURCE_DPAD), new PageData(R.string.pwr3s, AnalyticsListener.EVENT_RENDERER_READY_CHANGED), new PageData(R.string.record_time, 1795), new PageData(R.string.distance, 1281), new PageData(R.string.avg_speed, 258)});
            case 8:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.speed, 257), new PageData(R.string.cadence, InputDeviceCompat.SOURCE_DPAD), new PageData(R.string.distance, 1281), new PageData(R.string.record_time, 1795)});
            case 9:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.dist_ramain, 3844), new PageData(R.string.behind_time, 3843), new PageData(R.string.avg_speed, 258), new PageData(R.string.max_speed1, Manufacturer.ZWIFT)});
            case 10:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.training_load, 4099), new PageData(R.string.elapsed_time, 1793)});
            case 11:
                return CollectionsKt.listOf((Object[]) new PageData[]{new PageData(R.string.grade, 1538), new PageData(R.string.speed, 257), new PageData(R.string.elevation, GarminProduct.FR910XT_CHINA), new PageData(R.string.vertical_spd, 1539)});
        }
    }

    public final Pair<List<Integer>, List<Integer>> getPageItemDataHeightListAndSpanSizeList(String deviceName, int pageMode, int lineWidth) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Integer num = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        Intrinsics.checkNotNull(num);
        String binaryString = StringUtils.toBinaryString(pageMode, num.intValue() * 3);
        Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(...)");
        List listReversed = CollectionsKt.reversed(StringsKt.chunked(binaryString, 3));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listReversed, 10));
        Iterator it2 = listReversed.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(Integer.parseInt((String) it2.next(), 2)));
        }
        ArrayList arrayList2 = arrayList;
        Integer num2 = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        Intrinsics.checkNotNull(num2);
        String binaryString2 = StringUtils.toBinaryString(lineWidth, num2.intValue() * 3);
        Intrinsics.checkNotNullExpressionValue(binaryString2, "toBinaryString(...)");
        List listReversed2 = CollectionsKt.reversed(StringsKt.chunked(binaryString2, 3));
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listReversed2, 10));
        Iterator it3 = listReversed2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Integer.valueOf(Integer.parseInt((String) it3.next(), 2)));
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        int i = 0;
        for (Object obj : arrayList2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((Number) obj).intValue() == 1) {
                arrayList5.add(2);
                arrayList6.add(arrayList4.get(i));
            } else {
                for (int i3 = 0; i3 < 2; i3++) {
                    arrayList5.add(1);
                    arrayList6.add(arrayList4.get(i));
                }
            }
            i = i2;
        }
        LogUtils.d("获取HeightListAndSpanSizeList->pageMode = " + pageMode + ", lineWidth = " + lineWidth + ", dataFieldNumPerRowList = " + arrayList2 + ", spanSizeList = " + arrayList5 + ", heightPerRowList = " + arrayList4 + ", heightWeightList = " + arrayList6);
        return new Pair<>(arrayList6, arrayList5);
    }

    public final int getDataFieldNum(String deviceName, int pageMode, int lineWidth) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Integer num = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        Intrinsics.checkNotNull(num);
        String binaryString = StringUtils.toBinaryString(pageMode, num.intValue() * 3);
        Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(...)");
        List listReversed = CollectionsKt.reversed(StringsKt.chunked(binaryString, 3));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listReversed, 10));
        Iterator it2 = listReversed.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(Integer.parseInt((String) it2.next(), 2)));
        }
        ArrayList arrayList2 = arrayList;
        Integer num2 = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        Intrinsics.checkNotNull(num2);
        String binaryString2 = StringUtils.toBinaryString(lineWidth, num2.intValue() * 3);
        Intrinsics.checkNotNullExpressionValue(binaryString2, "toBinaryString(...)");
        List listReversed2 = CollectionsKt.reversed(StringsKt.chunked(binaryString2, 3));
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listReversed2, 10));
        Iterator it3 = listReversed2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Integer.valueOf(Integer.parseInt((String) it3.next(), 2)));
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList3) {
            if (((Number) obj).intValue() > 0) {
                arrayList4.add(obj);
            }
        }
        int size = arrayList4.size();
        ArrayList arrayList5 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList5.add(Integer.valueOf(((Number) arrayList2.get(i)).intValue()));
        }
        int iSumOfInt = CollectionsKt.sumOfInt(arrayList5);
        LogUtils.d("根据pageMode、lineWidth 计算数据的数量-> dataNumber = " + iSumOfInt);
        return iSumOfInt;
    }

    public final int getDataHeightWeight(String deviceName, int lineWidth) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Integer num = DEVICE_TOTAL_ROWS_NUM_MAP.get(deviceName);
        Intrinsics.checkNotNull(num);
        String binaryString = StringUtils.toBinaryString(lineWidth, num.intValue() * 3);
        Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(...)");
        Iterator it2 = CollectionsKt.reversed(StringsKt.chunked(binaryString, 3)).iterator();
        int i = 0;
        while (it2.hasNext()) {
            i += Integer.parseInt((String) it2.next(), 2);
        }
        return i;
    }

    public final String getDefaultDataValue(List<CategoryItem> dataList, int value) {
        Object next;
        String defaultValue;
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        Iterator it3 = arrayList.iterator();
        do {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (((Field) next).getDecValue() != value);
        Field field = (Field) next;
        return (field == null || (defaultValue = field.getDefaultValue()) == null) ? "--" : defaultValue;
    }

    public final String getDefaultDataName(List<CategoryItem> dataList, int value) {
        Object next;
        String name;
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        Iterator it3 = arrayList.iterator();
        do {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (((Field) next).getDecValue() != value);
        Field field = (Field) next;
        return (field == null || (name = field.getName()) == null) ? "" : name;
    }

    public final Pair<String, Integer> getGraphicalDataReplacedTextData(List<CategoryItem> dataList, int value) {
        Object obj;
        Object next;
        String name;
        Integer relatedTextFieldValue;
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = dataList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList, ((CategoryItem) it2.next()).getFields());
        }
        ArrayList arrayList2 = arrayList;
        Iterator it3 = arrayList2.iterator();
        do {
            obj = null;
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (((Field) next).getDecValue() != value);
        Field field = (Field) next;
        for (Object obj2 : arrayList2) {
            Field field2 = (Field) obj2;
            if (field != null) {
                int decValue = field2.getDecValue();
                Integer relatedTextFieldValue2 = field.getRelatedTextFieldValue();
                if (relatedTextFieldValue2 != null && decValue == relatedTextFieldValue2.intValue()) {
                    obj = obj2;
                    break;
                }
            }
        }
        Field field3 = (Field) obj;
        if (field3 == null || (name = field3.getName()) == null) {
            name = "";
        }
        return new Pair<>(name, Integer.valueOf((field == null || (relatedTextFieldValue = field.getRelatedTextFieldValue()) == null) ? 0 : relatedTextFieldValue.intValue()));
    }
}
