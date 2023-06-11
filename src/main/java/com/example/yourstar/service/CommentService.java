package com.example.yourstar.service;

import com.example.yourstar.data.dto.comment.*;
import com.example.yourstar.data.entity.CommentEntity;

public interface CommentService {
    String writeComment(CommentWriteFormDto commentWriteFormDto);

    String updateComment(CommentUpdateDto commentUpdateDto);

    String deleteComment(long commentsId);

    CommentEntity ToggleLikeComment(String user, Long commentId);
}
