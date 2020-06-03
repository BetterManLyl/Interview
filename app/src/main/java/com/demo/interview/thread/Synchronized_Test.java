package com.demo.interview.thread;

/**
 * 文 件 名：Synchronized_Test
 * 创 建 人：李跃龙
 * 创建日期：2020/6/2 23:17
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Synchronized_Test {

    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread_01 thread_01 = new Thread_01();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();

    }

    static class Thread_01 implements Runnable {
        private int num = 100;
        private boolean isStop = false;

        @Override
        public void run() {
            while (true) {
                //加上同步锁实现线程安全
                //synchronized (Synchronized_Test.class)
                synchronized (lock) {
                    if (num > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("thread-name" + Thread.currentThread().getName() + " num:" + num--);
                    } else {
                        break;
                    }
//                    System.out.println("Thread running");
                }
            }
        }
    }
}

