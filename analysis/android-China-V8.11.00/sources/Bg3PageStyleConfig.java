package com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style;

import com.igpsport.blelib.bean.Config;
import com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PageShowTypeBean;
import com.igpsport.globalapp.dialog.ConnectDeviceBottomDialog;
import com.igpsport.igpsportandroid.R;
import com.tencent.connect.common.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: Bg3PageStyleConfig.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/style/Bg3PageStyleConfig;", "", "<init>", "()V", "Companion", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Bg3PageStyleConfig {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IGS_BG2 = "BiNavi";
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids1;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids10;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids11;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids12;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids2;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids3;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids4;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids5;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids6;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids7;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids8;
    private static final List<Bg3PageShowTypeBean> bg1PageStyleForGrids9;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForAltitudeMeter;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForClimb;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForClimbMap;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForCompass;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForLap;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForMap;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForRidingPlatform;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForSegment;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForSportCamera;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForTrainingCourses;
    private static final List<Bg3PageShowTypeBean> bg1PicturePageStyleForTrainingEffect;
    private static final List<Bg3PageShowTypeBean> bg2PageStyleForGrids5;
    private static final List<Bg3PageShowTypeBean> bg2PageStyleForGrids7;
    private static final List<Bg3PageShowTypeBean> bg2PageStyleForGrids9;
    private static final List<Bg3PageShowTypeBean> bg2PicturePageStyleForClimb;
    private static final List<Bg3PageShowTypeBean> bg2PicturePageStyleForClimbMap;
    private static final List<Bg3PageShowTypeBean> bg2PicturePageStyleForMap;
    private static final List<Bg3PageShowTypeBean> bg2PicturePageStyleForMusicControl;
    private static final List<Bg3PageShowTypeBean> bg2PicturePageStyleForTeamRiding;

    /* JADX INFO: compiled from: Bg3PageStyleConfig.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0005J\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010*\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/style/Bg3PageStyleConfig$Companion;", "", "<init>", "()V", "IGS_BG2", "", "bg1PageStyleForGrids1", "", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageShowTypeBean;", "bg1PageStyleForGrids2", "bg1PageStyleForGrids3", "bg1PageStyleForGrids4", "bg1PageStyleForGrids5", "bg2PageStyleForGrids5", "bg1PageStyleForGrids6", "bg1PageStyleForGrids7", "bg2PageStyleForGrids7", "bg1PageStyleForGrids8", "bg1PageStyleForGrids9", "bg2PageStyleForGrids9", "bg1PageStyleForGrids10", "bg1PageStyleForGrids11", "bg1PageStyleForGrids12", "bg1PicturePageStyleForTrainingCourses", "bg1PicturePageStyleForAltitudeMeter", "bg1PicturePageStyleForMap", "bg2PicturePageStyleForMap", "bg1PicturePageStyleForCompass", "bg1PicturePageStyleForRidingPlatform", "bg1PicturePageStyleForClimb", "bg2PicturePageStyleForClimb", "bg1PicturePageStyleForTrainingEffect", "bg1PicturePageStyleForLap", "bg1PicturePageStyleForSegment", "bg1PicturePageStyleForClimbMap", "bg2PicturePageStyleForClimbMap", "bg2PicturePageStyleForTeamRiding", "bg2PicturePageStyleForMusicControl", "bg1PicturePageStyleForSportCamera", "getBg1PageStyleForGrids", "pageDataSize", "", ConnectDeviceBottomDialog.DEVICE_NAME_ARG, "getBg1PicturePageStyleForGrids", "pageType", "Lcom/igpsport/blelib/bean/Config$PAGE_TYPE;", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: Bg3PageStyleConfig.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Config.PAGE_TYPE.values().length];
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_COURSE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_ELEVATION.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_MAP.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_COMPASS.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_FEC.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_FITNESS.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_LAP.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_SEGMENT.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB_MAP.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_GROUP_RIDE_INFOR.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_MUSIC.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CAMERA.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BiNaviUltra) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:

            if (r8.equals("BiNavi") == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BSC500) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG3) != false) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.FC07) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0059, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG5) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0060, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG4) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0067, code lost:

            return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.bg2PageStyleForGrids9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x007e, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BiNaviUltra) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0085, code lost:

            if (r8.equals("BiNavi") == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008c, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BSC500) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0093, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG3) != false) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.FC07) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00a1, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG5) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00a8, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG4) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00af, code lost:

            return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.bg2PageStyleForGrids7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x00c6, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BiNaviUltra) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x00cd, code lost:

            if (r8.equals("BiNavi") == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x00d4, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.BSC500) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x00db, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG3) != false) goto L96;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x00e2, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.FC07) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x00e9, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG5) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x00f0, code lost:

            if (r8.equals(com.igpsport.blelib.config.DeviceConstant.iGSBG4) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x00f7, code lost:

            return com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.bg2PageStyleForGrids5;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PageShowTypeBean> getBg1PageStyleForGrids(int r7, java.lang.String r8) {
            /*
                Method dump skipped, instruction units count: 392
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.style.Bg3PageStyleConfig.Companion.getBg1PageStyleForGrids(int, java.lang.String):java.util.List");
        }

        public final List<Bg3PageShowTypeBean> getBg1PicturePageStyleForGrids(Config.PAGE_TYPE pageType, String deviceName) {
            Intrinsics.checkNotNullParameter(pageType, "pageType");
            Intrinsics.checkNotNullParameter(deviceName, "deviceName");
            switch (WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()]) {
                case 1:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForTrainingCourses;
                case 2:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForAltitudeMeter;
                case 3:
                    return Bg3PageStyleConfig.bg2PicturePageStyleForMap;
                case 4:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForCompass;
                case 5:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForRidingPlatform;
                case 6:
                    return Intrinsics.areEqual(deviceName, "BiNavi") ? Bg3PageStyleConfig.bg2PicturePageStyleForClimb : Bg3PageStyleConfig.bg1PicturePageStyleForClimb;
                case 7:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForTrainingEffect;
                case 8:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForLap;
                case 9:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForSegment;
                case 10:
                    return Intrinsics.areEqual(deviceName, "BiNavi") ? Bg3PageStyleConfig.bg2PicturePageStyleForClimbMap : Bg3PageStyleConfig.bg1PicturePageStyleForClimbMap;
                case 11:
                    return Bg3PageStyleConfig.bg2PicturePageStyleForTeamRiding;
                case 12:
                    return Bg3PageStyleConfig.bg2PicturePageStyleForMusicControl;
                case 13:
                    return Bg3PageStyleConfig.bg1PicturePageStyleForSportCamera;
                default:
                    return CollectionsKt.emptyList();
            }
        }
    }

    static {
        int i = 8;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Integer num = null;
        boolean z = false;
        bg1PageStyleForGrids1 = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("110", CharsKt.checkRadix(2)), num, z, i, defaultConstructorMarker));
        bg1PageStyleForGrids2 = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.parseInt("11011", CharsKt.checkRadix(2)), num, z, i, defaultConstructorMarker));
        int i2 = 8;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        boolean z2 = false;
        bg1PageStyleForGrids3 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.parseInt("10010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_3_a), z, i, defaultConstructorMarker), new Bg3PageShowTypeBean(Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.parseInt("1010011", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_3_b), z2, i2, defaultConstructorMarker2)});
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        bg1PageStyleForGrids4 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_4_a), z2, i2, defaultConstructorMarker2), new Bg3PageShowTypeBean(Integer.parseInt("1001010", CharsKt.checkRadix(2)), Integer.parseInt("10010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_4_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1010001", CharsKt.checkRadix(2)), Integer.parseInt("1010011", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_4_c), false, 8, defaultConstructorMarker3)});
        DefaultConstructorMarker defaultConstructorMarker4 = null;
        bg1PageStyleForGrids5 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_5_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001001001", CharsKt.checkRadix(2)), Integer.parseInt("1010010001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_5_b), false, 8, defaultConstructorMarker4)});
        bg2PageStyleForGrids5 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_5_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("010010001", CharsKt.checkRadix(2)), Integer.parseInt("010010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg2_page_mode_5_b), false, 8, defaultConstructorMarker4)});
        bg1PageStyleForGrids6 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001001001001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_6_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_6_b), false, 8, defaultConstructorMarker4), new Bg3PageShowTypeBean(Integer.parseInt("10010001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_6_c), false, 8, defaultConstructorMarker3)});
        DefaultConstructorMarker defaultConstructorMarker5 = null;
        bg1PageStyleForGrids7 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("10010001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_7_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010001010", CharsKt.checkRadix(2)), Integer.parseInt("1010010001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_7_b), false, 8, defaultConstructorMarker5)});
        bg2PageStyleForGrids7 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("10010001001001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_7_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("010010010001", CharsKt.checkRadix(2)), Integer.parseInt("001001010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg2_page_mode_7_b), false, 8, defaultConstructorMarker5)});
        bg1PageStyleForGrids8 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("10010001010001", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_8_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010010010", CharsKt.checkRadix(2)), Integer.parseInt("1001010010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_8_b), false, 8, defaultConstructorMarker5)});
        bg1PageStyleForGrids9 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("10010001010010", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_9_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010001010010", CharsKt.checkRadix(2)), Integer.parseInt("1001001001010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_9_b), false, 8, defaultConstructorMarker5)});
        bg2PageStyleForGrids9 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("010010010010001", CharsKt.checkRadix(2)), Integer.parseInt("001001001001010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg2_page_mode_9_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010001010010", CharsKt.checkRadix(2)), Integer.parseInt("1001001001010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_9_b), false, 8, defaultConstructorMarker5)});
        bg1PageStyleForGrids10 = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("10010001010010001", CharsKt.checkRadix(2)), Integer.parseInt("1001001001001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_10_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010010010010", CharsKt.checkRadix(2)), Integer.parseInt("1001010001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_icon_bg1_page_mode_10_b), false, 8, defaultConstructorMarker5)});
        int i3 = 8;
        DefaultConstructorMarker defaultConstructorMarker6 = null;
        Integer num2 = null;
        boolean z3 = false;
        bg1PageStyleForGrids11 = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("10010010010010001", CharsKt.checkRadix(2)), Integer.parseInt("1001001001001001", CharsKt.checkRadix(2)), num2, z3, i3, defaultConstructorMarker6));
        bg1PageStyleForGrids12 = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("10010010010010010", CharsKt.checkRadix(2)), Integer.parseInt("1001001001001001", CharsKt.checkRadix(2)), num2, z3, i3, defaultConstructorMarker6));
        bg1PicturePageStyleForTrainingCourses = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_training_courses_a), z3, i3, defaultConstructorMarker6), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_training_courses_b), false, 8, defaultConstructorMarker5), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_training_courses_c), false, 8, defaultConstructorMarker3)});
        int i4 = 8;
        DefaultConstructorMarker defaultConstructorMarker7 = null;
        boolean z4 = false;
        int i5 = 8;
        DefaultConstructorMarker defaultConstructorMarker8 = null;
        boolean z5 = false;
        int i6 = Integer.parseInt("10001", CharsKt.checkRadix(2));
        int i7 = Integer.parseInt("1001", CharsKt.checkRadix(2));
        Integer numValueOf = Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_e);
        int i8 = Integer.parseInt("10010", CharsKt.checkRadix(2));
        int i9 = Integer.parseInt("1001", CharsKt.checkRadix(2));
        Integer numValueOf2 = Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_f);
        List<Bg3PageShowTypeBean> listListOf = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_a), z4, i4, defaultConstructorMarker7), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_b), z5, i5, defaultConstructorMarker8), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_c), z4, i4, defaultConstructorMarker7), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_d), z5, i5, defaultConstructorMarker8), new Bg3PageShowTypeBean(i6, i7, numValueOf, z4, i4, defaultConstructorMarker7), new Bg3PageShowTypeBean(i8, i9, numValueOf2, false, 8, null)});
        bg1PicturePageStyleForAltitudeMeter = listListOf;
        DefaultConstructorMarker defaultConstructorMarker9 = null;
        bg1PicturePageStyleForMap = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_d), false, 8, defaultConstructorMarker9)});
        DefaultConstructorMarker defaultConstructorMarker10 = null;
        int i10 = 8;
        DefaultConstructorMarker defaultConstructorMarker11 = null;
        boolean z6 = false;
        DefaultConstructorMarker defaultConstructorMarker12 = null;
        bg2PicturePageStyleForMap = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_c), false, 8, defaultConstructorMarker9), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_altitude_d), false, 8, defaultConstructorMarker10), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf, z6, i10, defaultConstructorMarker11), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf2, false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010001", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_d), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010010", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_e), false, 8, defaultConstructorMarker12)});
        bg1PicturePageStyleForCompass = listListOf;
        bg1PicturePageStyleForRidingPlatform = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001001", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_b), false, 8, defaultConstructorMarker12), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1010", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_c), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010001", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_d), false, 8, defaultConstructorMarker10), new Bg3PageShowTypeBean(Integer.parseInt("10010010", CharsKt.checkRadix(2)), Integer.parseInt("1001001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_fec_e), false, 8, null)});
        int i11 = Integer.parseInt("0", CharsKt.checkRadix(2));
        int i12 = Integer.parseInt("0", CharsKt.checkRadix(2));
        Integer numValueOf3 = Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_a);
        DefaultConstructorMarker defaultConstructorMarker13 = null;
        int i13 = 8;
        DefaultConstructorMarker defaultConstructorMarker14 = null;
        boolean z7 = false;
        bg1PicturePageStyleForClimb = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(i11, i12, numValueOf3, false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_b), false, 8, defaultConstructorMarker13), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf, z6, i10, defaultConstructorMarker11), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf2, z7, i13, defaultConstructorMarker14)});
        bg2PicturePageStyleForClimb = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_b), false, 8, defaultConstructorMarker13), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf, z6, i10, defaultConstructorMarker11), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), numValueOf2, z7, i13, defaultConstructorMarker14)});
        DefaultConstructorMarker defaultConstructorMarker15 = null;
        bg1PicturePageStyleForTrainingEffect = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_a), z6, i10, defaultConstructorMarker11), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_climb_b), false, 8, defaultConstructorMarker15)});
        int i14 = 8;
        DefaultConstructorMarker defaultConstructorMarker16 = null;
        boolean z8 = false;
        DefaultConstructorMarker defaultConstructorMarker17 = null;
        bg1PicturePageStyleForLap = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), numValueOf3, z8, i14, defaultConstructorMarker16), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_c), false, 8, defaultConstructorMarker15), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_d), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_e), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_lap_f), false, 8, defaultConstructorMarker17)});
        bg1PicturePageStyleForSegment = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_c), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_d), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_e), false, 8, defaultConstructorMarker17)});
        DefaultConstructorMarker defaultConstructorMarker18 = null;
        bg1PicturePageStyleForClimbMap = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), numValueOf3, z8, i14, defaultConstructorMarker16), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_c), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_d), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_e), false, 8, defaultConstructorMarker18)});
        bg2PicturePageStyleForClimbMap = CollectionsKt.listOf((Object[]) new Bg3PageShowTypeBean[]{new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_a), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_b), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, CharsKt.checkRadix(2)), Integer.parseInt("1", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_c), false, 8, null), new Bg3PageShowTypeBean(Integer.parseInt("10001", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_d), false, 8, defaultConstructorMarker18), new Bg3PageShowTypeBean(Integer.parseInt("10010", CharsKt.checkRadix(2)), Integer.parseInt("1001", CharsKt.checkRadix(2)), Integer.valueOf(R.drawable.bg_bg1_picture_page_style_segment_e), false, 8, null)});
        bg2PicturePageStyleForTeamRiding = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), numValueOf3, z8, i14, defaultConstructorMarker16));
        bg2PicturePageStyleForMusicControl = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), numValueOf3, z8, i14, defaultConstructorMarker16));
        bg1PicturePageStyleForSportCamera = CollectionsKt.listOf(new Bg3PageShowTypeBean(Integer.parseInt("0", CharsKt.checkRadix(2)), Integer.parseInt("0", CharsKt.checkRadix(2)), numValueOf3, z8, i14, defaultConstructorMarker16));
    }
}
