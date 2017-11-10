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
import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.bean.Comments;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.bean.StoryLite;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetCommentModelImpl;
import com.example.luckychuan.myzhihudaily.presenter.GetStoryExtraPresenter;
import com.example.luckychuan.myzhihudaily.view.StoryExtraView;
import com.example.luckychuan.myzhihudaily.widget.TextActionProvider;
import com.mob.MobSDK;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 新闻的内容
 */
public class StoryActivity extends AppCompatActivity implements StoryExtraView, Toolbar.OnMenuItemClickListener,ContentFragment.OnLoadFinishListener {

    private static final String TAG = "StoryActivity";
    private GetStoryExtraPresenter mPresenter;

    private Story mStory;
    private List<Story> mStoryList;

    private Toolbar mToolbar;
    //toolbar上的menuItem
    private TextActionProvider mPraiseProvider;
    private TextActionProvider mCommentProvider;

    private StoryContent mContent;




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
        Log.d(TAG, "onCreate: "+mStory.getId());

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

        //初始化TextActionProvider
        Menu menu = mToolbar.getMenu();
        mPraiseProvider = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.love));
        mPraiseProvider.setDrawable(R.drawable.praise);
        mCommentProvider = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.comment));
        mCommentProvider.setDrawable(R.drawable.comment);


        //初始化ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        StoryContentAdapter adapter = new StoryContentAdapter(getSupportFragmentManager(), mStoryList,this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mStory = mStoryList.get(position);
                mPresenter.requestStoryExtra(mStory.getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //初始化Presenter
        mPresenter = new GetStoryExtraPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestStoryExtra(mStory.getId());


        //创建收藏的数据库
        Connector.getDatabase();
//        //       DataSupport.deleteAll(StoryLite.class);
//        for (StoryLite story : findAll()) {
//            Log.d(TAG, "onCreate: " + story.toString());
//        }


        //初始化shareSDK
        MobSDK.init(this,"223f364b5e394","f2ccd444045d58f63eaba442d0152a26");


    }


    private void share() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mContent.getTitle());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mContent.getShareUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mContent.getTitle() + mContent.getShareUrl());
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mContent.getShareUrl());

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mContent.getShareUrl());

        // 启动分享GUI
        oks.show(this);
    }


    @Override
    public void showErrorMsg(String error) {

    }


    @Override
    public void updateToolbar(final StoryExtra extra) {


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
                Intent intent = new Intent(StoryActivity.this,CommentActivity.class);
                intent.putExtra("id",mStory.getId());
                intent.putExtra("number",extra.getComments());
                startActivity(intent);
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
                if(mContent != null){
                    share();
                }
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

    @Override
    public void onLoadFinish(StoryContent content) {
        mContent = content;
    }
}
