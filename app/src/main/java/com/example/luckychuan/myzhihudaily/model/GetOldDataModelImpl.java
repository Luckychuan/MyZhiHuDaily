package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/4/25.
 */
public class GetOldDataModelImpl implements GetOldDataModel {
    @Override
    public void getOldData(String date, final Callback<News> callback) {
        RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getOldData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(News news) {
                        callback.onSuccess(news);
                    }
                });
    }
}
