package com.shekhar.confsays.domain;


public class CountResult {
    private long count;

    public CountResult(long count) {
        this.count = count;
    }

    public CountResult() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
