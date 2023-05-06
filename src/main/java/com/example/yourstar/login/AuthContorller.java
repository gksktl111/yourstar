package com.example.yourstar.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthContorller {
    @Autowired
    AuthServiec authServiec;

    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody signUp requestBody){
        ResponseDto<?> result = authServiec.signUp(requestBody);
        return result;
    }

    @
}
