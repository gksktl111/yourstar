package com.example.yourstar.data.dao.impl;

import com.example.yourstar.data.dao.AdminDao;
import com.example.yourstar.data.dto.GetAllUserDto;
import com.example.yourstar.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminDaoImpl implements AdminDao {
    private UserRepository userRepository;
    @Autowired
    public AdminDaoImpl(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    @Override
    public List<GetAllUserDto> getAllUsers() {
        return userRepository.getAllUser();
    }
}
