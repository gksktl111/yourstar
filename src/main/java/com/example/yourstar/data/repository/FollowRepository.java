package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Integer> {

    int countByFromUserId(String userId);
    int countByToUserId(String userId);
}
