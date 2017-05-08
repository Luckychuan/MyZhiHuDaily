package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_content_layout);

        String id = getIntent().getStringExtra("story_id");
        Log.d(TAG, "onCreate: " + id);

        mPresenter = new GetStoryContentPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestData(Integer.parseInt(id));

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void updateUI(StoryContent content) {
        Log.d(TAG, "updateUI: " + content.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
