package com.maoliicai.common.base;


import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Presenter的基类</p>
 *
 * @author yangyoupeng   2018、4、24
 * * 使用动态代理模式设计basePresenter的初忠：
 * 因为在使用mvp模式开发中，会遇到一些问题：
 * 比如：activity持有presenter的引用，presenter持有view的引用 ，这就导致几个问题，
 * 1.当activity销毁后，由于p持有activity的引用 导致activity无法释放，最终会引用内存泄漏
 * <p>
 * 2.p层处理完逻辑，调用v层来处理UI，怎么拿到V层的引用
 * <p>
 * 3.请求网络时，网络不太好，在这个时候用户没等到请求完成退出该页面，等获取数据成功之后再拿V的引用 ，这个时候view 有可能被销毁，v层的引用为空
 */

public abstract class BasePresenter<V extends IBaseView> {

    /**
     * 解决第一个问题
     * <p>
     * 弱引用, 防止内存泄漏
     */
    private WeakReference<V> mWeakReference;
    private V mProxyView;

    /**
     * 关联V层和P层
     *
     */
    public void attatchView(V v) {
        mWeakReference = new WeakReference<>(v);
        MvpViewHandler viewHandler = new MvpViewHandler(mWeakReference.get());
        mProxyView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), viewHandler);
    }


    /**
     * @return P层和V层是否关联.
     */
    public boolean isViewAttached() {
        return mWeakReference != null && mWeakReference.get() != null;
    }


    /**
     * 断开V层和P层
     * 在Acitivity的onDestory()中调用
     */
    public void detachView() {
        if (isViewAttached()) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /**
     * 解决第二个问题
     */


    public V getView() {
        return mProxyView;
    }


    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        Log.e("perfect-mvp","P onDestroy = ");
    }
    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     */
    public void onSaveInstanceState(Bundle presenterBundle) {
        Log.e("perfect-mvp","P onSaveInstanceState = ");
    }

    /**
     * 动态代理类
     */
    private class MvpViewHandler implements InvocationHandler {
        private IBaseView mvpView;

        MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //解决第三个问题，如果V层没被销毁, 执行V层的方法.
            try {
                if (isViewAttached()) {
                    return method.invoke(mvpView, args);
                }
            } catch (InvocationTargetException e){
                throw e.getCause();
            }


            //P层不需要关注V层的返回值
            return null;
        }
    }
}