package com.myclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.User;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends GenericController<User> {

	@Autowired
	UserService service;

	@Override
	public Object post(@RequestBody User user) {

		user.setId(0);
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

		service.saveOrUpdate(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);

	}

	@Override
	public Object put(@RequestBody User user) {

		if (service.findById(user.getId()) == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

		service.saveOrUpdate(user);

		return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);

	}

}
