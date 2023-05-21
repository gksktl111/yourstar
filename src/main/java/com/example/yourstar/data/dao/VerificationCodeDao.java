package com.example.yourstar.data.dao;

public interface VerificationCodeDao {
    void createVerification(String userEmail, int verificationCode); // 이메일과 인증번호 저장
    int getVerificationCode(String userEmail); // 해당 이메일의 인증번호 리턴
    void  removeVerificationCode(String userEmail); // 해당 이메일의 인증코드 삭제
}
