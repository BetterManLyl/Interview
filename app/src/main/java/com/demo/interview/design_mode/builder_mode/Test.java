package com.demo.interview.design_mode.builder_mode;

/**
 * 文 件 名：Test
 * 创 建 人：李跃龙
 * 创建日期：2020/5/31 20:42
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Test {
    public static void main(String args) {
        new Product.Builder()
                .setAge(10)
                .setName("lyl")
                .build();
    }
}
