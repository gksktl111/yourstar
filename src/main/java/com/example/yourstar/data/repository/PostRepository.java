package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    long countByUserId(String userId); // userProfile 개시물 수 구하기
    Page<PostEntity> findByUserIdOrderByPostTimeDesc(String userId, Pageable pageable); // 프로필 게시물 page(postId,meta) 불러오기
    List<PostEntity> findAllByUserIdIn(List<String> followedUserIds, Pageable pageable);
    
}
