package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity
@Table(name="tbl_author")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Author implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3043267215102912826L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authorid")
	private Integer authorId;
	@Column(name="authorname")
    private String authorName;
    @ManyToMany(mappedBy="authors", fetch = FetchType.LAZY)
    private Set<Book> books;

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

   

    public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
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
