package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dto.UpdateUserProfileDto;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {

    UserRepository userRepository;
    UserProfileRepository userProfileRepository;

    @Autowired
    public  UserDaoImpl(UserRepository userRepository,UserProfileRepository userProfileRepository){
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }
    @Override
    public UserEntity saveUser(UserEntity userEntity,UserProfileEntity userProfileEntity) {
        userRepository.save(userEntity);
        userProfileRepository.save(userProfileEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(String userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, String email, String pw, String phone, String introduce){
        userEntity.setUserEmail(email);
        userEntity.setUserPw(pw);
        userEntity.setPhone(phone);
        this.userRepository.updateById(userEntity.getUserId(), email, pw, phone, introduce);
        return userEntity;
    }



    @Override
    public UserEntity findEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }


}
