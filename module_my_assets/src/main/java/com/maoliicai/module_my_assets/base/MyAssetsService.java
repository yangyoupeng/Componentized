package com.maoliicai.module_my_assets.base;

import com.maoliicai.common.base.BaseResponse;
import com.maoliicai.common.bean.MyAccountBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public interface MyAssetsService {

    /**
     * 查询我的资产
     *
     * @param requestBody
     * @return
     */
    @POST(MyAssetsApi.MY_ASSETS_MYACCOUNT)
    Observable<BaseResponse> getMyAccount(@Body RequestBody requestBody);




}
