package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveMusicDto {
    private String musicTitle;
    private byte[] soundSourceByte;
    private String lyrics;
    private Long albumInfoId;
    private int musicIndex;

    public void setAlbumImage(MultipartFile soundSource) {
        try {
            this.soundSourceByte = soundSource.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            this.soundSourceByte = null;
        }
    }
}
