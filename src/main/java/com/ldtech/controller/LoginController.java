package com.ldtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
     
	@GetMapping("/getPage")
	public String getLoginPage(){
		return "Login";
	}
	
	@GetMapping("/getDashboard")
	public String getDashboard() {
		return "Dashboard";
		
	}
}
