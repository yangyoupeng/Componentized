package com.maoliicai.module_my_assets.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.maoliicai.common.base.BaseMvpFragment;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.mvp_senior.annotations.CreatePresenterAnnotation;
import com.maoliicai.module_my_assets.R;
import com.maoliicai.module_my_assets.contract.MyAssetsContract;
import com.maoliicai.module_my_assets.presenter.MyAssetsPresenter;

/**
 * yangyoupeng  on 2018/4/4.
 */
@CreatePresenterAnnotation(MyAssetsPresenter.class)
@Route(path = RouterPath.MY_ASSETS_FRAGMENT) // 路由地址，必须注明
public class MyAssetsFragment extends BaseMvpFragment<MyAssetsContract.View, MyAssetsPresenter> implements MyAssetsContract.View {


    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_my_assets;
    }



}
