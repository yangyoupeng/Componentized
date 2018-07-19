package com.maoliicai.common.webview_javascript;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.alibaba.android.arouter.launcher.ARouter;
import com.maoliicai.common.base.BaseFragment;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.eventbus.EventParameter;
import com.maoliicai.common.utils.SP;
import com.maoliicai.common.utils.SPKey;
import com.maoliicai.common.utils.SPUtils;


/**
 * @author mumuji
 * @version 1.0.0
 * @date 2017/10/16
 * @description
 */
public class JavaScriptInterface {

    private Context mContext;
    private OnShareWebListener mShareWebListener;
    private OnRechargeListener mOnRechargeListener;

    public JavaScriptInterface(Context context) {
        mContext = context;
    }


    /**
     * 去注册界面
     */
    @JavascriptInterface
    public void toRegister() {
        ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
    }

    /**
     * 去登录界面
     */
    @JavascriptInterface
    public void toLogin() {
        ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
    }

    /**
     * 去投资界面
     */
    @JavascriptInterface
    public void toInvest() {
        ARouter.getInstance().build(RouterPath.FRAMWORK_ACTIVITY)
                .withString("homeInvestment", "homeInvestment")
                .navigation();

    }

    /**
     * 去投资界面
     *
     * @param phone
     * @param auth
     */
    @JavascriptInterface
    public void toInvest(String phone, String auth) {
        updateUser(phone, auth);
        ARouter.getInstance().build(RouterPath.FRAMWORK_ACTIVITY)
                .withString("homeInvestment", "homeInvestment")
                .navigation();
    }

    /**
     * 去券包
     */
    @JavascriptInterface
    public void toVoucherPage() {
        ARouter.getInstance().build(RouterPath.SELECT_VOUCHER_ACTIVITY)
                .withString("myAssets", "myAssets")
                .navigation();
    }

    /**
     * 去绑定银行卡
     */
    @JavascriptInterface
    public void toBindBankCard() {
        ARouter.getInstance().build(RouterPath.MY_ASSETS_AUTHENTICATION_ACTIVITY)
                .withString(EventParameter.EXTRA_FROM, EventParameter.EXTRA_FROM_HTML).navigation();
    }

    /**
     * 去设置交易密码
     */
    @JavascriptInterface
    public void toSetTradePassword() {
        ARouter.getInstance().build(RouterPath.MY_SETPAYPWD_ACTIVITY).navigation();
    }

    /**
     * 显示分享的dialog
     */
    @JavascriptInterface
    public void toShowShare(String title, String description, String url) {
        if (!SP.getLoginStatus(mContext)) {
            ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
        } else {
            mShareWebListener.shareDo(title, description, url);
        }

    }

    /**
     * 显示邀请好友分享的dialog 这个需要调用接口统计分享次数
     */
    @JavascriptInterface
    public void toShowInvite(String title, String description, String url) {
        if (!SP.getLoginStatus(mContext)) {
            ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
        } else {
            mShareWebListener.inviteShareDo(title, description, url);
        }
    }

    /**
     * 去充值界面
     */
    @JavascriptInterface
    public void toRecharge() {
        if (!SP.getLoginStatus(mContext)) {//未登录
            ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
        } else {//登录
            mOnRechargeListener.toRecharge();
        }
    }

    /**
     * 更新登录信息
     *
     * @param phone
     * @param auth
     */
    private void updateUser(String phone, String auth) {
        SPUtils.put(mContext, SPKey.token, auth);
        SPUtils.put(mContext, SPKey.userName, phone);
        SPUtils.put(mContext, SPKey.loginStatus, true);
    }

    /**
     * 分享的监听
     */
    public interface OnShareWebListener {
        void shareDo(String title, String description, String url);

        void inviteShareDo(String title, String description, String url);
    }

    public void setShareWebListener(OnShareWebListener listener) {
        this.mShareWebListener = listener;
    }

    /**
     * 去充值的监听
     */
    public interface OnRechargeListener {
        void toRecharge();
    }

    public void setOnRechargeListener(OnRechargeListener listener) {
        this.mOnRechargeListener = listener;
    }
}
