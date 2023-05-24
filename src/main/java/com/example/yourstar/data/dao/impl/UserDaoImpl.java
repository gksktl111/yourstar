package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {

    UserRepository userRepository;

    @Autowired
    public  UserDaoImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(String userId) {
        return userRepository.getById(userId);
    }

    @Override
    public UserEntity deleteUser(String userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userRepository.deleteById(userId);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, String email, String pw, String phone, String introduce){
        userEntity.setUserEmail(email);
        userEntity.setUserPw(pw);
        userEntity.setPhone(phone);
        userEntity.setIntroduce(introduce);
        this.userRepository.updateById(userEntity.getUserId(), email, pw, phone, introduce);
        return userEntity;
    }

    @Override
    public UserEntity findEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }


}
