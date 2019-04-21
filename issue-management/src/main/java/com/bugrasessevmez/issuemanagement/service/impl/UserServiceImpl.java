package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.UserRepository;
import com.bugrasessevmez.issuemanagement.dto.RegistrationRequest;
import com.bugrasessevmez.issuemanagement.dto.UserDto;
import com.bugrasessevmez.issuemanagement.entity.User;
import com.bugrasessevmez.issuemanagement.service.UserService;
import com.bugrasessevmez.issuemanagement.util.TPage;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto save(User user) {
		if(user.getEmail() == null) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		return modelMapper.map(userRepository.save(user),UserDto.class);
	}

	@Override
	public UserDto getById(Long id) {
		return modelMapper.map(userRepository.getOne(id),UserDto.class);
	}

	@Override
	public TPage<UserDto> getAllPageable(Pageable pageable) {
		TPage<UserDto> page = new TPage<UserDto>();
		Page<User> data = userRepository.findAll(pageable);
		page.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto.class)));
		return page;
	}

	@Override
	public UserDto getByUserName(String username) {
		return modelMapper.map(userRepository.findByUsername(username),UserDto.class);
	}

	@Override
	public List<UserDto> getAll() {
		return Arrays.asList(modelMapper.map(userRepository.findAll(),UserDto[].class));
	}

	@Override
	public UserDto update(User user) {
		return modelMapper.map(userRepository.save(user),UserDto.class);
	}

	@Override
	public Boolean delete(Long id) {
		userRepository.deleteById(id);
		return true;
	}
	
	@Override
	public Boolean register(RegistrationRequest registrationRequest) {
		try {
			User user = new User();
			user.setEmail(registrationRequest.getEmail());
			user.setNameSurname(registrationRequest.getNameSurname());
			user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
			user.setUsername(registrationRequest.getUsername());
			userRepository.save(user);
			return Boolean.TRUE;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return Boolean.FALSE;
		}
	}

}
