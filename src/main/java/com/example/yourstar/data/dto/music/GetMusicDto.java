package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMusicDto {
    private long musicId;
    private String musicTitle;
    private String soundSource;
    private String lyrics;
    private String singer;
    private String musicImage;
}
