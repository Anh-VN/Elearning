package com.myclass.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ElearningControllerAdvice {

	@ExceptionHandler
	public String handleExp(Exception exp, Model model) {
		
		model.addAttribute("statusCode", "500");
		model.addAttribute("exception", exp.getMessage());
		
		return "errors/error";
	}
}
