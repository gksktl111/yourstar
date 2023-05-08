package com.example.yourstar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HelloController {
    @GetMapping("/profile")
    public String hello(){
        return Arrays.asList("서버 포트는 8080", "리액트 포트는 3000").toString();
    }
}