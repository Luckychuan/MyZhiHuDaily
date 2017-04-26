package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.MyRecyclerView;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.News;

import java.util.List;

/**
 * Created by Luckychuan on 2017/4/25.
 */
public class ListViewAdapter extends BaseAdapter implements StoryRecyclerAdapter.OnItemClickListener {

    private List<News> mNewsList;
    //当天新闻的适配器
    private StoryRecyclerAdapter mTodayAdapter;

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
        News news = mNewsList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.item_date);
            holder.recyclerView = (MyRecyclerView) convertView.findViewById(R.id.news_title_recycler);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(news.getDate());

        //设置RecyclerView
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
        //当天的新闻
        if (position == 0) {
            mTodayAdapter = new StoryRecyclerAdapter(news.getStories(), this);
            holder.recyclerView.setAdapter(mTodayAdapter);
        } else {
            StoryRecyclerAdapter adapter = new StoryRecyclerAdapter(news.getStories(), this);
        }

        return convertView;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }


    class ViewHolder {
        TextView textView;
        MyRecyclerView recyclerView;
    }
}


