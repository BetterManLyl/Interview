package com.demo.interview.mvc_mvp_mvvm.mvvm.test_mvvm;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.demo.interview.databinding.ActivityMvvmTestBinding;

/**
 * 文 件 名：MVVMViewModule
 * 创 建 人：李跃龙
 * 创建日期：2020/6/15 23:18
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MVVMViewModule extends BaseObservable {

    private String input;
    private MVVMModule mvvmModule;
    private String result;
    private ActivityMvvmTestBinding mBinding;

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        
        
        notifyPropertyChanged(com.demo.interview.BR.result);
    }

    public MVVMViewModule(Context context, ActivityMvvmTestBinding binding) {
        mvvmModule = new MVVMModule();
        mBinding = binding;
    }

    public void getData(View view) {
        mvvmModule.getData(input, new MCallback() {
            @Override
            public void onSuccess(Account account) {
                String info = account.getName() + "|" + account.getLevel();
                setResult(info);
            }

            @Override
            public void onFailed() {
                setResult("onFailed");
            }
        });
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
