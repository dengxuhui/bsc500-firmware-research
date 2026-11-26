package com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.utils.field;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Bg3PageField.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/field/Bg3PageField;", "", "<init>", "()V", "Companion", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Bg3PageField {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Integer> batteryDataFieldList = CollectionsKt.listOf((Object[]) new Integer[]{2564, 2565, 2566});

    /* JADX INFO: compiled from: Bg3PageField.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/utils/field/Bg3PageField$Companion;", "", "<init>", "()V", "batteryDataFieldList", "", "", "getBatteryDataFieldList", "()Ljava/util/List;", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Integer> getBatteryDataFieldList() {
            return Bg3PageField.batteryDataFieldList;
        }
    }
}
