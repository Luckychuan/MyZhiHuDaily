package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.view.View;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.TopStoryAdapter;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.widget.AutoSlideViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/2.
 */
public class BannerViewHolder extends BaseViewHolder<List<LatestData.TopStory>> {

    private List<LatestData.TopStory> mTopStories = new ArrayList<>();
    private TopStoryAdapter mAdapter;

    public BannerViewHolder(View itemView) {
        super(itemView);

        AutoSlideViewPager viewPager = (AutoSlideViewPager) itemView.findViewById(R.id.viewPager);
        mAdapter = new TopStoryAdapter(mTopStories);
        viewPager.setAdapter(mAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator) itemView.findViewById(R.id.pagerIndicator);
        indicator.setViewPager(viewPager);

    }


    @Override
    public void bindViewHolder(List<LatestData.TopStory> bean) {
        mTopStories.clear();
        mTopStories.addAll(bean);
        mAdapter.notifyDataSetChanged();
    }


}
