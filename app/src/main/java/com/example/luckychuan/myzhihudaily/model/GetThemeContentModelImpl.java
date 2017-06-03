package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.ThemeContent;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class GetThemeContentModelImpl implements GetThemeContentModel {
    @Override
    public void getThemeContent(String id, final Callback<ThemeContent> callback) {
        RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getThemeContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemeContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(ThemeContent content) {
                        callback.onSuccess(content);
                    }
                });
    }
}
