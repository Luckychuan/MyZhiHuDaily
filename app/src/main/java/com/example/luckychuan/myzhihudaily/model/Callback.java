package com.example.luckychuan.myzhihudaily.model;

/**
 * 获得数据后的回调
 */
public interface Callback<T> {

    void onSuccess( T bean);
    void onFail(String errorMsg);

}
