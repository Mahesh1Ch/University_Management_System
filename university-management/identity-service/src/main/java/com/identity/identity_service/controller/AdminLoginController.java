package com.identity.identity_service.controller;

import com.identity.identity_service.dto.LoginResponse;
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

    @PostMapping
    public ResponseEntity<LoginResponse> loginAdmin(@RequestParam String phone) {
        boolean isValid = adminLoginService.validateAdminLogin(phone);

        if (isValid) {
            return ResponseEntity.ok(new LoginResponse(true, "Success: Phone number found."));
        } else {
            return ResponseEntity.ok(new LoginResponse(false, "Failure: Phone number not found."));
        }
    }


}
