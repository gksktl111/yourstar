package com.example.yourstar.data.dao;

import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;

public interface UserProfileDao {
    int getFollowerCounts(String userId); // 팔로우 count
    int getFollowingCounts(String userId); // 팔로윙 count
    void updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto); // 프로필 업데이트(프로필 사진, 자기소개)
}
