package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.ThemeContent;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public interface GetThemeContentModel {

    void getThemeContent(String id, Callback<ThemeContent> callback);

}
