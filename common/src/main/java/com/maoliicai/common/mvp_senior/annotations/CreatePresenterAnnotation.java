package com.maoliicai.common.mvp_senior.annotations;

import com.maoliicai.common.base.BasePresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * yangyoupeng  on 2018/4/25.
 * <p>
 * 一个创建presenter的注解
 * @Inherited  这个注解表示  只能在类上使用并且是运行时
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenterAnnotation {

    Class<? extends BasePresenter> value();
}
