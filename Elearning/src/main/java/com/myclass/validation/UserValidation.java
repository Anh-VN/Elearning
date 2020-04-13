package com.myclass.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myclass.dto.UserDto;

@Component
public class UserValidation implements Validator {

	public boolean supports(Class<?> clazz) {

		return UserDto.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		UserDto userDto = (UserDto) target;


		if (!userDto.getConfirm().equals( userDto.getPassword())) {
	
			errors.rejectValue("confirm", "password.confirm", "A wrong confirm entered!");
		}

		

	}

}
