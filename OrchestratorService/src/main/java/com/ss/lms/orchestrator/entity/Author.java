package com.ss.lms.orchestrator.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Author implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3043267215102912826L;
	private Integer authorId;
    private String authorName;
    private List<Book> books;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
        Author author = (Author) o;
        return getAuthorId().equals(author.getAuthorId()) &&
                getAuthorName().equals(author.getAuthorName()) &&
                Objects.equals(getBooks(), author.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorId(), getAuthorName(), getBooks());
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", books=" + books +
                '}';
    }
}
