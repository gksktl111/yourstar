package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserProfileDao;
import com.example.yourstar.data.dto.profile.GetUserProfileDto;
import com.example.yourstar.data.dto.profile.IdImageDto;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.PostSaveEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.*;
import com.example.yourstar.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileDao userProfileDao;
    private UserProfileRepository userProfileRepository;
    private FollowRepository followRepository;
    private PostRepository postRepository;
    private PostSaveRepository postSaveRepository;
    private UserRepository userRepository;
    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao,UserProfileRepository userProfileRepository,PostRepository postRepository,FollowRepository followRepository,PostSaveRepository postSaveRepository,UserRepository userRepository){
        this.userProfileDao = userProfileDao;
        this.userProfileRepository = userProfileRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
        this.postSaveRepository = postSaveRepository;
        this.userRepository = userRepository;
    }
    @Override
    public String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto) {
        try{
            userProfileDao.updateUserProfile(userId, updateUserProfileDto);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public GetUserProfileDto getUserProfile(String userId) {
        log.info("프로필 주인 : {}",userId);
        GetUserProfileDto userProfileDto = new GetUserProfileDto();
        UserProfileEntity userProfileEntity = userProfileRepository.getById(userId);
        UserEntity user = userRepository.getById(userId);
        log.info("[getFollowingCount] : {}",userProfileDao.getFollowingCounts(user));
        log.info("[getFollowersCount] : {}",userProfileDao.getFollowerCounts(user));

        if (userProfileEntity.getUserProfile() != null) {
            userProfileDto.setProfileImage(Base64.getEncoder().encodeToString(userProfileEntity.getUserProfile()));
        } else {
            userProfileDto.setProfileImage(null);
        }
        userProfileDto.setUserName(userProfileEntity.getUserEntity().getUserName());
        userProfileDto.setIntroduce(userProfileEntity.getIntroduce());
        userProfileDto.setPostCount(postRepository.countByUserId(userId));
        userProfileDto.setFollowingCount(userProfileDao.getFollowingCounts(user));
        userProfileDto.setFollowerCount(userProfileDao.getFollowerCounts(user));
        return userProfileDto;
    }

    @Override
    public List<IdImageDto> getUserPost(String userId, int page) {
        Pageable pageable = PageRequest.of(page, 2, Sort.by("postTime").descending());
        Page<PostEntity> postPage = postRepository.findByUserIdOrderByPostTimeDesc(userId, pageable);
        List<PostEntity> postList = postPage.getContent();

        List<IdImageDto> idImageDTOList = postList.stream()
                .map(postEntity -> {
                    IdImageDto idImageDto = new IdImageDto();
                    idImageDto.setId(postEntity.getPostId());
                    try {
                        idImageDto.setImage(Base64.getEncoder().encodeToString(postEntity.getMeta().getBytes(1, (int) postEntity.getMeta().length())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return idImageDto;
                })
                .collect(Collectors.toList());
        return idImageDTOList;
    }

    @Override
    public String savePost(String userId, long postId) {
        try {
            UserEntity user = userRepository.findById(userId).orElse(null);
            PostEntity post = postRepository.findById(postId).orElse(null);
            PostSaveEntity postSaveEntity = new PostSaveEntity();
            postSaveEntity.setUserEntity(user);
            postSaveEntity.setPostEntity(post);
            postSaveEntity.setSaveTime(new Timestamp(System.currentTimeMillis()));
            postSaveRepository.save(postSaveEntity);
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @Override
    public List<IdImageDto> getPostSave(String userId, int page) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        Pageable pageable = PageRequest.of(page, 2, Sort.by(Sort.Direction.DESC, "saveTime"));
        Page<PostSaveEntity> postSave = postSaveRepository.findByUserEntityOrderBySaveTimeDesc(user, pageable);
        List<PostSaveEntity> postSaveList = postSave.getContent();
        List<IdImageDto> idImageDTOList = postSaveList.stream()
                .map(postSaveEntity -> {
                    IdImageDto idImageDto = new IdImageDto();
                    idImageDto.setId(postSaveEntity.getPostEntity().getPostId());
                    try {
                        idImageDto.setImage(Base64.getEncoder().encodeToString(postSaveEntity.getPostEntity().getMeta().getBytes(1, (int) postSaveEntity.getPostEntity().getMeta().length())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return idImageDto;
                })
                .collect(Collectors.toList());
        return idImageDTOList;
    }
}
