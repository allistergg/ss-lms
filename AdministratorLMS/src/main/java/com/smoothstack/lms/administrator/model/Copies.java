package com.smoothstack.lms.administrator.model;

import java.io.Serializable;

public class Copies implements Serializable {

	private static final long serialVersionUID = 7060294048432715136L;
	private Book book;
	private Branch branch;
	private Integer noOfCopies;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

}
