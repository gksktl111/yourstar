package com.example.yourstar.data.entity;

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
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private UserEntity fromUserId;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private UserEntity toUserId;
}
