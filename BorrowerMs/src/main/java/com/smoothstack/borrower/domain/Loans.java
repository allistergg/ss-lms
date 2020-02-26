package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_book_loans")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Loans implements Serializable {

	private static final long serialVersionUID = 1253980678028553981L;

	@EmbeddedId
	BorrowerID borrowerid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "loans", "publisher", "author", "genre", "bookcopies" })
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", insertable = false, updatable = false)
	@JsonIgnoreProperties("loans")
	private Branch branch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cardNo", insertable = false, updatable = false)
	@JsonIgnoreProperties("loans")
	private Borrower borrower;

	@Column(name = "dateOut")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateOut;

	@Column(name = "dueDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dueDate;

	@Column(name = "dateIn")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateIn;

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
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
	public Timestamp getDateDue() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDateDue(Timestamp dueDate) {
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

	/**
	 * @return the borrowerid
	 */
	public BorrowerID getBorrowerid() {
		return borrowerid;
	}

	/**
	 * @param borrowerid the borrowerid to set
	 */
	public void setBorrowerid(BorrowerID borrowerid) {
		this.borrowerid = borrowerid;
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

	@Override
	public int hashCode() {
		return Objects.hash(book, borrower, branch, dueDate, dateIn, dateOut);
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
		return Objects.equals(book, other.book) && Objects.equals(borrower, other.borrower)
				&& Objects.equals(branch, other.branch) && Objects.equals(dueDate, other.dueDate)
				&& Objects.equals(dateIn, other.dateIn) && Objects.equals(dateOut, other.dateOut);
	}

	@Override
	public String toString() {
		return "Loans [book=" + book + ", branch=" + branch + ", borrower=" + borrower + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + ", dateIn=" + dateIn + "]";
	}

	/**
	 * @param book
	 * @param branch
	 * @param borrower
	 * @param dateOut
	 * @param dueDate
	 * @param dateIn
	 */
	public Loans(Book book, Branch branch, Borrower borrower, Timestamp dateOut, Timestamp dueDate, Timestamp dateIn) {
		super();
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
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

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "cardno", insertable = false, updatable = false)
	 * 
	 * @JsonIgnoreProperties("loans") private Borrower borrower;
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "branchid", insertable = false, updatable = false)
	 * 
	 * @JsonIgnoreProperties("loans") private Branch branch;
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "bookid", insertable = false, updatable = false)
	 * 
	 * @JsonIgnoreProperties({ "loans", "publisher", "authors", "genres", "copies"
	 * }) private Book book;
	 */

}