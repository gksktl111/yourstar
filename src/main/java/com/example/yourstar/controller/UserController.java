package com.example.yourstar.controller;

import com.example.yourstar.data.dto.user.*;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.data.repository.UserRepository;
import com.example.yourstar.service.UserProfileService;
import com.example.yourstar.service.UserService;
import com.example.yourstar.service.VerificationCodeService;
import com.example.yourstar.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private VerificationCodeService verificationCodeService;
    private JwtUtil jwtUtil;
    private UserProfileRepository userProfileRepository;
    private UserRepository userRepository;
    private UserProfileService userProfileService;

    @Autowired
    public UserController(UserService userService,VerificationCodeService verificationCodeService,JwtUtil jwtUtil,UserProfileRepository userProfileRepository,UserRepository userRepository,UserProfileService userProfileService){
        this.userService = userService;
        this.verificationCodeService =verificationCodeService;
        this.jwtUtil =jwtUtil;
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
    }

    @PostMapping(value = "/signup") // 회원가입 유저 저장
    public String signUp(@RequestBody UserSignUpDto userSignUpDto){
        return userService.signUp(userSignUpDto);
    }

    @PostMapping(value = "/login")// 로그인
    public  String logIn(@RequestBody UserLogInDto userLogInDto){
            if (userService.logIn((userLogInDto)).equals("success")) { // 로그인을 성공하면
                return jwtUtil.generateToken(userLogInDto.getId()); // 토큰 생성 후 리턴
            }else{
            log.info("로그인 : 실패");
            return "failed";
        }
    }

    @PostMapping(value = "/sendcode") // 이메일로 인증 코드 전송
    public  String sendCode(@RequestBody UserEmailDto userEmailDto){
        return verificationCodeService.sendEmail(userEmailDto.getUserEmail());
    }

    @PostMapping(value = "/checkcode") // 인증 코드 검증
    public  String codeCheck(@RequestBody CheckCodeDto checkCodeDto){
            return verificationCodeService.verifyEmail(checkCodeDto);
    }

    @PostMapping(value = "/findid") // 아이디 찾기
    public  String idFind(@RequestBody UserEmailDto userEmailDto){
        log.info("psot 이메일 : {}",userEmailDto.getUserEmail());
        return userService.FindId(userEmailDto.getUserEmail());
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

    // 아이디 유무 체크
    @PostMapping(value = "/checkId")
    public boolean checkUserId(@RequestBody UserIdDto userIdDto){
        return userRepository.existsById(userIdDto.getUserId());
    }

    // 이메일 유무 체크
    @PostMapping(value = "/checkEmail")
    public  boolean checkEmail(@RequestBody UserEmailDto userEmailDto){
        return userRepository.existsByUserEmail(userEmailDto.getUserEmail());
    }

    // 핸드폰 유무 체크
    @PostMapping(value = "/checkPhone")
    public  boolean checkPhone(@RequestBody PhoneDto phoneDto){
        return userRepository.existsByPhone(phoneDto.getPhone());
    }

    @PostMapping(value = "/myid")
    public String returnMyId(Authentication authentication){
        if (authentication == null){
            return null;
        }else {
            return authentication.getName();
        }
    }


}
