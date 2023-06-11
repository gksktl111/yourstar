package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.UserProfileDao;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.FollowRepository;
import com.example.yourstar.data.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDaoImpl implements UserProfileDao {
    private FollowRepository followRepository;
    private UserProfileRepository userProfileRepository;
    @Autowired
    public UserProfileDaoImpl(FollowRepository followRepository,UserProfileRepository userProfileRepository){
        this.followRepository = followRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public int getFollowerCounts(UserEntity userId) {
        return followRepository.countByToUserId(userId);
    }

    @Override
    public int getFollowingCounts(UserEntity userId) {
        return followRepository.countByFromUserId(userId);
    }

    @Override
    public void updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto) {
        UserProfileEntity userProfileEntity= userProfileRepository.getById(userId);
        if(updateUserProfileDto.getProfileImage() != null){
            userProfileEntity.setUserProfile(updateUserProfileDto.getProfileImage());
        }
        if(updateUserProfileDto.getIntroduce() != null){
            userProfileEntity.setIntroduce(updateUserProfileDto.getIntroduce());
        }
        userProfileRepository.save(userProfileEntity);
    }
}
