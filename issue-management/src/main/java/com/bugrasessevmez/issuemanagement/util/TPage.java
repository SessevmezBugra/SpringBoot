package com.bugrasessevmez.issuemanagement.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class TPage<T> {
	private int page;
	private int size;
	private Sort sort;
	private int totalPage;
	private Long totalElements;
	private List<T> content;
	
	public TPage() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void setStat(Page<?> page, List<T> list) {
		this.page = page.getNumber();
		this.size = page.getSize();
		this.sort = page.getSort();
		this.totalPage = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.content = list;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
