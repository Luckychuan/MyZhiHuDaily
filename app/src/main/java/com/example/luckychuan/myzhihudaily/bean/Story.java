package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

/**
 * RecyclerView的新闻的新闻和标题
 */
public class Story {
    @SerializedName("images")
    private String[] imageUrl;
    private String id;
    private String title;
    @SerializedName("multipic")
    private boolean isMultiPic;




    public String getImageUrl() {
        return imageUrl[0];
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

    public void setIsMultipic(boolean isMultiPic) {
        this.isMultiPic = isMultiPic;
    }
}
