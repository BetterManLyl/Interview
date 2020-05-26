package com.demo.interview.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 文 件 名：MyThread
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 15:02
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 创建线程 方法一 继承Thread类
 */
public class MyThread extends Thread {

    private static volatile boolean isFinish = false;

    public MyThread() {
        super("MyThread");
    }

    private int i;

    @Override
    public void run() {
        super.run();
        while (!this.isInterrupted()) {
            i++;
            System.out.println("运行中:" + i);
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

//        myThread.setName("MyThread");
//        myThread.setPriority(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
//        isFinish = true;
//        Thread thread=new Thread(new MyRunnable());
//        thread.start();

        ExecutorService service = new ThreadPoolExecutor(5, 10, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ExecutorService");
            }
        });

        //四种不用的线程池
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(3);
        Executors.newCachedThreadPool();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 3, TimeUnit.SECONDS);
    }
    
    ArrayBlockingQueue arrayBlockingQueue;
    LinkedBlockingQueue linkedBlockingQueue;
    BlockingQueue blockingQueue;
}
