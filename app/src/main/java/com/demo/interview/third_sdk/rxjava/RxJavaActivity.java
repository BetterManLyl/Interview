package com.demo.interview.third_sdk.rxjava;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：RxJavaActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/23 20:59
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    public void initView() {

        findViewById(R.id.btn_create_rx_java).setOnClickListener(this);
        findViewById(R.id.btn_trans_rx_java).setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_rx_java:
                startActivity(new Intent(this, RxJavaCreateActivity.class));
                break;
            case R.id.btn_trans_rx_java:
                startActivity(new Intent(this, RxJavaTransActivity.class));
                break;
            default:
                break;
        }
    }
}
