package com.example.luckychuan.myzhihudaily.view;

import com.example.luckychuan.myzhihudaily.bean.Comment;

import java.util.List;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public interface CommentView extends BaseView {
    void showProgressDialog();

    void hideProgressDialog();

    void initLongComment(List<Comment> longComments);

    void initShortComment(List<Comment> shortComments);
}
