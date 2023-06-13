package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePlayListInfoDto {
    private String playListTitle;
    private byte[] playListImageByte;

    public void setAlbumImage(MultipartFile playLIstImage) {
        try {
            this.playListImageByte = playLIstImage.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            this.playListImageByte = null;
        }
    }
}
