package com.demo.interview.design_mode;

/**
 * 文 件 名：SingleInstance
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 18:03
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * <p>
 * 饿汉式
 */
public class SingleInstance {

    private static SingleInstance singleInstance = new SingleInstance();

    public static SingleInstance getSingleInstance() {
        return singleInstance;
    }

    private SingleInstance() {
    }
}
