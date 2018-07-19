package com.maoliicai.module_launch.contract;

import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;
import com.maoliicai.module_launch.activity.FrameworkActivity;

/**
 * yangyoupeng  on 2018/5/17.
 */

public interface IFrameworkActivityContract {

    interface View extends IBaseView {
        FrameworkActivity getThis();
    }

    abstract class Presenter extends BasePresenter<View> {
       public abstract void forcedToUpdate();//1.首页强制更新弹窗
    }

}
