package com.bugrasessevmez.issuemanagement.dto;

import java.util.Date;

import com.bugrasessevmez.issuemanagement.enums.IssueStatus;

public class IssueHistoryDto {

	private Long id;
    private Date date;
    private IssueStatus issueStatus;
    private String details;
	private UserDto assignee;
	private IssueDto issue;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public IssueStatus getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public UserDto getAssignee() {
		return assignee;
	}

	public void setAssignee(UserDto assignee) {
		this.assignee = assignee;
	}

	public IssueDto getIssue() {
		return issue;
	}

	public void setIssue(IssueDto issue) {
		this.issue = issue;
	}
	
	
}
