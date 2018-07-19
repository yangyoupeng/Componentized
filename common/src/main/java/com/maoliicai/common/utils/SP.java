package com.maoliicai.common.utils;

import android.content.Context;


/**
 * Created by lauyk on 2016/10/18.
 */

public class SP {

    public static String getToken(Context context) {
        return (String) SPUtils.get(context, SPKey.token, "");
        //return "a0338900-92dd-4f9e-9998-3caf3dbebecb";
    }

    public static boolean getLoginStatus(Context context) {
        return (boolean) SPUtils.get(context, SPKey.loginStatus, false);
    }

//    public static String getChannel(Context context) {
//        return String.valueOf(SPUtils.get(context, "channel", "0"));
//    }

    /**
     * 邀请码
     *
     * @param context
     * @return
     */
    public static String getInvitationCode(Context context) {
        return (String) SPUtils.get(context, SPKey.invitationCode, "");
    }

    /**
     * 用户名
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        return (String) SPUtils.get(context, SPKey.userName, "");
    }


    /**
     * 判断用户名
     *
     * @param context
     * @return
     */
    public static String getIsUserName(Context context) {
        return (String) SPUtils.get(context, SPKey.isUserName, "");
    }

    /**
     * 判断用户名
     *
     * @param context
     * @return
     */
    public static String getIsLockUserName(Context context) {
        return (String) SPUtils.get(context, SPKey.isLockUserName, "");
    }


    /**
     * 标示
     *
     * @param context
     * @return
     */
    public static String getFlag(Context context) {
        return (String) SPUtils.get(context, SPKey.flag, "");
    }

    /**
     * 标示isopenges
     *
     * @param context
     * @return
     */
    public static boolean getIsOpenges(Context context) {
        return (boolean) SPUtils.get(context, SPKey.isopenges, false);
    }

    /**
     * 标示isopenges
     *
     * @param context
     * @return
     */
    public static boolean getIsO(Context context) {
        return (boolean) SPUtils.get(context, SPKey.iso, false);
    }
    //

    /**
     * 标示
     *
     * @param context
     * @return
     */
    public static String getNoticeId(Context context) {
        return (String) SPUtils.get(context, SPKey.noticeId, "");
    }

    /**
     * 返回键
     */
    public static String getBackFlag(Context context) {
        return (String) SPUtils.get(context, SPKey.backFlag, "");
    }

    /**
     * 消息的标识
     */
    public static String getNoticeNum(Context context) {
        return (String) SPUtils.get(context, SPKey.noticeNum, "");
    }

    /**
     * 消息的标识
     */
    public static String getNoticeNums(Context context) {
        return (String) SPUtils.get(context, SPKey.noticeNums, "");
    }

    /**
     * 活动的标识
     */
    public static String getHuodongNum(Context context) {
        return (String) SPUtils.get(context, SPKey.huodongNum, "");
    }

    /**
     * 活动的标识
     */
    public static String getHuodongNums(Context context) {
        return (String) SPUtils.get(context, SPKey.huodongNums, "");
    }

    /**
     * 锁的标识
     */
    public static String getIntoLock(Context context) {
        return (String) SPUtils.get(context, SPKey.intoLock, "");
    }


    /**
     * 弹框标识
     */
    public static String getFl(Context context) {
        return (String) SPUtils.get(context, SPKey.fl, "");
    }

    /**
     * 首页活动弹框显示时间
     */
    public static String getDialogShowDate(Context context) {
        return (String) SPUtils.get(context, SPKey.dialog_show_date, "");
    }

    /**
     * 今天是否关闭了首页弹框
     */
    public static String getCloseDialgogDate(Context context) {
        return (String) SPUtils.get(context, SPKey.dialog_close_date, "");
    }


    /**
     * 是否激活
     */
    public static boolean getIsFirst(Context context) {
        return (boolean) SPUtils.get(context, SPKey.IS_FIRST, true);
    }

    /**
     * 是否激活
     */
    public static void setIsFirst(Context context, boolean b) {
        SPUtils.put(context, SPKey.IS_FIRST, b);
    }

    /**
     * 是否显示认证弹窗
     */
    public static boolean getIsShowAuthDialog(Context context) {
        return (boolean) SPUtils.get(context, SPKey.IS_SHOW_AUTH_DIALOG, true);
    }

    /**
     * 是否显示认证弹窗
     */
    public static void setIsShowAuthDialog(Context context, boolean b) {
        SPUtils.put(context, SPKey.IS_SHOW_AUTH_DIALOG, b);
    }

    /**
     * 是否第一次启动app
     */
    public static boolean getIsFirstStartApp(Context context) {
        return (boolean) SPUtils.get(context, SPKey.IS_FIRST_START_APP, true);
    }

    /**
     * 获取 是否第一次启动app
     */
    public static void setIsFirstStartApp(Context context, boolean b) {
        SPUtils.put(context, SPKey.IS_FIRST_START_APP, b);
    }

    /**
     * 设置客服电话
     */
    public static void setServiceTel(Context context, String tel) {
        SPUtils.put(context, SPKey.SERVICE_TEL, tel);
    }

    /**
     * 获取客服电话
     */
    public static String getServiceTel(Context context) {
        return (String) SPUtils.get(context, SPKey.SERVICE_TEL, "");
    }

    /**
     * 是否是第一次实名认证
     */
    public static void setIsFirstBind(Context context, boolean isFirstBind) {
        SPUtils.put(context, SPKey.IS_FIRST_BIND, isFirstBind);
    }

    /**
     * 是否是第一次实名认证
     */
    public static boolean getIsFirstBind(Context context) {
        return (boolean) SPUtils.get(context, SPKey.IS_FIRST_BIND, true);
    }

    /**
     * 实名认证成功图片地址
     */
    public static void setRalNameAuthenticationImgUrl(Context context, String imgUrl) {
        SPUtils.put(context, SPKey.RALNAME_AUTHENTICATION_IMGURL, imgUrl);
    }

    public static String getRalNameAuthenticationImgUrl(Context context) {
        return (String) SPUtils.get(context, SPKey.RALNAME_AUTHENTICATION_IMGURL, "");
    }

    /**
     * 是否显示金额
     */
    public static void setIsShowMoney(Context context, boolean isShowMoney) {
        SPUtils.put(context, SPKey.IS_SHOW_MONEY, isShowMoney);
    }

    /**
     * 是否显示金额
     */
    public static boolean getIsShowMoney(Context context) {
        return (boolean) SPUtils.get(context, SPKey.IS_SHOW_MONEY, true);
    }

    /**
     * 支持银行url
     */
    public static void setSupportBankUrl(Context context, String supportBankUrl) {
        SPUtils.put(context, SPKey.SUPPORT_BANK_URL, supportBankUrl);
    }

    /**
     * 支持银行url
     */
    public static String getSupportBankUrl(Context context) {
        return (String) SPUtils.get(context, SPKey.SUPPORT_BANK_URL, "");
    }

    /**
     * 使用规则url
     */
    public static void setUseUrl(Context context, String supportBankUrl) {
        SPUtils.put(context, SPKey.USE_URL, supportBankUrl);
    }

    /**
     * 使用规则url
     */
    public static String getUseUrl(Context context) {
        return (String) SPUtils.get(context, SPKey.USE_URL, "");
    }

    /**
     * 渠道号
     */
    public static void setChannel(Context context, String supportBankUrl) {
        SPUtils.put(context, SPKey.CHANNEL, supportBankUrl);
    }

    /**
     * 渠道号
     */
    public static String getChannel(Context context) {
        return (String) SPUtils.get(context, SPKey.CHANNEL, "0");
    }

}
