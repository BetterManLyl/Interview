package com.demo.interview.mvc_mvp_mvvm.mvp.view;

import com.demo.interview.mvc_mvp_mvvm.mvp.base.BaseMvpActivity;
import com.demo.interview.mvc_mvp_mvvm.mvp.contract.LoginContract;
import com.demo.interview.mvc_mvp_mvvm.mvp.presenter.LoginPresenter;

/**
 * 文 件 名：LoginActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 17:09
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> 
        implements LoginContract.View{
   
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void initView() {
        mPresenter.login("","");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
