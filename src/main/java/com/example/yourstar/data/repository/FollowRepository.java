package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Integer> {
    @Query("SELECT COUNT(f1) as followingCount, COUNT(f2) as followersCount FROM FollowEntity f1 INNER JOIN FollowEntity f2 ON f1.fromUserId.userId = f2.toUserId.userId WHERE f1.fromUserId.userId = ?1 AND f2.toUserId.userId = ?1")
    Object[] getFollowCounts(String userId);
}
