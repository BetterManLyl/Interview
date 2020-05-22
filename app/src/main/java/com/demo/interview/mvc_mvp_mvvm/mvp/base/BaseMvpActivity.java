package com.demo.interview.mvc_mvp_mvvm.mvp.base;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 文 件 名：BaseMvpActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 16:50
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity
        implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = getPresenter();
        if (null != mPresenter) {
            //noinspection unchecked
            mPresenter.attachView(this);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) actionBar.hide();
        initView();
        initData();
    }


    @Override
    public void showTip(String text) {

    }

    @Override
    public void showTip(String text, int xOffSet, int yOffset) {

    }

    @Override
    public void showTip(String text, int gravity, int xOffSet, int yOffset) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showTipDialog(String title, String message) {

    }

    @Override
    public void showTipDialog(String title, String message, Bitmap image) {

    }

    @Override
    public void showTipDialog(String title, String message, boolean showIgnoreBtn) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void dismissTipDialog() {
        
    }

    /**
     * 获取布局xml
     *
     * @return 布局xml
     */
    public abstract int getLayoutId();

    /**
     * 获取MVP模式中的presenter
     *
     * @return presenter
     */
    public abstract P getPresenter();

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}
