package com.demo.interview.java.data_structure.collection_frame.collection.list;

import android.os.Build;

import java.util.ArrayList;
import java.util.function.Consumer;

import androidx.annotation.RequiresApi;

/**
 * 文 件 名：ArrayListTest
 * 创 建 人：李跃龙
 * 创建日期：2020/5/27 17:09
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * <p>
 * 1、ArrayList是一个相对来说比较简单的数据结构，最重要的一点就是它的自动扩容，每次扩容50%
 * 并将当前数组中的所有元素都复制到新数组中
 * 增加 add(e);
 * 删除 remove(i);
 * 修改 set(i,e);
 * 查询 get(i);
 * 2、有序，可保存null,允许重复
 * 3、对于随机访问的get和set方法，ArrayList要优于LinkedList
 */
public class ArrayListTest {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
        for (String string : list) {
            System.out.println(string);
        }
        list.add(null);//可保存null
        list.remove(0);
        list.set(0, "你好");
        boolean isHas = list.contains("100");
        list.forEach(s -> System.out.println(s));
        String[] lists = (String[]) list.toArray();
    }
}
