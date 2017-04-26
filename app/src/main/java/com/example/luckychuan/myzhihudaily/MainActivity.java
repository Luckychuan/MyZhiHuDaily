package com.example.luckychuan.myzhihudaily;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.adapter.BannerAdapter;
import com.example.luckychuan.myzhihudaily.adapter.ListViewAdapter;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.presenter.GetLatestDataPresenter;
import com.example.luckychuan.myzhihudaily.presenter.GetOldDataPresenter;
import com.example.luckychuan.myzhihudaily.view.LatestDataView;
import com.example.luckychuan.myzhihudaily.view.OldDataView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LatestDataView, OldDataView {

    private static final String TAG = "MainActivity";
    private GetLatestDataPresenter mLDPresenter;
    private GetOldDataPresenter mODPresenter;

    private View mHeader;
    private BannerAdapter mBannerAdapter;
    private List<LatestData.TopStory> mTopStories = new ArrayList<>();
    //banner上图片的url
    List<String> mImageUrls = new ArrayList<>();
    //banner上的标题
    private TextView mTitleTextView;
    List<String> mTitles = new ArrayList<>();

    //ListView的数据
    private List<News> mNewsList = new ArrayList<>();
    private ListView mMainListView;
    private ListViewAdapter mListViewAdapter;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mMainListView = (ListView) findViewById(R.id.list_view);
        mListViewAdapter = new ListViewAdapter(mNewsList);
        mMainListView.setAdapter(mListViewAdapter);
        mMainListView.setVisibility(View.INVISIBLE);

        initBanner();


    }

    private void test() {
//        mODPresenter = new GetOldDataPresenter(this);
//        mODPresenter.attach(this);
//        mODPresenter.requestData("20170420");
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

        //更新listView的UI
        mMainListView.setVisibility(View.VISIBLE);
        mNewsList.clear();
        mNewsList.add(new News(data.getDate(), data.getStories()));
        mListViewAdapter.notifyDataSetChanged();



        //更新banner的UI
        mHeader.setVisibility(View.VISIBLE);
        mTopStories.clear();
        mImageUrls.clear();
        mTitles.clear();
        mTopStories.addAll(data.getTopStories());
        for (LatestData.TopStory topStory : mTopStories) {
            mImageUrls.add(topStory.getImageUrl());
            mTitles.add(topStory.getTitle());
        }
        //设置标题的变化
        mTitleTextView.setText(mTitles.get(0));
        mBannerAdapter.notifyDataSetChanged();


    }

    private void initBanner() {
        mHeader = LayoutInflater.from(this).inflate(R.layout.header_list_view, null);
        mHeader.setVisibility(View.INVISIBLE);
        RollPagerView bannerView = (RollPagerView) mHeader.findViewById(R.id.banner);
        bannerView.setPlayDelay(5000);
        bannerView.setAnimationDurtion(500);

        //设置banner的高度，使宽度和高度的比例为16：9
        int screenWidth = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        ViewGroup group = (ViewGroup) mHeader.findViewById(R.id.banner_layout);
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) group.getLayoutParams();
        params.height = screenWidth / 16 * 9;
        group.setLayoutParams(params);

        mBannerAdapter = new BannerAdapter(mImageUrls);
        bannerView.setAdapter(mBannerAdapter);

        mMainListView.addHeaderView(mHeader);


        mTitleTextView = (TextView) mHeader.findViewById(R.id.top_story_title);

        final ViewPager bannerViewPager = bannerView.getViewPager();
        bannerViewPager.addOnPageChangeListener(new BannerImageChangeListener());

        bannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onClick: " + mTitles.get(position));
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
        mLDPresenter.detach();
        mODPresenter.detach();
    }

    @Override
    public void updateUI(News data) {
        for (Story story : data.getStories()) {
            Log.d(TAG, "updateUI: " + story.toString());
        }
    }

    /**
     * 用于改变banner上的标题文字
     */
    class BannerImageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mTitleTextView.setText(mTitles.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
