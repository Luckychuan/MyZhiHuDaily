package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.StoryExtra;

/**
 * Created by Luckychuan on 2017/5/26.
 */

public interface GetStoryExtraModel {
    void getStoryExtra(String id, Callback<StoryExtra> callback);
}
