package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.AdminDao;
import com.example.yourstar.data.dto.GetAllUserDto;
import com.example.yourstar.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    AdminDao adminDao;
    @Autowired
    public AdminServiceImpl(AdminDao adminDao){
        this.adminDao = adminDao;
    }
    @Override
    public List<GetAllUserDto> getAllUsers() {
        return adminDao.getAllUsers();
    }
}
