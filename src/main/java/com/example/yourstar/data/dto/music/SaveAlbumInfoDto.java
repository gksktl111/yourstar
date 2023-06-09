package com.example.yourstar.data.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAlbumInfoDto { // 앨범_info 저장
    private String title;
    private byte[] albumImageByte;
    private String singer;

    public void setAlbumImage(MultipartFile albumImage) {
        try {
            this.albumImageByte = albumImage.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            this.albumImageByte = null;
        }
    }
}
