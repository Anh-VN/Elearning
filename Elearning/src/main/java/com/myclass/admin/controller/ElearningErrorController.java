package com.myclass.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ElearningErrorController implements ErrorController {

	@GetMapping("")
	public String error(HttpServletRequest request, Model model) {
		String message = "";
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		if (statusCode != null && statusCode == 404) {
			return "errors/url";
		}

		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

		if (exception == null) {
			message = "N/A";
		} else {
			message = exception.getMessage();
		}

		model.addAttribute("statusCode", statusCode);
		model.addAttribute("exception", message);

		return "errors/error";
	}

	@GetMapping("/403")
	public String error403() {
		return "errors/error403";
	}

	@Override
	public String getErrorPath() {
		return "errors/error";
	}

}
