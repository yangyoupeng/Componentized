package com.maoliicai.common.eventbus;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2017/10/25
 * @description
 */

public class EventType {
    public static final int EVENT_NO_LOGIN = 1000;//请重新登录
    public static final int EVENT_LOGIN = 1001;//登录
    public static final int EVENT_LOGOUT = 1002;//登出
    public static final int EVENT_REGISTER_SUCCESS = 1003;//注册成功
    public static final int EVENT_FORGET_LOGIN_PWD_SUCCESS = 1004;//忘记登录密码修改成功
    public static final int EVENT_SET_PAY_PWD_SUCCESS = 1005;//设置交易密码成功
    public static final int EVENT_SET_GESTURE_SUCCESS = 1006;//设置手势密码成功

    public static final int EVENT_RECHARGE_SUCCESS = 1010;//充值成功
    public static final int EVENT_WITHDRAW_SUCCESS = 1011; // 提现成功

//    public static final int EVENT_POPUP_BENEFIT = 1015;// 弹出新手福利
    public static final int EVENT_INVEST_SUCCESS = 1016;// 投资成功

    public static final int EVENT_AUTH_SUCCESS = 1030;//实名认证成功
    public static final int EVENT_REAUTH_SUCCESS = 1031;//第二次实名认证成功
    public static final int EVENT_AUTH_SUCCESS_FROM_RECHARGE = 1032;//从充值过来
    public static final int EVENT_AUTH_SUCCESS_FROM_MYFRAGMENT = 1033;//从实名认证过来
    public static final int EVENT_AUTH_SUCCESS_FROM_PERSONAL = 1034;//从个人中心过来
    public static final int EVENT_AUTH_SUCCESS_FROM_HOME = 1035;//从首页过来
    public static final int EVENT_AUTH_SUCCESS_FROM_PRODUCT = 1036;//从产品详情过来
    public static final int EVENT_AUTH_SUCCESS_FROM_HTML = 1037;//从H5页面跳转过来的

    public static final int TO_REA_NNAME_AUTHENTICATION = 1040;//产品详情，没有实名认证


    private int mType = -1;

    public EventType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }
}
