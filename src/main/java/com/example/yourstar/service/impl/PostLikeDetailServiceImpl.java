package com.example.yourstar.service.impl;

import com.example.yourstar.data.dto.post.PostLikeDetailDto;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.PostLikeDetailEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.PostLikeDetailRepository;
import com.example.yourstar.data.repository.PostRepository;
import com.example.yourstar.data.repository.UserRepository;
import com.example.yourstar.service.PostLikeDetailService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostLikeDetailServiceImpl implements PostLikeDetailService {
    private final PostLikeDetailRepository postLikeDetailRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Autowired
    public PostLikeDetailServiceImpl(PostLikeDetailRepository postLikeDetailRepository,
                                     PostRepository postRepository,
                                     UserRepository userRepository) {
        this.postLikeDetailRepository = postLikeDetailRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostLikeDetailDto addLike(Long postId, String userId) throws NotFoundException {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByUserId(userId));

        UserEntity user = userOptional.orElseThrow(() -> new NotFoundException("User not found"));

        PostLikeDetailEntity postLike = new PostLikeDetailEntity();
        postLikeDetailRepository.save(postLike);

        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);

        PostLikeDetailDto postLikeDetailDto = new PostLikeDetailDto();
        postLikeDetailDto.setId(postLike.getId());
        postLikeDetailDto.setPostId(postLike.getPostId());
        postLikeDetailDto.setUserId(postLike.getUserId());

        return postLikeDetailDto;
    }

    @Override
    public List<PostLikeDetailDto> getLikesByPostId(Long postId) {
        List<PostLikeDetailEntity> entities = postLikeDetailRepository.findByPostId(postId);
        List<PostLikeDetailDto> result = new ArrayList<>();

        for (PostLikeDetailEntity entity : entities) {
            PostLikeDetailDto dto = new PostLikeDetailDto();
            dto.setId(entity.getId());
            dto.setPostId(entity.getPostId());
            dto.setUserId(entity.getUserId());
            result.add(dto);
        }

        return result;
    }

    @Override
    public void cancelLike(Long postId, String userId) throws NotFoundException {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByUserId(userId));

        UserEntity user = userOptional.orElseThrow(() -> new NotFoundException("User not found"));

        PostLikeDetailEntity postLikeDetailEntity = postLikeDetailRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new NotFoundException("PostLike not found"));

        postLikeDetailRepository.delete(postLikeDetailEntity);

        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);
    }
}
