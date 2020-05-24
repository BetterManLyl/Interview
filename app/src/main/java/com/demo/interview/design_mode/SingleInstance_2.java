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
 * 懒汉式
 */
public class SingleInstance_2 {

    private static volatile SingleInstance_2 singleInstance_1 = null;

    private SingleInstance_2() {

    }

    public static SingleInstance_2 getSingleInstance_1() {
        if (null == singleInstance_1) {
            singleInstance_1 = new SingleInstance_2();
        }
        return singleInstance_1;
    }
}
