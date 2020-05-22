package com.demo.interview.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：TestServiceAc
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 10:11
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class TestServiceAc extends BaseActivity implements View.OnClickListener {
    private Intent intent;
    private MyService.MyBinder myBinder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_service;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.d("onServiceConnected");
            myBinder = (MyService.MyBinder) service;
            myBinder.test();
            myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.d("onServiceDisconnected");
        }
    };

    @Override
    public void initData() {
        intent = new Intent(this, MyService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(intent);
                break;
            case R.id.btn_stop_service:
                stopService(intent);
                break;
            case R.id.btn_bind_service:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(serviceConnection);
                break;
            default:
                break;
        }
    }
}
