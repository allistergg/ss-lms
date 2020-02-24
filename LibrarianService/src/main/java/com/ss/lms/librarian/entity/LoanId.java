package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LoanId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -585444889535776307L;
	@Column(name="bookid")
	private Integer bookId;
	@Column(name="branchid")
	private Integer branchId;
	@Column(name="cardno")
	private Integer cardNo;
	
	
	
	public LoanId() {
	}
	public LoanId(Integer bookId, Integer branchId, Integer cardNo) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
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
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bookId, branchId, cardNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanId other = (LoanId) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(branchId, other.branchId)
				&& Objects.equals(cardNo, other.cardNo);
	}
	
	
	
	
	

}
