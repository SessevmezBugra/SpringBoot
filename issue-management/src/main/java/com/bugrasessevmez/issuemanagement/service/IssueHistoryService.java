package com.bugrasessevmez.issuemanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bugrasessevmez.issuemanagement.dto.IssueHistoryDto;
import com.bugrasessevmez.issuemanagement.entity.IssueHistory;

public interface IssueHistoryService {
	
	IssueHistory save(IssueHistory issueHistory);
	
	IssueHistory getById(Long id);
	
	List<IssueHistoryDto> getByIssueId(Long id);
	
	Page<IssueHistory> getAllPageable(Pageable pageable);
	
	Boolean delete(IssueHistory issueHistory);
}
