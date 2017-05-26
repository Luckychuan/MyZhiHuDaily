package com.example.luckychuan.myzhihudaily.bean;

/**
 * 首页的RecyclerView的Item
 */
public class ItemBean {
    public int type;
    public Object bean;

    public ItemBean(int type, Object bean) {
        this.type = type;
        this.bean = bean;
    }
}