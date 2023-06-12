package com.example.yourstar.controller;

import com.example.yourstar.data.dto.GetFeedViewDto;
import com.example.yourstar.data.dto.post.*;
import com.example.yourstar.data.dto.profile.PageDto;
import com.example.yourstar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/writePost")
    public String writePost(Authentication authentication,
                            @RequestParam("contents") String contents,
                            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
                            @RequestPart(value = "videoFile", required = false) MultipartFile videoFile) {

        System.out.println("데이터는 넘어옴");
        System.out.println(authentication.getName());
        System.out.println(contents);
        System.out.println(imageFile);
        System.out.println(videoFile);

        PostWriteFormDto postWriteFormDto = new PostWriteFormDto();

        postWriteFormDto.setUserId(authentication.getName());
        postWriteFormDto.setContents(contents);

        if (imageFile != null && videoFile != null) {
            return "failed";
        }

        if (imageFile != null) {
            postWriteFormDto.setImageFile(imageFile);
        }

        if (videoFile != null) {
            postWriteFormDto.setVideoFile(videoFile);
        }

        System.out.println("트라이 직전 까지옴");

        try {
            if (postService.writePost(postWriteFormDto).equals("success")) {
                System.out.println("성공");
                return "success";
            } else {
                System.out.println("실패");
                return "failed";
            }
        } catch (Exception e) {
            System.out.println("캐치");
            e.printStackTrace();
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

    @PostMapping(value = "/get_feed_view")
    public List<GetFeedViewDto> getFeedView(Authentication authentication, @RequestBody PageDto pageDto){
        return postService.getFeedView(authentication.getName(),pageDto.getPage());
    }
}
