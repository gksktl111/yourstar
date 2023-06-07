package com.example.yourstar.service;

import com.example.yourstar.data.dto.profile.GetUserProfileDto;
import com.example.yourstar.data.dto.profile.IdImageDto;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;

import java.util.List;

public interface UserProfileService {
    String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto); // 유저 프로필 업데이트
    GetUserProfileDto getUserProfile(String userId); // 유저 프로필 가져오기
    List<IdImageDto> getUserPost(String userId,int page); // 유저가 올린 post 가져오기
    String savePost(String userId,long postId); // post 저장
    List<IdImageDto> getPostSave(String userId, int page); // 유저 프로필 가져오기
}
