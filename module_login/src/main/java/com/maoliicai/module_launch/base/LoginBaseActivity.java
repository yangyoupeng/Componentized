package com.maoliicai.module_launch.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.maoliicai.common.base.BaseMvpActivity;
import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;
import com.maoliicai.common.utils.StatusBarUtils;

/**
 * yangyoupeng  on 2018/4/20.
 */

public abstract class LoginBaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTransparent(this);
    }
}
