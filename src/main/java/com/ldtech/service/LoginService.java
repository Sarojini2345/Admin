package com.ldtech.service;

public interface LoginService {

	String sendPasswordResetEmail(String email);
    boolean validateResetToken(String token);    
    void updatePassword(String resetToken, String newPassword);
}
