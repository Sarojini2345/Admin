package com.ldtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ldtech.service.LoginService;
import com.ldtech.serviceImpl.LoginServiceImpl;



@RestController
public class LoginController {

	@Autowired
    private LoginServiceImpl adminService;
	
    @Autowired
    private Environment environment;
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
    	System.out.println("LoginController.forgotPassword()1");
        adminService.sendPasswordResetEmail(email);
        System.out.println("LoginController.forgotPassword()");
        return "Password reset link sent to your email.";
    }
 
//    @PostMapping("/reset-password")
//    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
//        adminService.resetPassword(email, newPassword);
//        return "Password reset successful!";
//    }
//    
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String resetToken, @RequestParam String newPassword) {
    	System.out.println(newPassword);
        if (adminService.validateResetToken(resetToken)) {
            adminService.updatePassword(resetToken, newPassword);
            System.out.println("5");
            return new ResponseEntity<>("Password reset successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }
    
  @GetMapping("/reset-password")
   public ModelAndView showResetPasswordForm(@RequestParam String token ) {
       // Create a ModelAndView object and set the view name
       ModelAndView modelAndView = new ModelAndView("ResetPassword");

       // Add the token to the model if needed in the form
      modelAndView.addObject("token", token);
       // Return the ModelAndView object
       return modelAndView;
   }
    
}
