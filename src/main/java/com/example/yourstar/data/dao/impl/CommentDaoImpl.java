package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.CommentDao;
import com.example.yourstar.data.entity.CommentEntity;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.CommentLikeRepository;
import com.example.yourstar.data.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentDaoImpl implements CommentDao {

    private CommentRepository commentRepository;

    private CommentLikeRepository commentLikeRepository;

    public CommentDaoImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentEntity writeComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public CommentEntity updateComment(PostEntity post, UserEntity user, CommentEntity comment, String texts) {
        comment.setText(comment.getText());
        comment.setUserId(comment.getUserId());
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity deleteComment(long commentsId) {
        CommentEntity comment = commentRepository.findById(commentsId).orElseThrow();
        commentRepository.deleteById(commentsId);
        return comment;
    }


}
