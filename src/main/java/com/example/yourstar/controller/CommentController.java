package com.example.yourstar.controller;

import com.example.yourstar.data.dto.comment.CommentDeleteDto;
import com.example.yourstar.data.dto.comment.CommentUpdateDto;
import com.example.yourstar.data.dto.comment.CommentWriteFormDto;
import com.example.yourstar.data.entity.CommentEntity;
import com.example.yourstar.service.CommentService;
import com.example.yourstar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/write_comment")
    public ResponseEntity<?> writeComment(@RequestBody CommentWriteFormDto dto) {
        commentService.writeComment(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update_comment")
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateDto dto) {
        commentService.updateComment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete_comment")
    public ResponseEntity<?> deleteComment(@RequestBody CommentDeleteDto dto) {
        commentService.deleteComment(dto.getCommentsId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/toggle_likeComment")
    public ResponseEntity<?> toggleLikeComment(@RequestParam Long commentId, @RequestBody Authentication authentication) {
        CommentEntity commentEntity = commentService.ToggleLikeComment(authentication.name(), commentId);
        return commentEntity != null ? ResponseEntity.ok(commentEntity) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}