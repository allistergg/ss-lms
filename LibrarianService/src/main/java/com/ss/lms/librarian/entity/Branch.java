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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_library_branch")
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384935676515840660L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="branchid")
	private Integer branchId;
	@Size(min = 1, max = 45)
	@Column(name="branchname")
	private String branchName;
	@Size(min = 1, max = 45)
	@Column(name="branchaddress")
	private String branchAddress;
	@JsonIgnoreProperties({"branch"})
	@OneToMany(mappedBy="loanId.branchId", fetch = FetchType.LAZY)
	private List<Loan> loans;
	@JsonIgnoreProperties({"branch"})
	@OneToMany(mappedBy="copyId.branchId", fetch = FetchType.LAZY)
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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Branch branch = (Branch) o;
		return getBranchId().equals(branch.getBranchId()) && Objects.equals(getBranchName(), branch.getBranchName())
				&& Objects.equals(getBranchAddress(), branch.getBranchAddress()) &&
                Objects.equals(getLoans(), branch.getLoans()) &&
                Objects.equals(getCopies(), branch.getCopies());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBranchId(), getBranchName(), getBranchAddress());
	}

	@Override
	public String toString() {
		return "Branch{" + "branchId=" + branchId + ", branchName='" + branchName + '\'' + ", branchAddress='"
				+ branchAddress + '\'';
	}
}
