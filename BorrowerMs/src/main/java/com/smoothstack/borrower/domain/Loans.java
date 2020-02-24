package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vpns3
 *
 */
@Entity
@Table(name = "tbl_book_loans")
public class Loans implements Serializable {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private Integer bookId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	private Integer branchId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardNo")
	private Integer cardNo;

	@Column(name = "dateOut")
	private Timestamp dateOut;

	@Column(name = "dueDate")
	private Timestamp dueDate;

	@Column(name = "dateIn")
	private Timestamp dateIn;

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

	/**
	 * @return the dateOut
	 */
	public Timestamp getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dueDate
	 */
	public Timestamp getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dateIn
	 */
	public Timestamp getDateIn() {
		return dateIn;
	}

	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, branchId, cardNo, dateIn, dateOut, dueDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loans other = (Loans) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(branchId, other.branchId)
				&& Objects.equals(cardNo, other.cardNo) && Objects.equals(dateIn, other.dateIn)
				&& Objects.equals(dateOut, other.dateOut) && Objects.equals(dueDate, other.dueDate);
	}

	@Override
	public String toString() {
		return "Loans [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + ", dateIn=" + dateIn + "]";
	}

	/**
	 * @param bookId
	 * 
	 * @param branchId
	 * @param cardNo
	 * @param dateOut
	 * @param dueDate
	 * @param dateIn
	 */
	public Loans(Integer bookId, Integer branchId, Integer cardNo, Timestamp dateOut, Timestamp dueDate,
			Timestamp dateIn) {
		super();
		this.bookId = bookId;

		this.branchId = branchId;
		this.cardNo = cardNo;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateIn = dateIn;
	}

	/**
	 * 
	 */
	public Loans() {
		super();
		// TODO Auto-generated constructor stub
	}

}
