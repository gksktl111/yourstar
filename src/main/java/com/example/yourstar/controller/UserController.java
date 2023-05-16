package com.example.yourstar.controller;

import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
import com.example.yourstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
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

    @PostMapping(value = "/mailcheck")
    public  String mailCheck(@RequestBody String userMail){
        try{
            return userService.sendMail(userMail);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("이메일 전송 실패");
            return "failed";
        }
    }

}
