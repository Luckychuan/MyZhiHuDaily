package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 主题日报内容查看
 */
public class ThemeContent {

    private List<Story> stories;
    private String description;
    @SerializedName("background")
    private String backgroundUrl;
    private String name;
    private List<Editor> editors;

    @Override
    public String toString() {
        return "ThemeContent{" +
                ", description='" + description + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Editor> getEditors() {
        return editors;
    }

    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

}
