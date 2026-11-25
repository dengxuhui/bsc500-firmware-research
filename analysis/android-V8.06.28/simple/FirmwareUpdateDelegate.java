package com.igpsport.blelib.devicemanager;

import android.bluetooth.BluetoothDevice;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.igpsport.blelib.BaseCommand;
import com.igpsport.blelib.CommandError;
import com.igpsport.blelib.DeviceReturnStatus;
import com.igpsport.blelib.IGPDeviceManager;
import com.igpsport.blelib.WriteCommand;
import com.igpsport.blelib.bean.Common;
import com.igpsport.blelib.bean.DevVerInfo;
import com.igpsport.blelib.bean.Firmware;
import com.igpsport.blelib.bean.FirmwareData;
import com.igpsport.blelib.bean.GeneralFileOperation;
import com.igpsport.blelib.device.IGPDevice;
import com.igpsport.blelib.pbfactory.BaseFactory;
import com.igpsport.blelib.pbfactory.ConfirmCommand;
import com.igpsport.blelib.pbfactory.DeviceVersionInfoServiceFactory;
import com.igpsport.blelib.pbfactory.FirmwareUpgradeServiceFactory;
import com.igpsport.blelib.pbfactory.Head20BytesInfo;
import com.igpsport.blelib.pbfactory.TransmitCompleteCommand;
import com.igpsport.blelib.pbfactory.TransmitCompleteCommandKt;
import com.igpsport.blelib.utils.CRC16Utils;
import com.igpsport.blelib.utils.Command;
import com.igpsport.blelib.utils.PktConstants;
import com.igpsport.blelib.utils.StringUtils;
import com.swallowsonny.convertextlibrary.ByteArrayExtKt;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import no.nordicsemi.android.ble.data.Data;

