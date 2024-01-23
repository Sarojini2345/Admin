package com.ldtech.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class ViewPageController {
     
	@GetMapping("/getPage")
	public String getLoginPage(){
		return "Login";
	}
	
	@GetMapping("/getHome")
	public String getDashboard() {
		return "Home_page";
		
	}

    @GetMapping("/hello")
    public String homePage(@RequestHeader(value = "Accept", defaultValue = "") String acceptHeader, Model model) {
        System.out.println("EmployeeAllocationController.homePage()");

        if (acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {
            // Return JSON response
            return "no";
        } else {
            // Return HTML page
            return "employee_allocation";
        }
    }
}
