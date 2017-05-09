package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.presenter.GetStoryContentPresenter;
import com.example.luckychuan.myzhihudaily.view.StoryContentView;

/**
 * 新闻的内容
 */
public class StoryActivity extends AppCompatActivity implements StoryContentView {

    private static final String TAG = "StoryActivity";
    private GetStoryContentPresenter mPresenter;

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        String id = getIntent().getStringExtra("story_id");
        Log.d(TAG, "onCreate: " + id);

        mPresenter = new GetStoryContentPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestData(Integer.parseInt(id));

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolBar);

        initWebView();

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

        String formatCss= String.format( "<link href=\"%s\" rel=\"stylesheet\" type=\"text/css\"/>",content.getCss().get(0));
        String formatHtml = content.getBody().replace( "class=\"img-place-holder\"", "class=\"img-place-holder-ignored\"");
        mWebView.loadData(formatCss+formatHtml, "text/html; charset=UTF-8", null);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
