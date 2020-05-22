package com.demo.interview.mvc_mvp_mvvm.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 文 件 名：BaseFragment
 * 创 建 人：魏海锋
 * 创建日期：2020/2/9 10:49
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    private View mRootView;
    protected P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mPresenter = getPresenter();
        if (null != mPresenter) {
            //noinspection SingleStatementInBlock,unchecked
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mRootView = inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(mRootView);
        initData();
    }

    /**
     * 获取布局xml
     *
     * @return 布局xml
     */
    protected abstract int getLayoutId();

    /**
     * 获取MVP模式中的presenter
     *
     * @return presenter
     */
    protected abstract P getPresenter();

    /**
     * 初始化界面
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}
