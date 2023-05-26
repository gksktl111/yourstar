package com.example.yourstar.controller;

import com.example.yourstar.data.dto.PostDeleteDto;
import com.example.yourstar.data.dto.PostUpdateDto;
import com.example.yourstar.data.dto.PostWriteFormDto;
import com.example.yourstar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping(value = "/writePost")//글 작성
    public String writePost(@RequestBody PostWriteFormDto postWriteFormDto){
        if (postService.writePost(postWriteFormDto).equals("success")){
            System.out.println("게시글 작성 성공");
            return "success";
        }else {
            System.out.println("게시글 작성 실패");
            return "failed";
        }
    }

    @PostMapping
    public String updatePost(@RequestBody PostUpdateDto postUpdateDto){
        if (postService.updatePost(postUpdateDto).equals("success")){
            System.out.println("업데이트 성공");
            return "success";
        }else {
            System.out.println("업데이트 실패");
            return "failed";
        }
    }

    @PostMapping
    public String deletePost(@RequestBody PostDeleteDto postDeleteDto){
        if(postService.deletePost(postDeleteDto.getPostId()).equals("success")) {
            System.out.println("삭제 성공");
            return "success";
        }else {
            System.out.println("삭제 실패");
            return "failed";
        }
    }

}
