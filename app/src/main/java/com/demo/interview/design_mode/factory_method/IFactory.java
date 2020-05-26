package com.demo.interview.design_mode.factory_method;

/**
 * 文 件 名：IFactory
 * 创 建 人：李跃龙
 * 创建日期：2020/5/25 21:49
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 *
 * 工厂方法模式(Factory Method Pattern)：定义一个用于创建对象的接口，
 *  * 让子类决定将哪一个类实例化。工厂方法模式让一个类的实例化延迟到其子类。
 *  * 工厂方法模式又简称为工厂模式(Factory Pattern)，又可称作虚拟构造器模式(Virtual Constructor Pattern)
 *  * 或多态工厂模式(Polymorphic Factory Pattern)。工厂方法模式是一种类创建型模式。
 */
public interface IFactory {
    IProduct createProduct();
}
