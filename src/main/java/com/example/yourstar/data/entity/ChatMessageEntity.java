package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_messages")
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_msg_id")
    private Long chatMsgId; // 메세지 id

    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender; // 보내는 사람

    @ManyToOne
    @JoinColumn(name = "receiver")
    private UserEntity receiver; // 받는 사람

    @Column(name = "content")
    private String content; // 내용

    @Column(name = "sent_at")
    private Timestamp sentAt; // 보낸 시간
}
