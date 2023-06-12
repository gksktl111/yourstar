package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFeedViewDto {
    private String userId;
    private String userName;
    private String userProFileImg;
    private long postId;
    private String meta;
    private String contents;
    private long likeCount;
    private boolean likeStatus;
}
