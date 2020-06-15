package com.demo.interview.mvc_mvp_mvvm.mvvm.test_mvvm;

import java.util.Random;

/**
 * 文 件 名：MVVMModule
 * 创 建 人：李跃龙
 * 创建日期：2020/6/15 23:18
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MVVMModule {


    public void getData(String input, MCallback callback) {
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        if (isSuccess) {
            Account account = new Account();
            account.setName(input);
            account.setLevel(100);
            callback.onSuccess(account);
        } else {
            callback.onFailed();
        }
    }
}
