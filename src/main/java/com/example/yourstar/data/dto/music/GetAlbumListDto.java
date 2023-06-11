package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAlbumListDto {
    private long albumId;
    private String albumTitle;
    private String albumImage;
    private Timestamp albumReleaseDate;
    private String singer;
}
