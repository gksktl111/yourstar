package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PlayLIstInfoEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayListInfoRepository extends JpaRepository<PlayLIstInfoEntity,Long> {
    // 내 플레이 리스트 가져오기
    @Query("SELECT p FROM PlayLIstInfoEntity p WHERE p.playListMakeUser = :user ORDER BY p.playListMakeDate DESC")
    List<PlayLIstInfoEntity> findByPlayListMakeUserOrderByPlayListMakeDateDesc(@Param("user") UserEntity user);
}
