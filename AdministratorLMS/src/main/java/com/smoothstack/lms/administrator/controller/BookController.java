package com.smoothstack.lms.administrator.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	@RequestMapping(path="/lms/books", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		try {
			Boolean success = bookService.saveBook(book);
			// Connection class not found
			if (success == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// book publisher, genres, or authors must not exist in the DB
			if (!success) { 
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
	@RequestMapping(path="/lms/books/{bookId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Book> getBookById(@PathVariable Integer bookId){
		try {
			List<Book> books = bookService.getBookById(bookId);
			// Connection class not found
			if (books == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No book with this Id
			if (books.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Book>(books.get(0), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ ALL BOOKS
	@RequestMapping(path="/lms/books", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Book>> getBooks() {
		try {
			List<Book> books = bookService.readBooks();
			// Connection class not found
			if (books == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (ONLY TITLE AND PUBLISHER)
	@RequestMapping(path="/lms/books/{bookId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBook(book);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// Book or publisher does not exist in DB
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (AUTHORS)
	@RequestMapping(path="/lms/books/{bookId}/authors", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBookAuthors(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBookAuthors(book);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// Book or authors does not exist in DB
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BOOK (GENRES)
	@RequestMapping(path="/lms/books/{bookId}/genres", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBookGenres(@RequestBody Book book, @PathVariable Integer bookId) {
		try {
			book.setBookId(bookId);
			Boolean exists = bookService.updateBookGenres(book);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// Book or genres does not exist in DB
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
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// The book does not exist in DB
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
