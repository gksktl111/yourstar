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
    public UserEntity saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
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
        userEntity.setIntroduce(introduce);
        this.userRepository.updateById(userEntity.getUserId(), email, pw, phone, introduce);
        return userEntity;
    }

    @Override
    public boolean checkUserId(String userid) {
        return userRepository.existsById(userid);
    }

    @Override
    public void updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto) {
        UserProfileEntity userProfileEntity;
        if(userProfileRepository.findById(userId).orElse(null)==null){
            userProfileEntity = new UserProfileEntity(userId,updateUserProfileDto.getIntroduce(), updateUserProfileDto.getProfileImage());
            userProfileRepository.save(userProfileEntity);
        }
        userProfileEntity= userProfileRepository.findById(userId).orElse(null);
        if(updateUserProfileDto.getProfileImage() != null){
            userProfileEntity.setUserProfile(updateUserProfileDto.getProfileImage());
        }
        if(updateUserProfileDto.getIntroduce() != null){
            userProfileEntity.setIntroduce(updateUserProfileDto.getIntroduce());
        }
        userProfileRepository.save(userProfileEntity);
    }

    @Override
    public UserEntity findEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }


}
