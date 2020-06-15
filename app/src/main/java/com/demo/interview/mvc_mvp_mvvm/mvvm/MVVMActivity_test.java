package com.demo.interview.mvc_mvp_mvvm.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.demo.interview.R;
import com.demo.interview.databinding.ActivityMvvmBinding;
import com.demo.interview.mvc_mvp_mvvm.mvvm.bean.User;
import com.demo.interview.mvc_mvp_mvvm.mvvm.test_mvvm.MVVMActivity;

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
    private ActivityMvvmBinding activityMvvmBinding = null;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        user = new User();
        activityMvvmBinding.setUserInfo(user);
        activityMvvmBinding.setGoods(new ObservableGoods("code", 123, "hi"));
        activityMvvmBinding.btnModify.setOnClickListener(v -> {
            ToastUtils.showShort("显示" + user.getName());
        });

    }


    /**
     * 点击事件  不需要设置setOnClickListener(this)
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_result:
                ToastUtils.showShort("点击了");
                break;
            case R.id.btn_list:
                startActivity(new Intent(this,ListActivityBD.class));
                break;
            case R.id.btn_skip:
                startActivity(new Intent(this, MVVMActivity.class));
                break;
            default:
                break;
        }
    }

}
