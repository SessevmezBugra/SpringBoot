package com.bugrasessevmez.issuemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="project")
public class Project extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name="project_code",length=30)
	private String projectCode;
	
	@Column(name="project_name",length=300)
	private String projectName;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="assignee_user_id")
	private User manager;

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

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectCode=" + projectCode + ", projectName=" + projectName + ", manager="
				+ manager + "]";
	}
	
	
	
	
}
