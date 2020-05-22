package com.demo.interview.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;

import androidx.annotation.Nullable;

/**
 * 文 件 名：MyService
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 10:14
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyService extends Service {

    private MyBinder myBinder;

    @Override
    public void onCreate() {
        LogUtils.d("onCreate");
        
        setFrontService();
        
        super.onCreate();
    }

    /**
     * 设置前台Service
     */
    private void setFrontService() {
        Intent notificationIntent=new Intent(this,TestServiceAc.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        //新建Builer对象
        Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        myBinder = new MyBinder();
        LogUtils.d("onBind");
        return myBinder;
    }

    @Override
    public void onDestroy() {
        LogUtils.d("onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("onUnbind");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public MyBinder() {

        }

        public void test() {
            LogUtils.d("test");
        }

        public MyService getService() {
            return MyService.this;
        }

    }
}
