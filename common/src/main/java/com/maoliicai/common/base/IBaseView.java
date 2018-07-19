package com.maoliicai.common.base;

import io.reactivex.ObservableTransformer;

/**
 * <p>View接口的基类</p>
 *
 * @author yangyoupeng
 * @name IBaseView
 */

public interface IBaseView {
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param
     * @return
     */
    <T> ObservableTransformer<T, T> bindLifeycle();

    /**
     * 显示正在加载
     */
    void showProgress();

    /**
     * 隐藏正在加载
     */
    void hideProgress();

}
