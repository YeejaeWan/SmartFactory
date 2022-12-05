package com.example.smartfactory.Activity.UserItemAdapter;

public class UserItem {
    private long followershipIndex;
    private String userName;
    private String context;

    public UserItem(long followershipIndex, String userName, String context) {
        this.followershipIndex=followershipIndex;
        this.userName = userName;
        this.context = context;
    }public UserItem(long followershipIndex, String userName) {
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