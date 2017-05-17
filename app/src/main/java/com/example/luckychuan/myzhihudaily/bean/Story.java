package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Arrays;

/**
 * RecyclerView的新闻的新闻和标题
 */
public class Story extends DataSupport implements Serializable{
    @SerializedName("images")
    private String[] imageUrl;
    @SerializedName("id")
    private String storyId;
    private String title;
    @SerializedName("multipic")
    private boolean isMultiPic;

    @Override
    public String toString() {
        return "Story{" +
                "imageUrl=" + Arrays.toString(imageUrl) +
                ", storyId='" + storyId + '\'' +
                ", title='" + title + '\'' +
                ", isMultiPic=" + isMultiPic +
                ", id=" + id +
                '}';
    }

    @SerializedName("litepalId")
    private long id;




    public String getImageUrl() {
        if(imageUrl !=null){
            return imageUrl[0];
        }else{
            return  "";
        }
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String id) {
        this.storyId = id;
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

    public void setMultiPic(boolean isMultiPic) {
        this.isMultiPic = isMultiPic;
    }
}
