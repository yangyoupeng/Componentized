package com.maoliicai.module_home.base;

import com.maoliicai.common.base.BaseResponse;


import retrofit2.http.POST;
import io.reactivex.Observable;
import retrofit2.http.Headers;


/**
 * yangyoupeng  on 2018/5/4.
 * <p>
 * 首页retrofit  接口
 */

public interface HomeService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST(HomeApi.home_new)
    Observable<BaseResponse> homeNew();


}