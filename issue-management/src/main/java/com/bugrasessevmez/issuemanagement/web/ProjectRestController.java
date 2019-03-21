package com.bugrasessevmez.issuemanagement.web;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dto.ProjectDto;
import com.bugrasessevmez.issuemanagement.entity.Project;
import com.bugrasessevmez.issuemanagement.service.ProjectService;
import com.bugrasessevmez.issuemanagement.util.ApiPaths;
import com.bugrasessevmez.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value=ApiPaths.ProjectCtrl.CTRL)
public class ProjectRestController {
	
	private final ProjectService projectService;
	
	public ProjectRestController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@ApiOperation(value="Get By Pagination Operation",response=ProjectDto.class)
	@RequestMapping(value="/pagination",method=RequestMethod.GET)
	public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable){
		return ResponseEntity.ok(projectService.getAllPageable(pageable));
	}
	
	@ApiOperation(value="Get By Id Operation",response=ProjectDto.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ProjectDto> getById(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(projectService.getById(id));
	}
	
	@ApiOperation(value="Create Operation",response=ProjectDto.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody Project project){
		return ResponseEntity.ok(projectService.save(project));
	}
	
	@ApiOperation(value="Update Operation",response=ProjectDto.class)
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody Project project){
		return ResponseEntity.ok(projectService.update(project));
	}
	
	@ApiOperation(value="Delete Operation",response=Boolean.class)
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<Boolean> deleteProject(@PathVariable(value="id",required=true) Long id){
		return ResponseEntity.ok(projectService.delete(id));
	}
} 
