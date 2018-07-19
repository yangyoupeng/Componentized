package com.maoliicai.common.https.observers_extension;


import android.support.annotation.NonNull;

import com.maoliicai.common.base.BasePresenter;

import io.reactivex.disposables.Disposable;

/**
 * yangyoupeng  on 2018/4/12.
 * <p>
 * 扩展类，用来调用请求网络时的进度条
 */

public abstract class ProgressObserver<T> extends BaseObserver<T> {
    private BasePresenter mBasePresenter;


    protected ProgressObserver(BasePresenter basePresenter) {
        this.mBasePresenter = basePresenter;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!d.isDisposed()) {
            mBasePresenter.getView().showProgress();
        }
    }

    @Override
    public void onComplete() {
        if (mBasePresenter.isViewAttached()) {
            mBasePresenter.getView().hideProgress();
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        if (mBasePresenter.isViewAttached()) {
            mBasePresenter.getView().hideProgress();
        }
    }


}
