package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity,String> {
}
