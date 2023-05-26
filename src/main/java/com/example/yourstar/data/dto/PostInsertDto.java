package com.example.yourstar.data.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostInsertDto {
    private String userId;
    private Timestamp postTime;
    private long likeCount;
    private long viewCount;
    private long shareCount;
    private String category;
}