package com.maoliicai.module_found.view.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.mvp_senior.annotations.CreatePresenterAnnotation;
import com.maoliicai.module_found.R;
import com.maoliicai.module_found.base.BaseFoundFragment;
import com.maoliicai.module_found.contract.IFoundFragmentContract;
import com.maoliicai.module_found.presenter.FoundFragmentPresenter;

/**
 * yangyoupeng  on 2018/4/4.
 */
@Route(path = RouterPath.FOUND_FRAGMENT) // 路由地址，必须注明
@CreatePresenterAnnotation(FoundFragmentPresenter.class)
public class FoundFragment extends BaseFoundFragment<IFoundFragmentContract.View, FoundFragmentPresenter>
        implements IFoundFragmentContract.View {

    private static final String TAG = "FoundFragment";



    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_found;
    }

}
