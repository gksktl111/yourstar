package com.example.yourstar.data.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follow")
public class FollowEntity {
    @Id
    @Name(value = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;

    @Column(name = "from_user_id")
    private String fromUserId;

    @Column(name = "to_user_id")
    private String toUserId;
}
