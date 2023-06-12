package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    long countByUserId(String userId); // userProfile 개시물 수 구하기
    Page<PostEntity> findByUserIdOrderByPostTimeDesc(String userId, Pageable pageable); // 프로필 게시물 page(postId,meta) 불러오기
    Page<PostEntity> findByUserNot(UserEntity user, Pageable pageable); // user빼고 모든 post 가져오기
}
