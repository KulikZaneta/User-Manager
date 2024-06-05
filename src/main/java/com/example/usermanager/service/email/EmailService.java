package com.example.usermanager.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(String to, String activationToken) {
        try {
            String activationLink = "http://localhost:8080/activate/" + activationToken;
            String subject = "Account activation";
            String text = "To activate your account, please click on the link below: " + activationLink;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Activation email sent successfully to: " + to);
        } catch (MailException e) {
            System.err.println("Failed to send activation email to: " + to);
            e.printStackTrace();
        }
    }
}

