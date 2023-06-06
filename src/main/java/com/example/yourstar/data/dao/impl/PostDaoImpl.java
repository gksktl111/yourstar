package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.PostDao;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostDaoImpl implements PostDao {

    private PostRepository postRepository;

    public PostDaoImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity writePost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity updatePost(PostEntity post, UserEntity user, String contents) {
        post.setContents(contents);
        post.setUserId(post.getUserId());
        post.setMeta(post.getMeta());
        return postRepository.save(post);
    }

    @Override
    public PostEntity deletePost(long postId) {
        PostEntity post = postRepository.findById(String.valueOf(postId)).orElseThrow();
        postRepository.deleteById(String.valueOf(postId));
        return post;
    }

    @Override
    public PostEntity likePost(PostEntity post, long likeCount) {
        post.setLikeCount(likeCount);
        return postRepository.save(post);
    }

    @Override
    public PostEntity unlikePost(PostEntity post, long likeCount) {
        post.setLikeCount(likeCount);
        return postRepository.save(post);
    }

    @Override
    public PostEntity viewPost(PostEntity post, long viewCount) {
        post.setViewCount(viewCount);
        return postRepository.save(post);
    }

    @Override
    public PostEntity sharePost(PostEntity post, long shareCount) {
        post.setShareCount(shareCount);
        return postRepository.save(post);
    }

    @Override
    public PostEntity unsharePost(PostEntity post, long shareCount) {
        post.setLikeCount(shareCount);
        return postRepository.save(post);
    }

}
