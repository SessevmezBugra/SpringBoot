package com.bugrasessevmez.issuemanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bugrasessevmez.issuemanagement.dto.RegistrationRequest;
import com.bugrasessevmez.issuemanagement.dto.UserDto;
import com.bugrasessevmez.issuemanagement.entity.User;
import com.bugrasessevmez.issuemanagement.util.TPage;

public interface UserService {
	
	UserDto save(User user);
	
	UserDto getById(Long id);
	
	TPage<UserDto > getAllPageable(Pageable pageable);
	
	List<UserDto> getAll();
	
	UserDto getByUserName(String username);
	
	UserDto update(User user);
	
	Boolean delete(Long id);
	
	Boolean register(RegistrationRequest registrationRequest);
}
