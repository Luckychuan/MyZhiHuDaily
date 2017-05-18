package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.FavoriteRecyclerAdapter;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.StoryLite;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/17.
 */

public class FavoriteFragment extends BaseFragment {

    private OnTitleChangeListener mListener;
    private List<Story> mList;
    private FavoriteRecyclerAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList = new ArrayList<>();
       initData();

        if (mListener != null) {
            mListener.changeToolbarTitle(mList.size() + "条收藏");
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FavoriteRecyclerAdapter(mList);
        recyclerView.setAdapter(mAdapter);


    }


    /**
     * /从数据库中获取数据
     */
    private void initData() {
        mList.clear();
        List<StoryLite> list = DataSupport.findAll(StoryLite.class);
        for (StoryLite storyLite : list) {

            Story story = new Story();
            story.setId(storyLite.getStoryId());
            story.setTitle(storyLite.getTitle());
            story.setImageUrl(new String[]{storyLite.getImageUrl()});
            story.setMultiPic(storyLite.isMultiPic());

            mList.add(story);
        }
    }

    @Override
    public void setTitleChangeListener(OnTitleChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
           refreshData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    public void refreshData(){
        initData();
        mAdapter.notifyDataSetChanged();
        if (mListener != null) {
            mListener.changeToolbarTitle(mList.size() + "条收藏");
        }
    }

}
