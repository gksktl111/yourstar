package com.example.yourstar.data.dao;

import com.example.yourstar.data.dto.GetAllUserDto;

import java.util.List;

public interface AdminDao {
    List<GetAllUserDto> getAllUsers();
}
