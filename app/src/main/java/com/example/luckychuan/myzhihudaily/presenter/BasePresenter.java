package com.example.luckychuan.myzhihudaily.presenter;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public abstract  class BasePresenter<T> {

    public T mView;

    public void attach(T view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }

}