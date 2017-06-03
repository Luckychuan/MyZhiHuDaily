package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/5/26.
 */

public class GetStoryExtraModelImpl implements GetStoryExtraModel {
    @Override
    public void getStoryExtra(String id, final Callback<StoryExtra> callback) {
        RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getStoryExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryExtra>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.getMessage());
                    }


                    @Override
                    public void onNext(StoryExtra extra) {
                        callback.onSuccess(extra);
                    }
                });
    }
}
