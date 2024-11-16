package com.identity.identity_service.service;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.entity.Otp;
import com.identity.identity_service.exception.ResourseNotFoundException;
import com.identity.identity_service.repositoty.AdminRegistrationRepository;
import com.identity.identity_service.repositoty.OtpRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRegistrationService
{
    private final AdminRegistrationRepository repository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public AdminRegistrationService(AdminRegistrationRepository repository)
    {
        this.repository = repository;
    }

    public Admin getAdminById(int id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Admin not found with id: " + id));
    }

   /* public Admin createAdmin(@Valid Admin admin)
    {
        Admin admin_phone = repository.findByPhone((admin.getPhone()));
        if(admin_phone == null)
        {
            String otp = generateOtp(admin.getPhone());
            System.out.println(otp);
            return repository.save(admin);
        }
        else
        {
            return null;
        }
    }*/

    public Admin updateAdmin(int adminId, @Valid Admin adminDetails) {
        Admin admin = getAdminById(adminId);
        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        admin.setPhone(adminDetails.getPhone());

        return repository.save(admin);
    }

    public void deleteAdmin(int adminId) {
        getAdminById(adminId); // Optional: Verify admin exists before deletion
        repository.deleteById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }


    @Transactional
    public String initiateAdminCreation(@Valid Admin admin)
    {
        Admin existingAdmin = repository.findByPhone(admin.getPhone());
        if (existingAdmin == null) {
            String otp = generateOtp(admin.getPhone());
            // Ideally, send the OTP via SMS or email here
            sendOtpToUser(admin.getPhone(), otp);
            // Return a status or message to indicate OTP has been sent
            return "OTP sent to " + admin.getPhone();
        } else {
            throw new ResourseNotFoundException("Admin with this phone number already exists.");
        }
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
    public boolean verifyOtp(String phoneNumber, String otpInput) {
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


    @Transactional
    // Method to save admin after OTP verification
    public Admin completeAdminCreation(@Valid Admin admin, String otpInput) {
        if (verifyOtp(admin.getPhone(), otpInput)) {
            Admin admin_save= repository.save(admin); // Save admin
            /*if(admin_save!=null)
            {
            String subject = "Registration Successful";
            String message = "Dear " + admin.getName() + ",\n\nThank you for registering with us!";
            emailService.sendEmail(admin.getEmail(), subject, message);
            }*/
            return repository.save(admin_save);
        } else {
            throw new ResourseNotFoundException("Invalid or expired OTP.");
        }
    }

    private void sendOtpToUser(String phoneNumber, String otp) {
        // Logic to send OTP to the user's phone via SMS
        System.out.println("Sending OTP: " + otp + " to " + phoneNumber);
    }

   /* public boolean verifyOtp(String phoneNumber, String otp)
    {
        Optional<Otp> otpOptional = otpRepository.findByPhoneNumber(phoneNumber);
        if (otpOptional.isPresent()) {
            Otp otpEntity = otpOptional.get();
            if (otpEntity.getOtp().equals(otp) && LocalDateTime.now().isBefore(otpEntity.getExpiryTime())) {
                otpRepository.delete(otpEntity); // Remove OTP after successful verification
                return true;
            }
        }
        return false; // OTP invalid or expired
    }*/
}
