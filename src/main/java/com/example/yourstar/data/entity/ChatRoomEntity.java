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
@Table(name = "chat_room")
public class ChatRoomEntity {

    @Id
    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatRoomId;

    @ManyToOne
    @JoinColumn(name = "chat_user_1")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "chat_user_2")
    private UserEntity user2;

    @Column(name = "make_room_time")
    private Timestamp makeRoomTime;
}
