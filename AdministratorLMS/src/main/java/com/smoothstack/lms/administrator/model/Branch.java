package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "tbl_library_branch")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Branch implements Serializable {
	
	private static final long serialVersionUID = 1280765973528383816L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchid")
	private Integer branchId;
	
	@Column(name = "branchname")
	private String branchName;
	
	@Column(name = "branchaddress")
	private String branchAddress;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loansIdentity.branchId", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("branch")
	private List<Loan> loans;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "copiesIdentity.branchId", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("branch")
	private List<Copies> copies;
	
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
	public List<Copies> getCopies() {
		return copies;
	}
	public void setCopies(List<Copies> copies) {
		this.copies = copies;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((copies == null) ? 0 : copies.hashCode());
		result = prime * result + ((loans == null) ? 0 : loans.hashCode());
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
		Branch other = (Branch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (copies == null) {
			if (other.copies != null)
				return false;
		} else if (!copies.equals(other.copies))
			return false;
		if (loans == null) {
			if (other.loans != null)
				return false;
		} else if (!loans.equals(other.loans))
			return false;
		return true;
	}
	
}
