package com.demo.interview.design_mode.factory_method;

/**
 * 文 件 名：FactoryProductA
 * 创 建 人：李跃龙
 * 创建日期：2020/5/25 21:51
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class FactoryProductA implements IFactory{
    @Override
    public IProduct createProduct() {
        return new ProductA();
    }
}
