package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.Comments;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public interface GetCommentModel {

    void getLongComment(String id, Callback<Comments> callback);
    void getShortComment(String id, Callback<Comments> callback);

}
