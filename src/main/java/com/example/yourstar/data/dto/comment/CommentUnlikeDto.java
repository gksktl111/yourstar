package com.example.yourstar.data.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUnlikeDto {
    private long commentsId;
    private long commentsLikeCount;
}
