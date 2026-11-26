package com.igpsport.globalapp.devicemodule.pagesetting.pagebg3;

import android.util.Base64;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.muxer.WebmConstants;
import com.blankj.utilcode.util.Utils;
import com.garmin.fit.Manufacturer;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.igpsport.blelib.BaseCommand;
import com.igpsport.blelib.IGPDeviceManager;
import com.igpsport.blelib.bean.Config;
import com.igpsport.blelib.config.DeviceConstant;
import com.igpsport.blelib.utils.StringUtils;
import com.igpsport.globalapp.common.LogUtils;
import com.igpsport.globalapp.core.DevicePageLayoutListResponse;
import com.igpsport.globalapp.core.NewApiService;
import com.igpsport.globalapp.core.PageLayoutInfo;
import com.igpsport.globalapp.devicemodule.base.BaseViewModel;
import com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3DataFieldBean;
import com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PageBean;
import com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean.Bg3PagePlanInfoBean;
import com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.Bg3PageConfigUtil;
import com.igpsport.globalapp.devicemodule.pagesettingnew.model.CategoryItem;
import com.igpsport.globalapp.devicemodule.scan.bean.ScannedDevice;
import com.igpsport.globalapp.devicemodule.service.DeviceBleManagerHandler;
import com.igpsport.globalapp.dialog.ConnectDeviceBottomDialog;
import com.igpsport.globalapp.domain.GetCurrentSportModeFieldsUseCase;
import com.igpsport.igpsportandroid.R;
import com.mapbox.search.result.SearchAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import retrofit2.Response;

/* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\u0006\u0010\u0011\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\rJ\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u000bJ\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010?\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010@J\u0018\u0010A\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010C0BH\u0086@¢\u0006\u0004\bD\u0010@J\u000e\u0010E\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010@J\u0014\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00060GH\u0086@¢\u0006\u0002\u0010@J\u0018\u0010H\u001a\u0004\u0018\u00010I2\u0006\u0010J\u001a\u00020,H\u0082@¢\u0006\u0002\u0010KJ\b\u0010L\u001a\u00020\u000bH\u0002J\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00060GJ\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00060G2\u0006\u0010O\u001a\u000202J\u000e\u0010P\u001a\u00020\u00062\u0006\u0010Q\u001a\u000202J\u0010\u0010R\u001a\u0004\u0018\u00010\r2\u0006\u0010S\u001a\u000202J\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020 0\u00132\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0;J\u0006\u0010W\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\tR\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001d\u0010+\u001a\u0004\u0018\u00010,8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b-\u0010.R\u001b\u00101\u001a\u0002028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u00100\u001a\u0004\b3\u00104R\u001b\u00106\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u00100\u001a\u0004\b7\u00108R\u001a\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0;0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0;0\b¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\t¨\u0006X"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/Bg3PageSettingViewModel;", "Lcom/igpsport/globalapp/devicemodule/base/BaseViewModel;", "<init>", "()V", "_isEdit", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isEdit", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "setEditState", "", "_bg3PagePlanInfoBean", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PagePlanInfoBean;", "bg3PagePlanInfoBean", "getBg3PagePlanInfoBean", "setBg3PagePlanInfoBean", "refreshBg3PagePlanInfoBean", "_bg3PagePlanInfoBeanList", "", "bg3PagePlanInfoBeanList", "getBg3PagePlanInfoBeanList", "addPlanAndRefresh", "plan", "deletePlanAndRefresh", "deletePosition", "", "selectOnePlan", "position", "removeUnsavedCustomPlan", "refreshBg1PagePlanInfoBeanList", "mCurrentPageData", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageBean;", "getMCurrentPageData", "()Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageBean;", "setMCurrentPageData", "(Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageBean;)V", "mCurrentEditDataFieldBean", "Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3DataFieldBean;", "getMCurrentEditDataFieldBean", "()Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3DataFieldBean;", "setMCurrentEditDataFieldBean", "(Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3DataFieldBean;)V", "bleManagerHandler", "Lcom/igpsport/globalapp/devicemodule/service/DeviceBleManagerHandler;", "getBleManagerHandler", "()Lcom/igpsport/globalapp/devicemodule/service/DeviceBleManagerHandler;", "bleManagerHandler$delegate", "Lkotlin/Lazy;", ConnectDeviceBottomDialog.DEVICE_NAME_ARG, "", "getDeviceName", "()Ljava/lang/String;", "deviceName$delegate", "firmwareVersion", "getFirmwareVersion", "()I", "firmwareVersion$delegate", "_currSportsModeAllFields", "", "Lcom/igpsport/globalapp/devicemodule/pagesettingnew/model/CategoryItem;", "currSportsModeAllFields", "getCurrSportsModeAllFields", "readDevicePageData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPageToDefault", "Lkotlin/Result;", "Lcom/igpsport/blelib/DeviceReturnStatus;", "resetPageToDefault-IoAF18A", "getCurrentSportModeFields", "writePageDataToDevice", "Lkotlinx/coroutines/flow/Flow;", "readCurrentRideMode", "Lcom/igpsport/blelib/bean/Config$mode_msg;", "managerHandler", "(Lcom/igpsport/globalapp/devicemodule/service/DeviceBleManagerHandler;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCustomPlanListFromServer", "saveCurrPagePlanToServer", "deleteServerPlan", "planId", "isSchemeNameExist", "planName", "createACopyOfCurrentPlan", "schemeName", "pageMsgListToPageBeanList", "pageMsgList", "Lcom/igpsport/blelib/bean/Config$page_msg;", "isBG1OrBG2", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Bg3PageSettingViewModel extends BaseViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Bg3PagePlanInfoBean> _bg3PagePlanInfoBean;
    private final MutableStateFlow<List<Bg3PagePlanInfoBean>> _bg3PagePlanInfoBeanList;
    private final MutableStateFlow<List<CategoryItem>> _currSportsModeAllFields;
    private final MutableStateFlow<Boolean> _isEdit;
    private final StateFlow<Bg3PagePlanInfoBean> bg3PagePlanInfoBean;
    private final StateFlow<List<Bg3PagePlanInfoBean>> bg3PagePlanInfoBeanList;

    /* JADX INFO: renamed from: bleManagerHandler$delegate, reason: from kotlin metadata */
    private final Lazy bleManagerHandler;
    private final StateFlow<List<CategoryItem>> currSportsModeAllFields;

    /* JADX INFO: renamed from: deviceName$delegate, reason: from kotlin metadata */
    private final Lazy deviceName;

    /* JADX INFO: renamed from: firmwareVersion$delegate, reason: from kotlin metadata */
    private final Lazy firmwareVersion;
    private final StateFlow<Boolean> isEdit;
    private Bg3DataFieldBean mCurrentEditDataFieldBean;
    private Bg3PageBean mCurrentPageData;

    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Config.PAGE_TYPE.values().length];
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_DATA.ordinal()] = 1;
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
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_COURSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_TRAINING_FEC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_LAP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_COMPASS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Config.PAGE_TYPE.enum_PAGE_TYPE_CLIMB.ordinal()] = 8;
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

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$getCurrentSportModeFields$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel", f = "Bg3PageSettingViewModel.kt", i = {0, 0, 0, 1}, l = {203, 224}, m = "getCurrentSportModeFields", n = {"this", "useCase", "sportModeId", "this"}, s = {"L$0", "L$1", "I$0", "L$0"})
    static final class C12881 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C12881(Continuation<? super C12881> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Bg3PageSettingViewModel.this.getCurrentSportModeFields(this);
        }
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$readCurrentRideMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel", f = "Bg3PageSettingViewModel.kt", i = {}, l = {275}, m = "readCurrentRideMode", n = {}, s = {})
    static final class C12901 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C12901(Continuation<? super C12901> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Bg3PageSettingViewModel.this.readCurrentRideMode(null, this);
        }
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$readDevicePageData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel", f = "Bg3PageSettingViewModel.kt", i = {0, 0, 0, 1, 1}, l = {160, 161}, m = "readDevicePageData", n = {"this", "bleManager", "pageBeanList", "this", "pageBeanList"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C12911 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C12911(Continuation<? super C12911> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Bg3PageSettingViewModel.this.readDevicePageData(this);
        }
    }

    public Bg3PageSettingViewModel() {
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(false);
        this._isEdit = MutableStateFlow;
        this.isEdit = MutableStateFlow;
        MutableStateFlow<Bg3PagePlanInfoBean> MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._bg3PagePlanInfoBean = MutableStateFlow2;
        this.bg3PagePlanInfoBean = MutableStateFlow2;
        MutableStateFlow<List<Bg3PagePlanInfoBean>> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new ArrayList());
        this._bg3PagePlanInfoBeanList = MutableStateFlow3;
        this.bg3PagePlanInfoBeanList = MutableStateFlow3;
        this.bleManagerHandler = LazyKt.lazy(new Function0() { // from class: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getCurrBleManagerHandler();
            }
        });
        this.deviceName = LazyKt.lazy(new Function0() { // from class: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Bg3PageSettingViewModel.deviceName_delegate$lambda$12(this.f$0);
            }
        });
        this.firmwareVersion = LazyKt.lazy(new Function0() { // from class: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(Bg3PageSettingViewModel.firmwareVersion_delegate$lambda$13(this.f$0));
            }
        });
        MutableStateFlow<List<CategoryItem>> MutableStateFlow4 = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._currSportsModeAllFields = MutableStateFlow4;
        this.currSportsModeAllFields = MutableStateFlow4;
    }

    public final StateFlow<Boolean> isEdit() {
        return this.isEdit;
    }

    public final void setEditState(boolean isEdit) {
        this._isEdit.setValue(Boolean.valueOf(isEdit));
    }

    public final StateFlow<Bg3PagePlanInfoBean> getBg3PagePlanInfoBean() {
        return this.bg3PagePlanInfoBean;
    }

    public final void setBg3PagePlanInfoBean(Bg3PagePlanInfoBean bg3PagePlanInfoBean) {
        this._bg3PagePlanInfoBean.setValue(bg3PagePlanInfoBean);
    }

    public final void refreshBg3PagePlanInfoBean() {
        Bg3PagePlanInfoBean value;
        Bg3PagePlanInfoBean bg3PagePlanInfoBean;
        MutableStateFlow<Bg3PagePlanInfoBean> mutableStateFlow = this._bg3PagePlanInfoBean;
        do {
            value = mutableStateFlow.getValue();
            bg3PagePlanInfoBean = value;
        } while (!mutableStateFlow.compareAndSet(value, bg3PagePlanInfoBean != null ? Bg3PagePlanInfoBean.copy$default(bg3PagePlanInfoBean, System.currentTimeMillis(), false, null, null, false, null, 62, null) : null));
    }

    public final StateFlow<List<Bg3PagePlanInfoBean>> getBg3PagePlanInfoBeanList() {
        return this.bg3PagePlanInfoBeanList;
    }

    public final void addPlanAndRefresh(Bg3PagePlanInfoBean plan) {
        Intrinsics.checkNotNullParameter(plan, "plan");
        List<Bg3PagePlanInfoBean> mutableList = CollectionsKt.toMutableList((Collection) this._bg3PagePlanInfoBeanList.getValue());
        Iterator it2 = mutableList.iterator();
        while (it2.hasNext()) {
            ((Bg3PagePlanInfoBean) it2.next()).setSelected(false);
        }
        List<Bg3PagePlanInfoBean> list = mutableList;
        list.add(plan);
        this._bg3PagePlanInfoBeanList.setValue(list);
        int size = this._bg3PagePlanInfoBeanList.getValue().size();
        List<Bg3PagePlanInfoBean> value = this._bg3PagePlanInfoBeanList.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
        for (Bg3PagePlanInfoBean bg3PagePlanInfoBean : value) {
            arrayList.add(bg3PagePlanInfoBean.getPlanName() + "," + bg3PagePlanInfoBean.isSelected() + "," + bg3PagePlanInfoBean.getPlanId());
        }
        LogUtils.d("addPlanAndRefresh currentList = " + size + HexStringBuilder.DEFAULT_SEPARATOR + arrayList + ", plan = " + plan.getPlanName() + plan.isSelected());
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003f  */
    public final void deletePlanAndRefresh(int deletePosition) {
        Bg3PagePlanInfoBean bg3PagePlanInfoBean;
        List<Bg3PagePlanInfoBean> mutableList = CollectionsKt.toMutableList((Collection) this._bg3PagePlanInfoBeanList.getValue());
        mutableList.remove(deletePosition);
        List<Bg3PagePlanInfoBean> list = mutableList;
        if ((list instanceof Collection) && list.isEmpty()) {
            bg3PagePlanInfoBean = (Bg3PagePlanInfoBean) CollectionsKt.firstOrNull((List) mutableList);
            if (bg3PagePlanInfoBean != null) {
                bg3PagePlanInfoBean.setSelected(true);
            }
        } else {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (((Bg3PagePlanInfoBean) it2.next()).isSelected()) {
                }
            }
            bg3PagePlanInfoBean = (Bg3PagePlanInfoBean) CollectionsKt.firstOrNull((List) mutableList);
            if (bg3PagePlanInfoBean != null) {
                bg3PagePlanInfoBean.setSelected(true);
            }
        }
        this._bg3PagePlanInfoBeanList.setValue(mutableList);
    }

    public final void selectOnePlan(int position) {
        MutableStateFlow<List<Bg3PagePlanInfoBean>> mutableStateFlow = this._bg3PagePlanInfoBeanList;
        List<Bg3PagePlanInfoBean> mutableList = CollectionsKt.toMutableList((Collection) mutableStateFlow.getValue());
        int i = 0;
        for (Object obj : mutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            mutableList.set(i, Bg3PagePlanInfoBean.copy$default((Bg3PagePlanInfoBean) obj, 0L, false, null, null, i == position, null, 47, null));
            i = i2;
        }
        mutableStateFlow.setValue(mutableList);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0044  */
    public final void removeUnsavedCustomPlan() {
        Bg3PagePlanInfoBean bg3PagePlanInfoBean;
        MutableStateFlow<List<Bg3PagePlanInfoBean>> mutableStateFlow = this._bg3PagePlanInfoBeanList;
        List<Bg3PagePlanInfoBean> mutableList = CollectionsKt.toMutableList((Collection) mutableStateFlow.getValue());
        CollectionsKt.removeAll((List) mutableList, new Function1() { // from class: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Bg3PageSettingViewModel.removeUnsavedCustomPlan$lambda$8$lambda$6((Bg3PagePlanInfoBean) obj));
            }
        });
        List<Bg3PagePlanInfoBean> list = mutableList;
        if ((list instanceof Collection) && list.isEmpty()) {
            bg3PagePlanInfoBean = (Bg3PagePlanInfoBean) CollectionsKt.firstOrNull((List) mutableList);
            if (bg3PagePlanInfoBean != null) {
                bg3PagePlanInfoBean.setSelected(true);
            }
        } else {
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                if (((Bg3PagePlanInfoBean) it2.next()).isSelected()) {
                }
            }
            bg3PagePlanInfoBean = (Bg3PagePlanInfoBean) CollectionsKt.firstOrNull((List) mutableList);
            if (bg3PagePlanInfoBean != null) {
                bg3PagePlanInfoBean.setSelected(true);
            }
        }
        mutableStateFlow.setValue(mutableList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeUnsavedCustomPlan$lambda$8$lambda$6(Bg3PagePlanInfoBean it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        return !it2.isCurrentDevicePlan() && it2.getPlanId() == null;
    }

    public final void refreshBg1PagePlanInfoBeanList() {
        MutableStateFlow<List<Bg3PagePlanInfoBean>> mutableStateFlow = this._bg3PagePlanInfoBeanList;
        List<Bg3PagePlanInfoBean> mutableList = CollectionsKt.toMutableList((Collection) mutableStateFlow.getValue());
        int i = 0;
        for (Object obj : mutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            mutableList.set(i, Bg3PagePlanInfoBean.copy$default((Bg3PagePlanInfoBean) obj, System.currentTimeMillis(), false, null, null, false, null, 62, null));
            i = i2;
        }
        mutableStateFlow.setValue(mutableList);
    }

    public final Bg3PageBean getMCurrentPageData() {
        return this.mCurrentPageData;
    }

    public final void setMCurrentPageData(Bg3PageBean bg3PageBean) {
        this.mCurrentPageData = bg3PageBean;
    }

    public final Bg3DataFieldBean getMCurrentEditDataFieldBean() {
        return this.mCurrentEditDataFieldBean;
    }

    public final void setMCurrentEditDataFieldBean(Bg3DataFieldBean bg3DataFieldBean) {
        this.mCurrentEditDataFieldBean = bg3DataFieldBean;
    }

    public final DeviceBleManagerHandler getBleManagerHandler() {
        return (DeviceBleManagerHandler) this.bleManagerHandler.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String deviceName_delegate$lambda$12(Bg3PageSettingViewModel bg3PageSettingViewModel) {
        ScannedDevice deviceInfo;
        String deviceName;
        DeviceBleManagerHandler currBleManagerHandler = bg3PageSettingViewModel.getCurrBleManagerHandler();
        return (currBleManagerHandler == null || (deviceInfo = currBleManagerHandler.getDeviceInfo()) == null || (deviceName = deviceInfo.getDeviceName()) == null) ? "" : deviceName;
    }

    public final String getDeviceName() {
        return (String) this.deviceName.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int firmwareVersion_delegate$lambda$13(Bg3PageSettingViewModel bg3PageSettingViewModel) {
        ScannedDevice deviceInfo;
        DeviceBleManagerHandler currBleManagerHandler = bg3PageSettingViewModel.getCurrBleManagerHandler();
        if (currBleManagerHandler == null || (deviceInfo = currBleManagerHandler.getDeviceInfo()) == null) {
            return 100;
        }
        return deviceInfo.getFirmwareCode();
    }

    public final int getFirmwareVersion() {
        return ((Number) this.firmwareVersion.getValue()).intValue();
    }

    public final StateFlow<List<CategoryItem>> getCurrSportsModeAllFields() {
        return this.currSportsModeAllFields;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0096 A[Catch: all -> 0x00cc, TryCatch #2 {all -> 0x00cc, blocks: (B:44:0x00b3, B:46:0x00b7, B:48:0x00bf, B:49:0x00c4, B:50:0x00cb, B:37:0x0091, B:39:0x0096, B:41:0x00a0, B:34:0x0080), top: B:68:0x0080 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x009f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b0, code lost:

        if (r11 == r1) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readDevicePageData(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.readDevicePageData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0094, code lost:

        if (r7 == r1) goto L36;
     */
    /* JADX INFO: renamed from: resetPageToDefault-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m16693resetPageToDefaultIoAF18A(kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.igpsport.blelib.DeviceReturnStatus>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$resetPageToDefault$1
            if (r0 == 0) goto L14
            r0 = r7
            com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$resetPageToDefault$1 r0 = (com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$resetPageToDefault$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$resetPageToDefault$1 r0 = new com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$resetPageToDefault$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3d
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L9e
            goto L97
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$0
            com.igpsport.blelib.IGPDeviceManager r6 = (com.igpsport.blelib.IGPDeviceManager) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L73
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            com.igpsport.globalapp.devicemodule.service.DeviceBleManagerHandler r7 = r6.getCurrBleManagerHandler()
            java.lang.String r2 = "设备为空"
            if (r7 != 0) goto L5a
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m20711constructorimpl(r6)
            return r6
        L5a:
            com.igpsport.globalapp.devicemodule.service.DeviceBleManagerHandler r5 = r6.getCurrBleManagerHandler()
            if (r5 == 0) goto Laa
            com.igpsport.blelib.IGPDeviceManager r5 = r5.getManager()
            if (r5 != 0) goto L67
            goto Laa
        L67:
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r7 = r6.readCurrentRideMode(r7, r0)
            if (r7 != r1) goto L72
            goto L96
        L72:
            r6 = r5
        L73:
            com.igpsport.blelib.bean.Config$mode_msg r7 = (com.igpsport.blelib.bean.Config.mode_msg) r7
            r2 = 0
            if (r7 == 0) goto L81
            int r7 = r7.getModeIndex()
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            goto L82
        L81:
            r7 = r2
        L82:
            kotlin.Result$Companion r5 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L9e
            com.igpsport.blelib.BaseCommand r6 = r6.resetPageToDefault(r7)     // Catch: java.lang.Throwable -> L9e
            com.igpsport.blelib.BaseCommand r6 = r6.retry(r4)     // Catch: java.lang.Throwable -> L9e
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L9e
            r0.label = r3     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r7 = r6.await(r0)     // Catch: java.lang.Throwable -> L9e
            if (r7 != r1) goto L97
        L96:
            return r1
        L97:
            com.igpsport.blelib.DeviceReturnStatus r7 = (com.igpsport.blelib.DeviceReturnStatus) r7     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r6 = kotlin.Result.m20711constructorimpl(r7)     // Catch: java.lang.Throwable -> L9e
            return r6
        L9e:
            r6 = move-exception
            kotlin.Result$Companion r7 = kotlin.Result.INSTANCE
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m20711constructorimpl(r6)
            return r6
        Laa:
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m20711constructorimpl(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.m16693resetPageToDefaultIoAF18A(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00df, code lost:

        if (r13 == r0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getCurrentSportModeFields(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.getCurrentSportModeFields(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$getCurrentSportModeFields$2, reason: invalid class name */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$getCurrentSportModeFields$2", f = "Bg3PageSettingViewModel.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $sportModeId;
        final /* synthetic */ GetCurrentSportModeFieldsUseCase $useCase;
        int label;
        final /* synthetic */ Bg3PageSettingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(GetCurrentSportModeFieldsUseCase getCurrentSportModeFieldsUseCase, int i, Bg3PageSettingViewModel bg3PageSettingViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$useCase = getCurrentSportModeFieldsUseCase;
            this.$sportModeId = i;
            this.this$0 = bg3PageSettingViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$useCase, this.$sportModeId, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM17615invokeyxL6bBk;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objM17615invokeyxL6bBk = this.$useCase.m17615invokeyxL6bBk(this.$sportModeId, this.this$0.getDeviceName(), this.this$0.getFirmwareVersion(), false, this);
                if (objM17615invokeyxL6bBk == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM17615invokeyxL6bBk = ((Result) obj).getValue();
            }
            Bg3PageSettingViewModel bg3PageSettingViewModel = this.this$0;
            if (Result.m20718isSuccessimpl(objM17615invokeyxL6bBk)) {
                List list = (List) objM17615invokeyxL6bBk;
                LogUtils.d("网格配置-> 接口刷新成功: " + list.size());
                bg3PageSettingViewModel._currSportsModeAllFields.setValue(list);
            }
            Throwable thM20714exceptionOrNullimpl = Result.m20714exceptionOrNullimpl(objM17615invokeyxL6bBk);
            if (thM20714exceptionOrNullimpl != null) {
                LogUtils.d("网格配置-> 接口刷新失败: " + thM20714exceptionOrNullimpl.getMessage());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$writePageDataToDevice$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$writePageDataToDevice$2", f = "Bg3PageSettingViewModel.kt", i = {0, 0, 1, 2}, l = {WebmConstants.MkvEbmlElement.BLOCK_ADD_ID, 259, Manufacturer.WATTEAM, Manufacturer.DYNOVELO}, m = "invokeSuspend", n = {"$this$flow", "handler", "$this$flow", "$this$flow"}, s = {"L$0", "L$2", "L$0", "L$0"})
    static final class C12932 extends SuspendLambda implements Function2<FlowCollector<? super Boolean>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C12932(Continuation<? super C12932> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C12932 c12932 = Bg3PageSettingViewModel.this.new C12932(continuation);
            c12932.L$0 = obj;
            return c12932;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Boolean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C12932) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0080  */
        /* JADX WARN: Code duplicated, block: B:69:0x01ac  */
        /* JADX WARN: Code duplicated, block: B:72:0x01c2  */
        /* JADX WARN: Code duplicated, block: B:74:0x01c4 A[PHI: r0 r5
          0x01c4: PHI (r0v21 java.lang.Object) = (r0v16 java.lang.Object), (r0v23 java.lang.Object) binds: [B:68:0x01aa, B:73:0x01c3] A[DONT_GENERATE, DONT_INLINE]
          0x01c4: PHI (r5v9 kotlinx.coroutines.flow.FlowCollector) = (r5v6 kotlinx.coroutines.flow.FlowCollector), (r5v10 kotlinx.coroutines.flow.FlowCollector) binds: [B:68:0x01aa, B:73:0x01c3] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:76:0x01ca  */
        /* JADX WARN: Code duplicated, block: B:81:0x01fc  */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x01f1, code lost:

            if (r5.emit(r3, r18) == r2) goto L78;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 519
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.C12932.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object writePageDataToDevice(Continuation<? super Flow<Boolean>> continuation) {
        return FlowKt.flow(new C12932(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x0076  */
    /* JADX WARN: Code duplicated, block: B:43:0x008e  */
    /* JADX WARN: Code duplicated, block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readCurrentRideMode(DeviceBleManagerHandler deviceBleManagerHandler, Continuation<? super Config.mode_msg> continuation) {
        C12901 c12901;
        Object objM20711constructorimpl;
        Config.mode_msg rideModel;
        BaseCommand<List<Config.mode_msg>> allRidingMode;
        Object next;
        if (continuation instanceof C12901) {
            c12901 = (C12901) continuation;
            if ((c12901.label & Integer.MIN_VALUE) != 0) {
                c12901.label -= Integer.MIN_VALUE;
            } else {
                c12901 = new C12901(continuation);
            }
        } else {
            c12901 = new C12901(continuation);
        }
        Object objAwait = c12901.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c12901.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objAwait);
                Result.Companion companion = Result.INSTANCE;
                rideModel = deviceBleManagerHandler.getRideModel();
                if (rideModel == null) {
                    IGPDeviceManager manager = deviceBleManagerHandler.getManager();
                    if (manager == null || (allRidingMode = manager.readAllRidingMode()) == null) {
                        rideModel = null;
                    } else {
                        c12901.label = 1;
                        objAwait = allRidingMode.await(c12901);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                objM20711constructorimpl = Result.m20711constructorimpl(rideModel);
                if (Result.m20717isFailureimpl(objM20711constructorimpl)) {
                    return null;
                }
                return objM20711constructorimpl;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
            List list = (List) objAwait;
            if (list != null) {
                Iterator it2 = list.iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (((Config.mode_msg) next).getInuse() != 1);
                rideModel = (Config.mode_msg) next;
            } else {
                rideModel = null;
            }
            objM20711constructorimpl = Result.m20711constructorimpl(rideModel);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM20711constructorimpl = Result.m20711constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m20717isFailureimpl(objM20711constructorimpl)) {
            return null;
        }
        return objM20711constructorimpl;
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$getCustomPlanListFromServer$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$getCustomPlanListFromServer$1", f = "Bg3PageSettingViewModel.kt", i = {}, l = {286}, m = "invokeSuspend", n = {}, s = {})
    static final class C12891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C12891(Continuation<? super C12891> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C12891 c12891 = Bg3PageSettingViewModel.this.new C12891(continuation);
            c12891.L$0 = obj;
            return c12891;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C12891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM20711constructorimpl;
            Object devicePageLayoutList;
            List<PageLayoutInfo> data;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Bg3PageSettingViewModel bg3PageSettingViewModel = Bg3PageSettingViewModel.this;
                    Result.Companion companion = Result.INSTANCE;
                    NewApiService newApiService = bg3PageSettingViewModel.getNewApiService();
                    String deviceName = bg3PageSettingViewModel.getDeviceName();
                    this.label = 1;
                    devicePageLayoutList = newApiService.getDevicePageLayoutList(1, deviceName, this);
                    if (devicePageLayoutList == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    devicePageLayoutList = obj;
                }
                DevicePageLayoutListResponse devicePageLayoutListResponse = (DevicePageLayoutListResponse) ((Response) devicePageLayoutList).body();
                if (devicePageLayoutListResponse != null && (data = devicePageLayoutListResponse.getData()) != null) {
                    objM20711constructorimpl = Result.m20711constructorimpl(data);
                    Bg3PageSettingViewModel bg3PageSettingViewModel2 = Bg3PageSettingViewModel.this;
                    if (Result.m20718isSuccessimpl(objM20711constructorimpl)) {
                        List<PageLayoutInfo> list = (List) objM20711constructorimpl;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        for (PageLayoutInfo pageLayoutInfo : list) {
                            List listSplit$default = StringsKt.split$default((CharSequence) pageLayoutInfo.getContent(), new String[]{","}, false, 0, 6, (Object) null);
                            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
                            Iterator it2 = listSplit$default.iterator();
                            while (it2.hasNext()) {
                                arrayList2.add(Config.page_msg.parseFrom(Base64.decode((String) it2.next(), 2)));
                            }
                            ArrayList arrayList3 = arrayList2;
                            String id = pageLayoutInfo.getId();
                            String title = pageLayoutInfo.getTitle();
                            if (title == null) {
                                title = "";
                            }
                            arrayList.add(new Bg3PagePlanInfoBean(0L, false, id, title, false, bg3PageSettingViewModel2.pageMsgListToPageBeanList(arrayList3), 3, null));
                        }
                        ArrayList arrayList4 = arrayList;
                        ((List) bg3PageSettingViewModel2._bg3PagePlanInfoBeanList.getValue()).addAll(arrayList4);
                        LogUtils.d("getCustomPlanListFromServer pagePlanInfoList = " + arrayList4.size());
                    }
                    Throwable thM20714exceptionOrNullimpl = Result.m20714exceptionOrNullimpl(objM20711constructorimpl);
                    if (thM20714exceptionOrNullimpl != null) {
                        LogUtils.e("getCustomPlanListFromServer error = " + ExceptionsKt.stackTraceToString(thM20714exceptionOrNullimpl));
                    }
                    return Unit.INSTANCE;
                }
                throw new Exception("getDevicePageLayoutList is null");
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM20711constructorimpl = Result.m20711constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    private final void getCustomPlanListFromServer() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C12891(null), 3, null);
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$saveCurrPagePlanToServer$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$saveCurrPagePlanToServer$1", f = "Bg3PageSettingViewModel.kt", i = {0, 0}, l = {338, 359}, m = "invokeSuspend", n = {"$this$flow", "planInfo"}, s = {"L$0", "L$1"})
    static final class C12921 extends SuspendLambda implements Function2<FlowCollector<? super Boolean>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C12921(Continuation<? super C12921> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C12921 c12921 = Bg3PageSettingViewModel.this.new C12921(continuation);
            c12921.L$0 = obj;
            return c12921;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Boolean> flowCollector, Continuation<? super Unit> continuation) {
            return ((C12921) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x01a7, code lost:

            if (r5.emit(kotlin.coroutines.jvm.internal.Boxing.boxBoolean(kotlin.Result.m20718isSuccessimpl(r0)), r19) == r2) goto L56;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 429
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.C12921.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$4$lambda$3(Config.page_msg page_msgVar) {
            String strEncodeToString = Base64.encodeToString(page_msgVar != null ? page_msgVar.toByteArray() : null, 2);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
            return strEncodeToString;
        }
    }

    public final Flow<Boolean> saveCurrPagePlanToServer() {
        return FlowKt.flow(new C12921(null));
    }

    /* JADX INFO: renamed from: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$deleteServerPlan$1, reason: invalid class name */
    /* JADX INFO: compiled from: Bg3PageSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel$deleteServerPlan$1", f = "Bg3PageSettingViewModel.kt", i = {0}, l = {366, 369}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FlowCollector<? super Boolean>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $planId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$planId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = Bg3PageSettingViewModel.this.new AnonymousClass1(this.$planId, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Boolean> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x00ac, code lost:

            if (r1.emit(kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7), r6) == r0) goto L30;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v9 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L23
                if (r1 == r3) goto L1b
                if (r1 != r2) goto L13
                kotlin.ResultKt.throwOnFailure(r7)
                goto Laf
            L13:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L1b:
                java.lang.Object r1 = r6.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L72
                goto L4d
            L23:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                r1 = r7
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel r7 = com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.this
                androidx.lifecycle.MutableLiveData r7 = r7.isRequesting()
                java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                r7.postValue(r4)
                com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel r7 = com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.this
                java.lang.String r4 = r6.$planId
                kotlin.Result$Companion r5 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L72
                com.igpsport.globalapp.core.NewApiService r7 = com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.access$getNewApiService(r7)     // Catch: java.lang.Throwable -> L72
                r6.L$0 = r1     // Catch: java.lang.Throwable -> L72
                r6.label = r3     // Catch: java.lang.Throwable -> L72
                java.lang.Object r7 = r7.delDevicePageLayoutInfo(r4, r6)     // Catch: java.lang.Throwable -> L72
                if (r7 != r0) goto L4d
                goto Lae
            L4d:
                retrofit2.Response r7 = (retrofit2.Response) r7     // Catch: java.lang.Throwable -> L72
                java.lang.Object r7 = r7.body()     // Catch: java.lang.Throwable -> L72
                com.igpsport.globalapp.core.NewCommonResponse r7 = (com.igpsport.globalapp.core.NewCommonResponse) r7     // Catch: java.lang.Throwable -> L72
                if (r7 == 0) goto L6a
                java.lang.Boolean r7 = r7.getData()     // Catch: java.lang.Throwable -> L72
                if (r7 == 0) goto L6a
                boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L72
                java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)     // Catch: java.lang.Throwable -> L72
                java.lang.Object r7 = kotlin.Result.m20711constructorimpl(r7)     // Catch: java.lang.Throwable -> L72
                goto L7d
            L6a:
                java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Throwable -> L72
                java.lang.String r3 = "delDevicePageLayoutInfo is null"
                r7.<init>(r3)     // Catch: java.lang.Throwable -> L72
                throw r7     // Catch: java.lang.Throwable -> L72
            L72:
                r7 = move-exception
                kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
                java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
                java.lang.Object r7 = kotlin.Result.m20711constructorimpl(r7)
            L7d:
                r3 = 0
                java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                boolean r5 = kotlin.Result.m20717isFailureimpl(r7)
                if (r5 == 0) goto L89
                r7 = r4
            L89:
                java.lang.Boolean r7 = (java.lang.Boolean) r7
                boolean r7 = r7.booleanValue()
                com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel r4 = com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.this
                androidx.lifecycle.MutableLiveData r4 = r4.isRequesting()
                java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                r4.postValue(r3)
                java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
                r3 = r6
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4 = 0
                r6.L$0 = r4
                r6.label = r2
                java.lang.Object r6 = r1.emit(r7, r3)
                if (r6 != r0) goto Laf
            Lae:
                return r0
            Laf:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.Bg3PageSettingViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Flow<Boolean> deleteServerPlan(String planId) {
        Intrinsics.checkNotNullParameter(planId, "planId");
        return FlowKt.flow(new AnonymousClass1(planId, null));
    }

    public final boolean isSchemeNameExist(String planName) {
        Intrinsics.checkNotNullParameter(planName, "planName");
        List<Bg3PagePlanInfoBean> value = this.bg3PagePlanInfoBeanList.getValue();
        if ((value instanceof Collection) && value.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = value.iterator();
        while (it2.hasNext()) {
            if (Intrinsics.areEqual(((Bg3PagePlanInfoBean) it2.next()).getPlanName(), planName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00ba  */
    public final Bg3PagePlanInfoBean createACopyOfCurrentPlan(String schemeName) {
        ArrayList arrayList;
        List<Bg3PageBean> pageList;
        Intrinsics.checkNotNullParameter(schemeName, "schemeName");
        Bg3PagePlanInfoBean value = this.bg3PagePlanInfoBean.getValue();
        if (value != null) {
            value.setSelected(false);
        }
        Bg3PagePlanInfoBean value2 = this.bg3PagePlanInfoBean.getValue();
        if (value2 == null) {
            return null;
        }
        Bg3PagePlanInfoBean value3 = this.bg3PagePlanInfoBean.getValue();
        if (value3 == null || (pageList = value3.getPageList()) == null) {
            arrayList = new ArrayList();
        } else {
            List<Bg3PageBean> list = pageList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Bg3PageBean bg3PageBean : list) {
                List<Bg3DataFieldBean> dataFieldList = bg3PageBean.getDataFieldList();
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dataFieldList, 10));
                Iterator<T> it2 = dataFieldList.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(Bg3DataFieldBean.copy$default((Bg3DataFieldBean) it2.next(), 0, null, false, null, null, null, null, null, 251, null));
                }
                arrayList2.add(Bg3PageBean.copy$default(bg3PageBean, null, false, false, 0, null, 0, null, null, null, null, null, CollectionsKt.toMutableList((Collection) arrayList3), 2047, null));
            }
            arrayList = CollectionsKt.toMutableList((Collection) arrayList2);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
        }
        return Bg3PagePlanInfoBean.copy$default(value2, 0L, false, null, schemeName, true, arrayList, 1, null);
    }

    public final List<Bg3PageBean> pageMsgListToPageBeanList(List<Config.page_msg> pageMsgList) {
        boolean zContains;
        boolean zContains2;
        Intrinsics.checkNotNullParameter(pageMsgList, "pageMsgList");
        ArrayList arrayList = new ArrayList();
        for (Iterator it2 = pageMsgList.iterator(); it2.hasNext(); it2 = it2) {
            Config.page_msg page_msgVar = (Config.page_msg) it2.next();
            Config.PAGE_TYPE pageType = page_msgVar.getPageType();
            StringBuilder sbAppend = new StringBuilder("页面配置-> ").append(pageType).append(SearchAddress.SEPARATOR).append(page_msgVar.getPageIndex()).append(SearchAddress.SEPARATOR).append(page_msgVar.getPageMode()).append(SearchAddress.SEPARATOR).append(page_msgVar.getLineWidth());
            String str = HexStringBuilder.DEFAULT_SEPARATOR;
            LogUtils.d(sbAppend.append(HexStringBuilder.DEFAULT_SEPARATOR).toString());
            Pair<List<Integer>, List<Integer>> pageItemDataHeightListAndSpanSizeList = Bg3PageConfigUtil.INSTANCE.getPageItemDataHeightListAndSpanSizeList(getDeviceName(), page_msgVar.getPageMode(), page_msgVar.getLineWidth());
            List<Integer> listComponent1 = pageItemDataHeightListAndSpanSizeList.component1();
            List<Integer> listComponent2 = pageItemDataHeightListAndSpanSizeList.component2();
            LogUtils.e("页面配置数据 pageMsg = " + page_msgVar);
            LogUtils.e("pageType = " + page_msgVar.getPageType());
            if (isBG1OrBG2()) {
                zContains = Bg3PageConfigUtil.INSTANCE.getAlwaysDisplayPageTypeList(getDeviceName()).contains(page_msgVar.getPageType());
            } else {
                zContains = page_msgVar.getForbiddenClose() == 1;
            }
            Config.PAGE_TYPE pageType2 = page_msgVar.getPageType();
            switch (pageType2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[pageType2.ordinal()]) {
                case 1:
                    String str2 = Utils.getApp().getString(R.string.page) + page_msgVar.getPageIndex();
                    boolean z = page_msgVar.getStatus() == 1;
                    int pageIndex = page_msgVar.getPageIndex();
                    Config.PAGE_TYPE pageType3 = page_msgVar.getPageType();
                    int pageMode = page_msgVar.getPageMode();
                    Integer numValueOf = Integer.valueOf(page_msgVar.getLineWidth());
                    Boolean boolValueOf = Boolean.valueOf(page_msgVar.getMainPageStatus() == 1);
                    Integer numValueOf2 = Integer.valueOf(page_msgVar.getGraphicDisplay());
                    Integer numValueOf3 = Integer.valueOf(page_msgVar.getDataSite());
                    List<Integer> dataList = page_msgVar.getDataList();
                    Intrinsics.checkNotNullExpressionValue(dataList, "getDataList(...)");
                    List<Integer> list = dataList;
                    boolean z2 = z;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it3 = list.iterator();
                    int i = 0;
                    while (it3.hasNext()) {
                        Object next = it3.next();
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Iterator it4 = it3;
                        Integer num = (Integer) next;
                        int i3 = pageIndex;
                        listComponent2.get(i).intValue();
                        Integer num2 = numValueOf2;
                        listComponent1.get(i).intValue();
                        boolean z3 = zContains;
                        String binaryString = StringUtils.toBinaryString(page_msgVar.getGraphicDisplay(), page_msgVar.getDataList().size());
                        Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(...)");
                        boolean z4 = CharsKt.digitToInt(StringsKt.reversed((CharSequence) binaryString).toString().charAt(i)) == 1;
                        Bg3PageConfigUtil bg3PageConfigUtil = Bg3PageConfigUtil.INSTANCE;
                        ArrayList arrayList3 = arrayList;
                        List<CategoryItem> value = this.currSportsModeAllFields.getValue();
                        Intrinsics.checkNotNull(num);
                        boolean zIsGraphicalDataField = bg3PageConfigUtil.isGraphicalDataField(value, num.intValue());
                        LogUtils.d("是否是图形化 设备isGraphFieldDevice = " + z4 + ", 后台isGraphFieldServer = " + zIsGraphicalDataField + "  " + num + str);
                        String defaultDataValue = Bg3PageConfigUtil.INSTANCE.getDefaultDataValue(this.currSportsModeAllFields.getValue(), num.intValue());
                        String graphImgResId = Bg3PageConfigUtil.INSTANCE.getGraphImgResId(this.currSportsModeAllFields.getValue(), getDeviceName(), num.intValue(), listComponent1.get(i).intValue(), listComponent2.get(i).intValue(), zIsGraphicalDataField);
                        String dataFieldResId = Bg3PageConfigUtil.INSTANCE.getDataFieldResId(this.currSportsModeAllFields.getValue(), num.intValue());
                        LogUtils.d("pageMsg.pageIndex = " + page_msgVar.getPageIndex() + ", i = " + num + ", displayedDefaultValue = " + defaultDataValue + ", isShowGraph = " + zIsGraphicalDataField + ", imageResId = " + graphImgResId + ", dataFieldResId = " + dataFieldResId);
                        arrayList2 = arrayList2;
                        arrayList2.add(new Bg3DataFieldBean(num.intValue(), defaultDataValue, false, listComponent1.get(i), listComponent2.get(i), Boolean.valueOf(zIsGraphicalDataField), graphImgResId, dataFieldResId));
                        zContains = z3;
                        i = i2;
                        it3 = it4;
                        pageIndex = i3;
                        numValueOf2 = num2;
                        str = str;
                        arrayList = arrayList3;
                    }
                    arrayList.add(new Bg3PageBean(str2, z2, zContains, pageIndex, pageType3, pageMode, numValueOf, boolValueOf, numValueOf2, numValueOf3, null, CollectionsKt.toMutableList((Collection) arrayList2), 1024, null));
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    boolean zHasForbiddenClose = page_msgVar.hasForbiddenClose();
                    if (zHasForbiddenClose) {
                        zContains2 = page_msgVar.getForbiddenClose() == 1;
                    } else {
                        zContains2 = Bg3PageConfigUtil.INSTANCE.getAlwaysDisplayPageTypeList(getDeviceName()).contains(page_msgVar.getPageType());
                    }
                    LogUtils.d("pageMsg.pageIndex = " + page_msgVar.getPageIndex() + ", pageMsg.pageType = " + page_msgVar.getPageType() + ", forbiddenClose = " + zContains2 + ", hasForbiddenClose = " + zHasForbiddenClose);
                    Bg3PageConfigUtil bg3PageConfigUtil2 = Bg3PageConfigUtil.INSTANCE;
                    String deviceName = getDeviceName();
                    Config.PAGE_TYPE pageType4 = page_msgVar.getPageType();
                    Intrinsics.checkNotNullExpressionValue(pageType4, "getPageType(...)");
                    String strComponent1 = bg3PageConfigUtil2.getBG1PicturePageNameAndChartImgResId(deviceName, pageType4, listComponent1.get(0).intValue()).component1();
                    boolean z5 = page_msgVar.getStatus() == 1;
                    int pageIndex2 = page_msgVar.getPageIndex();
                    Config.PAGE_TYPE pageType5 = page_msgVar.getPageType();
                    int pageMode2 = page_msgVar.getPageMode();
                    Integer numValueOf4 = Integer.valueOf(page_msgVar.getLineWidth());
                    Boolean boolValueOf2 = Boolean.valueOf(page_msgVar.getMainPageStatus() == 1);
                    Integer numValueOf5 = Integer.valueOf(page_msgVar.getGraphicDisplay());
                    Integer numValueOf6 = Integer.valueOf(page_msgVar.getDataSite());
                    List<Integer> dataList2 = page_msgVar.getDataList();
                    Intrinsics.checkNotNullExpressionValue(dataList2, "getDataList(...)");
                    List<Integer> list2 = dataList2;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    Iterator it5 = list2.iterator();
                    int i4 = 0;
                    while (it5.hasNext()) {
                        Object next2 = it5.next();
                        int i5 = i4 + 1;
                        if (i4 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Integer num3 = (Integer) next2;
                        listComponent2.get(i4).intValue();
                        Iterator it6 = it5;
                        listComponent1.get(i4).intValue();
                        boolean z6 = zContains2;
                        Bg3PageConfigUtil bg3PageConfigUtil3 = Bg3PageConfigUtil.INSTANCE;
                        List<CategoryItem> value2 = this.currSportsModeAllFields.getValue();
                        Intrinsics.checkNotNull(num3);
                        boolean zIsGraphicalDataField2 = bg3PageConfigUtil3.isGraphicalDataField(value2, num3.intValue());
                        String defaultDataValue2 = Bg3PageConfigUtil.INSTANCE.getDefaultDataValue(this.currSportsModeAllFields.getValue(), num3.intValue());
                        String graphImgResId2 = Bg3PageConfigUtil.INSTANCE.getGraphImgResId(this.currSportsModeAllFields.getValue(), this.getDeviceName(), num3.intValue(), listComponent1.get(i4).intValue(), listComponent2.get(i4).intValue(), zIsGraphicalDataField2);
                        String dataFieldResId2 = Bg3PageConfigUtil.INSTANCE.getDataFieldResId(this.currSportsModeAllFields.getValue(), num3.intValue());
                        LogUtils.d("图片页 pageMsg.pageIndex = " + page_msgVar.getPageIndex() + ", i = " + num3 + ", displayedDefaultValue = " + defaultDataValue2 + ", isShowGraph = " + zIsGraphicalDataField2 + ", imageResId = " + graphImgResId2 + ", dataFieldResId = " + dataFieldResId2);
                        arrayList4 = arrayList4;
                        arrayList4.add(new Bg3DataFieldBean(num3.intValue(), defaultDataValue2, false, listComponent1.get(i4), listComponent2.get(i4), Boolean.valueOf(zIsGraphicalDataField2), graphImgResId2, dataFieldResId2));
                        this = this;
                        zContains2 = z6;
                        it5 = it6;
                        i4 = i5;
                        arrayList = arrayList;
                    }
                    arrayList.add(new Bg3PageBean(strComponent1, z5, zContains2, pageIndex2, pageType5, pageMode2, numValueOf4, boolValueOf2, numValueOf5, numValueOf6, null, CollectionsKt.toMutableList((Collection) arrayList4), 1024, null));
                    break;
            }
        }
        return arrayList;
    }

    public final boolean isBG1OrBG2() {
        return DeviceConstant.INSTANCE.isIGSBG1(getDeviceName()) || DeviceConstant.INSTANCE.isIGSBG2(getDeviceName());
    }
}
