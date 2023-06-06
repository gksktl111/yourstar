package com.example.yourstar.controller;

import com.example.yourstar.data.dto.profile.GetUserProfileDto;
import com.example.yourstar.data.dto.profile.PageDto;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserProfileController {
    private UserProfileService userProfileService;
    private UserProfileRepository userProfileRepository;
    @Autowired
    public  UserProfileController(UserProfileService userProfileService,UserProfileRepository userProfileRepository){
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
    }

    // 프로필 업로드
    @PostMapping(value = "/updateuserprofile",consumes = "multipart/form-data")
    public  String updateUserProfile(Authentication authentication, @ModelAttribute UpdateUserProfileDto updateUserProfileDto){
        log.info("이미지 파일 : {}",updateUserProfileDto.getProfileImage());
        log.info("자기소개 : {}", updateUserProfileDto.getIntroduce());
        return  userProfileService.updateUserProfile(authentication.getName(), updateUserProfileDto);
    }

    // 프로필 메인
    @PostMapping(value = "/getUserProfile")
    public GetUserProfileDto getUserProfileImage(Authentication authentication, @RequestBody PageDto pageDto) {
        return userProfileService.getUserProfile("gksktl111",pageDto.getPage());
    }
}
