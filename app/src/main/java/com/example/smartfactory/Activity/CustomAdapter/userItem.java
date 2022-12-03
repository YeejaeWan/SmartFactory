package com.example.smartfactory.Activity.CustomAdapter;

public class userItem{
    private long followershipIndex;
    private String userName;
    private String context;

    public userItem(long followershipIndex,String userName, String context) {
        this.followershipIndex=followershipIndex;
        this.userName = userName;
        this.context = context;
    }public userItem(long followershipIndex,String userName) {
        this.followershipIndex=followershipIndex;
        this.userName = userName;
    }

    public long getFollowershipIndex() {
        return followershipIndex;
    }

    public String getUserName() {
        return userName;

    }

    public String getContext() {
        return context;
    }
}