package com.example.usermanager.service;

import com.example.usermanager.service.email.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Before
    public void setUp() {
        emailService = new EmailService(mailSender);
    }

    @Test
    public void testSendActivationEmailSuccessful() {
        // Given
        String to = "example@example.com";
        String activationToken = "exampleToken";
        String activationLink = "http://localhost:8080/activate/" + activationToken;
        String subject = "Account activation";
        String text = "To activate your account, please click on the link below: " + activationLink;

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(to);
        expectedMessage.setSubject(subject);
        expectedMessage.setText(text);

        // When
        emailService.sendActivationEmail(to, activationToken);

        // Then
        Mockito.verify(mailSender).send(Mockito.eq(expectedMessage));
    }
}
