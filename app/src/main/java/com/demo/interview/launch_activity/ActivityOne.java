package com.demo.interview.launch_activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.demo.interview.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 文 件 名：ActivityOne
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 9:17
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * 如果将ActivityOne的启动方式修改为singletask，那么从ActivityThree跳转到ActivityOne
 * 之后，ActivityTwo将会自动finish
 */
public class ActivityOne extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        LogUtils.d("onCreate");
        /**
         * 测试singleTop复用模式
         */
//        findViewById(R.id.skip).setOnClickListener(v -> startActivity(new Intent(ActivityOne.this, ActivityOne.class)));

        findViewById(R.id.skip).setOnClickListener(v -> startActivity(new Intent(ActivityOne.this, ActivityTwo.class)));


        /**
         * 如果给当前activity设置了android:configChanges="orientation|screenSize"
         * 那么在屏幕旋转的时候不会销毁该activity
         * 如果没有设置，则会销毁该activity，重新创建。
         */
        findViewById(R.id.btn_screen).setOnClickListener(v -> {
            if (!ScreenUtils.isLandscape()){
                /**
                 * 设置横屏
                 */
                ScreenUtils.setLandscape(ActivityOne.this);
            }else{
                /**
                 * 设置竖屏
                 */
                ScreenUtils.setPortrait(ActivityOne.this);
            }
        });
    }


    /**
     * 1、如果设置了android:configChanges="orientation|screenSize"
     * 屏幕旋转，生命周期不会改变，会回调onConfigurationChanged()该方法两次
     * 
     * 2、如果没有设置该参数，旋转屏幕，不会回调该方法
     * 屏幕旋转的生命周期改变过程
     * onPause()-->onStop()-->onDestroy()-->onCreate()-->onStart()-->onResume()
     * 
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d("onConfigurationChanged");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("onNewIntent");
    }
}
