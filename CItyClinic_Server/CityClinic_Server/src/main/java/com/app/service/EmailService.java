package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
    	System.out.println("in mail____________________");
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println(toEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("parthkadam391@gmail.com");
        mailSender.send(message);
    }
}
