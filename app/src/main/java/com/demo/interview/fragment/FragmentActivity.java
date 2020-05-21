package com.demo.interview.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 文 件 名：FragmentActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 11:10
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class FragmentActivity extends BaseActivity implements
        View.OnClickListener, FragmentOne.FmCallActivity {

    private Fragment mCurrentFm;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String mCurrentFms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.d("onCreate");
        /**
         *2、在这里进行状态恢复
         */
        if (null != savedInstanceState) {
            LogUtils.d("savedInstanceState");
            mCurrentFms = savedInstanceState.getString("mCurrentFm");
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 1、 注意 在这里如果进行了内存重启
     * 需要保存Fragment的当前状态
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d("onSaveInstanceState");
        //保存当前显示的Fragment类名
        outState.putString("mCurrentFm", mCurrentFm.getClass().getSimpleName());
        mCurrentFm = fragmentManager.findFragmentByTag(mCurrentFm.getClass().getSimpleName());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.d("onRestoreInstanceState");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_fg_one).setOnClickListener(this);
        findViewById(R.id.btn_fg_two).setOnClickListener(this);
        findViewById(R.id.btn_fg_three).setOnClickListener(this);
        findViewById(R.id.btn_screen).setOnClickListener(this);
        fragmentOne = FragmentOne.newInstance();
        fragmentTwo = FragmentTwo.newInstance();
        fragmentThree = FragmentThree.newInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentOne.setFmCallActivity(this);
        initFragment();
    }

    private void initFragment() {
        mCurrentFm = fragmentOne;
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(mCurrentFms)) {
            mCurrentFm = fragmentManager.findFragmentByTag(mCurrentFms);
            if (null != mCurrentFm) {
                fragmentTransaction.show(mCurrentFm);
                fragmentTransaction.commit();
            }
        } else {
            fragmentTransaction
                    .add(R.id.fl, fragmentOne, fragmentOne.getClass().getSimpleName())
                    .commit();
        }
        setArguement();
    }

    /**
     * 传递数据给Fragment
     * 通过setArguments
     */
    private void setArguement() {
        Bundle bundle = new Bundle();
        bundle.putString("params", "lyl");
        fragmentOne.setArguments(bundle);
    }

    private void showFm(Fragment fragment) {
        //这里注意，fragmentTransaction只能commit一次，每次commit都需要重新创建
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.hide(mCurrentFm)
                    .add(R.id.fl, fragment, fragment.getClass().getSimpleName());
        } else {
            fragmentTransaction.hide(mCurrentFm).show(fragment);
        }
        mCurrentFm = fragment;
        fragmentTransaction.commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fg_one:
                showFm(fragmentOne);
                break;
            case R.id.btn_fg_two:
                showFm(fragmentTwo);
                break;
            case R.id.btn_fg_three:
                showFm(fragmentThree);
                break;
            case R.id.btn_screen:
                if (ScreenUtils.isLandscape()) {
                    ScreenUtils.setPortrait(this);
                } else {
                    ScreenUtils.setLandscape(this);
                }
                break;
            default:
                break;
        }
    }

    /**
     * fragment 传过来的数据
     *
     * @param value
     */
    @Override
    public void callData(String value) {
        ToastUtils.showShort("接收到了Fragment传过来的数据" + value);
    }
}
