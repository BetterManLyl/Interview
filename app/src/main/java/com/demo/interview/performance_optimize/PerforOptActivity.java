package com.demo.interview.performance_optimize;

import android.content.Intent;
import android.view.View;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：PerforOptActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 09:30
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：性能优化
 */
public class PerforOptActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_perfor_opt;
    }

    @Override
    public void initView() {

        findViewById(R.id.btn_memory_opt).setOnClickListener(this);
        findViewById(R.id.btn_bitmap_opt).setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_memory_opt:
                startActivity(new Intent(this, MemoryOptActivity.class));
                break;
            case R.id.btn_bitmap_opt:
                startActivity(new Intent(this, BitmapPerforOptActivity.class));
                break;
            default:
                break;
        }
    }
}
