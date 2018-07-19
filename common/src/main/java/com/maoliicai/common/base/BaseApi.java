package com.maoliicai.common.base;

/**
 * yangyoupeng 2018/4/22
 */

public class BaseApi {

    public enum HostType {
        TEST_150,
        TEST_138,
        TEST_146,
        TEST_245,
        TEST_10,
        TEST_145
    }

    private static String sHost;

    public static void host(HostType hostType, boolean isDebug) {
        if (isDebug) {
            switch (hostType) {
                case TEST_150:
                    sHost = "http://192.168.0.150:8185/";//测试环境
                    break;
                case TEST_138:
                    sHost = "http://192.168.0.138:8185/";//军哥
                    break;
                case TEST_146:
                    sHost = "http://192.168.0.146:8185/";//陈小兵
                    break;
                case TEST_245:
                    sHost = "http://192.168.0.245:80/";//赵姑娘
                    break;
                case TEST_10:
                    sHost = "http://192.168.0.10:8185/";//张全耸
                    break;
                case TEST_145:
                    sHost = "http://192.168.0.145:8185/";//吕月月
                    break;
                default:
                    sHost = "http://192.168.0.150:8185/";//测试环境
                    break;
            }
        } else {
            sHost = "http://api.ibrjf.com/";
        }
    }

    public static String getBaseUrl() {
        return sHost == null ? "http://api.ibrjf.com/api/" : sHost + "api/";
    }


}
