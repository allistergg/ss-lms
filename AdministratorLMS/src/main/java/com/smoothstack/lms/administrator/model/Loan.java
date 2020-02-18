package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
	
	private static final long serialVersionUID = 5460221989751241343L;
	private Book book;
	private Branch branch;
	private Borrower borrower;
	private LocalDate dateOut;
	private LocalDate dateDue;
	private LocalDate dateIn;
	
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
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
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
		return true;
	}
	@Override
	public String toString() {
		return "Loan [book=" + book + ", branch=" + branch + ", borrower=" + borrower + ", dateOut=" + dateOut
				+ ", dateDue=" + dateDue + ", dateIn=" + dateIn + "]";
	}

}
