package com.example.yourstar.service;

import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;

public interface UserService {

    String signUp(UserSignUpDto userSignUpDto);
    String logIn(UserLogInDto userLogInDto);

    String sendMail(String userMail);
}
