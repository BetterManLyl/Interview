package com.demo.interview.message;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import androidx.annotation.NonNull;

/**
 * 文 件 名：HandlerThreadActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 9:42
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class HandlerThreadActivity extends BaseActivity {

    private HandlerThread handlerThread;
    private Handler handler;

    private TextView textView;

    private Button btn_start;

    @Override
    public int getLayoutId() {
        return R.layout.activity_handler_thread;
    }

    @Override
    public void initView() {
        textView = findViewById(R.id.tv_result);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(v -> {
            Message message = Message.obtain();
            message.what = 1;
            message.arg1 = 2;
            handler.sendMessage(message);
        });
    }

    @Override
    public void initData() {
        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        int message = msg.arg1;
                        LogUtils.d("消息：" + msg.arg1);
                        runOnUiThread(() -> {
                            LogUtils.d("Thread name 1:" + Thread.currentThread().getName());
                            //需要在主线程进行更新
                            textView.setText("接收到了消息：" + message);
                        });
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }
}
