package com.demo.interview.design_mode.factory_simple;

/**
 * 文 件 名：ProductA
 * 创 建 人：李跃龙
 * 创建日期：2020/5/25 21:16
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ProductA implements IProduct {
    @Override
    public void create() {
        System.out.println("create A");
    }
}
