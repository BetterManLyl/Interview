package com.demo.interview.mvc_mvp_mvvm.mvvm.test_mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.demo.interview.R;
import com.demo.interview.databinding.ActivityMvvmTestBinding;

/**
 * 文 件 名：MVVMActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/6/15 23:18
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MVVMActivity extends AppCompatActivity {

    private ActivityMvvmTestBinding binding;
    private MVVMViewModule mvvmViewModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_test);
        mvvmViewModule = new MVVMViewModule(this,binding);
        binding.setViewModule(mvvmViewModule);
    }
}
