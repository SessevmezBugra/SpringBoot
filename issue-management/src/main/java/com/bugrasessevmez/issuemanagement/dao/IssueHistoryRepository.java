package com.bugrasessevmez.issuemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugrasessevmez.issuemanagement.entity.IssueHistory;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{
	
}
