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

import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.service.AuthorService;

@RestController
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	// CREATE AUTHOR
	@RequestMapping(path="/lms/authors", method=RequestMethod.POST, 
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addAuthor(@RequestBody Author author) {
		try {
			Integer authorId = authorService.saveAuthor(author);
			// Connection class not found
			if (authorId == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(authorId)
                    .toUri();
			return ResponseEntity.created(location).build();
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ AUTHOR BY ID
	@RequestMapping(path = "/lms/authors/{authorId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Author> getAuthorById(@PathVariable Integer authorId) {
		try {
			List<Author> authors = authorService.getAuthorById(authorId);
			// Connection class not found
			if (authors == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No authors with that Id
			if (authors.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Author>(authors.get(0), HttpStatus.OK);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ AUTHORS
	@RequestMapping(path = "/lms/authors", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Author>> getAuthors() {
		try {
			List<Author> authors = authorService.readAuthors();
			// Connection class not found
			if (authors == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE AUTHOR
	@RequestMapping(path="/lms/authors/{authorId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateAuthor(@RequestBody Author author, @PathVariable Integer authorId) {
		try {
			author.setAuthorId(authorId);
			Boolean exists = authorService.updateAuthor(author);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// The author does not exist
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// DELETE AUTHOR
	@RequestMapping(path = "/lms/authors/{authorId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAuthor(@PathVariable Integer authorId) {
		try {
			Boolean exists = authorService.deleteAuthor(authorId);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// The author does not exist
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}


}
