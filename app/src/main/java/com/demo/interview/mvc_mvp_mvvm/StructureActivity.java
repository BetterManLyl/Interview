package com.demo.interview.mvc_mvp_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.demo.interview.R;
import com.demo.interview.databinding.ActivityStructreBinding;
import com.demo.interview.mvc_mvp_mvvm.mvvm.MVVMActivity_test;

/**
 * 文 件 名：StructureActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/6/9 15:41
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class StructureActivity extends AppCompatActivity {

    private ActivityStructreBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_structre);
        binding.setClick(new OnClickHandler());
    }


    public class OnClickHandler {
        public void onClick(View view) {
            startActivity(new Intent(StructureActivity.this, MVVMActivity_test.class));
        }
    }
}
