package com.demo.interview.four_component.launch_activity;

import android.content.Intent;
import android.os.Bundle;

import com.demo.interview.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 文 件 名：ActivityOne
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 9:17
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ActivityTwo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        

        findViewById(R.id.skip).setOnClickListener(v -> 
                startActivity(new Intent(ActivityTwo.this, ActivityThree.class)));

    }
}
