package com.maoliicai.module_launch.presenter;

import com.google.gson.Gson;
import com.maoliicai.common.https.observers_extension.ProgressObserver;
import com.maoliicai.common.https.scheduler.RxSchedulers;
import com.maoliicai.module_launch.base.BaseModule;
import com.maoliicai.module_launch.contract.IFrameworkActivityContract;
import com.maoliicai.module_launch.module.bean.TestBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * yangyoupeng  on 2018/5/17.
 */

public class FrameworkActivityPresenter extends IFrameworkActivityContract.Presenter {
    private static final String TAG = "FrameworkActivityPresen";


    /**
     * 1 强制更新弹窗
     */
    @Override
    public void httpTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "");
        map.put("2", "");
        map.put("3", "");
        Gson gson = new Gson();
        String s2 = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s2);

        BaseModule.createrRetrofit()
                .getUserIfExists(requestBody)
                .compose(RxSchedulers.observableIO2Main(getView()))
                .subscribe(new ProgressObserver<TestBean>(this) {
                    @Override
                    public void onSuccess(TestBean result) {

                    }
                });
    }

}
