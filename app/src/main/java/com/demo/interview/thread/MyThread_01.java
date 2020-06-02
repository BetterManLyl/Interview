package com.demo.interview.thread;

import com.demo.interview.R;

/**
 * 文 件 名：MyThread_01
 * 创 建 人：李跃龙
 * 创建日期：2020/6/2 23:17
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyThread_01 {


    public static void main(String[] args) {
        Thread_01 thread_01 = new Thread_01();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();

    }

    static class Thread_01 implements Runnable {
        private int num = 100;

        @Override
        public void run() {
            while (true) {
                if (num > 0) {
                    try {
                        //线程不安全
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread-name" + Thread.currentThread().getName() + " num:" + num--);
                } else {
                    Thread.interrupted();
                }
            }
        }
    }
}

