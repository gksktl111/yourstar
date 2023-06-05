package com.example.yourstar.data.dao;

import com.example.yourstar.data.dto.UpdateUserProfileDto;

public interface UserProfileDao {
    Object[] getFollowCounts(String userId);
    void updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto);
}
