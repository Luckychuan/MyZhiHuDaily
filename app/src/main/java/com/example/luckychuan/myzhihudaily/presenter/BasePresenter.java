package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.view.BaseView;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public abstract  class BasePresenter<T extends BaseView> {

    public T mView;

    public void attach(T view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }

}