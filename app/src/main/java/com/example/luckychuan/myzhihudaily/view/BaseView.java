package com.example.luckychuan.myzhihudaily.view;


public interface BaseView<T> {

    void updateUI(T data);
    void showErrorMsg(String error);
}
