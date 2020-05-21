package com.demo.interview.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

/**
 * 文 件 名：HandlerActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 16:54
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class HandlerActivity extends BaseActivity {

    private TextView textView;

    private MyHandler myHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_handler;
    }

    @Override
    public void initView() {
        textView = findViewById(R.id.tv);
        findViewById(R.id.btn_async_task).setOnClickListener(v -> startActivity(new Intent(HandlerActivity.this, AsyncTaskActivity.class)));
    }

    @Override
    public void initData() {
        myHandler = new MyHandler(this);
        new Thread(() -> {
            textView.setText("11111");
            try {
                Thread.sleep(1000);
                myHandler.sendEmptyMessage(22222);
            } catch (InterruptedException e) {
                LogUtils.e("error:" + e.getMessage());
            }
        }).start();
    }

    /**
     * 使用静态内部类并创建对Activity的弱引用
     * 防止内存泄漏
     */
    static class MyHandler extends Handler {

        private WeakReference<Activity> activityWeakReference = null;

        public MyHandler(Activity activity) {
            if (null == activityWeakReference)
                activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (null == activityWeakReference) {
                return;
            }
            if (null != activityWeakReference.get()) {
                HandlerActivity activity = (HandlerActivity)
                        activityWeakReference.get();
                activity.textView.setText(msg.what + "");
            }
        }
    }
}
