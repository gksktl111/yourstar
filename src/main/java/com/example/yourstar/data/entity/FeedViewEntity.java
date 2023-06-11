package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedViewEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)
    PostEntity post;

    @ManyToOne
    @JoinColumn(name = "follow_id", referencedColumnName = "follow_id", insertable = false, updatable = false)
    FollowEntity follow;

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "post_id")
    long postId;

    @Column(name = "to_user_id")
    String toUserId;
}