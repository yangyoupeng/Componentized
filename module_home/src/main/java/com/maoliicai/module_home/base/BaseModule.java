package com.maoliicai.module_home.base;

import com.maoliicai.common.https.RetrofitManager;

/**
 * yangyoupeng  on 2018/5/3.
 */

public class BaseModule {

    public static HomeService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(HomeService.class);
    }

}
