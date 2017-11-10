package com.example.luckychuan.myzhihudaily.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.ui.ContentFragment;

import java.util.List;


/**
 * Created by Luckychuan on 2017/5/26.
 */

public class StoryContentAdapter extends FragmentPagerAdapter {

    private List<Story> mStories;
    private ContentFragment.OnLoadFinishListener mListener;

    public StoryContentAdapter(FragmentManager fm, List<Story> stories, ContentFragment.OnLoadFinishListener listener) {
        super(fm);
        mStories = stories;
        mListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        ContentFragment fragment = new ContentFragment();
        fragment.setOnLoadFinshListener(mListener);
        Bundle bundle = new Bundle();
        bundle.putString("id",mStories.get(position).getId());
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return mStories.size();
    }
}
