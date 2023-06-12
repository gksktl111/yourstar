package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedViewDto {
    private String userId;
    private long postId;
    private String contents;
    private String meta;
    private Timestamp postTime;
    private long likeCount;
    private Boolean status;
}