package com.demo.interview.screen_adapter;

import android.view.View;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import me.jessyan.autosize.internal.CancelAdapt;

/**
 * 文 件 名：ScreenAdapterActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/7/6 16:16
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 不进行适配页面
 */
public class ScreenNoAdapterActivity extends BaseActivity implements CancelAdapt {
    @Override
    public int getLayoutId() {
        return R.layout.activity_no_screen_adapter;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {

    }
}
