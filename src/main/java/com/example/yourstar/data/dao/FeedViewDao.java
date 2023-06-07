package com.example.yourstar.data.dao;

import com.example.yourstar.data.entity.FeedViewEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedViewDao {
    List<FeedViewEntity> findAllByUser_Followers_UserId(String userId, Pageable pageable);
}
