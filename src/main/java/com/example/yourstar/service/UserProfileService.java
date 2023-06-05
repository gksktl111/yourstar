package com.example.yourstar.service;

import com.example.yourstar.data.dto.GetUserProfileDto;
import com.example.yourstar.data.dto.UpdateUserProfileDto;

public interface UserProfileService {
    String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto);
    GetUserProfileDto getUserProfile(String userId);
}
