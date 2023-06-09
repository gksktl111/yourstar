package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity,Long> {
}
