package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luckychuan on 2017/5/8.
 */
public class StoryContent implements Serializable{

    private String body;
    @SerializedName("image_source")
    private String imageSource;
    private String title;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("share_url")
    private String shareUrl;
    private int id;
    private List<String> css;
    private List<String> js;





    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getJs() {
        return js;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "StoryContent{" +
                "body='" + body + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", id=" + id +
                ", css=" + css +
                '}';
    }
}
