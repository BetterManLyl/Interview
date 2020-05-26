package com.demo.interview.java.data_structure.collection_frame.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 文 件 名：BlcokQueueT
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 16:46
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class BlcokQueueT {

    public static void main(String[] args) {

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            //如果队列满了，则崩溃
//            queue.add(i);
            //向队列中插入元素，插入成功返回true；插入失败返回false
            queue.offer(i);
            System.out.println("添加" + i);
        }
        for (int i = 0; i < queue.size(); i++) {
            //获取并移除头部的元素，如果为空则抛异常
            System.out.println("取数据：" + queue.peek());
        }
        System.out.println("大小：" + queue.size());
        BlcokQueueT blcokQueueT = new BlcokQueueT();
        blcokQueueT.startThread();
    }

    private void startThread() {
        new MyThread_1().start();

        new MyThread_2().start();
        new MyThread().start();
    }


    private ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    private void addData(String value) {
        System.out.println("添加数据" + value);
        arrayBlockingQueue.add(value);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                addData("第" + i + "个");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThread_1 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    System.out.println("取数据" + arrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThread_2 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    System.out.println("取数据" + arrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
