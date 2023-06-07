package com.example.yourstar.data.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileDto {
    private String introduce;
    private byte[] profileImage;

    public void setProfileImage(MultipartFile profileImage) {
        try {
            this.profileImage = profileImage.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            this.profileImage = null;
        }
    }

}
