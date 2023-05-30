package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.VerificationCodeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Slf4j
@Service
public class VerificationCodeDaoImpl implements VerificationCodeDao {
    HttpSession session;
    @Autowired
    public  VerificationCodeDaoImpl(HttpSession session){
        this.session = session;
    }
    private final String PREFIX = "Email:";  // Redis에 저장되는 Key값이 중복되지 않도록 상수 선언


    @Override
    public void createVerification(String userEmail, int verificationCode) { //사용자가 입력한 이메일과 인증번호를 저장하고 TTL을 180초로 설정
        session.setAttribute(PREFIX + userEmail, verificationCode);
        log.info("세션에 인증코드 저장 / {} : {}",userEmail,verificationCode);
    }

    @Override
    public int getVerificationCode(String userEmail) { // Session에서 이메일(KEY)에 해당하는 인증번호를 리턴
        log.info("{} 인증번호 리턴",userEmail);
        return  (int)session.getAttribute(PREFIX+ userEmail);
    }

    @Override
    public void removeVerificationCode(String userEmail) { // 인증이 완료되었을 경우 메모리 관리를 위해 Session에 저장된 인증번호 삭제
        session.removeAttribute(PREFIX + userEmail);
    }
}
