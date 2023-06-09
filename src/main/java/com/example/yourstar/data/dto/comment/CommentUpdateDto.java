package com.example.yourstar.data.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {
    private String userId;
    private String text;
    private long commentsId;
    private long postId;
    private long commentsLikeCount;
}
