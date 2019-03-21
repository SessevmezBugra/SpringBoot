package com.bugrasessevmez.issuemanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dto.ProjectDto;
import com.bugrasessevmez.issuemanagement.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("versioning")
@Api(value="versioning")
public class ProjectVersionedRestController {
	
	@Autowired
	private ProjectService projectService;
	
	@ApiOperation(value="Get By Id Operation V1",response=ProjectDto.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET,params="version=1")
	public ResponseEntity<ProjectDto> getByIdV1(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(projectService.getById(id));
	}
	
	@ApiOperation(value="Get By Id Operation V2",response=ProjectDto.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET,params="version=2")
	public ResponseEntity<ProjectDto> getByIdV2(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(projectService.getById(id));
	}
} 
