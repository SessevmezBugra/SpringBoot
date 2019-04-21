package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.IssueRepository;
import com.bugrasessevmez.issuemanagement.dto.IssueDetailDto;
import com.bugrasessevmez.issuemanagement.dto.IssueDto;
import com.bugrasessevmez.issuemanagement.dto.IssueHistoryDto;
import com.bugrasessevmez.issuemanagement.entity.Issue;
import com.bugrasessevmez.issuemanagement.entity.IssueHistory;
import com.bugrasessevmez.issuemanagement.enums.IssueStatus;
import com.bugrasessevmez.issuemanagement.service.IssueHistoryService;
import com.bugrasessevmez.issuemanagement.service.IssueService;
import com.bugrasessevmez.issuemanagement.util.TPage;


@Service
public class IssueServiceImpl implements IssueService{

	private final IssueRepository issueRepository;
	private final ModelMapper modelMapper;
	private final IssueHistoryService issueHistoryService;
	
	public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, IssueHistoryService issueHistoryService) {
		this.issueRepository = issueRepository;
		this.modelMapper = modelMapper;
		this.issueHistoryService = issueHistoryService;
	}
	
	@Override
	public IssueDto save(Issue issue) {
		issue.setDate(new Date());
		issue.setIssueStatus(IssueStatus.OPEN);
		return modelMapper.map(issueRepository.save(issue), IssueDto.class);
	}

	@Override
	public IssueDto getById(Long id) {
		return modelMapper.map(issueRepository.getOne(id), IssueDto.class);
	}
	
	@Override
	public IssueDetailDto getByIdWithDetails(Long id) {
		Issue issue = issueRepository.getOne(id);
		IssueDetailDto issueDetailDto = modelMapper.map(issue, IssueDetailDto.class);
		List<IssueHistoryDto> historyDtos = issueHistoryService.getByIssueId(id);
		issueDetailDto.setIssueHistories(historyDtos);
		return issueDetailDto;
	}

	@Override
	public TPage<IssueDto> getAllPageable(Pageable pageable) {
		Page<Issue> data = issueRepository.findAll(pageable);
		TPage<IssueDto> page = new TPage<IssueDto>();
		IssueDto[] issueDtos = modelMapper.map(data.getContent(), IssueDto[].class);
		page.setStat(data, Arrays.asList(issueDtos));
		return page;
	}

	@Override
	public Boolean delete(Issue issue) {
		issueRepository.delete(issue);
		return true;
	}

	@Override
	public Boolean delete(Long id) {
		issueRepository.deleteById(id);
		return true;
	}

	@Override
	public IssueDto update(Issue issue) {
		IssueHistory history = new IssueHistory();
		history.setAssignee(issue.getAssignee());
		history.setDate(new Date());
		history.setDetails(issue.getDetails());
		history.setIssue(issue);
		history.setIssueStatus(issue.getIssueStatus());
		issueHistoryService.save(history);
		return modelMapper.map(issueRepository.save(issue), IssueDto.class);
	}

	@Override
	public List<IssueDto> getAll() {
		return Arrays.asList(modelMapper.map(issueRepository.findAll(),IssueDto[].class));
	}

}
