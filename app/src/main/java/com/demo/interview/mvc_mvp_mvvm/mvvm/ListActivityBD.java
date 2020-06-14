package com.demo.interview.mvc_mvp_mvvm.mvvm;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.demo.interview.R;
import com.demo.interview.databinding.ActivityListBdBinding;
import com.demo.interview.mvc_mvp_mvvm.mvvm.adapter.BDListAdapter;

/**
 * 文 件 名：ListActivityBD
 * 创 建 人：李跃龙
 * 创建日期：2020/6/14 21:58
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class ListActivityBD extends AppCompatActivity {

    private ActivityListBdBinding binding;
    private BDListAdapter bdListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_bd);
        binding.setPresenter(new Presenter());
        initRV();
    }

    private void initRV() {
        bdListAdapter = new BDListAdapter(this);
        binding.rvView.setLayoutManager(new LinearLayoutManager(this));
        binding.rvView.setAdapter(bdListAdapter);
    }

    public class Presenter {

        public void onClickAddItem(View view) {

            ToastUtils.showShort("11111");
        }

        public void onClickRemoveItem(View view) {
            ToastUtils.showShort("22222");
        }
    }
}
