package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dao.VerificationCodeDao;
import com.example.yourstar.data.dto.UpdateUserProfileDto;
import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
import com.example.yourstar.data.dto.UserUpdateDto;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    PasswordEncoder passwordEncoder;
    UserDao userDao;
    VerificationCodeDao verificationCodeDao;
    @Autowired
    public  UserServiceImpl(UserDao userDao,VerificationCodeDao verificationCodeDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.verificationCodeDao =verificationCodeDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String signUp(UserSignUpDto userSignUpDto) {
        try{
            String userId = userSignUpDto.getId();
            String userName= userSignUpDto.getName();
            String userEmail = userSignUpDto.getEmail();
            String userPw = passwordEncoder.encode(userSignUpDto.getPw());
            String userGender = userSignUpDto.getGender();
            int userAge = userSignUpDto.getAge();
            int postCount = userSignUpDto.getPostCount();
            String phone = userSignUpDto.getPhone();
            String introduce = userSignUpDto.getIntroduce();
            Timestamp joinDate = new Timestamp(System.currentTimeMillis());
            UserEntity userEntity = new UserEntity(userId,userName,userEmail,userPw,userGender,userAge,postCount,phone,introduce,joinDate);
            userDao.saveUser(userEntity);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String logIn(UserLogInDto userLogInDto) {
        try{
            UserEntity user = userDao.getUser(userLogInDto.getId());
            if (passwordEncoder.matches(userLogInDto.getPw(),user.getUserPw())){
                return "success";
            }else {
                return "failed";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String FindId(String userEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); // 이메일 api
        log.info("유저 이메일 findId impl: {}",userEmail);
        UserEntity user = userDao.findEmail(userEmail); // 이메일 검색해서 user엔티티 가져옴
        if (user != null) { // 이메일로 유저가 검색이 되서 null이 아니면
            log.info("userDao.findEmail(userEmail) null 아님");
            // 1. 메일 수신자 설정
            simpleMailMessage.setTo(userEmail);

            // 2. 메일 제목 설정
            simpleMailMessage.setSubject("urstar 아이디 찾기 결과");

            // 3. 메일 내용 설정
            String contents = "urstar 아이디는 " + user.getUserId() + " 입니다";
            simpleMailMessage.setText(contents);

            // 4. 메일 전송
            javaMailSender.send(simpleMailMessage);
            log.info("이메일 전송 성공");
            return  "success";
        } else { // user가 null이면
            log.info("이메일 유저 없음");
            return "failed";
        }
    }


    @Override
    public String update(String userId,UserUpdateDto userUpdateDto) {
        try{
            UserEntity user = userDao.getUser(userId);
            if(user != null){
                this.userDao.updateUser(user, userUpdateDto.getEmail(), passwordEncoder.encode(userUpdateDto.getPw()), userUpdateDto.getPhone(), userUpdateDto.getIntroduce());
            }
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    @Override
    public String deleteUser(String userId) {
        try{
            userDao.deleteUser(userId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public boolean checkUserId(String userId) {
        return userDao.checkUserId(userId);
    }

    @Override
    public String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto) {
        try{
            userDao.updateUserProfile(userId, updateUserProfileDto);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }

    }
}