package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.FollowEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Integer> {

    int countByFromUserId(String userId);
    int countByToUserId(String userId);
    @Query("SELECT f.toUserId FROM FollowEntity f WHERE f.fromUserId = :fromUserId")
    List<UserEntity> findFollowedUsersByFromUserId(@Param("fromUserId") UserEntity fromUserId);
}
