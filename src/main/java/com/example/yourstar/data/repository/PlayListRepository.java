package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayListEntity,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PlayListEntity p WHERE p.musicEntity = :musicEntity AND p.playLIstInfoEntity = :playLIstInfoEntity")
    void deleteByMusicEntityAndPlayListInfoEntity(@Param("musicEntity") MusicEntity musicEntity, @Param("playLIstInfoEntity") PlayLIstInfoEntity playLIstInfoEntity);
    List<PlayListEntity> findByPlayLIstInfoEntityOrderByPlayListIndexAsc(PlayLIstInfoEntity playLIstInfoEntity);// 플레이 리스트 음악 가져오기
}
