package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_book_loans")
@Where(clause = "deleted = false")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Loan implements Serializable {
	
	private static final long serialVersionUID = 5460221989751241343L;
	
	@EmbeddedId
	private LoansIdentity loansIdentity;
	
	@Column(name = "deleted")
	private Boolean deleted;
	
	@Column(name = "dateout")
	private LocalDate dateOut;
	
	@Column(name = "duedate")
	private LocalDate dateDue;
	
	@Column(name = "datein")
	private LocalDate dateIn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cardno", insertable = false, updatable = false)
	@JsonIgnoreProperties("loans")
	private Borrower borrower;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="branchid", insertable = false, updatable = false)
	@JsonIgnoreProperties({"loans","copies"})
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bookid", insertable = false, updatable = false)
	@JsonIgnoreProperties({"loans", "publisher", "authors", "genres", "copies"})
	private Book book;
	
	public LocalDate getDateOut() {
		return dateOut;
	}
	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}
	public LocalDate getDateDue() {
		return dateDue;
	}
	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}
	public LocalDate getDateIn() {
		return dateIn;
	}
	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}
	public void setLoansIdentity(LoansIdentity loansIdentity) {
		this.loansIdentity = loansIdentity;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((dateDue == null) ? 0 : dateDue.hashCode());
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((loansIdentity == null) ? 0 : loansIdentity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (dateDue == null) {
			if (other.dateDue != null)
				return false;
		} else if (!dateDue.equals(other.dateDue))
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (loansIdentity == null) {
			if (other.loansIdentity != null)
				return false;
		} else if (!loansIdentity.equals(other.loansIdentity))
			return false;
		return true;
	}
	
}
