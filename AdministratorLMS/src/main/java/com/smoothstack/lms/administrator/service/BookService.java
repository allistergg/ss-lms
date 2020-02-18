package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.dao.BookDAO;
import com.smoothstack.lms.administrator.dao.GenreDAO;
import com.smoothstack.lms.administrator.dao.PublisherDAO;
import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Genre;

@Component
public class BookService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private BookDAO bdao;
	@Autowired
	private AuthorDAO adao;
	@Autowired
	private GenreDAO gdao;
	@Autowired
	private PublisherDAO pdao;
	
	// CREATE BOOK
	public Boolean saveBook(Book book) throws SQLException {
		try {
			// check if the publisher exists
			if (book.getPublisher() != null && !pdao.publisherExists(book.getPublisher().getPublisherId())) {
				return false;
			} 
			// check if the genres actually exist
			if (book.getGenres() == null) { book.setGenres(new ArrayList<Genre>()); }
			for (Genre g: book.getGenres()) {
				if (!gdao.genreExists(g.getGenreId())) {
					return false;
				}
			}
			// check if the authors actually exist
			if (book.getAuthors() == null) { book.setAuthors(new ArrayList<Author>()); }
			for(Author a: book.getAuthors()) {
				if (!adao.authorExists(a.getAuthorId())) {
					return false;
				}
			}
			Integer bookId = bdao.addBookReturnPk(book); // save new book and get primary key
			book.setBookId(bookId);
			// now add the books and genres
			for (Genre g: book.getGenres()) {
				bdao.insertBookGenres(book, g);
			}
			for (Author a: book.getAuthors()) {
				bdao.insertBookAuthors(book, a);
			}
			conn.commit();
			return true;

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add book.");
			conn.rollback();
		} 
		return null;
	}
	
	// READ BOOKS
	public List<Book> readBooks() throws SQLException{
		try {
			return bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read books.");
		} 
		return null;
	}
	
	// UPDATE BOOK
	public Boolean updateBook(Book book) throws SQLException{
		try {
			// check if publisher exists before updating database
			if (book.getPublisher() != null && !pdao.publisherExists(book.getPublisher().getPublisherId())) {
				return false;
			}
			Boolean exists = bdao.updateBook(book);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update book.");
			conn.rollback();
		} 
		return null;
	}
	
	// DELETE BOOK
	public Boolean deleteBook(Integer bookId) throws SQLException {
		try {
			Boolean exists = bdao.deleteBook(bookId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete book.");
			conn.rollback();
		} 
		return null;
	}
	
	// UPDATE BOOK AUTHORS
	public Boolean updateBookAuthors(Book book) throws SQLException {
		try {
			if (!bdao.bookExists(book.getBookId())) {
				return false;
			}
			// need to make sure all the authors exist first
			if (book.getAuthors() == null) { book.setAuthors(new ArrayList<Author>()); }
			for (Author a: book.getAuthors()) {
				if (!adao.authorExists(a.getAuthorId())) {
					return false;
				}
			}
			bdao.deleteBookAuthors(book);
			for (Author author: book.getAuthors()) {
				bdao.insertBookAuthors(book, author);
			}
			conn.commit();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update book authors.");
			conn.rollback();
		}
		return null;
	}
	
	// UPDATE BOOK GENRES
	public Boolean updateBookGenres(Book book) throws SQLException {
		try {
			if (!bdao.bookExists(book.getBookId())) {
				return false;
			}
			// need to make sure all the genres exist first
			if (book.getGenres() == null) { book.setGenres(new ArrayList<Genre>()); }
			for (Genre g: book.getGenres()) {
				if (!gdao.genreExists(g.getGenreId())) {
					return false;
				}
			}
			bdao.deleteBookGenres(book);
			for (Genre g: book.getGenres()) {
				bdao.insertBookGenres(book, g);
			}
			conn.commit();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update book genres.");
			conn.rollback();
		} 
		return null;
	}

	// READ BOOK
	public Book getBookById(Integer bookId) throws SQLException {
		try {
			return bdao.readBookById(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read book by id.");
		}
		return null;
	}

}
