package com.bugrasessevmez.issuemanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bugrasessevmez.issuemanagement.dto.IssueDetailDto;
import com.bugrasessevmez.issuemanagement.dto.IssueDto;
import com.bugrasessevmez.issuemanagement.entity.Issue;
import com.bugrasessevmez.issuemanagement.util.TPage;

public interface IssueService {
	IssueDto save(Issue issue);
	
	IssueDto getById(Long id);
	
	TPage<IssueDto> getAllPageable(Pageable pageable);
	
	List<IssueDto> getAll();
	
	IssueDetailDto getByIdWithDetails(Long id);
	
	Boolean delete(Issue issue);
	
	Boolean delete(Long id);
	
	IssueDto update(Issue issue);
}
