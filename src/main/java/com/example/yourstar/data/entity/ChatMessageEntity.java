package com.example.yourstar.data.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_messages")
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name(value = "chat_msg_id")
    private Long chatMsgId; // 메세지 id

    @Name(value = "sender")
    private String sender; // 보내는 사람

    @Name(value = "receiver")
    private String receiver; // 받는 사람

    @Name(value = "content")
    private String content; // 내용

    @Name(value = "sent_at")
    private LocalDateTime sentAt; // 보낸 시간
}
