package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, String> {
    long countByUserId(String userId); // userProfile 개시물 수 구하기
    Page<PostEntity> findByUserIdOrderByPostTimeDesc(String userId, Pageable pageable); // 프로필 게시물 page(postId,meta) 불러오기
}
