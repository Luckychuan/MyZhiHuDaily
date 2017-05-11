package com.example.luckychuan.myzhihudaily.bean;



public class StoryExtra {

    private int comments;
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "StoryExtra{" +
                "comments=" + comments +
                ", popularity=" + popularity +
                '}';
    }
}
