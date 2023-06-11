package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPlayListDto {
    private long playListId;
    private String playListTitle;
    private String playListImage;
    private Timestamp playListMakeDate;
    private String playListMakeUser;
}
