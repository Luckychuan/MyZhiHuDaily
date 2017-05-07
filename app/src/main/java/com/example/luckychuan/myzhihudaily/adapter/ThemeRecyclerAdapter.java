package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.EditorViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.StoryViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.ThemeHeaderViewHolder;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.ui.StoryActivity;

import java.util.List;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Object> mList;
    private static int TYPE_HEATER = 0;
    private static int TYPE_EDITOR = 1;
    private static int TYPE_STORY = 2;

    public ThemeRecyclerAdapter(List<Object> list) {
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEATER) {
            viewHolder = new ThemeHeaderViewHolder(inflater.inflate(R.layout.theme_header_holder_layout, parent, false));
        } else if (viewType == TYPE_EDITOR) {
            viewHolder = new EditorViewHolder(inflater.inflate(R.layout.editor_holder_layout, parent, false));
        } else {
            viewHolder = new StoryViewHolder(inflater.inflate(R.layout.story_holder_layout, parent, false));
            ((StoryViewHolder)viewHolder).setOnClickListener(new StoryViewHolder.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    String id = ((Story)mList.get(position)).getId();
                    Context context = parent.getContext();
                    Intent intent = new Intent(context, StoryActivity.class);
                    intent.putExtra("story_id",id);
                    context.startActivity(intent);
                }
            });
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
