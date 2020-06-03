package com.demo.interview.java.data_structure.collection_frame.collection;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

/**
 * 文 件 名：Sleep_Wait_Test
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 17:08
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        //add添加元素，放在队尾
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arrayList:" + arrayList.get(i));
        }
        System.out.println("arrayList:" + arrayList.set(0, "5"));
        arrayList.remove(0);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arrayList:" + arrayList.get(i));
        }
        System.out.println("\n\n\n");

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        linkedList.addFirst(100);
        linkedList.addLast(1000);

//
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("peek:" + linkedList.peek());


        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a", 1);
        hashMap.put("b", 1);
        hashMap.put("c", 3);
        hashMap.put("d", 4);
        hashMap.put("e", null);//允许空值
        System.out.println("hashmap" + hashMap.size());
        for (int i = 0; i < hashMap.size(); i++) {

        }

        Vector<String> vector=new Vector<>();

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
