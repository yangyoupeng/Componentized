package com.maoliicai.module_home.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.maoliicai.common.base.BaseMvpActivity;
import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;
import com.maoliicai.common.utils.StatusBarUtils;

/**
 * yangyoupeng  on 2018/4/25.
 * <p>
 * 产品基类
 */

public abstract class BaseHomeActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTransparent(this);//沉趁式  透明状态栏

    }
}
