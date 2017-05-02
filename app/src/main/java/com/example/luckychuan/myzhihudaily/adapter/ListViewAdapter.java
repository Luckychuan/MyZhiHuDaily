package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/4/25.
 */
public class ListViewAdapter extends BaseAdapter implements StoryRecyclerAdapter.OnItemClickListener {

    private static final String TAG = "ListViewAdapter";

    private List<News> mNewsList;
    //RecyclerView上的数据
    private List<Story> mStoryList;
    private StoryRecyclerAdapter mAdapter;

    public ListViewAdapter(List<News> list) {
        mNewsList = list;
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        News news = mNewsList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.item_date);

            //设置RecyclerView
            holder.recyclerView = (RecyclerView) convertView.findViewById(R.id.news_title_recycler);
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                //禁用RecyclerView的滚动，防止滑动冲突
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            holder.recyclerView.setLayoutManager(manager);
            mStoryList = new ArrayList<>();
            mStoryList.addAll(news.getStories());
            Log.d(TAG, "getView: init");
            for (Story s : mStoryList) {
                Log.d(TAG, "getView: " + s.toString());
            }


            setRecyclerViewHeight(holder.recyclerView,context);

            mAdapter = new StoryRecyclerAdapter(mStoryList, this);
            holder.recyclerView.setAdapter(mAdapter);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            mStoryList.clear();
            mStoryList.addAll(news.getStories());

            Log.d(TAG, "getView: else");
            for (Story s : mStoryList) {
                Log.d(TAG, "getView: " + s.toString());
            }

            setRecyclerViewHeight(holder.recyclerView,context);
            mAdapter.notifyDataSetChanged();
        }

        holder.textView.setText(news.getDate());


        return convertView;
    }

    private void setRecyclerViewHeight(RecyclerView recyclerView, Context context) {
        //因为RecyclerView无法滑动，所以要动态设置RecyclerView的高度，让所有item都显示出来
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = getRecyclerViewItemHeight(context) * mStoryList.size();
        recyclerView.setLayoutParams(params);
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }

    private int getRecyclerViewItemHeight(Context context) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_title_recycler_item, null);
        ViewGroup group = (ViewGroup) itemView.findViewById(R.id.content_layout);
        ViewGroup.LayoutParams params = group.getLayoutParams();
        if (params == null) {
            Log.d(TAG, "getRecyclerViewItemHeight: null");
        }
        return params.height;
    }


    class ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
    }
}


