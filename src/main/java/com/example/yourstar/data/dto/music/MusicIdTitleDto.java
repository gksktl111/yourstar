package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicIdTitleDto {
    private long musicId;
    private String musicTitle;
    private String singer;
    private String musicImage;
}
