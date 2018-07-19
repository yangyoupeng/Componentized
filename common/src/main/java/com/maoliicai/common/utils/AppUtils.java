package com.maoliicai.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by lauyk on 2016/9/28.
 * 跟App相关的辅助类
 */

public class AppUtils {

    private static PackageInfo getPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取应用程序名称
     *
     * @return 当前应用的名称
     */
    public static String getAppName(Context context) {
        if (getPackageManager(context)==null) {
            return "";
        }
        return context.getResources().getString(getPackageManager(context).applicationInfo.labelRes);
    }

    /**
     * 获取应用程序版本名称
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        if (getPackageManager(context)==null) {
            return "";
        }
        return getPackageManager(context).versionName;
    }

    /**
     * 获取应用程序版本 Code
     *
     * @return 当前应用的版本 Code
     */
    public static int getVersionCode(Context context) {
        if (getPackageManager(context)!=null) {
            return 0;
        }
        return getPackageManager(context).versionCode;
    }

    /**
     * 获取手机deviceId
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (!pinfo.isEmpty() && pinfo.size() > 0) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {

                    return true;
                }
            }
        }
        return false;
    }
}
