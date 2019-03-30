package com.bugrasessevmez.issuemanagement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bugrasessevmez.issuemanagement.dao.ProjectRepository;
import com.bugrasessevmez.issuemanagement.dto.ProjectDto;
import com.bugrasessevmez.issuemanagement.entity.Project;
import com.bugrasessevmez.issuemanagement.service.ProjectService;
import com.bugrasessevmez.issuemanagement.util.TPage;


@Service
public class ProjectServiceImpl implements ProjectService{
	
	private final ProjectRepository projectRepository;
	private final ModelMapper modelMapper;
	
	public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
		this.projectRepository = projectRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProjectDto save(Project project) {
		if(projectRepository.getByProjectCode(project.getProjectCode()) != null) {
			throw new IllegalArgumentException("ProjectCode cannot be same");
		}
		return modelMapper.map(projectRepository.save(project), ProjectDto.class);
	}


	@Override
	public TPage<ProjectDto> getAllPageable(Pageable pageable) {
		TPage<ProjectDto> page = new TPage<ProjectDto>();
		Page<Project> data = projectRepository.findAll(pageable);
		page.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
		return page;
	}
	
	@Override
	public List<ProjectDto> getAll() {
		List<Project> data = projectRepository.findAll();
		return Arrays.asList(modelMapper.map(data, ProjectDto[].class));
	}

	@Override
	public ProjectDto getByProjectCode(String projectCode) {
		return modelMapper.map(projectRepository.getByProjectCode(projectCode), ProjectDto.class);
	}

	@Override
	public List<ProjectDto> getByProjectCodeContains(String projectCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Project project) {
		projectRepository.delete(project);
		return Boolean.TRUE;
	}

	@Override
	public ProjectDto getById(Long id) {
		return modelMapper.map(projectRepository.getOne(id), ProjectDto.class);
	}

	@Override
	public ProjectDto update(Project project) {
		if(projectRepository.getOne(project.getId()) == null) {
			throw new IllegalArgumentException("Project is not exist");
		}
		Project projectDb = projectRepository.getByProjectCode(project.getProjectCode()); 
		if(projectDb != null && project.getId() != projectDb.getId()) {
			throw new IllegalArgumentException("ProjectCode cannot be same");
		}
		return modelMapper.map(projectRepository.save(project), ProjectDto.class);
	}

	@Override
	public Boolean delete(Long id) {
		projectRepository.deleteById(id);
		return true;
	}

}
