package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.PostDao;
import com.example.yourstar.data.dto.PostUpdateDto;
import com.example.yourstar.data.dto.PostWriteFormDto;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.repository.PostRepository;
import com.example.yourstar.service.PostService;
import com.example.yourstar.service.exception.PostNotFoundException;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    private PostDao postDao;

    private PostEntity postEntity;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostDao postDao) {
        this.postRepository = postRepository;
        this.postDao = postDao;
    }

    @Override
    public String writePost(PostWriteFormDto postWriteFormDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setUserId(postWriteFormDto.getUserId());
        postEntity.setPostTime(new Timestamp(System.currentTimeMillis()));
        postEntity.setContents(postWriteFormDto.getContents());
        postEntity.setLikeCount(0);
        postEntity.setViewCount(0);
        postEntity.setShareCount(0);
        postEntity.setCategory("default");

        try {
            if (postWriteFormDto.getImageFile() != null) {
                byte[] imageBytes = postWriteFormDto.getImageFile().getBytes();
                postEntity.setMeta(BlobProxy.generateProxy(imageBytes));
            }
            if (postWriteFormDto.getVideoFile() != null) {
                byte[] videoBytes = postWriteFormDto.getVideoFile().getBytes();
                postEntity.setMeta(BlobProxy.generateProxy(videoBytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }

        try {
            postRepository.save(postEntity);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String updatePost(PostUpdateDto postUpdateDto) {
        PostEntity postEntity = postRepository.getById(postUpdateDto.getPostId());

        if (postEntity == null) {
            return "failed";
        }

        postEntity.setContents(postUpdateDto.getContents());
        postEntity.setMeta(postUpdateDto.getMeta());
        try {
            postRepository.save(postEntity); //postEntity 정보를 업데이트하고 저장
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override //데이터베이스에서 글 삭제
    public String deletePost(long postId) {
        try {
            postRepository.deleteById(String.valueOf(postId)); //형변환 실패 방지를 위해 string 객체로 래핑
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public PostEntity likePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(String.valueOf(postId));

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //likeCount 1 증가
        postEntity.setLikeCount(postEntity.getLikeCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity unlikePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(String.valueOf(postId));

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //likeCount 1 감소
        postEntity.setLikeCount(postEntity.getLikeCount() - 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity viewPost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(String.valueOf(postId));

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //viewCount 1 증가
        postEntity.setViewCount(postEntity.getViewCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity sharePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(String.valueOf(postId));

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //shareCount 1 증가
        postEntity.setShareCount(postEntity.getShareCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity unsharePost(long postId) {
        // postId 에 해당하는 게시물을 데이터베이스에서 조회합니다.
        PostEntity postEntity = postRepository.getById(String.valueOf(postId));

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //shareCount 1 감소
        postEntity.setShareCount(postEntity.getShareCount() - 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

}
