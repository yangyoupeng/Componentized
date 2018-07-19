package com.maoliicai.module_launch.base;

import com.maoliicai.common.https.RetrofitManager;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public class BaseModule {

    public static LoginService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(LoginService.class);
    }
}
