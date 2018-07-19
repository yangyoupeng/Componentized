package com.maoliicai.common.https.scheduler;


import com.maoliicai.common.base.IBaseView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * yangyoupeng  on 2018/4/20.
 * <p>
 * 切换线程 与绑定rxlifeycle 生命周期
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> observableIO2Main(IBaseView iBaseView) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(iBaseView.bindLifeycle());
    }
}