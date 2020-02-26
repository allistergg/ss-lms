package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SQLDelete(sql = "UPDATE tbl_book SET deleted = b'1' WHERE bookId = ?")
@Where(clause = "deleted = false")
@Table(name = "tbl_book")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Book implements Serializable {
	
	private static final long serialVersionUID = 8433731159129230918L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookid")
	private Integer bookId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "deleted")
	private Boolean deleted;
	
	@ManyToOne
    @JoinColumn(name="pubid")
	@JsonIgnoreProperties("books")
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_authors", joinColumns = {
			@JoinColumn(name = "bookid", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "authorid",
			nullable = false, updatable = false) })
	@JsonIgnoreProperties("books")
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_genres", joinColumns = {
			@JoinColumn(name = "bookid", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "genre_id",
			nullable = false, updatable = false) })
	@JsonIgnoreProperties("books")
	private List<Genre> genres;
	
	@OneToMany(mappedBy = "copiesIdentity.bookId")
	@JsonIgnoreProperties("book")
	private List<Copies> copies;
	
	@OneToMany(mappedBy = "loansIdentity.bookId")
	@JsonIgnoreProperties("book")
	private List<Loan> loans;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public List<Copies> getCopies() {
		return copies;
	}
	public void setCopies(List<Copies> copies) {
		this.copies = copies;
	}
	public List<Loan> getLoans() {
		return loans;
	}
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((copies == null) ? 0 : copies.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((loans == null) ? 0 : loans.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (copies == null) {
			if (other.copies != null)
				return false;
		} else if (!copies.equals(other.copies))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (loans == null) {
			if (other.loans != null)
				return false;
		} else if (!loans.equals(other.loans))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@PreRemove
	public void deleteBook() {
		this.deleted = true;
	}
	
}
