package com.entity;

import java.util.List;

public class Page {

	private int pagesize;
	private int pageNum;
	private int totalrecords;
	private List list;
	private List<Book> booksincate;
	
	
	
	public List<Book> getBooksincate() {
		return booksincate;
	}
	public void setBooksincate(List<Book> booksincate) {
		this.booksincate = booksincate;
	}
	public List<Book> getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalrecords() {
		return totalrecords;
	}
	public void setTotalrecords(int totalrecords) {
		this.totalrecords = totalrecords;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
}
