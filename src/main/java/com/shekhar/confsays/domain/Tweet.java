package com.shekhar.confsays.domain;

public class Tweet {
    private String id;

    private long tweetId;

    private String text;

    private String tweetedBy;

    private long retweetId;

    private long retweetCount;


    public Tweet(String id, long tweetId, String text, String tweetedBy) {
        this.id = id;
        this.tweetId = tweetId;
        this.text = text;
        this.tweetedBy = tweetedBy;
    }

    public Tweet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTweetedBy() {
        return tweetedBy;
    }

    public void setTweetedBy(String tweetedBy) {
        this.tweetedBy = tweetedBy;
    }

    public long getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(long retweetCount) {
        this.retweetCount = retweetCount;
    }

    public long getRetweetId() {
        return retweetId;
    }

    public void setRetweetId(long retweetId) {
        this.retweetId = retweetId;
    }
}
