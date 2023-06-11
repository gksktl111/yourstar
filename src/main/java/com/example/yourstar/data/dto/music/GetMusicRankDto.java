package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMusicRankDto {
    private long musicId;
    private String image;
    private String musicTitle;
    private String singer;
    private long albumId;
}
