package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPLMusicDto {
    private String playListTitle;
    private String playListImage;
    private Timestamp playListMakeDate;
    private String playListMakeUser;
    private List<MusicIdTitleDto> musicInfo;
}
