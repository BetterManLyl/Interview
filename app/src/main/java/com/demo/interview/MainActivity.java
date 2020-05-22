package com.demo.interview;

import android.content.Intent;
import android.view.View;

import com.demo.interview.base.BaseActivity;
import com.demo.interview.fragment.FragmentActivity;
import com.demo.interview.four_component.launch_activity.ActivityOne;
import com.demo.interview.message.HandlerActivity;
import com.demo.interview.four_component.service.TestServiceAc;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_activity_start_mode).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
        findViewById(R.id.btn_service).setOnClickListener(this);

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
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                break;
            case R.id.btn_handler:
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(MainActivity.this, TestServiceAc.class));
                break;
            default:
                break;
        }
    }
}
