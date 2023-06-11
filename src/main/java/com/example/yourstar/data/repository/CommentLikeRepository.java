package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.CommentLikeEntity;
import com.example.yourstar.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {
    void delete(CommentLikeEntity commentLike);
    CommentLikeEntity findByUserAndComment(String user, PostEntity comment);
}
