package com.example.yourstar.controller;

import com.example.yourstar.data.dto.*;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserService;
import com.example.yourstar.service.VerificationCodeService;
import com.example.yourstar.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private VerificationCodeService verificationCodeService;
    private JwtUtil jwtUtil;
    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserController(UserService userService,VerificationCodeService verificationCodeService,JwtUtil jwtUtil,UserProfileRepository userProfileRepository){
        this.userService = userService;
        this.verificationCodeService =verificationCodeService;
        this.jwtUtil =jwtUtil;
        this.userProfileRepository = userProfileRepository;
    }

    @PostMapping(value = "/signup") // 회원가입 유저 저장
    public String signUp(@RequestBody UserSignUpDto userSignUpDto){
        if (userService.signUp(userSignUpDto).equals("success")){
            System.out.println("회원가입 성공");
            return "success";
        }else {
            System.out.println("회원가입 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/login")// 로그인
    public  String logIn(@RequestBody UserLogInDto userLogInDto){

            if (userService.logIn((userLogInDto)).equals("success")) {
                return jwtUtil.generateToken(userLogInDto.getId());
            }else{
            System.out.println("로그인 살패");
            return "failed";
        }
    }

    @PostMapping(value = "/sendcode") // 이메일로 인증 코드 전송
    public  String sendCode(@RequestBody String usereMail){
        try{
            return verificationCodeService.sendEmail(usereMail);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("코드 전송 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/checkcode") // 인증 코드 검증
    public  String codeCheck(@RequestBody CheckCodeDto checkCodeDto){
        try{
            return verificationCodeService.verifyEmail(checkCodeDto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("검증 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/findid") // 아이디 찾기
    public  String idFind(@RequestBody String userMail){
        try{
            return userService.FindId(userMail);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("없는 이메일");
            return "failed";
        }
    }

    @PutMapping(value ="/update")// 정보 수정
    public String update(Authentication authentication, @RequestBody UserUpdateDto userUpdateDto){

        if(userService.update(authentication.getName(),userUpdateDto).equals("success")){
            System.out.println("수정되었습니다");
            return "success";
        }else {
            System.out.println("수정 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/delate") // 유저 삭제
    public  String deleteUser(Authentication authentication){
        return userService.deleteUser(authentication.getName());
    }
    @PostMapping(value = "/checkid") // 아이디 유무 체크
    public String checkUserId(String userId){
        if(userService.checkUserId(userId)){
            return "success";
        }
        return  "failed";
    }

    // 프로필 업로드
    @PostMapping(value = "/updateuserprofile",consumes = "multipart/form-data")
    public  String updateUserProfile(Authentication authentication, @ModelAttribute UpdateUserProfileDto updateUserProfileDto){
        log.info("이미지 파일 : {}",updateUserProfileDto.getProfileImage());
        log.info("자기소개 : {}", updateUserProfileDto.getIntroduce());
        return  userService.updateUserProfile(authentication.getName(), updateUserProfileDto);
    }

    /**
     * 사용자 프로필 이미지를 가져오는 메서드
     * @return 이미지 파일이 포함된 ResponseEntity
     */
    @GetMapping(value = "/user/{userId}/image", produces = MediaType.APPLICATION_JSON_VALUE)
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
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        // 3. 이미지 또는 사용자 프로필이 존재하지 않으면 에러 상태를 반환합니다.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
