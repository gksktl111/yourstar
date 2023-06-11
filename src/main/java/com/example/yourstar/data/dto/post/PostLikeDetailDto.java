package com.example.yourstar.data.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDetailDto {
    private Long id;
    private Long postId;
    private String userId;
    private Boolean status; // 좋아요 상태를 나타내는 필드 추가
}
