package com.identity.identity_service.service;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.entity.Otp;
import com.identity.identity_service.exception.ResourseNotFoundException;
import com.identity.identity_service.repositoty.AdminRegistrationRepository;
import com.identity.identity_service.repositoty.OtpRepository;
import com.identity.identity_service.utility.OtpUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AdminLoginService {

    private final AdminRegistrationRepository adminRegistrationRepository;
    @Autowired
    private OtpUtility otpUtility;
    private final OtpRepository otpRepository;

    @Autowired
    public AdminLoginService(AdminRegistrationRepository adminRegistrationRepository,OtpRepository otpRepository) {
        this.adminRegistrationRepository = adminRegistrationRepository;
        this.otpRepository=otpRepository;
    }

    public String generateLoginOtp(String adminPhone)
    {
        Admin admin = adminRegistrationRepository.findByPhone(adminPhone);
        if(admin != null)
        {
            String otp = otpUtility.generateOtp(admin.getPhone());
            // Ideally, send the OTP via SMS or email here
            otpUtility.sendOtpToUser(admin.getPhone(), otp);
            // Return a status or message to indicate OTP has been sent
            return "OTP sent to " + admin.getPhone();

        }else
        {
            throw new ResourseNotFoundException("Admin with this phone number already exists.");
        }
    }

    @Transactional
    public Admin validateLoginOtp(String phone_no,String otpInput)
    {
        if (otpUtility.verifyOtp(phone_no, otpInput)) {
            Admin admin_save=adminRegistrationRepository.findByPhone(phone_no);
            /*if(admin_save!=null)
            {
            String subject = "Registration Successful";
            String message = "Dear " + admin.getName() + ",\n\nThank you for registering with us!";
            emailService.sendEmail(admin.getEmail(), subject, message);
            }*/
            return adminRegistrationRepository.findByPhone(phone_no);
        } else {
            throw new ResourseNotFoundException("Invalid or expired OTP.");
        }
    }

}
