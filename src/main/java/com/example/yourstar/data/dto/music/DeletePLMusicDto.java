package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletePLMusicDto {
    private long playListId;
    private long musicId;
}
