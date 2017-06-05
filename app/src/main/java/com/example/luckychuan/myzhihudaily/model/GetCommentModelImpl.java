package com.example.luckychuan.myzhihudaily.model;

import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.bean.Comments;
import com.example.luckychuan.myzhihudaily.retrofit.ApiService;
import com.example.luckychuan.myzhihudaily.retrofit.RetrofitUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public class GetCommentModelImpl implements GetCommentModel {

    private ApiService mService;

    public GetCommentModelImpl() {
        mService = RetrofitUtil.getInstance()
                .getRetrofit()
                .create(ApiService.class);
    }

    @Override
    public void getLongComment(String id, final Callback<Comments> callback) {
        mService.getLongComments(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Comments>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.toString());
                    }

                    @Override
                    public void onNext(Comments comments) {
                        callback.onSuccess(comments);
                    }
                });
    }

    @Override
    public void getShortComment(String id, final Callback<Comments> callback) {
        mService.getShortComments(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Comments>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.toString());
                    }

                    @Override
                    public void onNext(Comments comments) {
                        callback.onSuccess(comments);
                    }
                });
    }
}
