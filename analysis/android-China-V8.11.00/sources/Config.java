package com.igpsport.blelib.bean;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class Config {

    public interface accident_detection_msgOrBuilder extends MessageLiteOrBuilder {
        int getAccidentDetectionModeIndex();

        int getAccidentDetectionOpen();

        boolean hasAccidentDetectionModeIndex();

        boolean hasAccidentDetectionOpen();
    }

    public interface alarm_msgOrBuilder extends MessageLiteOrBuilder {
        int getAlarmIntervalTime();

        alarm_params_set_msg getSetMsg(int i);

        int getSetMsgCount();

        List<alarm_params_set_msg> getSetMsgList();

        boolean hasAlarmIntervalTime();
    }

    public interface alarm_params_set_msgOrBuilder extends MessageLiteOrBuilder {
        ALARM_TYPE getAlarmType();

        alarm_select_info getSelectInfo(int i);

        int getSelectInfoCount();

        List<alarm_select_info> getSelectInfoList();

        ALARM_SELECT getSelectType();

        int getStatus();

        int getValueMax();

        int getValueMin();

        boolean hasAlarmType();

        boolean hasSelectType();

        boolean hasStatus();

        boolean hasValueMax();

        boolean hasValueMin();
    }

    public interface alarm_select_infoOrBuilder extends MessageLiteOrBuilder {
        ALARM_SELECT getSelectType();

        int getValue();

        boolean hasSelectType();

        boolean hasValue();
    }

    public interface auto_set_msgOrBuilder extends MessageLiteOrBuilder {
        AUTO_TYPE getAutoType();

        int getParam1();

        int getStatus();

        boolean hasAutoType();

        boolean hasParam1();

        boolean hasStatus();
    }

    public interface backlight_msgOrBuilder extends MessageLiteOrBuilder {
        int getBacklightAuto();

        int getBacklightDayPercent();

        int getBacklightNightOn();

        int getBacklightNightPercent();

        int getBacklingTime();

        boolean hasBacklightAuto();

        boolean hasBacklightDayPercent();

        boolean hasBacklightNightOn();

        boolean hasBacklightNightPercent();

        boolean hasBacklingTime();
    }

    public interface bike_msgOrBuilder extends MessageLiteOrBuilder {
        int getAutoDia();

        int getBikeIndex();

        String getBikeName();

        ByteString getBikeNameBytes();

        int getBikeStatus();

        int getBikeWeigth();

        long getOdometer();

        int getWheelDia();

        boolean hasAutoDia();

        boolean hasBikeIndex();

        boolean hasBikeName();

        boolean hasBikeStatus();

        boolean hasBikeWeigth();

        boolean hasOdometer();

        boolean hasWheelDia();
    }

    public interface config_msgOrBuilder extends MessageLiteOrBuilder {
        accident_detection_msg getAccidentDetectionMessage();

        alarm_msg getAlarmMessage(int i);

        int getAlarmMessageCount();

        List<alarm_msg> getAlarmMessageList();

        int getAltitude();

        auto_set_msg getAutoSetMessage(int i);

        int getAutoSetMessageCount();

        List<auto_set_msg> getAutoSetMessageList();

        backlight_msg getBacklightMessage();

        bike_msg getBikeMessage(int i);

        int getBikeMessageCount();

        List<bike_msg> getBikeMessageList();

        CONFIG_OPERATE_TYPE getConfigOperateType();

        CONFIG_SERVICE_TYPE getConfigSeviceType();

        int getCurOperateMode();

        data_msg getDataMessage(int i);

        int getDataMessageCount();

        List<data_msg> getDataMessageList();

        key_set_msg getKeySetMessage(int i);

        int getKeySetMessageCount();

        List<key_set_msg> getKeySetMessageList();

        language_msg getLanguageMessage();

        lap_msg getLapMessage(int i);

        int getLapMessageCount();

        List<lap_msg> getLapMessageList();

        mode_config_msg getModeConfigMessage();

        mode_msg getModeMessage(int i);

        int getModeMessageCount();

        List<mode_msg> getModeMessageList();

        page_msg getPageMessage(int i);

        int getPageMessageCount();

        List<page_msg> getPageMessageList();

        cur_page_status_msg getPageStatusMessage();

        password_msg getPasswordMessage();

        Common.service_type_index getServiceType();

        sound_set_msg getSoundSetMessage(int i);

        int getSoundSetMessageCount();

        List<sound_set_msg> getSoundSetMessageList();

        TIME_SYSTEM_TYPE getTimeSystem();

        unit_msg getUnitMessage(int i);

        int getUnitMessageCount();

        List<unit_msg> getUnitMessageList();

        user_data_msg getUserDataMessage();

        boolean hasAccidentDetectionMessage();

        boolean hasAltitude();

        boolean hasBacklightMessage();

        boolean hasConfigOperateType();

        boolean hasConfigSeviceType();

        boolean hasCurOperateMode();

        boolean hasLanguageMessage();

        boolean hasModeConfigMessage();

        boolean hasPageStatusMessage();

        boolean hasPasswordMessage();

        boolean hasServiceType();

        boolean hasTimeSystem();

        boolean hasUserDataMessage();
    }

    public interface cur_page_status_msgOrBuilder extends MessageLiteOrBuilder {
        int getDataPageNumMax();

        LCD_TYPE getLcdType();

        int getMainPageOpen();

        int getPageLineDataMax();

        int getPageLineNumMax();

        int getSupportPageLayout(int i);

        int getSupportPageLayoutCount();

        List<Integer> getSupportPageLayoutList();

        int getUnsupportData(int i);

        int getUnsupportDataCount();

        List<Integer> getUnsupportDataList();

        boolean hasDataPageNumMax();

        boolean hasLcdType();

        boolean hasMainPageOpen();

        boolean hasPageLineDataMax();

        boolean hasPageLineNumMax();
    }

    public interface data_msgOrBuilder extends MessageLiteOrBuilder {
        DATA_TYPE getDataType();

        int getStatus();

        boolean hasDataType();

        boolean hasStatus();
    }

    public interface emergency_contact_msgOrBuilder extends MessageLiteOrBuilder {
        String getAreaCode();

        ByteString getAreaCodeBytes();

        String getEmail();

        ByteString getEmailBytes();

        String getName();

        ByteString getNameBytes();

        String getPhone();

        ByteString getPhoneBytes();

        boolean hasAreaCode();

        boolean hasEmail();

        boolean hasName();

        boolean hasPhone();
    }

    public interface key_set_msgOrBuilder extends MessageLiteOrBuilder {
        KEY_FUNCTION_TYPE getKeyFunctionType();

        KEY_FUNCTION_TYPE getKeySupport(int i);

        int getKeySupportCount();

        List<KEY_FUNCTION_TYPE> getKeySupportList();

        KEY_TYPE getKeyType();

        boolean hasKeyFunctionType();

        boolean hasKeyType();
    }

    public interface language_msgOrBuilder extends MessageLiteOrBuilder {
        LANGUAGE_TYPE getCurLanguage();

        LANGUAGE_TYPE getSupportedLanguage(int i);

        int getSupportedLanguageCount();

        List<LANGUAGE_TYPE> getSupportedLanguageList();

        boolean hasCurLanguage();
    }

    public interface lap_msgOrBuilder extends MessageLiteOrBuilder {
        LAP_TYPE getLapType();

        int getStatus();

        int getValue();

        boolean hasLapType();

        boolean hasStatus();

        boolean hasValue();
    }

    public interface mode_config_msgOrBuilder extends MessageLiteOrBuilder {
        int getDefaultModeNum();

        int getMaxModeNum();

        boolean hasDefaultModeNum();

        boolean hasMaxModeNum();
    }

    public interface mode_msgOrBuilder extends MessageLiteOrBuilder {
        int getInuse();

        int getModeColor();

        int getModeIndex();

        String getModeName();

        ByteString getModeNameBytes();

        int getTemplateIndex();

        int getValid();

        boolean hasInuse();

        boolean hasModeColor();

        boolean hasModeIndex();

        boolean hasModeName();

        boolean hasTemplateIndex();

        boolean hasValid();
    }

    public interface page_msgOrBuilder extends MessageLiteOrBuilder {
        int getData(int i);

        int getDataCount();

        List<Integer> getDataList();

        int getDataSite();

        int getForbiddenClose();

        int getGraphicDisplay();

        int getLineWidth();

        int getMainPageStatus();

        int getPageIndex();

        int getPageMode();

        String getPageName();

        ByteString getPageNameBytes();

        PAGE_TYPE getPageType();

        int getStatus();

        boolean hasDataSite();

        boolean hasForbiddenClose();

        boolean hasGraphicDisplay();

        boolean hasLineWidth();

        boolean hasMainPageStatus();

        boolean hasPageIndex();

        boolean hasPageMode();

        boolean hasPageName();

        boolean hasPageType();

        boolean hasStatus();
    }

    public interface password_msgOrBuilder extends MessageLiteOrBuilder {
        String getPassword();

        ByteString getPasswordBytes();

        boolean hasPassword();
    }

    public interface section_data_msgOrBuilder extends MessageLiteOrBuilder {
        int getCad(int i);

        int getCadCount();

        List<Integer> getCadList();

        int getFTP();

        int getHrm(int i);

        int getHrmCount();

        List<Integer> getHrmList();

        int getPower(int i);

        int getPowerCount();

        List<Integer> getPowerList();

        SECTION_HR_TYPE getRideHrType();

        int getRideHrm(int i);

        int getRideHrmCount();

        List<Integer> getRideHrmList();

        int getRideLacticAcidHrm(int i);

        int getRideLacticAcidHrmCount();

        List<Integer> getRideLacticAcidHrmList();

        int getRideReserveHr(int i);

        int getRideReserveHrCount();

        List<Integer> getRideReserveHrList();

        int getSpd(int i);

        int getSpdCount();

        List<Integer> getSpdList();

        boolean hasFTP();

        boolean hasRideHrType();
    }

    public interface sound_set_msgOrBuilder extends MessageLiteOrBuilder {
        SOUND_SCENE_TYPE getSoundScene();

        int getSoundType();

        int getStatus();

        boolean hasSoundScene();

        boolean hasSoundType();

        boolean hasStatus();
    }

    public interface unit_msgOrBuilder extends MessageLiteOrBuilder {
        UNIT_ITEM getUnitItem();

        UNIT_TYPE getUnitType();

        boolean hasUnitItem();

        boolean hasUnitType();
    }

    public interface user_data_msgOrBuilder extends MessageLiteOrBuilder {
        int getAge();

        int getBirthday();

        emergency_contact_msg getEmergencyContact(int i);

        int getEmergencyContactCount();

        List<emergency_contact_msg> getEmergencyContactList();

        int getHeight();

        String getMemberId();

        ByteString getMemberIdBytes();

        int getPersonalInforChangeTime();

        int getRestHr();

        int getRideHrLacticAcid();

        int getRideHrMax();

        section_data_msg getSectionData();

        int getSex();

        int getTimeZone();

        int getUpdateStatus();

        int getWeight();

        boolean hasAge();

        boolean hasBirthday();

        boolean hasHeight();

        boolean hasMemberId();

        boolean hasPersonalInforChangeTime();

        boolean hasRestHr();

        boolean hasRideHrLacticAcid();

        boolean hasRideHrMax();

        boolean hasSectionData();

        boolean hasSex();

        boolean hasTimeZone();

        boolean hasUpdateStatus();

        boolean hasWeight();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Config() {
    }

    public enum CONFIG_SERVICE_TYPE implements Internal.EnumLite {
        enum_CONFIG_SERVICE_TYPE_NONE(0),
        enum_CONFIG_SERVICE_TYPE_USER(1),
        enum_CONFIG_SERVICE_TYPE_PAGE(2),
        enum_CONFIG_SERVICE_TYPE_BIKE(3),
        enum_CONFIG_SERVICE_TYPE_UNIT(4),
        enum_CONFIG_SERVICE_TYPE_LANG(5),
        enum_CONFIG_SERVICE_TYPE_KEY(6),
        enum_CONFIG_SERVICE_TYPE_WHEEL(7),
        enum_CONFIG_SERVICE_TYPE_GPS(16),
        enum_CONFIG_SERVICE_TYPE_SOUND(17),
        enum_CONFIG_SERVICE_TYPE_POWER(18),
        enum_CONFIG_SERVICE_TYPE_DIS_COLOR(19),
        enum_CONFIG_SERVICE_TYPE_BK(20),
        enum_CONFIG_SERVICE_TYPE_TIME_SYS(21),
        enum_CONFIG_SERVICE_TYPE_ALARM(48),
        enum_CONFIG_SERVICE_TYPE_LAP(49),
        enum_CONFIG_SERVICE_TYPE_AUTO(50),
        enum_CONFIG_SERVICE_TYPE_MODE(51),
        enum_CONFIG_SERVICE_TYPE_ALTITUDE(52),
        enum_CONFIG_SERVICE_TYPE_DATA(53),
        enum_CONFIG_SERVICE_TYPE_MODE_TEMPLATE(54),
        enum_CONFIG_SERVICE_TYPE_PASSWORD(55),
        enum_CONFIG_SERVICE_TYPE_ACCIDENT(56);

        public static final int enum_CONFIG_SERVICE_TYPE_ACCIDENT_VALUE = 56;
        public static final int enum_CONFIG_SERVICE_TYPE_ALARM_VALUE = 48;
        public static final int enum_CONFIG_SERVICE_TYPE_ALTITUDE_VALUE = 52;
        public static final int enum_CONFIG_SERVICE_TYPE_AUTO_VALUE = 50;
        public static final int enum_CONFIG_SERVICE_TYPE_BIKE_VALUE = 3;
        public static final int enum_CONFIG_SERVICE_TYPE_BK_VALUE = 20;
        public static final int enum_CONFIG_SERVICE_TYPE_DATA_VALUE = 53;
        public static final int enum_CONFIG_SERVICE_TYPE_DIS_COLOR_VALUE = 19;
        public static final int enum_CONFIG_SERVICE_TYPE_GPS_VALUE = 16;
        public static final int enum_CONFIG_SERVICE_TYPE_KEY_VALUE = 6;
        public static final int enum_CONFIG_SERVICE_TYPE_LANG_VALUE = 5;
        public static final int enum_CONFIG_SERVICE_TYPE_LAP_VALUE = 49;
        public static final int enum_CONFIG_SERVICE_TYPE_MODE_TEMPLATE_VALUE = 54;
        public static final int enum_CONFIG_SERVICE_TYPE_MODE_VALUE = 51;
        public static final int enum_CONFIG_SERVICE_TYPE_NONE_VALUE = 0;
        public static final int enum_CONFIG_SERVICE_TYPE_PAGE_VALUE = 2;
        public static final int enum_CONFIG_SERVICE_TYPE_PASSWORD_VALUE = 55;
        public static final int enum_CONFIG_SERVICE_TYPE_POWER_VALUE = 18;
        public static final int enum_CONFIG_SERVICE_TYPE_SOUND_VALUE = 17;
        public static final int enum_CONFIG_SERVICE_TYPE_TIME_SYS_VALUE = 21;
        public static final int enum_CONFIG_SERVICE_TYPE_UNIT_VALUE = 4;
        public static final int enum_CONFIG_SERVICE_TYPE_USER_VALUE = 1;
        public static final int enum_CONFIG_SERVICE_TYPE_WHEEL_VALUE = 7;
        private static final Internal.EnumLiteMap<CONFIG_SERVICE_TYPE> internalValueMap = new Internal.EnumLiteMap<CONFIG_SERVICE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.CONFIG_SERVICE_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CONFIG_SERVICE_TYPE findValueByNumber(int i) {
                return CONFIG_SERVICE_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CONFIG_SERVICE_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static CONFIG_SERVICE_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return enum_CONFIG_SERVICE_TYPE_NONE;
                case 1:
                    return enum_CONFIG_SERVICE_TYPE_USER;
                case 2:
                    return enum_CONFIG_SERVICE_TYPE_PAGE;
                case 3:
                    return enum_CONFIG_SERVICE_TYPE_BIKE;
                case 4:
                    return enum_CONFIG_SERVICE_TYPE_UNIT;
                case 5:
                    return enum_CONFIG_SERVICE_TYPE_LANG;
                case 6:
                    return enum_CONFIG_SERVICE_TYPE_KEY;
                case 7:
                    return enum_CONFIG_SERVICE_TYPE_WHEEL;
                default:
                    switch (i) {
                        case 16:
                            return enum_CONFIG_SERVICE_TYPE_GPS;
                        case 17:
                            return enum_CONFIG_SERVICE_TYPE_SOUND;
                        case 18:
                            return enum_CONFIG_SERVICE_TYPE_POWER;
                        case 19:
                            return enum_CONFIG_SERVICE_TYPE_DIS_COLOR;
                        case 20:
                            return enum_CONFIG_SERVICE_TYPE_BK;
                        case 21:
                            return enum_CONFIG_SERVICE_TYPE_TIME_SYS;
                        default:
                            switch (i) {
                                case 48:
                                    return enum_CONFIG_SERVICE_TYPE_ALARM;
                                case 49:
                                    return enum_CONFIG_SERVICE_TYPE_LAP;
                                case 50:
                                    return enum_CONFIG_SERVICE_TYPE_AUTO;
                                case 51:
                                    return enum_CONFIG_SERVICE_TYPE_MODE;
                                case 52:
                                    return enum_CONFIG_SERVICE_TYPE_ALTITUDE;
                                case 53:
                                    return enum_CONFIG_SERVICE_TYPE_DATA;
                                case 54:
                                    return enum_CONFIG_SERVICE_TYPE_MODE_TEMPLATE;
                                case 55:
                                    return enum_CONFIG_SERVICE_TYPE_PASSWORD;
                                case 56:
                                    return enum_CONFIG_SERVICE_TYPE_ACCIDENT;
                                default:
                                    return null;
                            }
                    }
            }
        }

        public static Internal.EnumLiteMap<CONFIG_SERVICE_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CONFIG_SERVICE_TYPEVerifier.INSTANCE;
        }

        private static final class CONFIG_SERVICE_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CONFIG_SERVICE_TYPEVerifier();

            private CONFIG_SERVICE_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return CONFIG_SERVICE_TYPE.forNumber(i) != null;
            }
        }

        CONFIG_SERVICE_TYPE(int i) {
            this.value = i;
        }
    }

    public enum CONFIG_OPERATE_TYPE implements Internal.EnumLite {
        enum_CONFIG_OPERATE_TYPE_NONE(0),
        enum_CONFIG_OPERATE_TYPE_SET(1),
        enum_CONFIG_OPERATE_TYPE_GET(2),
        enum_CONFIG_OPERATE_TYPE_SEND(3),
        enum_CONFIG_OPERATE_TYPE_ADD(4),
        enum_CONFIG_OPERATE_TYPE_DEL(5),
        enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO(6),
        enum_CONFIG_OPERATE_TYPE_CTRL(7),
        enum_CONFIG_OPERATE_TYPE_SET_DEFAULT(8);

        public static final int enum_CONFIG_OPERATE_TYPE_ADD_VALUE = 4;
        public static final int enum_CONFIG_OPERATE_TYPE_CTRL_VALUE = 7;
        public static final int enum_CONFIG_OPERATE_TYPE_DEL_VALUE = 5;
        public static final int enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO_VALUE = 6;
        public static final int enum_CONFIG_OPERATE_TYPE_GET_VALUE = 2;
        public static final int enum_CONFIG_OPERATE_TYPE_NONE_VALUE = 0;
        public static final int enum_CONFIG_OPERATE_TYPE_SEND_VALUE = 3;
        public static final int enum_CONFIG_OPERATE_TYPE_SET_DEFAULT_VALUE = 8;
        public static final int enum_CONFIG_OPERATE_TYPE_SET_VALUE = 1;
        private static final Internal.EnumLiteMap<CONFIG_OPERATE_TYPE> internalValueMap = new Internal.EnumLiteMap<CONFIG_OPERATE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.CONFIG_OPERATE_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CONFIG_OPERATE_TYPE findValueByNumber(int i) {
                return CONFIG_OPERATE_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CONFIG_OPERATE_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static CONFIG_OPERATE_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return enum_CONFIG_OPERATE_TYPE_NONE;
                case 1:
                    return enum_CONFIG_OPERATE_TYPE_SET;
                case 2:
                    return enum_CONFIG_OPERATE_TYPE_GET;
                case 3:
                    return enum_CONFIG_OPERATE_TYPE_SEND;
                case 4:
                    return enum_CONFIG_OPERATE_TYPE_ADD;
                case 5:
                    return enum_CONFIG_OPERATE_TYPE_DEL;
                case 6:
                    return enum_CONFIG_OPERATE_TYPE_GET_MODULE_INFO;
                case 7:
                    return enum_CONFIG_OPERATE_TYPE_CTRL;
                case 8:
                    return enum_CONFIG_OPERATE_TYPE_SET_DEFAULT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<CONFIG_OPERATE_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CONFIG_OPERATE_TYPEVerifier.INSTANCE;
        }

        private static final class CONFIG_OPERATE_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CONFIG_OPERATE_TYPEVerifier();

            private CONFIG_OPERATE_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return CONFIG_OPERATE_TYPE.forNumber(i) != null;
            }
        }

        CONFIG_OPERATE_TYPE(int i) {
            this.value = i;
        }
    }

    public enum SOUND_TYPE implements Internal.EnumLite {
        enum_SOUND_TYPE_INVALID(0),
        enum_SOUND_RECORD_START(1),
        enum_SOUND_RECORD_PAUSE(2),
        enum_SOUND_RECORD_SAVE(4),
        enum_SOUND_LAP(8),
        enum_SOUND_ALART(16),
        enum_SOUND_KEY(32),
        enum_SOUND_USB(64),
        enum_SOUND_GPS(128),
        enum_SOUND_SENSOR(256),
        enum_SOUND_CALL(512),
        enum_SOUND_SOCIAL(1024),
        enum_SOUND_BURGLAR_ALARM(2048),
        enum_SOUND_DEV_FIND(4096);

        public static final int enum_SOUND_ALART_VALUE = 16;
        public static final int enum_SOUND_BURGLAR_ALARM_VALUE = 2048;
        public static final int enum_SOUND_CALL_VALUE = 512;
        public static final int enum_SOUND_DEV_FIND_VALUE = 4096;
        public static final int enum_SOUND_GPS_VALUE = 128;
        public static final int enum_SOUND_KEY_VALUE = 32;
        public static final int enum_SOUND_LAP_VALUE = 8;
        public static final int enum_SOUND_RECORD_PAUSE_VALUE = 2;
        public static final int enum_SOUND_RECORD_SAVE_VALUE = 4;
        public static final int enum_SOUND_RECORD_START_VALUE = 1;
        public static final int enum_SOUND_SENSOR_VALUE = 256;
        public static final int enum_SOUND_SOCIAL_VALUE = 1024;
        public static final int enum_SOUND_TYPE_INVALID_VALUE = 0;
        public static final int enum_SOUND_USB_VALUE = 64;
        private static final Internal.EnumLiteMap<SOUND_TYPE> internalValueMap = new Internal.EnumLiteMap<SOUND_TYPE>() { // from class: com.igpsport.blelib.bean.Config.SOUND_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SOUND_TYPE findValueByNumber(int i) {
                return SOUND_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SOUND_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static SOUND_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_SOUND_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_SOUND_RECORD_START;
            }
            if (i == 2) {
                return enum_SOUND_RECORD_PAUSE;
            }
            switch (i) {
                case 4:
                    return enum_SOUND_RECORD_SAVE;
                case 8:
                    return enum_SOUND_LAP;
                case 16:
                    return enum_SOUND_ALART;
                case 32:
                    return enum_SOUND_KEY;
                case 64:
                    return enum_SOUND_USB;
                case 128:
                    return enum_SOUND_GPS;
                case 256:
                    return enum_SOUND_SENSOR;
                case 512:
                    return enum_SOUND_CALL;
                case 1024:
                    return enum_SOUND_SOCIAL;
                case 2048:
                    return enum_SOUND_BURGLAR_ALARM;
                case 4096:
                    return enum_SOUND_DEV_FIND;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<SOUND_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SOUND_TYPEVerifier.INSTANCE;
        }

        private static final class SOUND_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SOUND_TYPEVerifier();

            private SOUND_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return SOUND_TYPE.forNumber(i) != null;
            }
        }

        SOUND_TYPE(int i) {
            this.value = i;
        }
    }

    public enum SOUND_SCENE_TYPE implements Internal.EnumLite {
        enum_SOUND_SCENE_TYPE_INVALID(0),
        enum_ALL_SCENE(1),
        enum_RECORDING_SCENE(2);

        public static final int enum_ALL_SCENE_VALUE = 1;
        public static final int enum_RECORDING_SCENE_VALUE = 2;
        public static final int enum_SOUND_SCENE_TYPE_INVALID_VALUE = 0;
        private static final Internal.EnumLiteMap<SOUND_SCENE_TYPE> internalValueMap = new Internal.EnumLiteMap<SOUND_SCENE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.SOUND_SCENE_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SOUND_SCENE_TYPE findValueByNumber(int i) {
                return SOUND_SCENE_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SOUND_SCENE_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static SOUND_SCENE_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_SOUND_SCENE_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_ALL_SCENE;
            }
            if (i != 2) {
                return null;
            }
            return enum_RECORDING_SCENE;
        }

        public static Internal.EnumLiteMap<SOUND_SCENE_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SOUND_SCENE_TYPEVerifier.INSTANCE;
        }

        private static final class SOUND_SCENE_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SOUND_SCENE_TYPEVerifier();

            private SOUND_SCENE_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return SOUND_SCENE_TYPE.forNumber(i) != null;
            }
        }

        SOUND_SCENE_TYPE(int i) {
            this.value = i;
        }
    }

    public enum KEY_TYPE implements Internal.EnumLite {
        enum_KEY_TYPE_INVALID(0),
        enum_KEY1(1),
        enum_KEY2(2),
        enum_KEY3(3),
        enum_KEY4(4),
        enum_KEY5(5),
        enum_KEY6(6);

        public static final int enum_KEY1_VALUE = 1;
        public static final int enum_KEY2_VALUE = 2;
        public static final int enum_KEY3_VALUE = 3;
        public static final int enum_KEY4_VALUE = 4;
        public static final int enum_KEY5_VALUE = 5;
        public static final int enum_KEY6_VALUE = 6;
        public static final int enum_KEY_TYPE_INVALID_VALUE = 0;
        private static final Internal.EnumLiteMap<KEY_TYPE> internalValueMap = new Internal.EnumLiteMap<KEY_TYPE>() { // from class: com.igpsport.blelib.bean.Config.KEY_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public KEY_TYPE findValueByNumber(int i) {
                return KEY_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static KEY_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static KEY_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return enum_KEY_TYPE_INVALID;
                case 1:
                    return enum_KEY1;
                case 2:
                    return enum_KEY2;
                case 3:
                    return enum_KEY3;
                case 4:
                    return enum_KEY4;
                case 5:
                    return enum_KEY5;
                case 6:
                    return enum_KEY6;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<KEY_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return KEY_TYPEVerifier.INSTANCE;
        }

        private static final class KEY_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new KEY_TYPEVerifier();

            private KEY_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return KEY_TYPE.forNumber(i) != null;
            }
        }

        KEY_TYPE(int i) {
            this.value = i;
        }
    }

    public enum KEY_FUNCTION_TYPE implements Internal.EnumLite {
        enum_KEY_FUNCTION_TYPE_INVALID(0),
        enum_RECORD_STATUS_MANUAL_PAUSE(1),
        enum_RECORD_STATUS_MANUAL_LAP(2),
        enum_RECORD_STATUS_FRONT_LIGHT(3),
        enum_RECORD_STATUS_TAIL_LIGHT(4);

        public static final int enum_KEY_FUNCTION_TYPE_INVALID_VALUE = 0;
        public static final int enum_RECORD_STATUS_FRONT_LIGHT_VALUE = 3;
        public static final int enum_RECORD_STATUS_MANUAL_LAP_VALUE = 2;
        public static final int enum_RECORD_STATUS_MANUAL_PAUSE_VALUE = 1;
        public static final int enum_RECORD_STATUS_TAIL_LIGHT_VALUE = 4;
        private static final Internal.EnumLiteMap<KEY_FUNCTION_TYPE> internalValueMap = new Internal.EnumLiteMap<KEY_FUNCTION_TYPE>() { // from class: com.igpsport.blelib.bean.Config.KEY_FUNCTION_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public KEY_FUNCTION_TYPE findValueByNumber(int i) {
                return KEY_FUNCTION_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static KEY_FUNCTION_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static KEY_FUNCTION_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_KEY_FUNCTION_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_RECORD_STATUS_MANUAL_PAUSE;
            }
            if (i == 2) {
                return enum_RECORD_STATUS_MANUAL_LAP;
            }
            if (i == 3) {
                return enum_RECORD_STATUS_FRONT_LIGHT;
            }
            if (i != 4) {
                return null;
            }
            return enum_RECORD_STATUS_TAIL_LIGHT;
        }

        public static Internal.EnumLiteMap<KEY_FUNCTION_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return KEY_FUNCTION_TYPEVerifier.INSTANCE;
        }

        private static final class KEY_FUNCTION_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new KEY_FUNCTION_TYPEVerifier();

            private KEY_FUNCTION_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return KEY_FUNCTION_TYPE.forNumber(i) != null;
            }
        }

        KEY_FUNCTION_TYPE(int i) {
            this.value = i;
        }
    }

    public enum AUTO_TYPE implements Internal.EnumLite {
        enum_AUTO_TYPE_INVALID(0),
        enum_PAUSE(1),
        enum_RECORD_START(2),
        enum_POWER_OFF(3),
        enum_SLEEP(4),
        enum_HOME_PAGE_BACK(5),
        enum_SMART_SAVE(6),
        enum_POWER_SAVE(7),
        enum_PAGE_AUTO(8),
        enum_MOTION_CHECK(9);

        public static final int enum_AUTO_TYPE_INVALID_VALUE = 0;
        public static final int enum_HOME_PAGE_BACK_VALUE = 5;
        public static final int enum_MOTION_CHECK_VALUE = 9;
        public static final int enum_PAGE_AUTO_VALUE = 8;
        public static final int enum_PAUSE_VALUE = 1;
        public static final int enum_POWER_OFF_VALUE = 3;
        public static final int enum_POWER_SAVE_VALUE = 7;
        public static final int enum_RECORD_START_VALUE = 2;
        public static final int enum_SLEEP_VALUE = 4;
        public static final int enum_SMART_SAVE_VALUE = 6;
        private static final Internal.EnumLiteMap<AUTO_TYPE> internalValueMap = new Internal.EnumLiteMap<AUTO_TYPE>() { // from class: com.igpsport.blelib.bean.Config.AUTO_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AUTO_TYPE findValueByNumber(int i) {
                return AUTO_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AUTO_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static AUTO_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return enum_AUTO_TYPE_INVALID;
                case 1:
                    return enum_PAUSE;
                case 2:
                    return enum_RECORD_START;
                case 3:
                    return enum_POWER_OFF;
                case 4:
                    return enum_SLEEP;
                case 5:
                    return enum_HOME_PAGE_BACK;
                case 6:
                    return enum_SMART_SAVE;
                case 7:
                    return enum_POWER_SAVE;
                case 8:
                    return enum_PAGE_AUTO;
                case 9:
                    return enum_MOTION_CHECK;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<AUTO_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return AUTO_TYPEVerifier.INSTANCE;
        }

        private static final class AUTO_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new AUTO_TYPEVerifier();

            private AUTO_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return AUTO_TYPE.forNumber(i) != null;
            }
        }

        AUTO_TYPE(int i) {
            this.value = i;
        }
    }

    public enum POWER_SAVE_STATUS implements Internal.EnumLite {
        POWER_SAVE_STATUS_OFF(0),
        POWER_SAVE_STATUS_ON(1),
        POWER_SAVE_STATUS_SMART(2);

        public static final int POWER_SAVE_STATUS_OFF_VALUE = 0;
        public static final int POWER_SAVE_STATUS_ON_VALUE = 1;
        public static final int POWER_SAVE_STATUS_SMART_VALUE = 2;
        private static final Internal.EnumLiteMap<POWER_SAVE_STATUS> internalValueMap = new Internal.EnumLiteMap<POWER_SAVE_STATUS>() { // from class: com.igpsport.blelib.bean.Config.POWER_SAVE_STATUS.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public POWER_SAVE_STATUS findValueByNumber(int i) {
                return POWER_SAVE_STATUS.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static POWER_SAVE_STATUS valueOf(int i) {
            return forNumber(i);
        }

        public static POWER_SAVE_STATUS forNumber(int i) {
            if (i == 0) {
                return POWER_SAVE_STATUS_OFF;
            }
            if (i == 1) {
                return POWER_SAVE_STATUS_ON;
            }
            if (i != 2) {
                return null;
            }
            return POWER_SAVE_STATUS_SMART;
        }

        public static Internal.EnumLiteMap<POWER_SAVE_STATUS> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return POWER_SAVE_STATUSVerifier.INSTANCE;
        }

        private static final class POWER_SAVE_STATUSVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new POWER_SAVE_STATUSVerifier();

            private POWER_SAVE_STATUSVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return POWER_SAVE_STATUS.forNumber(i) != null;
            }
        }

        POWER_SAVE_STATUS(int i) {
            this.value = i;
        }
    }

    public enum PAGE_AUTO_STATUS implements Internal.EnumLite {
        PAGE_AUTO_STATUS_OFF(0),
        PAGE_AUTO_STATUS_ON(1),
        PAGE_AUTO_STATUS_FAST(2),
        PAGE_AUTO_STATUS_SLOW(3),
        PAGE_AUTO_STATUS_MAIN(4);

        public static final int PAGE_AUTO_STATUS_FAST_VALUE = 2;
        public static final int PAGE_AUTO_STATUS_MAIN_VALUE = 4;
        public static final int PAGE_AUTO_STATUS_OFF_VALUE = 0;
        public static final int PAGE_AUTO_STATUS_ON_VALUE = 1;
        public static final int PAGE_AUTO_STATUS_SLOW_VALUE = 3;
        private static final Internal.EnumLiteMap<PAGE_AUTO_STATUS> internalValueMap = new Internal.EnumLiteMap<PAGE_AUTO_STATUS>() { // from class: com.igpsport.blelib.bean.Config.PAGE_AUTO_STATUS.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PAGE_AUTO_STATUS findValueByNumber(int i) {
                return PAGE_AUTO_STATUS.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PAGE_AUTO_STATUS valueOf(int i) {
            return forNumber(i);
        }

        public static PAGE_AUTO_STATUS forNumber(int i) {
            if (i == 0) {
                return PAGE_AUTO_STATUS_OFF;
            }
            if (i == 1) {
                return PAGE_AUTO_STATUS_ON;
            }
            if (i == 2) {
                return PAGE_AUTO_STATUS_FAST;
            }
            if (i == 3) {
                return PAGE_AUTO_STATUS_SLOW;
            }
            if (i != 4) {
                return null;
            }
            return PAGE_AUTO_STATUS_MAIN;
        }

        public static Internal.EnumLiteMap<PAGE_AUTO_STATUS> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PAGE_AUTO_STATUSVerifier.INSTANCE;
        }

        private static final class PAGE_AUTO_STATUSVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new PAGE_AUTO_STATUSVerifier();

            private PAGE_AUTO_STATUSVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return PAGE_AUTO_STATUS.forNumber(i) != null;
            }
        }

        PAGE_AUTO_STATUS(int i) {
            this.value = i;
        }
    }

    public enum ALARM_TYPE implements Internal.EnumLite {
        ALARM_TYPE_INVALID(0),
        ALARM_TYPE_RIDE_TIME(1),
        ALARM_TYPE_RIDE_DISTANCE(2),
        ALARM_TYPE_HRM(3),
        ALARM_TYPE_CAD(4),
        ALARM_TYPE_PWR(5),
        ALARM_TYPE_CALORIE(6),
        ALARM_TYPE_SPEED(7),
        ALARM_TYPE_WATER(8),
        ALARM_TYPE_FOOD(9),
        ALARM_TYPE_CORE_TEMP(10);

        public static final int ALARM_TYPE_CAD_VALUE = 4;
        public static final int ALARM_TYPE_CALORIE_VALUE = 6;
        public static final int ALARM_TYPE_CORE_TEMP_VALUE = 10;
        public static final int ALARM_TYPE_FOOD_VALUE = 9;
        public static final int ALARM_TYPE_HRM_VALUE = 3;
        public static final int ALARM_TYPE_INVALID_VALUE = 0;
        public static final int ALARM_TYPE_PWR_VALUE = 5;
        public static final int ALARM_TYPE_RIDE_DISTANCE_VALUE = 2;
        public static final int ALARM_TYPE_RIDE_TIME_VALUE = 1;
        public static final int ALARM_TYPE_SPEED_VALUE = 7;
        public static final int ALARM_TYPE_WATER_VALUE = 8;
        private static final Internal.EnumLiteMap<ALARM_TYPE> internalValueMap = new Internal.EnumLiteMap<ALARM_TYPE>() { // from class: com.igpsport.blelib.bean.Config.ALARM_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ALARM_TYPE findValueByNumber(int i) {
                return ALARM_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ALARM_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static ALARM_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return ALARM_TYPE_INVALID;
                case 1:
                    return ALARM_TYPE_RIDE_TIME;
                case 2:
                    return ALARM_TYPE_RIDE_DISTANCE;
                case 3:
                    return ALARM_TYPE_HRM;
                case 4:
                    return ALARM_TYPE_CAD;
                case 5:
                    return ALARM_TYPE_PWR;
                case 6:
                    return ALARM_TYPE_CALORIE;
                case 7:
                    return ALARM_TYPE_SPEED;
                case 8:
                    return ALARM_TYPE_WATER;
                case 9:
                    return ALARM_TYPE_FOOD;
                case 10:
                    return ALARM_TYPE_CORE_TEMP;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ALARM_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ALARM_TYPEVerifier.INSTANCE;
        }

        private static final class ALARM_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ALARM_TYPEVerifier();

            private ALARM_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return ALARM_TYPE.forNumber(i) != null;
            }
        }

        ALARM_TYPE(int i) {
            this.value = i;
        }
    }

    public enum ALARM_SELECT implements Internal.EnumLite {
        ALARM_SELECT_INVALID(0),
        ALARM_SELECT_RIDE_TIME(1),
        ALARM_SELECT_DISTANCE(2),
        ALARM_SELECT_CALORIE(3);

        public static final int ALARM_SELECT_CALORIE_VALUE = 3;
        public static final int ALARM_SELECT_DISTANCE_VALUE = 2;
        public static final int ALARM_SELECT_INVALID_VALUE = 0;
        public static final int ALARM_SELECT_RIDE_TIME_VALUE = 1;
        private static final Internal.EnumLiteMap<ALARM_SELECT> internalValueMap = new Internal.EnumLiteMap<ALARM_SELECT>() { // from class: com.igpsport.blelib.bean.Config.ALARM_SELECT.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ALARM_SELECT findValueByNumber(int i) {
                return ALARM_SELECT.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ALARM_SELECT valueOf(int i) {
            return forNumber(i);
        }

        public static ALARM_SELECT forNumber(int i) {
            if (i == 0) {
                return ALARM_SELECT_INVALID;
            }
            if (i == 1) {
                return ALARM_SELECT_RIDE_TIME;
            }
            if (i == 2) {
                return ALARM_SELECT_DISTANCE;
            }
            if (i != 3) {
                return null;
            }
            return ALARM_SELECT_CALORIE;
        }

        public static Internal.EnumLiteMap<ALARM_SELECT> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ALARM_SELECTVerifier.INSTANCE;
        }

        private static final class ALARM_SELECTVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ALARM_SELECTVerifier();

            private ALARM_SELECTVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return ALARM_SELECT.forNumber(i) != null;
            }
        }

        ALARM_SELECT(int i) {
            this.value = i;
        }
    }

    public enum LAP_TYPE implements Internal.EnumLite {
        LAP_TYPE_INVALID(0),
        LAP_TYPE_LOCATION(1),
        LAP_TYPE_TIME(2),
        LAP_TYPE_DISTANCE(3);

        public static final int LAP_TYPE_DISTANCE_VALUE = 3;
        public static final int LAP_TYPE_INVALID_VALUE = 0;
        public static final int LAP_TYPE_LOCATION_VALUE = 1;
        public static final int LAP_TYPE_TIME_VALUE = 2;
        private static final Internal.EnumLiteMap<LAP_TYPE> internalValueMap = new Internal.EnumLiteMap<LAP_TYPE>() { // from class: com.igpsport.blelib.bean.Config.LAP_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LAP_TYPE findValueByNumber(int i) {
                return LAP_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LAP_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static LAP_TYPE forNumber(int i) {
            if (i == 0) {
                return LAP_TYPE_INVALID;
            }
            if (i == 1) {
                return LAP_TYPE_LOCATION;
            }
            if (i == 2) {
                return LAP_TYPE_TIME;
            }
            if (i != 3) {
                return null;
            }
            return LAP_TYPE_DISTANCE;
        }

        public static Internal.EnumLiteMap<LAP_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LAP_TYPEVerifier.INSTANCE;
        }

        private static final class LAP_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new LAP_TYPEVerifier();

            private LAP_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return LAP_TYPE.forNumber(i) != null;
            }
        }

        LAP_TYPE(int i) {
            this.value = i;
        }
    }

    public enum UNIT_TYPE implements Internal.EnumLite {
        enum_UNIT_TYPE_INVALID(0),
        enum_UNIT_TYPE_METRIC(1),
        enum_UNIT_TYPE_INCH(2);

        public static final int enum_UNIT_TYPE_INCH_VALUE = 2;
        public static final int enum_UNIT_TYPE_INVALID_VALUE = 0;
        public static final int enum_UNIT_TYPE_METRIC_VALUE = 1;
        private static final Internal.EnumLiteMap<UNIT_TYPE> internalValueMap = new Internal.EnumLiteMap<UNIT_TYPE>() { // from class: com.igpsport.blelib.bean.Config.UNIT_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UNIT_TYPE findValueByNumber(int i) {
                return UNIT_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UNIT_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static UNIT_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_UNIT_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_UNIT_TYPE_METRIC;
            }
            if (i != 2) {
                return null;
            }
            return enum_UNIT_TYPE_INCH;
        }

        public static Internal.EnumLiteMap<UNIT_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return UNIT_TYPEVerifier.INSTANCE;
        }

        private static final class UNIT_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new UNIT_TYPEVerifier();

            private UNIT_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return UNIT_TYPE.forNumber(i) != null;
            }
        }

        UNIT_TYPE(int i) {
            this.value = i;
        }
    }

    public enum UNIT_ITEM implements Internal.EnumLite {
        enum_UNIT_ITEM_INVALID(0),
        enum_UNIT_ITEM_DISTANCE(1),
        enum_UNIT_ITEM_ELEVATION(2),
        enum_UNIT_ITEM_WEIGHT(3),
        enum_UNIT_ITEM_TEMPERATURE(4);

        public static final int enum_UNIT_ITEM_DISTANCE_VALUE = 1;
        public static final int enum_UNIT_ITEM_ELEVATION_VALUE = 2;
        public static final int enum_UNIT_ITEM_INVALID_VALUE = 0;
        public static final int enum_UNIT_ITEM_TEMPERATURE_VALUE = 4;
        public static final int enum_UNIT_ITEM_WEIGHT_VALUE = 3;
        private static final Internal.EnumLiteMap<UNIT_ITEM> internalValueMap = new Internal.EnumLiteMap<UNIT_ITEM>() { // from class: com.igpsport.blelib.bean.Config.UNIT_ITEM.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UNIT_ITEM findValueByNumber(int i) {
                return UNIT_ITEM.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UNIT_ITEM valueOf(int i) {
            return forNumber(i);
        }

        public static UNIT_ITEM forNumber(int i) {
            if (i == 0) {
                return enum_UNIT_ITEM_INVALID;
            }
            if (i == 1) {
                return enum_UNIT_ITEM_DISTANCE;
            }
            if (i == 2) {
                return enum_UNIT_ITEM_ELEVATION;
            }
            if (i == 3) {
                return enum_UNIT_ITEM_WEIGHT;
            }
            if (i != 4) {
                return null;
            }
            return enum_UNIT_ITEM_TEMPERATURE;
        }

        public static Internal.EnumLiteMap<UNIT_ITEM> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return UNIT_ITEMVerifier.INSTANCE;
        }

        private static final class UNIT_ITEMVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new UNIT_ITEMVerifier();

            private UNIT_ITEMVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return UNIT_ITEM.forNumber(i) != null;
            }
        }

        UNIT_ITEM(int i) {
            this.value = i;
        }
    }

    public enum SECTION_HR_TYPE implements Internal.EnumLite {
        SECTION_HR_TYPE_INVALID(0),
        SECTION_HR_TYPE_MAX(1),
        SECTION_HR_TYPE_LTHR(2),
        SECTION_HR_TYPE_RESERVE_HR(3);

        public static final int SECTION_HR_TYPE_INVALID_VALUE = 0;
        public static final int SECTION_HR_TYPE_LTHR_VALUE = 2;
        public static final int SECTION_HR_TYPE_MAX_VALUE = 1;
        public static final int SECTION_HR_TYPE_RESERVE_HR_VALUE = 3;
        private static final Internal.EnumLiteMap<SECTION_HR_TYPE> internalValueMap = new Internal.EnumLiteMap<SECTION_HR_TYPE>() { // from class: com.igpsport.blelib.bean.Config.SECTION_HR_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SECTION_HR_TYPE findValueByNumber(int i) {
                return SECTION_HR_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SECTION_HR_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static SECTION_HR_TYPE forNumber(int i) {
            if (i == 0) {
                return SECTION_HR_TYPE_INVALID;
            }
            if (i == 1) {
                return SECTION_HR_TYPE_MAX;
            }
            if (i == 2) {
                return SECTION_HR_TYPE_LTHR;
            }
            if (i != 3) {
                return null;
            }
            return SECTION_HR_TYPE_RESERVE_HR;
        }

        public static Internal.EnumLiteMap<SECTION_HR_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SECTION_HR_TYPEVerifier.INSTANCE;
        }

        private static final class SECTION_HR_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SECTION_HR_TYPEVerifier();

            private SECTION_HR_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return SECTION_HR_TYPE.forNumber(i) != null;
            }
        }

        SECTION_HR_TYPE(int i) {
            this.value = i;
        }
    }

    public enum PAGE_TYPE implements Internal.EnumLite {
        enum_PAGE_TYPE_INVALID(0),
        enum_PAGE_TYPE_DATA(1),
        enum_PAGE_TYPE_ELEVATION(2),
        enum_PAGE_TYPE_MAP(3),
        enum_PAGE_TYPE_AREA(4),
        enum_PAGE_TYPE_TRAINING_COURSE(5),
        enum_PAGE_TYPE_TRAINING_FEC(6),
        enum_PAGE_TYPE_COMPASS(7),
        enum_PAGE_TYPE_LAP(8),
        enum_PAGE_TYPE_ROADBOOK(9),
        enum_PAGE_TYPE_CLIMB(10),
        enum_PAGE_TYPE_AIPARTNER(11),
        enum_PAGE_TYPE_SEGMENT(12),
        enum_PAGE_TYPE_FITNESS(13),
        enum_PAGE_TYPE_CLIMB_MAP(14),
        enum_PAGE_TYPE_GROUP_RIDE_INFOR(15),
        enum_PAGE_TYPE_MUSIC(16),
        enum_PAGE_TYPE_CAMERA(17);

        public static final int enum_PAGE_TYPE_AIPARTNER_VALUE = 11;
        public static final int enum_PAGE_TYPE_AREA_VALUE = 4;
        public static final int enum_PAGE_TYPE_CAMERA_VALUE = 17;
        public static final int enum_PAGE_TYPE_CLIMB_MAP_VALUE = 14;
        public static final int enum_PAGE_TYPE_CLIMB_VALUE = 10;
        public static final int enum_PAGE_TYPE_COMPASS_VALUE = 7;
        public static final int enum_PAGE_TYPE_DATA_VALUE = 1;
        public static final int enum_PAGE_TYPE_ELEVATION_VALUE = 2;
        public static final int enum_PAGE_TYPE_FITNESS_VALUE = 13;
        public static final int enum_PAGE_TYPE_GROUP_RIDE_INFOR_VALUE = 15;
        public static final int enum_PAGE_TYPE_INVALID_VALUE = 0;
        public static final int enum_PAGE_TYPE_LAP_VALUE = 8;
        public static final int enum_PAGE_TYPE_MAP_VALUE = 3;
        public static final int enum_PAGE_TYPE_MUSIC_VALUE = 16;
        public static final int enum_PAGE_TYPE_ROADBOOK_VALUE = 9;
        public static final int enum_PAGE_TYPE_SEGMENT_VALUE = 12;
        public static final int enum_PAGE_TYPE_TRAINING_COURSE_VALUE = 5;
        public static final int enum_PAGE_TYPE_TRAINING_FEC_VALUE = 6;
        private static final Internal.EnumLiteMap<PAGE_TYPE> internalValueMap = new Internal.EnumLiteMap<PAGE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.PAGE_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PAGE_TYPE findValueByNumber(int i) {
                return PAGE_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PAGE_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static PAGE_TYPE forNumber(int i) {
            switch (i) {
                case 0:
                    return enum_PAGE_TYPE_INVALID;
                case 1:
                    return enum_PAGE_TYPE_DATA;
                case 2:
                    return enum_PAGE_TYPE_ELEVATION;
                case 3:
                    return enum_PAGE_TYPE_MAP;
                case 4:
                    return enum_PAGE_TYPE_AREA;
                case 5:
                    return enum_PAGE_TYPE_TRAINING_COURSE;
                case 6:
                    return enum_PAGE_TYPE_TRAINING_FEC;
                case 7:
                    return enum_PAGE_TYPE_COMPASS;
                case 8:
                    return enum_PAGE_TYPE_LAP;
                case 9:
                    return enum_PAGE_TYPE_ROADBOOK;
                case 10:
                    return enum_PAGE_TYPE_CLIMB;
                case 11:
                    return enum_PAGE_TYPE_AIPARTNER;
                case 12:
                    return enum_PAGE_TYPE_SEGMENT;
                case 13:
                    return enum_PAGE_TYPE_FITNESS;
                case 14:
                    return enum_PAGE_TYPE_CLIMB_MAP;
                case 15:
                    return enum_PAGE_TYPE_GROUP_RIDE_INFOR;
                case 16:
                    return enum_PAGE_TYPE_MUSIC;
                case 17:
                    return enum_PAGE_TYPE_CAMERA;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<PAGE_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PAGE_TYPEVerifier.INSTANCE;
        }

        private static final class PAGE_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new PAGE_TYPEVerifier();

            private PAGE_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return PAGE_TYPE.forNumber(i) != null;
            }
        }

        PAGE_TYPE(int i) {
            this.value = i;
        }
    }

    public enum LCD_TYPE implements Internal.EnumLite {
        INVALID(0),
        PIXEL_LCD(1),
        SECTION_LCD(2);

        public static final int INVALID_VALUE = 0;
        public static final int PIXEL_LCD_VALUE = 1;
        public static final int SECTION_LCD_VALUE = 2;
        private static final Internal.EnumLiteMap<LCD_TYPE> internalValueMap = new Internal.EnumLiteMap<LCD_TYPE>() { // from class: com.igpsport.blelib.bean.Config.LCD_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LCD_TYPE findValueByNumber(int i) {
                return LCD_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LCD_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static LCD_TYPE forNumber(int i) {
            if (i == 0) {
                return INVALID;
            }
            if (i == 1) {
                return PIXEL_LCD;
            }
            if (i != 2) {
                return null;
            }
            return SECTION_LCD;
        }

        public static Internal.EnumLiteMap<LCD_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LCD_TYPEVerifier.INSTANCE;
        }

        private static final class LCD_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new LCD_TYPEVerifier();

            private LCD_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return LCD_TYPE.forNumber(i) != null;
            }
        }

        LCD_TYPE(int i) {
            this.value = i;
        }
    }

    public enum LANGUAGE_TYPE implements Internal.EnumLite {
        enum_LANGUAGE_TYPE_INVALID(0),
        enum_LANGUAGE_TYPE_ENGLISH(1),
        enum_LANGUAGE_TYPE_SPANISH(2),
        enum_LANGUAGE_TYPE_FRENCH(3),
        enum_LANGUAGE_TYPE_GERMAN(4),
        enum_LANGUAGE_TYPE_JAPANESE(5),
        enum_LANGUAGE_TYPE_ITALIAN(6),
        enum_LANGUAGE_TYPE_PORTUGUESE(7),
        enum_LANGUAGE_TYPE_KOREAN(8),
        enum_LANGUAGE_TYPE_CHINESE(9),
        enum_LANGUAGE_TYPE_TAIWANESE(10),
        enum_LANGUAGE_TYPE_POLISH(11),
        enum_LANGUAGE_TYPE_CROATIAN(12),
        enum_LANGUAGE_TYPE_CZECH(13),
        enum_LANGUAGE_TYPE_DANISH(14),
        enum_LANGUAGE_TYPE_DUTCH(15),
        enum_LANGUAGE_TYPE_FINNISH(16),
        enum_LANGUAGE_TYPE_GREEK(17),
        enum_LANGUAGE_TYPE_HUNGARIAN(18),
        enum_LANGUAGE_TYPE_NORWEGIAN(19),
        enum_LANGUAGE_TYPE_SLOVAKIAN(20),
        enum_LANGUAGE_TYPE_SLOVENIAN(21),
        enum_LANGUAGE_TYPE_SWEDISH(22),
        enum_LANGUAGE_TYPE_RUSSIAN(23),
        enum_LANGUAGE_TYPE_TURKISH(24),
        enum_LANGUAGE_TYPE_LATVIAN(25),
        enum_LANGUAGE_TYPE_UKRAINIAN(26),
        enum_LANGUAGE_TYPE_ARABIC(27),
        enum_LANGUAGE_TYPE_FARSI(28),
        enum_LANGUAGE_TYPE_BULGARIAN(29),
        enum_LANGUAGE_TYPE_ROMANIAN(30),
        enum_LANGUAGE_TYPE_THAI(31),
        enum_LANGUAGE_TYPE_HEBREW(32),
        enum_LANGUAGE_TYPE_BRAZILIAN_PORTUGUESE(33),
        enum_LANGUAGE_TYPE_INDONESIAN(34),
        enum_LANGUAGE_TYPE_MALAYSIAN(35),
        enum_LANGUAGE_TYPE_VIETNAMESE(36),
        enum_LANGUAGE_TYPE_BURMESE(37),
        enum_LANGUAGE_TYPE_MONGOLIAN(38),
        enum_LANGUAGE_TYPE_ALL(100),
        enum_LANGUAGE_TYPE_CUSTOM(254);

        public static final int enum_LANGUAGE_TYPE_ALL_VALUE = 100;
        public static final int enum_LANGUAGE_TYPE_ARABIC_VALUE = 27;
        public static final int enum_LANGUAGE_TYPE_BRAZILIAN_PORTUGUESE_VALUE = 33;
        public static final int enum_LANGUAGE_TYPE_BULGARIAN_VALUE = 29;
        public static final int enum_LANGUAGE_TYPE_BURMESE_VALUE = 37;
        public static final int enum_LANGUAGE_TYPE_CHINESE_VALUE = 9;
        public static final int enum_LANGUAGE_TYPE_CROATIAN_VALUE = 12;
        public static final int enum_LANGUAGE_TYPE_CUSTOM_VALUE = 254;
        public static final int enum_LANGUAGE_TYPE_CZECH_VALUE = 13;
        public static final int enum_LANGUAGE_TYPE_DANISH_VALUE = 14;
        public static final int enum_LANGUAGE_TYPE_DUTCH_VALUE = 15;
        public static final int enum_LANGUAGE_TYPE_ENGLISH_VALUE = 1;
        public static final int enum_LANGUAGE_TYPE_FARSI_VALUE = 28;
        public static final int enum_LANGUAGE_TYPE_FINNISH_VALUE = 16;
        public static final int enum_LANGUAGE_TYPE_FRENCH_VALUE = 3;
        public static final int enum_LANGUAGE_TYPE_GERMAN_VALUE = 4;
        public static final int enum_LANGUAGE_TYPE_GREEK_VALUE = 17;
        public static final int enum_LANGUAGE_TYPE_HEBREW_VALUE = 32;
        public static final int enum_LANGUAGE_TYPE_HUNGARIAN_VALUE = 18;
        public static final int enum_LANGUAGE_TYPE_INDONESIAN_VALUE = 34;
        public static final int enum_LANGUAGE_TYPE_INVALID_VALUE = 0;
        public static final int enum_LANGUAGE_TYPE_ITALIAN_VALUE = 6;
        public static final int enum_LANGUAGE_TYPE_JAPANESE_VALUE = 5;
        public static final int enum_LANGUAGE_TYPE_KOREAN_VALUE = 8;
        public static final int enum_LANGUAGE_TYPE_LATVIAN_VALUE = 25;
        public static final int enum_LANGUAGE_TYPE_MALAYSIAN_VALUE = 35;
        public static final int enum_LANGUAGE_TYPE_MONGOLIAN_VALUE = 38;
        public static final int enum_LANGUAGE_TYPE_NORWEGIAN_VALUE = 19;
        public static final int enum_LANGUAGE_TYPE_POLISH_VALUE = 11;
        public static final int enum_LANGUAGE_TYPE_PORTUGUESE_VALUE = 7;
        public static final int enum_LANGUAGE_TYPE_ROMANIAN_VALUE = 30;
        public static final int enum_LANGUAGE_TYPE_RUSSIAN_VALUE = 23;
        public static final int enum_LANGUAGE_TYPE_SLOVAKIAN_VALUE = 20;
        public static final int enum_LANGUAGE_TYPE_SLOVENIAN_VALUE = 21;
        public static final int enum_LANGUAGE_TYPE_SPANISH_VALUE = 2;
        public static final int enum_LANGUAGE_TYPE_SWEDISH_VALUE = 22;
        public static final int enum_LANGUAGE_TYPE_TAIWANESE_VALUE = 10;
        public static final int enum_LANGUAGE_TYPE_THAI_VALUE = 31;
        public static final int enum_LANGUAGE_TYPE_TURKISH_VALUE = 24;
        public static final int enum_LANGUAGE_TYPE_UKRAINIAN_VALUE = 26;
        public static final int enum_LANGUAGE_TYPE_VIETNAMESE_VALUE = 36;
        private static final Internal.EnumLiteMap<LANGUAGE_TYPE> internalValueMap = new Internal.EnumLiteMap<LANGUAGE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.LANGUAGE_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LANGUAGE_TYPE findValueByNumber(int i) {
                return LANGUAGE_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LANGUAGE_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static LANGUAGE_TYPE forNumber(int i) {
            if (i == 100) {
                return enum_LANGUAGE_TYPE_ALL;
            }
            if (i != 254) {
                switch (i) {
                    case 0:
                        return enum_LANGUAGE_TYPE_INVALID;
                    case 1:
                        return enum_LANGUAGE_TYPE_ENGLISH;
                    case 2:
                        return enum_LANGUAGE_TYPE_SPANISH;
                    case 3:
                        return enum_LANGUAGE_TYPE_FRENCH;
                    case 4:
                        return enum_LANGUAGE_TYPE_GERMAN;
                    case 5:
                        return enum_LANGUAGE_TYPE_JAPANESE;
                    case 6:
                        return enum_LANGUAGE_TYPE_ITALIAN;
                    case 7:
                        return enum_LANGUAGE_TYPE_PORTUGUESE;
                    case 8:
                        return enum_LANGUAGE_TYPE_KOREAN;
                    case 9:
                        return enum_LANGUAGE_TYPE_CHINESE;
                    case 10:
                        return enum_LANGUAGE_TYPE_TAIWANESE;
                    case 11:
                        return enum_LANGUAGE_TYPE_POLISH;
                    case 12:
                        return enum_LANGUAGE_TYPE_CROATIAN;
                    case 13:
                        return enum_LANGUAGE_TYPE_CZECH;
                    case 14:
                        return enum_LANGUAGE_TYPE_DANISH;
                    case 15:
                        return enum_LANGUAGE_TYPE_DUTCH;
                    case 16:
                        return enum_LANGUAGE_TYPE_FINNISH;
                    case 17:
                        return enum_LANGUAGE_TYPE_GREEK;
                    case 18:
                        return enum_LANGUAGE_TYPE_HUNGARIAN;
                    case 19:
                        return enum_LANGUAGE_TYPE_NORWEGIAN;
                    case 20:
                        return enum_LANGUAGE_TYPE_SLOVAKIAN;
                    case 21:
                        return enum_LANGUAGE_TYPE_SLOVENIAN;
                    case 22:
                        return enum_LANGUAGE_TYPE_SWEDISH;
                    case 23:
                        return enum_LANGUAGE_TYPE_RUSSIAN;
                    case 24:
                        return enum_LANGUAGE_TYPE_TURKISH;
                    case 25:
                        return enum_LANGUAGE_TYPE_LATVIAN;
                    case 26:
                        return enum_LANGUAGE_TYPE_UKRAINIAN;
                    case 27:
                        return enum_LANGUAGE_TYPE_ARABIC;
                    case 28:
                        return enum_LANGUAGE_TYPE_FARSI;
                    case 29:
                        return enum_LANGUAGE_TYPE_BULGARIAN;
                    case 30:
                        return enum_LANGUAGE_TYPE_ROMANIAN;
                    case 31:
                        return enum_LANGUAGE_TYPE_THAI;
                    case 32:
                        return enum_LANGUAGE_TYPE_HEBREW;
                    case 33:
                        return enum_LANGUAGE_TYPE_BRAZILIAN_PORTUGUESE;
                    case 34:
                        return enum_LANGUAGE_TYPE_INDONESIAN;
                    case 35:
                        return enum_LANGUAGE_TYPE_MALAYSIAN;
                    case 36:
                        return enum_LANGUAGE_TYPE_VIETNAMESE;
                    case 37:
                        return enum_LANGUAGE_TYPE_BURMESE;
                    case 38:
                        return enum_LANGUAGE_TYPE_MONGOLIAN;
                    default:
                        return null;
                }
            }
            return enum_LANGUAGE_TYPE_CUSTOM;
        }

        public static Internal.EnumLiteMap<LANGUAGE_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LANGUAGE_TYPEVerifier.INSTANCE;
        }

        private static final class LANGUAGE_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new LANGUAGE_TYPEVerifier();

            private LANGUAGE_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return LANGUAGE_TYPE.forNumber(i) != null;
            }
        }

        LANGUAGE_TYPE(int i) {
            this.value = i;
        }
    }

    public enum DATA_TYPE implements Internal.EnumLite {
        enum_DATA_TYPE_INVALID(0),
        enum_DATA_TYPE_PWR_WITH_ZERO(1),
        enum_DATA_TYPE_CAD_WITH_ZERO(2);

        public static final int enum_DATA_TYPE_CAD_WITH_ZERO_VALUE = 2;
        public static final int enum_DATA_TYPE_INVALID_VALUE = 0;
        public static final int enum_DATA_TYPE_PWR_WITH_ZERO_VALUE = 1;
        private static final Internal.EnumLiteMap<DATA_TYPE> internalValueMap = new Internal.EnumLiteMap<DATA_TYPE>() { // from class: com.igpsport.blelib.bean.Config.DATA_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DATA_TYPE findValueByNumber(int i) {
                return DATA_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DATA_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static DATA_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_DATA_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_DATA_TYPE_PWR_WITH_ZERO;
            }
            if (i != 2) {
                return null;
            }
            return enum_DATA_TYPE_CAD_WITH_ZERO;
        }

        public static Internal.EnumLiteMap<DATA_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DATA_TYPEVerifier.INSTANCE;
        }

        private static final class DATA_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new DATA_TYPEVerifier();

            private DATA_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return DATA_TYPE.forNumber(i) != null;
            }
        }

        DATA_TYPE(int i) {
            this.value = i;
        }
    }

    public enum TIME_SYSTEM_TYPE implements Internal.EnumLite {
        enum_TIME_SYSTEM_TYPE_INVALID(0),
        enum_TIME_SYSTEM_TYPE_12HOUR(1),
        enum_TIME_SYSTEM_TYPE_24HOUR(2);

        public static final int enum_TIME_SYSTEM_TYPE_12HOUR_VALUE = 1;
        public static final int enum_TIME_SYSTEM_TYPE_24HOUR_VALUE = 2;
        public static final int enum_TIME_SYSTEM_TYPE_INVALID_VALUE = 0;
        private static final Internal.EnumLiteMap<TIME_SYSTEM_TYPE> internalValueMap = new Internal.EnumLiteMap<TIME_SYSTEM_TYPE>() { // from class: com.igpsport.blelib.bean.Config.TIME_SYSTEM_TYPE.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TIME_SYSTEM_TYPE findValueByNumber(int i) {
                return TIME_SYSTEM_TYPE.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TIME_SYSTEM_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static TIME_SYSTEM_TYPE forNumber(int i) {
            if (i == 0) {
                return enum_TIME_SYSTEM_TYPE_INVALID;
            }
            if (i == 1) {
                return enum_TIME_SYSTEM_TYPE_12HOUR;
            }
            if (i != 2) {
                return null;
            }
            return enum_TIME_SYSTEM_TYPE_24HOUR;
        }

        public static Internal.EnumLiteMap<TIME_SYSTEM_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TIME_SYSTEM_TYPEVerifier.INSTANCE;
        }

        private static final class TIME_SYSTEM_TYPEVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new TIME_SYSTEM_TYPEVerifier();

            private TIME_SYSTEM_TYPEVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return TIME_SYSTEM_TYPE.forNumber(i) != null;
            }
        }

        TIME_SYSTEM_TYPE(int i) {
            this.value = i;
        }
    }

    public static final class sound_set_msg extends GeneratedMessageLite<sound_set_msg, Builder> implements sound_set_msgOrBuilder {
        private static final sound_set_msg DEFAULT_INSTANCE;
        private static volatile Parser<sound_set_msg> PARSER = null;
        public static final int SOUND_SCENE_FIELD_NUMBER = 2;
        public static final int SOUND_TYPE_FIELD_NUMBER = 1;
        public static final int STATUS_FIELD_NUMBER = 3;
        private int bitField0_;
        private int soundScene_;
        private int soundType_;
        private int status_;

        private sound_set_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public boolean hasSoundType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public int getSoundType() {
            return this.soundType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSoundType(int i) {
            this.bitField0_ |= 1;
            this.soundType_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSoundType() {
            this.bitField0_ &= -2;
            this.soundType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public boolean hasSoundScene() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public SOUND_SCENE_TYPE getSoundScene() {
            SOUND_SCENE_TYPE sound_scene_typeForNumber = SOUND_SCENE_TYPE.forNumber(this.soundScene_);
            return sound_scene_typeForNumber == null ? SOUND_SCENE_TYPE.enum_SOUND_SCENE_TYPE_INVALID : sound_scene_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSoundScene(SOUND_SCENE_TYPE sound_scene_type) {
            this.soundScene_ = sound_scene_type.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSoundScene() {
            this.bitField0_ &= -3;
            this.soundScene_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 4;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -5;
            this.status_ = 0;
        }

        public static sound_set_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static sound_set_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static sound_set_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static sound_set_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static sound_set_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static sound_set_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static sound_set_msg parseFrom(InputStream inputStream) throws IOException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static sound_set_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static sound_set_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (sound_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static sound_set_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (sound_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static sound_set_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static sound_set_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (sound_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(sound_set_msg sound_set_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(sound_set_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<sound_set_msg, Builder> implements sound_set_msgOrBuilder {
            private Builder() {
                super(sound_set_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public boolean hasSoundType() {
                return ((sound_set_msg) this.instance).hasSoundType();
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public int getSoundType() {
                return ((sound_set_msg) this.instance).getSoundType();
            }

            public Builder setSoundType(int i) {
                copyOnWrite();
                ((sound_set_msg) this.instance).setSoundType(i);
                return this;
            }

            public Builder clearSoundType() {
                copyOnWrite();
                ((sound_set_msg) this.instance).clearSoundType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public boolean hasSoundScene() {
                return ((sound_set_msg) this.instance).hasSoundScene();
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public SOUND_SCENE_TYPE getSoundScene() {
                return ((sound_set_msg) this.instance).getSoundScene();
            }

            public Builder setSoundScene(SOUND_SCENE_TYPE sound_scene_type) {
                copyOnWrite();
                ((sound_set_msg) this.instance).setSoundScene(sound_scene_type);
                return this;
            }

            public Builder clearSoundScene() {
                copyOnWrite();
                ((sound_set_msg) this.instance).clearSoundScene();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public boolean hasStatus() {
                return ((sound_set_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.sound_set_msgOrBuilder
            public int getStatus() {
                return ((sound_set_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((sound_set_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((sound_set_msg) this.instance).clearStatus();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new sound_set_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဌ\u0001\u0003ဋ\u0002", new Object[]{"bitField0_", "soundType_", "soundScene_", SOUND_SCENE_TYPE.internalGetVerifier(), "status_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<sound_set_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (sound_set_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            sound_set_msg sound_set_msgVar = new sound_set_msg();
            DEFAULT_INSTANCE = sound_set_msgVar;
            GeneratedMessageLite.registerDefaultInstance(sound_set_msg.class, sound_set_msgVar);
        }

        public static sound_set_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<sound_set_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.igpsport.blelib.bean.Config$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class key_set_msg extends GeneratedMessageLite<key_set_msg, Builder> implements key_set_msgOrBuilder {
        private static final key_set_msg DEFAULT_INSTANCE;
        public static final int KEY_FUNCTION_TYPE_FIELD_NUMBER = 2;
        public static final int KEY_SUPPORT_FIELD_NUMBER = 3;
        public static final int KEY_TYPE_FIELD_NUMBER = 1;
        private static volatile Parser<key_set_msg> PARSER;
        private static final Internal.ListAdapter.Converter<Integer, KEY_FUNCTION_TYPE> keySupport_converter_ = new Internal.ListAdapter.Converter<Integer, KEY_FUNCTION_TYPE>() { // from class: com.igpsport.blelib.bean.Config.key_set_msg.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public KEY_FUNCTION_TYPE convert(Integer num) {
                KEY_FUNCTION_TYPE key_function_typeForNumber = KEY_FUNCTION_TYPE.forNumber(num.intValue());
                return key_function_typeForNumber == null ? KEY_FUNCTION_TYPE.enum_KEY_FUNCTION_TYPE_INVALID : key_function_typeForNumber;
            }
        };
        private int bitField0_;
        private int keyFunctionType_;
        private Internal.IntList keySupport_ = emptyIntList();
        private int keyType_;

        private key_set_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public boolean hasKeyType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public KEY_TYPE getKeyType() {
            KEY_TYPE key_typeForNumber = KEY_TYPE.forNumber(this.keyType_);
            return key_typeForNumber == null ? KEY_TYPE.enum_KEY_TYPE_INVALID : key_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyType(KEY_TYPE key_type) {
            this.keyType_ = key_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeyType() {
            this.bitField0_ &= -2;
            this.keyType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public boolean hasKeyFunctionType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public KEY_FUNCTION_TYPE getKeyFunctionType() {
            KEY_FUNCTION_TYPE key_function_typeForNumber = KEY_FUNCTION_TYPE.forNumber(this.keyFunctionType_);
            return key_function_typeForNumber == null ? KEY_FUNCTION_TYPE.enum_KEY_FUNCTION_TYPE_INVALID : key_function_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyFunctionType(KEY_FUNCTION_TYPE key_function_type) {
            this.keyFunctionType_ = key_function_type.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeyFunctionType() {
            this.bitField0_ &= -3;
            this.keyFunctionType_ = 0;
        }

        static {
            key_set_msg key_set_msgVar = new key_set_msg();
            DEFAULT_INSTANCE = key_set_msgVar;
            GeneratedMessageLite.registerDefaultInstance(key_set_msg.class, key_set_msgVar);
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public List<KEY_FUNCTION_TYPE> getKeySupportList() {
            return new Internal.ListAdapter(this.keySupport_, keySupport_converter_);
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public int getKeySupportCount() {
            return this.keySupport_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
        public KEY_FUNCTION_TYPE getKeySupport(int i) {
            return keySupport_converter_.convert(Integer.valueOf(this.keySupport_.getInt(i)));
        }

        private void ensureKeySupportIsMutable() {
            Internal.IntList intList = this.keySupport_;
            if (intList.isModifiable()) {
                return;
            }
            this.keySupport_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeySupport(int i, KEY_FUNCTION_TYPE key_function_type) {
            key_function_type.getClass();
            ensureKeySupportIsMutable();
            this.keySupport_.setInt(i, key_function_type.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addKeySupport(KEY_FUNCTION_TYPE key_function_type) {
            key_function_type.getClass();
            ensureKeySupportIsMutable();
            this.keySupport_.addInt(key_function_type.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllKeySupport(Iterable<? extends KEY_FUNCTION_TYPE> iterable) {
            ensureKeySupportIsMutable();
            Iterator<? extends KEY_FUNCTION_TYPE> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.keySupport_.addInt(it2.next().getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeySupport() {
            this.keySupport_ = emptyIntList();
        }

        public static key_set_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static key_set_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static key_set_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static key_set_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static key_set_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static key_set_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static key_set_msg parseFrom(InputStream inputStream) throws IOException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static key_set_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static key_set_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (key_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static key_set_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (key_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static key_set_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static key_set_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (key_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(key_set_msg key_set_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(key_set_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<key_set_msg, Builder> implements key_set_msgOrBuilder {
            private Builder() {
                super(key_set_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public boolean hasKeyType() {
                return ((key_set_msg) this.instance).hasKeyType();
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public KEY_TYPE getKeyType() {
                return ((key_set_msg) this.instance).getKeyType();
            }

            public Builder setKeyType(KEY_TYPE key_type) {
                copyOnWrite();
                ((key_set_msg) this.instance).setKeyType(key_type);
                return this;
            }

            public Builder clearKeyType() {
                copyOnWrite();
                ((key_set_msg) this.instance).clearKeyType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public boolean hasKeyFunctionType() {
                return ((key_set_msg) this.instance).hasKeyFunctionType();
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public KEY_FUNCTION_TYPE getKeyFunctionType() {
                return ((key_set_msg) this.instance).getKeyFunctionType();
            }

            public Builder setKeyFunctionType(KEY_FUNCTION_TYPE key_function_type) {
                copyOnWrite();
                ((key_set_msg) this.instance).setKeyFunctionType(key_function_type);
                return this;
            }

            public Builder clearKeyFunctionType() {
                copyOnWrite();
                ((key_set_msg) this.instance).clearKeyFunctionType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public List<KEY_FUNCTION_TYPE> getKeySupportList() {
                return ((key_set_msg) this.instance).getKeySupportList();
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public int getKeySupportCount() {
                return ((key_set_msg) this.instance).getKeySupportCount();
            }

            @Override // com.igpsport.blelib.bean.Config.key_set_msgOrBuilder
            public KEY_FUNCTION_TYPE getKeySupport(int i) {
                return ((key_set_msg) this.instance).getKeySupport(i);
            }

            public Builder setKeySupport(int i, KEY_FUNCTION_TYPE key_function_type) {
                copyOnWrite();
                ((key_set_msg) this.instance).setKeySupport(i, key_function_type);
                return this;
            }

            public Builder addKeySupport(KEY_FUNCTION_TYPE key_function_type) {
                copyOnWrite();
                ((key_set_msg) this.instance).addKeySupport(key_function_type);
                return this;
            }

            public Builder addAllKeySupport(Iterable<? extends KEY_FUNCTION_TYPE> iterable) {
                copyOnWrite();
                ((key_set_msg) this.instance).addAllKeySupport(iterable);
                return this;
            }

            public Builder clearKeySupport() {
                copyOnWrite();
                ((key_set_msg) this.instance).clearKeySupport();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new key_set_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003\u001e", new Object[]{"bitField0_", "keyType_", KEY_TYPE.internalGetVerifier(), "keyFunctionType_", KEY_FUNCTION_TYPE.internalGetVerifier(), "keySupport_", KEY_FUNCTION_TYPE.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<key_set_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (key_set_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static key_set_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<key_set_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class auto_set_msg extends GeneratedMessageLite<auto_set_msg, Builder> implements auto_set_msgOrBuilder {
        public static final int AUTO_TYPE_FIELD_NUMBER = 1;
        private static final auto_set_msg DEFAULT_INSTANCE;
        public static final int PARAM1_FIELD_NUMBER = 3;
        private static volatile Parser<auto_set_msg> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int autoType_;
        private int bitField0_;
        private int param1_;
        private int status_;

        private auto_set_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public boolean hasAutoType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public AUTO_TYPE getAutoType() {
            AUTO_TYPE auto_typeForNumber = AUTO_TYPE.forNumber(this.autoType_);
            return auto_typeForNumber == null ? AUTO_TYPE.enum_AUTO_TYPE_INVALID : auto_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoType(AUTO_TYPE auto_type) {
            this.autoType_ = auto_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoType() {
            this.bitField0_ &= -2;
            this.autoType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 2;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -3;
            this.status_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public boolean hasParam1() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
        public int getParam1() {
            return this.param1_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParam1(int i) {
            this.bitField0_ |= 4;
            this.param1_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearParam1() {
            this.bitField0_ &= -5;
            this.param1_ = 0;
        }

        public static auto_set_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static auto_set_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static auto_set_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static auto_set_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static auto_set_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static auto_set_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static auto_set_msg parseFrom(InputStream inputStream) throws IOException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static auto_set_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static auto_set_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (auto_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static auto_set_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (auto_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static auto_set_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static auto_set_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (auto_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(auto_set_msg auto_set_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(auto_set_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<auto_set_msg, Builder> implements auto_set_msgOrBuilder {
            private Builder() {
                super(auto_set_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public boolean hasAutoType() {
                return ((auto_set_msg) this.instance).hasAutoType();
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public AUTO_TYPE getAutoType() {
                return ((auto_set_msg) this.instance).getAutoType();
            }

            public Builder setAutoType(AUTO_TYPE auto_type) {
                copyOnWrite();
                ((auto_set_msg) this.instance).setAutoType(auto_type);
                return this;
            }

            public Builder clearAutoType() {
                copyOnWrite();
                ((auto_set_msg) this.instance).clearAutoType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public boolean hasStatus() {
                return ((auto_set_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public int getStatus() {
                return ((auto_set_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((auto_set_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((auto_set_msg) this.instance).clearStatus();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public boolean hasParam1() {
                return ((auto_set_msg) this.instance).hasParam1();
            }

            @Override // com.igpsport.blelib.bean.Config.auto_set_msgOrBuilder
            public int getParam1() {
                return ((auto_set_msg) this.instance).getParam1();
            }

            public Builder setParam1(int i) {
                copyOnWrite();
                ((auto_set_msg) this.instance).setParam1(i);
                return this;
            }

            public Builder clearParam1() {
                copyOnWrite();
                ((auto_set_msg) this.instance).clearParam1();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new auto_set_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဋ\u0001\u0003ဋ\u0002", new Object[]{"bitField0_", "autoType_", AUTO_TYPE.internalGetVerifier(), "status_", "param1_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<auto_set_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (auto_set_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            auto_set_msg auto_set_msgVar = new auto_set_msg();
            DEFAULT_INSTANCE = auto_set_msgVar;
            GeneratedMessageLite.registerDefaultInstance(auto_set_msg.class, auto_set_msgVar);
        }

        public static auto_set_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<auto_set_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class alarm_select_info extends GeneratedMessageLite<alarm_select_info, Builder> implements alarm_select_infoOrBuilder {
        private static final alarm_select_info DEFAULT_INSTANCE;
        private static volatile Parser<alarm_select_info> PARSER = null;
        public static final int SELECT_TYPE_FIELD_NUMBER = 1;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int selectType_;
        private int value_;

        private alarm_select_info() {
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
        public boolean hasSelectType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
        public ALARM_SELECT getSelectType() {
            ALARM_SELECT alarm_selectForNumber = ALARM_SELECT.forNumber(this.selectType_);
            return alarm_selectForNumber == null ? ALARM_SELECT.ALARM_SELECT_INVALID : alarm_selectForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectType(ALARM_SELECT alarm_select) {
            this.selectType_ = alarm_select.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectType() {
            this.bitField0_ &= -2;
            this.selectType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
        public int getValue() {
            return this.value_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(int i) {
            this.bitField0_ |= 2;
            this.value_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = 0;
        }

        public static alarm_select_info parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static alarm_select_info parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static alarm_select_info parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static alarm_select_info parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static alarm_select_info parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static alarm_select_info parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static alarm_select_info parseFrom(InputStream inputStream) throws IOException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_select_info parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_select_info parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (alarm_select_info) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_select_info parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_select_info) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_select_info parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static alarm_select_info parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_select_info) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(alarm_select_info alarm_select_infoVar) {
            return DEFAULT_INSTANCE.createBuilder(alarm_select_infoVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<alarm_select_info, Builder> implements alarm_select_infoOrBuilder {
            private Builder() {
                super(alarm_select_info.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
            public boolean hasSelectType() {
                return ((alarm_select_info) this.instance).hasSelectType();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
            public ALARM_SELECT getSelectType() {
                return ((alarm_select_info) this.instance).getSelectType();
            }

            public Builder setSelectType(ALARM_SELECT alarm_select) {
                copyOnWrite();
                ((alarm_select_info) this.instance).setSelectType(alarm_select);
                return this;
            }

            public Builder clearSelectType() {
                copyOnWrite();
                ((alarm_select_info) this.instance).clearSelectType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
            public boolean hasValue() {
                return ((alarm_select_info) this.instance).hasValue();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_select_infoOrBuilder
            public int getValue() {
                return ((alarm_select_info) this.instance).getValue();
            }

            public Builder setValue(int i) {
                copyOnWrite();
                ((alarm_select_info) this.instance).setValue(i);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((alarm_select_info) this.instance).clearValue();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new alarm_select_info();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "selectType_", ALARM_SELECT.internalGetVerifier(), "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<alarm_select_info> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (alarm_select_info.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            alarm_select_info alarm_select_infoVar = new alarm_select_info();
            DEFAULT_INSTANCE = alarm_select_infoVar;
            GeneratedMessageLite.registerDefaultInstance(alarm_select_info.class, alarm_select_infoVar);
        }

        public static alarm_select_info getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<alarm_select_info> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class alarm_params_set_msg extends GeneratedMessageLite<alarm_params_set_msg, Builder> implements alarm_params_set_msgOrBuilder {
        public static final int ALARM_TYPE_FIELD_NUMBER = 1;
        private static final alarm_params_set_msg DEFAULT_INSTANCE;
        private static volatile Parser<alarm_params_set_msg> PARSER = null;
        public static final int SELECT_INFO_FIELD_NUMBER = 6;
        public static final int SELECT_TYPE_FIELD_NUMBER = 5;
        public static final int STATUS_FIELD_NUMBER = 4;
        public static final int VALUE_MAX_FIELD_NUMBER = 2;
        public static final int VALUE_MIN_FIELD_NUMBER = 3;
        private int alarmType_;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private Internal.ProtobufList<alarm_select_info> selectInfo_ = emptyProtobufList();
        private int selectType_;
        private int status_;
        private int valueMax_;
        private int valueMin_;

        private alarm_params_set_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public boolean hasAlarmType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public ALARM_TYPE getAlarmType() {
            ALARM_TYPE alarm_typeForNumber = ALARM_TYPE.forNumber(this.alarmType_);
            return alarm_typeForNumber == null ? ALARM_TYPE.ALARM_TYPE_INVALID : alarm_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlarmType(ALARM_TYPE alarm_type) {
            this.alarmType_ = alarm_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlarmType() {
            this.bitField0_ &= -2;
            this.alarmType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public boolean hasValueMax() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public int getValueMax() {
            return this.valueMax_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValueMax(int i) {
            this.bitField0_ |= 2;
            this.valueMax_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValueMax() {
            this.bitField0_ &= -3;
            this.valueMax_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public boolean hasValueMin() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public int getValueMin() {
            return this.valueMin_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValueMin(int i) {
            this.bitField0_ |= 4;
            this.valueMin_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValueMin() {
            this.bitField0_ &= -5;
            this.valueMin_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 8;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -9;
            this.status_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public boolean hasSelectType() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public ALARM_SELECT getSelectType() {
            ALARM_SELECT alarm_selectForNumber = ALARM_SELECT.forNumber(this.selectType_);
            return alarm_selectForNumber == null ? ALARM_SELECT.ALARM_SELECT_INVALID : alarm_selectForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectType(ALARM_SELECT alarm_select) {
            this.selectType_ = alarm_select.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectType() {
            this.bitField0_ &= -17;
            this.selectType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public List<alarm_select_info> getSelectInfoList() {
            return this.selectInfo_;
        }

        public List<? extends alarm_select_infoOrBuilder> getSelectInfoOrBuilderList() {
            return this.selectInfo_;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public int getSelectInfoCount() {
            return this.selectInfo_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
        public alarm_select_info getSelectInfo(int i) {
            return this.selectInfo_.get(i);
        }

        public alarm_select_infoOrBuilder getSelectInfoOrBuilder(int i) {
            return this.selectInfo_.get(i);
        }

        private void ensureSelectInfoIsMutable() {
            Internal.ProtobufList<alarm_select_info> protobufList = this.selectInfo_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.selectInfo_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectInfo(int i, alarm_select_info alarm_select_infoVar) {
            alarm_select_infoVar.getClass();
            ensureSelectInfoIsMutable();
            this.selectInfo_.set(i, alarm_select_infoVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSelectInfo(alarm_select_info alarm_select_infoVar) {
            alarm_select_infoVar.getClass();
            ensureSelectInfoIsMutable();
            this.selectInfo_.add(alarm_select_infoVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSelectInfo(int i, alarm_select_info alarm_select_infoVar) {
            alarm_select_infoVar.getClass();
            ensureSelectInfoIsMutable();
            this.selectInfo_.add(i, alarm_select_infoVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSelectInfo(Iterable<? extends alarm_select_info> iterable) {
            ensureSelectInfoIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.selectInfo_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectInfo() {
            this.selectInfo_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSelectInfo(int i) {
            ensureSelectInfoIsMutable();
            this.selectInfo_.remove(i);
        }

        public static alarm_params_set_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static alarm_params_set_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static alarm_params_set_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static alarm_params_set_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static alarm_params_set_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static alarm_params_set_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static alarm_params_set_msg parseFrom(InputStream inputStream) throws IOException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_params_set_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_params_set_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (alarm_params_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_params_set_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_params_set_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_params_set_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static alarm_params_set_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_params_set_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(alarm_params_set_msg alarm_params_set_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(alarm_params_set_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<alarm_params_set_msg, Builder> implements alarm_params_set_msgOrBuilder {
            private Builder() {
                super(alarm_params_set_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public boolean hasAlarmType() {
                return ((alarm_params_set_msg) this.instance).hasAlarmType();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public ALARM_TYPE getAlarmType() {
                return ((alarm_params_set_msg) this.instance).getAlarmType();
            }

            public Builder setAlarmType(ALARM_TYPE alarm_type) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setAlarmType(alarm_type);
                return this;
            }

            public Builder clearAlarmType() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearAlarmType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public boolean hasValueMax() {
                return ((alarm_params_set_msg) this.instance).hasValueMax();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public int getValueMax() {
                return ((alarm_params_set_msg) this.instance).getValueMax();
            }

            public Builder setValueMax(int i) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setValueMax(i);
                return this;
            }

            public Builder clearValueMax() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearValueMax();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public boolean hasValueMin() {
                return ((alarm_params_set_msg) this.instance).hasValueMin();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public int getValueMin() {
                return ((alarm_params_set_msg) this.instance).getValueMin();
            }

            public Builder setValueMin(int i) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setValueMin(i);
                return this;
            }

            public Builder clearValueMin() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearValueMin();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public boolean hasStatus() {
                return ((alarm_params_set_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public int getStatus() {
                return ((alarm_params_set_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearStatus();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public boolean hasSelectType() {
                return ((alarm_params_set_msg) this.instance).hasSelectType();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public ALARM_SELECT getSelectType() {
                return ((alarm_params_set_msg) this.instance).getSelectType();
            }

            public Builder setSelectType(ALARM_SELECT alarm_select) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setSelectType(alarm_select);
                return this;
            }

            public Builder clearSelectType() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearSelectType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public List<alarm_select_info> getSelectInfoList() {
                return Collections.unmodifiableList(((alarm_params_set_msg) this.instance).getSelectInfoList());
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public int getSelectInfoCount() {
                return ((alarm_params_set_msg) this.instance).getSelectInfoCount();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_params_set_msgOrBuilder
            public alarm_select_info getSelectInfo(int i) {
                return ((alarm_params_set_msg) this.instance).getSelectInfo(i);
            }

            public Builder setSelectInfo(int i, alarm_select_info alarm_select_infoVar) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setSelectInfo(i, alarm_select_infoVar);
                return this;
            }

            public Builder setSelectInfo(int i, alarm_select_info.Builder builder) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).setSelectInfo(i, builder.build());
                return this;
            }

            public Builder addSelectInfo(alarm_select_info alarm_select_infoVar) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).addSelectInfo(alarm_select_infoVar);
                return this;
            }

            public Builder addSelectInfo(int i, alarm_select_info alarm_select_infoVar) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).addSelectInfo(i, alarm_select_infoVar);
                return this;
            }

            public Builder addSelectInfo(alarm_select_info.Builder builder) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).addSelectInfo(builder.build());
                return this;
            }

            public Builder addSelectInfo(int i, alarm_select_info.Builder builder) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).addSelectInfo(i, builder.build());
                return this;
            }

            public Builder addAllSelectInfo(Iterable<? extends alarm_select_info> iterable) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).addAllSelectInfo(iterable);
                return this;
            }

            public Builder clearSelectInfo() {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).clearSelectInfo();
                return this;
            }

            public Builder removeSelectInfo(int i) {
                copyOnWrite();
                ((alarm_params_set_msg) this.instance).removeSelectInfo(i);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new alarm_params_set_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0002\u0001ᔌ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ᔋ\u0003\u0005ဌ\u0004\u0006\u001b", new Object[]{"bitField0_", "alarmType_", ALARM_TYPE.internalGetVerifier(), "valueMax_", "valueMin_", "status_", "selectType_", ALARM_SELECT.internalGetVerifier(), "selectInfo_", alarm_select_info.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<alarm_params_set_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (alarm_params_set_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            alarm_params_set_msg alarm_params_set_msgVar = new alarm_params_set_msg();
            DEFAULT_INSTANCE = alarm_params_set_msgVar;
            GeneratedMessageLite.registerDefaultInstance(alarm_params_set_msg.class, alarm_params_set_msgVar);
        }

        public static alarm_params_set_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<alarm_params_set_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class alarm_msg extends GeneratedMessageLite<alarm_msg, Builder> implements alarm_msgOrBuilder {
        public static final int ALARM_INTERVAL_TIME_FIELD_NUMBER = 2;
        private static final alarm_msg DEFAULT_INSTANCE;
        private static volatile Parser<alarm_msg> PARSER = null;
        public static final int SET_MSG_FIELD_NUMBER = 1;
        private int alarmIntervalTime_;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private Internal.ProtobufList<alarm_params_set_msg> setMsg_ = emptyProtobufList();

        private alarm_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
        public List<alarm_params_set_msg> getSetMsgList() {
            return this.setMsg_;
        }

        public List<? extends alarm_params_set_msgOrBuilder> getSetMsgOrBuilderList() {
            return this.setMsg_;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
        public int getSetMsgCount() {
            return this.setMsg_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
        public alarm_params_set_msg getSetMsg(int i) {
            return this.setMsg_.get(i);
        }

        public alarm_params_set_msgOrBuilder getSetMsgOrBuilder(int i) {
            return this.setMsg_.get(i);
        }

        private void ensureSetMsgIsMutable() {
            Internal.ProtobufList<alarm_params_set_msg> protobufList = this.setMsg_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.setMsg_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetMsg(int i, alarm_params_set_msg alarm_params_set_msgVar) {
            alarm_params_set_msgVar.getClass();
            ensureSetMsgIsMutable();
            this.setMsg_.set(i, alarm_params_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSetMsg(alarm_params_set_msg alarm_params_set_msgVar) {
            alarm_params_set_msgVar.getClass();
            ensureSetMsgIsMutable();
            this.setMsg_.add(alarm_params_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSetMsg(int i, alarm_params_set_msg alarm_params_set_msgVar) {
            alarm_params_set_msgVar.getClass();
            ensureSetMsgIsMutable();
            this.setMsg_.add(i, alarm_params_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSetMsg(Iterable<? extends alarm_params_set_msg> iterable) {
            ensureSetMsgIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.setMsg_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetMsg() {
            this.setMsg_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSetMsg(int i) {
            ensureSetMsgIsMutable();
            this.setMsg_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
        public boolean hasAlarmIntervalTime() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
        public int getAlarmIntervalTime() {
            return this.alarmIntervalTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlarmIntervalTime(int i) {
            this.bitField0_ |= 1;
            this.alarmIntervalTime_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlarmIntervalTime() {
            this.bitField0_ &= -2;
            this.alarmIntervalTime_ = 0;
        }

        public static alarm_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static alarm_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static alarm_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static alarm_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static alarm_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static alarm_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static alarm_msg parseFrom(InputStream inputStream) throws IOException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (alarm_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static alarm_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static alarm_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static alarm_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (alarm_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(alarm_msg alarm_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(alarm_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<alarm_msg, Builder> implements alarm_msgOrBuilder {
            private Builder() {
                super(alarm_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
            public List<alarm_params_set_msg> getSetMsgList() {
                return Collections.unmodifiableList(((alarm_msg) this.instance).getSetMsgList());
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
            public int getSetMsgCount() {
                return ((alarm_msg) this.instance).getSetMsgCount();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
            public alarm_params_set_msg getSetMsg(int i) {
                return ((alarm_msg) this.instance).getSetMsg(i);
            }

            public Builder setSetMsg(int i, alarm_params_set_msg alarm_params_set_msgVar) {
                copyOnWrite();
                ((alarm_msg) this.instance).setSetMsg(i, alarm_params_set_msgVar);
                return this;
            }

            public Builder setSetMsg(int i, alarm_params_set_msg.Builder builder) {
                copyOnWrite();
                ((alarm_msg) this.instance).setSetMsg(i, builder.build());
                return this;
            }

            public Builder addSetMsg(alarm_params_set_msg alarm_params_set_msgVar) {
                copyOnWrite();
                ((alarm_msg) this.instance).addSetMsg(alarm_params_set_msgVar);
                return this;
            }

            public Builder addSetMsg(int i, alarm_params_set_msg alarm_params_set_msgVar) {
                copyOnWrite();
                ((alarm_msg) this.instance).addSetMsg(i, alarm_params_set_msgVar);
                return this;
            }

            public Builder addSetMsg(alarm_params_set_msg.Builder builder) {
                copyOnWrite();
                ((alarm_msg) this.instance).addSetMsg(builder.build());
                return this;
            }

            public Builder addSetMsg(int i, alarm_params_set_msg.Builder builder) {
                copyOnWrite();
                ((alarm_msg) this.instance).addSetMsg(i, builder.build());
                return this;
            }

            public Builder addAllSetMsg(Iterable<? extends alarm_params_set_msg> iterable) {
                copyOnWrite();
                ((alarm_msg) this.instance).addAllSetMsg(iterable);
                return this;
            }

            public Builder clearSetMsg() {
                copyOnWrite();
                ((alarm_msg) this.instance).clearSetMsg();
                return this;
            }

            public Builder removeSetMsg(int i) {
                copyOnWrite();
                ((alarm_msg) this.instance).removeSetMsg(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
            public boolean hasAlarmIntervalTime() {
                return ((alarm_msg) this.instance).hasAlarmIntervalTime();
            }

            @Override // com.igpsport.blelib.bean.Config.alarm_msgOrBuilder
            public int getAlarmIntervalTime() {
                return ((alarm_msg) this.instance).getAlarmIntervalTime();
            }

            public Builder setAlarmIntervalTime(int i) {
                copyOnWrite();
                ((alarm_msg) this.instance).setAlarmIntervalTime(i);
                return this;
            }

            public Builder clearAlarmIntervalTime() {
                copyOnWrite();
                ((alarm_msg) this.instance).clearAlarmIntervalTime();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new alarm_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001Л\u0002ဋ\u0000", new Object[]{"bitField0_", "setMsg_", alarm_params_set_msg.class, "alarmIntervalTime_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<alarm_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (alarm_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            alarm_msg alarm_msgVar = new alarm_msg();
            DEFAULT_INSTANCE = alarm_msgVar;
            GeneratedMessageLite.registerDefaultInstance(alarm_msg.class, alarm_msgVar);
        }

        public static alarm_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<alarm_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class lap_msg extends GeneratedMessageLite<lap_msg, Builder> implements lap_msgOrBuilder {
        private static final lap_msg DEFAULT_INSTANCE;
        public static final int LAP_TYPE_FIELD_NUMBER = 1;
        private static volatile Parser<lap_msg> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 3;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int lapType_;
        private byte memoizedIsInitialized = 2;
        private int status_;
        private int value_;

        private lap_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public boolean hasLapType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public LAP_TYPE getLapType() {
            LAP_TYPE lap_typeForNumber = LAP_TYPE.forNumber(this.lapType_);
            return lap_typeForNumber == null ? LAP_TYPE.LAP_TYPE_INVALID : lap_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLapType(LAP_TYPE lap_type) {
            this.lapType_ = lap_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLapType() {
            this.bitField0_ &= -2;
            this.lapType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public int getValue() {
            return this.value_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(int i) {
            this.bitField0_ |= 2;
            this.value_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 4;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -5;
            this.status_ = 0;
        }

        public static lap_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static lap_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static lap_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static lap_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static lap_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static lap_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static lap_msg parseFrom(InputStream inputStream) throws IOException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static lap_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static lap_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (lap_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static lap_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (lap_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static lap_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static lap_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (lap_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(lap_msg lap_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(lap_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<lap_msg, Builder> implements lap_msgOrBuilder {
            private Builder() {
                super(lap_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public boolean hasLapType() {
                return ((lap_msg) this.instance).hasLapType();
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public LAP_TYPE getLapType() {
                return ((lap_msg) this.instance).getLapType();
            }

            public Builder setLapType(LAP_TYPE lap_type) {
                copyOnWrite();
                ((lap_msg) this.instance).setLapType(lap_type);
                return this;
            }

            public Builder clearLapType() {
                copyOnWrite();
                ((lap_msg) this.instance).clearLapType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public boolean hasValue() {
                return ((lap_msg) this.instance).hasValue();
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public int getValue() {
                return ((lap_msg) this.instance).getValue();
            }

            public Builder setValue(int i) {
                copyOnWrite();
                ((lap_msg) this.instance).setValue(i);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((lap_msg) this.instance).clearValue();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public boolean hasStatus() {
                return ((lap_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.lap_msgOrBuilder
            public int getStatus() {
                return ((lap_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((lap_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((lap_msg) this.instance).clearStatus();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new lap_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ᔌ\u0000\u0002ဋ\u0001\u0003ဋ\u0002", new Object[]{"bitField0_", "lapType_", LAP_TYPE.internalGetVerifier(), "value_", "status_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<lap_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (lap_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            lap_msg lap_msgVar = new lap_msg();
            DEFAULT_INSTANCE = lap_msgVar;
            GeneratedMessageLite.registerDefaultInstance(lap_msg.class, lap_msgVar);
        }

        public static lap_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<lap_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class unit_msg extends GeneratedMessageLite<unit_msg, Builder> implements unit_msgOrBuilder {
        private static final unit_msg DEFAULT_INSTANCE;
        private static volatile Parser<unit_msg> PARSER = null;
        public static final int UNIT_ITEM_FIELD_NUMBER = 1;
        public static final int UNIT_TYPE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int unitItem_;
        private int unitType_;

        private unit_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
        public boolean hasUnitItem() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
        public UNIT_ITEM getUnitItem() {
            UNIT_ITEM unit_itemForNumber = UNIT_ITEM.forNumber(this.unitItem_);
            return unit_itemForNumber == null ? UNIT_ITEM.enum_UNIT_ITEM_INVALID : unit_itemForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnitItem(UNIT_ITEM unit_item) {
            this.unitItem_ = unit_item.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnitItem() {
            this.bitField0_ &= -2;
            this.unitItem_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
        public boolean hasUnitType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
        public UNIT_TYPE getUnitType() {
            UNIT_TYPE unit_typeForNumber = UNIT_TYPE.forNumber(this.unitType_);
            return unit_typeForNumber == null ? UNIT_TYPE.enum_UNIT_TYPE_INVALID : unit_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnitType(UNIT_TYPE unit_type) {
            this.unitType_ = unit_type.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnitType() {
            this.bitField0_ &= -3;
            this.unitType_ = 0;
        }

        public static unit_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static unit_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static unit_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static unit_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static unit_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static unit_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static unit_msg parseFrom(InputStream inputStream) throws IOException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static unit_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static unit_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (unit_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static unit_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (unit_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static unit_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static unit_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (unit_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(unit_msg unit_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(unit_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<unit_msg, Builder> implements unit_msgOrBuilder {
            private Builder() {
                super(unit_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
            public boolean hasUnitItem() {
                return ((unit_msg) this.instance).hasUnitItem();
            }

            @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
            public UNIT_ITEM getUnitItem() {
                return ((unit_msg) this.instance).getUnitItem();
            }

            public Builder setUnitItem(UNIT_ITEM unit_item) {
                copyOnWrite();
                ((unit_msg) this.instance).setUnitItem(unit_item);
                return this;
            }

            public Builder clearUnitItem() {
                copyOnWrite();
                ((unit_msg) this.instance).clearUnitItem();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
            public boolean hasUnitType() {
                return ((unit_msg) this.instance).hasUnitType();
            }

            @Override // com.igpsport.blelib.bean.Config.unit_msgOrBuilder
            public UNIT_TYPE getUnitType() {
                return ((unit_msg) this.instance).getUnitType();
            }

            public Builder setUnitType(UNIT_TYPE unit_type) {
                copyOnWrite();
                ((unit_msg) this.instance).setUnitType(unit_type);
                return this;
            }

            public Builder clearUnitType() {
                copyOnWrite();
                ((unit_msg) this.instance).clearUnitType();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new unit_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"bitField0_", "unitItem_", UNIT_ITEM.internalGetVerifier(), "unitType_", UNIT_TYPE.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<unit_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (unit_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            unit_msg unit_msgVar = new unit_msg();
            DEFAULT_INSTANCE = unit_msgVar;
            GeneratedMessageLite.registerDefaultInstance(unit_msg.class, unit_msgVar);
        }

        public static unit_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<unit_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class section_data_msg extends GeneratedMessageLite<section_data_msg, Builder> implements section_data_msgOrBuilder {
        public static final int CAD_FIELD_NUMBER = 2;
        private static final section_data_msg DEFAULT_INSTANCE;
        public static final int FTP_FIELD_NUMBER = 10;
        public static final int HRM_FIELD_NUMBER = 1;
        private static volatile Parser<section_data_msg> PARSER = null;
        public static final int POWER_FIELD_NUMBER = 11;
        public static final int RIDE_HRM_FIELD_NUMBER = 20;
        public static final int RIDE_HR_TYPE_FIELD_NUMBER = 23;
        public static final int RIDE_LACTIC_ACID_HRM_FIELD_NUMBER = 21;
        public static final int RIDE_RESERVE_HR_FIELD_NUMBER = 22;
        public static final int SPD_FIELD_NUMBER = 3;
        private int bitField0_;
        private int fTP_;
        private int rideHrType_;
        private Internal.IntList hrm_ = emptyIntList();
        private Internal.IntList cad_ = emptyIntList();
        private Internal.IntList spd_ = emptyIntList();
        private Internal.IntList power_ = emptyIntList();
        private Internal.IntList rideHrm_ = emptyIntList();
        private Internal.IntList rideLacticAcidHrm_ = emptyIntList();
        private Internal.IntList rideReserveHr_ = emptyIntList();

        private section_data_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getHrmList() {
            return this.hrm_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getHrmCount() {
            return this.hrm_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getHrm(int i) {
            return this.hrm_.getInt(i);
        }

        private void ensureHrmIsMutable() {
            Internal.IntList intList = this.hrm_;
            if (intList.isModifiable()) {
                return;
            }
            this.hrm_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHrm(int i, int i2) {
            ensureHrmIsMutable();
            this.hrm_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addHrm(int i) {
            ensureHrmIsMutable();
            this.hrm_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllHrm(Iterable<? extends Integer> iterable) {
            ensureHrmIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.hrm_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHrm() {
            this.hrm_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getCadList() {
            return this.cad_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getCadCount() {
            return this.cad_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getCad(int i) {
            return this.cad_.getInt(i);
        }

        private void ensureCadIsMutable() {
            Internal.IntList intList = this.cad_;
            if (intList.isModifiable()) {
                return;
            }
            this.cad_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCad(int i, int i2) {
            ensureCadIsMutable();
            this.cad_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCad(int i) {
            ensureCadIsMutable();
            this.cad_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCad(Iterable<? extends Integer> iterable) {
            ensureCadIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.cad_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCad() {
            this.cad_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getSpdList() {
            return this.spd_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getSpdCount() {
            return this.spd_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getSpd(int i) {
            return this.spd_.getInt(i);
        }

        private void ensureSpdIsMutable() {
            Internal.IntList intList = this.spd_;
            if (intList.isModifiable()) {
                return;
            }
            this.spd_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpd(int i, int i2) {
            ensureSpdIsMutable();
            this.spd_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSpd(int i) {
            ensureSpdIsMutable();
            this.spd_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSpd(Iterable<? extends Integer> iterable) {
            ensureSpdIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.spd_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpd() {
            this.spd_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public boolean hasFTP() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getFTP() {
            return this.fTP_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFTP(int i) {
            this.bitField0_ |= 1;
            this.fTP_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFTP() {
            this.bitField0_ &= -2;
            this.fTP_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getPowerList() {
            return this.power_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getPowerCount() {
            return this.power_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getPower(int i) {
            return this.power_.getInt(i);
        }

        private void ensurePowerIsMutable() {
            Internal.IntList intList = this.power_;
            if (intList.isModifiable()) {
                return;
            }
            this.power_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPower(int i, int i2) {
            ensurePowerIsMutable();
            this.power_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPower(int i) {
            ensurePowerIsMutable();
            this.power_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPower(Iterable<? extends Integer> iterable) {
            ensurePowerIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.power_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPower() {
            this.power_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getRideHrmList() {
            return this.rideHrm_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideHrmCount() {
            return this.rideHrm_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideHrm(int i) {
            return this.rideHrm_.getInt(i);
        }

        private void ensureRideHrmIsMutable() {
            Internal.IntList intList = this.rideHrm_;
            if (intList.isModifiable()) {
                return;
            }
            this.rideHrm_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideHrm(int i, int i2) {
            ensureRideHrmIsMutable();
            this.rideHrm_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRideHrm(int i) {
            ensureRideHrmIsMutable();
            this.rideHrm_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllRideHrm(Iterable<? extends Integer> iterable) {
            ensureRideHrmIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.rideHrm_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideHrm() {
            this.rideHrm_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getRideLacticAcidHrmList() {
            return this.rideLacticAcidHrm_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideLacticAcidHrmCount() {
            return this.rideLacticAcidHrm_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideLacticAcidHrm(int i) {
            return this.rideLacticAcidHrm_.getInt(i);
        }

        private void ensureRideLacticAcidHrmIsMutable() {
            Internal.IntList intList = this.rideLacticAcidHrm_;
            if (intList.isModifiable()) {
                return;
            }
            this.rideLacticAcidHrm_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideLacticAcidHrm(int i, int i2) {
            ensureRideLacticAcidHrmIsMutable();
            this.rideLacticAcidHrm_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRideLacticAcidHrm(int i) {
            ensureRideLacticAcidHrmIsMutable();
            this.rideLacticAcidHrm_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllRideLacticAcidHrm(Iterable<? extends Integer> iterable) {
            ensureRideLacticAcidHrmIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.rideLacticAcidHrm_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideLacticAcidHrm() {
            this.rideLacticAcidHrm_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public List<Integer> getRideReserveHrList() {
            return this.rideReserveHr_;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideReserveHrCount() {
            return this.rideReserveHr_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public int getRideReserveHr(int i) {
            return this.rideReserveHr_.getInt(i);
        }

        private void ensureRideReserveHrIsMutable() {
            Internal.IntList intList = this.rideReserveHr_;
            if (intList.isModifiable()) {
                return;
            }
            this.rideReserveHr_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideReserveHr(int i, int i2) {
            ensureRideReserveHrIsMutable();
            this.rideReserveHr_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRideReserveHr(int i) {
            ensureRideReserveHrIsMutable();
            this.rideReserveHr_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllRideReserveHr(Iterable<? extends Integer> iterable) {
            ensureRideReserveHrIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.rideReserveHr_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideReserveHr() {
            this.rideReserveHr_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public boolean hasRideHrType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
        public SECTION_HR_TYPE getRideHrType() {
            SECTION_HR_TYPE section_hr_typeForNumber = SECTION_HR_TYPE.forNumber(this.rideHrType_);
            return section_hr_typeForNumber == null ? SECTION_HR_TYPE.SECTION_HR_TYPE_INVALID : section_hr_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideHrType(SECTION_HR_TYPE section_hr_type) {
            this.rideHrType_ = section_hr_type.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideHrType() {
            this.bitField0_ &= -3;
            this.rideHrType_ = 0;
        }

        public static section_data_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static section_data_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static section_data_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static section_data_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static section_data_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static section_data_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static section_data_msg parseFrom(InputStream inputStream) throws IOException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static section_data_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static section_data_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (section_data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static section_data_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (section_data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static section_data_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static section_data_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (section_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(section_data_msg section_data_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(section_data_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<section_data_msg, Builder> implements section_data_msgOrBuilder {
            private Builder() {
                super(section_data_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getHrmList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getHrmList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getHrmCount() {
                return ((section_data_msg) this.instance).getHrmCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getHrm(int i) {
                return ((section_data_msg) this.instance).getHrm(i);
            }

            public Builder setHrm(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setHrm(i, i2);
                return this;
            }

            public Builder addHrm(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addHrm(i);
                return this;
            }

            public Builder addAllHrm(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllHrm(iterable);
                return this;
            }

            public Builder clearHrm() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearHrm();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getCadList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getCadList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getCadCount() {
                return ((section_data_msg) this.instance).getCadCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getCad(int i) {
                return ((section_data_msg) this.instance).getCad(i);
            }

            public Builder setCad(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setCad(i, i2);
                return this;
            }

            public Builder addCad(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addCad(i);
                return this;
            }

            public Builder addAllCad(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllCad(iterable);
                return this;
            }

            public Builder clearCad() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearCad();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getSpdList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getSpdList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getSpdCount() {
                return ((section_data_msg) this.instance).getSpdCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getSpd(int i) {
                return ((section_data_msg) this.instance).getSpd(i);
            }

            public Builder setSpd(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setSpd(i, i2);
                return this;
            }

            public Builder addSpd(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addSpd(i);
                return this;
            }

            public Builder addAllSpd(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllSpd(iterable);
                return this;
            }

            public Builder clearSpd() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearSpd();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public boolean hasFTP() {
                return ((section_data_msg) this.instance).hasFTP();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getFTP() {
                return ((section_data_msg) this.instance).getFTP();
            }

            public Builder setFTP(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).setFTP(i);
                return this;
            }

            public Builder clearFTP() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearFTP();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getPowerList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getPowerList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getPowerCount() {
                return ((section_data_msg) this.instance).getPowerCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getPower(int i) {
                return ((section_data_msg) this.instance).getPower(i);
            }

            public Builder setPower(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setPower(i, i2);
                return this;
            }

            public Builder addPower(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addPower(i);
                return this;
            }

            public Builder addAllPower(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllPower(iterable);
                return this;
            }

            public Builder clearPower() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearPower();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getRideHrmList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getRideHrmList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideHrmCount() {
                return ((section_data_msg) this.instance).getRideHrmCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideHrm(int i) {
                return ((section_data_msg) this.instance).getRideHrm(i);
            }

            public Builder setRideHrm(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setRideHrm(i, i2);
                return this;
            }

            public Builder addRideHrm(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addRideHrm(i);
                return this;
            }

            public Builder addAllRideHrm(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllRideHrm(iterable);
                return this;
            }

            public Builder clearRideHrm() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearRideHrm();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getRideLacticAcidHrmList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getRideLacticAcidHrmList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideLacticAcidHrmCount() {
                return ((section_data_msg) this.instance).getRideLacticAcidHrmCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideLacticAcidHrm(int i) {
                return ((section_data_msg) this.instance).getRideLacticAcidHrm(i);
            }

            public Builder setRideLacticAcidHrm(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setRideLacticAcidHrm(i, i2);
                return this;
            }

            public Builder addRideLacticAcidHrm(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addRideLacticAcidHrm(i);
                return this;
            }

            public Builder addAllRideLacticAcidHrm(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllRideLacticAcidHrm(iterable);
                return this;
            }

            public Builder clearRideLacticAcidHrm() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearRideLacticAcidHrm();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public List<Integer> getRideReserveHrList() {
                return Collections.unmodifiableList(((section_data_msg) this.instance).getRideReserveHrList());
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideReserveHrCount() {
                return ((section_data_msg) this.instance).getRideReserveHrCount();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public int getRideReserveHr(int i) {
                return ((section_data_msg) this.instance).getRideReserveHr(i);
            }

            public Builder setRideReserveHr(int i, int i2) {
                copyOnWrite();
                ((section_data_msg) this.instance).setRideReserveHr(i, i2);
                return this;
            }

            public Builder addRideReserveHr(int i) {
                copyOnWrite();
                ((section_data_msg) this.instance).addRideReserveHr(i);
                return this;
            }

            public Builder addAllRideReserveHr(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((section_data_msg) this.instance).addAllRideReserveHr(iterable);
                return this;
            }

            public Builder clearRideReserveHr() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearRideReserveHr();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public boolean hasRideHrType() {
                return ((section_data_msg) this.instance).hasRideHrType();
            }

            @Override // com.igpsport.blelib.bean.Config.section_data_msgOrBuilder
            public SECTION_HR_TYPE getRideHrType() {
                return ((section_data_msg) this.instance).getRideHrType();
            }

            public Builder setRideHrType(SECTION_HR_TYPE section_hr_type) {
                copyOnWrite();
                ((section_data_msg) this.instance).setRideHrType(section_hr_type);
                return this;
            }

            public Builder clearRideHrType() {
                copyOnWrite();
                ((section_data_msg) this.instance).clearRideHrType();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new section_data_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\u0017\t\u0000\u0007\u0000\u0001\u001d\u0002\u001d\u0003\u001d\nဋ\u0000\u000b\u001d\u0014\u001d\u0015\u001d\u0016\u001d\u0017ဌ\u0001", new Object[]{"bitField0_", "hrm_", "cad_", "spd_", "fTP_", "power_", "rideHrm_", "rideLacticAcidHrm_", "rideReserveHr_", "rideHrType_", SECTION_HR_TYPE.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<section_data_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (section_data_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            section_data_msg section_data_msgVar = new section_data_msg();
            DEFAULT_INSTANCE = section_data_msgVar;
            GeneratedMessageLite.registerDefaultInstance(section_data_msg.class, section_data_msgVar);
        }

        public static section_data_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<section_data_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class emergency_contact_msg extends GeneratedMessageLite<emergency_contact_msg, Builder> implements emergency_contact_msgOrBuilder {
        public static final int AREA_CODE_FIELD_NUMBER = 3;
        private static final emergency_contact_msg DEFAULT_INSTANCE;
        public static final int EMAIL_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<emergency_contact_msg> PARSER = null;
        public static final int PHONE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private String phone_ = "";
        private String areaCode_ = "";
        private String email_ = "";

        private emergency_contact_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            this.name_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public boolean hasPhone() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public String getPhone() {
            return this.phone_;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public ByteString getPhoneBytes() {
            return ByteString.copyFromUtf8(this.phone_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhone(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.phone_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPhone() {
            this.bitField0_ &= -3;
            this.phone_ = getDefaultInstance().getPhone();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneBytes(ByteString byteString) {
            this.phone_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public boolean hasAreaCode() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public String getAreaCode() {
            return this.areaCode_;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public ByteString getAreaCodeBytes() {
            return ByteString.copyFromUtf8(this.areaCode_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAreaCode(String str) {
            str.getClass();
            this.bitField0_ |= 4;
            this.areaCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAreaCode() {
            this.bitField0_ &= -5;
            this.areaCode_ = getDefaultInstance().getAreaCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAreaCodeBytes(ByteString byteString) {
            this.areaCode_ = byteString.toStringUtf8();
            this.bitField0_ |= 4;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public boolean hasEmail() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public String getEmail() {
            return this.email_;
        }

        @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
        public ByteString getEmailBytes() {
            return ByteString.copyFromUtf8(this.email_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmail(String str) {
            str.getClass();
            this.bitField0_ |= 8;
            this.email_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEmail() {
            this.bitField0_ &= -9;
            this.email_ = getDefaultInstance().getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmailBytes(ByteString byteString) {
            this.email_ = byteString.toStringUtf8();
            this.bitField0_ |= 8;
        }

        public static emergency_contact_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static emergency_contact_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static emergency_contact_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static emergency_contact_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static emergency_contact_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static emergency_contact_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static emergency_contact_msg parseFrom(InputStream inputStream) throws IOException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static emergency_contact_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static emergency_contact_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (emergency_contact_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static emergency_contact_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (emergency_contact_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static emergency_contact_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static emergency_contact_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (emergency_contact_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(emergency_contact_msg emergency_contact_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(emergency_contact_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<emergency_contact_msg, Builder> implements emergency_contact_msgOrBuilder {
            private Builder() {
                super(emergency_contact_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public boolean hasName() {
                return ((emergency_contact_msg) this.instance).hasName();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public String getName() {
                return ((emergency_contact_msg) this.instance).getName();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public ByteString getNameBytes() {
                return ((emergency_contact_msg) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setNameBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public boolean hasPhone() {
                return ((emergency_contact_msg) this.instance).hasPhone();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public String getPhone() {
                return ((emergency_contact_msg) this.instance).getPhone();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public ByteString getPhoneBytes() {
                return ((emergency_contact_msg) this.instance).getPhoneBytes();
            }

            public Builder setPhone(String str) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setPhone(str);
                return this;
            }

            public Builder clearPhone() {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).clearPhone();
                return this;
            }

            public Builder setPhoneBytes(ByteString byteString) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setPhoneBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public boolean hasAreaCode() {
                return ((emergency_contact_msg) this.instance).hasAreaCode();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public String getAreaCode() {
                return ((emergency_contact_msg) this.instance).getAreaCode();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public ByteString getAreaCodeBytes() {
                return ((emergency_contact_msg) this.instance).getAreaCodeBytes();
            }

            public Builder setAreaCode(String str) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setAreaCode(str);
                return this;
            }

            public Builder clearAreaCode() {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).clearAreaCode();
                return this;
            }

            public Builder setAreaCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setAreaCodeBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public boolean hasEmail() {
                return ((emergency_contact_msg) this.instance).hasEmail();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public String getEmail() {
                return ((emergency_contact_msg) this.instance).getEmail();
            }

            @Override // com.igpsport.blelib.bean.Config.emergency_contact_msgOrBuilder
            public ByteString getEmailBytes() {
                return ((emergency_contact_msg) this.instance).getEmailBytes();
            }

            public Builder setEmail(String str) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setEmail(str);
                return this;
            }

            public Builder clearEmail() {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).clearEmail();
                return this;
            }

            public Builder setEmailBytes(ByteString byteString) {
                copyOnWrite();
                ((emergency_contact_msg) this.instance).setEmailBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new emergency_contact_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"bitField0_", "name_", "phone_", "areaCode_", "email_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<emergency_contact_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (emergency_contact_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            emergency_contact_msg emergency_contact_msgVar = new emergency_contact_msg();
            DEFAULT_INSTANCE = emergency_contact_msgVar;
            GeneratedMessageLite.registerDefaultInstance(emergency_contact_msg.class, emergency_contact_msgVar);
        }

        public static emergency_contact_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<emergency_contact_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class user_data_msg extends GeneratedMessageLite<user_data_msg, Builder> implements user_data_msgOrBuilder {
        public static final int AGE_FIELD_NUMBER = 3;
        public static final int BIRTHDAY_FIELD_NUMBER = 23;
        private static final user_data_msg DEFAULT_INSTANCE;
        public static final int EMERGENCY_CONTACT_FIELD_NUMBER = 22;
        public static final int HEIGHT_FIELD_NUMBER = 4;
        public static final int MEMBER_ID_FIELD_NUMBER = 8;
        private static volatile Parser<user_data_msg> PARSER = null;
        public static final int PERSONAL_INFOR_CHANGE_TIME_FIELD_NUMBER = 21;
        public static final int REST_HR_FIELD_NUMBER = 14;
        public static final int RIDE_HR_LACTIC_ACID_FIELD_NUMBER = 13;
        public static final int RIDE_HR_MAX_FIELD_NUMBER = 11;
        public static final int SECTION_DATA_FIELD_NUMBER = 20;
        public static final int SEX_FIELD_NUMBER = 1;
        public static final int TIME_ZONE_FIELD_NUMBER = 7;
        public static final int UPDATE_STATUS_FIELD_NUMBER = 9;
        public static final int WEIGHT_FIELD_NUMBER = 2;
        private int age_;
        private int birthday_;
        private int bitField0_;
        private int height_;
        private int personalInforChangeTime_;
        private int restHr_;
        private int rideHrLacticAcid_;
        private int rideHrMax_;
        private section_data_msg sectionData_;
        private int sex_;
        private int timeZone_;
        private int updateStatus_;
        private int weight_;
        private String memberId_ = "";
        private Internal.ProtobufList<emergency_contact_msg> emergencyContact_ = emptyProtobufList();

        private user_data_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasSex() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getSex() {
            return this.sex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSex(int i) {
            this.bitField0_ |= 1;
            this.sex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSex() {
            this.bitField0_ &= -2;
            this.sex_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasWeight() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getWeight() {
            return this.weight_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWeight(int i) {
            this.bitField0_ |= 2;
            this.weight_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWeight() {
            this.bitField0_ &= -3;
            this.weight_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasAge() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getAge() {
            return this.age_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAge(int i) {
            this.bitField0_ |= 4;
            this.age_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAge() {
            this.bitField0_ &= -5;
            this.age_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasHeight() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getHeight() {
            return this.height_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeight(int i) {
            this.bitField0_ |= 8;
            this.height_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeight() {
            this.bitField0_ &= -9;
            this.height_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasTimeZone() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getTimeZone() {
            return this.timeZone_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimeZone(int i) {
            this.bitField0_ |= 16;
            this.timeZone_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeZone() {
            this.bitField0_ &= -17;
            this.timeZone_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasMemberId() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public String getMemberId() {
            return this.memberId_;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public ByteString getMemberIdBytes() {
            return ByteString.copyFromUtf8(this.memberId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMemberId(String str) {
            str.getClass();
            this.bitField0_ |= 32;
            this.memberId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMemberId() {
            this.bitField0_ &= -33;
            this.memberId_ = getDefaultInstance().getMemberId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMemberIdBytes(ByteString byteString) {
            this.memberId_ = byteString.toStringUtf8();
            this.bitField0_ |= 32;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasUpdateStatus() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getUpdateStatus() {
            return this.updateStatus_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateStatus(int i) {
            this.bitField0_ |= 64;
            this.updateStatus_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateStatus() {
            this.bitField0_ &= -65;
            this.updateStatus_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasRideHrMax() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getRideHrMax() {
            return this.rideHrMax_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideHrMax(int i) {
            this.bitField0_ |= 128;
            this.rideHrMax_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideHrMax() {
            this.bitField0_ &= -129;
            this.rideHrMax_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasRideHrLacticAcid() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getRideHrLacticAcid() {
            return this.rideHrLacticAcid_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRideHrLacticAcid(int i) {
            this.bitField0_ |= 256;
            this.rideHrLacticAcid_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRideHrLacticAcid() {
            this.bitField0_ &= -257;
            this.rideHrLacticAcid_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasRestHr() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getRestHr() {
            return this.restHr_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRestHr(int i) {
            this.bitField0_ |= 512;
            this.restHr_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRestHr() {
            this.bitField0_ &= -513;
            this.restHr_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasSectionData() {
            return (this.bitField0_ & 1024) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public section_data_msg getSectionData() {
            section_data_msg section_data_msgVar = this.sectionData_;
            return section_data_msgVar == null ? section_data_msg.getDefaultInstance() : section_data_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSectionData(section_data_msg section_data_msgVar) {
            section_data_msgVar.getClass();
            this.sectionData_ = section_data_msgVar;
            this.bitField0_ |= 1024;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSectionData(section_data_msg section_data_msgVar) {
            section_data_msgVar.getClass();
            section_data_msg section_data_msgVar2 = this.sectionData_;
            if (section_data_msgVar2 != null && section_data_msgVar2 != section_data_msg.getDefaultInstance()) {
                this.sectionData_ = section_data_msg.newBuilder(this.sectionData_).mergeFrom(section_data_msgVar).buildPartial();
            } else {
                this.sectionData_ = section_data_msgVar;
            }
            this.bitField0_ |= 1024;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSectionData() {
            this.sectionData_ = null;
            this.bitField0_ &= -1025;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasPersonalInforChangeTime() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getPersonalInforChangeTime() {
            return this.personalInforChangeTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPersonalInforChangeTime(int i) {
            this.bitField0_ |= 2048;
            this.personalInforChangeTime_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPersonalInforChangeTime() {
            this.bitField0_ &= -2049;
            this.personalInforChangeTime_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public List<emergency_contact_msg> getEmergencyContactList() {
            return this.emergencyContact_;
        }

        public List<? extends emergency_contact_msgOrBuilder> getEmergencyContactOrBuilderList() {
            return this.emergencyContact_;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getEmergencyContactCount() {
            return this.emergencyContact_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public emergency_contact_msg getEmergencyContact(int i) {
            return this.emergencyContact_.get(i);
        }

        public emergency_contact_msgOrBuilder getEmergencyContactOrBuilder(int i) {
            return this.emergencyContact_.get(i);
        }

        private void ensureEmergencyContactIsMutable() {
            Internal.ProtobufList<emergency_contact_msg> protobufList = this.emergencyContact_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.emergencyContact_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmergencyContact(int i, emergency_contact_msg emergency_contact_msgVar) {
            emergency_contact_msgVar.getClass();
            ensureEmergencyContactIsMutable();
            this.emergencyContact_.set(i, emergency_contact_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEmergencyContact(emergency_contact_msg emergency_contact_msgVar) {
            emergency_contact_msgVar.getClass();
            ensureEmergencyContactIsMutable();
            this.emergencyContact_.add(emergency_contact_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEmergencyContact(int i, emergency_contact_msg emergency_contact_msgVar) {
            emergency_contact_msgVar.getClass();
            ensureEmergencyContactIsMutable();
            this.emergencyContact_.add(i, emergency_contact_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllEmergencyContact(Iterable<? extends emergency_contact_msg> iterable) {
            ensureEmergencyContactIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.emergencyContact_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEmergencyContact() {
            this.emergencyContact_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeEmergencyContact(int i) {
            ensureEmergencyContactIsMutable();
            this.emergencyContact_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public boolean hasBirthday() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
        public int getBirthday() {
            return this.birthday_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBirthday(int i) {
            this.bitField0_ |= 4096;
            this.birthday_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBirthday() {
            this.bitField0_ &= -4097;
            this.birthday_ = 0;
        }

        public static user_data_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static user_data_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static user_data_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static user_data_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static user_data_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static user_data_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static user_data_msg parseFrom(InputStream inputStream) throws IOException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static user_data_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static user_data_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (user_data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static user_data_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (user_data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static user_data_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static user_data_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (user_data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(user_data_msg user_data_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(user_data_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<user_data_msg, Builder> implements user_data_msgOrBuilder {
            private Builder() {
                super(user_data_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasSex() {
                return ((user_data_msg) this.instance).hasSex();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getSex() {
                return ((user_data_msg) this.instance).getSex();
            }

            public Builder setSex(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setSex(i);
                return this;
            }

            public Builder clearSex() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearSex();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasWeight() {
                return ((user_data_msg) this.instance).hasWeight();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getWeight() {
                return ((user_data_msg) this.instance).getWeight();
            }

            public Builder setWeight(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setWeight(i);
                return this;
            }

            public Builder clearWeight() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearWeight();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasAge() {
                return ((user_data_msg) this.instance).hasAge();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getAge() {
                return ((user_data_msg) this.instance).getAge();
            }

            public Builder setAge(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setAge(i);
                return this;
            }

            public Builder clearAge() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearAge();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasHeight() {
                return ((user_data_msg) this.instance).hasHeight();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getHeight() {
                return ((user_data_msg) this.instance).getHeight();
            }

            public Builder setHeight(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setHeight(i);
                return this;
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearHeight();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasTimeZone() {
                return ((user_data_msg) this.instance).hasTimeZone();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getTimeZone() {
                return ((user_data_msg) this.instance).getTimeZone();
            }

            public Builder setTimeZone(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setTimeZone(i);
                return this;
            }

            public Builder clearTimeZone() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearTimeZone();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasMemberId() {
                return ((user_data_msg) this.instance).hasMemberId();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public String getMemberId() {
                return ((user_data_msg) this.instance).getMemberId();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public ByteString getMemberIdBytes() {
                return ((user_data_msg) this.instance).getMemberIdBytes();
            }

            public Builder setMemberId(String str) {
                copyOnWrite();
                ((user_data_msg) this.instance).setMemberId(str);
                return this;
            }

            public Builder clearMemberId() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearMemberId();
                return this;
            }

            public Builder setMemberIdBytes(ByteString byteString) {
                copyOnWrite();
                ((user_data_msg) this.instance).setMemberIdBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasUpdateStatus() {
                return ((user_data_msg) this.instance).hasUpdateStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getUpdateStatus() {
                return ((user_data_msg) this.instance).getUpdateStatus();
            }

            public Builder setUpdateStatus(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setUpdateStatus(i);
                return this;
            }

            public Builder clearUpdateStatus() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearUpdateStatus();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasRideHrMax() {
                return ((user_data_msg) this.instance).hasRideHrMax();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getRideHrMax() {
                return ((user_data_msg) this.instance).getRideHrMax();
            }

            public Builder setRideHrMax(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setRideHrMax(i);
                return this;
            }

            public Builder clearRideHrMax() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearRideHrMax();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasRideHrLacticAcid() {
                return ((user_data_msg) this.instance).hasRideHrLacticAcid();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getRideHrLacticAcid() {
                return ((user_data_msg) this.instance).getRideHrLacticAcid();
            }

            public Builder setRideHrLacticAcid(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setRideHrLacticAcid(i);
                return this;
            }

            public Builder clearRideHrLacticAcid() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearRideHrLacticAcid();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasRestHr() {
                return ((user_data_msg) this.instance).hasRestHr();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getRestHr() {
                return ((user_data_msg) this.instance).getRestHr();
            }

            public Builder setRestHr(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setRestHr(i);
                return this;
            }

            public Builder clearRestHr() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearRestHr();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasSectionData() {
                return ((user_data_msg) this.instance).hasSectionData();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public section_data_msg getSectionData() {
                return ((user_data_msg) this.instance).getSectionData();
            }

            public Builder setSectionData(section_data_msg section_data_msgVar) {
                copyOnWrite();
                ((user_data_msg) this.instance).setSectionData(section_data_msgVar);
                return this;
            }

            public Builder setSectionData(section_data_msg.Builder builder) {
                copyOnWrite();
                ((user_data_msg) this.instance).setSectionData(builder.build());
                return this;
            }

            public Builder mergeSectionData(section_data_msg section_data_msgVar) {
                copyOnWrite();
                ((user_data_msg) this.instance).mergeSectionData(section_data_msgVar);
                return this;
            }

            public Builder clearSectionData() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearSectionData();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasPersonalInforChangeTime() {
                return ((user_data_msg) this.instance).hasPersonalInforChangeTime();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getPersonalInforChangeTime() {
                return ((user_data_msg) this.instance).getPersonalInforChangeTime();
            }

            public Builder setPersonalInforChangeTime(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setPersonalInforChangeTime(i);
                return this;
            }

            public Builder clearPersonalInforChangeTime() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearPersonalInforChangeTime();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public List<emergency_contact_msg> getEmergencyContactList() {
                return Collections.unmodifiableList(((user_data_msg) this.instance).getEmergencyContactList());
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getEmergencyContactCount() {
                return ((user_data_msg) this.instance).getEmergencyContactCount();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public emergency_contact_msg getEmergencyContact(int i) {
                return ((user_data_msg) this.instance).getEmergencyContact(i);
            }

            public Builder setEmergencyContact(int i, emergency_contact_msg emergency_contact_msgVar) {
                copyOnWrite();
                ((user_data_msg) this.instance).setEmergencyContact(i, emergency_contact_msgVar);
                return this;
            }

            public Builder setEmergencyContact(int i, emergency_contact_msg.Builder builder) {
                copyOnWrite();
                ((user_data_msg) this.instance).setEmergencyContact(i, builder.build());
                return this;
            }

            public Builder addEmergencyContact(emergency_contact_msg emergency_contact_msgVar) {
                copyOnWrite();
                ((user_data_msg) this.instance).addEmergencyContact(emergency_contact_msgVar);
                return this;
            }

            public Builder addEmergencyContact(int i, emergency_contact_msg emergency_contact_msgVar) {
                copyOnWrite();
                ((user_data_msg) this.instance).addEmergencyContact(i, emergency_contact_msgVar);
                return this;
            }

            public Builder addEmergencyContact(emergency_contact_msg.Builder builder) {
                copyOnWrite();
                ((user_data_msg) this.instance).addEmergencyContact(builder.build());
                return this;
            }

            public Builder addEmergencyContact(int i, emergency_contact_msg.Builder builder) {
                copyOnWrite();
                ((user_data_msg) this.instance).addEmergencyContact(i, builder.build());
                return this;
            }

            public Builder addAllEmergencyContact(Iterable<? extends emergency_contact_msg> iterable) {
                copyOnWrite();
                ((user_data_msg) this.instance).addAllEmergencyContact(iterable);
                return this;
            }

            public Builder clearEmergencyContact() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearEmergencyContact();
                return this;
            }

            public Builder removeEmergencyContact(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).removeEmergencyContact(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public boolean hasBirthday() {
                return ((user_data_msg) this.instance).hasBirthday();
            }

            @Override // com.igpsport.blelib.bean.Config.user_data_msgOrBuilder
            public int getBirthday() {
                return ((user_data_msg) this.instance).getBirthday();
            }

            public Builder setBirthday(int i) {
                copyOnWrite();
                ((user_data_msg) this.instance).setBirthday(i);
                return this;
            }

            public Builder clearBirthday() {
                copyOnWrite();
                ((user_data_msg) this.instance).clearBirthday();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new user_data_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000e\u0000\u0001\u0001\u0017\u000e\u0000\u0001\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0007င\u0004\bဈ\u0005\tဋ\u0006\u000bဋ\u0007\rဋ\b\u000eဋ\t\u0014ဉ\n\u0015ဋ\u000b\u0016\u001b\u0017ဋ\f", new Object[]{"bitField0_", "sex_", "weight_", "age_", "height_", "timeZone_", "memberId_", "updateStatus_", "rideHrMax_", "rideHrLacticAcid_", "restHr_", "sectionData_", "personalInforChangeTime_", "emergencyContact_", emergency_contact_msg.class, "birthday_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<user_data_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (user_data_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            user_data_msg user_data_msgVar = new user_data_msg();
            DEFAULT_INSTANCE = user_data_msgVar;
            GeneratedMessageLite.registerDefaultInstance(user_data_msg.class, user_data_msgVar);
        }

        public static user_data_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<user_data_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class mode_msg extends GeneratedMessageLite<mode_msg, Builder> implements mode_msgOrBuilder {
        private static final mode_msg DEFAULT_INSTANCE;
        public static final int INUSE_FIELD_NUMBER = 4;
        public static final int MODE_COLOR_FIELD_NUMBER = 2;
        public static final int MODE_INDEX_FIELD_NUMBER = 1;
        public static final int MODE_NAME_FIELD_NUMBER = 6;
        private static volatile Parser<mode_msg> PARSER = null;
        public static final int TEMPLATE_INDEX_FIELD_NUMBER = 5;
        public static final int VALID_FIELD_NUMBER = 3;
        private int bitField0_;
        private int inuse_;
        private int modeColor_;
        private int modeIndex_;
        private String modeName_ = "";
        private int templateIndex_;
        private int valid_;

        private mode_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasModeIndex() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public int getModeIndex() {
            return this.modeIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeIndex(int i) {
            this.bitField0_ |= 1;
            this.modeIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModeIndex() {
            this.bitField0_ &= -2;
            this.modeIndex_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasModeColor() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public int getModeColor() {
            return this.modeColor_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeColor(int i) {
            this.bitField0_ |= 2;
            this.modeColor_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModeColor() {
            this.bitField0_ &= -3;
            this.modeColor_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasValid() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public int getValid() {
            return this.valid_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValid(int i) {
            this.bitField0_ |= 4;
            this.valid_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValid() {
            this.bitField0_ &= -5;
            this.valid_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasInuse() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public int getInuse() {
            return this.inuse_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInuse(int i) {
            this.bitField0_ |= 8;
            this.inuse_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInuse() {
            this.bitField0_ &= -9;
            this.inuse_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasTemplateIndex() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public int getTemplateIndex() {
            return this.templateIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTemplateIndex(int i) {
            this.bitField0_ |= 16;
            this.templateIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTemplateIndex() {
            this.bitField0_ &= -17;
            this.templateIndex_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public boolean hasModeName() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public String getModeName() {
            return this.modeName_;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
        public ByteString getModeNameBytes() {
            return ByteString.copyFromUtf8(this.modeName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeName(String str) {
            str.getClass();
            this.bitField0_ |= 32;
            this.modeName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModeName() {
            this.bitField0_ &= -33;
            this.modeName_ = getDefaultInstance().getModeName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeNameBytes(ByteString byteString) {
            this.modeName_ = byteString.toStringUtf8();
            this.bitField0_ |= 32;
        }

        public static mode_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static mode_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static mode_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static mode_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static mode_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static mode_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static mode_msg parseFrom(InputStream inputStream) throws IOException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static mode_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static mode_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (mode_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static mode_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static mode_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static mode_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(mode_msg mode_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(mode_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<mode_msg, Builder> implements mode_msgOrBuilder {
            private Builder() {
                super(mode_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasModeIndex() {
                return ((mode_msg) this.instance).hasModeIndex();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public int getModeIndex() {
                return ((mode_msg) this.instance).getModeIndex();
            }

            public Builder setModeIndex(int i) {
                copyOnWrite();
                ((mode_msg) this.instance).setModeIndex(i);
                return this;
            }

            public Builder clearModeIndex() {
                copyOnWrite();
                ((mode_msg) this.instance).clearModeIndex();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasModeColor() {
                return ((mode_msg) this.instance).hasModeColor();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public int getModeColor() {
                return ((mode_msg) this.instance).getModeColor();
            }

            public Builder setModeColor(int i) {
                copyOnWrite();
                ((mode_msg) this.instance).setModeColor(i);
                return this;
            }

            public Builder clearModeColor() {
                copyOnWrite();
                ((mode_msg) this.instance).clearModeColor();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasValid() {
                return ((mode_msg) this.instance).hasValid();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public int getValid() {
                return ((mode_msg) this.instance).getValid();
            }

            public Builder setValid(int i) {
                copyOnWrite();
                ((mode_msg) this.instance).setValid(i);
                return this;
            }

            public Builder clearValid() {
                copyOnWrite();
                ((mode_msg) this.instance).clearValid();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasInuse() {
                return ((mode_msg) this.instance).hasInuse();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public int getInuse() {
                return ((mode_msg) this.instance).getInuse();
            }

            public Builder setInuse(int i) {
                copyOnWrite();
                ((mode_msg) this.instance).setInuse(i);
                return this;
            }

            public Builder clearInuse() {
                copyOnWrite();
                ((mode_msg) this.instance).clearInuse();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasTemplateIndex() {
                return ((mode_msg) this.instance).hasTemplateIndex();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public int getTemplateIndex() {
                return ((mode_msg) this.instance).getTemplateIndex();
            }

            public Builder setTemplateIndex(int i) {
                copyOnWrite();
                ((mode_msg) this.instance).setTemplateIndex(i);
                return this;
            }

            public Builder clearTemplateIndex() {
                copyOnWrite();
                ((mode_msg) this.instance).clearTemplateIndex();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public boolean hasModeName() {
                return ((mode_msg) this.instance).hasModeName();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public String getModeName() {
                return ((mode_msg) this.instance).getModeName();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_msgOrBuilder
            public ByteString getModeNameBytes() {
                return ((mode_msg) this.instance).getModeNameBytes();
            }

            public Builder setModeName(String str) {
                copyOnWrite();
                ((mode_msg) this.instance).setModeName(str);
                return this;
            }

            public Builder clearModeName() {
                copyOnWrite();
                ((mode_msg) this.instance).clearModeName();
                return this;
            }

            public Builder setModeNameBytes(ByteString byteString) {
                copyOnWrite();
                ((mode_msg) this.instance).setModeNameBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new mode_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005ဋ\u0004\u0006ဈ\u0005", new Object[]{"bitField0_", "modeIndex_", "modeColor_", "valid_", "inuse_", "templateIndex_", "modeName_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<mode_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (mode_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            mode_msg mode_msgVar = new mode_msg();
            DEFAULT_INSTANCE = mode_msgVar;
            GeneratedMessageLite.registerDefaultInstance(mode_msg.class, mode_msgVar);
        }

        public static mode_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<mode_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class mode_config_msg extends GeneratedMessageLite<mode_config_msg, Builder> implements mode_config_msgOrBuilder {
        private static final mode_config_msg DEFAULT_INSTANCE;
        public static final int DEFAULT_MODE_NUM_FIELD_NUMBER = 1;
        public static final int MAX_MODE_NUM_FIELD_NUMBER = 2;
        private static volatile Parser<mode_config_msg> PARSER;
        private int bitField0_;
        private int defaultModeNum_;
        private int maxModeNum_;

        private mode_config_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
        public boolean hasDefaultModeNum() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
        public int getDefaultModeNum() {
            return this.defaultModeNum_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDefaultModeNum(int i) {
            this.bitField0_ |= 1;
            this.defaultModeNum_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDefaultModeNum() {
            this.bitField0_ &= -2;
            this.defaultModeNum_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
        public boolean hasMaxModeNum() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
        public int getMaxModeNum() {
            return this.maxModeNum_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxModeNum(int i) {
            this.bitField0_ |= 2;
            this.maxModeNum_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaxModeNum() {
            this.bitField0_ &= -3;
            this.maxModeNum_ = 0;
        }

        public static mode_config_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static mode_config_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static mode_config_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static mode_config_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static mode_config_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static mode_config_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static mode_config_msg parseFrom(InputStream inputStream) throws IOException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static mode_config_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static mode_config_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (mode_config_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static mode_config_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_config_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static mode_config_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static mode_config_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (mode_config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(mode_config_msg mode_config_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(mode_config_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<mode_config_msg, Builder> implements mode_config_msgOrBuilder {
            private Builder() {
                super(mode_config_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
            public boolean hasDefaultModeNum() {
                return ((mode_config_msg) this.instance).hasDefaultModeNum();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
            public int getDefaultModeNum() {
                return ((mode_config_msg) this.instance).getDefaultModeNum();
            }

            public Builder setDefaultModeNum(int i) {
                copyOnWrite();
                ((mode_config_msg) this.instance).setDefaultModeNum(i);
                return this;
            }

            public Builder clearDefaultModeNum() {
                copyOnWrite();
                ((mode_config_msg) this.instance).clearDefaultModeNum();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
            public boolean hasMaxModeNum() {
                return ((mode_config_msg) this.instance).hasMaxModeNum();
            }

            @Override // com.igpsport.blelib.bean.Config.mode_config_msgOrBuilder
            public int getMaxModeNum() {
                return ((mode_config_msg) this.instance).getMaxModeNum();
            }

            public Builder setMaxModeNum(int i) {
                copyOnWrite();
                ((mode_config_msg) this.instance).setMaxModeNum(i);
                return this;
            }

            public Builder clearMaxModeNum() {
                copyOnWrite();
                ((mode_config_msg) this.instance).clearMaxModeNum();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new mode_config_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "defaultModeNum_", "maxModeNum_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<mode_config_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (mode_config_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            mode_config_msg mode_config_msgVar = new mode_config_msg();
            DEFAULT_INSTANCE = mode_config_msgVar;
            GeneratedMessageLite.registerDefaultInstance(mode_config_msg.class, mode_config_msgVar);
        }

        public static mode_config_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<mode_config_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class page_msg extends GeneratedMessageLite<page_msg, Builder> implements page_msgOrBuilder {
        public static final int DATA_FIELD_NUMBER = 3;
        public static final int DATA_SITE_FIELD_NUMBER = 4;
        private static final page_msg DEFAULT_INSTANCE;
        public static final int FORBIDDEN_CLOSE_FIELD_NUMBER = 11;
        public static final int GRAPHIC_DISPLAY_FIELD_NUMBER = 10;
        public static final int LINE_WIDTH_FIELD_NUMBER = 9;
        public static final int MAIN_PAGE_STATUS_FIELD_NUMBER = 8;
        public static final int PAGE_INDEX_FIELD_NUMBER = 1;
        public static final int PAGE_MODE_FIELD_NUMBER = 7;
        public static final int PAGE_NAME_FIELD_NUMBER = 5;
        public static final int PAGE_TYPE_FIELD_NUMBER = 6;
        private static volatile Parser<page_msg> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int dataSite_;
        private int forbiddenClose_;
        private int graphicDisplay_;
        private int lineWidth_;
        private int mainPageStatus_;
        private int pageIndex_;
        private int pageMode_;
        private int pageType_;
        private int status_;
        private Internal.IntList data_ = emptyIntList();
        private String pageName_ = "";

        private page_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasPageIndex() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getPageIndex() {
            return this.pageIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageIndex(int i) {
            this.bitField0_ |= 1;
            this.pageIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageIndex() {
            this.bitField0_ &= -2;
            this.pageIndex_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 2;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -3;
            this.status_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public List<Integer> getDataList() {
            return this.data_;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getDataCount() {
            return this.data_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getData(int i) {
            return this.data_.getInt(i);
        }

        private void ensureDataIsMutable() {
            Internal.IntList intList = this.data_;
            if (intList.isModifiable()) {
                return;
            }
            this.data_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setData(int i, int i2) {
            ensureDataIsMutable();
            this.data_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addData(int i) {
            ensureDataIsMutable();
            this.data_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllData(Iterable<? extends Integer> iterable) {
            ensureDataIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.data_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearData() {
            this.data_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasDataSite() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getDataSite() {
            return this.dataSite_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDataSite(int i) {
            this.bitField0_ |= 4;
            this.dataSite_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDataSite() {
            this.bitField0_ &= -5;
            this.dataSite_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasPageName() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public String getPageName() {
            return this.pageName_;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public ByteString getPageNameBytes() {
            return ByteString.copyFromUtf8(this.pageName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageName(String str) {
            str.getClass();
            this.bitField0_ |= 8;
            this.pageName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageName() {
            this.bitField0_ &= -9;
            this.pageName_ = getDefaultInstance().getPageName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageNameBytes(ByteString byteString) {
            this.pageName_ = byteString.toStringUtf8();
            this.bitField0_ |= 8;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasPageType() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public PAGE_TYPE getPageType() {
            PAGE_TYPE page_typeForNumber = PAGE_TYPE.forNumber(this.pageType_);
            return page_typeForNumber == null ? PAGE_TYPE.enum_PAGE_TYPE_INVALID : page_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageType(PAGE_TYPE page_type) {
            this.pageType_ = page_type.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageType() {
            this.bitField0_ &= -17;
            this.pageType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasPageMode() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getPageMode() {
            return this.pageMode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageMode(int i) {
            this.bitField0_ |= 32;
            this.pageMode_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageMode() {
            this.bitField0_ &= -33;
            this.pageMode_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasMainPageStatus() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getMainPageStatus() {
            return this.mainPageStatus_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMainPageStatus(int i) {
            this.bitField0_ |= 64;
            this.mainPageStatus_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMainPageStatus() {
            this.bitField0_ &= -65;
            this.mainPageStatus_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasLineWidth() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getLineWidth() {
            return this.lineWidth_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLineWidth(int i) {
            this.bitField0_ |= 128;
            this.lineWidth_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLineWidth() {
            this.bitField0_ &= -129;
            this.lineWidth_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasGraphicDisplay() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getGraphicDisplay() {
            return this.graphicDisplay_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGraphicDisplay(int i) {
            this.bitField0_ |= 256;
            this.graphicDisplay_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGraphicDisplay() {
            this.bitField0_ &= -257;
            this.graphicDisplay_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public boolean hasForbiddenClose() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
        public int getForbiddenClose() {
            return this.forbiddenClose_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setForbiddenClose(int i) {
            this.bitField0_ |= 512;
            this.forbiddenClose_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearForbiddenClose() {
            this.bitField0_ &= -513;
            this.forbiddenClose_ = 0;
        }

        public static page_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static page_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static page_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static page_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static page_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static page_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static page_msg parseFrom(InputStream inputStream) throws IOException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static page_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static page_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (page_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static page_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (page_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static page_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static page_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (page_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(page_msg page_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(page_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<page_msg, Builder> implements page_msgOrBuilder {
            private Builder() {
                super(page_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasPageIndex() {
                return ((page_msg) this.instance).hasPageIndex();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getPageIndex() {
                return ((page_msg) this.instance).getPageIndex();
            }

            public Builder setPageIndex(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setPageIndex(i);
                return this;
            }

            public Builder clearPageIndex() {
                copyOnWrite();
                ((page_msg) this.instance).clearPageIndex();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasStatus() {
                return ((page_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getStatus() {
                return ((page_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((page_msg) this.instance).clearStatus();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public List<Integer> getDataList() {
                return Collections.unmodifiableList(((page_msg) this.instance).getDataList());
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getDataCount() {
                return ((page_msg) this.instance).getDataCount();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getData(int i) {
                return ((page_msg) this.instance).getData(i);
            }

            public Builder setData(int i, int i2) {
                copyOnWrite();
                ((page_msg) this.instance).setData(i, i2);
                return this;
            }

            public Builder addData(int i) {
                copyOnWrite();
                ((page_msg) this.instance).addData(i);
                return this;
            }

            public Builder addAllData(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((page_msg) this.instance).addAllData(iterable);
                return this;
            }

            public Builder clearData() {
                copyOnWrite();
                ((page_msg) this.instance).clearData();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasDataSite() {
                return ((page_msg) this.instance).hasDataSite();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getDataSite() {
                return ((page_msg) this.instance).getDataSite();
            }

            public Builder setDataSite(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setDataSite(i);
                return this;
            }

            public Builder clearDataSite() {
                copyOnWrite();
                ((page_msg) this.instance).clearDataSite();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasPageName() {
                return ((page_msg) this.instance).hasPageName();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public String getPageName() {
                return ((page_msg) this.instance).getPageName();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public ByteString getPageNameBytes() {
                return ((page_msg) this.instance).getPageNameBytes();
            }

            public Builder setPageName(String str) {
                copyOnWrite();
                ((page_msg) this.instance).setPageName(str);
                return this;
            }

            public Builder clearPageName() {
                copyOnWrite();
                ((page_msg) this.instance).clearPageName();
                return this;
            }

            public Builder setPageNameBytes(ByteString byteString) {
                copyOnWrite();
                ((page_msg) this.instance).setPageNameBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasPageType() {
                return ((page_msg) this.instance).hasPageType();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public PAGE_TYPE getPageType() {
                return ((page_msg) this.instance).getPageType();
            }

            public Builder setPageType(PAGE_TYPE page_type) {
                copyOnWrite();
                ((page_msg) this.instance).setPageType(page_type);
                return this;
            }

            public Builder clearPageType() {
                copyOnWrite();
                ((page_msg) this.instance).clearPageType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasPageMode() {
                return ((page_msg) this.instance).hasPageMode();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getPageMode() {
                return ((page_msg) this.instance).getPageMode();
            }

            public Builder setPageMode(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setPageMode(i);
                return this;
            }

            public Builder clearPageMode() {
                copyOnWrite();
                ((page_msg) this.instance).clearPageMode();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasMainPageStatus() {
                return ((page_msg) this.instance).hasMainPageStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getMainPageStatus() {
                return ((page_msg) this.instance).getMainPageStatus();
            }

            public Builder setMainPageStatus(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setMainPageStatus(i);
                return this;
            }

            public Builder clearMainPageStatus() {
                copyOnWrite();
                ((page_msg) this.instance).clearMainPageStatus();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasLineWidth() {
                return ((page_msg) this.instance).hasLineWidth();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getLineWidth() {
                return ((page_msg) this.instance).getLineWidth();
            }

            public Builder setLineWidth(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setLineWidth(i);
                return this;
            }

            public Builder clearLineWidth() {
                copyOnWrite();
                ((page_msg) this.instance).clearLineWidth();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasGraphicDisplay() {
                return ((page_msg) this.instance).hasGraphicDisplay();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getGraphicDisplay() {
                return ((page_msg) this.instance).getGraphicDisplay();
            }

            public Builder setGraphicDisplay(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setGraphicDisplay(i);
                return this;
            }

            public Builder clearGraphicDisplay() {
                copyOnWrite();
                ((page_msg) this.instance).clearGraphicDisplay();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public boolean hasForbiddenClose() {
                return ((page_msg) this.instance).hasForbiddenClose();
            }

            @Override // com.igpsport.blelib.bean.Config.page_msgOrBuilder
            public int getForbiddenClose() {
                return ((page_msg) this.instance).getForbiddenClose();
            }

            public Builder setForbiddenClose(int i) {
                copyOnWrite();
                ((page_msg) this.instance).setForbiddenClose(i);
                return this;
            }

            public Builder clearForbiddenClose() {
                copyOnWrite();
                ((page_msg) this.instance).clearForbiddenClose();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new page_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003\u001d\u0004ဋ\u0002\u0005ဈ\u0003\u0006ဌ\u0004\u0007ဋ\u0005\bဋ\u0006\tဋ\u0007\nဋ\b\u000bဋ\t", new Object[]{"bitField0_", "pageIndex_", "status_", "data_", "dataSite_", "pageName_", "pageType_", PAGE_TYPE.internalGetVerifier(), "pageMode_", "mainPageStatus_", "lineWidth_", "graphicDisplay_", "forbiddenClose_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<page_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (page_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            page_msg page_msgVar = new page_msg();
            DEFAULT_INSTANCE = page_msgVar;
            GeneratedMessageLite.registerDefaultInstance(page_msg.class, page_msgVar);
        }

        public static page_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<page_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class cur_page_status_msg extends GeneratedMessageLite<cur_page_status_msg, Builder> implements cur_page_status_msgOrBuilder {
        public static final int DATA_PAGE_NUM_MAX_FIELD_NUMBER = 3;
        private static final cur_page_status_msg DEFAULT_INSTANCE;
        public static final int LCD_TYPE_FIELD_NUMBER = 5;
        public static final int MAIN_PAGE_OPEN_FIELD_NUMBER = 11;
        public static final int PAGE_LINE_DATA_MAX_FIELD_NUMBER = 2;
        public static final int PAGE_LINE_NUM_MAX_FIELD_NUMBER = 1;
        private static volatile Parser<cur_page_status_msg> PARSER = null;
        public static final int SUPPORT_PAGE_LAYOUT_FIELD_NUMBER = 6;
        public static final int UNSUPPORT_DATA_FIELD_NUMBER = 4;
        private int bitField0_;
        private int dataPageNumMax_;
        private int lcdType_;
        private int mainPageOpen_;
        private int pageLineDataMax_;
        private int pageLineNumMax_;
        private Internal.IntList unsupportData_ = emptyIntList();
        private Internal.IntList supportPageLayout_ = emptyIntList();

        private cur_page_status_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public boolean hasPageLineNumMax() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getPageLineNumMax() {
            return this.pageLineNumMax_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageLineNumMax(int i) {
            this.bitField0_ |= 1;
            this.pageLineNumMax_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageLineNumMax() {
            this.bitField0_ &= -2;
            this.pageLineNumMax_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public boolean hasPageLineDataMax() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getPageLineDataMax() {
            return this.pageLineDataMax_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageLineDataMax(int i) {
            this.bitField0_ |= 2;
            this.pageLineDataMax_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageLineDataMax() {
            this.bitField0_ &= -3;
            this.pageLineDataMax_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public boolean hasDataPageNumMax() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getDataPageNumMax() {
            return this.dataPageNumMax_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDataPageNumMax(int i) {
            this.bitField0_ |= 4;
            this.dataPageNumMax_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDataPageNumMax() {
            this.bitField0_ &= -5;
            this.dataPageNumMax_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public List<Integer> getUnsupportDataList() {
            return this.unsupportData_;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getUnsupportDataCount() {
            return this.unsupportData_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getUnsupportData(int i) {
            return this.unsupportData_.getInt(i);
        }

        private void ensureUnsupportDataIsMutable() {
            Internal.IntList intList = this.unsupportData_;
            if (intList.isModifiable()) {
                return;
            }
            this.unsupportData_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnsupportData(int i, int i2) {
            ensureUnsupportDataIsMutable();
            this.unsupportData_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUnsupportData(int i) {
            ensureUnsupportDataIsMutable();
            this.unsupportData_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllUnsupportData(Iterable<? extends Integer> iterable) {
            ensureUnsupportDataIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.unsupportData_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnsupportData() {
            this.unsupportData_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public boolean hasLcdType() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public LCD_TYPE getLcdType() {
            LCD_TYPE lcd_typeForNumber = LCD_TYPE.forNumber(this.lcdType_);
            return lcd_typeForNumber == null ? LCD_TYPE.INVALID : lcd_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLcdType(LCD_TYPE lcd_type) {
            this.lcdType_ = lcd_type.getNumber();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLcdType() {
            this.bitField0_ &= -9;
            this.lcdType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public List<Integer> getSupportPageLayoutList() {
            return this.supportPageLayout_;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getSupportPageLayoutCount() {
            return this.supportPageLayout_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getSupportPageLayout(int i) {
            return this.supportPageLayout_.getInt(i);
        }

        private void ensureSupportPageLayoutIsMutable() {
            Internal.IntList intList = this.supportPageLayout_;
            if (intList.isModifiable()) {
                return;
            }
            this.supportPageLayout_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportPageLayout(int i, int i2) {
            ensureSupportPageLayoutIsMutable();
            this.supportPageLayout_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportPageLayout(int i) {
            ensureSupportPageLayoutIsMutable();
            this.supportPageLayout_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportPageLayout(Iterable<? extends Integer> iterable) {
            ensureSupportPageLayoutIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.supportPageLayout_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportPageLayout() {
            this.supportPageLayout_ = emptyIntList();
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public boolean hasMainPageOpen() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
        public int getMainPageOpen() {
            return this.mainPageOpen_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMainPageOpen(int i) {
            this.bitField0_ |= 16;
            this.mainPageOpen_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMainPageOpen() {
            this.bitField0_ &= -17;
            this.mainPageOpen_ = 0;
        }

        public static cur_page_status_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static cur_page_status_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static cur_page_status_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static cur_page_status_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static cur_page_status_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static cur_page_status_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static cur_page_status_msg parseFrom(InputStream inputStream) throws IOException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static cur_page_status_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static cur_page_status_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (cur_page_status_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static cur_page_status_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (cur_page_status_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static cur_page_status_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static cur_page_status_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (cur_page_status_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(cur_page_status_msg cur_page_status_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(cur_page_status_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<cur_page_status_msg, Builder> implements cur_page_status_msgOrBuilder {
            private Builder() {
                super(cur_page_status_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public boolean hasPageLineNumMax() {
                return ((cur_page_status_msg) this.instance).hasPageLineNumMax();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getPageLineNumMax() {
                return ((cur_page_status_msg) this.instance).getPageLineNumMax();
            }

            public Builder setPageLineNumMax(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setPageLineNumMax(i);
                return this;
            }

            public Builder clearPageLineNumMax() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearPageLineNumMax();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public boolean hasPageLineDataMax() {
                return ((cur_page_status_msg) this.instance).hasPageLineDataMax();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getPageLineDataMax() {
                return ((cur_page_status_msg) this.instance).getPageLineDataMax();
            }

            public Builder setPageLineDataMax(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setPageLineDataMax(i);
                return this;
            }

            public Builder clearPageLineDataMax() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearPageLineDataMax();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public boolean hasDataPageNumMax() {
                return ((cur_page_status_msg) this.instance).hasDataPageNumMax();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getDataPageNumMax() {
                return ((cur_page_status_msg) this.instance).getDataPageNumMax();
            }

            public Builder setDataPageNumMax(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setDataPageNumMax(i);
                return this;
            }

            public Builder clearDataPageNumMax() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearDataPageNumMax();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public List<Integer> getUnsupportDataList() {
                return Collections.unmodifiableList(((cur_page_status_msg) this.instance).getUnsupportDataList());
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getUnsupportDataCount() {
                return ((cur_page_status_msg) this.instance).getUnsupportDataCount();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getUnsupportData(int i) {
                return ((cur_page_status_msg) this.instance).getUnsupportData(i);
            }

            public Builder setUnsupportData(int i, int i2) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setUnsupportData(i, i2);
                return this;
            }

            public Builder addUnsupportData(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).addUnsupportData(i);
                return this;
            }

            public Builder addAllUnsupportData(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).addAllUnsupportData(iterable);
                return this;
            }

            public Builder clearUnsupportData() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearUnsupportData();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public boolean hasLcdType() {
                return ((cur_page_status_msg) this.instance).hasLcdType();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public LCD_TYPE getLcdType() {
                return ((cur_page_status_msg) this.instance).getLcdType();
            }

            public Builder setLcdType(LCD_TYPE lcd_type) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setLcdType(lcd_type);
                return this;
            }

            public Builder clearLcdType() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearLcdType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public List<Integer> getSupportPageLayoutList() {
                return Collections.unmodifiableList(((cur_page_status_msg) this.instance).getSupportPageLayoutList());
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getSupportPageLayoutCount() {
                return ((cur_page_status_msg) this.instance).getSupportPageLayoutCount();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getSupportPageLayout(int i) {
                return ((cur_page_status_msg) this.instance).getSupportPageLayout(i);
            }

            public Builder setSupportPageLayout(int i, int i2) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setSupportPageLayout(i, i2);
                return this;
            }

            public Builder addSupportPageLayout(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).addSupportPageLayout(i);
                return this;
            }

            public Builder addAllSupportPageLayout(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).addAllSupportPageLayout(iterable);
                return this;
            }

            public Builder clearSupportPageLayout() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearSupportPageLayout();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public boolean hasMainPageOpen() {
                return ((cur_page_status_msg) this.instance).hasMainPageOpen();
            }

            @Override // com.igpsport.blelib.bean.Config.cur_page_status_msgOrBuilder
            public int getMainPageOpen() {
                return ((cur_page_status_msg) this.instance).getMainPageOpen();
            }

            public Builder setMainPageOpen(int i) {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).setMainPageOpen(i);
                return this;
            }

            public Builder clearMainPageOpen() {
                copyOnWrite();
                ((cur_page_status_msg) this.instance).clearMainPageOpen();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new cur_page_status_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u000b\u0007\u0000\u0002\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004\u001d\u0005ဌ\u0003\u0006\u001d\u000bဋ\u0004", new Object[]{"bitField0_", "pageLineNumMax_", "pageLineDataMax_", "dataPageNumMax_", "unsupportData_", "lcdType_", LCD_TYPE.internalGetVerifier(), "supportPageLayout_", "mainPageOpen_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<cur_page_status_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (cur_page_status_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            cur_page_status_msg cur_page_status_msgVar = new cur_page_status_msg();
            DEFAULT_INSTANCE = cur_page_status_msgVar;
            GeneratedMessageLite.registerDefaultInstance(cur_page_status_msg.class, cur_page_status_msgVar);
        }

        public static cur_page_status_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<cur_page_status_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class bike_msg extends GeneratedMessageLite<bike_msg, Builder> implements bike_msgOrBuilder {
        public static final int AUTO_DIA_FIELD_NUMBER = 6;
        public static final int BIKE_INDEX_FIELD_NUMBER = 1;
        public static final int BIKE_NAME_FIELD_NUMBER = 2;
        public static final int BIKE_STATUS_FIELD_NUMBER = 7;
        public static final int BIKE_WEIGTH_FIELD_NUMBER = 3;
        private static final bike_msg DEFAULT_INSTANCE;
        public static final int ODOMETER_FIELD_NUMBER = 5;
        private static volatile Parser<bike_msg> PARSER = null;
        public static final int WHEEL_DIA_FIELD_NUMBER = 4;
        private int autoDia_;
        private int bikeIndex_;
        private String bikeName_ = "";
        private int bikeStatus_;
        private int bikeWeigth_;
        private int bitField0_;
        private long odometer_;
        private int wheelDia_;

        private bike_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasBikeIndex() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public int getBikeIndex() {
            return this.bikeIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeIndex(int i) {
            this.bitField0_ |= 1;
            this.bikeIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBikeIndex() {
            this.bitField0_ &= -2;
            this.bikeIndex_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasBikeName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public String getBikeName() {
            return this.bikeName_;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public ByteString getBikeNameBytes() {
            return ByteString.copyFromUtf8(this.bikeName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeName(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.bikeName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBikeName() {
            this.bitField0_ &= -3;
            this.bikeName_ = getDefaultInstance().getBikeName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeNameBytes(ByteString byteString) {
            this.bikeName_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasBikeWeigth() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public int getBikeWeigth() {
            return this.bikeWeigth_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeWeigth(int i) {
            this.bitField0_ |= 4;
            this.bikeWeigth_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBikeWeigth() {
            this.bitField0_ &= -5;
            this.bikeWeigth_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasWheelDia() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public int getWheelDia() {
            return this.wheelDia_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWheelDia(int i) {
            this.bitField0_ |= 8;
            this.wheelDia_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWheelDia() {
            this.bitField0_ &= -9;
            this.wheelDia_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasOdometer() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public long getOdometer() {
            return this.odometer_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOdometer(long j) {
            this.bitField0_ |= 16;
            this.odometer_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOdometer() {
            this.bitField0_ &= -17;
            this.odometer_ = 0L;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasAutoDia() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public int getAutoDia() {
            return this.autoDia_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoDia(int i) {
            this.bitField0_ |= 32;
            this.autoDia_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoDia() {
            this.bitField0_ &= -33;
            this.autoDia_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public boolean hasBikeStatus() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
        public int getBikeStatus() {
            return this.bikeStatus_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeStatus(int i) {
            this.bitField0_ |= 64;
            this.bikeStatus_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBikeStatus() {
            this.bitField0_ &= -65;
            this.bikeStatus_ = 0;
        }

        public static bike_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static bike_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static bike_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static bike_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static bike_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static bike_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static bike_msg parseFrom(InputStream inputStream) throws IOException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static bike_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static bike_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (bike_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static bike_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (bike_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static bike_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static bike_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (bike_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(bike_msg bike_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(bike_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<bike_msg, Builder> implements bike_msgOrBuilder {
            private Builder() {
                super(bike_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasBikeIndex() {
                return ((bike_msg) this.instance).hasBikeIndex();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public int getBikeIndex() {
                return ((bike_msg) this.instance).getBikeIndex();
            }

            public Builder setBikeIndex(int i) {
                copyOnWrite();
                ((bike_msg) this.instance).setBikeIndex(i);
                return this;
            }

            public Builder clearBikeIndex() {
                copyOnWrite();
                ((bike_msg) this.instance).clearBikeIndex();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasBikeName() {
                return ((bike_msg) this.instance).hasBikeName();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public String getBikeName() {
                return ((bike_msg) this.instance).getBikeName();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public ByteString getBikeNameBytes() {
                return ((bike_msg) this.instance).getBikeNameBytes();
            }

            public Builder setBikeName(String str) {
                copyOnWrite();
                ((bike_msg) this.instance).setBikeName(str);
                return this;
            }

            public Builder clearBikeName() {
                copyOnWrite();
                ((bike_msg) this.instance).clearBikeName();
                return this;
            }

            public Builder setBikeNameBytes(ByteString byteString) {
                copyOnWrite();
                ((bike_msg) this.instance).setBikeNameBytes(byteString);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasBikeWeigth() {
                return ((bike_msg) this.instance).hasBikeWeigth();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public int getBikeWeigth() {
                return ((bike_msg) this.instance).getBikeWeigth();
            }

            public Builder setBikeWeigth(int i) {
                copyOnWrite();
                ((bike_msg) this.instance).setBikeWeigth(i);
                return this;
            }

            public Builder clearBikeWeigth() {
                copyOnWrite();
                ((bike_msg) this.instance).clearBikeWeigth();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasWheelDia() {
                return ((bike_msg) this.instance).hasWheelDia();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public int getWheelDia() {
                return ((bike_msg) this.instance).getWheelDia();
            }

            public Builder setWheelDia(int i) {
                copyOnWrite();
                ((bike_msg) this.instance).setWheelDia(i);
                return this;
            }

            public Builder clearWheelDia() {
                copyOnWrite();
                ((bike_msg) this.instance).clearWheelDia();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasOdometer() {
                return ((bike_msg) this.instance).hasOdometer();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public long getOdometer() {
                return ((bike_msg) this.instance).getOdometer();
            }

            public Builder setOdometer(long j) {
                copyOnWrite();
                ((bike_msg) this.instance).setOdometer(j);
                return this;
            }

            public Builder clearOdometer() {
                copyOnWrite();
                ((bike_msg) this.instance).clearOdometer();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasAutoDia() {
                return ((bike_msg) this.instance).hasAutoDia();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public int getAutoDia() {
                return ((bike_msg) this.instance).getAutoDia();
            }

            public Builder setAutoDia(int i) {
                copyOnWrite();
                ((bike_msg) this.instance).setAutoDia(i);
                return this;
            }

            public Builder clearAutoDia() {
                copyOnWrite();
                ((bike_msg) this.instance).clearAutoDia();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public boolean hasBikeStatus() {
                return ((bike_msg) this.instance).hasBikeStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.bike_msgOrBuilder
            public int getBikeStatus() {
                return ((bike_msg) this.instance).getBikeStatus();
            }

            public Builder setBikeStatus(int i) {
                copyOnWrite();
                ((bike_msg) this.instance).setBikeStatus(i);
                return this;
            }

            public Builder clearBikeStatus() {
                copyOnWrite();
                ((bike_msg) this.instance).clearBikeStatus();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new bike_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဈ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005ဃ\u0004\u0006ဋ\u0005\u0007ဋ\u0006", new Object[]{"bitField0_", "bikeIndex_", "bikeName_", "bikeWeigth_", "wheelDia_", "odometer_", "autoDia_", "bikeStatus_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<bike_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (bike_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            bike_msg bike_msgVar = new bike_msg();
            DEFAULT_INSTANCE = bike_msgVar;
            GeneratedMessageLite.registerDefaultInstance(bike_msg.class, bike_msgVar);
        }

        public static bike_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<bike_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class language_msg extends GeneratedMessageLite<language_msg, Builder> implements language_msgOrBuilder {
        public static final int CUR_LANGUAGE_FIELD_NUMBER = 1;
        private static final language_msg DEFAULT_INSTANCE;
        private static volatile Parser<language_msg> PARSER = null;
        public static final int SUPPORTED_LANGUAGE_FIELD_NUMBER = 2;
        private static final Internal.ListAdapter.Converter<Integer, LANGUAGE_TYPE> supportedLanguage_converter_ = new Internal.ListAdapter.Converter<Integer, LANGUAGE_TYPE>() { // from class: com.igpsport.blelib.bean.Config.language_msg.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public LANGUAGE_TYPE convert(Integer num) {
                LANGUAGE_TYPE language_typeForNumber = LANGUAGE_TYPE.forNumber(num.intValue());
                return language_typeForNumber == null ? LANGUAGE_TYPE.enum_LANGUAGE_TYPE_INVALID : language_typeForNumber;
            }
        };
        private int bitField0_;
        private int curLanguage_;
        private byte memoizedIsInitialized = 2;
        private Internal.IntList supportedLanguage_ = emptyIntList();

        private language_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
        public boolean hasCurLanguage() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
        public LANGUAGE_TYPE getCurLanguage() {
            LANGUAGE_TYPE language_typeForNumber = LANGUAGE_TYPE.forNumber(this.curLanguage_);
            return language_typeForNumber == null ? LANGUAGE_TYPE.enum_LANGUAGE_TYPE_INVALID : language_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurLanguage(LANGUAGE_TYPE language_type) {
            this.curLanguage_ = language_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurLanguage() {
            this.bitField0_ &= -2;
            this.curLanguage_ = 0;
        }

        static {
            language_msg language_msgVar = new language_msg();
            DEFAULT_INSTANCE = language_msgVar;
            GeneratedMessageLite.registerDefaultInstance(language_msg.class, language_msgVar);
        }

        @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
        public List<LANGUAGE_TYPE> getSupportedLanguageList() {
            return new Internal.ListAdapter(this.supportedLanguage_, supportedLanguage_converter_);
        }

        @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
        public int getSupportedLanguageCount() {
            return this.supportedLanguage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
        public LANGUAGE_TYPE getSupportedLanguage(int i) {
            return supportedLanguage_converter_.convert(Integer.valueOf(this.supportedLanguage_.getInt(i)));
        }

        private void ensureSupportedLanguageIsMutable() {
            Internal.IntList intList = this.supportedLanguage_;
            if (intList.isModifiable()) {
                return;
            }
            this.supportedLanguage_ = GeneratedMessageLite.mutableCopy(intList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedLanguage(int i, LANGUAGE_TYPE language_type) {
            language_type.getClass();
            ensureSupportedLanguageIsMutable();
            this.supportedLanguage_.setInt(i, language_type.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedLanguage(LANGUAGE_TYPE language_type) {
            language_type.getClass();
            ensureSupportedLanguageIsMutable();
            this.supportedLanguage_.addInt(language_type.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedLanguage(Iterable<? extends LANGUAGE_TYPE> iterable) {
            ensureSupportedLanguageIsMutable();
            Iterator<? extends LANGUAGE_TYPE> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.supportedLanguage_.addInt(it2.next().getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedLanguage() {
            this.supportedLanguage_ = emptyIntList();
        }

        public static language_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static language_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static language_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static language_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static language_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static language_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static language_msg parseFrom(InputStream inputStream) throws IOException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static language_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static language_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (language_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static language_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (language_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static language_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static language_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (language_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(language_msg language_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(language_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<language_msg, Builder> implements language_msgOrBuilder {
            private Builder() {
                super(language_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
            public boolean hasCurLanguage() {
                return ((language_msg) this.instance).hasCurLanguage();
            }

            @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
            public LANGUAGE_TYPE getCurLanguage() {
                return ((language_msg) this.instance).getCurLanguage();
            }

            public Builder setCurLanguage(LANGUAGE_TYPE language_type) {
                copyOnWrite();
                ((language_msg) this.instance).setCurLanguage(language_type);
                return this;
            }

            public Builder clearCurLanguage() {
                copyOnWrite();
                ((language_msg) this.instance).clearCurLanguage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
            public List<LANGUAGE_TYPE> getSupportedLanguageList() {
                return ((language_msg) this.instance).getSupportedLanguageList();
            }

            @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
            public int getSupportedLanguageCount() {
                return ((language_msg) this.instance).getSupportedLanguageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.language_msgOrBuilder
            public LANGUAGE_TYPE getSupportedLanguage(int i) {
                return ((language_msg) this.instance).getSupportedLanguage(i);
            }

            public Builder setSupportedLanguage(int i, LANGUAGE_TYPE language_type) {
                copyOnWrite();
                ((language_msg) this.instance).setSupportedLanguage(i, language_type);
                return this;
            }

            public Builder addSupportedLanguage(LANGUAGE_TYPE language_type) {
                copyOnWrite();
                ((language_msg) this.instance).addSupportedLanguage(language_type);
                return this;
            }

            public Builder addAllSupportedLanguage(Iterable<? extends LANGUAGE_TYPE> iterable) {
                copyOnWrite();
                ((language_msg) this.instance).addAllSupportedLanguage(iterable);
                return this;
            }

            public Builder clearSupportedLanguage() {
                copyOnWrite();
                ((language_msg) this.instance).clearSupportedLanguage();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new language_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001ᔌ\u0000\u0002\u001e", new Object[]{"bitField0_", "curLanguage_", LANGUAGE_TYPE.internalGetVerifier(), "supportedLanguage_", LANGUAGE_TYPE.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<language_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (language_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static language_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<language_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class backlight_msg extends GeneratedMessageLite<backlight_msg, Builder> implements backlight_msgOrBuilder {
        public static final int BACKLIGHT_AUTO_FIELD_NUMBER = 3;
        public static final int BACKLIGHT_DAY_PERCENT_FIELD_NUMBER = 4;
        public static final int BACKLIGHT_NIGHT_ON_FIELD_NUMBER = 1;
        public static final int BACKLIGHT_NIGHT_PERCENT_FIELD_NUMBER = 5;
        public static final int BACKLING_TIME_FIELD_NUMBER = 2;
        private static final backlight_msg DEFAULT_INSTANCE;
        private static volatile Parser<backlight_msg> PARSER;
        private int backlightAuto_;
        private int backlightDayPercent_;
        private int backlightNightOn_;
        private int backlightNightPercent_;
        private int backlingTime_;
        private int bitField0_;

        private backlight_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public boolean hasBacklightNightOn() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public int getBacklightNightOn() {
            return this.backlightNightOn_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklightNightOn(int i) {
            this.bitField0_ |= 1;
            this.backlightNightOn_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklightNightOn() {
            this.bitField0_ &= -2;
            this.backlightNightOn_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public boolean hasBacklingTime() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public int getBacklingTime() {
            return this.backlingTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklingTime(int i) {
            this.bitField0_ |= 2;
            this.backlingTime_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklingTime() {
            this.bitField0_ &= -3;
            this.backlingTime_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public boolean hasBacklightAuto() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public int getBacklightAuto() {
            return this.backlightAuto_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklightAuto(int i) {
            this.bitField0_ |= 4;
            this.backlightAuto_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklightAuto() {
            this.bitField0_ &= -5;
            this.backlightAuto_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public boolean hasBacklightDayPercent() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public int getBacklightDayPercent() {
            return this.backlightDayPercent_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklightDayPercent(int i) {
            this.bitField0_ |= 8;
            this.backlightDayPercent_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklightDayPercent() {
            this.bitField0_ &= -9;
            this.backlightDayPercent_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public boolean hasBacklightNightPercent() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
        public int getBacklightNightPercent() {
            return this.backlightNightPercent_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklightNightPercent(int i) {
            this.bitField0_ |= 16;
            this.backlightNightPercent_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklightNightPercent() {
            this.bitField0_ &= -17;
            this.backlightNightPercent_ = 0;
        }

        public static backlight_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static backlight_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static backlight_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static backlight_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static backlight_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static backlight_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static backlight_msg parseFrom(InputStream inputStream) throws IOException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static backlight_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static backlight_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (backlight_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static backlight_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (backlight_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static backlight_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static backlight_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (backlight_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(backlight_msg backlight_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(backlight_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<backlight_msg, Builder> implements backlight_msgOrBuilder {
            private Builder() {
                super(backlight_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public boolean hasBacklightNightOn() {
                return ((backlight_msg) this.instance).hasBacklightNightOn();
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public int getBacklightNightOn() {
                return ((backlight_msg) this.instance).getBacklightNightOn();
            }

            public Builder setBacklightNightOn(int i) {
                copyOnWrite();
                ((backlight_msg) this.instance).setBacklightNightOn(i);
                return this;
            }

            public Builder clearBacklightNightOn() {
                copyOnWrite();
                ((backlight_msg) this.instance).clearBacklightNightOn();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public boolean hasBacklingTime() {
                return ((backlight_msg) this.instance).hasBacklingTime();
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public int getBacklingTime() {
                return ((backlight_msg) this.instance).getBacklingTime();
            }

            public Builder setBacklingTime(int i) {
                copyOnWrite();
                ((backlight_msg) this.instance).setBacklingTime(i);
                return this;
            }

            public Builder clearBacklingTime() {
                copyOnWrite();
                ((backlight_msg) this.instance).clearBacklingTime();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public boolean hasBacklightAuto() {
                return ((backlight_msg) this.instance).hasBacklightAuto();
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public int getBacklightAuto() {
                return ((backlight_msg) this.instance).getBacklightAuto();
            }

            public Builder setBacklightAuto(int i) {
                copyOnWrite();
                ((backlight_msg) this.instance).setBacklightAuto(i);
                return this;
            }

            public Builder clearBacklightAuto() {
                copyOnWrite();
                ((backlight_msg) this.instance).clearBacklightAuto();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public boolean hasBacklightDayPercent() {
                return ((backlight_msg) this.instance).hasBacklightDayPercent();
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public int getBacklightDayPercent() {
                return ((backlight_msg) this.instance).getBacklightDayPercent();
            }

            public Builder setBacklightDayPercent(int i) {
                copyOnWrite();
                ((backlight_msg) this.instance).setBacklightDayPercent(i);
                return this;
            }

            public Builder clearBacklightDayPercent() {
                copyOnWrite();
                ((backlight_msg) this.instance).clearBacklightDayPercent();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public boolean hasBacklightNightPercent() {
                return ((backlight_msg) this.instance).hasBacklightNightPercent();
            }

            @Override // com.igpsport.blelib.bean.Config.backlight_msgOrBuilder
            public int getBacklightNightPercent() {
                return ((backlight_msg) this.instance).getBacklightNightPercent();
            }

            public Builder setBacklightNightPercent(int i) {
                copyOnWrite();
                ((backlight_msg) this.instance).setBacklightNightPercent(i);
                return this;
            }

            public Builder clearBacklightNightPercent() {
                copyOnWrite();
                ((backlight_msg) this.instance).clearBacklightNightPercent();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new backlight_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005ဋ\u0004", new Object[]{"bitField0_", "backlightNightOn_", "backlingTime_", "backlightAuto_", "backlightDayPercent_", "backlightNightPercent_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<backlight_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (backlight_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            backlight_msg backlight_msgVar = new backlight_msg();
            DEFAULT_INSTANCE = backlight_msgVar;
            GeneratedMessageLite.registerDefaultInstance(backlight_msg.class, backlight_msgVar);
        }

        public static backlight_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<backlight_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class data_msg extends GeneratedMessageLite<data_msg, Builder> implements data_msgOrBuilder {
        public static final int DATA_TYPE_FIELD_NUMBER = 1;
        private static final data_msg DEFAULT_INSTANCE;
        private static volatile Parser<data_msg> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int dataType_;
        private int status_;

        private data_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
        public boolean hasDataType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
        public DATA_TYPE getDataType() {
            DATA_TYPE data_typeForNumber = DATA_TYPE.forNumber(this.dataType_);
            return data_typeForNumber == null ? DATA_TYPE.enum_DATA_TYPE_INVALID : data_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDataType(DATA_TYPE data_type) {
            this.dataType_ = data_type.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDataType() {
            this.bitField0_ &= -2;
            this.dataType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
        public int getStatus() {
            return this.status_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(int i) {
            this.bitField0_ |= 2;
            this.status_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -3;
            this.status_ = 0;
        }

        public static data_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static data_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static data_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static data_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static data_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static data_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static data_msg parseFrom(InputStream inputStream) throws IOException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static data_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static data_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static data_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (data_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static data_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static data_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (data_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(data_msg data_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(data_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<data_msg, Builder> implements data_msgOrBuilder {
            private Builder() {
                super(data_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
            public boolean hasDataType() {
                return ((data_msg) this.instance).hasDataType();
            }

            @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
            public DATA_TYPE getDataType() {
                return ((data_msg) this.instance).getDataType();
            }

            public Builder setDataType(DATA_TYPE data_type) {
                copyOnWrite();
                ((data_msg) this.instance).setDataType(data_type);
                return this;
            }

            public Builder clearDataType() {
                copyOnWrite();
                ((data_msg) this.instance).clearDataType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
            public boolean hasStatus() {
                return ((data_msg) this.instance).hasStatus();
            }

            @Override // com.igpsport.blelib.bean.Config.data_msgOrBuilder
            public int getStatus() {
                return ((data_msg) this.instance).getStatus();
            }

            public Builder setStatus(int i) {
                copyOnWrite();
                ((data_msg) this.instance).setStatus(i);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((data_msg) this.instance).clearStatus();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new data_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "dataType_", DATA_TYPE.internalGetVerifier(), "status_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<data_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (data_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            data_msg data_msgVar = new data_msg();
            DEFAULT_INSTANCE = data_msgVar;
            GeneratedMessageLite.registerDefaultInstance(data_msg.class, data_msgVar);
        }

        public static data_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<data_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class password_msg extends GeneratedMessageLite<password_msg, Builder> implements password_msgOrBuilder {
        private static final password_msg DEFAULT_INSTANCE;
        private static volatile Parser<password_msg> PARSER = null;
        public static final int PASSWORD_FIELD_NUMBER = 1;
        private int bitField0_;
        private String password_ = "";

        private password_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
        public boolean hasPassword() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
        public String getPassword() {
            return this.password_;
        }

        @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
        public ByteString getPasswordBytes() {
            return ByteString.copyFromUtf8(this.password_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPassword(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.password_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPassword() {
            this.bitField0_ &= -2;
            this.password_ = getDefaultInstance().getPassword();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPasswordBytes(ByteString byteString) {
            this.password_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        public static password_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static password_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static password_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static password_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static password_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static password_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static password_msg parseFrom(InputStream inputStream) throws IOException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static password_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static password_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (password_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static password_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (password_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static password_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static password_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (password_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(password_msg password_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(password_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<password_msg, Builder> implements password_msgOrBuilder {
            private Builder() {
                super(password_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
            public boolean hasPassword() {
                return ((password_msg) this.instance).hasPassword();
            }

            @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
            public String getPassword() {
                return ((password_msg) this.instance).getPassword();
            }

            @Override // com.igpsport.blelib.bean.Config.password_msgOrBuilder
            public ByteString getPasswordBytes() {
                return ((password_msg) this.instance).getPasswordBytes();
            }

            public Builder setPassword(String str) {
                copyOnWrite();
                ((password_msg) this.instance).setPassword(str);
                return this;
            }

            public Builder clearPassword() {
                copyOnWrite();
                ((password_msg) this.instance).clearPassword();
                return this;
            }

            public Builder setPasswordBytes(ByteString byteString) {
                copyOnWrite();
                ((password_msg) this.instance).setPasswordBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new password_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "password_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<password_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (password_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            password_msg password_msgVar = new password_msg();
            DEFAULT_INSTANCE = password_msgVar;
            GeneratedMessageLite.registerDefaultInstance(password_msg.class, password_msgVar);
        }

        public static password_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<password_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class accident_detection_msg extends GeneratedMessageLite<accident_detection_msg, Builder> implements accident_detection_msgOrBuilder {
        public static final int ACCIDENT_DETECTION_MODE_INDEX_FIELD_NUMBER = 2;
        public static final int ACCIDENT_DETECTION_OPEN_FIELD_NUMBER = 1;
        private static final accident_detection_msg DEFAULT_INSTANCE;
        private static volatile Parser<accident_detection_msg> PARSER;
        private int accidentDetectionModeIndex_;
        private int accidentDetectionOpen_;
        private int bitField0_;

        private accident_detection_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
        public boolean hasAccidentDetectionOpen() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
        public int getAccidentDetectionOpen() {
            return this.accidentDetectionOpen_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccidentDetectionOpen(int i) {
            this.bitField0_ |= 1;
            this.accidentDetectionOpen_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccidentDetectionOpen() {
            this.bitField0_ &= -2;
            this.accidentDetectionOpen_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
        public boolean hasAccidentDetectionModeIndex() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
        public int getAccidentDetectionModeIndex() {
            return this.accidentDetectionModeIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccidentDetectionModeIndex(int i) {
            this.bitField0_ |= 2;
            this.accidentDetectionModeIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccidentDetectionModeIndex() {
            this.bitField0_ &= -3;
            this.accidentDetectionModeIndex_ = 0;
        }

        public static accident_detection_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static accident_detection_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static accident_detection_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static accident_detection_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static accident_detection_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static accident_detection_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static accident_detection_msg parseFrom(InputStream inputStream) throws IOException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static accident_detection_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static accident_detection_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (accident_detection_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static accident_detection_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (accident_detection_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static accident_detection_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static accident_detection_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (accident_detection_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(accident_detection_msg accident_detection_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(accident_detection_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<accident_detection_msg, Builder> implements accident_detection_msgOrBuilder {
            private Builder() {
                super(accident_detection_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
            public boolean hasAccidentDetectionOpen() {
                return ((accident_detection_msg) this.instance).hasAccidentDetectionOpen();
            }

            @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
            public int getAccidentDetectionOpen() {
                return ((accident_detection_msg) this.instance).getAccidentDetectionOpen();
            }

            public Builder setAccidentDetectionOpen(int i) {
                copyOnWrite();
                ((accident_detection_msg) this.instance).setAccidentDetectionOpen(i);
                return this;
            }

            public Builder clearAccidentDetectionOpen() {
                copyOnWrite();
                ((accident_detection_msg) this.instance).clearAccidentDetectionOpen();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
            public boolean hasAccidentDetectionModeIndex() {
                return ((accident_detection_msg) this.instance).hasAccidentDetectionModeIndex();
            }

            @Override // com.igpsport.blelib.bean.Config.accident_detection_msgOrBuilder
            public int getAccidentDetectionModeIndex() {
                return ((accident_detection_msg) this.instance).getAccidentDetectionModeIndex();
            }

            public Builder setAccidentDetectionModeIndex(int i) {
                copyOnWrite();
                ((accident_detection_msg) this.instance).setAccidentDetectionModeIndex(i);
                return this;
            }

            public Builder clearAccidentDetectionModeIndex() {
                copyOnWrite();
                ((accident_detection_msg) this.instance).clearAccidentDetectionModeIndex();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()];
            switch (i) {
                case 1:
                    return new accident_detection_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "accidentDetectionOpen_", "accidentDetectionModeIndex_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<accident_detection_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (accident_detection_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            accident_detection_msg accident_detection_msgVar = new accident_detection_msg();
            DEFAULT_INSTANCE = accident_detection_msgVar;
            GeneratedMessageLite.registerDefaultInstance(accident_detection_msg.class, accident_detection_msgVar);
        }

        public static accident_detection_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<accident_detection_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class config_msg extends GeneratedMessageLite<config_msg, Builder> implements config_msgOrBuilder {
        public static final int ACCIDENT_DETECTION_MESSAGE_FIELD_NUMBER = 30;
        public static final int ALARM_MESSAGE_FIELD_NUMBER = 21;
        public static final int ALTITUDE_FIELD_NUMBER = 9;
        public static final int AUTO_SET_MESSAGE_FIELD_NUMBER = 23;
        public static final int BACKLIGHT_MESSAGE_FIELD_NUMBER = 13;
        public static final int BIKE_MESSAGE_FIELD_NUMBER = 6;
        public static final int CONFIG_OPERATE_TYPE_FIELD_NUMBER = 3;
        public static final int CONFIG_SEVICE_TYPE_FIELD_NUMBER = 2;
        public static final int CUR_OPERATE_MODE_FIELD_NUMBER = 19;
        public static final int DATA_MESSAGE_FIELD_NUMBER = 26;
        private static final config_msg DEFAULT_INSTANCE;
        public static final int KEY_SET_MESSAGE_FIELD_NUMBER = 24;
        public static final int LANGUAGE_MESSAGE_FIELD_NUMBER = 12;
        public static final int LAP_MESSAGE_FIELD_NUMBER = 22;
        public static final int MODE_CONFIG_MESSAGE_FIELD_NUMBER = 27;
        public static final int MODE_MESSAGE_FIELD_NUMBER = 20;
        public static final int PAGE_MESSAGE_FIELD_NUMBER = 5;
        public static final int PAGE_STATUS_MESSAGE_FIELD_NUMBER = 8;
        private static volatile Parser<config_msg> PARSER = null;
        public static final int PASSWORD_MESSAGE_FIELD_NUMBER = 29;
        public static final int SERVICE_TYPE_FIELD_NUMBER = 1;
        public static final int SOUND_SET_MESSAGE_FIELD_NUMBER = 25;
        public static final int TIME_SYSTEM_FIELD_NUMBER = 28;
        public static final int UNIT_MESSAGE_FIELD_NUMBER = 7;
        public static final int USER_DATA_MESSAGE_FIELD_NUMBER = 4;
        private accident_detection_msg accidentDetectionMessage_;
        private int altitude_;
        private backlight_msg backlightMessage_;
        private int bitField0_;
        private int configOperateType_;
        private int configSeviceType_;
        private int curOperateMode_;
        private language_msg languageMessage_;
        private mode_config_msg modeConfigMessage_;
        private cur_page_status_msg pageStatusMessage_;
        private password_msg passwordMessage_;
        private int timeSystem_;
        private user_data_msg userDataMessage_;
        private byte memoizedIsInitialized = 2;
        private int serviceType_ = 12;
        private Internal.ProtobufList<page_msg> pageMessage_ = emptyProtobufList();
        private Internal.ProtobufList<bike_msg> bikeMessage_ = emptyProtobufList();
        private Internal.ProtobufList<unit_msg> unitMessage_ = emptyProtobufList();
        private Internal.ProtobufList<mode_msg> modeMessage_ = emptyProtobufList();
        private Internal.ProtobufList<alarm_msg> alarmMessage_ = emptyProtobufList();
        private Internal.ProtobufList<lap_msg> lapMessage_ = emptyProtobufList();
        private Internal.ProtobufList<auto_set_msg> autoSetMessage_ = emptyProtobufList();
        private Internal.ProtobufList<key_set_msg> keySetMessage_ = emptyProtobufList();
        private Internal.ProtobufList<sound_set_msg> soundSetMessage_ = emptyProtobufList();
        private Internal.ProtobufList<data_msg> dataMessage_ = emptyProtobufList();

        private config_msg() {
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasServiceType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public Common.service_type_index getServiceType() {
            Common.service_type_index service_type_indexVarForNumber = Common.service_type_index.forNumber(this.serviceType_);
            return service_type_indexVarForNumber == null ? Common.service_type_index.enum_SERVICE_TYPE_INDEX_CONFIG : service_type_indexVarForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setServiceType(Common.service_type_index service_type_indexVar) {
            this.serviceType_ = service_type_indexVar.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearServiceType() {
            this.bitField0_ &= -2;
            this.serviceType_ = 12;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasConfigSeviceType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public CONFIG_SERVICE_TYPE getConfigSeviceType() {
            CONFIG_SERVICE_TYPE config_service_typeForNumber = CONFIG_SERVICE_TYPE.forNumber(this.configSeviceType_);
            return config_service_typeForNumber == null ? CONFIG_SERVICE_TYPE.enum_CONFIG_SERVICE_TYPE_NONE : config_service_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigSeviceType(CONFIG_SERVICE_TYPE config_service_type) {
            this.configSeviceType_ = config_service_type.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigSeviceType() {
            this.bitField0_ &= -3;
            this.configSeviceType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasConfigOperateType() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public CONFIG_OPERATE_TYPE getConfigOperateType() {
            CONFIG_OPERATE_TYPE config_operate_typeForNumber = CONFIG_OPERATE_TYPE.forNumber(this.configOperateType_);
            return config_operate_typeForNumber == null ? CONFIG_OPERATE_TYPE.enum_CONFIG_OPERATE_TYPE_NONE : config_operate_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigOperateType(CONFIG_OPERATE_TYPE config_operate_type) {
            this.configOperateType_ = config_operate_type.getNumber();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigOperateType() {
            this.bitField0_ &= -5;
            this.configOperateType_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasUserDataMessage() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public user_data_msg getUserDataMessage() {
            user_data_msg user_data_msgVar = this.userDataMessage_;
            return user_data_msgVar == null ? user_data_msg.getDefaultInstance() : user_data_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserDataMessage(user_data_msg user_data_msgVar) {
            user_data_msgVar.getClass();
            this.userDataMessage_ = user_data_msgVar;
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUserDataMessage(user_data_msg user_data_msgVar) {
            user_data_msgVar.getClass();
            user_data_msg user_data_msgVar2 = this.userDataMessage_;
            if (user_data_msgVar2 != null && user_data_msgVar2 != user_data_msg.getDefaultInstance()) {
                this.userDataMessage_ = user_data_msg.newBuilder(this.userDataMessage_).mergeFrom(user_data_msgVar).buildPartial();
            } else {
                this.userDataMessage_ = user_data_msgVar;
            }
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserDataMessage() {
            this.userDataMessage_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<page_msg> getPageMessageList() {
            return this.pageMessage_;
        }

        public List<? extends page_msgOrBuilder> getPageMessageOrBuilderList() {
            return this.pageMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getPageMessageCount() {
            return this.pageMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public page_msg getPageMessage(int i) {
            return this.pageMessage_.get(i);
        }

        public page_msgOrBuilder getPageMessageOrBuilder(int i) {
            return this.pageMessage_.get(i);
        }

        private void ensurePageMessageIsMutable() {
            Internal.ProtobufList<page_msg> protobufList = this.pageMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.pageMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageMessage(int i, page_msg page_msgVar) {
            page_msgVar.getClass();
            ensurePageMessageIsMutable();
            this.pageMessage_.set(i, page_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPageMessage(page_msg page_msgVar) {
            page_msgVar.getClass();
            ensurePageMessageIsMutable();
            this.pageMessage_.add(page_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPageMessage(int i, page_msg page_msgVar) {
            page_msgVar.getClass();
            ensurePageMessageIsMutable();
            this.pageMessage_.add(i, page_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPageMessage(Iterable<? extends page_msg> iterable) {
            ensurePageMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.pageMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageMessage() {
            this.pageMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePageMessage(int i) {
            ensurePageMessageIsMutable();
            this.pageMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<bike_msg> getBikeMessageList() {
            return this.bikeMessage_;
        }

        public List<? extends bike_msgOrBuilder> getBikeMessageOrBuilderList() {
            return this.bikeMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getBikeMessageCount() {
            return this.bikeMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public bike_msg getBikeMessage(int i) {
            return this.bikeMessage_.get(i);
        }

        public bike_msgOrBuilder getBikeMessageOrBuilder(int i) {
            return this.bikeMessage_.get(i);
        }

        private void ensureBikeMessageIsMutable() {
            Internal.ProtobufList<bike_msg> protobufList = this.bikeMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.bikeMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBikeMessage(int i, bike_msg bike_msgVar) {
            bike_msgVar.getClass();
            ensureBikeMessageIsMutable();
            this.bikeMessage_.set(i, bike_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBikeMessage(bike_msg bike_msgVar) {
            bike_msgVar.getClass();
            ensureBikeMessageIsMutable();
            this.bikeMessage_.add(bike_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBikeMessage(int i, bike_msg bike_msgVar) {
            bike_msgVar.getClass();
            ensureBikeMessageIsMutable();
            this.bikeMessage_.add(i, bike_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllBikeMessage(Iterable<? extends bike_msg> iterable) {
            ensureBikeMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.bikeMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBikeMessage() {
            this.bikeMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeBikeMessage(int i) {
            ensureBikeMessageIsMutable();
            this.bikeMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<unit_msg> getUnitMessageList() {
            return this.unitMessage_;
        }

        public List<? extends unit_msgOrBuilder> getUnitMessageOrBuilderList() {
            return this.unitMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getUnitMessageCount() {
            return this.unitMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public unit_msg getUnitMessage(int i) {
            return this.unitMessage_.get(i);
        }

        public unit_msgOrBuilder getUnitMessageOrBuilder(int i) {
            return this.unitMessage_.get(i);
        }

        private void ensureUnitMessageIsMutable() {
            Internal.ProtobufList<unit_msg> protobufList = this.unitMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.unitMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnitMessage(int i, unit_msg unit_msgVar) {
            unit_msgVar.getClass();
            ensureUnitMessageIsMutable();
            this.unitMessage_.set(i, unit_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUnitMessage(unit_msg unit_msgVar) {
            unit_msgVar.getClass();
            ensureUnitMessageIsMutable();
            this.unitMessage_.add(unit_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUnitMessage(int i, unit_msg unit_msgVar) {
            unit_msgVar.getClass();
            ensureUnitMessageIsMutable();
            this.unitMessage_.add(i, unit_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllUnitMessage(Iterable<? extends unit_msg> iterable) {
            ensureUnitMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.unitMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnitMessage() {
            this.unitMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeUnitMessage(int i) {
            ensureUnitMessageIsMutable();
            this.unitMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasPageStatusMessage() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public cur_page_status_msg getPageStatusMessage() {
            cur_page_status_msg cur_page_status_msgVar = this.pageStatusMessage_;
            return cur_page_status_msgVar == null ? cur_page_status_msg.getDefaultInstance() : cur_page_status_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPageStatusMessage(cur_page_status_msg cur_page_status_msgVar) {
            cur_page_status_msgVar.getClass();
            this.pageStatusMessage_ = cur_page_status_msgVar;
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePageStatusMessage(cur_page_status_msg cur_page_status_msgVar) {
            cur_page_status_msgVar.getClass();
            cur_page_status_msg cur_page_status_msgVar2 = this.pageStatusMessage_;
            if (cur_page_status_msgVar2 != null && cur_page_status_msgVar2 != cur_page_status_msg.getDefaultInstance()) {
                this.pageStatusMessage_ = cur_page_status_msg.newBuilder(this.pageStatusMessage_).mergeFrom(cur_page_status_msgVar).buildPartial();
            } else {
                this.pageStatusMessage_ = cur_page_status_msgVar;
            }
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPageStatusMessage() {
            this.pageStatusMessage_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasAltitude() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getAltitude() {
            return this.altitude_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAltitude(int i) {
            this.bitField0_ |= 32;
            this.altitude_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAltitude() {
            this.bitField0_ &= -33;
            this.altitude_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasLanguageMessage() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public language_msg getLanguageMessage() {
            language_msg language_msgVar = this.languageMessage_;
            return language_msgVar == null ? language_msg.getDefaultInstance() : language_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLanguageMessage(language_msg language_msgVar) {
            language_msgVar.getClass();
            this.languageMessage_ = language_msgVar;
            this.bitField0_ |= 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLanguageMessage(language_msg language_msgVar) {
            language_msgVar.getClass();
            language_msg language_msgVar2 = this.languageMessage_;
            if (language_msgVar2 != null && language_msgVar2 != language_msg.getDefaultInstance()) {
                this.languageMessage_ = language_msg.newBuilder(this.languageMessage_).mergeFrom(language_msgVar).buildPartial();
            } else {
                this.languageMessage_ = language_msgVar;
            }
            this.bitField0_ |= 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLanguageMessage() {
            this.languageMessage_ = null;
            this.bitField0_ &= -65;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasBacklightMessage() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public backlight_msg getBacklightMessage() {
            backlight_msg backlight_msgVar = this.backlightMessage_;
            return backlight_msgVar == null ? backlight_msg.getDefaultInstance() : backlight_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBacklightMessage(backlight_msg backlight_msgVar) {
            backlight_msgVar.getClass();
            this.backlightMessage_ = backlight_msgVar;
            this.bitField0_ |= 128;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBacklightMessage(backlight_msg backlight_msgVar) {
            backlight_msgVar.getClass();
            backlight_msg backlight_msgVar2 = this.backlightMessage_;
            if (backlight_msgVar2 != null && backlight_msgVar2 != backlight_msg.getDefaultInstance()) {
                this.backlightMessage_ = backlight_msg.newBuilder(this.backlightMessage_).mergeFrom(backlight_msgVar).buildPartial();
            } else {
                this.backlightMessage_ = backlight_msgVar;
            }
            this.bitField0_ |= 128;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBacklightMessage() {
            this.backlightMessage_ = null;
            this.bitField0_ &= -129;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasCurOperateMode() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getCurOperateMode() {
            return this.curOperateMode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurOperateMode(int i) {
            this.bitField0_ |= 256;
            this.curOperateMode_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurOperateMode() {
            this.bitField0_ &= -257;
            this.curOperateMode_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<mode_msg> getModeMessageList() {
            return this.modeMessage_;
        }

        public List<? extends mode_msgOrBuilder> getModeMessageOrBuilderList() {
            return this.modeMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getModeMessageCount() {
            return this.modeMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public mode_msg getModeMessage(int i) {
            return this.modeMessage_.get(i);
        }

        public mode_msgOrBuilder getModeMessageOrBuilder(int i) {
            return this.modeMessage_.get(i);
        }

        private void ensureModeMessageIsMutable() {
            Internal.ProtobufList<mode_msg> protobufList = this.modeMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.modeMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeMessage(int i, mode_msg mode_msgVar) {
            mode_msgVar.getClass();
            ensureModeMessageIsMutable();
            this.modeMessage_.set(i, mode_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addModeMessage(mode_msg mode_msgVar) {
            mode_msgVar.getClass();
            ensureModeMessageIsMutable();
            this.modeMessage_.add(mode_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addModeMessage(int i, mode_msg mode_msgVar) {
            mode_msgVar.getClass();
            ensureModeMessageIsMutable();
            this.modeMessage_.add(i, mode_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllModeMessage(Iterable<? extends mode_msg> iterable) {
            ensureModeMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.modeMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModeMessage() {
            this.modeMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeModeMessage(int i) {
            ensureModeMessageIsMutable();
            this.modeMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<alarm_msg> getAlarmMessageList() {
            return this.alarmMessage_;
        }

        public List<? extends alarm_msgOrBuilder> getAlarmMessageOrBuilderList() {
            return this.alarmMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getAlarmMessageCount() {
            return this.alarmMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public alarm_msg getAlarmMessage(int i) {
            return this.alarmMessage_.get(i);
        }

        public alarm_msgOrBuilder getAlarmMessageOrBuilder(int i) {
            return this.alarmMessage_.get(i);
        }

        private void ensureAlarmMessageIsMutable() {
            Internal.ProtobufList<alarm_msg> protobufList = this.alarmMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.alarmMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlarmMessage(int i, alarm_msg alarm_msgVar) {
            alarm_msgVar.getClass();
            ensureAlarmMessageIsMutable();
            this.alarmMessage_.set(i, alarm_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAlarmMessage(alarm_msg alarm_msgVar) {
            alarm_msgVar.getClass();
            ensureAlarmMessageIsMutable();
            this.alarmMessage_.add(alarm_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAlarmMessage(int i, alarm_msg alarm_msgVar) {
            alarm_msgVar.getClass();
            ensureAlarmMessageIsMutable();
            this.alarmMessage_.add(i, alarm_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAlarmMessage(Iterable<? extends alarm_msg> iterable) {
            ensureAlarmMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.alarmMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlarmMessage() {
            this.alarmMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAlarmMessage(int i) {
            ensureAlarmMessageIsMutable();
            this.alarmMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<lap_msg> getLapMessageList() {
            return this.lapMessage_;
        }

        public List<? extends lap_msgOrBuilder> getLapMessageOrBuilderList() {
            return this.lapMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getLapMessageCount() {
            return this.lapMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public lap_msg getLapMessage(int i) {
            return this.lapMessage_.get(i);
        }

        public lap_msgOrBuilder getLapMessageOrBuilder(int i) {
            return this.lapMessage_.get(i);
        }

        private void ensureLapMessageIsMutable() {
            Internal.ProtobufList<lap_msg> protobufList = this.lapMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.lapMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLapMessage(int i, lap_msg lap_msgVar) {
            lap_msgVar.getClass();
            ensureLapMessageIsMutable();
            this.lapMessage_.set(i, lap_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLapMessage(lap_msg lap_msgVar) {
            lap_msgVar.getClass();
            ensureLapMessageIsMutable();
            this.lapMessage_.add(lap_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLapMessage(int i, lap_msg lap_msgVar) {
            lap_msgVar.getClass();
            ensureLapMessageIsMutable();
            this.lapMessage_.add(i, lap_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllLapMessage(Iterable<? extends lap_msg> iterable) {
            ensureLapMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.lapMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLapMessage() {
            this.lapMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeLapMessage(int i) {
            ensureLapMessageIsMutable();
            this.lapMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<auto_set_msg> getAutoSetMessageList() {
            return this.autoSetMessage_;
        }

        public List<? extends auto_set_msgOrBuilder> getAutoSetMessageOrBuilderList() {
            return this.autoSetMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getAutoSetMessageCount() {
            return this.autoSetMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public auto_set_msg getAutoSetMessage(int i) {
            return this.autoSetMessage_.get(i);
        }

        public auto_set_msgOrBuilder getAutoSetMessageOrBuilder(int i) {
            return this.autoSetMessage_.get(i);
        }

        private void ensureAutoSetMessageIsMutable() {
            Internal.ProtobufList<auto_set_msg> protobufList = this.autoSetMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.autoSetMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoSetMessage(int i, auto_set_msg auto_set_msgVar) {
            auto_set_msgVar.getClass();
            ensureAutoSetMessageIsMutable();
            this.autoSetMessage_.set(i, auto_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAutoSetMessage(auto_set_msg auto_set_msgVar) {
            auto_set_msgVar.getClass();
            ensureAutoSetMessageIsMutable();
            this.autoSetMessage_.add(auto_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAutoSetMessage(int i, auto_set_msg auto_set_msgVar) {
            auto_set_msgVar.getClass();
            ensureAutoSetMessageIsMutable();
            this.autoSetMessage_.add(i, auto_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAutoSetMessage(Iterable<? extends auto_set_msg> iterable) {
            ensureAutoSetMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.autoSetMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoSetMessage() {
            this.autoSetMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAutoSetMessage(int i) {
            ensureAutoSetMessageIsMutable();
            this.autoSetMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<key_set_msg> getKeySetMessageList() {
            return this.keySetMessage_;
        }

        public List<? extends key_set_msgOrBuilder> getKeySetMessageOrBuilderList() {
            return this.keySetMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getKeySetMessageCount() {
            return this.keySetMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public key_set_msg getKeySetMessage(int i) {
            return this.keySetMessage_.get(i);
        }

        public key_set_msgOrBuilder getKeySetMessageOrBuilder(int i) {
            return this.keySetMessage_.get(i);
        }

        private void ensureKeySetMessageIsMutable() {
            Internal.ProtobufList<key_set_msg> protobufList = this.keySetMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.keySetMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeySetMessage(int i, key_set_msg key_set_msgVar) {
            key_set_msgVar.getClass();
            ensureKeySetMessageIsMutable();
            this.keySetMessage_.set(i, key_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addKeySetMessage(key_set_msg key_set_msgVar) {
            key_set_msgVar.getClass();
            ensureKeySetMessageIsMutable();
            this.keySetMessage_.add(key_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addKeySetMessage(int i, key_set_msg key_set_msgVar) {
            key_set_msgVar.getClass();
            ensureKeySetMessageIsMutable();
            this.keySetMessage_.add(i, key_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllKeySetMessage(Iterable<? extends key_set_msg> iterable) {
            ensureKeySetMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.keySetMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeySetMessage() {
            this.keySetMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeKeySetMessage(int i) {
            ensureKeySetMessageIsMutable();
            this.keySetMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<sound_set_msg> getSoundSetMessageList() {
            return this.soundSetMessage_;
        }

        public List<? extends sound_set_msgOrBuilder> getSoundSetMessageOrBuilderList() {
            return this.soundSetMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getSoundSetMessageCount() {
            return this.soundSetMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public sound_set_msg getSoundSetMessage(int i) {
            return this.soundSetMessage_.get(i);
        }

        public sound_set_msgOrBuilder getSoundSetMessageOrBuilder(int i) {
            return this.soundSetMessage_.get(i);
        }

        private void ensureSoundSetMessageIsMutable() {
            Internal.ProtobufList<sound_set_msg> protobufList = this.soundSetMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.soundSetMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSoundSetMessage(int i, sound_set_msg sound_set_msgVar) {
            sound_set_msgVar.getClass();
            ensureSoundSetMessageIsMutable();
            this.soundSetMessage_.set(i, sound_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSoundSetMessage(sound_set_msg sound_set_msgVar) {
            sound_set_msgVar.getClass();
            ensureSoundSetMessageIsMutable();
            this.soundSetMessage_.add(sound_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSoundSetMessage(int i, sound_set_msg sound_set_msgVar) {
            sound_set_msgVar.getClass();
            ensureSoundSetMessageIsMutable();
            this.soundSetMessage_.add(i, sound_set_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSoundSetMessage(Iterable<? extends sound_set_msg> iterable) {
            ensureSoundSetMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.soundSetMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSoundSetMessage() {
            this.soundSetMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSoundSetMessage(int i) {
            ensureSoundSetMessageIsMutable();
            this.soundSetMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public List<data_msg> getDataMessageList() {
            return this.dataMessage_;
        }

        public List<? extends data_msgOrBuilder> getDataMessageOrBuilderList() {
            return this.dataMessage_;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public int getDataMessageCount() {
            return this.dataMessage_.size();
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public data_msg getDataMessage(int i) {
            return this.dataMessage_.get(i);
        }

        public data_msgOrBuilder getDataMessageOrBuilder(int i) {
            return this.dataMessage_.get(i);
        }

        private void ensureDataMessageIsMutable() {
            Internal.ProtobufList<data_msg> protobufList = this.dataMessage_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.dataMessage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDataMessage(int i, data_msg data_msgVar) {
            data_msgVar.getClass();
            ensureDataMessageIsMutable();
            this.dataMessage_.set(i, data_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDataMessage(data_msg data_msgVar) {
            data_msgVar.getClass();
            ensureDataMessageIsMutable();
            this.dataMessage_.add(data_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDataMessage(int i, data_msg data_msgVar) {
            data_msgVar.getClass();
            ensureDataMessageIsMutable();
            this.dataMessage_.add(i, data_msgVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllDataMessage(Iterable<? extends data_msg> iterable) {
            ensureDataMessageIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.dataMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDataMessage() {
            this.dataMessage_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeDataMessage(int i) {
            ensureDataMessageIsMutable();
            this.dataMessage_.remove(i);
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasModeConfigMessage() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public mode_config_msg getModeConfigMessage() {
            mode_config_msg mode_config_msgVar = this.modeConfigMessage_;
            return mode_config_msgVar == null ? mode_config_msg.getDefaultInstance() : mode_config_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModeConfigMessage(mode_config_msg mode_config_msgVar) {
            mode_config_msgVar.getClass();
            this.modeConfigMessage_ = mode_config_msgVar;
            this.bitField0_ |= 512;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeModeConfigMessage(mode_config_msg mode_config_msgVar) {
            mode_config_msgVar.getClass();
            mode_config_msg mode_config_msgVar2 = this.modeConfigMessage_;
            if (mode_config_msgVar2 != null && mode_config_msgVar2 != mode_config_msg.getDefaultInstance()) {
                this.modeConfigMessage_ = mode_config_msg.newBuilder(this.modeConfigMessage_).mergeFrom(mode_config_msgVar).buildPartial();
            } else {
                this.modeConfigMessage_ = mode_config_msgVar;
            }
            this.bitField0_ |= 512;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModeConfigMessage() {
            this.modeConfigMessage_ = null;
            this.bitField0_ &= -513;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasTimeSystem() {
            return (this.bitField0_ & 1024) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public TIME_SYSTEM_TYPE getTimeSystem() {
            TIME_SYSTEM_TYPE time_system_typeForNumber = TIME_SYSTEM_TYPE.forNumber(this.timeSystem_);
            return time_system_typeForNumber == null ? TIME_SYSTEM_TYPE.enum_TIME_SYSTEM_TYPE_INVALID : time_system_typeForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimeSystem(TIME_SYSTEM_TYPE time_system_type) {
            this.timeSystem_ = time_system_type.getNumber();
            this.bitField0_ |= 1024;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeSystem() {
            this.bitField0_ &= -1025;
            this.timeSystem_ = 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasPasswordMessage() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public password_msg getPasswordMessage() {
            password_msg password_msgVar = this.passwordMessage_;
            return password_msgVar == null ? password_msg.getDefaultInstance() : password_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPasswordMessage(password_msg password_msgVar) {
            password_msgVar.getClass();
            this.passwordMessage_ = password_msgVar;
            this.bitField0_ |= 2048;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePasswordMessage(password_msg password_msgVar) {
            password_msgVar.getClass();
            password_msg password_msgVar2 = this.passwordMessage_;
            if (password_msgVar2 != null && password_msgVar2 != password_msg.getDefaultInstance()) {
                this.passwordMessage_ = password_msg.newBuilder(this.passwordMessage_).mergeFrom(password_msgVar).buildPartial();
            } else {
                this.passwordMessage_ = password_msgVar;
            }
            this.bitField0_ |= 2048;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPasswordMessage() {
            this.passwordMessage_ = null;
            this.bitField0_ &= -2049;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public boolean hasAccidentDetectionMessage() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
        public accident_detection_msg getAccidentDetectionMessage() {
            accident_detection_msg accident_detection_msgVar = this.accidentDetectionMessage_;
            return accident_detection_msgVar == null ? accident_detection_msg.getDefaultInstance() : accident_detection_msgVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccidentDetectionMessage(accident_detection_msg accident_detection_msgVar) {
            accident_detection_msgVar.getClass();
            this.accidentDetectionMessage_ = accident_detection_msgVar;
            this.bitField0_ |= 4096;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAccidentDetectionMessage(accident_detection_msg accident_detection_msgVar) {
            accident_detection_msgVar.getClass();
            accident_detection_msg accident_detection_msgVar2 = this.accidentDetectionMessage_;
            if (accident_detection_msgVar2 != null && accident_detection_msgVar2 != accident_detection_msg.getDefaultInstance()) {
                this.accidentDetectionMessage_ = accident_detection_msg.newBuilder(this.accidentDetectionMessage_).mergeFrom(accident_detection_msgVar).buildPartial();
            } else {
                this.accidentDetectionMessage_ = accident_detection_msgVar;
            }
            this.bitField0_ |= 4096;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccidentDetectionMessage() {
            this.accidentDetectionMessage_ = null;
            this.bitField0_ &= -4097;
        }

        public static config_msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static config_msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static config_msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static config_msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static config_msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static config_msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static config_msg parseFrom(InputStream inputStream) throws IOException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static config_msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static config_msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (config_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static config_msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (config_msg) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static config_msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static config_msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (config_msg) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(config_msg config_msgVar) {
            return DEFAULT_INSTANCE.createBuilder(config_msgVar);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<config_msg, Builder> implements config_msgOrBuilder {
            private Builder() {
                super(config_msg.DEFAULT_INSTANCE);
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasServiceType() {
                return ((config_msg) this.instance).hasServiceType();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public Common.service_type_index getServiceType() {
                return ((config_msg) this.instance).getServiceType();
            }

            public Builder setServiceType(Common.service_type_index service_type_indexVar) {
                copyOnWrite();
                ((config_msg) this.instance).setServiceType(service_type_indexVar);
                return this;
            }

            public Builder clearServiceType() {
                copyOnWrite();
                ((config_msg) this.instance).clearServiceType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasConfigSeviceType() {
                return ((config_msg) this.instance).hasConfigSeviceType();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public CONFIG_SERVICE_TYPE getConfigSeviceType() {
                return ((config_msg) this.instance).getConfigSeviceType();
            }

            public Builder setConfigSeviceType(CONFIG_SERVICE_TYPE config_service_type) {
                copyOnWrite();
                ((config_msg) this.instance).setConfigSeviceType(config_service_type);
                return this;
            }

            public Builder clearConfigSeviceType() {
                copyOnWrite();
                ((config_msg) this.instance).clearConfigSeviceType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasConfigOperateType() {
                return ((config_msg) this.instance).hasConfigOperateType();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public CONFIG_OPERATE_TYPE getConfigOperateType() {
                return ((config_msg) this.instance).getConfigOperateType();
            }

            public Builder setConfigOperateType(CONFIG_OPERATE_TYPE config_operate_type) {
                copyOnWrite();
                ((config_msg) this.instance).setConfigOperateType(config_operate_type);
                return this;
            }

            public Builder clearConfigOperateType() {
                copyOnWrite();
                ((config_msg) this.instance).clearConfigOperateType();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasUserDataMessage() {
                return ((config_msg) this.instance).hasUserDataMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public user_data_msg getUserDataMessage() {
                return ((config_msg) this.instance).getUserDataMessage();
            }

            public Builder setUserDataMessage(user_data_msg user_data_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setUserDataMessage(user_data_msgVar);
                return this;
            }

            public Builder setUserDataMessage(user_data_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setUserDataMessage(builder.build());
                return this;
            }

            public Builder mergeUserDataMessage(user_data_msg user_data_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergeUserDataMessage(user_data_msgVar);
                return this;
            }

            public Builder clearUserDataMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearUserDataMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<page_msg> getPageMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getPageMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getPageMessageCount() {
                return ((config_msg) this.instance).getPageMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public page_msg getPageMessage(int i) {
                return ((config_msg) this.instance).getPageMessage(i);
            }

            public Builder setPageMessage(int i, page_msg page_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setPageMessage(i, page_msgVar);
                return this;
            }

            public Builder setPageMessage(int i, page_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setPageMessage(i, builder.build());
                return this;
            }

            public Builder addPageMessage(page_msg page_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addPageMessage(page_msgVar);
                return this;
            }

            public Builder addPageMessage(int i, page_msg page_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addPageMessage(i, page_msgVar);
                return this;
            }

            public Builder addPageMessage(page_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addPageMessage(builder.build());
                return this;
            }

            public Builder addPageMessage(int i, page_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addPageMessage(i, builder.build());
                return this;
            }

            public Builder addAllPageMessage(Iterable<? extends page_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllPageMessage(iterable);
                return this;
            }

            public Builder clearPageMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearPageMessage();
                return this;
            }

            public Builder removePageMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removePageMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<bike_msg> getBikeMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getBikeMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getBikeMessageCount() {
                return ((config_msg) this.instance).getBikeMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public bike_msg getBikeMessage(int i) {
                return ((config_msg) this.instance).getBikeMessage(i);
            }

            public Builder setBikeMessage(int i, bike_msg bike_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setBikeMessage(i, bike_msgVar);
                return this;
            }

            public Builder setBikeMessage(int i, bike_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setBikeMessage(i, builder.build());
                return this;
            }

            public Builder addBikeMessage(bike_msg bike_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addBikeMessage(bike_msgVar);
                return this;
            }

            public Builder addBikeMessage(int i, bike_msg bike_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addBikeMessage(i, bike_msgVar);
                return this;
            }

            public Builder addBikeMessage(bike_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addBikeMessage(builder.build());
                return this;
            }

            public Builder addBikeMessage(int i, bike_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addBikeMessage(i, builder.build());
                return this;
            }

            public Builder addAllBikeMessage(Iterable<? extends bike_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllBikeMessage(iterable);
                return this;
            }

            public Builder clearBikeMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearBikeMessage();
                return this;
            }

            public Builder removeBikeMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeBikeMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<unit_msg> getUnitMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getUnitMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getUnitMessageCount() {
                return ((config_msg) this.instance).getUnitMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public unit_msg getUnitMessage(int i) {
                return ((config_msg) this.instance).getUnitMessage(i);
            }

            public Builder setUnitMessage(int i, unit_msg unit_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setUnitMessage(i, unit_msgVar);
                return this;
            }

            public Builder setUnitMessage(int i, unit_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setUnitMessage(i, builder.build());
                return this;
            }

            public Builder addUnitMessage(unit_msg unit_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addUnitMessage(unit_msgVar);
                return this;
            }

            public Builder addUnitMessage(int i, unit_msg unit_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addUnitMessage(i, unit_msgVar);
                return this;
            }

            public Builder addUnitMessage(unit_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addUnitMessage(builder.build());
                return this;
            }

            public Builder addUnitMessage(int i, unit_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addUnitMessage(i, builder.build());
                return this;
            }

            public Builder addAllUnitMessage(Iterable<? extends unit_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllUnitMessage(iterable);
                return this;
            }

            public Builder clearUnitMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearUnitMessage();
                return this;
            }

            public Builder removeUnitMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeUnitMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasPageStatusMessage() {
                return ((config_msg) this.instance).hasPageStatusMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public cur_page_status_msg getPageStatusMessage() {
                return ((config_msg) this.instance).getPageStatusMessage();
            }

            public Builder setPageStatusMessage(cur_page_status_msg cur_page_status_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setPageStatusMessage(cur_page_status_msgVar);
                return this;
            }

            public Builder setPageStatusMessage(cur_page_status_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setPageStatusMessage(builder.build());
                return this;
            }

            public Builder mergePageStatusMessage(cur_page_status_msg cur_page_status_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergePageStatusMessage(cur_page_status_msgVar);
                return this;
            }

            public Builder clearPageStatusMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearPageStatusMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasAltitude() {
                return ((config_msg) this.instance).hasAltitude();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getAltitude() {
                return ((config_msg) this.instance).getAltitude();
            }

            public Builder setAltitude(int i) {
                copyOnWrite();
                ((config_msg) this.instance).setAltitude(i);
                return this;
            }

            public Builder clearAltitude() {
                copyOnWrite();
                ((config_msg) this.instance).clearAltitude();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasLanguageMessage() {
                return ((config_msg) this.instance).hasLanguageMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public language_msg getLanguageMessage() {
                return ((config_msg) this.instance).getLanguageMessage();
            }

            public Builder setLanguageMessage(language_msg language_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setLanguageMessage(language_msgVar);
                return this;
            }

            public Builder setLanguageMessage(language_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setLanguageMessage(builder.build());
                return this;
            }

            public Builder mergeLanguageMessage(language_msg language_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergeLanguageMessage(language_msgVar);
                return this;
            }

            public Builder clearLanguageMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearLanguageMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasBacklightMessage() {
                return ((config_msg) this.instance).hasBacklightMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public backlight_msg getBacklightMessage() {
                return ((config_msg) this.instance).getBacklightMessage();
            }

            public Builder setBacklightMessage(backlight_msg backlight_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setBacklightMessage(backlight_msgVar);
                return this;
            }

            public Builder setBacklightMessage(backlight_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setBacklightMessage(builder.build());
                return this;
            }

            public Builder mergeBacklightMessage(backlight_msg backlight_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergeBacklightMessage(backlight_msgVar);
                return this;
            }

            public Builder clearBacklightMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearBacklightMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasCurOperateMode() {
                return ((config_msg) this.instance).hasCurOperateMode();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getCurOperateMode() {
                return ((config_msg) this.instance).getCurOperateMode();
            }

            public Builder setCurOperateMode(int i) {
                copyOnWrite();
                ((config_msg) this.instance).setCurOperateMode(i);
                return this;
            }

            public Builder clearCurOperateMode() {
                copyOnWrite();
                ((config_msg) this.instance).clearCurOperateMode();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<mode_msg> getModeMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getModeMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getModeMessageCount() {
                return ((config_msg) this.instance).getModeMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public mode_msg getModeMessage(int i) {
                return ((config_msg) this.instance).getModeMessage(i);
            }

            public Builder setModeMessage(int i, mode_msg mode_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setModeMessage(i, mode_msgVar);
                return this;
            }

            public Builder setModeMessage(int i, mode_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setModeMessage(i, builder.build());
                return this;
            }

            public Builder addModeMessage(mode_msg mode_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addModeMessage(mode_msgVar);
                return this;
            }

            public Builder addModeMessage(int i, mode_msg mode_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addModeMessage(i, mode_msgVar);
                return this;
            }

            public Builder addModeMessage(mode_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addModeMessage(builder.build());
                return this;
            }

            public Builder addModeMessage(int i, mode_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addModeMessage(i, builder.build());
                return this;
            }

            public Builder addAllModeMessage(Iterable<? extends mode_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllModeMessage(iterable);
                return this;
            }

            public Builder clearModeMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearModeMessage();
                return this;
            }

            public Builder removeModeMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeModeMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<alarm_msg> getAlarmMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getAlarmMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getAlarmMessageCount() {
                return ((config_msg) this.instance).getAlarmMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public alarm_msg getAlarmMessage(int i) {
                return ((config_msg) this.instance).getAlarmMessage(i);
            }

            public Builder setAlarmMessage(int i, alarm_msg alarm_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setAlarmMessage(i, alarm_msgVar);
                return this;
            }

            public Builder setAlarmMessage(int i, alarm_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setAlarmMessage(i, builder.build());
                return this;
            }

            public Builder addAlarmMessage(alarm_msg alarm_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addAlarmMessage(alarm_msgVar);
                return this;
            }

            public Builder addAlarmMessage(int i, alarm_msg alarm_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addAlarmMessage(i, alarm_msgVar);
                return this;
            }

            public Builder addAlarmMessage(alarm_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addAlarmMessage(builder.build());
                return this;
            }

            public Builder addAlarmMessage(int i, alarm_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addAlarmMessage(i, builder.build());
                return this;
            }

            public Builder addAllAlarmMessage(Iterable<? extends alarm_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllAlarmMessage(iterable);
                return this;
            }

            public Builder clearAlarmMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearAlarmMessage();
                return this;
            }

            public Builder removeAlarmMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeAlarmMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<lap_msg> getLapMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getLapMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getLapMessageCount() {
                return ((config_msg) this.instance).getLapMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public lap_msg getLapMessage(int i) {
                return ((config_msg) this.instance).getLapMessage(i);
            }

            public Builder setLapMessage(int i, lap_msg lap_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setLapMessage(i, lap_msgVar);
                return this;
            }

            public Builder setLapMessage(int i, lap_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setLapMessage(i, builder.build());
                return this;
            }

            public Builder addLapMessage(lap_msg lap_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addLapMessage(lap_msgVar);
                return this;
            }

            public Builder addLapMessage(int i, lap_msg lap_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addLapMessage(i, lap_msgVar);
                return this;
            }

            public Builder addLapMessage(lap_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addLapMessage(builder.build());
                return this;
            }

            public Builder addLapMessage(int i, lap_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addLapMessage(i, builder.build());
                return this;
            }

            public Builder addAllLapMessage(Iterable<? extends lap_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllLapMessage(iterable);
                return this;
            }

            public Builder clearLapMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearLapMessage();
                return this;
            }

            public Builder removeLapMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeLapMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<auto_set_msg> getAutoSetMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getAutoSetMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getAutoSetMessageCount() {
                return ((config_msg) this.instance).getAutoSetMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public auto_set_msg getAutoSetMessage(int i) {
                return ((config_msg) this.instance).getAutoSetMessage(i);
            }

            public Builder setAutoSetMessage(int i, auto_set_msg auto_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setAutoSetMessage(i, auto_set_msgVar);
                return this;
            }

            public Builder setAutoSetMessage(int i, auto_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setAutoSetMessage(i, builder.build());
                return this;
            }

            public Builder addAutoSetMessage(auto_set_msg auto_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addAutoSetMessage(auto_set_msgVar);
                return this;
            }

            public Builder addAutoSetMessage(int i, auto_set_msg auto_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addAutoSetMessage(i, auto_set_msgVar);
                return this;
            }

            public Builder addAutoSetMessage(auto_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addAutoSetMessage(builder.build());
                return this;
            }

            public Builder addAutoSetMessage(int i, auto_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addAutoSetMessage(i, builder.build());
                return this;
            }

            public Builder addAllAutoSetMessage(Iterable<? extends auto_set_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllAutoSetMessage(iterable);
                return this;
            }

            public Builder clearAutoSetMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearAutoSetMessage();
                return this;
            }

            public Builder removeAutoSetMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeAutoSetMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<key_set_msg> getKeySetMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getKeySetMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getKeySetMessageCount() {
                return ((config_msg) this.instance).getKeySetMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public key_set_msg getKeySetMessage(int i) {
                return ((config_msg) this.instance).getKeySetMessage(i);
            }

            public Builder setKeySetMessage(int i, key_set_msg key_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setKeySetMessage(i, key_set_msgVar);
                return this;
            }

            public Builder setKeySetMessage(int i, key_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setKeySetMessage(i, builder.build());
                return this;
            }

            public Builder addKeySetMessage(key_set_msg key_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addKeySetMessage(key_set_msgVar);
                return this;
            }

            public Builder addKeySetMessage(int i, key_set_msg key_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addKeySetMessage(i, key_set_msgVar);
                return this;
            }

            public Builder addKeySetMessage(key_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addKeySetMessage(builder.build());
                return this;
            }

            public Builder addKeySetMessage(int i, key_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addKeySetMessage(i, builder.build());
                return this;
            }

            public Builder addAllKeySetMessage(Iterable<? extends key_set_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllKeySetMessage(iterable);
                return this;
            }

            public Builder clearKeySetMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearKeySetMessage();
                return this;
            }

            public Builder removeKeySetMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeKeySetMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<sound_set_msg> getSoundSetMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getSoundSetMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getSoundSetMessageCount() {
                return ((config_msg) this.instance).getSoundSetMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public sound_set_msg getSoundSetMessage(int i) {
                return ((config_msg) this.instance).getSoundSetMessage(i);
            }

            public Builder setSoundSetMessage(int i, sound_set_msg sound_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setSoundSetMessage(i, sound_set_msgVar);
                return this;
            }

            public Builder setSoundSetMessage(int i, sound_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setSoundSetMessage(i, builder.build());
                return this;
            }

            public Builder addSoundSetMessage(sound_set_msg sound_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addSoundSetMessage(sound_set_msgVar);
                return this;
            }

            public Builder addSoundSetMessage(int i, sound_set_msg sound_set_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addSoundSetMessage(i, sound_set_msgVar);
                return this;
            }

            public Builder addSoundSetMessage(sound_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addSoundSetMessage(builder.build());
                return this;
            }

            public Builder addSoundSetMessage(int i, sound_set_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addSoundSetMessage(i, builder.build());
                return this;
            }

            public Builder addAllSoundSetMessage(Iterable<? extends sound_set_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllSoundSetMessage(iterable);
                return this;
            }

            public Builder clearSoundSetMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearSoundSetMessage();
                return this;
            }

            public Builder removeSoundSetMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeSoundSetMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public List<data_msg> getDataMessageList() {
                return Collections.unmodifiableList(((config_msg) this.instance).getDataMessageList());
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public int getDataMessageCount() {
                return ((config_msg) this.instance).getDataMessageCount();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public data_msg getDataMessage(int i) {
                return ((config_msg) this.instance).getDataMessage(i);
            }

            public Builder setDataMessage(int i, data_msg data_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setDataMessage(i, data_msgVar);
                return this;
            }

            public Builder setDataMessage(int i, data_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setDataMessage(i, builder.build());
                return this;
            }

            public Builder addDataMessage(data_msg data_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addDataMessage(data_msgVar);
                return this;
            }

            public Builder addDataMessage(int i, data_msg data_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).addDataMessage(i, data_msgVar);
                return this;
            }

            public Builder addDataMessage(data_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addDataMessage(builder.build());
                return this;
            }

            public Builder addDataMessage(int i, data_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).addDataMessage(i, builder.build());
                return this;
            }

            public Builder addAllDataMessage(Iterable<? extends data_msg> iterable) {
                copyOnWrite();
                ((config_msg) this.instance).addAllDataMessage(iterable);
                return this;
            }

            public Builder clearDataMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearDataMessage();
                return this;
            }

            public Builder removeDataMessage(int i) {
                copyOnWrite();
                ((config_msg) this.instance).removeDataMessage(i);
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasModeConfigMessage() {
                return ((config_msg) this.instance).hasModeConfigMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public mode_config_msg getModeConfigMessage() {
                return ((config_msg) this.instance).getModeConfigMessage();
            }

            public Builder setModeConfigMessage(mode_config_msg mode_config_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setModeConfigMessage(mode_config_msgVar);
                return this;
            }

            public Builder setModeConfigMessage(mode_config_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setModeConfigMessage(builder.build());
                return this;
            }

            public Builder mergeModeConfigMessage(mode_config_msg mode_config_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergeModeConfigMessage(mode_config_msgVar);
                return this;
            }

            public Builder clearModeConfigMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearModeConfigMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasTimeSystem() {
                return ((config_msg) this.instance).hasTimeSystem();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public TIME_SYSTEM_TYPE getTimeSystem() {
                return ((config_msg) this.instance).getTimeSystem();
            }

            public Builder setTimeSystem(TIME_SYSTEM_TYPE time_system_type) {
                copyOnWrite();
                ((config_msg) this.instance).setTimeSystem(time_system_type);
                return this;
            }

            public Builder clearTimeSystem() {
                copyOnWrite();
                ((config_msg) this.instance).clearTimeSystem();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasPasswordMessage() {
                return ((config_msg) this.instance).hasPasswordMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public password_msg getPasswordMessage() {
                return ((config_msg) this.instance).getPasswordMessage();
            }

            public Builder setPasswordMessage(password_msg password_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setPasswordMessage(password_msgVar);
                return this;
            }

            public Builder setPasswordMessage(password_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setPasswordMessage(builder.build());
                return this;
            }

            public Builder mergePasswordMessage(password_msg password_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergePasswordMessage(password_msgVar);
                return this;
            }

            public Builder clearPasswordMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearPasswordMessage();
                return this;
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public boolean hasAccidentDetectionMessage() {
                return ((config_msg) this.instance).hasAccidentDetectionMessage();
            }

            @Override // com.igpsport.blelib.bean.Config.config_msgOrBuilder
            public accident_detection_msg getAccidentDetectionMessage() {
                return ((config_msg) this.instance).getAccidentDetectionMessage();
            }

            public Builder setAccidentDetectionMessage(accident_detection_msg accident_detection_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).setAccidentDetectionMessage(accident_detection_msgVar);
                return this;
            }

            public Builder setAccidentDetectionMessage(accident_detection_msg.Builder builder) {
                copyOnWrite();
                ((config_msg) this.instance).setAccidentDetectionMessage(builder.build());
                return this;
            }

            public Builder mergeAccidentDetectionMessage(accident_detection_msg accident_detection_msgVar) {
                copyOnWrite();
                ((config_msg) this.instance).mergeAccidentDetectionMessage(accident_detection_msgVar);
                return this;
            }

            public Builder clearAccidentDetectionMessage() {
                copyOnWrite();
                ((config_msg) this.instance).clearAccidentDetectionMessage();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new config_msg();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0017\u0000\u0001\u0001\u001e\u0017\u0000\n\u0006\u0001ᔌ\u0000\u0002ᔌ\u0001\u0003ᔌ\u0002\u0004ဉ\u0003\u0005\u001b\u0006\u001b\u0007\u001b\bဉ\u0004\tဋ\u0005\fᐉ\u0006\rဉ\u0007\u0013ဋ\b\u0014\u001b\u0015Л\u0016Л\u0017\u001b\u0018\u001b\u0019\u001b\u001a\u001b\u001bဉ\t\u001cဌ\n\u001dဉ\u000b\u001eဉ\f", new Object[]{"bitField0_", "serviceType_", Common.service_type_index.internalGetVerifier(), "configSeviceType_", CONFIG_SERVICE_TYPE.internalGetVerifier(), "configOperateType_", CONFIG_OPERATE_TYPE.internalGetVerifier(), "userDataMessage_", "pageMessage_", page_msg.class, "bikeMessage_", bike_msg.class, "unitMessage_", unit_msg.class, "pageStatusMessage_", "altitude_", "languageMessage_", "backlightMessage_", "curOperateMode_", "modeMessage_", mode_msg.class, "alarmMessage_", alarm_msg.class, "lapMessage_", lap_msg.class, "autoSetMessage_", auto_set_msg.class, "keySetMessage_", key_set_msg.class, "soundSetMessage_", sound_set_msg.class, "dataMessage_", data_msg.class, "modeConfigMessage_", "timeSystem_", TIME_SYSTEM_TYPE.internalGetVerifier(), "passwordMessage_", "accidentDetectionMessage_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<config_msg> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (config_msg.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            config_msg config_msgVar = new config_msg();
            DEFAULT_INSTANCE = config_msgVar;
            GeneratedMessageLite.registerDefaultInstance(config_msg.class, config_msgVar);
        }

        public static config_msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<config_msg> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
