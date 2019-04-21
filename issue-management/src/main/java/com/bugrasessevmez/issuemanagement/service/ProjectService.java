package com.bugrasessevmez.issuemanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bugrasessevmez.issuemanagement.dto.ProjectDto;
import com.bugrasessevmez.issuemanagement.entity.Project;
import com.bugrasessevmez.issuemanagement.util.TPage;

public interface ProjectService {
	
	ProjectDto save(Project project);
	
	ProjectDto getById(Long id);
	
	List<ProjectDto> getAll();
	
	ProjectDto getByProjectCode(String projectCode);
	
	List<ProjectDto> getByProjectCodeContains(String projectCode);
	
	TPage<ProjectDto> getAllPageable(Pageable pageable);
	
	Boolean delete(Project project);
	
	Boolean delete(Long id);
	
	ProjectDto update(Project project);
}
