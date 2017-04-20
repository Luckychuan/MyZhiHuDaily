package com.example.luckychuan.myzhihudaily.model;

import android.util.Log;

import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public class GetLatestDataModelImpl implements GetLatestDataModel {

    @Override
    public void getLatestData(final Callback<LatestData> callback) {
        RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getLatestData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LatestData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivity", "onError: "+e.getMessage());
                        callback.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(LatestData latestData) {
                        callback.onSuccess(latestData);
                    }
                });
    }
}
