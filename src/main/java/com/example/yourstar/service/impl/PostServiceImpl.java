package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.PostDao;

import com.example.yourstar.data.dto.PostUpdateDto;
import com.example.yourstar.data.dto.PostWriteFormDto;
import com.example.yourstar.service.PostService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Blob;

public class PostServiceImpl implements PostService {

    PostDao postDao;
    @Autowired
    public PostServiceImpl(PostDao userDao){
        this.postDao = postDao;
    }

    @Override
    public String writePost(PostWriteFormDto postWriteFormDto, Authentication authentication) {
        try {
            String contents = postWriteFormDto.getContents();
            Blob meta = postWriteFormDto.getMeta();
        }catch (Exception e){
            return null;
        }
        return null;
    }


    @Override
    public String updatePost(PostUpdateDto postUpdateDto) {
        return null;
    }

    @Override
    public String deletePost(long postId) {
        return null;
    }
}
