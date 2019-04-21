package com.bugrasessevmez.issuemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugrasessevmez.issuemanagement.entity.IssueHistory;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{
	
	List<IssueHistory> getByIssueIdOrderById(Long id);
}
