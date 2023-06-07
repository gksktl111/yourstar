package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserDao;
import com.example.yourstar.data.dao.VerificationCodeDao;
import com.example.yourstar.data.dto.user.CheckCodeDto;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.service.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Slf4j
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
            UserEntity userEntity = userDao.findEmail(userEmail);
            if (userEntity != null){
                String getUserEmail = userEntity.getUserEmail();
                log.info("클라이언트에서 받은 이메일 : {}",userEmail);
                log.info("DB에서 검색한 이메일 : {}", getUserEmail);
                if (getUserEmail.trim().equals(userEmail.trim())){
                    log.info("이메일이 같음");
                    // 1. 메일 수신자 설정
                    simpleMailMessage.setTo(userEmail);

                    // 2. 메일 제목 설정
                    simpleMailMessage.setSubject("urstar 이메일 인증 번호");

                    int randomNum = (int)(Math.random() * 1000000); // 랜덤 숫자 6개 생성
                    String contents = "urstar 인증 번호는 " + randomNum + " 입니다"; // 메일 내용 생성

                    verificationCodeDao.createVerification(userEmail,randomNum); // 랜덤 숫지 세션 저장
                    log.info("session에 랜덤 숫자 저장");

                    // 3. 메일 내용 설정
                    simpleMailMessage.setText(contents);

                    // 4. 메일 전송
                    javaMailSender.send(simpleMailMessage);
                    log.info("이메일 전송 성공");
                    return "success";
                }else {
                    log.info("이메일이 다름");
                    return "failed";
                }
            }else{
                log.info("sendEmail : 검색된 회원이 없음");
                return "failed";
            }
        }catch (Exception e) {
            log.error("이메일 전송 실패");
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String verifyEmail(CheckCodeDto checkCodeDto) {
        try {
            // 세션에 저장된 인증번호를 가져와 클라이언가 보낸 인증번호를 비교
            if (verificationCodeDao.getVerificationCode(checkCodeDto.getUserEmail()) != checkCodeDto.getVerificationCode()) {
                log.info("{}의 인증번호가 일치하지 않습니다. 인증실",checkCodeDto.getUserEmail());
                return "failed";
            }
            verificationCodeDao.removeVerificationCode(checkCodeDto.getUserEmail()); // 인증번호가 같으면 세션에서 인증번호 삭제
            log.info("{} 인증성공, 인증번호 삭제",checkCodeDto.getUserEmail());
            return "success";
        }catch (Exception e){
            log.error("인증번호 검증 에러");
            e.printStackTrace();
            return "failed";
        }

    }
}
