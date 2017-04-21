package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻首页API的数据
 */
public class LatestData {

    private String date;
    private List<Story> stories;
    @SerializedName("top_stories")
    private List<TopStory> topStories;

    public LatestData(){
        stories = new ArrayList<>();
        topStories = new ArrayList<>();
    }


    public class Story {
        @SerializedName("images")
        private String[] imageUrl;
        private String id;
        private String title;

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

        @Override
        public String toString() {
            return "Story{" +
                    "imageUrl='" + imageUrl + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public class TopStory {
        @SerializedName("image")
        private String imageUrl;
        private String id;
        private String title;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
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

        @Override
        public String toString() {
            return "Story{" +
                    "imageUrl='" + imageUrl + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStorys(List<TopStory> topStories) {
        this.topStories = topStories;
    }
}
