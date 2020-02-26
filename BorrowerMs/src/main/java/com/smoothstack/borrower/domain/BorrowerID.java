package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BorrowerID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5749996897203330299L;

	@Column(name="bookId")
	private Integer bookId;
	
	@Column(name="branchId")
	private Integer branchId;
	
	@Column(name="cardNo")
	private Integer cardNo;

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
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
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
		BorrowerID other = (BorrowerID) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(branchId, other.branchId)
				&& Objects.equals(cardNo, other.cardNo);
	}

	@Override
	public String toString() {
		return "BorrowerID [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + "]";
	}

	/**
	 * @param bookId
	 * @param branchId
	 * @param cardNo
	 */
	public BorrowerID(Integer bookId, Integer branchId, Integer cardNo) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
	}

	/**
	 * 
	 */
	public BorrowerID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
