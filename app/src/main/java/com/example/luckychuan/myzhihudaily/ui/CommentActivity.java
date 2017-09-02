package com.example.luckychuan.myzhihudaily.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.adapter.CommentRecyclerAdapter;
import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.bean.ItemBean;
import com.example.luckychuan.myzhihudaily.presenter.CommentPresenter;
import com.example.luckychuan.myzhihudaily.view.CommentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public class CommentActivity extends AppCompatActivity implements CommentView {

    private static final String TAG = "CommentActivity";
    private CommentPresenter mPresenter;
    private List<ItemBean> mComments;
    private CommentRecyclerAdapter mAdapter;
    private int mSum;

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
        mSum = getIntent().getIntExtra("number", 0);
        toolbar.setTitle(mSum + "条点评");
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);


        mComments = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_comment);
        mAdapter = new CommentRecyclerAdapter(mComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);


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
        ItemBean longCommentText = new ItemBean(CommentRecyclerAdapter.TEXT, longComments.size() + "条长评");
        mComments.add(longCommentText);
        ItemBean shortCommentText = new ItemBean(CommentRecyclerAdapter.TEXT, mSum - longComments.size() + "条短评");
        mComments.add(mComments.size(),shortCommentText);
        mAdapter.notifyDataSetChanged();
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
