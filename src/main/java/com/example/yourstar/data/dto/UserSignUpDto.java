package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {

    private  String userId;
    private  String userName;
    private  String userEmail;
    private  String userPw;
    private  String userGender;
    private  int userAge;
    private  int postCount;
    private  String phone;
    private  String introduce;
}
