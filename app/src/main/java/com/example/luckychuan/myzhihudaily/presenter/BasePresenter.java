package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.view.BaseView;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public abstract  class BasePresenter {

    public BaseView mView;

    public void attach(BaseView view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }

}