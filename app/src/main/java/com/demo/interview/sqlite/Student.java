package com.demo.interview.sqlite;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 文 件 名：Student
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 21:08
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Student extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
