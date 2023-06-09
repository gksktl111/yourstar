package com.example.yourstar.data.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdNameImageDto {
    private String userId;
    private String name;
    private String image;
}
