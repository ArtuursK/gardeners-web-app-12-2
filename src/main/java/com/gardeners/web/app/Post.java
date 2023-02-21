package com.gardeners.web.app;

import java.sql.Timestamp;

public class Post {

    private int post_id;
    private String authorName;
    private String description;
    private Timestamp datetime;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", datetime=" + datetime +
                "}\n\n";
    }
}
