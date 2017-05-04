package com.example.luckychuan.myzhihudaily.retrofit;

import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.Theme;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public interface ApiService {

    @GET("api/4/news/latest")
    Observable<LatestData> getLatestData();

    @GET("api/4/news/before/{date}")
    Observable<News> getOldData(@Path("date") String date);

    @GET("api/4/themes")
    Observable<Theme> getTheme();

}
