package com.example.yourstar.controller;

import com.example.yourstar.data.dto.GetAllUserDto;
import com.example.yourstar.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AdminController {
    AdminService adminService;
    public  AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping(value = "getalluser")
    public List<GetAllUserDto> getAllUser(){
        return adminService.getAllUsers();
    }
}
