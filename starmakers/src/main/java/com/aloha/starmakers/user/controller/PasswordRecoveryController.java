package com.aloha.starmakers.user.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.service.UserService;

@Controller
@RequestMapping("/password")
public class PasswordRecoveryController {
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/recover")
    public ResponseEntity<String> recoverPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        // Generate a token
        String token = UUID.randomUUID().toString();

        // Save the token and email association (e.g., in a database)
        userService.createPasswordResetTokenForUser(email, token);

        // Create password reset URL
        String resetUrl = "http://localhost:8080/password/reset?token=" + token;

        // Send email
        emailService.sendPasswordResetEmail(email, resetUrl);

        return ResponseEntity.ok("Password reset email sent.");
    }
}
