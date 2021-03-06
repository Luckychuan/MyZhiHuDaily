package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BannerViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.DateViewHolder;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.StoryViewHolder;
import com.example.luckychuan.myzhihudaily.bean.ItemBean;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.ui.StoryActivity;

import java.io.Serializable;
import java.util.ArrayList;
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
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder;
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_BANNER) {
            viewHolder = new BannerViewHolder(inflater.inflate(R.layout.top_story_holder_layout, parent, false));
        }else if(viewType == TYPE_DATE){
            viewHolder = new DateViewHolder(inflater.inflate(R.layout.date_holder_layout, parent, false));
        }else{
            viewHolder = new StoryViewHolder(inflater.inflate(R.layout.story_holder_layout,parent,false));
            ((StoryViewHolder)viewHolder).setOnClickListener(new StoryViewHolder.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {

                    //传递整个Story列表和被点击的Story在列表中的位置
                    Story currentStory = (Story) mList.get(position).bean;
                    List<Story> list = new ArrayList<>();
                    int positionInList = 0;
                    for(ItemBean itemBean : mList){
                        if(itemBean.type == TYPE_STORY){
                            Story story = (Story) itemBean.bean;
                            list.add(story);

                            if(story.getId().equals(currentStory.getId())){
                                positionInList = list.size()-1;
                            }
                        }
                    }

                    Context context = parent.getContext();
                    Intent intent = new Intent(context, StoryActivity.class);
                    intent.putExtra("storyList", (Serializable) list);
                    intent.putExtra("position",positionInList);
                    context.startActivity(intent);
                }
            });
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

    /**
     * 找到当前新闻列表的日期返回给Activity动态设置Toolbar标题
     * @param position  当前view中第一个item的position
     * @return
     */
    public String getDate(int position) {
        for (int i = position; i >= 0; i--) {
            if(mList.get(i).type == TYPE_DATE){
                return (String) mList.get(i).bean;
            }
        }
        return null;
    }


}


