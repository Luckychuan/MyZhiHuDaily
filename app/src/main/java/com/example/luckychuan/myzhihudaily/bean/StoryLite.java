package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.Arrays;

/**
 * litepal用到的bean
 */

public class StoryLite extends DataSupport{

    private int id;
    private String imageUrl;
    private String storyId;
    private String title;
    private boolean isMultiPic;

    @Override
    public String toString() {
        return "StoryLite{" +
                "id=" + id +
                ", imageUrl=" + imageUrl +
                ", storyId='" + storyId + '\'' +
                ", title='" + title + '\'' +
                ", isMultiPic=" + isMultiPic +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultiPic() {
        return isMultiPic;
    }

    public void setMultiPic(boolean multiPic) {
        isMultiPic = multiPic;
    }
}
