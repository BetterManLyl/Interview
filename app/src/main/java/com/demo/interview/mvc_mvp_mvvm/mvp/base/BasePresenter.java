package com.demo.interview.mvc_mvp_mvvm.mvp.base;

/**
 * 文 件 名：BasePresenter
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 16:55
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class BasePresenter<T extends IBaseView> implements IPresenter<T> {

    protected T mRootView;

    @Override
    public void attachView(T iBaseView) {
        this.mRootView = iBaseView;
    }

    @Override
    public void detachView() {
        mRootView = null;
    }

    public boolean isViewAttached() {
        return null != mRootView;
    }
}
