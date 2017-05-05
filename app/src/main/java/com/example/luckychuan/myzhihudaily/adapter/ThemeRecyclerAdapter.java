package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.StoryViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.ThemeHeaderViewHolder;

import java.util.List;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Object> mList;
    private static int TYPE_HEATER = 0;
    private static int TYPE_EDITOR = 1;
    private static int TYPE_STORY = 2;

    public ThemeRecyclerAdapter(List<Object> list){
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEATER) {
            viewHolder = new ThemeHeaderViewHolder(inflater.inflate(R.layout.theme_header_holder_layout, parent, false));
        }else if(viewType == TYPE_EDITOR){
            // TODO: 2017/5/5
        }else{
            viewHolder = new StoryViewHolder(inflater.inflate(R.layout.story_holder_layout,parent,false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEATER;
        } else if (position == 1) {
            return TYPE_EDITOR;
        } else {
            return TYPE_STORY;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
