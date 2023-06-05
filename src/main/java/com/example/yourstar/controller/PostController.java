package com.example.yourstar.controller;

import com.example.yourstar.data.dto.*;
import com.example.yourstar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/post")
@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping(value = "/writePost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//글 작성
    public String writePost(@RequestPart("postWriteFormDto") PostWriteFormDto postWriteFormDto,
                            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
                            @RequestPart(value = "videoFile", required = false) MultipartFile videoFile) throws IOException {

        // 이미지와 비디오 파일이 모두 없는 경우 처리
        if (imageFile == null && videoFile == null) {
            return "failed";
        }

        // 이미지와 비디오 파일이 모두 있는 경우 처리
        if (imageFile != null && videoFile != null) {
            return "failed";
        }

        if (imageFile != null) {
            postWriteFormDto.setImageFile(imageFile);
        }

        if (videoFile != null) {
            postWriteFormDto.setVideoFile(videoFile);
        }

        if (postService.writePost(postWriteFormDto).equals("success")) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping(value = "/updatePost")//글 업데이트
    public String updatePost(@RequestBody PostUpdateDto postUpdateDto) {
        if (postService.updatePost(postUpdateDto).equals("success")) {
            System.out.println("업데이트 성공");
            return "success";
        } else {
            System.out.println("업데이트 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/deletePost")//글 삭제
    public String deletePost(@RequestBody PostDeleteDto postDeleteDto) {
        if (postService.deletePost(postDeleteDto.getPostId()).equals("success")) {
            System.out.println("삭제 성공");
            return "success";
        } else {
            System.out.println("삭제 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/likePost")//좋아요
    public String likePost(@RequestBody PostLikeDto postLikeDto) {
        if (postService.likePost(postLikeDto.getPostId()).equals("success")) {
            System.out.println("♥");
            return "success";
        } else {
            System.out.println("오류 발생");
            return "failed";
        }
    }

    @PostMapping(value = "/unlikePost")//좋아요 취소
    public String unlikePost(@RequestBody PostUnlikeDto postUnlikeDto) {
        if (postService.unlikePost(postUnlikeDto.getPostId()).equals("success")) {
            System.out.println("♡");
            return "success";
        } else {
            System.out.println("오류 발생");
            return "failed";
        }
    }

    @PostMapping(value = "/sharePost")//공유하기
    public String sharePost(@RequestBody PostShareDto postShareDto) {
        if (postService.sharePost(postShareDto.getPostId()).equals("success")) {
            System.out.println("공유 성공!");
            return "success";
        } else {
            System.out.println("오류 발생");
            return "failed";
        }
    }

    @PostMapping(value = "/unsharePost")//공유하기
    public String sharePost(@RequestBody PostUnshareDto postUnshareDto) {
        if (postService.unsharePost(postUnshareDto.getPostId()).equals("success")) {
            System.out.println("공유 성공!");
            return "success";
        } else {
            System.out.println("오류 발생");
            return "failed";
        }
    }

    @PostMapping(value = "/viewPost")//조회수
    public String viewPost(@RequestBody PostViewDto postViewDto) {
        try {
            boolean isViewed = postService.viewPost(postViewDto.getPostId()) != null;
            if (isViewed) {
                return "success";
            } else {
                System.out.println("조회수 추가 실패");
                return "failed";
            }
        } catch (NullPointerException e) {
            System.out.println("오류 발생 : " + e.getMessage());
            return "failed";
        }
    }
}
