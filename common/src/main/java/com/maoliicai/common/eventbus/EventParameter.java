package com.maoliicai.common.eventbus;

/**
 * yangyoupeng  on 2018/1/31.
 * <p>
 * 区分eventbus发送到不同页面的dialog
 */

public class EventParameter {

    /**
     * 参数key from
     */
    public final static String EXTRA_FROM = "from";
    public final static String EXTRA_FROM_PERSONAL = "personal";//个人中心
    public final static String EXTRA_FROM_MYASSETS = "myAssets";//我的资产
    public final static String EXTRA_FROM_MYASSETS_RECHARGE = "recharge";//充值
    public final static String EXTRA_FROM_PRODUCT = "product";//产品详情
    public final static String EXTRA_FROM_HOME = "home";//首页
    public final static String EXTRA_FROM_HTML = "html";//H5页面

}
