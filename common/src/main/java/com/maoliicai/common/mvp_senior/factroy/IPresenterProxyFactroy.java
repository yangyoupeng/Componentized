package com.maoliicai.common.mvp_senior.factroy;

import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;

/**
 * yangyoupeng  on 2018/4/25.
 * <p>
 * 提供get,set工厂方法
 * <p>
 * 目的为了扩展，可以自定义工厂，创建不同的presenter
 */

public interface IPresenterProxyFactroy<V extends IBaseView, P extends BasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(IMvpPresenterFactroy<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    IMvpPresenterFactroy<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();
}
