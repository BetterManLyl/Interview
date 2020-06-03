package com.demo.interview.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 文 件 名：Test
 * 创 建 人：李跃龙
 * 创建日期：2020/6/3 21:24
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：反射
 */
public class Test {
    public static void main(String[] args) {

        try {
            Class reflectClass = Class.forName("com.demo.interview.java.reflect.ReflectBean");
            Constructor[] constructor = reflectClass.getConstructors();

            //获取class对象的所有属性
            Field[] declaredFields = reflectClass.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                System.out.println(declaredFields[i].getName());
            }
            //获取class对象的所有public属性
            Field[] fields = reflectClass.getFields();

            //获取class对象的指定属性
            Field id = reflectClass.getDeclaredField("id");

            //获取class指定的public属性
            reflectClass.getField("name");

            //获取class对象的所有声明方法
            Method[] declaredMethods = reflectClass.getDeclaredMethods();

            //获取class对象的所有public方法 包括父类的方法
            Method[] methods = reflectClass.getMethods();

            reflectClass.getMethod("test_1",null);

            reflectClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
