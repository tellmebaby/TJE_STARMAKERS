package com.aloha.starmakers.user.service;

public interface EmailService {
    
    public void sendPasswordResetEmail (String email, String resetUrl);     
}
