package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CopyId implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060619916052489143L;
	@Column(name="bookid")
	private Integer bookId;
	@Column(name="branchid")
	private Integer branchId;
	
	

	public CopyId() {
	
	}

	public CopyId(Integer bookId, Integer branchId) {

		this.bookId = bookId;
		this.branchId = branchId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, branchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopyId other = (CopyId) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(branchId, other.branchId);
	}
	
	
	
	
	
	
	
	
	
	
	
}
