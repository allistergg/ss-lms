package com.ss.lms.orchestrator.entity;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Size;

public class Genre {

        private Integer genreId;
        @Size(min=1, max=45, message="Genre name must be between 1 and 45 characters")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return getGenreId().equals(genre.getGenreId()) &&
                Objects.equals(getGenreName(), genre.getGenreName()) &&
                Objects.equals(getBooks(), genre.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGenreId(), getGenreName(), getBooks());
    }
}
