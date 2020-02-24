package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_book_loans")
public class Loan implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6923200241363934635L;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookid", insertable=false, updatable = false)
	private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branchid", insertable=false, updatable=false)
    private Branch branch;
    @JsonIgnoreProperties("loans")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cardno", insertable=false, updatable=false)
    private Borrower borrower;
    @Column(name="datein")
    private LocalDate dateIn;
    @Column(name="dateout")
    private LocalDate dateOut;
    @Column(name="duedate")
    private LocalDate dueDate;
    
    @EmbeddedId
    private LoanId loanId;

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

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return getBook().equals(loan.getBook()) &&
                getBranch().equals(loan.getBranch()) &&
                getBorrower().equals(loan.getBorrower()) &&
                Objects.equals(getDateIn(), loan.getDateIn()) &&
                Objects.equals(getDateOut(), loan.getDateOut()) &&
                Objects.equals(getDueDate(), loan.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getBranch(), getBorrower(), getDateIn(), getDateOut(), getDueDate());
    }
}
