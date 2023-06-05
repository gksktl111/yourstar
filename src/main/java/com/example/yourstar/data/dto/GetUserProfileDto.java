package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProfileDto {
    private String profileImage;
    private String userName;
    private int followCount;
    private int followerCount;
    private String introduce;
    private List<IdImageDto> post;
}
