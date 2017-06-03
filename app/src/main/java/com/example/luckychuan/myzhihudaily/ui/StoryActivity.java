package com.example.luckychuan.myzhihudaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.StoryContentAdapter;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.bean.StoryLite;
import com.example.luckychuan.myzhihudaily.presenter.GetStoryExtraPresenter;
import com.example.luckychuan.myzhihudaily.view.StoryExtraView;
import com.example.luckychuan.myzhihudaily.widget.TextActionProvider;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻的内容
 */
public class StoryActivity extends AppCompatActivity implements StoryExtraView, Toolbar.OnMenuItemClickListener {

    private static final String TAG = "StoryActivity";
    private GetStoryExtraPresenter mPresenter;

    private Story mStory;
    private List<Story> mStoryList;
    private ViewPager mViewPager;

    Toolbar mToolbar;
    //toolbar上的menuItem
    TextActionProvider mPraiseProvider;
    TextActionProvider mCommentProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        //获得传递过来的Story列表
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        mStoryList = new ArrayList<>();
        List<Story> list = (List<Story>) intent.getSerializableExtra("storyList");
        mStoryList.addAll(list);
        mStory = mStoryList.get(position);

        //初始化Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mToolbar.inflateMenu(R.menu.story_detail);
        mToolbar.setOnMenuItemClickListener(this);

        Menu menu = mToolbar.getMenu();
        mPraiseProvider = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.love));
        mPraiseProvider.setDrawable(R.drawable.praise);
        mCommentProvider = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.comment));
        mCommentProvider.setDrawable(R.drawable.comment);


        //初始化ViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(1);
        StoryContentAdapter adapter = new StoryContentAdapter(getSupportFragmentManager(), mStoryList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mStory = mStoryList.get(mViewPager.getCurrentItem());
                    mPresenter.requestStoryExtra(Integer.parseInt(mStory.getId()));
                }
            }
        });

        //初始化Presenter
        mPresenter = new GetStoryExtraPresenter(this);
        mPresenter.attach(this);
        int id = Integer.parseInt(mStory.getId());
        mPresenter.requestStoryExtra(id);


        //创建收藏的数据库
        Connector.getDatabase();
//        //       DataSupport.deleteAll(StoryLite.class);
//        for (StoryLite story : findAll()) {
//            Log.d(TAG, "onCreate: " + story.toString());
//        }


    }


    @Override
    public void showErrorMsg(String error) {

    }


    @Override
    public void updateToolbar(StoryExtra extra) {


        Menu menu = mToolbar.getMenu();

        MenuItem favorite = menu.findItem(R.id.favorite);
        if (isStoryExist()) {
            favorite.setIcon(R.drawable.favorite_yellow);
        }else{
            favorite.setIcon(R.drawable.favorite);
        }

        mPraiseProvider.setNumber(extra.getPopularity());
        mCommentProvider.setNumber(extra.getComments());
        mCommentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setLoadingUI() {
        Menu menu = mToolbar.getMenu();
        MenuItem favorite = menu.findItem(R.id.favorite);
        favorite.setIcon(R.drawable.favorite);
        mCommentProvider.setLoadingText();
        mPraiseProvider.setLoadingText();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }


    /**
     * //从数据库查询当前story是否已存在数据库中
     *
     * @return
     */
    private boolean isStoryExist() {
        List<StoryLite> storyList = DataSupport.where("storyId =?", mStory.getId() + "").find(StoryLite.class);
        boolean isExist = false;
        if (storyList.size() > 0) {
            isExist = true;
        }
        Log.d(TAG, "isStoryExist: " + isExist);
        return isExist;
    }

    private List<StoryLite> findAll() {
        return DataSupport.findAll(StoryLite.class);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "尚未开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite:
                if (isStoryExist()) {
                    item.setIcon(R.drawable.favorite);
                    //从数据库中移除
                    DataSupport.deleteAll(StoryLite.class, "storyId = ?", mStory.getId() + "");
                } else {
                    item.setIcon(R.drawable.favorite_yellow);
                    //添加到数据库中
                    StoryLite story = new StoryLite();
                    story.setMultiPic(mStory.isMultiPic());
                    story.setTitle(mStory.getTitle());
                    story.setImageUrl(mStory.getImageUrl());
                    story.setStoryId(mStory.getId());
                    boolean result = story.save();
                    Log.d(TAG, "onOptionsItemSelected: " + result);
                }
                break;
        }
        return true;
    }
}
