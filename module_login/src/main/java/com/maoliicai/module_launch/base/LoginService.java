package com.maoliicai.module_launch.base;


import com.maoliicai.common.base.BaseResponse;
import com.maoliicai.module_launch.module.bean.TestBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginService {


    @POST(LoginApi.LOGIN_USER_IF_EXISTS)
    Observable<BaseResponse<TestBean>> getUserIfExists(@Body RequestBody requestBody);


}
