package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BannerViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
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
        if (viewType == TYPE_BANNER) {
            viewHolder = new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_story_holder_layout, parent, false));
        }else if(viewType == TYPE_DATE){
            //// TODO: 2017/5/2
        }else{
            // TODO: 2017/5/2
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int type= mList.get(position).type;
        if(type == TYPE_BANNER){
            holder.bindViewHolder(mList.get(position).bean);
        }
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


