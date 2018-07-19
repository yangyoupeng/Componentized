package com.maoliicai.module_home.view.fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.mvp_senior.annotations.CreatePresenterAnnotation;
import com.maoliicai.module_home.R;
import com.maoliicai.module_home.base.BaseHomeFragment;
import com.maoliicai.module_home.contract.IHomeFragmentContract;
import com.maoliicai.module_home.presenter.HomeFragmentPresenter;

/**
 * yangyoupeng  on 2018/4/4.
 */
@CreatePresenterAnnotation(HomeFragmentPresenter.class)
@Route(path = RouterPath.HOME_FRAGMENT) // 路由地址，必须注明
public class HomeFragment extends BaseHomeFragment<IHomeFragmentContract.View, HomeFragmentPresenter>
        implements IHomeFragmentContract.View{

    private String mGuideStatus = "0";

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }





}
