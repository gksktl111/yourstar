package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.PostLikeDetailEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeDetailRepository extends JpaRepository<PostLikeDetailEntity, String> {

    List<PostLikeDetailEntity> findByPostId(Long postId);
    Optional<PostLikeDetailEntity> findByPostIdAndUserId(Long postId, String userId);
    Optional<UserEntity> findByUserId(String userId);

    PostLikeDetailEntity findByPost(PostEntity postId);
}
