package com.demo.interview.widget.custom;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：MyCustomViewActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/27 8:51
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyCustomViewActivity extends BaseActivity {

    private MyCustomView myCustomView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        myCustomView = findViewById(R.id.custom_view);
        
        findViewById(R.id.btn_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCustomView.setTxt("hello java");
            }
        });
        myCustomView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("dispatchTouchEvent");
        //return true;
        //return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("onTouchEvent");
        return super.onTouchEvent(event);
    }
}
