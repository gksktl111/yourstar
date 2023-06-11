package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_like")
public class PostLikeDetailEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(nullable = false, name = "post_id", insertable = false, updatable = false)
    long postId;

    @Column(nullable = false, name = "user_id", insertable = false, updatable = false)
    String userId;

    @Column(name = "status") // 좋아요 상태를 나타내는 필드
    private Boolean status;

    public PostLikeDetailEntity(PostEntity post, UserEntity user) {
        this.post = post;
        this.postId = post.getPostId();
        this.user = user;
        this.userId = user.getUserId();
    }
}
