package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Arrays;

/**
 * RecyclerView的新闻的新闻和标题
 */
public class Story implements Serializable{
    @SerializedName("images")
    private String[] imageUrl;
    private String id;
    private String title;
    @SerializedName("multipic")
    private boolean isMultiPic;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
