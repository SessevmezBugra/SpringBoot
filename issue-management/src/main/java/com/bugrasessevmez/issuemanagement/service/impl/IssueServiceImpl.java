package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.IssueRepository;
import com.bugrasessevmez.issuemanagement.dto.IssueDto;
import com.bugrasessevmez.issuemanagement.entity.Issue;
import com.bugrasessevmez.issuemanagement.service.IssueService;
import com.bugrasessevmez.issuemanagement.util.TPage;


@Service
public class IssueServiceImpl implements IssueService{

	private final IssueRepository issueRepository;
	private final ModelMapper modelMapper;
	
	public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
		this.issueRepository = issueRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public IssueDto save(Issue issue) {
		if(issue.getDate() == null) {
			throw new IllegalArgumentException("Issue cannot be null");
		}
		return modelMapper.map(issueRepository.save(issue), IssueDto.class);
	}

	@Override
	public IssueDto getById(Long id) {
		return modelMapper.map(issueRepository.getOne(id), IssueDto.class);
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
		return modelMapper.map(issueRepository.save(issue), IssueDto.class);
	}

}
