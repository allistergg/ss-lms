package com.ss.lms.orchestrator.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private Book book;
    private Branch branch;
    private Borrower borrower;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalDate dueDate;

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
