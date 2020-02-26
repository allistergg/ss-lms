package com.smoothstack.borrower.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookCopyID implements Serializable {

	private static final long serialVersionUID = 6696549260413624L;

	@Column(name = "bookId")
	private Integer bookId;

	@Column(name = "branchId")
	private Integer branchId;

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the noOfCopies
	 */

	@Override
	public String toString() {
		return "BookCopyID [bookId=" + bookId + ", branchId=" + branchId + "]";
	}

	
	/**
	 * @param bookId
	 * @param branchId
	 */
	public BookCopyID(Integer bookId, Integer branchId) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
	}

	public BookCopyID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
