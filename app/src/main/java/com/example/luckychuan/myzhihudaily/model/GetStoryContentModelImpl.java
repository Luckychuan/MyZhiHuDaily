package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/5/8.
 */
public class GetStoryContentModelImpl implements GetStoryContentModel {
    @Override
    public void getStoryContent(String id, final Callback<StoryContent> callback) {
        RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.getMessage());
                    }


                    @Override
                    public void onNext(StoryContent content) {
                        callback.onSuccess(content);
                    }
                });
    }


}
