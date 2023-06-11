package com.example.yourstar.data.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteFormDto {
    private static String userId;
    private long commentsId;
    private long postId;
    private String text;
    private Timestamp commentTime;
    private long commentsLikeCount;

    public static String getUserId() {
        return userId;
    }
}
