package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dao.VerificationCodeDao;
import com.example.yourstar.data.dto.CheckCodeDto;
import com.example.yourstar.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    UserDao userDao;
    VerificationCodeDao verificationCodeDao;
    @Autowired
    public  VerificationCodeServiceImpl(UserDao userDao,VerificationCodeDao verificationCodeDao){
        this.userDao = userDao;
        this.verificationCodeDao =verificationCodeDao;
    }
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public String sendEmail(String userEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        try{
            System.out.println("받은 이메일 : " + userEmail);
            System.out.println("검색한 이메일 : " + userDao.findEmail(userEmail).getUserEmail());
            if (userDao.findEmail(userEmail).getUserEmail().trim().equals(userEmail.trim())){
                System.out.println("이메일이 같음");
                // 1. 메일 수신자 설정
                simpleMailMessage.setTo(userEmail);
                System.out.println("메일 수신자 설정");
                // 2. 메일 제목 설정
                simpleMailMessage.setSubject("urstar 이메일 인증 번호");
                System.out.println("메일 제목 설정");

                int randomNum = (int)(Math.random() * 1000000); // 랜덤 숫자 6개 생성
                String contents = "urstar 인증 번호는 " + randomNum + " 입니다";
                System.out.println("메일 내용 생성");

                verificationCodeDao.createVerification(userEmail,randomNum);
                System.out.println("session에 저장");

                // 3. 메일 내용 설정
                simpleMailMessage.setText(contents);
                System.out.println("메일 내용 설정");

                // 4. 메일 전송
                javaMailSender.send(simpleMailMessage);
                return "success";
            }else {
                System.out.println("이메일이 다름");
                return "failed";
            }

        }catch (Exception e) {
            System.out.println("에러");
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String verifyEmail(CheckCodeDto checkCodeDto) {
        if (verificationCodeDao.getVerificationCode(checkCodeDto.getUserEmail()) != checkCodeDto.getVerificationCode()) {
            System.out.println("인증번호가 일치하지 않습니다");
            return "failed";
        }
        verificationCodeDao.removeVerificationCode(checkCodeDto.getUserEmail());
        return "success";
    }
}
