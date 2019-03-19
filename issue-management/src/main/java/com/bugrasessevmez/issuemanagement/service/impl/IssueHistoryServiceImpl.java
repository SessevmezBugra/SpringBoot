package com.bugrasessevmez.issuemanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.IssueHistoryRepository;
import com.bugrasessevmez.issuemanagement.entity.IssueHistory;
import com.bugrasessevmez.issuemanagement.service.IssueHistoryService;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService{
	
	private final IssueHistoryRepository issueHistoryRepository;
	
	public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository) {
		this.issueHistoryRepository = issueHistoryRepository;
	}
	@Override
	public IssueHistory save(IssueHistory issueHistory) {
		if(issueHistory.getDate() == null) {
			throw new IllegalArgumentException("Issue cannot be null");
		}
		return issueHistoryRepository.save(issueHistory);
	}

	@Override
	public IssueHistory getById(Long id) {
		return issueHistoryRepository.getOne(id);
	}

	@Override
	public Page<IssueHistory> getAllPageable(Pageable pageable) {
		return issueHistoryRepository.findAll(pageable);
	}

	@Override
	public Boolean delete(IssueHistory issueHistory) {
		issueHistoryRepository.delete(issueHistory);
		return Boolean.TRUE;
	}

}
