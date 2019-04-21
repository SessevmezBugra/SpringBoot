package com.bugrasessevmez.issuemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Project Transfer Object")
public class ProjectDto {
	
	@ApiModelProperty(value="Project ID")
	private Long id;
	
	@ApiModelProperty(required=true, value="Code Of Project")
	private String projectCode;
	
	@ApiModelProperty(required=true, value="Name Of Project")
	private String projectName;
	
	@ApiModelProperty(required=true, value="Project Manager")
	private UserDto manager;
	
    @ApiModelProperty(required = true,value = "Project Manager ID")
    private Long managerId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public UserDto getManager() {
		return manager;
	}

	public void setManager(UserDto manager) {
		this.manager = manager;
	}

}
