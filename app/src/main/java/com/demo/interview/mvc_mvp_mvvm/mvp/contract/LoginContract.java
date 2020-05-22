package com.demo.interview.mvc_mvp_mvvm.mvp.contract;

import com.demo.interview.mvc_mvp_mvvm.mvp.base.IBaseView;

/**
 * 文 件 名：LoginContract
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 17:05
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class LoginContract {

    public interface View extends IBaseView {

    }


    public interface LoginPresenter {
        void login(String userName, String userPass);
    }
}
