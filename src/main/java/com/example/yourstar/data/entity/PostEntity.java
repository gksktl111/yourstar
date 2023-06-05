package com.example.yourstar.data.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "post")
public class PostEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    UserEntity user;

    @Id
    @Column(name = "post_id", columnDefinition = "bigint")
    long postId;

    @Column(name = "contents")
    String contents;

    @Column(name = "user_id")
    String userId;

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

