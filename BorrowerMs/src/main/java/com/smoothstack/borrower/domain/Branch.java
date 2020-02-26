package com.smoothstack.borrower.domain;

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
@Table(name = "tbl_library_branch")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Branch implements Serializable {
	
	private static final long serialVersionUID = -2766782311170232024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "branchName")
	private String branchName;
	
	@Column(name = "branchAddress")
	private String branchAddress;
	
	@JsonIgnoreProperties({"branch"})
	@OneToMany(mappedBy="borrowerid.branchId", fetch = FetchType.LAZY)
	private List<Loans> loans;
	
	@JsonIgnoreProperties({"branch"})
	@OneToMany(mappedBy="bookcopyid.branchId", fetch = FetchType.LAZY)
    private List<BookCopies> bookcopies;

	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchAddress, branchId, branchName);
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
		return Objects.equals(branchAddress, other.branchAddress) && Objects.equals(branchId, other.branchId)
				&& Objects.equals(branchName, other.branchName);
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchAddress=" + branchAddress + "]";
	}

	/**
	 * @param branchId
	 * @param branchName
	 * @param branchAddress
	 */
	public Branch(Integer branchId, String branchName, String branchAddress) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	/**
	 * 
	 */
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	

}
