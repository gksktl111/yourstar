package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUnshareDto {
    private long postId;
    private long shareCount;
}