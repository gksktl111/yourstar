package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.AlbumInfoEntity;
import com.example.yourstar.data.entity.MusicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<MusicEntity,Long> {
    List<MusicEntity> findByAlbumInfoEntityOrderByHitsAsc(AlbumInfoEntity albumInfoEntity);

    List<MusicEntity> findAllByOrderByHitsDesc(Pageable pageable); // 조회수 내림차순으로 가져오기
}
