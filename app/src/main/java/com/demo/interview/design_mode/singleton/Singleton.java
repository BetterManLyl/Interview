package com.demo.interview.design_mode.singleton;

/**
 * 文 件 名：Singleton
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 18:16
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 原理
 * 根据枚举类型的下述特点，满足单例模式所需的 创建单例、线程安全、实现简洁的需求
 * 这是 最简洁、易用 的单例实现方式，借用《Effective Java》的话：
 * 单元素的枚举类型已经成为实现 Singleton的最佳方法
 */
public enum Singleton {//定义1个枚举的元素，即为单例类的1个实例
    INSTANCE;

    // 隐藏了1个空的、私有的 构造方法
    // private Singleton () {}


    public void test(){

    }
}

