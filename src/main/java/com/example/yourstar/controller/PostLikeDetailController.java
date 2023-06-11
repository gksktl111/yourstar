package com.example.yourstar.controller;

import com.example.yourstar.data.dto.post.PostLikeDetailDto;
import com.example.yourstar.service.PostLikeDetailService;
import javassist.NotFoundException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post_like_detail")
public class PostLikeDetailController {
    private PostLikeDetailService postLikeDetailService;

    @Autowired
    public PostLikeDetailController(PostLikeDetailService postLikeDetailService) {
        this.postLikeDetailService = postLikeDetailService;
    }

    @PostMapping(value = "/likesPost")
    public ResponseEntity<PostLikeDetailDto> addLike(@RequestBody PostLikeDetailDto postLikeDetailDto) throws NotFoundException {
        Long postId = postLikeDetailDto.getPostId();
        String userId = postLikeDetailDto.getUserId();

        PostLikeDetailDto result = postLikeDetailService.addLike(postId, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<PostLikeDetailDto>> getLikesByPostId(@PathVariable Long postId) {
        List<PostLikeDetailDto> result = postLikeDetailService.getLikesByPostId(postId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{postId}/{userId}/cancel")
    public ResponseEntity<Void> cancelLike(@PathVariable Long postId, @PathVariable Authentication authentication) throws NotFoundException {
        postLikeDetailService.cancelLike(postId, authentication.name());
        return ResponseEntity.ok().build();
    }
}