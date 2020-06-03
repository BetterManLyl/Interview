package com.demo.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
        try {
            this.wait(5000);
            System.out.println("wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (!this.isInterrupted()) {
//            i++;
//            System.out.println("运行中:" + i);
//        }
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

//        ExecutorService service = new ThreadPoolExecutor(5, 10, 10,
//                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("ExecutorService");
//            }
//        });

        //四种不用的线程池
//        Executors.newSingleThreadExecutor();
//        Executors.newFixedThreadPool(3);
//        Executors.newCachedThreadPool();
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 3, TimeUnit.SECONDS);
        Callable<Integer> myCallable = new MyCallable();    // 创建MyCallable对象
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable); //使用FutureTask来包装MyCallable对象
        Thread thread = new Thread(ft);   //FutureTask对象作为Thread对象的target创建新的线程

        thread.start();
        try {
            int value = ft.get();
            System.out.println("value:" + value);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyCallable implements Callable<Integer> {
        private int i = 0;

        // 与run()方法不同的是，call()方法具有返回值
        @Override
        public Integer call() {
            int sum = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum = i;
            }
            return sum;
        }
    }
//    ArrayBlockingQueue arrayBlockingQueue;
//    LinkedBlockingQueue linkedBlockingQueue;
//    BlockingQueue blockingQueue;
}
