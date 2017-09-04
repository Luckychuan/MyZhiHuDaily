package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.CommentTextViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.CommentViewHolder;
import com.example.luckychuan.myzhihudaily.bean.ItemBean;

import java.util.List;

/**
 * Created by Luckychuan on 2017/9/2.
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ItemBean> mList;
    public static final int TEXT = 0;
    public static final int COMMENT = 1;


    public CommentRecyclerAdapter(List<ItemBean> list) {
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        View itemView;
        if (viewType == TEXT) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_text_holder_layout, parent, false);
            holder = new CommentTextViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_holder_layout, parent, false);
            holder = new CommentViewHolder(itemView);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Log.d("position", "position " +position);
        holder.bindViewHolder(mList.get(position).bean,position);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
