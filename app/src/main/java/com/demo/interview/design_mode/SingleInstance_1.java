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
 * <p>
 * // 说明
 * * // 校验锁1：第1个if
 * * // 作用：若单例已创建，则直接返回已创建的单例，无需再执行加锁操作
 * * // 即直接跳到执行 return ourInstance
 * *
 * * // 校验锁2：第2个 if
 * * // 作用：防止多次创建单例问题
 * * // 原理
 * *   // 1. 线程A调用newInstance()，当运行到②位置时，此时线程B也调用了newInstance()
 * *   // 2. 因线程A并没有执行instance = new Singleton();，此时instance仍为空，因此线程B能突破第1层 if 判断，运行到①位置等待synchronized中的A线程执行完毕
 * *   // 3. 当线程A释放同步锁时，单例已创建，即instance已非空
 * *   // 4. 此时线程B 从①开始执行到位置②。此时第2层 if 判断 = 为空（单例已创建），因此也不会创建多余的实例
 */
public class SingleInstance_1 {

    private static SingleInstance_1 singleInstance_1 = null;

    private SingleInstance_1() {

    }

    public static SingleInstance_1 getSingleInstance_1() {
        if (null == singleInstance_1) {
            synchronized (SingleInstance_1.class) {
                if (null == singleInstance_1)
                    singleInstance_1 = new SingleInstance_1();
            }
        }
        return singleInstance_1;
    }
}
