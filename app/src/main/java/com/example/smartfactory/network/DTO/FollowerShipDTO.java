package com.example.smartfactory.network.DTO;


public class FollowerShipDTO {
    private Long index;
    private Long followerUserIndex;
    private Long followUserIndex;
    private boolean enable;
    private String createdDate;
    private String  modifiedDate;


    public FollowerShipDTO(Long index, Long followerUserIndex, Long followUserIndex, boolean enable, String createdDate, String modifiedDate) {
        this.index = index;
        this.followerUserIndex = followerUserIndex;
        this.followUserIndex = followUserIndex;
        this.enable = enable;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getIndex() {
        return index;
    }

    public Long getFollowerUserIndex() {
        return followerUserIndex;
    }

    public Long getFollowUserIndex() {
        return followUserIndex;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }
}