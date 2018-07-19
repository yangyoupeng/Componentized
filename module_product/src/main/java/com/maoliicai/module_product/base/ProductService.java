package com.maoliicai.module_product.base;

import com.maoliicai.common.base.BaseResponse;
import com.maoliicai.common.bean.MyAccountBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * yangyoupeng  on 2018/4/25.
 */

public interface ProductService {
    /**
     * 产品列表
     */
    @POST(ProductApi.PRODUCT_LIST)
    Observable<BaseResponse> getProductList(@Body RequestBody requestBody);
}
