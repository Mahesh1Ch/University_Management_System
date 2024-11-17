package com.identity.identity_service.controller;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @Autowired
    public AdminLoginController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/otpgeneration")
    public ResponseEntity<Map<String,String>>  generateLoginOtp(@RequestParam String phone) {
        String responseMessage = adminLoginService.generateLoginOtp(phone);
        Map<String, String> response = new HashMap<>();
        response.put("message", responseMessage);  // The message will be "OTP sent to 9999999990"
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/otpvalidation")
    public ResponseEntity<Map<String,Object>> validateLoginOtp(@RequestParam String phone,@RequestParam String otpInput)
    {
        Admin admin = adminLoginService.validateLoginOtp(phone,otpInput);

        Map<String, Object> response = new HashMap<>();

        if (admin != null) {
            response.put("status", "success");  // Adding the status field
            response.put("data", admin);        // Adding the admin data
        } else {
            response.put("status", "error");
            response.put("message", "Invalid OTP or registration failed !");
        }

        return ResponseEntity.ok(response);
    }




}
