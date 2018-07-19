package com.maoliicai.module_my_assets.contract;

import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;
import com.maoliicai.common.bean.MyAccountBean;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/4/28
 * @description
 */

public interface MyAssetsContract {

    interface View extends IBaseView {
    }

    abstract class Presenter extends BasePresenter<View> {

    }
}
