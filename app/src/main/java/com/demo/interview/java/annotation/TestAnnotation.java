package com.demo.interview.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文 件 名：TestAnnotation
 * 创 建 人：李跃龙
 * 创建日期：2020/8/21 0021 11:02
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：注解的使用
 * https://blog.csdn.net/xsp_happyboy/article/details/80987484
 * <p>
 * 元注解：专门修饰注解的注解
 * <p>
 * 第一步，定义注解——相当于定义标记；
 * 第二步，配置注解——把标记打在需要用到的程序代码中；
 * 第三步，解析注解——在编译期或运行时检测到标记，并进行特殊操作。
 */

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {

    public String getName() default "lyl";

    public int age() default 28;

    public String sex() default "man";

}
