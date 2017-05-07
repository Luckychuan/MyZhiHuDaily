package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.luckychuan.myzhihudaily.R;

/**
 * 新闻的内容
 */
public class StoryActivity extends AppCompatActivity {

    private static final String TAG = "StoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_content_layout);

        String id = getIntent().getStringExtra("story_id");
        Log.d(TAG, "onCreate: "+id);

    }
}
