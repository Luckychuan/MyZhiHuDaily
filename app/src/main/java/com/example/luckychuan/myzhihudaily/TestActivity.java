package com.example.luckychuan.myzhihudaily;

import android.app.Activity;
import android.os.Bundle;

import com.example.luckychuan.myzhihudaily.adapter.TopStoryAdapter;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.presenter.GetLatestDataPresenter;
import com.example.luckychuan.myzhihudaily.view.LatestDataView;
import com.example.luckychuan.myzhihudaily.widget.AutoSlideViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/2.
 */
public class TestActivity extends Activity implements LatestDataView {

    private List<LatestData.TopStory> mTopStories = new ArrayList<>();
    private GetLatestDataPresenter mLDPresenter;
    private TopStoryAdapter mAdapter;
    private CirclePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_story_holder_layout);

        AutoSlideViewPager viewPager = (AutoSlideViewPager) findViewById(R.id.viewPager);
        mAdapter = new TopStoryAdapter(mTopStories);
        viewPager.setAdapter(mAdapter);
        mIndicator = (CirclePageIndicator) findViewById(R.id.pagerIndicator);
        mIndicator.setViewPager(viewPager);

        mLDPresenter = new GetLatestDataPresenter(this);
        mLDPresenter.attach(this);
        mLDPresenter.requestData();

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void updateUI(LatestData data) {
        mTopStories.addAll(data.getTopStories());
        mAdapter.notifyDataSetChanged();
    }
}
