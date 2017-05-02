package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public  abstract  class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract  void bindViewHolder(T bean);
}
