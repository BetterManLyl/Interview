package com.demo.interview.mvc_mvp_mvvm.mvp.base;

import android.graphics.Bitmap;

/**
 * 文 件 名：IBaseView
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 16:50
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public interface IBaseView {

    void showTip(String text);

    void showTip(String text, int xOffSet, int yOffset);

    void showTip(String text, int gravity, int xOffSet, int yOffset);

    void showLoading();

    void dismissLoading();

    void showTipDialog(String title, String message);

    void showTipDialog(String title, String message, Bitmap image);

    void showTipDialog(String title, String message, boolean showIgnoreBtn);

    void dismissTipDialog();
}
