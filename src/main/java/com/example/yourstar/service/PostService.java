package com.example.yourstar.service;

import com.example.yourstar.data.dto.PostUpdateDto;
import com.example.yourstar.data.dto.PostWriteFormDto;
import com.example.yourstar.data.entity.PostEntity;

import java.io.IOException;

public interface PostService {


    String writePost(PostWriteFormDto postWriteFormDto) throws IOException;

    String updatePost(PostUpdateDto postUpdateDto); //게시글 업데이트 기능

    String deletePost(long postId);

    PostEntity likePost(long postId);

    PostEntity unlikePost(long postId);

    PostEntity viewPost(long postId);

    PostEntity sharePost(long postId);

    PostEntity unsharePost(long postId);
}
