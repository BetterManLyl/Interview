package com.demo.interview.thread;

/**
 * 文 件 名：Sleep_Wait_Test
 * 创 建 人：李跃龙
 * 创建日期：2020/6/2 16:50
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 1、sleep方法只让出了CPU，而并不会释放同步资源锁！！！
 * 2、wait可以释放锁资源
 */
public class Sleep_Wait_Test {

    public static void main(String[] args) {
        new Thread(new MyThread_1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new MyThread_2()).start();
    }

    public static class MyThread_1 implements Runnable {
        @Override
        public void run() {
            synchronized (Sleep_Wait_Test.class) {
                System.out.println("enter thread1 ...");
                System.out.println("thread1 is waiting");
                try {
                    //释放锁有两种方式：(1)程序自然离开监视器的范围，即离开synchronized关键字管辖的代码范围
                    //(2)在synchronized关键字管辖的代码内部调用监视器对象的wait()方法。这里使用wait方法
                    Sleep_Wait_Test.class.wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ...");
                System.out.println("thread1 is being over!");
            }
        }
    }

    public static class MyThread_2 implements Runnable {
        @Override
        public void run() {
            synchronized (Sleep_Wait_Test.class) {
                System.out.println("enter thread2 ...");
                System.out.println("thread2 notify other thread can release wait status ...");
                Sleep_Wait_Test.class.notify();
                System.out.println("thread2 is sleeping ten millisecond ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on ...");
                System.out.println("thread2 is being over!");
            }
        }
    }
}
