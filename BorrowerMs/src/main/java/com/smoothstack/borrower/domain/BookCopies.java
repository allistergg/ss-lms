package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@IdClass(BookCopyID.class)
@Table(name = "tbl_book_copies")
public class BookCopies implements Serializable {
	private static final long serialVersionUID = 3579197543131527182L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "BookCopies" })
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "BookCopies" })
	private Branch branch;

	@Column(name = "noOfCopies")
	private Integer noOfCopies;
	
	@EmbeddedId
	private BookCopyID bookcopyid;
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return the bookcopyid
	 */
	public BookCopyID getBookcopyid() {
		return bookcopyid;
	}

	/**
	 * @param bookcopyid the bookcopyid to set
	 */
	public void setBookcopyid(BookCopyID bookcopyid) {
		this.bookcopyid = bookcopyid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, branch, noOfCopies);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopies other = (BookCopies) obj;
		return Objects.equals(book, other.book) && Objects.equals(branch, other.branch)
				&& Objects.equals(noOfCopies, other.noOfCopies);
	}

	@Override
	public String toString() {
		return "BookCopies [book=" + book + ", branch=" + branch + ", noOfCopies=" + noOfCopies + "]";
	}

	/**
	 * @param book
	 * @param branch
	 * @param noOfCopies
	 */
	public BookCopies(Book book, Branch branch, Integer noOfCopies) {
		super();
		this.book = book;
		this.branch = branch;
		this.noOfCopies = noOfCopies;
	}

	/**
	 * 
	 */
	public BookCopies() {
		super();
		// TODO Auto-generated constructor stub
	}

}
