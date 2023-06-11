package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments_id")
    private CommentEntity comment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "commentLikeId")
    long commentLikeIid;

    @Column(nullable = false, name = "comments_id", insertable = false, updatable = false)
    long commentsId;

    @Column(nullable = false, name = "user_id", insertable = false, updatable = false)
    String userId;

    @Column(name = "status") // 좋아요 상태를 나타내는 필드
    private Boolean status;

    public void setComment(PostEntity post) {
    }
}
