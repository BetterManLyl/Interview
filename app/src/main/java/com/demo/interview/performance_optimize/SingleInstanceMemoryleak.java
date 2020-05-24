package com.demo.interview.performance_optimize;

import android.content.Context;

/**
 * 文 件 名：SingleInstanceMemoryleak
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 09:54
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：演示单例模式引起的内存泄漏
 */

/**
 * // 创建单例时，需传入一个Context
 * // 若传入的是Activity的Context，此时单例 则持有该Activity的引用
 * // 由于单例一直持有该Activity的引用（直到整个应用生命周期结束），
 * 即使该Activity退出，该Activity的内存也不会被回收
 * // 特别是一些庞大的Activity，此处非常容易导致OOM
 * <p>
 * <p>
 * 解决方案
 * 单例模式引用的对象的生命周期 = 应用的生命周期
 * 应传递Application的Context，因Application的生命周期 = 整个应用的生命周期
 */
public class SingleInstanceMemoryleak {

    private static SingleInstanceMemoryleak instanceMemoryleak;
    private Context mContext;

    public static SingleInstanceMemoryleak getInstanceMemoryleak(Context context) {
        if (null == instanceMemoryleak)
            instanceMemoryleak = new SingleInstanceMemoryleak(context);
        return instanceMemoryleak;
    }

    private SingleInstanceMemoryleak(Context context) {
//        this.mContext = context;
        this.mContext = context.getApplicationContext();// 传递的是Application 的context
    }
}
