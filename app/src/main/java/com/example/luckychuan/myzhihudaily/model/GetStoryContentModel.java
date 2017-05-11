package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;

/**
 * Created by Luckychuan on 2017/5/8.
 */
public interface GetStoryContentModel {

    void getStoryContent(int id ,Callback<StoryContent> callback);
    void getStoryExtra(int id, Callback<StoryExtra> callback);
}
