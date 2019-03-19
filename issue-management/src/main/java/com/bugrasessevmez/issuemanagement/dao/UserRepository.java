package com.bugrasessevmez.issuemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugrasessevmez.issuemanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
