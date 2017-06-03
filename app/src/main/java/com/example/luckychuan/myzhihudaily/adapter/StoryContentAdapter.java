package com.example.luckychuan.myzhihudaily.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.ui.ContentFragment;

import java.util.List;


/**
 * Created by Luckychuan on 2017/5/26.
 */

public class StoryContentAdapter extends FragmentPagerAdapter {

    private List<Story> mStories;

    public StoryContentAdapter(FragmentManager fm,List<Story> stories) {
        super(fm);
        mStories = stories;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ContentFragment();
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
