package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserProfileDao;
import com.example.yourstar.data.dto.GetUserProfileDto;
import com.example.yourstar.data.dto.UpdateUserProfileDto;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileDao userProfileDao;
    private UserProfileRepository userProfileRepository;
    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao,UserProfileRepository userProfileRepository){
        this.userProfileDao = userProfileDao;
        this.userProfileRepository = userProfileRepository;
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
        GetUserProfileDto userProfileDto = new GetUserProfileDto();
        Object[] counts = userProfileDao.getFollowCounts(userId);
        UserProfileEntity userProfileEntity = userProfileRepository.getById(userId);

        userProfileDto.setProfileImage(Base64.getEncoder().encodeToString(userProfileEntity.getUserProfile()));
        userProfileDto.setUserName(userProfileEntity.getUserEntity().getUserName());
        userProfileDto.setIntroduce(userProfileEntity.getIntroduce());
        // post 개시물 수
        userProfileDto.setFollowCount((int) counts[0]);
        userProfileDto.setFollowerCount((int) counts[1]);
        // 포스트 사진,아이디
        return new GetUserProfileDto(); // 수정 해야함
    }
}
