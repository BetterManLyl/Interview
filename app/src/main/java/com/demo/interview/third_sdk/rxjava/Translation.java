package com.demo.interview.third_sdk.rxjava;

import com.blankj.utilcode.util.LogUtils;

/**
 * 文 件 名：Translation
 * 创 建 人：李跃龙
 * 创建日期：2020/5/23 20:42
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Translation {
    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        LogUtils.d("RxJava:" + content.out);
    }
}
