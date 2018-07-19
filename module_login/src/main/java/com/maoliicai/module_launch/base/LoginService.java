package com.maoliicai.module_launch.base;


import com.maoliicai.common.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginService {


    @POST(LoginApi.LOGIN_USER_IF_EXISTS)
    Observable<BaseResponse> getUserIfExists(@Body RequestBody requestBody);


}
