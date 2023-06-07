package com.example.yourstar.controller;

import com.example.yourstar.data.dto.profile.*;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // 프로필 정보 가져오기
    @PostMapping(value = "/getUserprofile")
    public GetUserProfileDto getUserProfileImage(Authentication authentication) {
        return userProfileService.getUserProfile("gksktl111");
    }

    // 유저가 올린 post 가져오기
    @PostMapping(value = "/getUserPost")
    public List<IdImageDto> getUserPost(Authentication authentication, @RequestBody PageDto pageDto) {
        return userProfileService.getUserPost("gksktl111",pageDto.getPage());
    }


    // post 저장하기
    @PostMapping(value = "/postsave")
    public String postSave(Authentication authentication, @RequestBody PostDto postDto){
        return userProfileService.savePost(authentication.getName(),postDto.getPostId());
    }

    // 저장한 post 가져오기
    @PostMapping(value = "/getpostsave")
    public List<IdImageDto> getPostSave(Authentication authentication, @RequestBody PageDto pageDto) {
        return userProfileService.getPostSave(authentication.getName(),pageDto.getPage());
    }
}
