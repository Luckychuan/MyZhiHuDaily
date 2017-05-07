package com.example.luckychuan.myzhihudaily.bean;

public class Editor{

    private String bio;
    private String avatar;
    private String name;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "bio='" + bio + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}