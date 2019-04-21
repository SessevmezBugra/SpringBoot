package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.IssueHistoryRepository;
import com.bugrasessevmez.issuemanagement.dto.IssueHistoryDto;
import com.bugrasessevmez.issuemanagement.entity.IssueHistory;
import com.bugrasessevmez.issuemanagement.service.IssueHistoryService;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService{
	
	private final IssueHistoryRepository issueHistoryRepository;
	private final ModelMapper modelMapper;
	
	public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository,ModelMapper modelMapper) {
		this.issueHistoryRepository = issueHistoryRepository;
		this.modelMapper = modelMapper;
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
	
	@Override
	public List<IssueHistoryDto> getByIssueId(Long id) {
		return Arrays.asList(modelMapper.map(issueHistoryRepository.getByIssueIdOrderById(id), IssueHistoryDto[].class));
	}

}
