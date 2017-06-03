package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.StoryViewHolder;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.StoryLite;
import com.example.luckychuan.myzhihudaily.ui.StoryActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/18.
 */

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<StoryViewHolder> {

    private List<Story> mList;

    public FavoriteRecyclerAdapter(List<Story> list) {
        mList = list;
    }


    @Override
    public StoryViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        StoryViewHolder viewHolder = new StoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.story_holder_layout, parent, false));

        viewHolder.setOnClickListener(new StoryViewHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Context context = parent.getContext();

                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("storyList", (Serializable) mList);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
