package com.example.luckychuan.myzhihudaily.retrofit;

import com.example.luckychuan.myzhihudaily.bean.LatestData;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public interface ApiService {

    @GET("api/4/news/latest")
    Observable<LatestData> getLatestData();

}
