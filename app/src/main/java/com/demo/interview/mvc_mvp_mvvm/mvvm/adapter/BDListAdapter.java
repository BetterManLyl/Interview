package com.demo.interview.mvc_mvp_mvvm.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.interview.R;
import com.demo.interview.mvc_mvp_mvvm.mvvm.bean.Employ;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名：BDListAdapter
 * 创 建 人：李跃龙
 * 创建日期：2020/6/14 22:34
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class BDListAdapter extends RecyclerView.Adapter<BDListAdapter.MyViewHolder> {


    private static final int ITEM_VIEW_TYPE_ON = 1;
    private static final int ITEM_VIEW_TYPE_OFF = 2;

    private List<Employ> employs;

    private LayoutInflater layoutInflater;

    public interface OnItemClick {

        void onEmployOnClick(Employ employ);
    }


    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    @Override
    public int getItemViewType(int position) {
        final Employ employ = employs.get(position);
        if (employ.isFired()) {
            return ITEM_VIEW_TYPE_OFF;
        } else {
            return ITEM_VIEW_TYPE_ON;
        }
    }

    public BDListAdapter(Context context) {
        super();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        employs = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        if (viewType == ITEM_VIEW_TYPE_ON) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_bd_off,
                    parent, false);
        } else {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_bd_on,
                    parent, false);

        }
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Employ employ = employs.get(position);
        holder.getmBinding().setVariable(com.demo.interview.BR.item, employ);
        holder.getmBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return employs.size();
    }

    public class MyViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private T mBinding;

        public MyViewHolder(@NonNull T binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public T getmBinding() {
            return mBinding;
        }
    }
}
