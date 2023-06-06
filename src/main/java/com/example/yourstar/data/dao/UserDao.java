package com.example.yourstar.data.dao;

import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.entity.UserProfileEntity;

public interface UserDao {
    UserEntity saveUser(UserEntity userEntity, UserProfileEntity userProfileEntity); // 회원가입
    UserEntity getUser(String userId); // 아이디에 해당하는 정보를 DB에서 가져와 리턴
    void deleteUser(String userId); // 아이디에 해당하는 정보를  DB에서 삭제
    UserEntity findEmail(String userEmail); // userEmail로 유저 검색
    UserEntity updateUser(UserEntity user, String email, String pw, String phone, String introduce); //엔티티에 해당하는 유저 정보를 DB에서 수정
}
