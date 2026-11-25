package com.igpsport.blelib.devicemanager;

import com.google.protobuf.InvalidProtocolBufferException;
import com.igpsport.blelib.BaseCommand;
import com.igpsport.blelib.DeviceReturnStatus;
import com.igpsport.blelib.IGPDeviceManager;
import com.igpsport.blelib.WriteCommand;
import com.igpsport.blelib.bean.Common;
import com.igpsport.blelib.bean.Config;
import com.igpsport.blelib.config.DeviceConstant;
import com.igpsport.blelib.device.IGPDevice;
import com.igpsport.blelib.pbfactory.ComputerConfigureServiceFactory;
import com.igpsport.blelib.pbfactory.Head20BytesInfo;
import com.igpsport.blelib.pbfactory.TransmitCompleteCommand;
import com.igpsport.blelib.utils.StringUtils;
import com.igpsport.globalapp.util.CommonUtils;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigurationDelegate.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 R2\u00020\u0001:\u0001RB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000eJ\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\nJ%\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00150\nJ1\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0011\"\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001aJ/\u0010\u001b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0015\u0012\u0004\u0012\u00020\u001d0\u001c0\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001eJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\nJ\u001d\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001eJ%\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020#0\u0011\"\u00020#¢\u0006\u0002\u0010$J%\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020#0\u0011\"\u00020#¢\u0006\u0002\u0010$J\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00150\nJ#\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u00150\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001eJ1\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020(0\u0011\"\u00020(2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010+J#\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00150\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001eJ1\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0\u0011\"\u00020-2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010/J\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\nJ\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u00103\u001a\u000204J\u0014\u00105\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010\u00150\nJ\u0014\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010\u00150\nJ\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u00109\u001a\u000206J\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u00109\u001a\u000206J\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u00109\u001a\u000206J%\u0010<\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020#0\u0011\"\u00020#¢\u0006\u0002\u0010$J\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010>0\nJ\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010@\u001a\u00020>J1\u0010A\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020B0\u0011\"\u00020B2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010CJ%\u0010D\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020E0\u0011\"\u00020E¢\u0006\u0002\u0010FJ\u0012\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0\u00150\nJ#\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u00150\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001eJ\u0014\u0010I\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020J\u0018\u00010\u00150\nJ\u001e\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\fJ\u000e\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\nJ\u0016\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\u0006\u0010Q\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate;", "", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "<init>", "(Lcom/igpsport/blelib/IGPDeviceManager;)V", "iGPDeviceType", "Lcom/igpsport/blelib/device/IGPDevice;", "firstQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/igpsport/blelib/BaseCommand;", "writeUserInformation", "", "config", "Lcom/igpsport/blelib/bean/Config$user_data_msg;", "readUserInformation", "writeUnit", "", "Lcom/igpsport/blelib/bean/Config$unit_msg;", "([Lcom/igpsport/blelib/bean/Config$unit_msg;)Lcom/igpsport/blelib/BaseCommand;", "readUnit", "", "writePage", "Lcom/igpsport/blelib/bean/Config$page_msg;", "curOperateMode", "", "([Lcom/igpsport/blelib/bean/Config$page_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readPage", "Lkotlin/Pair;", "Lcom/igpsport/blelib/bean/Config$cur_page_status_msg;", "(Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readPageInformation", "resetPageToDefault", "Lcom/igpsport/blelib/DeviceReturnStatus;", "addBikeInformation", "Lcom/igpsport/blelib/bean/Config$bike_msg;", "([Lcom/igpsport/blelib/bean/Config$bike_msg;)Lcom/igpsport/blelib/BaseCommand;", "deleteBikeInformation", "readBikeInformation", "readAlarmConfig", "Lcom/igpsport/blelib/bean/Config$alarm_msg;", "writeAlarmConfig", "configs", "([Lcom/igpsport/blelib/bean/Config$alarm_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readLapConfig", "Lcom/igpsport/blelib/bean/Config$lap_msg;", "writeLapConfig", "([Lcom/igpsport/blelib/bean/Config$lap_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readSoundSetting", "Lcom/igpsport/blelib/bean/Config$config_msg;", "writeSoundSetting", "soundSetMsg", "Lcom/igpsport/blelib/bean/Config$sound_set_msg;", "readAllRidingMode", "Lcom/igpsport/blelib/bean/Config$mode_msg;", "readRidingModeTemplate", "modifyRidingMode", "mode", "addRidingMode", "deleteRidingMode", "writeBikeInformation", "readDisplaySettings", "Lcom/igpsport/blelib/bean/Config$backlight_msg;", "writeDisplaySettings", "displaySettings", "writeAutomaticSetting", "Lcom/igpsport/blelib/bean/Config$auto_set_msg;", "([Lcom/igpsport/blelib/bean/Config$auto_set_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "writeKeySetting", "Lcom/igpsport/blelib/bean/Config$key_set_msg;", "([Lcom/igpsport/blelib/bean/Config$key_set_msg;)Lcom/igpsport/blelib/BaseCommand;", "readKeySetting", "readAutomaticSetting", "readDataProcessingSettings", "Lcom/igpsport/blelib/bean/Config$data_msg;", "dataProcessingFilter0Value", "dataType", "Lcom/igpsport/blelib/bean/Config$DATA_TYPE;", "isFilter", "readAltitude", "writeAltitude", CommonUtils.TYPE_UNIT_ALTITUDE, "Companion", "blelib_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ConfigurationDelegate {
    public static final Companion Companion = null;
    private final IGPDeviceManager deviceManager;
    private final ConcurrentLinkedQueue<BaseCommand<?>> firstQueue;
    private final IGPDevice iGPDeviceType;

    /* JADX INFO: compiled from: ConfigurationDelegate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate$Companion;", "", "<init>", "()V", "create", "Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate;", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "blelib_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private Companion() {
        }

        public final ConfigurationDelegate create(IGPDeviceManager r3) {
            Intrinsics.checkNotNullParameter(r3, "deviceManager");
            return new ConfigurationDelegate(r3, null);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-YHhoQN7zsNK8tN00gkFsFsuiUg, reason: not valid java name */
    public static /* synthetic */ boolean m11339$r8$lambda$YHhoQN7zsNK8tN00gkFsFsuiUg(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return addRidingMode$lambda$48(r0, r1, r2);
    }

    public static /* synthetic */ List $r8$lambda$2KzYqZsaZuiYzoCLMFzzdx7AOy8(byte[] r0, byte[] r1) {
        return readAlarmConfig$lambda$27(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$2wXOoC6dLW0Ypza6ydOapACdACo(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeUnit$lambda$5(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$3UeQjlB8DLt5_TyO-R3EnBL7qCg, reason: not valid java name */
    public static /* synthetic */ boolean m11340$r8$lambda$3UeQjlB8DLt5_TyOR3EnBL7qCg(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return addBikeInformation$lambda$20(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$3nHHd8NXhkmK_jzUW9VEzHmBduw(byte[] r0, byte[] r1) {
        return addBikeInformation$lambda$19(r0, r1);
    }

    public static /* synthetic */ List $r8$lambda$4nqmn0pFccbxLJCU2DLaFnWQTbM(byte[] r0, byte[] r1) {
        return readUnit$lambda$7(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$5_2ntz-kdjRmSy_Fk5VTjhTsj3k, reason: not valid java name */
    public static /* synthetic */ Config.config_msg m11341$r8$lambda$5_2ntzkdjRmSy_Fk5VTjhTsj3k(byte[] r0, byte[] r1) {
        return readSoundSetting$lambda$38(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$6EGo6efLdw1enUwYUQsfQCeCoHU(byte[] r0, byte[] r1) {
        return dataProcessingFilter0Value$lambda$71(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$6HXiobHiHwpWpBHX9ghyNld-xcQ, reason: not valid java name */
    public static /* synthetic */ boolean m11342$r8$lambda$6HXiobHiHwpWpBHX9ghyNldxcQ(byte[] r0, byte[] r1) {
        return readSoundSetting$lambda$37(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$6w56QfTFKjEE-rmWe5eMXQeY-jA, reason: not valid java name */
    public static /* synthetic */ boolean m11343$r8$lambda$6w56QfTFKjEErmWe5eMXQeYjA(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeAutomaticSetting$lambda$59(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$7-bCfGIZqiw6e8sF9OPhuSSliUI, reason: not valid java name */
    public static /* synthetic */ boolean m11344$r8$lambda$7bCfGIZqiw6e8sF9OPhuSSliUI(byte[] r0, byte[] r1) {
        return deleteBikeInformation$lambda$21(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$7EShLIF4bVqg1gBX_uO5YZuH3iQ(byte[] r0, byte[] r1) {
        return writeAutomaticSetting$lambda$58(r0, r1);
    }

    public static /* synthetic */ Config.backlight_msg $r8$lambda$8QP_wCnr8hOIJRuPRWATa0crCh0(byte[] r0, byte[] r1) {
        return readDisplaySettings$lambda$54(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$8UDrKeKMFfA9fNhSlx-GlFVpKEU, reason: not valid java name */
    public static /* synthetic */ boolean m11345$r8$lambda$8UDrKeKMFfA9fNhSlxGlFVpKEU(byte[] r0, byte[] r1) {
        return readAlarmConfig$lambda$26(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$8cDZMvlbSE2xmOj3Z30dSgLqzI4(byte[] r0, byte[] r1) {
        return writeBikeInformation$lambda$51(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$AhycE9RbFGmdJbYKqAKe_y7gItI(byte[] r0, byte[] r1) {
        return writeUnit$lambda$4(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$BAdmjj_q2qadXms1MnFlzReZKvk(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return deleteBikeInformation$lambda$22(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$BDLhNJCU478xL-UijZiu_b-ap_Y, reason: not valid java name */
    public static /* synthetic */ List m11346$r8$lambda$BDLhNJCU478xLUijZiu_bap_Y(byte[] r0, byte[] r1) {
        return readLapConfig$lambda$33(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$BysRhmxCmoo54twc-t9UW4YEJdc, reason: not valid java name */
    public static /* synthetic */ boolean m11347$r8$lambda$BysRhmxCmoo54twct9UW4YEJdc(byte[] r0, byte[] r1) {
        return readBikeInformation$lambda$23(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$Cri0twaZHUZ09JTb4xCCu5T43G0(byte[] r0, byte[] r1) {
        return readPageInformation$lambda$14(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$DqF2T8-2ibZWFoHWCVax6ROYNV4, reason: not valid java name */
    public static /* synthetic */ boolean m11348$r8$lambda$DqF2T82ibZWFoHWCVax6ROYNV4(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeDisplaySettings$lambda$56(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$E1COQmKUX2lrfuN4JUYIlrvuD60(byte[] r0, byte[] r1) {
        return writeAltitude$lambda$77(r0, r1);
    }

    public static /* synthetic */ List $r8$lambda$E2zLKt7e3ECH7qo1sGzGOoi9UVk(byte[] r0, byte[] r1) {
        return readKeySetting$lambda$63(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$FaPP-MgdqyW3_Nj7fKLM0F4DW24, reason: not valid java name */
    public static /* synthetic */ boolean m11349$r8$lambda$FaPPMgdqyW3_Nj7fKLM0F4DW24(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return modifyRidingMode$lambda$46(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$FiWTiRHk5RQWB8uTUgybzE-xGNs, reason: not valid java name */
    public static /* synthetic */ List m11350$r8$lambda$FiWTiRHk5RQWB8uTUgybzExGNs(byte[] r0, byte[] r1) {
        return readBikeInformation$lambda$24(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$HZhsoujm-nxC0n6M7xb0KOVgTAM, reason: not valid java name */
    public static /* synthetic */ boolean m11351$r8$lambda$HZhsoujmnxC0n6M7xb0KOVgTAM(byte[] r0, byte[] r1) {
        return readDataProcessingSettings$lambda$68(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$HbziO6orIGyCUyrFuSzu2iIDIq8(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeAlarmConfig$lambda$30(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$ISW8lwtSGc4y87LFQb9ax5J-M3Q, reason: not valid java name */
    public static /* synthetic */ boolean m11352$r8$lambda$ISW8lwtSGc4y87LFQb9ax5JM3Q(byte[] r0, byte[] r1) {
        return writeSoundSetting$lambda$39(r0, r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$IUfGrmHhTjXhiSdj3LDA4lU7o_8(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return dataProcessingFilter0Value$lambda$72(r0, r1, r2);
    }

    public static /* synthetic */ List $r8$lambda$Izz9yOSbDU8dcwGOs0rLtZ77hWM(byte[] r0, byte[] r1) {
        return readRidingModeTemplate$lambda$44(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$JBa1Jx_Gy4jWBjnPS3I-47-5u40, reason: not valid java name */
    public static /* synthetic */ boolean m11353$r8$lambda$JBa1Jx_Gy4jWBjnPS3I475u40(byte[] r0, byte[] r1) {
        return writeLapConfig$lambda$35(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$L7ePsLuqSOl4vapRDGHpFvuFhZ8(byte[] r0, byte[] r1) {
        return writeUserInformation$lambda$0(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$MGSBAXawUrpef5-YAgO2Lo0in_4, reason: not valid java name */
    public static /* synthetic */ boolean m11354$r8$lambda$MGSBAXawUrpef5YAgO2Lo0in_4(byte[] r0, byte[] r1) {
        return writeKeySetting$lambda$60(r0, r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$MYVWNJJdukct62BnToGen_1RqeU(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeAltitude$lambda$78(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$MnmlZ2J47XOiwHps89-EXpfriOQ, reason: not valid java name */
    public static /* synthetic */ boolean m11355$r8$lambda$MnmlZ2J47XOiwHps89EXpfriOQ(byte[] r0, byte[] r1) {
        return readUnit$lambda$6(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$NY9_OXcoX_axQuz8o_158Dj6E8k(Common.service_type_index r0, Config.CONFIG_SERVICE_TYPE r1, Config.CONFIG_OPERATE_TYPE r2, byte[] r3, byte[] r4) {
        return readRidingModeTemplate$lambda$43(r0, r1, r2, r3, r4);
    }

    public static /* synthetic */ DeviceReturnStatus $r8$lambda$NjuY9vsCRUkcl7iG9ileFRyQwBU(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return resetPageToDefault$lambda$18(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$Nz39eycQug8pEfr4QZev3geiWhg(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeUserInformation$lambda$1(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$OW47fQ-A4kJ1rKX1mmVQjN3ypbU, reason: not valid java name */
    public static /* synthetic */ List m11356$r8$lambda$OW47fQA4kJ1rKX1mmVQjN3ypbU(byte[] r0, byte[] r1) {
        return readAllRidingMode$lambda$42(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$Ten7bHpw4LLnsmxYe1PIZxGe9zU(byte[] r0, byte[] r1) {
        return readLapConfig$lambda$32(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$UdjrxWXcSnoiN0nGkToqjqXGcXE(byte[] r0, byte[] r1) {
        return readAllRidingMode$lambda$41(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$YIqSNsD-aN1HFaT8uFczczv2xo4, reason: not valid java name */
    public static /* synthetic */ boolean m11357$r8$lambda$YIqSNsDaN1HFaT8uFczczv2xo4(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeBikeInformation$lambda$52(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$ZMAvJrGZtc7rNlJFfkd2v2ScHYo(byte[] r0, byte[] r1) {
        return readAutomaticSetting$lambda$65(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ZU58I5h5SB2po79Liwvhy3Bugrc(byte[] r0, byte[] r1) {
        return writeAlarmConfig$lambda$29(r0, r1);
    }

    public static /* synthetic */ Integer $r8$lambda$ZaMcTe0wQ0flAtOW_WGzrBPAQ3s(byte[] r0, byte[] r1) {
        return readAltitude$lambda$75(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$a7LXXDwSrNDe8cuiX2DOX6aTrVw(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeKeySetting$lambda$61(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$aj_TBjQJz5iLzL43ixMPy2AMbFg(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writePage$lambda$10(r0, r1, r2);
    }

    public static /* synthetic */ Config.user_data_msg $r8$lambda$b8ImZbnkVa0LDSDUHpmuICuxgME(byte[] r0, byte[] r1) {
        return readUserInformation$lambda$3(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$d7HbijqXB2FM-rewi5tzGrm2O7c, reason: not valid java name */
    public static /* synthetic */ boolean m11358$r8$lambda$d7HbijqXB2FMrewi5tzGrm2O7c(byte[] r0, byte[] r1) {
        return writePage$lambda$9(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$eBlj9ca0YAIJ1bSqDq6P8GOm7kY(byte[] r0, byte[] r1) {
        return addRidingMode$lambda$47(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$gZUKUzj0f42uZmHhA3Beu26WQVA(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return readPage$lambda$12(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$hH1RiveoFXTE22XjCiwKLLu7-7Y, reason: not valid java name */
    public static /* synthetic */ boolean m11359$r8$lambda$hH1RiveoFXTE22XjCiwKLLu77Y(byte[] r0, byte[] r1) {
        return readKeySetting$lambda$62(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$hmgNg2tv8x5C-80akuAFvZLnKAs, reason: not valid java name */
    public static /* synthetic */ boolean m11360$r8$lambda$hmgNg2tv8x5C80akuAFvZLnKAs(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeLapConfig$lambda$36(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$iVXqO8sLnojPBq93uoXbEl0OTkA(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return writeSoundSetting$lambda$40(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$kH1kcE6WvGXgz-FfLUEieEDgFZA, reason: not valid java name */
    public static /* synthetic */ Pair m11361$r8$lambda$kH1kcE6WvGXgzFfLUEieEDgFZA(byte[] r0, byte[] r1) {
        return readPage$lambda$13(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$kJ_rOERj73DCSKh1Xm2L7EepQN0(byte[] r0, byte[] r1) {
        return writeDisplaySettings$lambda$55(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$kPNnaoe9Gob2Z-L7rh1uDb10ETg, reason: not valid java name */
    public static /* synthetic */ Config.cur_page_status_msg m11362$r8$lambda$kPNnaoe9Gob2ZL7rh1uDb10ETg(byte[] r0, byte[] r1) {
        return readPageInformation$lambda$15(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$n1xfJDpPR3hmXGvI7eAhNwZJjsU(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return resetPageToDefault$lambda$17(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$nfg0QXB6SWZxQi2V23eRU0X6X5o(byte[] r0, byte[] r1) {
        return modifyRidingMode$lambda$45(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$o9jlNiIkbSwihgvcJxH4VIwCuLo(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        return deleteRidingMode$lambda$50(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$ouEZ4VFNqsCJ-gWhdBfKorPUtmM, reason: not valid java name */
    public static /* synthetic */ boolean m11363$r8$lambda$ouEZ4VFNqsCJgWhdBfKorPUtmM(byte[] r0, byte[] r1) {
        return deleteRidingMode$lambda$49(r0, r1);
    }

    public static /* synthetic */ List $r8$lambda$tnSni6tBwedXnzATAi0mFeOeqfE(byte[] r0, byte[] r1) {
        return readAutomaticSetting$lambda$66(r0, r1);
    }

    /* JADX INFO: renamed from: $r8$lambda$u91b_s-dHQLAiRGE4jZXm8xsECE, reason: not valid java name */
    public static /* synthetic */ boolean m11364$r8$lambda$u91b_sdHQLAiRGE4jZXm8xsECE(byte[] r0, byte[] r1) {
        return readUserInformation$lambda$2(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$wwFkdr_16aL0gqL2N6sA_XlXBjQ(byte[] r0, byte[] r1) {
        return readAltitude$lambda$74(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$xrNTXyNDvd1BceLY3XYt9G25W8s(byte[] r0, byte[] r1) {
        return readDisplaySettings$lambda$53(r0, r1);
    }

    public static /* synthetic */ List $r8$lambda$z5jhFViHpo3ElEPuU1F3SBvE0rM(byte[] r0, byte[] r1) {
        return readDataProcessingSettings$lambda$69(r0, r1);
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ ConfigurationDelegate(IGPDeviceManager r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    private ConfigurationDelegate(IGPDeviceManager r2) {
        this.deviceManager = r2;
        this.iGPDeviceType = r2.getIGPDeviceType();
        this.firstQueue = r2.getFirstQueue();
    }

    public final BaseCommand<Boolean> writeUserInformation(Config.user_data_msg r10) {
        Intrinsics.checkNotNullParameter(r10, "config");
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setUserDataMessage(r10).build());
        byte[] r1 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L7:
        if (r4 != null) goto L10;
    L11:
        if (r4 != null) goto L14;
    L16:
        if (r4 != null) goto L19;
    L22:
        BaseCommand<Boolean> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r2);
        r5.setCommandTag(WriteCommand.WriteUserInformation);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda22());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda33(this));
        return r5;
    L19:
        if (r4.intValue() != 4) goto L22;
        r1 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L22
    L14:
        if (r4.intValue() != 3) goto L16;
    L21:
        r1 = r11.getFirst();
        r2 = r11.getSecond();
        goto L22
    L10:
        if (r4.intValue() == 2) goto L21;
    L5:
        r4 = null;
        goto L7
    }

    private static final boolean writeUserInformation$lambda$0(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeUserInformation$lambda$1(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Config.user_data_msg> readUserInformation() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L5;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L7:
        if (r5 != null) goto L10;
    L11:
        if (r5 != null) goto L14;
    L16:
        if (r5 != null) goto L19;
    L22:
        BaseCommand<Config.user_data_msg> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadUserInformation);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda16());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda17());
        return r6;
    L19:
        if (r5.intValue() != 4) goto L22;
        r2 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L22
    L14:
        if (r5.intValue() != 3) goto L16;
    L21:
        r2 = r0.getFirst();
        r3 = r0.getSecond();
        goto L22
    L10:
        if (r5.intValue() == 2) goto L21;
    L5:
        r5 = null;
        goto L7
    }

    private static final boolean readUserInformation$lambda$2(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber())) goto L9;
        return true;
    L9:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L16;
        return true;
    L16:
        return false;
    }

    private static final Config.user_data_msg readUserInformation$lambda$3(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getUserDataMessage();
    L4:
        e = move-exception;
        e.printStackTrace();
        return Config.user_data_msg.newBuilder().build();
    }

    public final BaseCommand<Boolean> writeUnit(Config.unit_msg... r10) {
        Intrinsics.checkNotNullParameter(r10, "config");
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllUnitMessage(ArraysKt.asIterable(r10)).build());
        byte[] r1 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L7:
        if (r4 != null) goto L10;
    L11:
        if (r4 != null) goto L14;
    L16:
        if (r4 != null) goto L19;
    L22:
        BaseCommand<Boolean> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r2);
        r5.setCommandTag(WriteCommand.WriteUnit);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda18());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda19(this));
        return r5;
    L19:
        if (r4.intValue() != 4) goto L22;
        r1 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L22
    L14:
        if (r4.intValue() != 3) goto L16;
    L21:
        r1 = r11.getFirst();
        r2 = r11.getSecond();
        goto L22
    L10:
        if (r4.intValue() == 2) goto L21;
    L5:
        r4 = null;
        goto L7
    }

    private static final boolean writeUnit$lambda$4(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeUnit$lambda$5(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<List<Config.unit_msg>> readUnit() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L5;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L7:
        if (r5 != null) goto L10;
    L11:
        if (r5 != null) goto L14;
    L16:
        if (r5 != null) goto L19;
    L22:
        BaseCommand<List<Config.unit_msg>> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadUnit);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda5());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda6());
        return r6;
    L19:
        if (r5.intValue() != 4) goto L22;
        r2 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L22
    L14:
        if (r5.intValue() != 3) goto L16;
    L21:
        r2 = r0.getFirst();
        r3 = r0.getSecond();
        goto L22
    L10:
        if (r5.intValue() == 2) goto L21;
    L5:
        r5 = null;
        goto L7
    }

    private static final boolean readUnit$lambda$6(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readUnit$lambda$7(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getUnitMessageList();
    L5:
        return CollectionsKt.listOf(Config.unit_msg.newBuilder().build());
    }

    public static /* synthetic */ BaseCommand writePage$default(ConfigurationDelegate r0, Config.page_msg[] r1, Integer r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.writePage(r1, r2);
    }

    public final BaseCommand<Boolean> writePage(Config.page_msg[] r8, Integer r9) {
        Intrinsics.checkNotNullParameter(r8, "config");
        ComputerConfigureServiceFactory r0 = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder r1 = Config.config_msg.newBuilder();
        r1.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r1.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        r1.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        r1.addAllPageMessage(ArraysKt.asIterable(r8));
        IGPDevice r10 = this.iGPDeviceType;
        if (r10 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r11 = r0.getMessage(r1.build());
        byte[] r12 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L13;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L15:
        if (r4 != null) goto L18;
    L19:
        if (r4 != null) goto L22;
    L24:
        if (r4 != null) goto L27;
    L30:
        BaseCommand<Boolean> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r12, r2);
        r5.setCommandTag(WriteCommand.WritePage);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda49());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda50(this));
        return r5;
    L27:
        if (r4.intValue() != 4) goto L30;
        r12 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L30
    L22:
        if (r4.intValue() != 3) goto L24;
    L29:
        r12 = r11.getFirst();
        r2 = r11.getSecond();
        goto L30
    L18:
        if (r4.intValue() == 2) goto L29;
    L13:
        r4 = null;
        goto L15
    L5:
        if (r10.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r13 = r9.intValue();
    L9:
        r1.setCurOperateMode(r13);
        goto L10
    L8:
        r13 = 0;
        goto L9
    }

    private static final boolean writePage$lambda$9(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writePage$lambda$10(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public static /* synthetic */ BaseCommand readPage$default(ConfigurationDelegate r0, Integer r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.readPage(r1);
    }

    public final BaseCommand<Pair<List<Config.page_msg>, Config.cur_page_status_msg>> readPage(Integer r9) {
        ComputerConfigureServiceFactory r0 = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder r1 = Config.config_msg.newBuilder();
        r1.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r1.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        r1.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r10 = r0.getMessage(r1.build());
        byte[] r3 = new byte[0];
        byte[] r4 = new byte[0];
        IGPDevice r5 = this.iGPDeviceType;
        if (r5 == null) goto L13;
        Integer r6 = Integer.valueOf(r5.getGeneration());
    L15:
        if (r6 != null) goto L18;
    L19:
        if (r6 != null) goto L22;
    L24:
        if (r6 != null) goto L27;
    L30:
        BaseCommand<Pair<List<Config.page_msg>, Config.cur_page_status_msg>> r7 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r3, r4);
        r7.setCommandTag(WriteCommand.ReadPage);
        r7.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda62(this));
        r7.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda63());
        return r7;
    L27:
        if (r6.intValue() != 4) goto L30;
        r3 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L30
    L22:
        if (r6.intValue() != 3) goto L24;
    L29:
        r3 = r10.getFirst();
        r4 = r10.getSecond();
        goto L30
    L18:
        if (r6.intValue() == 2) goto L29;
    L13:
        r6 = null;
        goto L15
    L5:
        if (r2.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r11 = r9.intValue();
    L9:
        r1.setCurOperateMode(r11);
        goto L10
    L8:
        r11 = 0;
        goto L9
    }

    private static final boolean readPage$lambda$12(ConfigurationDelegate r4, byte[] r5, byte[] r6) {
        Intrinsics.checkNotNullParameter(r4, "this$0");
        Intrinsics.checkNotNull(r5);
        TransmitCompleteCommand r7 = new TransmitCompleteCommand(r5);
        r4.deviceManager.log("--isReceiveFinish-- ", r7.getMainService() + "," + r7.getSecondService() + "," + r7.getMainOperation());
        IGPDevice r8 = r4.iGPDeviceType;
        if (r8 == null) goto L15;
        if (r8.getGeneration() != 4) goto L15;
        if (r7.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L9;
    L13:
        return false;
    L9:
        if (r7.getSecondService() != ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber())) goto L13;
        if (r7.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L15:
        if (r7.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L17;
    L21:
        return false;
    L17:
        if (r7.getSecondService() != ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber())) goto L21;
        if (r7.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber())) goto L21;
        return true;
    }

    private static final Pair readPage$lambda$13(byte[] r1, byte[] r2) {
        List<Config.page_msg> r3 = Config.config_msg.parseFrom(r1).getPageMessageList();     // Catch: InvalidProtocolBufferException -> L4
    L10:
        Config.cur_page_status_msg r4 = Config.config_msg.parseFrom(r1).getPageStatusMessage();     // Catch: InvalidProtocolBufferException -> L7
    L9:
        return new Pair(r3, r4);
    L7:
        r4 = Config.cur_page_status_msg.newBuilder().build();
    L4:
        r3 = CollectionsKt.listOf(Config.page_msg.newBuilder().build());
        goto L10
    }

    public final BaseCommand<Config.cur_page_status_msg> readPageInformation() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO).build());
        BaseCommand<Config.cur_page_status_msg> r1 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r0.getFirst(), r0.getSecond());
        r1.setCommandTag(WriteCommand.ReadPageInformation);
        r1.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda23());
        r1.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda24());
        return r1;
    }

    private static final boolean readPageInformation$lambda$14(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Config.cur_page_status_msg readPageInformation$lambda$15(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getPageStatusMessage();
    L5:
        return Config.cur_page_status_msg.newBuilder().build();
    }

    public final BaseCommand<DeviceReturnStatus> resetPageToDefault(Integer r9) {
        ComputerConfigureServiceFactory r0 = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder r1 = Config.config_msg.newBuilder();
        r1.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r1.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        r1.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET_DEFAULT);
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r10 = r0.getMessage(r1.build());
        byte[] r3 = new byte[0];
        byte[] r4 = new byte[0];
        IGPDevice r5 = this.iGPDeviceType;
        if (r5 == null) goto L13;
        Integer r6 = Integer.valueOf(r5.getGeneration());
    L15:
        if (r6 != null) goto L18;
    L19:
        if (r6 != null) goto L22;
    L24:
        if (r6 != null) goto L27;
    L30:
        BaseCommand<DeviceReturnStatus> r7 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r3, r4);
        r7.setCommandTag(WriteCommand.ReadPage);
        r7.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda36(this));
        r7.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda37(this));
        return r7;
    L27:
        if (r6.intValue() != 4) goto L30;
        r3 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L30
    L22:
        if (r6.intValue() != 3) goto L24;
    L29:
        r3 = r10.getFirst();
        r4 = r10.getSecond();
        goto L30
    L18:
        if (r6.intValue() == 2) goto L29;
    L13:
        r6 = null;
        goto L15
    L5:
        if (r2.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r11 = r9.intValue();
    L9:
        r1.setCurOperateMode(r11);
        goto L10
    L8:
        r11 = 0;
        goto L9
    }

    private static final boolean resetPageToDefault$lambda$17(ConfigurationDelegate r3, byte[] r4, byte[] r5) {
        Intrinsics.checkNotNullParameter(r3, "this$0");
        Intrinsics.checkNotNull(r4);
        TransmitCompleteCommand r6 = new TransmitCompleteCommand(r4);
        r3.deviceManager.log("--isReceiveFinish-- ", r6.getMainService() + "," + r6.getSecondService() + "," + r6.getMainOperation());
        if (r6.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r6.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber())) goto L7;
        return false;
    L7:
        if (r6.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET_DEFAULT.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final DeviceReturnStatus resetPageToDefault$lambda$18(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Byte r3 = r0.deviceManager.getCommandStatus(r2);
        return DeviceReturnStatus.Companion.fromStatus(r3);
    }

    public final BaseCommand<Boolean> addBikeInformation(Config.bike_msg... r7) {
        Intrinsics.checkNotNullParameter(r7, "config");
        Pair<byte[], byte[]> r8 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD).addAllBikeMessage(ArraysKt.asIterable(r7)).build());
        BaseCommand<Boolean> r0 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r8.getFirst(), r8.getSecond());
        r0.setCommandTag(WriteCommand.AddBikeInformation);
        r0.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda27());
        r0.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda28(this));
        return r0;
    }

    private static final boolean addBikeInformation$lambda$19(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean addBikeInformation$lambda$20(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Boolean> deleteBikeInformation(Config.bike_msg... r7) {
        Intrinsics.checkNotNullParameter(r7, "config");
        Pair<byte[], byte[]> r8 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL).addAllBikeMessage(ArraysKt.asIterable(r7)).build());
        BaseCommand<Boolean> r0 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r8.getFirst(), r8.getSecond());
        r0.setCommandTag(WriteCommand.DeleteBikeInformation);
        r0.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda40());
        r0.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda41(this));
        return r0;
    }

    private static final boolean deleteBikeInformation$lambda$21(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean deleteBikeInformation$lambda$22(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<List<Config.bike_msg>> readBikeInformation() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        BaseCommand<List<Config.bike_msg>> r1 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r0.getFirst(), r0.getSecond());
        r1.setCommandTag(WriteCommand.ReadBikeInformation);
        r1.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda47());
        r1.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda48());
        return r1;
    }

    private static final boolean readBikeInformation$lambda$23(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readBikeInformation$lambda$24(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getBikeMessageList();
    L5:
        return CollectionsKt.listOf(Config.bike_msg.newBuilder().build());
    }

    public static /* synthetic */ BaseCommand readAlarmConfig$default(ConfigurationDelegate r0, Integer r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.readAlarmConfig(r1);
    }

    public final BaseCommand<List<Config.alarm_msg>> readAlarmConfig(Integer r9) {
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice r1 = this.iGPDeviceType;
        if (r1 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L13;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L15:
        if (r5 != null) goto L18;
    L19:
        if (r5 != null) goto L22;
    L24:
        if (r5 != null) goto L27;
    L30:
        BaseCommand<List<Config.alarm_msg>> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadAlarmConfig);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda45());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda46());
        return r6;
    L27:
        if (r5.intValue() != 4) goto L30;
        r2 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L30
    L22:
        if (r5.intValue() != 3) goto L24;
    L29:
        r2 = r10.getFirst();
        r3 = r10.getSecond();
        goto L30
    L18:
        if (r5.intValue() == 2) goto L29;
    L13:
        r5 = null;
        goto L15
    L5:
        if (r1.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r11 = r9.intValue();
    L9:
        r0.setCurOperateMode(r11);
        goto L10
    L8:
        r11 = 0;
        goto L9
    }

    private static final boolean readAlarmConfig$lambda$26(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readAlarmConfig$lambda$27(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getAlarmMessageList();
    L4:
        e = move-exception;
        e.printStackTrace();
        return CollectionsKt.listOf(Config.alarm_msg.newBuilder().build());
    }

    public static /* synthetic */ BaseCommand writeAlarmConfig$default(ConfigurationDelegate r0, Config.alarm_msg[] r1, Integer r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.writeAlarmConfig(r1, r2);
    }

    public final BaseCommand<Boolean> writeAlarmConfig(Config.alarm_msg[] r8, Integer r9) {
        Intrinsics.checkNotNullParameter(r8, "configs");
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        r0.addAllAlarmMessage(ArraysKt.asIterable(r8));
        IGPDevice r10 = this.iGPDeviceType;
        if (r10 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r12 = new byte[0];
        byte[] r1 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L13;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L15:
        if (r4 != null) goto L18;
    L19:
        if (r4 != null) goto L22;
    L24:
        if (r4 != null) goto L27;
    L30:
        BaseCommand<Boolean> r2 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r12, r1);
        r2.setCommandTag(WriteCommand.WriteAlarmConfig);
        r2.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda38());
        r2.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda39(this));
        return r2;
    L27:
        if (r4.intValue() != 4) goto L30;
        r12 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L30
    L22:
        if (r4.intValue() != 3) goto L24;
    L29:
        r12 = r11.getFirst();
        r1 = r11.getSecond();
        goto L30
    L18:
        if (r4.intValue() == 2) goto L29;
    L13:
        r4 = null;
        goto L15
    L5:
        if (r10.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r13 = r9.intValue();
    L9:
        r0.setCurOperateMode(r13);
        goto L10
    L8:
        r13 = 0;
        goto L9
    }

    private static final boolean writeAlarmConfig$lambda$29(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeAlarmConfig$lambda$30(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public static /* synthetic */ BaseCommand readLapConfig$default(ConfigurationDelegate r0, Integer r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.readLapConfig(r1);
    }

    public final BaseCommand<List<Config.lap_msg>> readLapConfig(Integer r9) {
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice r1 = this.iGPDeviceType;
        if (r1 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L13;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L15:
        if (r5 != null) goto L18;
    L19:
        if (r5 != null) goto L22;
    L24:
        if (r5 != null) goto L27;
    L30:
        BaseCommand<List<Config.lap_msg>> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadLapConfig);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda0());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda11());
        return r6;
    L27:
        if (r5.intValue() != 4) goto L30;
        r2 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L30
    L22:
        if (r5.intValue() != 3) goto L24;
    L29:
        r2 = r10.getFirst();
        r3 = r10.getSecond();
        goto L30
    L18:
        if (r5.intValue() == 2) goto L29;
    L13:
        r5 = null;
        goto L15
    L5:
        if (r1.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r11 = r9.intValue();
    L9:
        r0.setCurOperateMode(r11);
        goto L10
    L8:
        r11 = 0;
        goto L9
    }

    private static final boolean readLapConfig$lambda$32(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readLapConfig$lambda$33(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getLapMessageList();
    L4:
        e = move-exception;
        e.printStackTrace();
        return CollectionsKt.listOf(Config.lap_msg.newBuilder().setLapType(Config.LAP_TYPE.LAP_TYPE_INVALID).build());
    }

    public static /* synthetic */ BaseCommand writeLapConfig$default(ConfigurationDelegate r0, Config.lap_msg[] r1, Integer r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.writeLapConfig(r1, r2);
    }

    public final BaseCommand<Boolean> writeLapConfig(Config.lap_msg[] r8, Integer r9) {
        Intrinsics.checkNotNullParameter(r8, "configs");
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        r0.addAllLapMessage(ArraysKt.asIterable(r8));
        IGPDevice r10 = this.iGPDeviceType;
        if (r10 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r12 = new byte[0];
        byte[] r1 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L13;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L15:
        if (r4 != null) goto L18;
    L19:
        if (r4 != null) goto L22;
    L24:
        if (r4 != null) goto L27;
    L30:
        BaseCommand<Boolean> r2 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r12, r1);
        r2.setCommandTag(WriteCommand.WriteLapConfig);
        r2.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda44());
        r2.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda55(this));
        return r2;
    L27:
        if (r4.intValue() != 4) goto L30;
        r12 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L30
    L22:
        if (r4.intValue() != 3) goto L24;
    L29:
        r12 = r11.getFirst();
        r1 = r11.getSecond();
        goto L30
    L18:
        if (r4.intValue() == 2) goto L29;
    L13:
        r4 = null;
        goto L15
    L5:
        if (r10.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r13 = r9.intValue();
    L9:
        r0.setCurOperateMode(r13);
        goto L10
    L8:
        r13 = 0;
        goto L9
    }

    private static final boolean writeLapConfig$lambda$35(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeLapConfig$lambda$36(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Config.config_msg> readSoundSetting() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L5;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L7:
        if (r5 != null) goto L10;
    L11:
        if (r5 != null) goto L14;
    L16:
        if (r5 != null) goto L19;
    L22:
        BaseCommand<Config.config_msg> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadSoundSetting);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda3());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda4());
        return r6;
    L19:
        if (r5.intValue() != 4) goto L22;
        r2 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L22
    L14:
        if (r5.intValue() != 3) goto L16;
    L21:
        r2 = r0.getFirst();
        r3 = r0.getSecond();
        goto L22
    L10:
        if (r5.intValue() == 2) goto L21;
    L5:
        r5 = null;
        goto L7
    }

    private static final boolean readSoundSetting$lambda$37(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Config.config_msg readSoundSetting$lambda$38(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0);
    L4:
        e = move-exception;
        e.printStackTrace();
        return Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_NONE).build();
    }

    public final BaseCommand<Boolean> writeSoundSetting(Config.sound_set_msg r10) {
        Intrinsics.checkNotNullParameter(r10, "soundSetMsg");
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addSoundSetMessage(r10).build());
        byte[] r1 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L7:
        if (r4 != null) goto L10;
    L11:
        if (r4 != null) goto L14;
    L16:
        if (r4 != null) goto L19;
    L22:
        BaseCommand<Boolean> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r2);
        r5.setCommandTag(WriteCommand.WriteSoundSetting);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda42());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda43(this));
        return r5;
    L19:
        if (r4.intValue() != 4) goto L22;
        r1 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L22
    L14:
        if (r4.intValue() != 3) goto L16;
    L21:
        r1 = r11.getFirst();
        r2 = r11.getSecond();
        goto L22
    L10:
        if (r4.intValue() == 2) goto L21;
    L5:
        r4 = null;
        goto L7
    }

    private static final boolean writeSoundSetting$lambda$39(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeSoundSetting$lambda$40(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<List<Config.mode_msg>> readAllRidingMode() {
        DeviceConstant.Companion r0 = DeviceConstant.Companion;
        IGPDevice r1 = this.iGPDeviceType;
        Integer r2 = null;
        if (r1 == null) goto L5;
        String r3 = r1.getDeviceName();
    L6:
        Pair<byte[], byte[]> r4 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).setCurOperateMode(r0.getAllRidingModeCount(r3)).build());
        byte[] r5 = new byte[0];
        byte[] r9 = new byte[0];
        IGPDevice r6 = this.iGPDeviceType;
        if (r6 == null) goto L9;
        r2 = Integer.valueOf(r6.getGeneration());
    L9:
        if (r2 != null) goto L12;
    L14:
        BaseCommand<List<Config.mode_msg>> r7 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r5, r9);
        r7.setCommandTag(WriteCommand.ReadAllRidingModel);
        r7.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda31());
        r7.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda32());
        return r7;
    L12:
        if (r2.intValue() != 4) goto L14;
        r5 = StringUtils.byteMerger(new byte[][]{r4.getSecond(), r4.getFirst()});
        goto L14
    L5:
        r3 = null;
        goto L6
    }

    private static final boolean readAllRidingMode$lambda$41(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readAllRidingMode$lambda$42(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getModeMessageList();
    L4:
        return null;
    }

    public final BaseCommand<List<Config.mode_msg>> readRidingModeTemplate() {
        final Common.service_type_index r0 = Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG;
        final Config.CONFIG_SERVICE_TYPE r1 = Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE_TEMPLATE;
        final Config.CONFIG_OPERATE_TYPE r2 = Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET;
        Pair<byte[], byte[]> r3 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(r0).setConfigSeviceType(r1).setConfigOperateType(r2).build());
        byte[] r11 = StringUtils.byteMerger(new byte[][]{r3.getSecond(), r3.getFirst()});
        IGPDeviceManager r8 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r9 = this.firstQueue;
        Intrinsics.checkNotNull(r11);
        BaseCommand<List<Config.mode_msg>> r7 = new BaseCommand(r8, r9, 1, r11, new byte[0]);
        r7.setCommandTag(WriteCommand.ReadRidingModeTemplate);
        r7.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda56(r0, r1, r2));
        r7.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda57());
        return r7;
    }

    private static final boolean readRidingModeTemplate$lambda$43(Common.service_type_index r0, Config.CONFIG_SERVICE_TYPE r1, Config.CONFIG_OPERATE_TYPE r2, byte[] r3, byte[] r4) {
        Intrinsics.checkNotNullParameter(r0, "$serviceType");
        Intrinsics.checkNotNullParameter(r1, "$subServiceType");
        Intrinsics.checkNotNullParameter(r2, "$operationType");
        Intrinsics.checkNotNull(r3);
        TransmitCompleteCommand r5 = new TransmitCompleteCommand(r3);
        if (r5.getMainService() == ((byte) r0.getNumber())) goto L5;
        return false;
    L5:
        if (r5.getSecondService() == ((byte) r1.getNumber())) goto L7;
        return false;
    L7:
        if (r5.getMainOperation() != ((byte) r2.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readRidingModeTemplate$lambda$44(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getModeMessageList();
    L4:
        return null;
    }

    public final BaseCommand<Boolean> modifyRidingMode(Config.mode_msg r9) {
        Intrinsics.checkNotNullParameter(r9, "mode");
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addModeMessage(r9).build());
        byte[] r1 = new byte[0];
        byte[] r7 = new byte[0];
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 == null) goto L5;
        Integer r3 = Integer.valueOf(r2.getGeneration());
    L6:
        if (r3 != null) goto L9;
    L11:
        BaseCommand<Boolean> r4 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r7);
        r4.setCommandTag(WriteCommand.ModifyRidingModel);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda53());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda54(this));
        return r4;
    L9:
        if (r3.intValue() != 4) goto L11;
        r1 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L11
    L5:
        r3 = null;
        goto L6
    }

    private static final boolean modifyRidingMode$lambda$45(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean modifyRidingMode$lambda$46(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Boolean> addRidingMode(Config.mode_msg r9) {
        Intrinsics.checkNotNullParameter(r9, "mode");
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD).addModeMessage(r9).build());
        byte[] r1 = new byte[0];
        byte[] r7 = new byte[0];
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 == null) goto L5;
        Integer r3 = Integer.valueOf(r2.getGeneration());
    L6:
        if (r3 != null) goto L9;
    L11:
        BaseCommand<Boolean> r4 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r7);
        r4.setCommandTag(WriteCommand.AddRidingModel);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda12());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda13(this));
        return r4;
    L9:
        if (r3.intValue() != 4) goto L11;
        r1 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L11
    L5:
        r3 = null;
        goto L6
    }

    private static final boolean addRidingMode$lambda$47(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean addRidingMode$lambda$48(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Boolean> deleteRidingMode(Config.mode_msg r9) {
        Intrinsics.checkNotNullParameter(r9, "mode");
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL).addModeMessage(r9).build());
        byte[] r1 = new byte[0];
        byte[] r7 = new byte[0];
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 == null) goto L5;
        Integer r3 = Integer.valueOf(r2.getGeneration());
    L6:
        if (r3 != null) goto L9;
    L11:
        BaseCommand<Boolean> r4 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r7);
        r4.setCommandTag(WriteCommand.DeleteRidingModel);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda58());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda59(this));
        return r4;
    L9:
        if (r3.intValue() != 4) goto L11;
        r1 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L11
    L5:
        r3 = null;
        goto L6
    }

    private static final boolean deleteRidingMode$lambda$49(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean deleteRidingMode$lambda$50(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Boolean> writeBikeInformation(Config.bike_msg... r7) {
        Intrinsics.checkNotNullParameter(r7, "config");
        Pair<byte[], byte[]> r8 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllBikeMessage(ArraysKt.asIterable(r7)).build());
        BaseCommand<Boolean> r0 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r8.getFirst(), r8.getSecond());
        r0.setCommandTag(WriteCommand.WriteBikeInformation);
        r0.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda14());
        r0.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda15(this));
        return r0;
    }

    private static final boolean writeBikeInformation$lambda$51(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeBikeInformation$lambda$52(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Config.backlight_msg> readDisplaySettings() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] r2 = new byte[0];
        byte[] r8 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L6:
        if (r4 != null) goto L9;
    L11:
        BaseCommand<Config.backlight_msg> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r8);
        r5.setCommandTag(WriteCommand.ReadDisplaySettings);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda1());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda2());
        return r5;
    L9:
        if (r4.intValue() != 4) goto L11;
        r2 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L11
    L5:
        r4 = null;
        goto L6
    }

    private static final boolean readDisplaySettings$lambda$53(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Config.backlight_msg readDisplaySettings$lambda$54(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getBacklightMessage();
    L4:
        return null;
    }

    public final BaseCommand<Boolean> writeDisplaySettings(Config.backlight_msg r9) {
        Intrinsics.checkNotNullParameter(r9, "displaySettings");
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setBacklightMessage(r9).build());
        byte[] r1 = new byte[0];
        byte[] r7 = new byte[0];
        IGPDevice r2 = this.iGPDeviceType;
        if (r2 == null) goto L5;
        Integer r3 = Integer.valueOf(r2.getGeneration());
    L6:
        if (r3 != null) goto L9;
    L11:
        BaseCommand<Boolean> r4 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r7);
        r4.setCommandTag(WriteCommand.WriteDisplaySettings);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda60());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda61(this));
        return r4;
    L9:
        if (r3.intValue() != 4) goto L11;
        r1 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L11
    L5:
        r3 = null;
        goto L6
    }

    private static final boolean writeDisplaySettings$lambda$55(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeDisplaySettings$lambda$56(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public static /* synthetic */ BaseCommand writeAutomaticSetting$default(ConfigurationDelegate r0, Config.auto_set_msg[] r1, Integer r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.writeAutomaticSetting(r1, r2);
    }

    public final BaseCommand<Boolean> writeAutomaticSetting(Config.auto_set_msg[] r8, Integer r9) {
        Intrinsics.checkNotNullParameter(r8, "configs");
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        r0.addAllAutoSetMessage(ArraysKt.asIterable(r8));
        IGPDevice r10 = this.iGPDeviceType;
        if (r10 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r12 = new byte[0];
        byte[] r1 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L13;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L15:
        if (r4 != null) goto L18;
    L19:
        if (r4 != null) goto L22;
    L24:
        if (r4 != null) goto L27;
    L30:
        BaseCommand<Boolean> r2 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r12, r1);
        r2.setCommandTag(WriteCommand.WriteAutomaticSetting);
        r2.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda51());
        r2.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda52(this));
        return r2;
    L27:
        if (r4.intValue() != 4) goto L30;
        r12 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L30
    L22:
        if (r4.intValue() != 3) goto L24;
    L29:
        r12 = r11.getFirst();
        r1 = r11.getSecond();
        goto L30
    L18:
        if (r4.intValue() == 2) goto L29;
    L13:
        r4 = null;
        goto L15
    L5:
        if (r10.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r13 = r9.intValue();
    L9:
        r0.setCurOperateMode(r13);
        goto L10
    L8:
        r13 = 0;
        goto L9
    }

    private static final boolean writeAutomaticSetting$lambda$58(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeAutomaticSetting$lambda$59(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<Boolean> writeKeySetting(Config.key_set_msg... r10) {
        Intrinsics.checkNotNullParameter(r10, "configs");
        Pair<byte[], byte[]> r11 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllKeySetMessage(ArraysKt.asIterable(r10)).build());
        byte[] r1 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L7:
        if (r4 != null) goto L10;
    L11:
        if (r4 != null) goto L14;
    L16:
        if (r4 != null) goto L19;
    L22:
        BaseCommand<Boolean> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r2);
        r5.setCommandTag(WriteCommand.WriteKeySetting);
        r5.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda34());
        r5.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda35(this));
        return r5;
    L19:
        if (r4.intValue() != 4) goto L22;
        r1 = StringUtils.byteMerger(new byte[][]{r11.getSecond(), r11.getFirst()});
        goto L22
    L14:
        if (r4.intValue() != 3) goto L16;
    L21:
        r1 = r11.getFirst();
        r2 = r11.getSecond();
        goto L22
    L10:
        if (r4.intValue() == 2) goto L21;
    L5:
        r4 = null;
        goto L7
    }

    private static final boolean writeKeySetting$lambda$60(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final boolean writeKeySetting$lambda$61(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);
        if (r3 != null) goto L5;
        return false;
    L5:
        return r3.booleanValue();
    }

    public final BaseCommand<List<Config.key_set_msg>> readKeySetting() {
        Pair<byte[], byte[]> r0 = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L5;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L7:
        if (r5 != null) goto L10;
    L11:
        if (r5 != null) goto L14;
    L16:
        if (r5 != null) goto L19;
    L22:
        BaseCommand<List<Config.key_set_msg>> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadKeySetting);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda9());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda10());
        return r6;
    L19:
        if (r5.intValue() != 4) goto L22;
        r2 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L22
    L14:
        if (r5.intValue() != 3) goto L16;
    L21:
        r2 = r0.getFirst();
        r3 = r0.getSecond();
        goto L22
    L10:
        if (r5.intValue() == 2) goto L21;
    L5:
        r5 = null;
        goto L7
    }

    private static final boolean readKeySetting$lambda$62(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readKeySetting$lambda$63(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getKeySetMessageList();
    L4:
        e = move-exception;
        e.printStackTrace();
        return CollectionsKt.listOf(Config.key_set_msg.newBuilder().build());
    }

    public static /* synthetic */ BaseCommand readAutomaticSetting$default(ConfigurationDelegate r0, Integer r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.readAutomaticSetting(r1);
    }

    public final BaseCommand<List<Config.auto_set_msg>> readAutomaticSetting(Integer r9) {
        Config.config_msg.Builder r0 = Config.config_msg.newBuilder();
        r0.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO);
        r0.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice r1 = this.iGPDeviceType;
        if (r1 != null) goto L5;
    L10:
        Pair<byte[], byte[]> r10 = new ComputerConfigureServiceFactory().getMessage(r0.build());
        byte[] r2 = new byte[0];
        byte[] r3 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L13;
        Integer r5 = Integer.valueOf(r4.getGeneration());
    L15:
        if (r5 != null) goto L18;
    L19:
        if (r5 != null) goto L22;
    L24:
        if (r5 != null) goto L27;
    L30:
        BaseCommand<List<Config.auto_set_msg>> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r2, r3);
        r6.setCommandTag(WriteCommand.ReadAutomaticSetting);
        r6.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda7());
        r6.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda8());
        return r6;
    L27:
        if (r5.intValue() != 4) goto L30;
        r2 = StringUtils.byteMerger(new byte[][]{r10.getSecond(), r10.getFirst()});
        goto L30
    L22:
        if (r5.intValue() != 3) goto L24;
    L29:
        r2 = r10.getFirst();
        r3 = r10.getSecond();
        goto L30
    L18:
        if (r5.intValue() == 2) goto L29;
    L13:
        r5 = null;
        goto L15
    L5:
        if (r1.getGeneration() != 4) goto L10;
        if (r9 == null) goto L8;
        int r11 = r9.intValue();
    L9:
        r0.setCurOperateMode(r11);
        goto L10
    L8:
        r11 = 0;
        goto L9
    }

    private static final boolean readAutomaticSetting$lambda$65(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readAutomaticSetting$lambda$66(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getAutoSetMessageList();
    L4:
        e = move-exception;
        e.printStackTrace();
        return CollectionsKt.listOf(Config.auto_set_msg.newBuilder().build());
    }

    public final BaseCommand<List<Config.data_msg>> readDataProcessingSettings() {
        byte[] r0 = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build().toByteArray();
        Head20BytesInfo r1 = new Head20BytesInfo();
        r1.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r1.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber());
        r1.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
        Intrinsics.checkNotNull(r0);
        r1.setPbDataBytes(r0);
        byte[] r5 = StringUtils.byteMerger(new byte[][]{r1.confirmCommandByteArray(), r0});
        IGPDeviceManager r2 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r3 = this.firstQueue;
        Intrinsics.checkNotNull(r5);
        BaseCommand<List<Config.data_msg>> r4 = new BaseCommand(r2, r3, 1, r5, new byte[0]);
        r4.setCommandTag(WriteCommand.ReadDataProcessingSettings);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda20());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda21());
        return r4;
    }

    private static final boolean readDataProcessingSettings$lambda$68(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final List readDataProcessingSettings$lambda$69(byte[] r0, byte[] r1) {
        return Config.config_msg.parseFrom(r0).getDataMessageList();
    L4:
        return null;
    }

    public final BaseCommand<Boolean> dataProcessingFilter0Value(Config.DATA_TYPE r7, boolean r8) {
        Intrinsics.checkNotNullParameter(r7, "dataType");
        byte[] r9 = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addDataMessage(Config.data_msg.newBuilder().setDataType(r7).setStatus(!r8 ? 1 : 0).build()).build().toByteArray();
        Head20BytesInfo r10 = new Head20BytesInfo();
        r10.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r10.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber());
        r10.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
        Intrinsics.checkNotNull(r9);
        r10.setPbDataBytes(r9);
        byte[] r4 = StringUtils.byteMerger(new byte[][]{r10.confirmCommandByteArray(), r9});
        IGPDeviceManager r1 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r2 = this.firstQueue;
        Intrinsics.checkNotNull(r4);
        BaseCommand<Boolean> r0 = new BaseCommand(r1, r2, 1, r4, new byte[0]);
        r0.setCommandTag(WriteCommand.DataProcessingFilter0Value);
        r0.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda29());
        r0.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda30(this));
        return r0;
    }

    private static final boolean dataProcessingFilter0Value$lambda$71(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Boolean dataProcessingFilter0Value$lambda$72(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);     // Catch: InvalidProtocolBufferException -> L9
        if (r3 == null) goto L6;
        boolean r4 = r3.booleanValue();     // Catch: InvalidProtocolBufferException -> L9
    L7:
        return Boolean.valueOf(r4);
    L6:
        r4 = false;
    L9:
        return null;
    }

    public final BaseCommand<Integer> readAltitude() {
        byte[] r0 = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build().toByteArray();
        Head20BytesInfo r1 = new Head20BytesInfo();
        r1.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r1.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber());
        r1.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
        Intrinsics.checkNotNull(r0);
        r1.setPbDataBytes(r0);
        byte[] r5 = StringUtils.byteMerger(new byte[][]{r1.confirmCommandByteArray(), r0});
        IGPDeviceManager r2 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r3 = this.firstQueue;
        Intrinsics.checkNotNull(r5);
        BaseCommand<Integer> r4 = new BaseCommand(r2, r3, 1, r5, new byte[0]);
        r4.setCommandTag(WriteCommand.ReadAltitude);
        r4.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda64());
        r4.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda65());
        return r4;
    }

    private static final boolean readAltitude$lambda$74(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Integer readAltitude$lambda$75(byte[] r0, byte[] r1) {
        return Integer.valueOf(Config.config_msg.parseFrom(r0).getAltitude());
    L4:
        return null;
    }

    public final BaseCommand<Boolean> writeAltitude(int r7) {
        byte[] r8 = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setAltitude(r7).build().toByteArray();
        Head20BytesInfo r0 = new Head20BytesInfo();
        r0.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        r0.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber());
        r0.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
        Intrinsics.checkNotNull(r8);
        r0.setPbDataBytes(r8);
        byte[] r4 = StringUtils.byteMerger(new byte[][]{r0.confirmCommandByteArray(), r8});
        IGPDeviceManager r1 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r2 = this.firstQueue;
        Intrinsics.checkNotNull(r4);
        BaseCommand<Boolean> r3 = new BaseCommand(r1, r2, 1, r4, new byte[0]);
        r3.setReceiveFinish(new ConfigurationDelegate$$ExternalSyntheticLambda25());
        r3.setTranslation(new ConfigurationDelegate$$ExternalSyntheticLambda26(this));
        r3.setCommandTag(WriteCommand.WriteAltitude);
        return r3;
    }

    private static final boolean writeAltitude$lambda$77(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber())) goto L7;
        return false;
    L7:
        if (r3.getMainOperation() != ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber())) goto L13;
        return true;
    L13:
        return false;
    }

    private static final Boolean writeAltitude$lambda$78(ConfigurationDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Boolean r3 = r0.deviceManager.isCommandStatusSuccess(r2);     // Catch: InvalidProtocolBufferException -> L9
        if (r3 == null) goto L6;
        boolean r4 = r3.booleanValue();     // Catch: InvalidProtocolBufferException -> L9
    L7:
        return Boolean.valueOf(r4);
    L6:
        r4 = false;
    L9:
        return null;
    }
}