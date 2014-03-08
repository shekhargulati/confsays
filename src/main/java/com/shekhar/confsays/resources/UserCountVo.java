package com.shekhar.confsays.resources;

public class UserCountVo {

    private String twitterHandle;
    private long count;

    public UserCountVo(String twitterHandle, long count) {
        this.count = count;
        this.twitterHandle = twitterHandle;
    }
    
    public UserCountVo() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }
}
