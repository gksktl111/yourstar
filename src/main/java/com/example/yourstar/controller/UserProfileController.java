package com.example.yourstar.controller;

import com.example.yourstar.data.dto.UpdateUserProfileDto;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    /**
     * 사용자 프로필 이미지를 가져오는 메서드
     * @return 이미지 파일이 포함된 ResponseEntity
     */
    @PostMapping(value = "/getUserProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUserProfileImage(Authentication authentication) {
        // 1. 사용자 프로필 정보를 가져옵니다.
        Optional<UserProfileEntity> userProfileOptional = userProfileRepository.findById(authentication.getName());

        // 2. 사용자 프로필이 존재하면
        if (userProfileOptional.isPresent()) {
            UserProfileEntity userProfileEntity = userProfileOptional.get();
            byte[] imageBytes = userProfileEntity.getUserProfile();
            String introduce = userProfileEntity.getIntroduce();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HashMap<String, Object> response = new HashMap<>();
            response.put("profileImage", imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null);
            response.put("introduce", introduce);
            response.put("userName",userProfileEntity.getUserEntity().getUserName());
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        // 3. 이미지 또는 사용자 프로필이 존재하지 않으면 에러 상태를 반환합니다.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
