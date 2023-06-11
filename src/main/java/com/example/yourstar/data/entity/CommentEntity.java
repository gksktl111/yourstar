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