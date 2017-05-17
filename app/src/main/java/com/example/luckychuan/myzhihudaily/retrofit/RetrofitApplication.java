package com.example.luckychuan.myzhihudaily.retrofit;


import org.litepal.LitePalApplication;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public class RetrofitApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitUtil.getInstance().init(getApplicationContext());
    }
}
