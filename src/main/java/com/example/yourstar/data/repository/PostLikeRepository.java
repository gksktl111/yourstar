package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {//포스트라이크엔티티, 정수형
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO post_like(post_id, user_id) VALUES(:postId, :userId)", nativeQuery = true)
    void insertPostLike(@Param("postId") long postId, @Param("userId") String userId); //postId와 userId 파라미터 전달로 포스트 좋아요 메소드 구현

    //@Query("DELETE FROM PostLikeEntity pl WHERE pl.postEntity.id = :postId AND pl.userEntity.id = :userId")
    void deleteByPostEntityPostIdAndUserEntityUserId(Long postId, String userId);//유저 아이디와 포스트 아이디로 조회해서 포스트나 혹은 유저가 조회되지 않을 경우 좋아요 자동으로 삭제됨
}
