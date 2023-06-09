package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.AlbumEntity;
import com.example.yourstar.data.entity.AlbumInfoEntity;
import com.example.yourstar.data.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    List<AlbumEntity> findByAlbumInfoEntity(AlbumInfoEntity albumInfoEntity);// 앨범 음악 가져오기

    @Query("SELECT DISTINCT a.albumInfoEntity FROM AlbumEntity a WHERE a.musicEntity = ?1")
    AlbumInfoEntity findDistinctAlbumInfoEntityByMusicEntity(MusicEntity musicEntity);
}
