package com.example.luckychuan.myzhihudaily.view;

import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;


public interface StoryContentView extends BaseView {

    void updateUI(StoryContent content);
    void updateToolbar(StoryExtra extra);

}
