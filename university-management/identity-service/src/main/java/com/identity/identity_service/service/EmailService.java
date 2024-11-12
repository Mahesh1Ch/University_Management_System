package com.identity.identity_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    @Autowired
    private JavaMailSender mailSender;

    @Value("$(spring.mail.username)")
    private String fromMail;


    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromMail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
