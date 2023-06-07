package com.example.yourstar.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class FeedViewEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)
    PostEntity post;

    @Column(name = "post_id")
    long postId;

    @Column(name = "user_id")
    String userId;
}
