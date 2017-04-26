package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Story;

import java.util.List;

/**
 * Created by Luckychuan on 2017/4/21.
 */
public class StoryRecyclerAdapter extends RecyclerView.Adapter<StoryRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NewsRecyclerAdapter";
    private OnItemClickListener mListener;
    private List<Story> mList;
    private Context mContext;

    public StoryRecyclerAdapter(List<Story> list, OnItemClickListener listener) {
        mList = list;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext =parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.news_title_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = mList.get(position);
        holder.textView.setText(story.getTitle());

        Glide.with(mContext)
                .load(story.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.new_image);
            textView = (TextView) itemView.findViewById(R.id.new_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getLayoutPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(getLayoutPosition());
                    return false;
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

}
