package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_genre")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "books", })
public class Genre implements Serializable {

	private static final long serialVersionUID = -4512825603598885141L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private Integer genreId;

	@Column(name = "genre_name")
	private String genreName;

	@ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("genres")
	private Set<Book> books;

	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * @return the books
	 */
	public Set<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(books, genreId, genreName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return Objects.equals(books, other.books) && Objects.equals(genreId, other.genreId)
				&& Objects.equals(genreName, other.genreName);
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + ", books=" + books + "]";
	}

	/**
	 * @param genreId
	 * @param genreName
	 * @param books
	 */
	public Genre(Integer genreId, String genreName, Set<Book> books) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
		this.books = books;
	}

	/**
	 * 
	 */
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

}
