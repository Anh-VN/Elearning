package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/login")
public class AdminLoginController {

	@GetMapping("")
	public String index(@RequestParam(required = false) String state, Model model) {
		String message = "";
        String color = "text-danger";
		if (state != null) {
			if (state.equals("error")) {
				message = "Invalid Email or Password!";
			}
			if (state.equals("reset")) {
				message = "Your ROLE has been changed! Login to continue...";
				color = "text-info";
			}

		}
		
		model.addAttribute("message", message);
		model.addAttribute("color", color);

		
		return "login/login-admin";
	}

}
