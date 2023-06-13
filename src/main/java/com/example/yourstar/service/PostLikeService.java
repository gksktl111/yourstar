package com.example.yourstar.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface PostLikeService {

    void saveLikes(long postId, String name);//좋아요 저장 메소드

    void deleteLikes(long postId, String name);//좋아요 취소 메소드
}
