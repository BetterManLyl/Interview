package com.demo.interview.audio_video.camera.common;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * 文 件 名：ThreadHandler
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 13:28
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */

public class ThreadHandler extends Handler {
    private HandlerThread mThread;
    private Handler mHandler;

    public ThreadHandler(String name) {
        mThread = new HandlerThread(name);
    }

    public Handler create() {
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
        return mHandler;
    }

    public void destroy() {
        mThread.quit();
        mHandler = null;
    }
}