package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("DELETE FROM CommentEntity c WHERE comments_id = :commentId")
    void deleteByCommentLike(@Param("commentId") Long commentId);
}