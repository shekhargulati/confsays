package com.shekhar.confsays.service;

import java.util.List;

public class Job {

    private String name;
    private List<String> hashtags;
    private String id;

    public Job(List<String> hashtags, String name, String id) {
        this.hashtags = hashtags;
        this.name = name;
        this.id = id;
    }

    public Job() {
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
