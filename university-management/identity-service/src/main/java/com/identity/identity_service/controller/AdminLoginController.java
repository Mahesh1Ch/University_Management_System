package com.identity.identity_service.controller;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @Autowired
    public AdminLoginController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/otpgeneration")
    public ResponseEntity<String>  generateLoginOtp(@RequestParam String phone) {
        String responseMessage = adminLoginService.generateLoginOtp(phone);

        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/otpvalidation")
    public ResponseEntity<Admin> validateLoginOtp(@RequestParam String phone,@RequestParam String otpInput)
    {
        Admin admin = adminLoginService.validateLoginOtp(phone,otpInput);

        return ResponseEntity.ok(admin);
    }




}
