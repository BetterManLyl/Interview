package com.demo.interview.widget.android.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * 文 件 名：VerticalLinearLayout
 * 创 建 人：李跃龙
 * 创建日期：2020/7/10 11:13
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class VerticalLinearLayout extends LinearLayout {
    private int maxOffsetY;

    public VerticalLinearLayout(Context context) {
        super(context);
        init();
    }

    public VerticalLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        View child;
        int childCount = getChildCount();
        int offset = 0;
        for(int i=0;i<childCount;i++){
            child = getChildAt(i);
            if(child!=null && child.getVisibility()!=View.GONE
                    && !(child instanceof ViewPager)  && !(child instanceof TabLayout)){
                measureChildWithMargins(child,widthMeasureSpec,0,MeasureSpec.UNSPECIFIED,0);
                offset = offset + child.getMeasuredHeight();
            }
        }
        this.maxOffsetY = offset;//可滑出屏幕的最大距离
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(specSize + offset,MeasureSpec.EXACTLY));
    }

    public int getMaxOffsetY(){
        return maxOffsetY;
    }

}
