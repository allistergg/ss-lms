package com.smoothstack.lms.administrator.controller;

import java.net.URI;
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
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	// CREATE BOOK
	@RequestMapping(path="/lms/books", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		Book savedBook = bookService.saveBook(book);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getBookId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// READ BOOK BY ID
	@RequestMapping(path="/lms/books/{bookId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Book> getBookById(@PathVariable Integer bookId){
		Result<Book> rs = bookService.getBookById(bookId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(rs.getResult(), HttpStatus.OK);
	}
	
	// READ ALL BOOKS
	@RequestMapping(path="/lms/books", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.readBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	// UPDATE BOOK
	@RequestMapping(path="/lms/books/{bookId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable Integer bookId) {
		book.setBookId(bookId);
		Result<Void> rs = bookService.updateBook(book);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE BOOK
	@RequestMapping(path = "/lms/books/{bookId}", method=RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAuthor(@PathVariable Integer bookId) {
		Result<Void> rs = bookService.deleteBook(bookId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
