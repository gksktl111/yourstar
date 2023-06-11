package com.example.yourstar.data.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeDetailDto {
    private Long commentLikeId;
    private Long commentsId;
    private String userId;
    private Boolean status; // 좋아요 상태를 나타내는 필드 추가
}
