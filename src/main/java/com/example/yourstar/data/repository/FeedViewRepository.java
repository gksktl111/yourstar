package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.FeedViewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedViewRepository extends JpaRepository<FeedViewEntity, Long> {
    List<FeedViewEntity> findAllByUser_Followers_UserId(String userId, Pageable pageable);
}