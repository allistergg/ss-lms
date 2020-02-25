package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_book_copies")
public class Copy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4706183973407504388L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bookid", insertable = false, updatable = false)
	@JsonIgnoreProperties({"copies"})
	private Book book;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="branchid", insertable = false, updatable = false)
	@JsonIgnoreProperties({"copies"})
	private Branch branch;
	@Column(name="noofcopies")
	private Integer noOfCopies;
	@EmbeddedId
	private CopyId copyId;
	

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

	public CopyId getCopyId() {
		return copyId;
	}

	public void setCopyId(CopyId copyId) {
		this.copyId = copyId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, branch, copyId, noOfCopies);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copy other = (Copy) obj;
		return Objects.equals(book, other.book) && Objects.equals(branch, other.branch)
				&& Objects.equals(copyId, other.copyId) && Objects.equals(noOfCopies, other.noOfCopies);
	}

	

	
    
    
  


}




