package com.demo.interview;

import android.app.Application;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * 文 件 名：App
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 21:03
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class App extends LitePalApplication {
    
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        
        //测试分支合并
        //测试分支冲突
    }
}
