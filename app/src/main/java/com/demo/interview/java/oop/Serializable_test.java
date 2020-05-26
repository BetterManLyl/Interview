package com.demo.interview.java.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * 文 件 名：Serializable_test
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 23:33
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Serializable_test {

    public static void main(String[] args) {

    }

    private void test() {
        People people = new People();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(people);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
