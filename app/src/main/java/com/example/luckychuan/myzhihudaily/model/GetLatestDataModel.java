package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.LatestData;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public interface GetLatestDataModel {

    void getLatestData(Callback<LatestData> callback);

}
