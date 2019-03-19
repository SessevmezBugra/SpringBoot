package com.bugrasessevmez.issuemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugrasessevmez.issuemanagement.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{

}
