package com.demo.interview.widget.android;

import android.widget.ListView;

import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：ListviewActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/26 9:59
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ListviewActivity extends BaseActivity {
    
    private ListView listView;
    
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //设置空数据界面
        //listView.setEmptyView();
        //listView.addFooterView();
//        listView.addHeaderView();
    }

    @Override
    public void initData() {

    }
}
