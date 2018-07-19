package com.maoliicai.common.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.maoliicai.common.utils.Utils;
import com.maoliicai.common.widget.lock.LockPatternUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * yangyoupeng  on 2018/4/3.
 */

public class BaseApplication extends Application {
    private static BaseApplication mApplication;
    private LockPatternUtils mLockPatternUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        Utils.init(this);
        BaseApi.host(BaseApi.HostType.TEST_150, true);  //初始化api环境


        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "fce6ddb348", true);

    }

    /**
     * bugly热修复
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }


    /**
     * 利用单例模式获取HuoQApplication实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (null == mApplication) {
            mApplication = new BaseApplication();
        }
        return mApplication;
    }

    public LockPatternUtils getLockPatternUtils() {
        if (mLockPatternUtils == null) {
            mLockPatternUtils = new LockPatternUtils(mApplication);
        }
        return mLockPatternUtils;
    }
}
