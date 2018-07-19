package com.maoliicai.common.https.public_parameters;

/**
 * yangyoupeng  on 2018/4/26.
 * <p>
 * 请求头公共参数
 */

public class HeadersPublic {

    /**
     * app包名：appPackageName
     * app版本号：version
     * 请求方法版本号：method_version   接口无多个版本方法，可以为空
     * 渠道名称：channel
     * 渠道签名：channelSigna   与旧版app保持一致
     * 签名：imeiSigna          与旧版app保持一致
     * 登录标识：token			 未登录情况可以为空
     * 请求签名：sign
     * 时间戳：timestamp
     * 设备编号：deviceId
     * 设备ID签名：deviceIdSigna与旧版app保持一致
     * 设备标识：deviceToken
     * 设备标识签名：deviceTokenSigna与旧版app保持一致
     * 移动设备类型：deviceType    1.安卓  2.IOS
     */

    public static final String APP_PACKAGE_NAME = "appPackageName";
    public static final String VERSION = "version";
    public static final String METHOD_VERSION = "methodVersion";
    public static final String CHANNEL = "channel";
    public static final String CHANNEL_SIGNA = "channelSigna";
    public static final String TOKEN = "token";
    public static final String SIGN = "sign";
    public static final String TIMESTAMP = "timestamp";
    public static final String DEVICE_ID = "deviceId";
    public static final String DEVICE_ID_SIGNA = "deviceIdSigna";
    public static final String DEVICE_TOKEN = "deviceToken";
    public static final String DEVICE_TOKEN_SIGNA = "deviceTokenSigna";
    public static final String DEVICE_TYPE = "deviceType";

    public static final String APP_KEY = "6643150578D49C0BFAB2A53DBC52B801";

    public static final String APP_SECRET = "D580AB0E7BEAEAB465463444BC1A9533";

    public static final String TYPE = "1";//0:web端注册; 1:Android移动端; 2:IOS移动端; 3:微信注册;
    public static final String METHOD_VERSION_CODE = "";//接口版本号


}
