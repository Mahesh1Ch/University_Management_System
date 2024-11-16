package com.identity.identity_service.controller;

import com.identity.identity_service.entity.Admin;
import com.identity.identity_service.service.AdminRegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminregistraion")
public class AdminRegistrationController
{
    private final AdminRegistrationService adminRegistrationService;

    @Autowired
    public AdminRegistrationController(AdminRegistrationService adminRegistrationService)
    {
        this.adminRegistrationService = adminRegistrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") int adminId) {
        Admin admin = adminRegistrationService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

   /* @PostMapping
    public ResponseEntity<Admin> createAdmin(@Validated @RequestBody Admin adminDetails) {
        Admin admin = adminRegistrationService.createAdmin(adminDetails);
        return ResponseEntity.ok(admin);
    }*/


    @PostMapping("/initiate")
    public ResponseEntity<String> initiateAdminCreation(@Validated @RequestBody Admin adminDetails) {
        String responseMessage = adminRegistrationService.initiateAdminCreation(adminDetails);
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to complete admin creation after OTP verification
    @PostMapping("/complete")
    public ResponseEntity<Admin> completeAdminCreation(@Validated @RequestBody Admin adminDetails,
                                                       @RequestParam String otp) {
        Admin admin = adminRegistrationService.completeAdminCreation(adminDetails, otp);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") int adminId,
                                             @Validated @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminRegistrationService.updateAdmin(adminId, adminDetails);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") int adminId) {
        adminRegistrationService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRegistrationService.getAllAdmins(); // You need to implement this in the service
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp, HttpServletRequest request, Model model) {
        if (adminRegistrationService.verifyOtp(phoneNumber, otp)) {
            // OTP is correct, store user info in the session
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", phoneNumber); // Store phone number as user identifier
            return "welcome"; // Redirect to a welcome page
        } else {
            model.addAttribute("errorMessage", "Invalid or expired OTP.");
            model.addAttribute("phoneNumber", phoneNumber);
            return "verifyOtp"; // Return to OTP verification with an error message
        }
    }
}

