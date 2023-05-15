package com.example.yourstar.data.dao;

import com.example.yourstar.data.entity.UserEntity;

public interface UserDao {
    UserEntity saveUser(UserEntity userEntity);
    UserEntity getUser(String userId);
    UserEntity deleteUser(String userPw);

}
