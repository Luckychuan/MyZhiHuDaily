package com.example.luckychuan.myzhihudaily.bean;

/**
 * 首页的RecyclerView的Item
 */
public class ItemBean<T> {
    public int type;
    public T bean;

    public ItemBean(int type, T bean) {
        this.type = type;
        this.bean = bean;
    }
}