package com.demo.interview.design_mode.singleton;

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
 * 静态内部类
 * <p>
 * 根据 静态内部类 的特性，同时解决了按需加载、线程安全的问题，同时实现简洁
 * 在静态内部类里创建单例，在装载该内部类时才会去创建单例
 * 线程安全：类是由 JVM加载，而JVM只会加载1遍，保证只有1个单例
 * <p>
 * 静态内部类的优点是：
 * 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存
 */
public class SingleInstance_3 {
    // 私有构造函数
    private SingleInstance_3() {

    }

    // 1. 创建静态内部类
    public static class SingleInstance_4 {
        // 在静态内部类里创建单例
        private static SingleInstance_3 singleInstance_3 = new SingleInstance_3();
    }

    // 延迟加载、按需创建
    public static SingleInstance_3 getInstance() {
        return SingleInstance_4.singleInstance_3;
    }

    public void test(){
        System.out.println("test SingleInstance_3");
    }
}
