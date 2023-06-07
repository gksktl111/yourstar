package com.example.yourstar.data.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
    private String userId;
    private String contents;
    private Blob meta;

    private MultipartFile imageFile;
    private MultipartFile videoFile;

    public Long getPostId() {
        return userId;
    }
}