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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {


	private static final long serialVersionUID = 2422412941926519197L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private Integer bookId;

	@Column(nullable = false, length = 20, name = "title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "pubId", insertable = false, updatable = false)
	private Publisher publisher;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_authors", joinColumns = @JoinColumn(name = "bookId", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "authorId", nullable = false, updatable = false))
	@JsonIgnoreProperties({ "books" })
	private List<Author> authors;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_genres", joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@JsonIgnoreProperties("books")
	private List<Genre> genres;

	@OneToMany(mappedBy = "bookcopyid.bookId", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("book")
	private List<BookCopies> copies;

	@OneToMany(mappedBy = "borrowerid.branchId", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("book")
	private List<Loans> loans;

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * @return the copies
	 */
	public List<BookCopies> getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(List<BookCopies> copies) {
		this.copies = copies;
	}

	/**
	 * @return the loans
	 */
	public List<Loans> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loans> loans) {
		this.loans = loans;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, bookId, copies, genres, loans, publisher, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(copies, other.copies) && Objects.equals(genres, other.genres)
				&& Objects.equals(loans, other.loans) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", publisher=" + publisher + ", authors=" + authors
				+ ", genres=" + genres + ", copies=" + copies + ", loans=" + loans + "]";
	}

	/**
	 * @param bookId
	 * @param title
	 * @param publisher
	 * @param authors
	 * @param genres
	 * @param copies
	 * @param loans
	 */
	public Book(Integer bookId, String title, Publisher publisher, List<Author> authors, List<Genre> genres,
			List<BookCopies> copies, List<Loans> loans) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.publisher = publisher;
		this.authors = authors;
		this.genres = genres;
		this.copies = copies;
		this.loans = loans;
	}

	/**
	 * 
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

}
