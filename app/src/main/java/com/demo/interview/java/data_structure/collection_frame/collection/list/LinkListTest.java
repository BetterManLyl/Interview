package com.demo.interview.java.data_structure.collection_frame.collection.list;

import java.util.LinkedList;

/**
 * 文 件 名：LinkListTest
 * 创 建 人：李跃龙
 * 创建日期：2020/5/28 9:12
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 1、以双向链表实现。链表无容量限制，但双向链表本身使用了更多空间，也需要
 * 额外的链表指针操作。
 */
public class LinkListTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add("第" + i + "个");
        }
        linkedList.addFirst("a");
        linkedList.addLast("b");
        linkedList.getFirst();
        linkedList.getLast();
        linkedList.set(0,"测试修改");
        linkedList.removeFirst();
        linkedList.removeLast();
    }
}
