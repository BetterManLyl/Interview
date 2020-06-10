package com.demo.interview.mvc_mvp_mvvm.mvvm;

import android.os.Bundle;
import android.view.View;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;
import com.demo.interview.databinding.ActivityMvvmBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * 文 件 名：MVVMActivity_test
 * 创 建 人：李跃龙
 * 创建日期：2020/6/9 15:30
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MVVMActivity_test extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding activityMvvmBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        activityMvvmBinding.btnModify.setOnClickListener(v -> {
            User user = new User();
            user.setAge(12);
            user.setName("lyl");
            activityMvvmBinding.setUserInfo(user);
        });
    }
}
