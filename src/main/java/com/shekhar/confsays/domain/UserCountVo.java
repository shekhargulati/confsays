package com.shekhar.confsays.domain;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 23/01/14
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserCountVo {

    private String twitterHandle;
    private long count;

    public UserCountVo(long count, String twitterHandle) {
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
