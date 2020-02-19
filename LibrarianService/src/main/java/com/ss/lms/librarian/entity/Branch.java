package com.ss.lms.librarian.entity;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Branch {


	private Integer branchId;
	 @Size(min=1, max=45)
	private String branchName;
	 @Size(min=1,max=45)
	private String branchAddress;
    private List<Loan> loans;
    private List<Copy> copies;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return getBranchId().equals(branch.getBranchId()) &&
                Objects.equals(getBranchName(), branch.getBranchName()) &&
                Objects.equals(getBranchAddress(), branch.getBranchAddress()) &&
                Objects.equals(getLoans(), branch.getLoans()) &&
                Objects.equals(getCopies(), branch.getCopies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranchId(), getBranchName(), getBranchAddress(), getLoans(), getCopies());
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", loans=" + loans +
                ", copies=" + copies +
                '}';
    }
}
