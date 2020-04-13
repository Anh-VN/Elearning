package com.myclass.service;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public interface UserService extends GenericService<User, Integer>{

	public User findByEmail(String email);
	
	public void saveUserbyUserDto(UserDto userDto);
	
	public UserDto createUserDtoFromUser(User user);
	
	public String updateAuth(UserDto updatedUser);
}
