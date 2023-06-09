package com.example.yourstar.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "post")
public class PostEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @Id
    @Column(name = "post_id", columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //트랜잭션이 실행될때마다 자동적으로 테이블의 id값이 1씩 증가
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
