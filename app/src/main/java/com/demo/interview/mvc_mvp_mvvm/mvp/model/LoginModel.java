package com.demo.interview.mvc_mvp_mvvm.mvp.model;

/**
 * 文 件 名：LoginModel
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 17:06
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class LoginModel {

    public void login(String userName, String passWord, ILoginCallBack callBack) {

    }

    public interface ILoginCallBack {

        void onFailure(int errCode, String errDes);

        void onSuccess();
    }
}
