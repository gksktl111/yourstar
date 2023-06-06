package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteFormDto {
    private String userId;
    private String contents;
    private Blob meta;
    private Timestamp postTime;
    private long likeCount;
    private long viewCount;
    private long shareCount;
    private String category;

    private MultipartFile imageFile;
    private MultipartFile videoFile;

    public String getUserId() {
        return userId;
    }
}