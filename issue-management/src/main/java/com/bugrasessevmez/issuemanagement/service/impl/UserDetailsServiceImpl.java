package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.UserRepository;
import com.bugrasessevmez.issuemanagement.entity.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username); 
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Arrays.asList(new SimpleGrantedAuthority("USER")));
	}

}
