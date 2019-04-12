package com.bugrasessevmez.issuemanagement.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dto.UserDto;
import com.bugrasessevmez.issuemanagement.entity.User;
import com.bugrasessevmez.issuemanagement.service.UserService;
import com.bugrasessevmez.issuemanagement.util.ApiPaths;
import com.bugrasessevmez.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value=ApiPaths.UserCtrl.CTRL)
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value="Get By Pagination Operation",response=UserDto.class)
	@RequestMapping(value="/pagination",method=RequestMethod.GET)
	public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable){
		TPage<UserDto> data  = userService.getAllPageable(pageable);
		return ResponseEntity.ok(data);
	}
	
	@ApiOperation(value="Get All",response=UserDto.class)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDto>> getAll(){
		return ResponseEntity.ok(userService.getAll()); 
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)	
	@ApiOperation(value="Get By Id Operation", response= UserDto.class)
	public ResponseEntity<UserDto> getById(@PathVariable(value="id", required=true) Long id){
		System.err.println("ID : "+id);
		return ResponseEntity.ok(userService.getById(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create Operation", response= UserDto.class)
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user){
		return ResponseEntity.ok(userService.save(user));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Update Operation", response= UserDto.class)
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody User user){
		return ResponseEntity.ok(userService.update(user));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete Operation", response= Boolean.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userService.delete(id));
	}
} 
