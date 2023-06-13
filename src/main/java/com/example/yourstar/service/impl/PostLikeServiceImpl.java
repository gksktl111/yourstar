package com.example.yourstar.service.impl;

import com.example.yourstar.data.repository.PostLikeRepository;
import com.example.yourstar.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    @Autowired
    PostLikeRepository postLikeRepository;

    @Transactional
    public void saveLikes(long postId, String userId) {//postId와 userId 인자로 받음
        postLikeRepository.insertPostLike(postId, userId);
    }

    @Transactional
    public void deleteLikes(long postId, String userId) {//postId와 userId 인자로 받음
        postLikeRepository.deleteByPostEntityPostIdAndUserEntityUserId(postId, userId);
    }
}