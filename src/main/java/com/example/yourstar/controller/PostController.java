package com.example.yourstar.controller;

import com.example.yourstar.data.dto.*;
import com.example.yourstar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

@RequestMapping("/post")
@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    public Blob multipartFileToBlob(MultipartFile file) throws IOException, SQLException {
        byte[] fileContent = file.getBytes();
        Blob blob = new SerialBlob(fileContent);
        return blob;
    }

    @PostMapping(value = "/writePost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String writePost(@RequestPart("userId") String userId,
                            @RequestPart("contents") String contents,
                            @RequestPart("meta") MultipartFile meta) throws SQLException, IOException {

        System.out.println("포스트 넣기");

        PostWriteFormDto postWriteFormDto = new PostWriteFormDto();
        postWriteFormDto.setUserId(userId);
        postWriteFormDto.setContents(contents);

        // 메타 파일을 Blob으로 변환
        Blob metaBlob = multipartFileToBlob(meta);
        postWriteFormDto.setMeta(metaBlob);

        if (postService.writePost(postWriteFormDto).equals("success")) {
            return "success";
        } else {
            System.out.println("failed");
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
