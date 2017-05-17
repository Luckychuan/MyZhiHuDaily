package com.example.luckychuan.myzhihudaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.bean.StoryLite;
import com.example.luckychuan.myzhihudaily.presenter.GetStoryContentPresenter;
import com.example.luckychuan.myzhihudaily.view.StoryContentView;
import com.example.luckychuan.myzhihudaily.widget.TextActionProvider;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * 新闻的内容
 */
public class StoryActivity extends AppCompatActivity implements StoryContentView {

    private static final String TAG = "StoryActivity";
    private GetStoryContentPresenter mPresenter;

    private WebView mWebView;
    private Toolbar mToolbar;

    private Story mStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        mStory = (Story) getIntent().getSerializableExtra("story");
              int id = Integer.parseInt(mStory.getId());

        mPresenter = new GetStoryContentPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestStoryContent(id);
        mPresenter.requestStoryExtra(id);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initWebView();


        //创建收藏的数据库
        Connector.getDatabase();
 //       DataSupport.deleteAll(StoryLite.class);
        for(StoryLite story:findAll()){
            Log.d(TAG, "onCreate: "+story.toString());
        }



    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void updateUI(StoryContent content) {

        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.story_detail_layout);
        layout.setVisibility(View.VISIBLE);

        ImageView imageView = (ImageView) findViewById(R.id.image_detail);
        TextView title = (TextView) findViewById(R.id.title_detail);
        TextView source = (TextView) findViewById(R.id.source_detail);

        Glide.with(this)
                .load(content.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        title.setText(content.getTitle());
        source.setText(content.getImageSource());

        String formatCss = String.format("<link href=\"%s\" rel=\"stylesheet\" type=\"text/css\"/>", content.getCss().get(0));
        String formatHtml = content.getBody().replace("class=\"img-place-holder\"", "class=\"img-place-holder-ignored\"");
        mWebView.loadData(formatCss + formatHtml, "text/html; charset=UTF-8", null);

    }

    @Override
    public void updateToolbar(StoryExtra extra) {
        Log.d(TAG, "updateToolbar: "+extra.toString());
        mToolbar.inflateMenu(R.menu.story_detail);
        Menu menu = mToolbar.getMenu();

        MenuItem favorite = menu.findItem(R.id.favorite);
        if(isStoryExist()){
            favorite.setIcon(R.drawable.favorite_yellow);
        }

        TextActionProvider number = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.love));
        number.setResource(R.drawable.praise, extra.getPopularity());

        TextActionProvider comment = (TextActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.comment));
        comment.setResource(R.drawable.comment, extra.getComments());
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this, "尚未开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite:
                if(isStoryExist()){
                    item.setIcon(R.drawable.favorite);
                    //从数据库中移除
                    DataSupport.deleteAll(StoryLite.class, "storyId = ?", mStory.getId()+"");
                }else{
                    item.setIcon(R.drawable.favorite_yellow);
                    //添加到数据库中
                    StoryLite story = new StoryLite();
                    story.setMultiPic(mStory.isMultiPic());
                    story.setTitle(mStory.getTitle());
                    story.setImageUrl(mStory.getImageUrl());
                    story.setStoryId(mStory.getId());
                    boolean result = story.save();
                    Log.d(TAG, "onOptionsItemSelected: "+result);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }


    /**
     * //从数据库查询当前story是否已存在数据库中
     * @return
     */
    private boolean isStoryExist(){
        List<StoryLite> storyList = DataSupport.where("storyId =?",mStory.getId()+"").find(StoryLite.class);
        boolean isExist = false;
        if(storyList.size() > 0){
            isExist = true;
        }
        Log.d(TAG, "isStoryExist: "+isExist);
        return isExist;
    }

    private List<StoryLite> findAll(){
        return DataSupport.findAll(StoryLite.class);
    }

}
