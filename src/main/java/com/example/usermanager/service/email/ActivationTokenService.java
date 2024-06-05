package com.example.usermanager.service.email;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationTokenService {

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}

