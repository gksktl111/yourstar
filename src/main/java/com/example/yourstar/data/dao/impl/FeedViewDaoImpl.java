package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.FeedViewDao;
import com.example.yourstar.data.entity.FeedViewEntity;
import com.example.yourstar.data.repository.FeedViewRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedViewDaoImpl implements FeedViewDao {

    private FeedViewRepository feedViewRepository;

    public FeedViewDaoImpl(FeedViewRepository feedViewRepository) { this.feedViewRepository = feedViewRepository; }

    @Override
    public List<FeedViewEntity> findAllByUser_Followers_UserId(String userId, Pageable pageable) {
        return feedViewRepository.findAllByUser_Followers_UserId(userId, pageable);
    }
}