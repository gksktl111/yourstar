package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.CommentDao;
import com.example.yourstar.data.entity.CommentEntity;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.CommentRepository;
import com.example.yourstar.data.repository.PostRepository;

public class CommentDaoImpl implements CommentDao {
    private CommentRepository commentRepository;

    public CommentDaoImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentEntity writeComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public CommentEntity updatePost(CommentEntity comment, UserEntity user, String contents) {
        comment.setContents(contents);
        comment.setUserId(comment.getUserId());
        comment.setMeta(comment.getMeta());
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity deletePost(long postId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.deleteById(postId);
        return comment;
    }

    @Override
    public CommentEntity likePost(CommentEntity comment, long likeCount) {
        comment.setLikeCount(likeCount);
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity unlikePost(CommentEntity comment, long likeCount) {
        comment.setLikeCount(likeCount);
        return commentRepository.save(comment);
    }
}
