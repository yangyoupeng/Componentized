package com.maoliicai.module_found.contract;

import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;

/**
 * yangyoupeng  on 2018/5/2.
 */

public interface IFoundFragmentContract {

    interface View extends IBaseView {

    }

    abstract class Presenter extends BasePresenter<View> {

    }

}
