package com.example.yourstar.service;

import com.example.yourstar.data.dto.user.CheckCodeDto;

public interface VerificationCodeService {
    String sendEmail(String userEmail); // 이메일 전송
    String verifyEmail(CheckCodeDto checkCodeDto); // 인증번호가 동일한지 확인
}
