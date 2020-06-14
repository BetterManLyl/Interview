package com.demo.interview.mvc_mvp_mvvm.mvvm;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;

/**
 * 文 件 名：ObservableGoods
 * 创 建 人：李跃龙
 * 创建日期：2020/6/14 17:54
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ObservableGoods {

    public ObservableField<String> name;

    public ObservableFloat price;

    public ObservableField<String> details;

    public ObservableGoods(String name, float price, String details) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price);
        this.details = new ObservableField<>(details);
    }
}
