package com.example.smartfactory.network.DTO;


import java.time.LocalDateTime;


public class FollowerShipDTO {
    private Long index;
    private Long followerUserIndex;
    private Long followUserIndex;
    private boolean enable;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public FollowerShipDTO(Long index, Long followerUserIndex, Long followUserIndex, boolean enable, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.index = index;
        this.followerUserIndex = followerUserIndex;
        this.followUserIndex = followUserIndex;
        this.enable = enable;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}