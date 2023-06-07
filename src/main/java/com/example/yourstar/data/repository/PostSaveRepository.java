package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.PostSaveEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostSaveRepository extends JpaRepository<PostSaveEntity,Long> {
    Page<PostSaveEntity> findByUserEntityOrderBySaveTimeDesc(UserEntity userEntity, Pageable pageable);

}
