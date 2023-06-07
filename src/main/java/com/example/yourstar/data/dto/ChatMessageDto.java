package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private String sender;   // 메시지를 보낸 사람
    private String receiver; // 메시지를 받는 사람
    private String content;  // 메시지 내용
    private Timestamp sentAt; // 전송 시간
}
