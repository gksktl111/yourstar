package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dao.VerificationCodeDao;
import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
import com.example.yourstar.data.dto.UserUpdateDto;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;
    VerificationCodeDao verificationCodeDao;
    @Autowired
    public  UserServiceImpl(UserDao userDao,VerificationCodeDao verificationCodeDao){
        this.userDao = userDao;
        this.verificationCodeDao =verificationCodeDao;
    }

    @Autowired
    private JavaMailSender javaMailSender;





    @Override
    public String signUp(UserSignUpDto userSignUpDto) {
        try{
            String userId = userSignUpDto.getId();
            String userName= userSignUpDto.getName();
            String userEmail = userSignUpDto.getEmail();
            String userPw = userSignUpDto.getPw();
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
            if (user.getUserPw().equals(userLogInDto.getPw())){
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
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try{
             if (userDao.findEmail(userEmail).getUserEmail().equals(userEmail)){
                 UserEntity user = userDao.findEmail(userEmail); // 이메일로 userid 가져오기

                 // 1. 메일 수신자 설정
                 simpleMailMessage.setTo(userEmail);

                 // 2. 메일 제목 설정
                 simpleMailMessage.setSubject("urstar 아이디 찾기 결과");

                 // 3. 메일 내용 설정
                 String contents = "urstar 아이디는 " + user.getUserId() + " 입니다";
                 simpleMailMessage.setText(contents);

                 // 4. 메일 전송
                 javaMailSender.send(simpleMailMessage);
                 return  "success";
            }else {
                 return "failed";
             }

        }catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String update(UserUpdateDto userUpdateDto) {
        try{
            UserEntity user = userDao.getUser(userUpdateDto.getId());
            if(user != null){
                this.userDao.updateUser(user, userUpdateDto.getEmail(), userUpdateDto.getPw(), userUpdateDto.getPhone(), userUpdateDto.getIntroduce());
            }
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }
}