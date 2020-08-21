package com.demo.interview.java.annotation;

/**
 * 文 件 名：Student
 * 创 建 人：李跃龙
 * 创建日期：2020/8/21 0021 13:16
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Student {
    @TestAnnotation(getName = "cml", age = 26, sex = "女")
    public void study(int time) {
        for (int i = 0; i < 10; i++) {
            System.out.println("good good study day day up");
        }
    }
}
