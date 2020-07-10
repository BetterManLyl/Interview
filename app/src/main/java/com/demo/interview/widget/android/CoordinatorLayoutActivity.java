package com.demo.interview.widget.android;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * 文 件 名：CoordinatorLayoutActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/7/9 15:46
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * Android悬浮折叠效果
 * 参考博客
 * https://blog.csdn.net/geduo_83/article/details/85452569
 * https://mp.weixin.qq.com/s/2u7Ei2tu1zs8V1QwP4Uwfg
 * <p>
 * （1）scroll：标题栏会跟RecyclerView一起滑动（必选，不然标题栏就不会滑动）。
 *  （2）enterAlways：只要手指向下划滑动RecyclerView，标题栏就会马上跟着滑动出现；
 * 只要手指向上划滑动RecyclerView，标题栏就会马上跟着滑动消失。
 *  （3）exitUntilCollapsed：首先给标题栏设置一个最小高度，当手指向上滑动RecyclerView时，
 * RecyclerView先不会滑动，而是先滑动标题栏，当标题栏滑到设置的最小高度的时候才会滑动RecyclerView，
 * 当RecyclerView滑到底的时候再滑动剩余的标题栏，最后标题栏全部消失
 */
public class CoordinatorLayoutActivity extends BaseActivity {
    private AppBarLayout appBarLayout;
    private TextView tv_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public void initView() {
        appBarLayout = findViewById(R.id.appbar);
        tv_title = findViewById(R.id.tv_title);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtils.d("verticalOffset:" + verticalOffset + " appBarLayout:" + appBarLayout.getTotalScrollRange());
                if (-verticalOffset == appBarLayout.getTotalScrollRange()) {
                    if (tv_title.getVisibility() == View.GONE) {
                        tv_title.setVisibility(View.VISIBLE);
                    }
                } else if (-verticalOffset < appBarLayout.getTotalScrollRange()/2) {
                    if (tv_title.getVisibility() == View.VISIBLE) {
                        tv_title.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}
