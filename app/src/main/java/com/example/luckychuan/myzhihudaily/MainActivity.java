package com.example.luckychuan.myzhihudaily;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LatestDataView, OldDataView {

    private static final String TAG = "MainActivity";
    private GetLatestDataPresenter mLDPresenter;
    private GetOldDataPresenter mODPresenter;

    private List<ItemBean> mDataList;
    private LatestRecyclerAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    

    //加载出来的最前一天新闻的日期
    private String mLastDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        mLDPresenter = new GetLatestDataPresenter(this);
        mLDPresenter.attach(this);
        mLDPresenter.requestData();

        test();

    }

    private void initUI() {
        //初始化抽屉和标题
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        mDataList = new ArrayList<>();
        mAdapter = new LatestRecyclerAdapter(mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                        mODPresenter = new GetOldDataPresenter(MainActivity.this);
                        mODPresenter.attach(MainActivity.this);
                    }
                    //加载往日新闻
                    mODPresenter.requestData(mLastDate);
                }


                //获得RecyclerView当前可见的item的日期，给ToolBar设置日期
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findFirstVisibleItemPosition();
                String date = mAdapter.getDate(position);
                if (date == null) {
                    toolbar.setTitle("首页");
                } else {
                    toolbar.setTitle(DateViewHolder.format(date));
                }
            }
        });

        mRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#03A9F4"));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLDPresenter.requestData();
            }
        });


    }

    private void test() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    protected void onDestroy() {
        super.onDestroy();
        mLDPresenter.detach();
        if (mODPresenter != null) {
            mODPresenter.detach();
        }
    }

}
