package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.News;

/**
 * Created by Luckychuan on 2017/4/25.
 */
public interface GetOldDataModel {

    void getOldData(String date,Callback<News> callback);

}