/* JADX INFO: compiled from: FirmwareUpdateDelegate.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 22\u00020\u0001:\u00012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nJM\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u000f2%\b\u0002\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001aJ{\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\n2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u000f2%\b\u0002\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001aJ\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\nJ\\\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+2%\b\u0002\u0010,\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001a2%\b\u0002\u0010.\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001aJ\u0016\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001000\n2\u0006\u00101\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/igpsport/blelib/devicemanager/FirmwareUpdateDelegate;", "", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "<init>", "(Lcom/igpsport/blelib/IGPDeviceManager;)V", "iGPDeviceType", "Lcom/igpsport/blelib/device/IGPDevice;", "firstQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/igpsport/blelib/BaseCommand;", "fourthQueue", "readFirmwareVersion", "Lno/nordicsemi/android/ble/data/Data;", "retryTime", "", "(Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "sendEnterDFU", "", "sendFirmwareFileToDevice", "firmwareFile", "Ljava/io/File;", "firmwareFileMD5", "", "resumeOffset", "onProgress", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "progress", "", "Lcom/igpsport/blelib/DeviceReturnStatus;", "fileBytes", "", "fileName", "fileExtension", "totalFileSize", "isSupportSubPackage", "", "subPackageIndex", "rebootDevice", "updateFirmware", "firmwareData", "Lcom/igpsport/blelib/bean/FirmwareData;", "onFirmwareUpdateStatusResponse", "errorCode", "onFirmwareUpdateProgress", "getFileTransferModeInfo", "Lcom/igpsport/blelib/bean/GeneralFileOperation$file_transfer_method_information;", "fileMd5", "Companion", "blelib_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FirmwareUpdateDelegate {
    public static final Companion Companion = null;
    private final IGPDeviceManager deviceManager;
    private final ConcurrentLinkedQueue<BaseCommand<?>> firstQueue;
    private final ConcurrentLinkedQueue<BaseCommand<?>> fourthQueue;
    private final IGPDevice iGPDeviceType;

    /* JADX INFO: compiled from: FirmwareUpdateDelegate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/igpsport/blelib/devicemanager/FirmwareUpdateDelegate$Companion;", "", "<init>", "()V", "create", "Lcom/igpsport/blelib/devicemanager/FirmwareUpdateDelegate;", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "blelib_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private Companion() {
        }

        public final FirmwareUpdateDelegate create(IGPDeviceManager r3) {
            Intrinsics.checkNotNullParameter(r3, "deviceManager");
            return new FirmwareUpdateDelegate(r3, null);
        }
    }

    public static /* synthetic */ byte $r8$lambda$4ZZ5ZdRi0mDi9BcarrhZTFJZyo8(byte[] r0, byte[] r1) {
        return updateFirmware$lambda$18(r0, r1);
    }

    public static /* synthetic */ GeneralFileOperation.file_transfer_method_information $r8$lambda$5Yj_rL_DZui1LFI_vaQDh5DYSnQ(byte[] r0, byte[] r1) {
        return getFileTransferModeInfo$lambda$23(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$BbqHrfoV3nOBLb3IBiaQv2S8C5c(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return readFirmwareVersion$lambda$0(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$ChUWqnoliFBBVRiply3KzoTk-8Q, reason: not valid java name */
    public static /* synthetic */ Unit m11389$r8$lambda$ChUWqnoliFBBVRiply3KzoTk8Q(Ref.IntRef r0, byte[] r1, Function1 r2, byte[] r3) {
        return sendFirmwareFileToDevice$lambda$6(r0, r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$EUQ7Y9cTMuF3_FUKVvYxw9FA590(byte[] r0, byte[] r1) {
        return sendFirmwareFileToDevice$lambda$7(r0, r1);
    }

    public static /* synthetic */ Unit $r8$lambda$IMLHjrw2lZtU6n68_VNcrkFjb6k(FirmwareUpdateDelegate r0, BluetoothDevice r1, Byte r2, CommandError r3) {
        return updateFirmware$lambda$19(r0, r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$KZxkwUDragNPKYe2ovjZJ1hF_WQ(byte[] r0, byte[] r1) {
        return rebootDevice$lambda$13(r0, r1);
    }

    public static /* synthetic */ byte $r8$lambda$Tc7oF1Dna4SAKEi3pAzMhgNWKZk(byte[] r0, byte[] r1) {
        return sendFirmwareFileToDevice$lambda$8(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ZRVGeqwoyTDiZUwWhPa6O5qdXqc(Function1 r0, FirmwareUpdateDelegate r1, BaseCommand r2, Function1 r3, byte[] r4, byte[] r5) {
        return updateFirmware$lambda$17(r0, r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$_E05CU5Op7sMhFqSBjoBHCyPX48(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return sendEnterDFU$lambda$2(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$aY7Vg2KjIhC_8NZ02G2Tdk-Fi0Q, reason: not valid java name */
    public static /* synthetic */ DeviceReturnStatus m11390$r8$lambda$aY7Vg2KjIhC_8NZ02G2TdkFi0Q(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return sendFirmwareFileToDevice$lambda$12(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$aq3Q-3gAELmj_WgV0KG6yb7WB1M, reason: not valid java name */
    public static /* synthetic */ boolean m11391$r8$lambda$aq3Q3gAELmj_WgV0KG6yb7WB1M(byte[] r0, byte[] r1) {
        return sendFirmwareFileToDevice$lambda$11(r0, r1);
    }

    public static /* synthetic */ DeviceReturnStatus $r8$lambda$d6P0eTBaLrOj6oavdnsTG0sY05Y(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return rebootDevice$lambda$14(r0, r1, r2);
    }

    /* JADX INFO: renamed from: $r8$lambda$ktAWlX_EQ-esMAReMxWRD3rDnLA, reason: not valid java name */
    public static /* synthetic */ Byte m11392$r8$lambda$ktAWlX_EQesMAReMxWRD3rDnLA(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return sendEnterDFU$lambda$3(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$pwHZpvNUAES3MyuX2SYfmRhP5ew(Common.service_type_index r0, Common.SERVICE_OPERATE_TYPE r1, byte[] r2, byte[] r3) {
        return getFileTransferModeInfo$lambda$22(r0, r1, r2, r3);
    }

    /* JADX INFO: renamed from: $r8$lambda$wRDRqlyUK4SLQy900rsydej-HvQ, reason: not valid java name */
    public static /* synthetic */ Unit m11393$r8$lambda$wRDRqlyUK4SLQy900rsydejHvQ(Ref.IntRef r0, byte[] r1, Function1 r2, byte[] r3) {
        return sendFirmwareFileToDevice$lambda$10(r0, r1, r2, r3);
    }

    /* JADX INFO: renamed from: $r8$lambda$x55HlMgAo1NK-mO0kPvRcNJA4x4, reason: not valid java name */
    public static /* synthetic */ Data m11394$r8$lambda$x55HlMgAo1NKmO0kPvRcNJA4x4(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        return readFirmwareVersion$lambda$1(r0, r1, r2);
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ FirmwareUpdateDelegate(IGPDeviceManager r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    private FirmwareUpdateDelegate(IGPDeviceManager r2) {
        this.deviceManager = r2;
        this.iGPDeviceType = r2.getIGPDeviceType();
        this.firstQueue = r2.getFirstQueue();
        this.fourthQueue = r2.getFourthQueue();
    }

    public static /* synthetic */ BaseCommand readFirmwareVersion$default(FirmwareUpdateDelegate r0, Integer r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.readFirmwareVersion(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final BaseCommand<Data> readFirmwareVersion(Integer r13) {
        byte[] r1 = new byte[0];
        byte[] r2 = new byte[0];
        IGPDevice r3 = this.iGPDeviceType;
        if (r3 == null) goto L5;
        Integer r4 = Integer.valueOf(r3.getGeneration());
    L6:
        int r5 = 3;
        if (r4 != null) goto L10;
    L13:
        if (r4 != null) goto L16;
    L18:
        if (r4 != null) goto L21;
    L23:
        BaseCommand<Data> r6 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r1, r2);
        if (r13 == null) goto L26;
        r5 = r13.intValue();
    L26:
        r6.setRetryTime(r5);
        r6.setCommandTag(WriteCommand.ReadFirmwareVersionByPB);
        r6.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda3(this));
        r6.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda4(this));
        return r6;
    L21:
        if (r4.intValue() != 4) goto L23;
        Pair r7 = FirmwareUpgradeServiceFactory.getMessage$default(new FirmwareUpgradeServiceFactory(), Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_GET_VERSION, null, 2, null);
        r1 = StringUtils.byteMerger(new byte[][]{r7.getSecond(), r7.getFirst()});
        goto L23
    L16:
        if (r4.intValue() != 3) goto L18;
        Pair<byte[], byte[]> r0 = new DeviceVersionInfoServiceFactory().getMessage(DevVerInfo.OPERATE_TYPE.enum_OPERATE_TYPE_GET);
        r1 = r0.getFirst();
        r2 = r0.getSecond();
        goto L23
    L10:
        if (r4.intValue() != 2) goto L13;
        Pair r8 = FirmwareUpgradeServiceFactory.getMessage$default(new FirmwareUpgradeServiceFactory(), Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_GET_VERSION, null, 2, null);
        r1 = (byte[]) r8.getFirst();
        r2 = (byte[]) r8.getSecond();
        goto L23
    L5:
        r4 = null;
        goto L6
    }

    private static final boolean readFirmwareVersion$lambda$0(FirmwareUpdateDelegate r3, byte[] r4, byte[] r5) {
        Intrinsics.checkNotNullParameter(r3, "this$0");
        Intrinsics.checkNotNull(r4);
        TransmitCompleteCommand r6 = new TransmitCompleteCommand(r4);
        IGPDevice r7 = r3.iGPDeviceType;
        if (r7 == null) goto L5;
        Integer r8 = Integer.valueOf(r7.getGeneration());
    L7:
        if (r8 != null) goto L10;
    L11:
        if (r8 != null) goto L14;
    L16:
        if (r8 != null) goto L19;
    L25:
        return false;
    L19:
        if (r8.intValue() != 3) goto L25;
        if (r6.getMainService() != ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_DEV_VER_INFO.getNumber())) goto L25;
        if (r6.getMainOperation() != ((byte) DevVerInfo.OPERATE_TYPE.enum_OPERATE_TYPE_SEND.getNumber())) goto L25;
        return true;
    L14:
        if (r8.intValue() != 4) goto L16;
    L27:
        if (r6.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FIRMWARE.getNumber())) goto L29;
    L31:
        return false;
    L29:
        if (r6.getMainOperation() != ((byte) Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_SEND_VERSION.getNumber())) goto L31;
        return true;
    L10:
        if (r8.intValue() == 2) goto L27;
    L5:
        r8 = null;
        goto L7
    }

    private static final Data readFirmwareVersion$lambda$1(FirmwareUpdateDelegate r4, byte[] r5, byte[] r6) {
        Intrinsics.checkNotNullParameter(r4, "this$0");
        IGPDeviceManager r7 = r4.deviceManager;
        IGPDevice r0 = r4.iGPDeviceType;
        if (r0 == null) goto L5;
        Integer r1 = Integer.valueOf(r0.getGeneration());
    L6:
        r7.log("translation---- ", " iGPDeviceType?.generation = " + r1);
        IGPDevice r8 = r4.iGPDeviceType;
        if (r8 == null) goto L9;
        Integer r9 = Integer.valueOf(r8.getGeneration());
    L11:
        if (r9 != null) goto L14;
    L15:
        if (r9 != null) goto L18;
    L20:
        if (r9 != null) goto L23;
    L26:
        return null;
    L23:
        if (r9.intValue() != 3) goto L26;
        byte[] r10 = DevVerInfo.dev_ver_info_msg.parseFrom(r5).getVersionMessage().toByteArray();
        Intrinsics.checkNotNullExpressionValue(r10, "toByteArray(...)");
        return TransmitCompleteCommandKt.toData(r10);
    L18:
        if (r9.intValue() != 4) goto L20;
    L27:
        byte[] r11 = Firmware.firmware_msg.parseFrom(r5).getFirmwareDataMsg().toByteArray();
        Intrinsics.checkNotNullExpressionValue(r11, "toByteArray(...)");
        return TransmitCompleteCommandKt.toData(r11);
    L14:
        if (r9.intValue() == 2) goto L27;
    L9:
        r9 = null;
        goto L11
    L5:
        r1 = null;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final BaseCommand<Byte> sendEnterDFU() {
        IGPDevice r0 = this.iGPDeviceType;
        Integer r1 = null;
        if (r0 == null) goto L5;
        Object r2 = r0.getEnterDfuCommand();
    L6:
        Intrinsics.checkNotNull(r2, "null cannot be cast to non-null type kotlin.Pair<kotlin.ByteArray, kotlin.ByteArray>");
        Pair r3 = (Pair) r2;
        byte[] r4 = new byte[0];
        byte[] r5 = new byte[0];
        IGPDevice r6 = this.iGPDeviceType;
        if (r6 == null) goto L10;
        r1 = Integer.valueOf(r6.getGeneration());
    L10:
        if (r1 == null) goto L17;
        if (r1.intValue() != 1) goto L17;
        r4 = Command.Companion.getSendDfuCommand().invoke();
    L32:
        BaseCommand<Byte> r7 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r4, r5);
        r7.setCommandTag(WriteCommand.EnterDFU);
        r7.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda0(this));
        r7.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda8(this));
        IGPDevice r8 = this.iGPDeviceType;
        if (r8 != null) goto L35;
    L37:
        return r7;
    L35:
        if (r8.getGeneration() != 1) goto L37;
        r7.setRetryTime(0);
    L17:
        if (r1 != null) goto L20;
    L21:
        if (r1 != null) goto L24;
    L26:
        if (r1 == null) goto L32;
        if (r1.intValue() != 4) goto L32;
        r4 = StringUtils.byteMerger(new byte[][]{r3.getSecond(), r3.getFirst()});
        goto L32
    L24:
        if (r1.intValue() != 3) goto L26;
    L31:
        r4 = (byte[]) r3.getFirst();
        r5 = (byte[]) r3.getSecond();
        goto L32
    L20:
        if (r1.intValue() == 2) goto L31;
    L5:
        r2 = null;
        goto L6
    }

    private static final boolean sendEnterDFU$lambda$2(FirmwareUpdateDelegate r1, byte[] r2, byte[] r3) {
        Intrinsics.checkNotNullParameter(r1, "this$0");
        IGPDevice r4 = r1.iGPDeviceType;
        if (r4 != null) goto L5;
    L7:
        Intrinsics.checkNotNull(r2);
        TransmitCompleteCommand r5 = new TransmitCompleteCommand(r2);
        if (r5.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FIRMWARE.getNumber())) goto L10;
        return false;
    L10:
        if (r5.getMainOperation() != ((byte) Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_BLE_UPDATE.getNumber())) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (r4.getGeneration() != 1) goto L7;
        return true;
    }

    private static final Byte sendEnterDFU$lambda$3(FirmwareUpdateDelegate r5, byte[] r6, byte[] r7) {
        Intrinsics.checkNotNullParameter(r5, "this$0");
        IGPDevice r0 = r5.iGPDeviceType;
        if (r0 != null) goto L5;
    L19:
        Intrinsics.checkNotNull(r7);
        return Byte.valueOf(new ConfirmCommand(r7).getStatus());
    L5:
        if (r0.getGeneration() != 1) goto L19;
        IGPDeviceManager r8 = r5.deviceManager;
        if (r6 == null) goto L9;
        String r1 = ByteArrayExtKt.toHexString$default(r6, false, 1, null);
    L11:
        if (r6 == null) goto L13;
        Byte r2 = Byte.valueOf(r6[2]);
    L14:
        r8.log("NEW SDK", " data = " + r1 + " , " + r2);
        if (r6 != null) goto L17;
        return null;
    L17:
        return Byte.valueOf(r6[2]);
    L13:
        r2 = null;
        goto L14
    L9:
        r1 = null;
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BaseCommand sendFirmwareFileToDevice$default(FirmwareUpdateDelegate r0, File r1, String r2, int r3, Function1 r4, int r5, Object r6) {
        if ((r5 & 4) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 8) == 0) goto L9;
        r4 = null;
    L9:
        return r0.sendFirmwareFileToDevice(r1, r2, r3, r4);
    }

    public final BaseCommand<Byte> sendFirmwareFileToDevice(File r17, String r18, int r19, final Function1<? super Integer, Unit> r20) {
        Intrinsics.checkNotNullParameter(r17, "firmwareFile");
        Intrinsics.checkNotNullParameter(r18, "firmwareFileMD5");
        Head20BytesInfo r3 = new Head20BytesInfo();
        r3.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION);
        r3.modifyMainOperationByte((byte) Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD.getNumber());
        byte[] r4 = r3.confirmCommandByteArray();
        byte[] r5 = FilesKt.readBytes(r17);
        int r6 = r5.length + r19;
        byte[] r7 = new TransmitCompleteCommand(r4).getByteArray();
        r7[3] = PktConstants.HEAD_CODE;
        this.deviceManager.log("mcu 文件名称 ", HexStringBuilder.DEFAULT_SEPARATOR + FilesKt.getNameWithoutExtension(r17));
        String r8 = FilesKt.getNameWithoutExtension(r17);
        String r9 = r8;
        if (StringsKt.contains$default(r9, "iGS", false, 2, null) == false) goto L10;
        if (StringsKt.startsWith$default(r8, "iGS", false, 2, null) == true) goto L21;
        r8 = r8.substring(StringsKt.indexOf$default(r9, CmcdHeadersFactory.OBJECT_TYPE_INIT_SEGMENT, 0, false, 6, null));
        Intrinsics.checkNotNullExpressionValue(r8, "substring(...)");
    L21:
        String r10 = StringsKt.replace$default(r8, "_resume", "", false, 4, null);
        this.deviceManager.log("断点续传->  ", "mcu 文件名称: " + FilesKt.getNameWithoutExtension(r17) + ", fileName = " + r10 + ", totalFileSize = " + r6 + ", resumeOffset = " + r19 + ", fileBytesSize = " + r5.length);
        byte[] r1 = GeneralFileOperation.general_file_operation.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION).setOperateType(Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD).setFileType(GeneralFileOperation.file_operation_type.enum_FILE_TYPE_FIRMWARE).setFileId(0).setFileExtension(FilesKt.getExtension(r17)).setFileName(r10).setFileSize(r6).setFileMd5(r18).setMethodInformation(GeneralFileOperation.file_transfer_method_information.newBuilder().setResumeOffset(r19).build()).build().toByteArray();
        final byte[] r11 = StringUtils.byteMerger(new byte[][]{BaseFactory.Companion.updateCRC(r7), ByteArrayExtKt.writeInt32BE$default(new byte[4], r1.length, 0, 2, null), r1, FilesKt.readBytes(r17)});
        IGPDeviceManager r12 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r13 = this.fourthQueue;
        Intrinsics.checkNotNull(r11);
        BaseCommand<Byte> r2 = new BaseCommand(r12, r13, 4, r11, new byte[0]);
        r2.setRetryTime(0);
        r2.setCommandTag(WriteCommand.UpdateFirmware);
        final Ref.IntRef r14 = new Ref.IntRef();
        r2.setOnSendDataListener(new FirmwareUpdateDelegate$$ExternalSyntheticLambda11(r14, r11, r20));
        r2.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda12());
        r2.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda13());
        return r2;
    L10:
        if (StringsKt.contains$default(r9, "BSC", false, 2, null) == false) goto L16;
        if (StringsKt.startsWith$default(r8, "BSC", false, 2, null) == true) goto L21;
        r8 = r8.substring(StringsKt.indexOf$default(r9, "B", 0, false, 6, null));
        Intrinsics.checkNotNullExpressionValue(r8, "substring(...)");
        goto L21
    L16:
        if (StringsKt.contains$default(r9, "Bi", false, 2, null) == false) goto L21;
        if (StringsKt.startsWith$default(r8, "Bi", false, 2, null) == true) goto L21;
        r8 = r8.substring(StringsKt.indexOf$default(r9, "B", 0, false, 6, null));
        Intrinsics.checkNotNullExpressionValue(r8, "substring(...)");
        goto L21
    }

    private static final Unit sendFirmwareFileToDevice$lambda$6(Ref.IntRef r4, byte[] r5, Function1 r6, byte[] r7) {
        Intrinsics.checkNotNullParameter(r4, "$currentSendDataSize");
        int r0 = r4.element;
        if (r7 == null) goto L5;
        int r8 = r7.length;
    L6:
        r4.element = r0 + r8;
        int r9 = (int) ((((double) r4.element) * 100.0d) / ((double) r5.length));
        if (r6 == null) goto L10;
        r6.invoke(Integer.valueOf(r9));
    L10:
        return Unit.INSTANCE;
    L5:
        r8 = 0;
        goto L6
    }

    private static final boolean sendFirmwareFileToDevice$lambda$7(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getMainOperation() != ((byte) Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD.getNumber())) goto L10;
        return true;
    L10:
        return false;
    }

    private static final byte sendFirmwareFileToDevice$lambda$8(byte[] r0, byte[] r1) {
        Intrinsics.checkNotNull(r1);
        return new ConfirmCommand(r1).getStatus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BaseCommand sendFirmwareFileToDevice$default(FirmwareUpdateDelegate r1, byte[] r2, String r3, String r4, int r5, String r6, int r7, boolean r8, int r9, Function1 r10, int r11, Object r12) {
        if ((r11 & 32) == 0) goto L6;
        r7 = 0;
    L6:
        if ((r11 & 64) == 0) goto L9;
        r8 = false;
    L9:
        if ((r11 & 128) == 0) goto L12;
        r9 = 0;
    L12:
        if ((r11 & 256) == 0) goto L15;
        r10 = null;
    L15:
        return r1.sendFirmwareFileToDevice(r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    private static final byte[] sendFirmwareFileToDevice$buildSubPackageHeader(byte[] r8, int r9, int r10) {
        byte[] r11 = StringUtils.byteMerger(new byte[][]{new byte[]{90, -91, -61, 60}, CRC16Utils.INSTANCE.calculateCRC16Bytes(r8), new byte[]{(byte) (r10 & 255), (byte) ((r10 >> 8) & 255)}, new byte[]{(byte) (r9 & 255), (byte) ((r9 >> 8) & 255), (byte) ((r9 >> 16) & 255), (byte) ((r9 >> 24) & 255)}});
        Intrinsics.checkNotNullExpressionValue(r11, "byteMerger(...)");
        return r11;
    }

    public final BaseCommand<DeviceReturnStatus> sendFirmwareFileToDevice(byte[] r21, String r22, String r23, int r24, String r25, int r26, boolean r27, int r28, final Function1<? super Integer, Unit> r29) {
        Intrinsics.checkNotNullParameter(r21, "fileBytes");
        Intrinsics.checkNotNullParameter(r22, "fileName");
        Intrinsics.checkNotNullParameter(r23, "fileExtension");
        Intrinsics.checkNotNullParameter(r25, "firmwareFileMD5");
        if (r28 != 0) goto L8;
        Head20BytesInfo r10 = new Head20BytesInfo();
        r10.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION);
        r10.modifyMainOperationByte((byte) Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD.getNumber());
        byte[] r11 = new TransmitCompleteCommand(r10.confirmCommandByteArray()).getByteArray();
        r11[3] = PktConstants.HEAD_CODE;
        this.deviceManager.log("断点续传+分包->  ", "mcu 文件名称: " + r22 + ", fileName = " + r22 + ", totalFileSize = " + r24 + ", resumeOffset = " + r26 + ", isSupportSubPackage = " + r27 + ", fileBytesSize = " + r21.length);
        byte[] r2 = GeneralFileOperation.general_file_operation.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION).setOperateType(Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD).setFileType(GeneralFileOperation.file_operation_type.enum_FILE_TYPE_FIRMWARE).setFileId(0).setFileExtension(r23).setFileName(r22).setFileSize(r24).setFileMd5(r25).setMethodInformation(GeneralFileOperation.file_transfer_method_information.newBuilder().setResumeOffset(r26).setPacketAckSupported(r27).build()).build().toByteArray();
        byte[] r3 = ByteArrayExtKt.writeInt32BE$default(new byte[4], r2.length, 0, 2, null);
        byte[] r4 = sendFirmwareFileToDevice$buildSubPackageHeader(r21, r21.length, r28);
        this.deviceManager.log("断点续传+分包->  ", "headData: " + ByteArrayExtKt.toHexString$default(r11, false, 1, null));
        this.deviceManager.log("断点续传+分包->  ", "小包头: " + ByteArrayExtKt.toHexString$default(r4, false, 1, null));
        if (r27 == false) goto L7;
        byte[] r1 = StringUtils.byteMerger(new byte[][]{BaseFactory.Companion.updateCRC(r11), r3, r2, r4, r21});
    L9:
        final byte[] r210 = r1;
        BaseCommand<DeviceReturnStatus> r5 = new BaseCommand(this.deviceManager, this.fourthQueue, 4, r210, new byte[0]);
        r5.setRetryTime(0);
        r5.setCommandTag(WriteCommand.UpdateFirmware);
        final Ref.IntRef r6 = new Ref.IntRef();
        r5.setOnSendDataListener(new FirmwareUpdateDelegate$$ExternalSyntheticLambda16(r6, r210, r29));
        r5.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda1());
        r5.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda2(this));
        return r5;
    L7:
        r1 = StringUtils.byteMerger(new byte[][]{BaseFactory.Companion.updateCRC(r11), r3, r2, r21});
        goto L9
    L8:
        r1 = StringUtils.byteMerger(new byte[][]{sendFirmwareFileToDevice$buildSubPackageHeader(r21, r21.length, r28), r21});
        goto L9
    }

    private static final Unit sendFirmwareFileToDevice$lambda$10(Ref.IntRef r4, byte[] r5, Function1 r6, byte[] r7) {
        Intrinsics.checkNotNullParameter(r4, "$currentSendDataSize");
        Intrinsics.checkNotNullParameter(r5, "$requestData");
        int r0 = r4.element;
        if (r7 == null) goto L5;
        int r8 = r7.length;
    L6:
        r4.element = r0 + r8;
        int r9 = (int) ((((double) r4.element) * 100.0d) / ((double) r5.length));
        if (r6 == null) goto L10;
        r6.invoke(Integer.valueOf(r9));
    L10:
        return Unit.INSTANCE;
    L5:
        r8 = 0;
        goto L6
    }

    private static final boolean sendFirmwareFileToDevice$lambda$11(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getMainOperation() != ((byte) Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_ADD.getNumber())) goto L10;
        return true;
    L10:
        return false;
    }

    private static final DeviceReturnStatus sendFirmwareFileToDevice$lambda$12(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Byte r3 = r0.deviceManager.getCommandStatus(r2);
        return DeviceReturnStatus.Companion.fromStatus(r3);
    }

    public final BaseCommand<DeviceReturnStatus> rebootDevice() {
        Integer r2 = null;
        Pair<byte[], byte[]> r0 = new FirmwareUpgradeServiceFactory().getMessage(Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_MCU_UPDATE, null);
        byte[] r3 = new byte[0];
        byte[] r9 = new byte[0];
        IGPDevice r4 = this.iGPDeviceType;
        if (r4 == null) goto L5;
        r2 = Integer.valueOf(r4.getGeneration());
    L5:
        if (r2 != null) goto L8;
    L10:
        BaseCommand<DeviceReturnStatus> r5 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r3, r9);
        r5.setCommandTag(WriteCommand.RebootDevice);
        r5.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda9());
        r5.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda10(this));
        return r5;
    L8:
        if (r2.intValue() != 4) goto L10;
        r3 = StringUtils.byteMerger(new byte[][]{r0.getSecond(), r0.getFirst()});
        goto L10
    }

    private static final boolean rebootDevice$lambda$13(byte[] r1, byte[] r2) {
        Intrinsics.checkNotNull(r1);
        TransmitCompleteCommand r3 = new TransmitCompleteCommand(r1);
        if (r3.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FIRMWARE.getNumber())) goto L5;
        return false;
    L5:
        if (r3.getMainOperation() != ((byte) Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_MCU_UPDATE.getNumber())) goto L10;
        return true;
    L10:
        return false;
    }

    private static final DeviceReturnStatus rebootDevice$lambda$14(FirmwareUpdateDelegate r0, byte[] r1, byte[] r2) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        Byte r3 = r0.deviceManager.getCommandStatus(r2);
        return DeviceReturnStatus.Companion.fromStatus(r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateFirmware$default(FirmwareUpdateDelegate r1, FirmwareData r2, Function1 r3, Function1 r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r5 & 4) == 0) goto L8;
        r4 = null;
    L8:
        r1.updateFirmware(r2, r3, r4);
    }

    public final void updateFirmware(FirmwareData r7, final Function1<? super Byte, Unit> r8, final Function1<? super Integer, Unit> r9) {
        Intrinsics.checkNotNullParameter(r7, "firmwareData");
        Pair<byte[], byte[]> r10 = new FirmwareUpgradeServiceFactory().getMessage(Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_MCU_UPDATE, r7);
        final BaseCommand r0 = new BaseCommand(this.deviceManager, this.firstQueue, 1, r10.getFirst(), r10.getSecond());
        r0.setCommandTag(WriteCommand.UpdateFirmware);
        r0.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda5(r8, this, r0, r9));
        r0.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda6());
        r0.enqueue(new FirmwareUpdateDelegate$$ExternalSyntheticLambda7(this));
    }

    private static final boolean updateFirmware$lambda$17(Function1 r4, FirmwareUpdateDelegate r5, BaseCommand r6, Function1 r7, byte[] r8, byte[] r9) {
        Intrinsics.checkNotNullParameter(r5, "this$0");
        Intrinsics.checkNotNullParameter(r6, "$command");
        Intrinsics.checkNotNull(r8);
        TransmitCompleteCommand r0 = new TransmitCompleteCommand(r8);
        if (r0.getMainService() != ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_FIRMWARE.getNumber())) goto L19;
        byte r1 = r0.getMainOperation();
        if (r1 != ((byte) Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_MCU_UPDATE.getNumber())) goto L12;
        if (r4 == null) goto L8;
        r4.invoke(Byte.valueOf(new ConfirmCommand(r8).getStatus()));
    L8:
        r5.deviceManager.log("NEW SDK ", "MCU updateFirmware-- UpdateStatus = " + new ConfirmCommand(r8).getStatus());
        if (new ConfirmCommand(r8).getStatus() == 0) goto L21;
        return true;
    L21:
        return false;
    L12:
        if (r1 != ((byte) Firmware.FIRMWARE_OPERATE_TYPE.enum_FIRMWARE_OPERATE_TYPE_PROGRESS.getNumber())) goto L22;
        Firmware.firmware_msg r10 = Firmware.firmware_msg.parseFrom(r9);
        r6.clearStoredData();
        if (r7 == null) goto L16;
        r7.invoke(Integer.valueOf(r10.getFirmwareDataMsg().getProcess()));
    L16:
        r5.deviceManager.log("NEW SDK ", "MCU updateFirmware-- Progress = " + r10.getFirmwareDataMsg().getProcess());
        if (r10.getFirmwareDataMsg().getProcess() != 100) goto L23;
        return true;
    L23:
        return false;
    L22:
        return false;
    L19:
        return false;
    }

    private static final byte updateFirmware$lambda$18(byte[] r0, byte[] r1) {
        Intrinsics.checkNotNull(r1);
        return new ConfirmCommand(r1).getStatus();
    }

    private static final Unit updateFirmware$lambda$19(FirmwareUpdateDelegate r0, BluetoothDevice r1, Byte r2, CommandError r3) {
        Intrinsics.checkNotNullParameter(r0, "this$0");
        r0.deviceManager.log("NEW SDK ", "MCU updateFirmware-- 结束");
        return Unit.INSTANCE;
    }

    public final BaseCommand<GeneralFileOperation.file_transfer_method_information> getFileTransferModeInfo(String r11) {
        Intrinsics.checkNotNullParameter(r11, "fileMd5");
        final Common.service_type_index r0 = Common.service_type_index.enum_SERVICE_TYPE_INDEX_FILE_OPERATION;
        final Common.SERVICE_OPERATE_TYPE r1 = Common.SERVICE_OPERATE_TYPE.enum_SERVICE_OPERATE_TYPE_GET;
        GeneralFileOperation.file_operation_type r2 = GeneralFileOperation.file_operation_type.enum_FILE_TYPE_FIRMWARE;
        byte[] r12 = GeneralFileOperation.general_file_operation.newBuilder().setServiceType(r0).setOperateType(r1).setFileType(r2).setFileSize(0).setFileId(0).setFileMd5(r11).build().toByteArray();
        Head20BytesInfo r3 = new Head20BytesInfo();
        r3.setMainServiceType(r0);
        r3.modifyMainOperationByte((byte) r1.getNumber());
        Intrinsics.checkNotNull(r12);
        r3.setPbDataBytes(r12);
        byte[] r4 = new TransmitCompleteCommand(r3.confirmCommandByteArray()).getByteArray();
        r4[3] = PktConstants.HEAD_CODE;
        byte[] r8 = StringUtils.byteMerger(new byte[][]{BaseFactory.Companion.updateCRC(r4), r12});
        IGPDeviceManager r5 = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> r6 = this.fourthQueue;
        Intrinsics.checkNotNull(r8);
        BaseCommand<GeneralFileOperation.file_transfer_method_information> r7 = new BaseCommand(r5, r6, 4, r8, new byte[0]);
        r7.setCommandTag(WriteCommand.GetFileTransferModeInfo);
        r7.setReceiveFinish(new FirmwareUpdateDelegate$$ExternalSyntheticLambda14(r0, r1));
        r7.setTranslation(new FirmwareUpdateDelegate$$ExternalSyntheticLambda15());
        return r7;
    }

    private static final boolean getFileTransferModeInfo$lambda$22(Common.service_type_index r0, Common.SERVICE_OPERATE_TYPE r1, byte[] r2, byte[] r3) {
        Intrinsics.checkNotNullParameter(r0, "$serviceType");
        Intrinsics.checkNotNullParameter(r1, "$operationType");
        Result.Companion r4 = Result.Companion;     // Catch: Throwable -> L5
        Intrinsics.checkNotNull(r2);     // Catch: Throwable -> L5
        Object r5 = Result.m15480constructorimpl(new TransmitCompleteCommand(r2));     // Catch: Throwable -> L5
    L8:
        if (Result.m15486isFailureimpl(r5) == false) goto L10;
        r5 = null;
    L10:
        TransmitCompleteCommand r6 = (TransmitCompleteCommand) r5;
        if (r6 != null) goto L13;
    L17:
        return false;
    L13:
        if (r6.getMainService() != ((byte) r0.getNumber())) goto L17;
        if (r6.getMainOperation() != ((byte) r1.getNumber())) goto L17;
        return true;
    L5:
        th = move-exception;
        Result.Companion r7 = Result.Companion;
        r5 = Result.m15480constructorimpl(ResultKt.createFailure(th));
        goto L8
    }

    private static final GeneralFileOperation.file_transfer_method_information getFileTransferModeInfo$lambda$23(byte[] r0, byte[] r1) {
        return GeneralFileOperation.general_file_operation.parseFrom(r0).getMethodInformation();
    L4:
        return null;
    }
}