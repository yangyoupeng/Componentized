package com.maoliicai.module_found.base;

import com.maoliicai.common.https.RetrofitManager;

/**
 * yangyoupeng  on 2018/5/3.
 */

public class BaseModule {

    public static FoundService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(FoundService.class);
    }

}
