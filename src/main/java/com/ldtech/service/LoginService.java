package com.ldtech.service;

public interface LoginService {

	void sendPasswordResetEmail(String email);
    boolean validateResetToken(String token);    
    void updatePassword(String resetToken, String newPassword);
}
