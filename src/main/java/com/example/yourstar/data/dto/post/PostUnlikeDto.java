package com.example.yourstar.data.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUnlikeDto {
    private long postId;
    private long likeCount;
}