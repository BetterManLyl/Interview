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
 * 1、同步锁 优缺点
 * 优点：解决了线程的安全机制
 * 缺点：相对降低了效率，因为同步外的线程都会判断同步锁
 * <p>
 * 同步的前提：
 * 同步中必须有多个线程并使用同一个锁
 */
public class Synchronized_Test {


    public static void main(String[] args) {
        Thread_01 thread_01 = new Thread_01();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
        new Thread(thread_01).start();
    }


}

class Thread_01 implements Runnable {
    private int num = 100;
    private boolean isStop = false;
    private Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            //加上同步锁实现线程安全
            //synchronized (Synchronized_Test.class)
            //同步代码块
//            synchronized (lock) {
                add();
//            }
        }
    }

    /**
     * 同步函数
     */
    public synchronized void add() {
        if (num > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-name" + Thread.currentThread().getName() + " num:" + num--);
        } else {
            return;
        }
    }
}
