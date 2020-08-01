package com.demo.interview.screen_adapter;

import android.content.Intent;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：ScreenAdapterActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/7/6 16:16
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ScreenAdapterActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_screen_adapter;
    }

    @Override
    public void initView() {

        findViewById(R.id.btn_have_adapter).setOnClickListener(v -> 
                startActivity(new Intent(ScreenAdapterActivity.this,ScreenHaveAdapterActivity.class)));
        
        findViewById(R.id.btn_no_adapter).setOnClickListener(v -> 
                startActivity(new Intent(ScreenAdapterActivity.this,ScreenNoAdapterActivity.class)));
    }

    @Override
    public void initData() {

    }
}
