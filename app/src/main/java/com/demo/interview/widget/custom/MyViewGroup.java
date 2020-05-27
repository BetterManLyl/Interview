package com.demo.interview.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;

import androidx.annotation.Nullable;

/**
 * 文 件 名：MyViewGroup
 * 创 建 人：李跃龙
 * 创建日期：2020/5/27 15:36
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyViewGroup extends LinearLayout {
    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("dispatchTouchEvent");
//        return super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        LogUtils.d("onInterceptTouchEvent");
        return super.onInterceptTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("onTouchEvent");
        return super.onTouchEvent(event);
    }

}
