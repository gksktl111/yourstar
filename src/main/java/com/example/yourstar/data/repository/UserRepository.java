package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, String> {
    UserEntity findByUserEmail(String eMail); // 해당 이메일의 정보 DB에서 가져오기

}
