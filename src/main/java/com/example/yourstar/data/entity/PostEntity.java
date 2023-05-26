package com.example.yourstar.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "post")
public class PostEntity {

    @Id
    @OneToMany
    @Column(name = "post_id", columnDefinition = "bigint")
    long postId;

    @ManyToOne
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

    @ManyToOne
    @Column(name = "category")
    String category;
}
