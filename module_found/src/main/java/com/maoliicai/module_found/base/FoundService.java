package com.maoliicai.module_found.base;

import com.maoliicai.common.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * yangyoupeng  on 2018/5/3.
 */

public interface FoundService {


    /**
     * 发现列表
     */
//    @FormUrlEncoded
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST(FoundApi.FIND)
    Observable<BaseResponse> find(@Body RequestBody route);


}
