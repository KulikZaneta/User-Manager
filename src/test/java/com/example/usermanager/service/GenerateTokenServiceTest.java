package com.example.usermanager.service;

import com.example.usermanager.service.email.ActivationTokenService;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenerateTokenServiceTest {

    @Test
    public void test_Generate_Token_Returns_Non_Null_UUID() {
        // Given
        ActivationTokenService generateTokenService = new ActivationTokenService();

        // When
        String token = generateTokenService.generateToken();

        // Then
        assertNotNull(token);
        assertTrue(token.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @Test
    public void test_Generate_Token_Returns_Unique_Token() {
        // Given
        ActivationTokenService generateTokenService = new ActivationTokenService();

        // When
        String token1 = generateTokenService.generateToken();
        String token2 = generateTokenService.generateToken();

        // Then
        assertNotEquals(token1, token2);
    }
}

