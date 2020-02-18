package com.ss.lms.librarian.entity;

import java.util.Objects;

public class Copy {

    private Book book;
    private Branch branch;
    private Integer noOfCopies;

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

    public Integer getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Integer noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return getBook().equals(copy.getBook()) &&
                getBranch().equals(copy.getBranch()) &&
                Objects.equals(getNoOfCopies(), copy.getNoOfCopies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getBranch(), getNoOfCopies());
    }

    @Override
    public String toString() {
        return "Copy{" +
                "book=" + book +
                ", branch=" + branch +
                ", noOfCopies=" + noOfCopies +
                '}';
    }
}




