package com.example.yourstar.data.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProfileDto {
    private String profileImage;
    private String userName;
    private long postCount;
    private int followingCount;
    private int followerCount;
    private String introduce;
}
