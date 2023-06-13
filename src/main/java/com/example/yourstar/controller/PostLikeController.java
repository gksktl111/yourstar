package com.example.yourstar.controller;

import com.example.yourstar.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "postLike")
@RestController
public class PostLikeController {

    @Autowired
    PostLikeService postLikeService;

    @PostMapping("postLike")//좋아요가 눌러져있지 않은 경우 postMapping으로 좋아요 처리
    public ResponseEntity<String> PostLike(long postId, Authentication authentication){//pathvariable 어노테이션으로 url 처리, 파라미터는 postId
        postLikeService.saveLikes(postId, authentication.getName());
        return ResponseEntity.ok().body("좋아요");
    }

    @DeleteMapping("postLike")//좋아요가 눌러진 경우 deleteMapping으로 좋아요 취소 처리
    public ResponseEntity<String> unPostLike(long postId, Authentication authentication){
        postLikeService.deleteLikes(postId, authentication.getName());
        return ResponseEntity.ok().body("좋아요 해제");
    }
}
