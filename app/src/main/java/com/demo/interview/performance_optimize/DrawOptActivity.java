package com.demo.interview.performance_optimize;

import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：DrawOptActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 13:59
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * <p>
 * 参考博客
 * https://www.jianshu.com/p/cbdaeb1bede5
 *
 * <p>
 * 绘制性能的好坏 主要影响 ：Android应用中的页面显示速度
 * 绘制影响Android性能的实质：页面的绘制时间
 * 主要优化方向是：
 * <p>
 * 1、降低View.onDraw（）的复杂度
 * onDraw（）中不要创建新的局部对象
 * 避免onDraw（）执行大量 & 耗时操作
 * 2、避免过度绘制（Overdraw）
 *
 * 优化方案1： 移除默认的 Window 背景
 * 优化方案2：移除 控件中不必要的背景
 * 优化方案3：减少布局文件的层级（减少不必要的嵌套）
 * 优化方案4：自定义控件View优化：使用 clipRect() 、 quickReject()
 */
public class DrawOptActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
