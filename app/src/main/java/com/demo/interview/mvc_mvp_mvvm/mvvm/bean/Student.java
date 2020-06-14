package com.demo.interview.mvc_mvp_mvvm.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * 文 件 名：Student
 * 创 建 人：李跃龙
 * 创建日期：2020/6/9 15:21
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Student extends BaseObservable {

    @Bindable
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyChange();
    }
}
