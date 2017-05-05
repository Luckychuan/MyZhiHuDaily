package com.example.luckychuan.myzhihudaily.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.LatestRecyclerAdapter;
import com.example.luckychuan.myzhihudaily.adapter.viewholder.DateViewHolder;
import com.example.luckychuan.myzhihudaily.bean.ItemBean;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.presenter.GetLatestDataPresenter;
import com.example.luckychuan.myzhihudaily.presenter.GetOldDataPresenter;
import com.example.luckychuan.myzhihudaily.view.LatestDataView;
import com.example.luckychuan.myzhihudaily.view.OldDataView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的界面
 */
public class HomeFragment extends Fragment implements LatestDataView, OldDataView {

    private GetLatestDataPresenter mLDPresenter;
    private GetOldDataPresenter mODPresenter;

    private List<ItemBean> mDataList;
    private LatestRecyclerAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    //加载出来的最前一天新闻的日期
    private String mLastDate;

    private RecyclerViewScrollListener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLDPresenter = new GetLatestDataPresenter(this);
        mLDPresenter.attach(this);
        mLDPresenter.requestData();


        //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_main);
        mDataList = new ArrayList<>();
        mAdapter = new LatestRecyclerAdapter(mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //判断RecyclerView是否滑动到最底端
                //滑动过的距离=偏移量+当前view显示的区域
                int total = recyclerView.computeVerticalScrollOffset() + recyclerView.computeVerticalScrollExtent();
                if (total >= recyclerView.computeVerticalScrollRange()) {
                    //滑动到最底端
                    if (mODPresenter == null) {
                        mODPresenter = new GetOldDataPresenter(HomeFragment.this);
                        mODPresenter.attach(HomeFragment.this);
                    }
                    //加载往日新闻
                    mODPresenter.requestData(mLastDate);
                }


                //获得RecyclerView当前可见的item的日期，给ToolBar设置日期
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findFirstVisibleItemPosition();
                String date = mAdapter.getDate(position);
                if (date == null) {
                    mListener.changeToolbarTitle("首页");
                } else {
                    mListener.changeToolbarTitle(DateViewHolder.format(date));
                }
            }
        });

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#03A9F4"));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLDPresenter.requestData();
            }
        });


    }

    /**
     * 更新今日新闻
     *
     * @param data
     */
    @Override
    public void updateUI(LatestData data) {

        mRefreshLayout.setRefreshing(false);

        mLastDate = data.getDate();

        mDataList.clear();

        //添加banner数据
        mDataList.add(new ItemBean<>(LatestRecyclerAdapter.TYPE_BANNER, data.getTopStories()));

        //添加日期
        mDataList.add(new ItemBean<>(LatestRecyclerAdapter.TYPE_DATE, data.getDate()));


        //添加新闻列表
        for (Story story : data.getStories()) {
            mDataList.add(new ItemBean<>(LatestRecyclerAdapter.TYPE_STORY, story));
        }

        mAdapter.notifyDataSetChanged();
    }


    /**
     * 更新往日新闻
     *
     * @param data
     */
    @Override
    public void updateUI(News data) {

        mLastDate = data.getDate();

        //添加日期
        mDataList.add(new ItemBean<>(LatestRecyclerAdapter.TYPE_DATE, data.getDate()));


        //添加新闻列表
        for (Story story : data.getStories()) {
            mDataList.add(new ItemBean<>(LatestRecyclerAdapter.TYPE_STORY, story));
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMsg(String error) {
        //由于知乎日报在首页并没有提示错误，此方法不写内容
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLDPresenter.detach();
        if (mODPresenter != null) {
            mODPresenter.detach();
        }
    }

    public void setRecyclerViewScrollListener(RecyclerViewScrollListener listener) {
        mListener = listener;
    }

    /**
     * 当RecyclerView滑动时，通知Toolbar改变标题
     */
    interface RecyclerViewScrollListener {
        void changeToolbarTitle(String title);
    }

}
