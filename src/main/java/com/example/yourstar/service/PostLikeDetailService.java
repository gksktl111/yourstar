package com.example.yourstar.service;

import com.example.yourstar.data.dto.post.PostLikeDetailDto;
import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostLikeDetailService {
    PostLikeDetailDto addLike(Long postId, String UserId) throws NotFoundException;
    List<PostLikeDetailDto> getLikesByPostId(Long postId);
    @Transactional
    void cancelLike(Long postId, String userId) throws NotFoundException;
}
