package com.example.yourstar.service;

import com.example.yourstar.data.dto.profile.GetUserProfileDto;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;

public interface UserProfileService {
    String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto); // 유저 프로필 업데이트
    GetUserProfileDto getUserProfile(String userId,int page); // 유저 프로필 가져오기
}
