package com.demo.interview.mvc_mvp_mvvm.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.demo.interview.BR;

/**
 * 文 件 名：User
 * 创 建 人：李跃龙
 * 创建日期：2020/6/9 15:20
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class User extends BaseObservable {
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private int age;

    private String sex;

    public boolean male = true;

    private String passWord;


    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyChange();
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;

    }
}
