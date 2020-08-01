package com.demo.interview.animation;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.demo.interview.R;

/**
 * 文 件 名：LottieAnim
 * 创 建 人：李跃龙
 * 创建日期：2020/6/24 16:33
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * LottieAnim加载json动画
 * 参考博客
 */
public class LottieAnimActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        progressDialog = new ProgressDialog(this);

        lottieAnimationView = findViewById(R.id.lottie_loading);
//        lottieAnimationView.playAnimation();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lottieAnimationView.pauseAnimation();
//            }
//        }, 3000);


//        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog.setTips("12333333");
//                progressDialog.show();
//            }
//        });
//        findViewById(R.id.btn_start2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog.show();
//            }
//        });
//        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog.dismiss();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieAnimationView.cancelAnimation();
    }

    /**
     * 加载json 的url地址
     * @param url
     */
    private void loadUrl(String url){
        lottieAnimationView.setAnimationFromUrl(url);
        lottieAnimationView.playAnimation();
    }
}
