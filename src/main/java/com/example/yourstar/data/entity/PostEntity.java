package com.example.yourstar.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "post")
public class PostEntity {
    // userEntity를 참조함
    @ManyToOne UserEntity user;

    @Id
    @Column(name = "post_id", columnDefinition = "bigint")
    long postId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "contents")
    String contents;

    @Lob
    @Column(name = "meta")
    Blob meta;

    @Column(name = "post_time")
    Timestamp postTime;

    @Column(name = "like_count", columnDefinition = "bigint")
    long likeCount;

    @Column(name = "view_count", columnDefinition = "bigint")
    long viewCount;

    @Column(name = "share_count", columnDefinition = "bigint")
    long shareCount;

    @Column(name = "category")
    String category;
}
