package com.myclass.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myclass.entity.ErrorResponse;

@ControllerAdvice (basePackages = "com.myclass.controller")
public class ApiControllerAdvice {

	@ExceptionHandler
	public Object handleExp(Exception exp) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value() + " - BAD REQUEST", exp.getMessage(),
				formatter.format(new Date()));

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	/*
	 * @ExceptionHandler(UsernameNotFoundException.class) public ResponseEntity<?>
	 * handle(UsernameNotFoundException exp) {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 * 
	 * ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value() +
	 * " - BAD REQUEST", exp.getMessage(), formatter.format(new Date()));
	 * 
	 * return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST); }
	 */

}
