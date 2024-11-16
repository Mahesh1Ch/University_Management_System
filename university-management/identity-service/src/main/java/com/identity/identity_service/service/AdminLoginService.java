package com.identity.identity_service.service;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.repositoty.AdminRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    private final AdminRegistrationRepository adminRegistrationRepository;

    @Autowired
    public AdminLoginService(AdminRegistrationRepository adminRegistrationRepository) {
        this.adminRegistrationRepository = adminRegistrationRepository;
    }

    public boolean validateAdminLogin(String adminPhone) {
        Admin admin = adminRegistrationRepository.findByPhone(adminPhone);
        return admin != null; // Returns true if found, false otherwise
    }
}
