package com.bugrasessevmez.issuemanagement.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dto.IssueDetailDto;
import com.bugrasessevmez.issuemanagement.dto.IssueDto;
import com.bugrasessevmez.issuemanagement.entity.Issue;
import com.bugrasessevmez.issuemanagement.enums.IssueStatus;
import com.bugrasessevmez.issuemanagement.service.IssueService;
import com.bugrasessevmez.issuemanagement.util.ApiPaths;
import com.bugrasessevmez.issuemanagement.util.TPage;

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
	
	@ApiOperation(value="Get By Pagination Operation",response=IssueDto.class)
	@RequestMapping(value="/pagination",method=RequestMethod.GET)
	public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable){
		return ResponseEntity.ok(issueService.getAllPageable(pageable));
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)	
	@ApiOperation(value="Get By Id Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(issueService.getById(id));
	}
	
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)	
	@ApiOperation(value="Get By Id Operation", response= IssueDto.class)
	public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(issueService.getByIdWithDetails(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> createProject(@Valid @RequestBody Issue issue){
		return ResponseEntity.ok(issueService.save(issue));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Update Operation", response= IssueDto.class)
	public ResponseEntity<IssueDto> updateProject(@RequestBody Issue issue){
		return ResponseEntity.ok(issueService.update(issue));
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	@ApiOperation(value="Delete Operation", response= Boolean.class)
	public ResponseEntity<Boolean> deleteProject(@PathVariable(value="id",required=true) Long id){
		return ResponseEntity.ok(issueService.delete(id));
	}
	@RequestMapping(value="/statuses",method=RequestMethod.GET)	
	@ApiOperation(value="Get By Id Operation", response= IssueDto.class)
	public ResponseEntity<List<IssueStatus>> getStatuses(){
		return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
	}
	
} 
