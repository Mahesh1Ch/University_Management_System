package com.identity.identity_service.service;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.entity.Otp;
import com.identity.identity_service.exception.ResourseNotFoundException;
import com.identity.identity_service.repositoty.AdminRegistrationRepository;
import com.identity.identity_service.repositoty.OtpRepository;
import com.identity.identity_service.utility.OtpUtility;
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

    private final OtpUtility otpUtility;

    @Autowired
    public AdminRegistrationService(AdminRegistrationRepository repository,OtpUtility otpUtility)
    {
        this.repository = repository;
        this.otpUtility=otpUtility;
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

    public List<Admin> getAllAdmins()
    {
        return repository.findAll();
    }


    @Transactional
    public String initiateAdminCreation(@Valid Admin admin)
    {
        Admin existingAdmin = repository.findByPhone(admin.getPhone());
        if (existingAdmin == null) {
            String otp = otpUtility.generateOtp(admin.getPhone());
            // Ideally, send the OTP via SMS or email here
            otpUtility.sendOtpToUser(admin.getPhone(), otp);
            // Return a status or message to indicate OTP has been sent
            return "OTP sent to " + admin.getPhone();
        } else {
            throw new ResourseNotFoundException("Admin with this phone number already exists.");
        }
    }




    @Transactional
    // Method to save admin after OTP verification
    public Admin completeAdminCreation(@Valid Admin admin, String otpInput) {
        if (otpUtility.verifyOtp(admin.getPhone(), otpInput)) {
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
