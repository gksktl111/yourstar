package com.example.yourstar.service;

import com.example.yourstar.data.dto.GetAllUserDto;

import java.util.List;

public interface AdminService {
    List<GetAllUserDto> getAllUsers(); // 유저 관리자페이지 모든 유저 불러오기
}
