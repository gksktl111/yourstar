package com.example.yourstar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/login/check")
    // post로 보내면 리퀘스트바디로 받아야함
    public boolean loginCheck(@RequestBody Map<String, String> data) {
        String id = data.get("id");
        String pw = data.get("pw");
        boolean loginResult;

        System.out.println(id);
        System.out.println(pw);

        if (id.equals("123") && pw.equals("123")){
            loginResult = true;
        }else{
            loginResult = false;
        }

        return loginResult;
    }
}
