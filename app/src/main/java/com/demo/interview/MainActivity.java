package com.demo.interview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.interview.animation.AnimationActivity;
import com.demo.interview.animation.LottieAnimActivity;
import com.demo.interview.base.BaseActivity;
import com.demo.interview.data_store.DataStoreActivity;
import com.demo.interview.four_component.launch_activity.ActivityOne;
import com.demo.interview.four_component.service.TestServiceAc;
import com.demo.interview.fragment.FragmentActivity;
import com.demo.interview.message.HandlerActivity;
import com.demo.interview.mvc_mvp_mvvm.StructureActivity;
import com.demo.interview.network.socket.TCPClientActivity;
import com.demo.interview.performance_optimize.PerforOptActivity;
import com.demo.interview.screen_adapter.ScreenAdapterActivity;
import com.demo.interview.third_sdk.rxjava.RxJavaActivity;
import com.demo.interview.widget.android.CoordinatorLayoutActivity;
import com.demo.interview.widget.android.CoordinatorLayoutActivitytest;
import com.demo.interview.widget.custom.MyCustomViewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        findViewById(R.id.btn_animation).setOnClickListener(this);
        findViewById(R.id.btn_rxjava).setOnClickListener(this);
        findViewById(R.id.btn_perfor_opt).setOnClickListener(this);
        findViewById(R.id.btn_data_store).setOnClickListener(this);
        findViewById(R.id.btn_custom_view).setOnClickListener(this);
        findViewById(R.id.btn_socket).setOnClickListener(this);
        findViewById(R.id.btn_structure).setOnClickListener(this);
        findViewById(R.id.btn_screen).setOnClickListener(this);
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
            case R.id.btn_animation:
                startActivity(new Intent(MainActivity.this, LottieAnimActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                break;
            case R.id.btn_perfor_opt:
                startActivity(new Intent(MainActivity.this, PerforOptActivity.class));
                break;
            case R.id.btn_data_store:
                startActivity(new Intent(MainActivity.this, DataStoreActivity.class));
                break;
            case R.id.btn_custom_view:
                startActivity(new Intent(MainActivity.this, MyCustomViewActivity.class));
                break;
            case R.id.btn_socket:
                startActivity(new Intent(MainActivity.this, TCPClientActivity.class));
                break;
            case R.id.btn_structure:
                startActivity(new Intent(MainActivity.this, StructureActivity.class));
                break;
            case R.id.btn_test:
                ToastUtils.showShort("点击了");
                break;
            //测试
            case R.id.btn_screen:
                Map<String, String> map = new HashMap<>();
                map.put("1", "test1");
                map.put("2", "test2");
                map.put("3", "test3");
                JSONObject jsonObject1 = new JSONObject(map);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("roles", jsonObject1);
                    jsonObject.put("orderId", "1234455");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LogUtils.d("json:" + jsonObject.toString());
                startActivity(new Intent(MainActivity.this, ScreenAdapterActivity.class));
                break;
            case R.id.btn_layout:
//                startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity.class));
                startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivitytest.class));

                break;
            default:
                break;
        }
    }


}
