package com.igpsport.blelib.devicemanager;

import androidx.exifinterface.media.ExifInterface;
import com.google.protobuf.InvalidProtocolBufferException;
import com.igpsport.blelib.BaseCommand;
import com.igpsport.blelib.DeviceReturnStatus;
import com.igpsport.blelib.IGPDeviceManager;
import com.igpsport.blelib.WriteCommand;
import com.igpsport.blelib.bean.Common;
import com.igpsport.blelib.bean.Config;
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
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: ConfigurationDelegate.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 m2\u00020\u0001:\u0001mB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0012\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0013J4\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\n\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010 \u001a\u00020!J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\nJ%\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020%0$\"\u00020%¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0(0\nJ1\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020*0$\"\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010-J/\u0010.\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0(\u0012\u0004\u0012\u0002000/0\n2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u00101J\f\u00102\u001a\b\u0012\u0004\u0012\u0002000\nJ\u001d\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001040\n2\b\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u00101J%\u00105\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u0002060$\"\u000206¢\u0006\u0002\u00107J%\u00108\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u0002060$\"\u000206¢\u0006\u0002\u00107J\u0012\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060(0\nJ#\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0(0\n2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u00101J1\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0$\"\u00020;2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010>J#\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0(0\n2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u00101J1\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u00020@0$\"\u00020@2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010BJ\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\nJ\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010F\u001a\u00020GJ\u0014\u0010H\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020I\u0018\u00010(0\nJ\"\u0010J\u001a\u001e\u0012\u001a\u0012\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0(\u0012\u0006\u0012\u0004\u0018\u00010K\u0018\u00010/0\nJ\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010M\u001a\u00020IJ\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010M\u001a\u00020IJ\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010M\u001a\u00020IJ%\u0010P\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u0002060$\"\u000206¢\u0006\u0002\u00107J\u000e\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010R0\nJ\u0014\u0010S\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010T\u001a\u00020RJ1\u0010U\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u00020V0$\"\u00020V2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010WJ%\u0010X\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0012\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u00020Y0$\"\u00020Y¢\u0006\u0002\u0010ZJ\u0012\u0010[\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0(0\nJ#\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0(0\n2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u00101J\u0014\u0010]\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020^\u0018\u00010(0\nJ\u001e\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\n2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u001fJ\u000e\u0010c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0\nJ\u0016\u0010d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\n2\u0006\u0010e\u001a\u00020,J\u001b\u0010f\u001a\b\u0012\u0004\u0012\u00020\u001f0\n2\u0006\u0010g\u001a\u00020hH\u0000¢\u0006\u0002\biJ\f\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\nJ\u000e\u0010l\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006n"}, d2 = {"Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate;", "", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "<init>", "(Lcom/igpsport/blelib/IGPDeviceManager;)V", "iGPDeviceType", "Lcom/igpsport/blelib/device/IGPDevice;", "firstQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/igpsport/blelib/BaseCommand;", "_onDeviceNeedAccidentDetect", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "onDeviceNeedAccidentDetect", "Lkotlinx/coroutines/flow/SharedFlow;", "getOnDeviceNeedAccidentDetect", "()Lkotlinx/coroutines/flow/SharedFlow;", "emitDeviceNeedAccidentDetect", "emitDeviceNeedAccidentDetect$blelib_release", "getCommand", ExifInterface.GPS_DIRECTION_TRUE, "configServiceType", "Lcom/igpsport/blelib/bean/Config$CONFIG_SERVICE_TYPE;", "configOperateType", "Lcom/igpsport/blelib/bean/Config$CONFIG_OPERATE_TYPE;", "commandTag", "Lcom/igpsport/blelib/WriteCommand;", "configMsg", "Lcom/igpsport/blelib/bean/Config$config_msg$Builder;", "writeUserInformation", "", "config", "Lcom/igpsport/blelib/bean/Config$user_data_msg;", "readUserInformation", "writeUnit", "", "Lcom/igpsport/blelib/bean/Config$unit_msg;", "([Lcom/igpsport/blelib/bean/Config$unit_msg;)Lcom/igpsport/blelib/BaseCommand;", "readUnit", "", "writePage", "Lcom/igpsport/blelib/bean/Config$page_msg;", "curOperateMode", "", "([Lcom/igpsport/blelib/bean/Config$page_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readPage", "Lkotlin/Pair;", "Lcom/igpsport/blelib/bean/Config$cur_page_status_msg;", "(Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readPageInformation", "resetPageToDefault", "Lcom/igpsport/blelib/DeviceReturnStatus;", "addBikeInformation", "Lcom/igpsport/blelib/bean/Config$bike_msg;", "([Lcom/igpsport/blelib/bean/Config$bike_msg;)Lcom/igpsport/blelib/BaseCommand;", "deleteBikeInformation", "readBikeInformation", "readAlarmConfig", "Lcom/igpsport/blelib/bean/Config$alarm_msg;", "writeAlarmConfig", "configs", "([Lcom/igpsport/blelib/bean/Config$alarm_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readLapConfig", "Lcom/igpsport/blelib/bean/Config$lap_msg;", "writeLapConfig", "([Lcom/igpsport/blelib/bean/Config$lap_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "readSoundSetting", "Lcom/igpsport/blelib/bean/Config$config_msg;", "writeSoundSetting", "soundSetMsg", "Lcom/igpsport/blelib/bean/Config$sound_set_msg;", "readAllRidingMode", "Lcom/igpsport/blelib/bean/Config$mode_msg;", "readRidingModeTemplate", "Lcom/igpsport/blelib/bean/Config$mode_config_msg;", "modifyRidingMode", "mode", "addRidingMode", "deleteRidingMode", "writeBikeInformation", "readDisplaySettings", "Lcom/igpsport/blelib/bean/Config$backlight_msg;", "writeDisplaySettings", "displaySettings", "writeAutomaticSetting", "Lcom/igpsport/blelib/bean/Config$auto_set_msg;", "([Lcom/igpsport/blelib/bean/Config$auto_set_msg;Ljava/lang/Integer;)Lcom/igpsport/blelib/BaseCommand;", "writeKeySetting", "Lcom/igpsport/blelib/bean/Config$key_set_msg;", "([Lcom/igpsport/blelib/bean/Config$key_set_msg;)Lcom/igpsport/blelib/BaseCommand;", "readKeySetting", "readAutomaticSetting", "readDataProcessingSettings", "Lcom/igpsport/blelib/bean/Config$data_msg;", "dataProcessingFilter0Value", "dataType", "Lcom/igpsport/blelib/bean/Config$DATA_TYPE;", "isFilter", "readAltitude", "writeAltitude", CommonUtils.TYPE_UNIT_ALTITUDE, "writeTimeFormat", "timeFormat", "Lcom/igpsport/blelib/bean/Config$TIME_SYSTEM_TYPE;", "writeTimeFormat$blelib_release", "readDevicePassword", "", "readAccidentDetectionSwitch", "Companion", "blelib_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ConfigurationDelegate {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MutableSharedFlow<Unit> _onDeviceNeedAccidentDetect;
    private final IGPDeviceManager deviceManager;
    private final ConcurrentLinkedQueue<BaseCommand<?>> firstQueue;
    private final IGPDevice iGPDeviceType;
    private final SharedFlow<Unit> onDeviceNeedAccidentDetect;

    public /* synthetic */ ConfigurationDelegate(IGPDeviceManager iGPDeviceManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(iGPDeviceManager);
    }

    private ConfigurationDelegate(IGPDeviceManager iGPDeviceManager) {
        this.deviceManager = iGPDeviceManager;
        this.iGPDeviceType = iGPDeviceManager.getIGPDeviceType();
        this.firstQueue = iGPDeviceManager.getFirstQueue();
        MutableSharedFlow<Unit> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 6, null);
        this._onDeviceNeedAccidentDetect = mutableSharedFlowMutableSharedFlow$default;
        this.onDeviceNeedAccidentDetect = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
    }

    public final SharedFlow<Unit> getOnDeviceNeedAccidentDetect() {
        return this.onDeviceNeedAccidentDetect;
    }

    public final void emitDeviceNeedAccidentDetect$blelib_release() {
        BuildersKt__Builders_commonKt.launch$default(this.deviceManager.getManagerScope(), null, null, new ConfigurationDelegate$emitDeviceNeedAccidentDetect$1(this, null), 3, null);
    }

    /* JADX INFO: compiled from: ConfigurationDelegate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate$Companion;", "", "<init>", "()V", "create", "Lcom/igpsport/blelib/devicemanager/ConfigurationDelegate;", "deviceManager", "Lcom/igpsport/blelib/IGPDeviceManager;", "blelib_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConfigurationDelegate create(IGPDeviceManager deviceManager) {
            Intrinsics.checkNotNullParameter(deviceManager, "deviceManager");
            return new ConfigurationDelegate(deviceManager, null);
        }
    }

    private final <T> BaseCommand<T> getCommand(final Config.CONFIG_SERVICE_TYPE configServiceType, final Config.CONFIG_OPERATE_TYPE configOperateType, WriteCommand commandTag, Config.config_msg.Builder configMsg) {
        final Common.service_type_index service_type_indexVar = Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG;
        byte[] byteArray = configMsg.setServiceType(service_type_indexVar).setConfigSeviceType(configServiceType).setConfigOperateType(configOperateType).build().toByteArray();
        Head20BytesInfo head20BytesInfo = new Head20BytesInfo();
        head20BytesInfo.setMainServiceType(service_type_indexVar);
        head20BytesInfo.modifySecondServiceByte((byte) configServiceType.getNumber());
        head20BytesInfo.modifySecondOperationByte((byte) configOperateType.getNumber());
        Intrinsics.checkNotNull(byteArray);
        head20BytesInfo.setPbDataBytes(byteArray);
        byte[] bArrByteMerger = StringUtils.byteMerger(head20BytesInfo.confirmCommandByteArray(), byteArray);
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<T> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setCommandTag(commandTag);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda61
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.getCommand$lambda$2(this.f$0, service_type_indexVar, configServiceType, configOperateType, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getCommand$lambda$2(ConfigurationDelegate configurationDelegate, Common.service_type_index service_type_indexVar, Config.CONFIG_SERVICE_TYPE config_service_type, Config.CONFIG_OPERATE_TYPE config_operate_type, byte[] bArr, byte[] bArr2) {
        Object objM20711constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull(bArr);
            objM20711constructorimpl = Result.m20711constructorimpl(new TransmitCompleteCommand(bArr));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM20711constructorimpl = Result.m20711constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m20717isFailureimpl(objM20711constructorimpl)) {
            objM20711constructorimpl = null;
        }
        TransmitCompleteCommand transmitCompleteCommand = (TransmitCompleteCommand) objM20711constructorimpl;
        return transmitCompleteCommand != null && transmitCompleteCommand.getMainService() == ((byte) service_type_indexVar.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) config_service_type.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) config_operate_type.getNumber());
    }

    public final BaseCommand<Boolean> writeUserInformation(Config.user_data_msg config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setUserDataMessage(config).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteUserInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeUserInformation$lambda$3((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeUserInformation$lambda$4(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeUserInformation$lambda$3(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeUserInformation$lambda$4(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Config.user_data_msg> readUserInformation() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Config.user_data_msg> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadUserInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readUserInformation$lambda$5((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readUserInformation$lambda$6((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readUserInformation$lambda$5(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        if (transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_USER.getNumber())) {
            return transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber()) || transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Config.user_data_msg readUserInformation$lambda$6(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getUserDataMessage();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Config.user_data_msg.newBuilder().build();
        }
    }

    public final BaseCommand<Boolean> writeUnit(Config.unit_msg... config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllUnitMessage(ArraysKt.asIterable(config)).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteUnit);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeUnit$lambda$7((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeUnit$lambda$8(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeUnit$lambda$7(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeUnit$lambda$8(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<List<Config.unit_msg>> readUnit() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.unit_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadUnit);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readUnit$lambda$9((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readUnit$lambda$10((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readUnit$lambda$9(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_UNIT.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readUnit$lambda$10(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getUnitMessageList();
        } catch (InvalidProtocolBufferException unused) {
            return CollectionsKt.listOf(Config.unit_msg.newBuilder().build());
        }
    }

    public static /* synthetic */ BaseCommand writePage$default(ConfigurationDelegate configurationDelegate, Config.page_msg[] page_msgVarArr, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        return configurationDelegate.writePage(page_msgVarArr, num);
    }

    public final BaseCommand<Boolean> writePage(Config.page_msg[] config, Integer curOperateMode) {
        Intrinsics.checkNotNullParameter(config, "config");
        ComputerConfigureServiceFactory computerConfigureServiceFactory = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        builderNewBuilder.addAllPageMessage(ArraysKt.asIterable(config));
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = computerConfigureServiceFactory.getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WritePage);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writePage$lambda$12((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writePage$lambda$13(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writePage$lambda$12(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writePage$lambda$13(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ BaseCommand readPage$default(ConfigurationDelegate configurationDelegate, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return configurationDelegate.readPage(num);
    }

    public final BaseCommand<Pair<List<Config.page_msg>, Config.cur_page_status_msg>> readPage(Integer curOperateMode) {
        ComputerConfigureServiceFactory computerConfigureServiceFactory = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = computerConfigureServiceFactory.getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Pair<List<Config.page_msg>, Config.cur_page_status_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadPage);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda66
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readPage$lambda$15(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda67
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readPage$lambda$16((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readPage$lambda$15(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        configurationDelegate.deviceManager.log("--isReceiveFinish-- ", ((int) transmitCompleteCommand.getMainService()) + "," + ((int) transmitCompleteCommand.getSecondService()) + "," + ((int) transmitCompleteCommand.getMainOperation()));
        IGPDevice iGPDevice = configurationDelegate.iGPDeviceType;
        if (iGPDevice == null || iGPDevice.getGeneration() != 4) {
            return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber());
        }
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair readPage$lambda$16(byte[] bArr, byte[] bArr2) {
        List<Config.page_msg> listListOf;
        Config.cur_page_status_msg cur_page_status_msgVarBuild;
        try {
            listListOf = Config.config_msg.parseFrom(bArr).getPageMessageList();
        } catch (InvalidProtocolBufferException unused) {
            listListOf = CollectionsKt.listOf(Config.page_msg.newBuilder().build());
        }
        try {
            cur_page_status_msgVarBuild = Config.config_msg.parseFrom(bArr).getPageStatusMessage();
        } catch (InvalidProtocolBufferException unused2) {
            cur_page_status_msgVarBuild = Config.cur_page_status_msg.newBuilder().build();
        }
        return new Pair(listListOf, cur_page_status_msgVarBuild);
    }

    public final BaseCommand<Config.cur_page_status_msg> readPageInformation() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO).build());
        BaseCommand<Config.cur_page_status_msg> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, message.getFirst(), message.getSecond());
        baseCommand.setCommandTag(WriteCommand.ReadPageInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readPageInformation$lambda$17((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readPageInformation$lambda$18((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readPageInformation$lambda$17(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Config.cur_page_status_msg readPageInformation$lambda$18(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getPageStatusMessage();
        } catch (InvalidProtocolBufferException unused) {
            return Config.cur_page_status_msg.newBuilder().build();
        }
    }

    public final BaseCommand<DeviceReturnStatus> resetPageToDefault(Integer curOperateMode) {
        ComputerConfigureServiceFactory computerConfigureServiceFactory = new ComputerConfigureServiceFactory();
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET_DEFAULT);
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = computerConfigureServiceFactory.getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<DeviceReturnStatus> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadPage);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.resetPageToDefault$lambda$20(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.resetPageToDefault$lambda$21(this.f$0, (byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean resetPageToDefault$lambda$20(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        configurationDelegate.deviceManager.log("--isReceiveFinish-- ", ((int) transmitCompleteCommand.getMainService()) + "," + ((int) transmitCompleteCommand.getSecondService()) + "," + ((int) transmitCompleteCommand.getMainOperation()));
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PAGE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET_DEFAULT.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DeviceReturnStatus resetPageToDefault$lambda$21(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        return DeviceReturnStatus.INSTANCE.fromStatus(configurationDelegate.deviceManager.getCommandStatus(bArr2));
    }

    public final BaseCommand<Boolean> addBikeInformation(Config.bike_msg... config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD).addAllBikeMessage(ArraysKt.asIterable(config)).build());
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, message.getFirst(), message.getSecond());
        baseCommand.setCommandTag(WriteCommand.AddBikeInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.addBikeInformation$lambda$22((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.addBikeInformation$lambda$23(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBikeInformation$lambda$22(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBikeInformation$lambda$23(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Boolean> deleteBikeInformation(Config.bike_msg... config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL).addAllBikeMessage(ArraysKt.asIterable(config)).build());
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, message.getFirst(), message.getSecond());
        baseCommand.setCommandTag(WriteCommand.DeleteBikeInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.deleteBikeInformation$lambda$24((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.deleteBikeInformation$lambda$25(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteBikeInformation$lambda$24(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteBikeInformation$lambda$25(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<List<Config.bike_msg>> readBikeInformation() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        BaseCommand<List<Config.bike_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, message.getFirst(), message.getSecond());
        baseCommand.setCommandTag(WriteCommand.ReadBikeInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readBikeInformation$lambda$26((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readBikeInformation$lambda$27((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readBikeInformation$lambda$26(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SEND.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readBikeInformation$lambda$27(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getBikeMessageList();
        } catch (InvalidProtocolBufferException unused) {
            return CollectionsKt.listOf(Config.bike_msg.newBuilder().build());
        }
    }

    public static /* synthetic */ BaseCommand readAlarmConfig$default(ConfigurationDelegate configurationDelegate, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return configurationDelegate.readAlarmConfig(num);
    }

    public final BaseCommand<List<Config.alarm_msg>> readAlarmConfig(Integer curOperateMode) {
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.alarm_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadAlarmConfig);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readAlarmConfig$lambda$29((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readAlarmConfig$lambda$30((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readAlarmConfig$lambda$29(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readAlarmConfig$lambda$30(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getAlarmMessageList();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return CollectionsKt.listOf(Config.alarm_msg.newBuilder().build());
        }
    }

    public static /* synthetic */ BaseCommand writeAlarmConfig$default(ConfigurationDelegate configurationDelegate, Config.alarm_msg[] alarm_msgVarArr, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        return configurationDelegate.writeAlarmConfig(alarm_msgVarArr, num);
    }

    public final BaseCommand<Boolean> writeAlarmConfig(Config.alarm_msg[] configs, Integer curOperateMode) {
        Intrinsics.checkNotNullParameter(configs, "configs");
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        builderNewBuilder.addAllAlarmMessage(ArraysKt.asIterable(configs));
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteAlarmConfig);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeAlarmConfig$lambda$32((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeAlarmConfig$lambda$33(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeAlarmConfig$lambda$32(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALARM.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeAlarmConfig$lambda$33(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ BaseCommand readLapConfig$default(ConfigurationDelegate configurationDelegate, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return configurationDelegate.readLapConfig(num);
    }

    public final BaseCommand<List<Config.lap_msg>> readLapConfig(Integer curOperateMode) {
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.lap_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadLapConfig);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readLapConfig$lambda$35((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readLapConfig$lambda$36((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readLapConfig$lambda$35(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readLapConfig$lambda$36(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getLapMessageList();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return CollectionsKt.listOf(Config.lap_msg.newBuilder().setLapType(Config.LAP_TYPE.LAP_TYPE_INVALID).build());
        }
    }

    public static /* synthetic */ BaseCommand writeLapConfig$default(ConfigurationDelegate configurationDelegate, Config.lap_msg[] lap_msgVarArr, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        return configurationDelegate.writeLapConfig(lap_msgVarArr, num);
    }

    public final BaseCommand<Boolean> writeLapConfig(Config.lap_msg[] configs, Integer curOperateMode) {
        Intrinsics.checkNotNullParameter(configs, "configs");
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        builderNewBuilder.addAllLapMessage(ArraysKt.asIterable(configs));
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteLapConfig);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeLapConfig$lambda$38((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeLapConfig$lambda$39(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeLapConfig$lambda$38(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_LAP.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeLapConfig$lambda$39(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Config.config_msg> readSoundSetting() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Config.config_msg> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadSoundSetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readSoundSetting$lambda$40((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readSoundSetting$lambda$41((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readSoundSetting$lambda$40(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Config.config_msg readSoundSetting$lambda$41(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_NONE).build();
        }
    }

    public final BaseCommand<Boolean> writeSoundSetting(Config.sound_set_msg soundSetMsg) {
        Intrinsics.checkNotNullParameter(soundSetMsg, "soundSetMsg");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addSoundSetMessage(soundSetMsg).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteSoundSetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeSoundSetting$lambda$42((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeSoundSetting$lambda$43(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeSoundSetting$lambda$42(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_SOUND.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeSoundSetting$lambda$43(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<List<Config.mode_msg>> readAllRidingMode() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).setCurOperateMode(255).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.mode_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.ReadAllRidingModel);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readAllRidingMode$lambda$44((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readAllRidingMode$lambda$45((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readAllRidingMode$lambda$44(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readAllRidingMode$lambda$45(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getModeMessageList();
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Pair<List<Config.mode_msg>, Config.mode_config_msg>> readRidingModeTemplate() {
        final Common.service_type_index service_type_indexVar = Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG;
        final Config.CONFIG_SERVICE_TYPE config_service_type = Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE_TEMPLATE;
        final Config.CONFIG_OPERATE_TYPE config_operate_type = Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET;
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(service_type_indexVar).setConfigSeviceType(config_service_type).setConfigOperateType(config_operate_type).build());
        byte[] bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<Pair<List<Config.mode_msg>, Config.mode_config_msg>> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setCommandTag(WriteCommand.ReadRidingModeTemplate);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readRidingModeTemplate$lambda$46(service_type_indexVar, config_service_type, config_operate_type, (byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readRidingModeTemplate$lambda$48((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readRidingModeTemplate$lambda$46(Common.service_type_index service_type_indexVar, Config.CONFIG_SERVICE_TYPE config_service_type, Config.CONFIG_OPERATE_TYPE config_operate_type, byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) service_type_indexVar.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) config_service_type.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) config_operate_type.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair readRidingModeTemplate$lambda$48(byte[] bArr, byte[] bArr2) {
        try {
            Config.config_msg from = Config.config_msg.parseFrom(bArr);
            return new Pair(from.getModeMessageList(), from.hasModeConfigMessage() ? from.getModeConfigMessage() : null);
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> modifyRidingMode(Config.mode_msg mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addModeMessage(mode).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.ModifyRidingModel);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda57
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.modifyRidingMode$lambda$49((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.modifyRidingMode$lambda$50(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean modifyRidingMode$lambda$49(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean modifyRidingMode$lambda$50(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Boolean> addRidingMode(Config.mode_msg mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD).addModeMessage(mode).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.AddRidingModel);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.addRidingMode$lambda$51((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.addRidingMode$lambda$52(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addRidingMode$lambda$51(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_ADD.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addRidingMode$lambda$52(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Boolean> deleteRidingMode(Config.mode_msg mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL).addModeMessage(mode).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.DeleteRidingModel);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda62
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.deleteRidingMode$lambda$53((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda63
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.deleteRidingMode$lambda$54(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteRidingMode$lambda$53(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_MODE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_DEL.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteRidingMode$lambda$54(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Boolean> writeBikeInformation(Config.bike_msg... config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllBikeMessage(ArraysKt.asIterable(config)).build());
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, message.getFirst(), message.getSecond());
        baseCommand.setCommandTag(WriteCommand.WriteBikeInformation);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeBikeInformation$lambda$55((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeBikeInformation$lambda$56(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeBikeInformation$lambda$55(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BIKE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeBikeInformation$lambda$56(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Config.backlight_msg> readDisplaySettings() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Config.backlight_msg> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.ReadDisplaySettings);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readDisplaySettings$lambda$57((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readDisplaySettings$lambda$58((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readDisplaySettings$lambda$57(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Config.backlight_msg readDisplaySettings$lambda$58(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getBacklightMessage();
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> writeDisplaySettings(Config.backlight_msg displaySettings) {
        Intrinsics.checkNotNullParameter(displaySettings, "displaySettings");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setBacklightMessage(displaySettings).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] bArr = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, bArr);
        baseCommand.setCommandTag(WriteCommand.WriteDisplaySettings);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda64
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeDisplaySettings$lambda$59((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda65
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeDisplaySettings$lambda$60(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeDisplaySettings$lambda$59(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_BK.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeDisplaySettings$lambda$60(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ BaseCommand writeAutomaticSetting$default(ConfigurationDelegate configurationDelegate, Config.auto_set_msg[] auto_set_msgVarArr, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        return configurationDelegate.writeAutomaticSetting(auto_set_msgVarArr, num);
    }

    public final BaseCommand<Boolean> writeAutomaticSetting(Config.auto_set_msg[] configs, Integer curOperateMode) {
        Intrinsics.checkNotNullParameter(configs, "configs");
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET);
        builderNewBuilder.addAllAutoSetMessage(ArraysKt.asIterable(configs));
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteAutomaticSetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeAutomaticSetting$lambda$62((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeAutomaticSetting$lambda$63(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeAutomaticSetting$lambda$62(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeAutomaticSetting$lambda$63(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<Boolean> writeKeySetting(Config.key_set_msg... configs) {
        Intrinsics.checkNotNullParameter(configs, "configs");
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addAllKeySetMessage(ArraysKt.asIterable(configs)).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.WriteKeySetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeKeySetting$lambda$64((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeKeySetting$lambda$65(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeKeySetting$lambda$64(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeKeySetting$lambda$65(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<List<Config.key_set_msg>> readKeySetting() {
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice = this.iGPDeviceType;
        Integer numValueOf = iGPDevice != null ? Integer.valueOf(iGPDevice.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.key_set_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadKeySetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readKeySetting$lambda$66((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readKeySetting$lambda$67((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readKeySetting$lambda$66(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_KEY.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readKeySetting$lambda$67(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getKeySetMessageList();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return CollectionsKt.listOf(Config.key_set_msg.newBuilder().build());
        }
    }

    public static /* synthetic */ BaseCommand readAutomaticSetting$default(ConfigurationDelegate configurationDelegate, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return configurationDelegate.readAutomaticSetting(num);
    }

    public final BaseCommand<List<Config.auto_set_msg>> readAutomaticSetting(Integer curOperateMode) {
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        builderNewBuilder.setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        builderNewBuilder.setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO);
        builderNewBuilder.setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET);
        IGPDevice iGPDevice = this.iGPDeviceType;
        if (iGPDevice != null && iGPDevice.getGeneration() == 4) {
            builderNewBuilder.setCurOperateMode(curOperateMode != null ? curOperateMode.intValue() : 0);
        }
        Pair<byte[], byte[]> message = new ComputerConfigureServiceFactory().getMessage(builderNewBuilder.build());
        byte[] bArrByteMerger = new byte[0];
        byte[] second = new byte[0];
        IGPDevice iGPDevice2 = this.iGPDeviceType;
        Integer numValueOf = iGPDevice2 != null ? Integer.valueOf(iGPDevice2.getGeneration()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 2) || (numValueOf != null && numValueOf.intValue() == 3)) {
            bArrByteMerger = message.getFirst();
            second = message.getSecond();
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            bArrByteMerger = StringUtils.byteMerger(message.getSecond(), message.getFirst());
        }
        BaseCommand<List<Config.auto_set_msg>> baseCommand = new BaseCommand<>(this.deviceManager, this.firstQueue, 1, bArrByteMerger, second);
        baseCommand.setCommandTag(WriteCommand.ReadAutomaticSetting);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readAutomaticSetting$lambda$69((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readAutomaticSetting$lambda$70((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readAutomaticSetting$lambda$69(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_AUTO.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readAutomaticSetting$lambda$70(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getAutoSetMessageList();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return CollectionsKt.listOf(Config.auto_set_msg.newBuilder().build());
        }
    }

    public final BaseCommand<List<Config.data_msg>> readDataProcessingSettings() {
        byte[] byteArray = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build().toByteArray();
        Head20BytesInfo head20BytesInfo = new Head20BytesInfo();
        head20BytesInfo.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        head20BytesInfo.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber());
        head20BytesInfo.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
        Intrinsics.checkNotNull(byteArray);
        head20BytesInfo.setPbDataBytes(byteArray);
        byte[] bArrByteMerger = StringUtils.byteMerger(head20BytesInfo.confirmCommandByteArray(), byteArray);
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<List<Config.data_msg>> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setCommandTag(WriteCommand.ReadDataProcessingSettings);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readDataProcessingSettings$lambda$72((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readDataProcessingSettings$lambda$73((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readDataProcessingSettings$lambda$72(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List readDataProcessingSettings$lambda$73(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getDataMessageList();
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> dataProcessingFilter0Value(Config.DATA_TYPE dataType, boolean isFilter) {
        Intrinsics.checkNotNullParameter(dataType, "dataType");
        byte[] byteArray = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).addDataMessage(Config.data_msg.newBuilder().setDataType(dataType).setStatus(!isFilter ? 1 : 0).build()).build().toByteArray();
        Head20BytesInfo head20BytesInfo = new Head20BytesInfo();
        head20BytesInfo.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        head20BytesInfo.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber());
        head20BytesInfo.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
        Intrinsics.checkNotNull(byteArray);
        head20BytesInfo.setPbDataBytes(byteArray);
        byte[] bArrByteMerger = StringUtils.byteMerger(head20BytesInfo.confirmCommandByteArray(), byteArray);
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setCommandTag(WriteCommand.DataProcessingFilter0Value);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.dataProcessingFilter0Value$lambda$75((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.dataProcessingFilter0Value$lambda$76(this.f$0, (byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean dataProcessingFilter0Value$lambda$75(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_DATA.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean dataProcessingFilter0Value$lambda$76(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        try {
            Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
            return Boolean.valueOf(boolIsCommandStatusSuccess != null ? boolIsCommandStatusSuccess.booleanValue() : false);
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Integer> readAltitude() {
        byte[] byteArray = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET).build().toByteArray();
        Head20BytesInfo head20BytesInfo = new Head20BytesInfo();
        head20BytesInfo.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        head20BytesInfo.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber());
        head20BytesInfo.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
        Intrinsics.checkNotNull(byteArray);
        head20BytesInfo.setPbDataBytes(byteArray);
        byte[] bArrByteMerger = StringUtils.byteMerger(head20BytesInfo.confirmCommandByteArray(), byteArray);
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<Integer> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setCommandTag(WriteCommand.ReadAltitude);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda68
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.readAltitude$lambda$78((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda69
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readAltitude$lambda$79((byte[]) obj, (byte[]) obj2);
            }
        });
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean readAltitude$lambda$78(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer readAltitude$lambda$79(byte[] bArr, byte[] bArr2) {
        try {
            return Integer.valueOf(Config.config_msg.parseFrom(bArr).getAltitude());
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> writeAltitude(int altitude) {
        byte[] byteArray = Config.config_msg.newBuilder().setServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG).setConfigSeviceType(Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE).setConfigOperateType(Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET).setAltitude(altitude).build().toByteArray();
        Head20BytesInfo head20BytesInfo = new Head20BytesInfo();
        head20BytesInfo.setMainServiceType(Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG);
        head20BytesInfo.modifySecondServiceByte((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber());
        head20BytesInfo.modifyMainOperationByte((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
        Intrinsics.checkNotNull(byteArray);
        head20BytesInfo.setPbDataBytes(byteArray);
        byte[] bArrByteMerger = StringUtils.byteMerger(head20BytesInfo.confirmCommandByteArray(), byteArray);
        IGPDeviceManager iGPDeviceManager = this.deviceManager;
        ConcurrentLinkedQueue<BaseCommand<?>> concurrentLinkedQueue = this.firstQueue;
        Intrinsics.checkNotNull(bArrByteMerger);
        BaseCommand<Boolean> baseCommand = new BaseCommand<>(iGPDeviceManager, concurrentLinkedQueue, 1, bArrByteMerger, new byte[0]);
        baseCommand.setReceiveFinish(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeAltitude$lambda$81((byte[]) obj, (byte[]) obj2));
            }
        });
        baseCommand.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.writeAltitude$lambda$82(this.f$0, (byte[]) obj, (byte[]) obj2);
            }
        });
        baseCommand.setCommandTag(WriteCommand.WriteAltitude);
        return baseCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeAltitude$lambda$81(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNull(bArr);
        TransmitCompleteCommand transmitCompleteCommand = new TransmitCompleteCommand(bArr);
        return transmitCompleteCommand.getMainService() == ((byte) Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG.getNumber()) && transmitCompleteCommand.getSecondService() == ((byte) Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ALTITUDE.getNumber()) && transmitCompleteCommand.getMainOperation() == ((byte) Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean writeAltitude$lambda$82(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        try {
            Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
            return Boolean.valueOf(boolIsCommandStatusSuccess != null ? boolIsCommandStatusSuccess.booleanValue() : false);
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> writeTimeFormat$blelib_release(Config.TIME_SYSTEM_TYPE timeFormat) {
        Intrinsics.checkNotNullParameter(timeFormat, "timeFormat");
        Config.CONFIG_SERVICE_TYPE config_service_type = Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_TIME_SYS;
        Config.CONFIG_OPERATE_TYPE config_operate_type = Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_SET;
        WriteCommand writeCommand = WriteCommand.WriteTimeFormat;
        Config.config_msg.Builder timeSystem = Config.config_msg.newBuilder().setTimeSystem(timeFormat);
        Intrinsics.checkNotNullExpressionValue(timeSystem, "setTimeSystem(...)");
        BaseCommand<Boolean> command = getCommand(config_service_type, config_operate_type, writeCommand, timeSystem);
        command.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConfigurationDelegate.writeTimeFormat$lambda$83(this.f$0, (byte[]) obj, (byte[]) obj2));
            }
        });
        return command;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean writeTimeFormat$lambda$83(ConfigurationDelegate configurationDelegate, byte[] bArr, byte[] bArr2) {
        Boolean boolIsCommandStatusSuccess = configurationDelegate.deviceManager.isCommandStatusSuccess(bArr2);
        if (boolIsCommandStatusSuccess != null) {
            return boolIsCommandStatusSuccess.booleanValue();
        }
        return false;
    }

    public final BaseCommand<String> readDevicePassword() {
        Config.CONFIG_SERVICE_TYPE config_service_type = Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_PASSWORD;
        Config.CONFIG_OPERATE_TYPE config_operate_type = Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET;
        WriteCommand writeCommand = WriteCommand.ReadDevicePassword;
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BaseCommand<String> command = getCommand(config_service_type, config_operate_type, writeCommand, builderNewBuilder);
        command.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readDevicePassword$lambda$84((byte[]) obj, (byte[]) obj2);
            }
        });
        return command;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String readDevicePassword$lambda$84(byte[] bArr, byte[] bArr2) {
        try {
            return Config.config_msg.parseFrom(bArr).getPasswordMessage().getPassword();
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    public final BaseCommand<Boolean> readAccidentDetectionSwitch() {
        Config.CONFIG_SERVICE_TYPE config_service_type = Config.CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_ACCIDENT;
        Config.CONFIG_OPERATE_TYPE config_operate_type = Config.CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_GET;
        WriteCommand writeCommand = WriteCommand.ReadAccidentDetectionSwitch;
        Config.config_msg.Builder builderNewBuilder = Config.config_msg.newBuilder();
        Intrinsics.checkNotNullExpressionValue(builderNewBuilder, "newBuilder(...)");
        BaseCommand<Boolean> command = getCommand(config_service_type, config_operate_type, writeCommand, builderNewBuilder);
        command.setTranslation(new Function2() { // from class: com.igpsport.blelib.devicemanager.ConfigurationDelegate$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ConfigurationDelegate.readAccidentDetectionSwitch$lambda$85((byte[]) obj, (byte[]) obj2);
            }
        });
        return command;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean readAccidentDetectionSwitch$lambda$85(byte[] bArr, byte[] bArr2) {
        try {
            boolean z = true;
            if (Config.config_msg.parseFrom(bArr).getAccidentDetectionMessage().getAccidentDetectionOpen() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }
}
