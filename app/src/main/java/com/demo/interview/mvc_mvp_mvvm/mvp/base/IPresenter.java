package com.demo.interview.mvc_mvp_mvvm.mvp.base;

/**
 * 文 件 名：IPresenter
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 16:50
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public interface IPresenter<T extends IBaseView> {

    void attachView(T rootView);

    void detachView();
}
