package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.Theme;

/**
 * Created by Luckychuan on 2017/5/4.
 */
public interface GetThemeModel {

    void getTheme(Callback<Theme> callback);

}
