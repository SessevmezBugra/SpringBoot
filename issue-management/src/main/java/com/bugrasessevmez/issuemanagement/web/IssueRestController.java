package com.bugrasessevmez.issuemanagement.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dto.IssueDto;
import com.bugrasessevmez.issuemanagement.entity.Issue;
import com.bugrasessevmez.issuemanagement.service.IssueService;
import com.bugrasessevmez.issuemanagement.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value=ApiPaths.IssueCtrl.CTRL)
public class IssueRestController {
	
	private final IssueService issueService;
	
	public IssueRestController(IssueService issueService) {
		this.issueService = issueService;
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)	
	@ApiOperation(value="Get By Id Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable(value="id", required=true) Long id){
		System.err.println("ID : "+id);
		return ResponseEntity.ok(issueService.getById(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> createProject(@Valid @RequestBody Issue issue){
		return ResponseEntity.ok(issueService.save(issue));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Update Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> updateProject(@Valid @RequestBody Issue issue){
		return ResponseEntity.ok(issueService.update(issue));
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	@ApiOperation(value="Delete Operation", response= Boolean.class)
	public ResponseEntity<Boolean> deleteProject(@PathVariable(value="id",required=true) Long id){
		return ResponseEntity.ok(issueService.delete(id));
	}
} 
