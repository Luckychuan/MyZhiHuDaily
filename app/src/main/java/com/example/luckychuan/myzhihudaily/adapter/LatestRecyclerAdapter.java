package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BannerViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.DateViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.StoryViewHolder;
import com.example.luckychuan.myzhihudaily.bean.ItemBean;

import java.util.List;

/**
 * Created by Luckychuan on 2017/5/2.
 */
public class LatestRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_STORY = 2;
    private List<ItemBean> mList;

    public LatestRecyclerAdapter(List<ItemBean> list){
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_BANNER) {
            viewHolder = new BannerViewHolder(inflater.inflate(R.layout.top_story_holder_layout, parent, false));
        }else if(viewType == TYPE_DATE){
            viewHolder = new DateViewHolder(inflater.inflate(R.layout.date_holder_layout, parent, false));
        }else{
            viewHolder = new StoryViewHolder(inflater.inflate(R.layout.story_holder_layout,parent,false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.bindViewHolder(mList.get(position).bean);
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


