package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {

    private  String id;
    private  String name;
    private  String email;
    private  String pw;
    private  String gender;
    private  int age;
    private  String phone;
}
