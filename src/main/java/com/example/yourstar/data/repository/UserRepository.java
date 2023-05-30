package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository  extends JpaRepository<UserEntity, String> {
    UserEntity findByUserEmail(String eMail); // 해당 이메일의 정보 DB에서 가져오기
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity user SET userEmail = ?1, userPw = ?2, phone = ?3, introduce = ?4 WHERE userId = ?5")
    void updateById(String userId, String email, String pw, String phone, String introduce);
}

