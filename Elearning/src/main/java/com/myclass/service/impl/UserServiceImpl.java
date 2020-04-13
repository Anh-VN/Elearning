package com.myclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	protected RoleService roleService;
	
	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public void saveUserbyUserDto(UserDto userDto) {
		userRepository.save(createUserFromDto(userDto));
	}

	private User createUserFromDto(UserDto userDto) {
		
		User user = new User();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAddress(userDto.getAddress());
		user.setAvatar(userDto.getAvatar());
		user.setFullname(userDto.getFullname());
		user.setPhone(userDto.getPhone());
		user.setRoleId(userDto.getRoleId());
		user.setRole(roleService.findById(userDto.getRoleId()));
		
		return user;
	}

	@Override
	public UserDto createUserDtoFromUser(User user) {
		
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAddress(user.getAddress());
		userDto.setAvatar(user.getAvatar());
		userDto.setFullname(user.getFullname());
		userDto.setPhone(user.getPhone());
		userDto.setRoleId(user.getRoleId());

		return userDto;
	}

	@Override
	public String updateAuth(UserDto updatedUser) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			LoginDto loginDto = (LoginDto) userDetails;

			if (loginDto.getUserId() == updatedUser.getId()) {
				loginDto.setUsername(updatedUser.getEmail());
				loginDto.setFullname(updatedUser.getFullname());
				loginDto.setAvatar(updatedUser.getAvatar());

				String roleNameString = roleService.findById(updatedUser.getRoleId()).getName();

				if (!loginDto.getAuthorities().toString().equals("[" + roleNameString + "]")) {			
					return "resetLogin";
				}
			}
		}
		
		return null;
	}
	

}
