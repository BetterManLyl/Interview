package com.demo.interview.third_sdk.rxjava;

import java.util.List;

/**
 * 文 件 名：Student
 * 创 建 人：李跃龙
 * 创建日期：2020/5/23 22:18
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Student {
    private String name;
    private String sex;

    private List<Source> list;

    public Student(String name, String sex, List<Source> list) {
        this.name = name;
        this.sex = sex;
        this.list = list;
    }

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

    public List<Source> getList() {
        return list;
    }

    public void setList(List<Source> list) {
        this.list = list;
    }

    public static class Source {

        public Source(String sourceName, String id) {
            this.sourceName = sourceName;
            this.id = id;
        }

        private String sourceName;
        private String id;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
