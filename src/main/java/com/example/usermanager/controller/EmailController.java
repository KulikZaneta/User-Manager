package com.example.usermanager.controller;

import com.example.usermanager.service.email.ActivationTokenService;
import com.example.usermanager.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private ActivationTokenService activationTokenService;
    private final EmailService emailService;

    @Autowired
    public EmailController(ActivationTokenService activationTokenService, EmailService emailService) {
        this.activationTokenService = activationTokenService;
        this.emailService = emailService;
    }


    @GetMapping("/activate/{email}")
    public String activateAccount(@PathVariable String email) {
        String activationToken = activationTokenService.generateToken();
        emailService.sendActivationEmail(email, activationToken);
        return "An activation message has been sent to the email address: " + email;
    }
}

