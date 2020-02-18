package com.ss.lms.orchestrator.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7729047382432003306L;
	private Integer bookId;
    private String title;
    private Publisher publisher;
    private List<Author> authors;
    private List<Genre> genres;


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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookId().equals(book.getBookId()) &&
                getTitle().equals(book.getTitle()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(getGenres(), book.getGenres());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getTitle(), getPublisher(), getAuthors(), getGenres());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
