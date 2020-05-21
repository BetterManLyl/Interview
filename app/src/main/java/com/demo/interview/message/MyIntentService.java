package com.demo.interview.message;

import android.app.IntentService;
import android.content.Intent;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 文 件 名：MyIntentService
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 17:23
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        
    }
}
