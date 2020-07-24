package com.demo.interview.widget.android;

import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;
import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;
import com.demo.interview.widget.android.adapter.RecyclerViewAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

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
 * https://www.jianshu.com/p/02e557228d70
 * <p>
 * （1）scroll：标题栏会跟RecyclerView一起滑动（必选，不然标题栏就不会滑动）。
 *  （2）enterAlways：只要手指向下划滑动RecyclerView，标题栏就会马上跟着滑动出现；
 * 只要手指向上划滑动RecyclerView，标题栏就会马上跟着滑动消失。
 *  （3）exitUntilCollapsed：首先给标题栏设置一个最小高度，当手指向上滑动RecyclerView时，
 * RecyclerView先不会滑动，而是先滑动标题栏，当标题栏滑到设置的最小高度的时候才会滑动RecyclerView，
 * 当RecyclerView滑到底的时候再滑动剩余的标题栏，最后标题栏全部消失
 */
public class CoordinatorLayoutActivity extends BaseActivity {
    public static final int STATUS_EXPANDED = 1;
    public static final int STATUS_COLLAPSED = 2;

    private AppBarLayout appBarLayout;
    private TextView tv_title;
    /**
     * 默认展开状态
     */
    private int mStatus = STATUS_EXPANDED;

    private XRefreshView refreshview;
    private RecyclerView recyclerview;
    private XRefreshLayout xRefreshLayout;

    private int headHeight;
    private int minHeadTopHeight = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    private RecyclerViewAdapter adapter;

    @Override
    public void initView() {
        headHeight = (int) this.getResources().getDimension(R.dimen.appbar_height);
        appBarLayout = findViewById(R.id.appbar);
        tv_title = findViewById(R.id.tv_title);
        refreshview = findViewById(R.id.refreshview);
        recyclerview = findViewById(R.id.recyclerview);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("rrr" + i);
        }
        adapter = new RecyclerViewAdapter(arrayList);
        recyclerview.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtils.d("verticalOffset:" + verticalOffset + " appBarLayout:" + appBarLayout.getTotalScrollRange());
                if (-verticalOffset == appBarLayout.getTotalScrollRange()) {
                    if (tv_title.getVisibility() == View.GONE) {
                        tv_title.setVisibility(View.VISIBLE);
                    }
                } else if (-verticalOffset < appBarLayout.getTotalScrollRange() / 2) {
                    if (tv_title.getVisibility() == View.VISIBLE) {
                        tv_title.setVisibility(View.GONE);
                    }
                }
                //verticalOffset 向上滑动得到的值是负的，初始值为0 就是展开状态
                //剩下未滑出屏幕的高度
                int h = headHeight + verticalOffset;
                if (verticalOffset == 0) {
                    //展开状态
                    mStatus = STATUS_EXPANDED;
                } else if (h == minHeadTopHeight) {
                    mStatus = STATUS_COLLAPSED;
                } else {
                    mStatus = 0;
                }
            }
        });
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        xRefreshLayout = new XRefreshLayout(this);
        refreshview.setCustomHeaderView(xRefreshLayout);
        refreshview.setPinnedContent(true);
    }

    @Override
    public void initData() {

    }

    private boolean isDragDown;
    private int mLastX = 0;
    private int mLastY = 0;
    private int mTouchSlop;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("ylli10", "dispatchTouchEvent: ACTION_DOWN");
                isDragDown = false;
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ylli10", "dispatchTouchEvent: ACTION_MOVE");
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaY) >= Math.abs(deltaX) && deltaY >= mTouchSlop) {
                    isDragDown = true;
                } else {
                    isDragDown = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e("ylli10", "dispatchTouchEvent: ACTION_UP");
                mLastX = 0;
                mLastY = 0;
                isDragDown = false;
                break;
            default:
                break;
        }
        Log.e("ylli10", "isDragDown: " + isDragDown);
        canPtr(isDragDown);
        return super.dispatchTouchEvent(event);
    }

    private void canPtr(boolean isDragDown) {
        //展开 && recyclerView列表数据置顶 && 向下滑动时，可下来刷新
        //若正在刷新时，上滑，下拉刷新控件需要消耗事件，不往下传递事件
        Log.e("ylli101", "isDragDown:" + isDragDown +
                " mStatus:" + (mStatus == STATUS_EXPANDED) + " isRecyclerTop:" + isRecyclerTop());
        boolean isCanPtr = isDragDown && mStatus == STATUS_EXPANDED && isRecyclerTop();
        refreshview.disallowInterceptTouchEvent(!isCanPtr);
    }

    private boolean isRecyclerTop() {
        if (recyclerview != null) {
            RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View childAt = recyclerview.getChildAt(0);
                if (childAt == null || (firstVisibleItemPosition <= 1 && childAt.getTop() == 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
