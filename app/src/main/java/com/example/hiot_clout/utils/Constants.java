package com.example.hiot_clout.utils;

public class Constants {
    public static final int MAIN_FRAGMENT_COUNT = 4;
    public static final int MAIN_VIEWPAGER_INDEX_MESSAGE = 0;
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT = 1;
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 2;
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 3;

    /**
     * APP登录de logincode
     */
    public static final String LOGIN_CODE_APP = "app";
    /**
     * APP的注册用户类型
     */
    public static final String REGISTER_TYPE_NORMAL = "1";

    /**
     * 服务返回消息状态属性成功
     */
    public static final int MSG_STATUS_SUCCESS = 1;

    /**
     * 网络访问失败吐司
     */
    public static final String TOAST_MSG_NETWORK_FALL = "当前网络无法访问，请稍后再试";

    /**
     * MediaType.parse 类型常量
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final int MSG_STATUS_TOKEN_OUT = -100;

    /**
     * 设备已绑定
     */
    public static final int DEVICE_STATUS_BINDED = 1;

    /**
     * 设备未绑定
     */
    public static final int DEVICE_STATUS_UNBINDED = 1;

    /**
     * 设备id
     */
    public static final String INTENT_EXTRA_DEVICE_ID = "DEVICE_ID";

    /**
     * 设备已激活
     */
    public static final String DEVICE_STATUS_ACTIVITY = "1";

    public static final String DEVICE_STATUS_UNACTIVITY = "0";

    /**
     * 开关类型
     */
    public static final String DATA_STREAM_TYPE_SWITCH = "2";
    /**
     * 类型
     */
    public static final String DATA_STREAM_TYPE_VALUE = "1";
    /**
     * 类型
     */
    public static final String DATA_STREAM_TYPE_GPS = "3";
    /**
     * 类型
     */
    public static final String DATA_STREAM_TYPE_TEXT = "4";

    /**
     * 开关状态开
     */
    public static final Integer SWITCH_STATUS_ON = 1;
    /**
     * 开关状态关
     */
    public static final Integer SWITCH_STATUS_OFF = 0;

}
