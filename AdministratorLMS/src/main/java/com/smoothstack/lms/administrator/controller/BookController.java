package com.smoothstack.lms.administrator.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	// CREATE BOOK
	@RequestMapping(path="/lms/books", method=RequestMethod.POST)
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		try {
			Boolean success = bookService.saveBook(book);
			if (!success) {
				// book publisher, genres, or authors must not exist in the DB
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Integer bookId = book.getBookId();
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(bookId)
                    .toUri();
			return ResponseEntity.created(location).build();
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BOOK BY ID
	@RequestMapping(path = "/lms/books/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer bookId){
		try {
			Book book = bookService.getBookById(bookId);
			if (book == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ ALL BOOKS
	@RequestMapping(path = "/lms/books")
	public ResponseEntity<List<Book>> getBooks() {
		try {
			List<Book> books = bookService.readBooks();
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (ONLY TITLE AND PUBLISHER)
	@RequestMapping(path="/lms/books/{bookId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBook(book);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (AUTHORS)
	@RequestMapping(path="/lms/books/{bookId}/authors", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBookAuthors(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBookAuthors(book);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (GENRES)
	@RequestMapping(path="/lms/books/{bookId}/genres", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBookGenres(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBookGenres(book);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// DELETE BOOK
	@RequestMapping(path = "/lms/books/{bookId}", method=RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAuthor(@PathVariable Integer bookId) {
		try {
			Boolean exists = bookService.deleteBook(bookId);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
