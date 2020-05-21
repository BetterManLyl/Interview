package com.demo.interview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 文 件 名：FragmentOne
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 11:10
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class FragmentOne extends BaseFragment {

    public static FragmentOne newInstance() {
        return new FragmentOne();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.d("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("onCreateView");
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        getArg();
        return view;
    }

    /**
     * 接收Activity传过来的数据
     * 通过getArguments();
     */
    public void getArg() {
        String param = getArguments().getString("params");
        LogUtils.d("param:" + param);
        if (null != fmCallActivity) {
            fmCallActivity.callData("你好 FragmentActivity");
        }
    }

    private FmCallActivity fmCallActivity;

    public void setFmCallActivity(FmCallActivity fmCallActivity) {
        this.fmCallActivity = fmCallActivity;
    }

    public interface FmCallActivity {
        void callData(String value);
    }
}
