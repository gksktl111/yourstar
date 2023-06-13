package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class CommentEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)
    PostEntity post;

    @Id
    @Column(name = "comments_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //트랜잭션이 실행될때마다 자동적으로 테이블의 id값이 1씩 증가
    long commentsId;

    @Column(name = "post_id")
    long postId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "text")
    String text;

    @Column(name = "comments_time")
    Timestamp commentsTime;

    @Column(name = "comments_like_count", columnDefinition = "bigint")
    long commentsLikeCount;

}