package com.demo.interview;

import android.content.Intent;
import android.view.View;

import com.demo.interview.base.BaseActivity;
import com.demo.interview.launch_activity.ActivityOne;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_activity_start_mode).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_start_mode:
                startActivity(new Intent(MainActivity.this, ActivityOne.class));
                break;
            case R.id.btn_fragment:
                startActivity(new Intent(MainActivity.this, ActivityOne.class));
                break;
            default:
                break;
        }
    }
}
