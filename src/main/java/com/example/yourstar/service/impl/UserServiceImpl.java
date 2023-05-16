package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dto.UserLogInDto;
import com.example.yourstar.data.dto.UserSignUpDto;
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
    @Autowired
    public  UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
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
    public String sendMail(String userMail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String ranNum;
        try{
            // 1. 메일 수신자 설정
            simpleMailMessage.setTo(userMail);

            // 2. 메일 제목 설정
            simpleMailMessage.setSubject("urstar 이메일 인증 번호");
            int randomVal = (int)(Math.random() * 1000000);
            ranNum = Integer.toString(randomVal);
            String contents = "urstar 인증 번호는 " + ranNum + " 입니다";

            // 3. 메일 내용 설정
            simpleMailMessage.setText(contents);

            // 4. 메일 전송
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return ranNum;
    }
}