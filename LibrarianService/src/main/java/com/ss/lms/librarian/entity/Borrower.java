package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_borrower")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Borrower  implements Serializable{

   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8702362109641003649L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="cardno")
	private Integer cardNo;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @OneToMany(mappedBy = "loanId.cardNo", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("borrower")
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
                Objects.equals(getPhone(), borrower.getPhone());
 
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNo(), getName(), getAddress(), getPhone());
    }
}