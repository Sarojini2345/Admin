package com.ldtech.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ldtech.dao.LoginRepo;
import com.ldtech.entity.AdminLogin;
import com.ldtech.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginRepo repo2;
	
	@Autowired
	JavaMailSender javaMailSender;
	

	@Override
    public void sendPasswordResetEmail(String email) {
		System.out.println("LoginServiceImpl.sendPasswordResetEmail()1");
        AdminLogin admin = repo2.findByEmail(email);
        if (admin != null) {
            String resetToken = generateResetToken();
            admin.setResetToken(resetToken);
            admin.setResetTokenExpiration(LocalDateTime.now().plusHours(1));
            repo2.save(admin);
 
            // Send password reset email
            System.out.println("LoginServiceImpl.sendPasswordResetEmail()");
            sendResetEmail(admin.getEmail(), resetToken);
        }
    }
 
    private void sendResetEmail(String email, String resetToken) {
        // Implement logic to send a password reset email with a link containing the resetToken
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password Reset");
        mailMessage.setText("To reset your password, click the link below:\n\n"
                + "http://localhost:8080/reset-password?token=" + resetToken);
 
        javaMailSender.send(mailMessage);
        System.out.println("LoginServiceImpl.sendResetEmail()");
    }
    
    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
 
//    @Override
//    public void resetPassword(String email, String newPassword) {
//        Admin admin = adminRepository.findByEmail(email);
//        if (admin != null) {
//            admin.setPassword(newPassword);
//            adminRepository.save(admin);
//        }
//    }
    
    @Override
    public boolean validateResetToken(String resetToken) {
        AdminLogin admin = repo2.findByResetToken(resetToken);
        return admin != null && isTokenValid(admin.getResetTokenExpiration());
    }
 
    @Override
    public void updatePassword(String resetToken, String newPassword) {
    	System.out.println("AdminServiceImpl.updatePassword()");
        AdminLogin admin = repo2.findByResetToken(resetToken);
        System.out.println("2");
        if (admin != null && isTokenValid(admin.getResetTokenExpiration())) {
        	System.out.println("3");
        	System.out.println(newPassword);
            admin.setPassword(newPassword);
            System.out.println(admin.getPassword());
            System.out.println("4");
            admin.setResetToken(null);
            admin.setResetTokenExpiration(null);
            repo2.save(admin);
        }
    }
 
    private boolean isTokenValid(LocalDateTime expirationTime) {
        if (expirationTime != null) {
            LocalDateTime now = LocalDateTime.now();
            return now.isBefore(expirationTime);
        }
        return false;
    }	
}
