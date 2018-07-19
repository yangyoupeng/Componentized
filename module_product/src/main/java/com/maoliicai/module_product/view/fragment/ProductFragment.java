package com.maoliicai.module_product.view.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.mvp_senior.annotations.CreatePresenterAnnotation;
import com.maoliicai.module_product.R;
import com.maoliicai.module_product.base.BaseProductFragment;
import com.maoliicai.module_product.contract.IProductFragmentContract;
import com.maoliicai.module_product.presenter.ProductFragmentPresenter;

/**
 * yangyoupeng  on 2018/4/4.
 * <p>
 * <p>
 * 产品列表页面
 */
@CreatePresenterAnnotation(ProductFragmentPresenter.class)
@Route(path = RouterPath.PRODUCT_FRAGMENT) // 路由地址，必须注明
public class ProductFragment extends BaseProductFragment<IProductFragmentContract.View, ProductFragmentPresenter>
        implements IProductFragmentContract.View {

    private static final String TAG = "ProductFragment";


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {

    }

}
