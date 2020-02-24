package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
@Table(name = "tbl_genre")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Genre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6203033775108013577L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genreId;
	private String genreName;
	@ManyToMany(mappedBy="genres", fetch = FetchType.LAZY)
	private Set<Book> books;

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}


	
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Genre genre = (Genre) o;
		return getGenreId().equals(genre.getGenreId()) && Objects.equals(getGenreName(), genre.getGenreName())
				&& Objects.equals(getBooks(), genre.getBooks());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getGenreId(), getGenreName(), getBooks());
	}
}
