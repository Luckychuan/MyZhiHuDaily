package com.example.luckychuan.myzhihudaily.retrofit;

import android.app.Application;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public class RetrofitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitUtil.getInstance().init(getApplicationContext());
    }
}
