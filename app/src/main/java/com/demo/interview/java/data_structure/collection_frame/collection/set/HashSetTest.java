package com.demo.interview.java.data_structure.collection_frame.collection.set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 文 件 名：HashSetTest
 * 创 建 人：李跃龙
 * 创建日期：2020/5/28 9:43
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：无序 不可重复 线程不安全
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("1");
        hashSet.add(null);

        Iterator iterator = hashSet.iterator();

        while (iterator.hasNext()) {
            System.out.println("hashset:" + iterator.next());
        }
    }

}
