package com.demo.interview.thread;

/**
 * 文 件 名：MyRunnable
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 15:05
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注： 
 * 创建线程 方法二：实现Runnable接口
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable");
    }
}
