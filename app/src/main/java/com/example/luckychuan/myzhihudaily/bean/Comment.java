package com.example.luckychuan.myzhihudaily.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public class Comment {

    private String author;
    private String content;
    private String avatar;
    private long time;
    @SerializedName("reply_to")
    private ReplyTo replyTo;
    private long id;
    private int likes;




    public class ReplyTo {
        private String content;
        private int status;
        private long id;
        private String author;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "ReplyTo{" +
                    "content='" + content + '\'' +
                    ", status=" + status +
                    ", id=" + id +
                    ", author='" + author + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", time=" + time +
                ", replyTo=" + replyTo +
                ", id=" + id +
                ", likes=" + likes +
                '}';
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ReplyTo getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ReplyTo replyTo) {
        this.replyTo = replyTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }



}
