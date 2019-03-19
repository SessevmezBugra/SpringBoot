package com.bugrasessevmez.issuemanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.UserRepository;
import com.bugrasessevmez.issuemanagement.entity.User;
import com.bugrasessevmez.issuemanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		if(user.getEmail() == null) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		return userRepository.save(user);
	}

	@Override
	public User getById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public Page<User> getAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User getByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
