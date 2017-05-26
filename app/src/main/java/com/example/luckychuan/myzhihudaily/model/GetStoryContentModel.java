package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.StoryContent;

/**
 * Created by Luckychuan on 2017/5/8.
 */
public interface GetStoryContentModel {

    void getStoryContent(int id ,Callback<StoryContent> callback);
}
