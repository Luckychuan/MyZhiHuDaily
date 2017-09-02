package com.example.luckychuan.myzhihudaily.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.presenter.CommentPresenter;
import com.example.luckychuan.myzhihudaily.view.CommentView;

import java.util.List;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public class CommentActivity extends Activity implements CommentView {

    private static final String TAG = "CommentActivity";
    private CommentPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        mPresenter = new CommentPresenter(this);
        mPresenter.attach(this);
        String id = getIntent().getStringExtra("id");
        Log.d(TAG, "onCreate: " + id);
        mPresenter.requestLongComment(id);
        mPresenter.requestShortComment(id);

        //初始化Toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_comment);
        toolbar.setTitle(getIntent().getIntExtra("number", 0) + "条点评");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void initLongComment(List<Comment> longComments) {
        Log.d(TAG, "long size " + longComments.size());
        for (Comment comment : longComments) {
            Log.d(TAG, "long: " + comment.toString());
        }
    }

    @Override
    public void initShortComment(List<Comment> shortComments) {
        Log.d(TAG, "short size " + shortComments.size());
        for (Comment comment : shortComments) {
            Log.d(TAG, "short: " + comment.toString());
        }
    }


    @Override
    public void showErrorMsg(String error) {

    }
}
