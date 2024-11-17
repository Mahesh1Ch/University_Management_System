package com.identity.identity_service.utility;

import com.identity.identity_service.entity.Otp;
import com.identity.identity_service.repositoty.OtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OtpUtility
{

    @Autowired
    private OtpRepository otpRepository;




    public OtpUtility()
    {

    }


    @Transactional
    public String generateOtp(String phoneNumber) {
        String otp = String.format("%06d", (int) (Math.random() * 999999));
        Otp otpEntity = new Otp();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(2));

        Optional<Otp> existingOtp = otpRepository.findByPhoneNumber(phoneNumber);
        existingOtp.ifPresent(otpRepository::delete); // delete existing OTP if present

        otpRepository.save(otpEntity);
        return otp;
    }


    // Method to verify the OTP
    public boolean verifyOtp(String phoneNumber, String otpInput)
    {
        Optional<Otp> otpEntityOptional = otpRepository.findByPhoneNumber(phoneNumber);

        if (otpEntityOptional.isPresent()) {
            Otp otpEntity = otpEntityOptional.get();
            boolean otpStatus = otpEntity.getOtp().equals(otpInput) && LocalDateTime.now().isBefore(otpEntity.getExpiryTime());

            if (otpStatus)
            {
                otpRepository.deleteByPhoneNumber(phoneNumber);
                return true;
            }
        }

        return false; // OTP is invalid or not found
    }

    public void sendOtpToUser(String phoneNumber, String otp) {
        // Logic to send OTP to the user's phone via SMS
        System.out.println("Sending OTP: " + otp + " to " + phoneNumber);
    }
}
