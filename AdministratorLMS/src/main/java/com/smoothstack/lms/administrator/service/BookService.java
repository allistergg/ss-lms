package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.dao.BookDAO;
import com.smoothstack.lms.administrator.dao.GenreDAO;
import com.smoothstack.lms.administrator.dao.PublisherDAO;
import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Genre;

@Service
public class BookService {
	
	@Autowired
	private ConnectionUtil connUtil;
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
		try (Connection conn = connUtil.getConnection()) {
			try {
				// check if the publisher exists
				if (book.getPublisher() != null && !pdao.publisherExists(conn, book.getPublisher().getPublisherId())) {
					return false;
				} 
				// check if the genres actually exist
				if (book.getGenres() == null) { book.setGenres(new ArrayList<Genre>()); }
				for (Genre g: book.getGenres()) {
					if (!gdao.genreExists(conn, g.getGenreId())) {
						return false;
					}
				}
				// check if the authors actually exist
				if (book.getAuthors() == null) { book.setAuthors(new ArrayList<Author>()); }
				for(Author a: book.getAuthors()) {
					if (!adao.authorExists(conn, a.getAuthorId())) {
						return false;
					}
				}
				Integer bookId = bdao.addBookReturnPk(conn, book); // save new book and get primary key
				book.setBookId(bookId);
				// now add the books and genres
				for (Genre g: book.getGenres()) {
					bdao.insertBookGenres(conn, book, g);
				}
				for (Author a: book.getAuthors()) {
					bdao.insertBookAuthors(conn, book, a);
				}
				conn.commit();
				return true;
			} catch(SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ BOOK
	public List<Book> getBookById(Integer bookId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return bdao.readBookById(conn, bookId);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	// READ BOOKS
	public List<Book> readBooks() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return bdao.readBooks(conn);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// UPDATE BOOK
	public Boolean updateBook(Book book) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			// check if publisher exists before updating database
			if (book.getPublisher() != null && !pdao.publisherExists(conn, book.getPublisher().getPublisherId())) {
				return false;
			}
			Boolean exists = bdao.updateBook(conn, book);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE BOOK
	public Boolean deleteBook(Integer bookId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = bdao.deleteBook(conn, bookId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// UPDATE BOOK AUTHORS
	public Boolean updateBookAuthors(Book book) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			try {
				if (!bdao.bookExists(conn, book.getBookId())) {
					return false;
				}
				// need to make sure all the authors exist first
				if (book.getAuthors() != null) { 
					for (Author a: book.getAuthors()) {
						if (!adao.authorExists(conn, a.getAuthorId())) {
							return false;
						}
					}
				}
				// delete old authors
				bdao.deleteBookAuthors(conn, book);
				// add in the new authors
				if (book.getAuthors() != null) {
					for (Author author: book.getAuthors()) {
						bdao.insertBookAuthors(conn, book, author);
					}
				}
				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	// UPDATE BOOK GENRES
	public Boolean updateBookGenres(Book book) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			try {
				if (!bdao.bookExists(conn, book.getBookId())) {
					return false;
				}
				// need to make sure all the genres exist first
				if (book.getGenres() == null) { book.setGenres(new ArrayList<Genre>()); }
				for (Genre g: book.getGenres()) {
					if (!gdao.genreExists(conn, g.getGenreId())) {
						return false;
					}
				}
				bdao.deleteBookGenres(conn, book);
				for (Genre g: book.getGenres()) {
					bdao.insertBookGenres(conn, book, g);
				}
				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
