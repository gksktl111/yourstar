package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}