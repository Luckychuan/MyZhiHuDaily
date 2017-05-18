package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.ThemeRecyclerAdapter;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.ThemeHeaderViewHolder;
import com.example.luckychuan.myzhihudaily.bean.Editor;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.ThemeContent;
import com.example.luckychuan.myzhihudaily.presenter.GetThemeContentPresenter;
import com.example.luckychuan.myzhihudaily.view.ThemeContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeFragment extends BaseFragment implements ThemeContentView {

    private GetThemeContentPresenter mPresenter;

    private List<Object> mList;
    private ThemeRecyclerAdapter mAdapter;
    private OnTitleChangeListener mListener;

    private static final String TAG = "ThemeFragment";
    private int mId;
    private String mName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new GetThemeContentPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestData((Integer) getArguments().get("id"));

        mList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_theme);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ThemeRecyclerAdapter(mList);
        recyclerView.setAdapter(mAdapter);


    }

    public void refreshData(int id) {
        if (mId != id) {
            mId = id;
            mPresenter.requestData(id);
        }
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void updateUI(ThemeContent content) {

        mName = content.getName();
        if(mListener !=null){
            mListener.changeToolbarTitle(mName);
        }

        mList.clear();

        ThemeHeaderViewHolder.HeaderBean headerBean = new ThemeHeaderViewHolder.HeaderBean(content.getBackgroundUrl(),content.getDescription());
        mList.add(headerBean);

        List<Editor> list = new ArrayList<>();
        list.addAll(content.getEditors());
        mList.add(list);

        for (Story story : content.getStories()) {
            mList.add(story);
        }

        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    @Override
    public void setTitleChangeListener(OnTitleChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(mListener !=null){
                mListener.changeToolbarTitle(mName);
            }
        }


    }


}
