package com.demo.interview.java.reflect;

import com.blankj.utilcode.util.LogUtils;

/**
 * 文 件 名：ReflectBean
 * 创 建 人：李跃龙
 * 创建日期：2020/6/3 21:22
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ReflectBean {

    public String name;
    private int age;
    protected String id;

    public ReflectBean(String name, int num) {

    }

    private void test_1() {
        System.out.println("test_1");
    }

    private void test_1(int num) {
        System.out.println("test_1 num");
    }

    private void test_1(String name) {
        System.out.println("test_1 name");
    }
}
