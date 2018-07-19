package com.maoliicai.module_product.base;

import com.maoliicai.common.https.RetrofitManager;
import com.maoliicai.module_product.base.ProductService;

/**
 * yangyoupeng  on 2018/5/7.
 */

public class BaseModule {

   public static ProductService createrRetrofit() {
      return RetrofitManager.getInstance().getRetrofitService(ProductService.class);
   }
}
