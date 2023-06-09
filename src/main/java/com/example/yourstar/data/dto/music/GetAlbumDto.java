package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAlbumDto {
    private String albumTitle;
    private String albumImage;
    private Timestamp albumReleaseDate;
    private String singer;
    private List<MusicIdTitleDto> musicInfo;
}
