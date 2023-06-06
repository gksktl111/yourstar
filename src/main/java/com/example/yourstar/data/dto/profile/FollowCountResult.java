package com.example.yourstar.data.dto.profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowCountResult {
   private int followingCount;
   private int followersCount;
    public FollowCountResult(int followCount, int followerCount) {
        this.followingCount = followCount;
        this.followersCount = followerCount;
    }
}
