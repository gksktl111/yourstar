package com.example.yourstar.data.dao;

import com.example.yourstar.data.dto.UpdateUserProfileDto;
import com.example.yourstar.data.entity.UserEntity;

public interface UserDao {

    UserEntity saveUser(UserEntity userEntity); // 유저 정보 DB애 저장
    UserEntity getUser(String userId); // 아이디에 해당하는 정보를 DB에서 가져와 리턴
    void deleteUser(String userId); // 아이디에 해당하는 정보를  DB에서 삭제
    UserEntity findEmail(String userEmail);
    UserEntity updateUser(UserEntity user, String email, String pw, String phone, String introduce); // 이메일로 user 정보 검색
    void updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto);
}
