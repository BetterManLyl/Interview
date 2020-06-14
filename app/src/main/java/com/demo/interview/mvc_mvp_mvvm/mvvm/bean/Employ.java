package com.demo.interview.mvc_mvp_mvvm.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * 文 件 名：Employ
 * 创 建 人：李跃龙
 * 创建日期：2020/6/14 22:33
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Employ extends BaseObservable {

    private boolean isFired;

    private String firstName;

    private String lastName;

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Bindable
    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }
}
