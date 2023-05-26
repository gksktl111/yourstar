package com.example.yourstar.service;

import com.example.yourstar.data.dto.PostUpdateDto;
import com.example.yourstar.data.dto.PostWriteFormDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface PostService {
    String writePost(PostWriteFormDto postWriteFormDto); //게시글 쓰기 기능

    String writePost(PostWriteFormDto postWriteFormDto, Authentication authentication);

    String updatePost(PostUpdateDto postUpdateDto); //게시글 업데이트 기능

    String deletePost(long postId);
}
