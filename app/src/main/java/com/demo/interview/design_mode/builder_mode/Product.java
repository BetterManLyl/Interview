package com.demo.interview.design_mode.builder_mode;

import okhttp3.OkHttpClient;

/**
 * 文 件 名：Product
 * 创 建 人：李跃龙
 * 创建日期：2020/5/31 20:41
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Product {

    private String mName;
    private String mSex;
    private int age;
    private String num;
    private String nation;

    public Product(Builder builder) {
        mName = builder.mName;
        mSex = builder.mSex;
        age = builder.age;
        num = builder.num;
        nation = builder.nation;
    }

    public static class Builder {
        private String mName;
        private String mSex;
        private int age;
        private String num;
        private String nation;

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setSex(String sex) {
            this.mSex = sex;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setNum(String num) {
            this.num = num;
            return this;
        }

        public Builder setNation(String nation) {
            this.nation = nation;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
