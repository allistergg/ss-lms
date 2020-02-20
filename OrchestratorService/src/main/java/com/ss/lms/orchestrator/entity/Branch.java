package com.ss.lms.orchestrator.entity;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Branch {


	private Integer branchId;
	@Size(min=1,max=45, message="Branch name must be between 1 and 45 characters")
	private String branchName;
	@Size(min=1,max=45, message="Branch address must be between 1 and 45 characters")
	private String branchAddress;
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
                Objects.equals(getCopies(), branch.getCopies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranchId(), getBranchName(), getBranchAddress(), getCopies());
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", copies=" + copies +
                '}';
    }
}
