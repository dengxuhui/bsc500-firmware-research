package com.igpsport.globalapp.devicemodule.pagesetting.pagebg3.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bg3PageShowTypeBean.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J8\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageShowTypeBean;", "", "pageMode", "", "lineWidth", "imgRes", "isSelected", "", "<init>", "(IILjava/lang/Integer;Z)V", "getPageMode", "()I", "getLineWidth", "getImgRes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "copy", "(IILjava/lang/Integer;Z)Lcom/igpsport/globalapp/devicemodule/pagesetting/pagebg3/bean/Bg3PageShowTypeBean;", "equals", "other", "hashCode", "toString", "", "app_cnRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class Bg3PageShowTypeBean {
    public static final int $stable = 8;
    private final Integer imgRes;
    private boolean isSelected;
    private final int lineWidth;
    private final int pageMode;

    public static /* synthetic */ Bg3PageShowTypeBean copy$default(Bg3PageShowTypeBean bg3PageShowTypeBean, int i, int i2, Integer num, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bg3PageShowTypeBean.pageMode;
        }
        if ((i3 & 2) != 0) {
            i2 = bg3PageShowTypeBean.lineWidth;
        }
        if ((i3 & 4) != 0) {
            num = bg3PageShowTypeBean.imgRes;
        }
        if ((i3 & 8) != 0) {
            z = bg3PageShowTypeBean.isSelected;
        }
        return bg3PageShowTypeBean.copy(i, i2, num, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPageMode() {
        return this.pageMode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLineWidth() {
        return this.lineWidth;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getImgRes() {
        return this.imgRes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    public final Bg3PageShowTypeBean copy(int pageMode, int lineWidth, Integer imgRes, boolean isSelected) {
        return new Bg3PageShowTypeBean(pageMode, lineWidth, imgRes, isSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Bg3PageShowTypeBean)) {
            return false;
        }
        Bg3PageShowTypeBean bg3PageShowTypeBean = (Bg3PageShowTypeBean) other;
        return this.pageMode == bg3PageShowTypeBean.pageMode && this.lineWidth == bg3PageShowTypeBean.lineWidth && Intrinsics.areEqual(this.imgRes, bg3PageShowTypeBean.imgRes) && this.isSelected == bg3PageShowTypeBean.isSelected;
    }

    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.pageMode) * 31) + Integer.hashCode(this.lineWidth)) * 31;
        Integer num = this.imgRes;
        return ((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + Boolean.hashCode(this.isSelected);
    }

    public String toString() {
        return "Bg3PageShowTypeBean(pageMode=" + this.pageMode + ", lineWidth=" + this.lineWidth + ", imgRes=" + this.imgRes + ", isSelected=" + this.isSelected + ")";
    }

    public Bg3PageShowTypeBean(int i, int i2, Integer num, boolean z) {
        this.pageMode = i;
        this.lineWidth = i2;
        this.imgRes = num;
        this.isSelected = z;
    }

    public /* synthetic */ Bg3PageShowTypeBean(int i, int i2, Integer num, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? null : num, (i3 & 8) != 0 ? false : z);
    }

    public final int getPageMode() {
        return this.pageMode;
    }

    public final int getLineWidth() {
        return this.lineWidth;
    }

    public final Integer getImgRes() {
        return this.imgRes;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
