package com.ss.lms.orchestrator.entity;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Borrower {

    private Integer cardNo;
    @Size(min=1, max=45, message="Borrower name must be between 1 and 45 characters")
    private String name;
    @Size(min=1, max=45, message="Borrower address must be between 1 and 45 characters")
    private String address;
    @Size(min=1, max=45, message="Borrower phone must be between 1 and 45 characters")
    private String phone;
    private List<Loan> loans;

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return getCardNo().equals(borrower.getCardNo()) &&
                Objects.equals(getName(), borrower.getName()) &&
                Objects.equals(getAddress(), borrower.getAddress()) &&
                Objects.equals(getPhone(), borrower.getPhone()) &&
                Objects.equals(getLoans(), borrower.getLoans());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNo(), getName(), getAddress(), getPhone(), getLoans());
    }
}