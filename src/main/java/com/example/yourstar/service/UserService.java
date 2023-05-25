package com.example.yourstar.service;

import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
import com.example.yourstar.data.dto.UserUpdateDto;

public interface UserService {
    String signUp(UserSignUpDto userSignUpDto); // 회원 가입 기능
    String logIn(UserLogInDto userLogInDto); // 로그인 기능
    String FindId(String userEmail); // 아이디 찾아서 이메일로 전송
    String update(String userId,UserUpdateDto userUpdateDto);

    String deleteUser(String userId); // 유저 삭제
}
