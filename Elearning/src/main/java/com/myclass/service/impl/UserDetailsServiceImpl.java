package com.myclass.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("Email NOT Found");
		}
		
		String roleNameString = user.getRole().getName();
		
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		authorities.add( new SimpleGrantedAuthority(roleNameString));
		
		LoginDto loginDto = new LoginDto(user.getEmail(), user.getPassword(), authorities, user.getAvatar(), user.getId());
		
		return loginDto;
	}

}
