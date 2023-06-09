package com.example.yourstar.data.repository;

import com.example.yourstar.data.dto.GetAllUserDto;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserEmail(String eMail); // 해당 이메일의 정보 DB에서 가져오기

    UserEntity findByUserId(String UserId);
    boolean existsByUserEmail(String userEmail); // 이메일 유무 확인
    boolean existsByPhone(String phone); // 전 전화번호 유부 확인
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity user SET userEmail = ?1, userPw = ?2, phone = ?3, introduce = ?4 WHERE userId = ?5")
    void updateById(String userId, String email, String pw, String phone, String introduce);
    @Query("SELECT new com.example.yourstar.data.dto.GetAllUserDto(u.userId, u.userName, u.userEmail, u.userGender, u.userAge, u.phone, u.joinDate) FROM UserEntity u")
    List<GetAllUserDto> getAllUser();

}

