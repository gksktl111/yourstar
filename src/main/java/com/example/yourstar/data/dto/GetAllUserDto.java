package com.example.yourstar.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class GetAllUserDto {
    private String userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private int userAge;
    private String phone;
    private Date joinDate;

    public GetAllUserDto(String userId, String userName, String userEmail,
                         String userGender, int userAge, String phone, Date joinDate) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userAge = userAge;
        this.phone = phone;
        this.joinDate = joinDate;
    }
}
