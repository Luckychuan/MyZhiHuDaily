package com.example.luckychuan.myzhihudaily;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.adapter.BannerAdapter;
import com.example.luckychuan.myzhihudaily.adapter.NewsRecyclerAdapter;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.presenter.GetLatestDataPresenter;
import com.example.luckychuan.myzhihudaily.view.BaseView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseView<LatestData> {

    private static final String TAG = "MainActivity";
    private GetLatestDataPresenter mPresenter;
    private NewsRecyclerAdapter mRecyclerAdapter;
    private LatestData mLatestData;
    //banner上的标题
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        mPresenter = new GetLatestDataPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestData();

        test();

    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mLatestData = new LatestData();
        mRecyclerAdapter = new NewsRecyclerAdapter(mLatestData.getStories(), new NewsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_title_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mRecyclerAdapter);

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

    @Override
    public void updateUI(LatestData data) {
        mLatestData.setDate(data.getDate());
        mLatestData.getStories().clear();
        mLatestData.getStories().addAll(data.getStories());
        mRecyclerAdapter.notifyDataSetChanged();

        mLatestData.getTopStories().clear();
        mLatestData.getTopStories().addAll(data.getTopStories());

        initBanner();

    }

    private void initBanner() {
        RelativeLayout bannerLayout = (RelativeLayout) findViewById(R.id.banner_layout);
        RollPagerView bannerView = (RollPagerView) findViewById(R.id.banner);
        bannerView.setPlayDelay(5000);
        bannerView.setAnimationDurtion(500);

        //设置banner的高度，使宽度和高度的比例为16：9
        int screenWidth = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bannerLayout.getLayoutParams();
        params.height = screenWidth / 16 * 9;
        bannerLayout.setLayoutParams(params);

        //获得新闻标题和图片url
        final List<LatestData.TopStory> topStories = mLatestData.getTopStories();
        List<String> imageUrls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (LatestData.TopStory topStory : topStories) {
            imageUrls.add(topStory.getImageUrl());
            titles.add(topStory.getTitle());
        }

        //设置标题的变化
        mTitleTextView = (TextView) findViewById(R.id.top_story_title);
        mTitleTextView.setText(titles.get(0));
        final ViewPager bannerViewPager = bannerView.getViewPager();
        bannerViewPager.addOnPageChangeListener(new BannerImageChangeListener(titles));

        bannerView.setAdapter(new BannerAdapter(imageUrls));


        bannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onClick: " + topStories.get(position));
            }
        });


    }

    @Override
    public void showErrorMsg(String error) {
        //由于知乎日报在首页并没有提示错误，此方法不写内容
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    /**
     * 用于改变banner上的标题文字
     */
    class BannerImageChangeListener implements ViewPager.OnPageChangeListener {

        private List<String> list;

        public BannerImageChangeListener(List<String> titles) {
            list = titles;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mTitleTextView.setText(list.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
