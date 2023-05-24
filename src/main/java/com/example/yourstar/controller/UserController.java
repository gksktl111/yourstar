package com.example.yourstar.controller;

import com.example.yourstar.data.dto.CheckCodeDto;
import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
import com.example.yourstar.data.dto.UserUpdateDto;
import com.example.yourstar.service.UserService;
import com.example.yourstar.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private VerificationCodeService verificationCodeService;

    @Autowired
    public UserController(UserService userService,VerificationCodeService verificationCodeService){
        this.userService = userService;
        this.verificationCodeService =verificationCodeService;
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
        if (userService.logIn(userLogInDto).equals("success")){
            System.out.println("로그인 성공");
            return "success";
        }else {
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
    public String update(@RequestBody UserUpdateDto userUpdateDto){

        if(userService.update(userUpdateDto).equals("success")){
            System.out.println("수정되었습니다");
            return "success";
        }else {
            System.out.println("수정 실패");
            return "failed";
        }
    }
}
