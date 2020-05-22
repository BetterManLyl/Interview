package com.demo.interview.mvc_mvp_mvvm.mvp.presenter;

import com.demo.interview.mvc_mvp_mvvm.mvp.base.BasePresenter;
import com.demo.interview.mvc_mvp_mvvm.mvp.contract.LoginContract;
import com.demo.interview.mvc_mvp_mvvm.mvp.model.LoginModel;

/**
 * 文 件 名：LoginPresenter
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 17:08
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class LoginPresenter extends BasePresenter
        implements LoginContract.LoginPresenter {

    private LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }


    @Override
    public void login(String userName, String userPass) {
        loginModel.login(userName, userPass, new LoginModel.ILoginCallBack() {
            @Override
            public void onFailure(int errCode, String errDes) {
                
            }

            @Override
            public void onSuccess() {

            }
        });
    }
}
