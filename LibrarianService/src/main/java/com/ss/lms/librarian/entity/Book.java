package com.ss.lms.librarian.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "authors", "genres", "copies", "loans"})
public class Book implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7729047382432003306L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookid")
	private Integer bookId;
	@Column(name="title")
    private String title;
	@ManyToOne
	@JoinColumn(name="pubid", insertable = false, updatable = false)
    private Publisher publisher;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="tbl_book_authors", joinColumns = @JoinColumn(name="bookid"),
    inverseJoinColumns = @JoinColumn(name="authorid"))
    private Set<Author> authors;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="tbl_book_genres", joinColumns = @JoinColumn(name="bookid"),
    inverseJoinColumns = @JoinColumn(name="genreId"))
    private Set<Genre> genres;
    @OneToMany(mappedBy = "copyId.bookId", fetch = FetchType.LAZY)
    private List<Copy> copies;
    @OneToMany(mappedBy = "loanId.bookId", fetch = FetchType.LAZY)
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

   
    


    public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public List<Copy> getCopies() {
		return copies;
	}

	public void setCopies(List<Copy> copies) {
		this.copies = copies;
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
