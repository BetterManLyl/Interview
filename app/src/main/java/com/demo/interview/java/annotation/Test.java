package com.demo.interview.java.annotation;

import java.lang.invoke.CallSite;
import java.lang.reflect.Method;

/**
 * 文 件 名：Test
 * 创 建 人：李跃龙
 * 创建日期：2020/8/21 0021 11:18
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Test {
    public static void main(String[] args) {
        try {
            Class student = Class.forName("com.demo.interview.java.annotation.Student");
            Method method = student.getMethod("study", int.class);
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                System.out.println("Student 类 配置了TestAnnotation注解");
                TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                System.out.println("name:" + testAnnotation.getName() + ",age:" + testAnnotation.age() + ",sex:" + testAnnotation.sex());
            } else {
                System.out.println("Student 类 没有配置了TestAnnotation注解");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}


