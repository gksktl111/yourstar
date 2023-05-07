package com.example.yourstar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello(){
        return "안녕하세요. 현재시간은 "+new Date() +"입니다. \n";
    }
}