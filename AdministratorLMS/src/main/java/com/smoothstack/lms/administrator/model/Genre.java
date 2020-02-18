package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Genre implements Serializable {
	
	private static final long serialVersionUID = -7816443721846069003L;
	private Integer genreId;
	private String genreName;
	private List<Book> books;
	
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
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
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

	
}
