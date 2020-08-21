package com.demo.interview.java.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文 件 名：JavaScriptApi
 * 创 建 人：李跃龙
 * 创建日期：2020/8/21 0021 9:59
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JavaScriptApi {

    public boolean isFull() default false;

    public String name() default "123";

}
