package com.example.yourstar.data.entity;

import lombok.*;
import org.apache.catalina.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder//생성 및 표현 분리 디자인 패턴
@AllArgsConstructor//모든 필드 값을 파라미터로 받는 생성자 생성
@NoArgsConstructor//파라미터 없는 기본 생성자 생성
@Getter//getter 자동 생성
@Entity//엔티티
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="post_like",
                        columnNames = {"post_id", "user_id"}
                )
        }
)//UK로 결합, 좋아요 중복 불가 및 테이블 이름 선언
public class PostLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long likeId;

    @OnDelete(action = OnDeleteAction.CASCADE)//단방향 연관관계로 삭제 불가능 문제 해결
    @JoinColumn(name="post_id")
    @ManyToOne
    private PostEntity postEntity;//포스트엔티티 연관관계 주입

    @JoinColumn(name="user_id")
    @ManyToOne
    private UserEntity userEntity;//유저엔티티 연관관계 주입
}