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

import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.service.AuthorService;
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	// CREATE AUTHOR
	@RequestMapping(path="/lms/authors", method=RequestMethod.POST, 
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addAuthor(@RequestBody Author author) {
		Author savedAuthor = authorService.saveAuthor(author);
		
		// location header
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getAuthorId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// READ AUTHOR BY ID
	@RequestMapping(path = "/lms/authors/{authorId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Author> getAuthorById(@PathVariable Integer authorId) {
		Result<Author> rs = authorService.getAuthorById(authorId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Author>(rs.getResult(), HttpStatus.OK);
	}
	
	// READ AUTHORS
	@RequestMapping(path = "/lms/authors", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Author>> getAuthors() {
		List<Author> authors = authorService.readAuthors();
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}
	
	// UPDATE AUTHOR
	@RequestMapping(path="/lms/authors/{authorId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateAuthor(@RequestBody Author author, @PathVariable Integer authorId) {
		author.setAuthorId(authorId);
		Result<Void> rs = authorService.updateAuthor(author);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE AUTHOR
	@RequestMapping(path = "/lms/authors/{authorId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAuthor(@PathVariable Integer authorId) {
		Result<Void> rs = authorService.deleteAuthor(authorId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
