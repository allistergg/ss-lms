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

import com.smoothstack.lms.administrator.model.Publisher;
import com.smoothstack.lms.administrator.service.PublisherService;

@RestController
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	// CREATE PUBLISHER
	@RequestMapping(path="/lms/publishers", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addPublisher(@RequestBody Publisher publisher) {
		try {
			Integer publisherId = publisherService.savePublisher(publisher);
			// Connection class not found
			if (publisherId == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(publisherId)
                    .toUri();
			return ResponseEntity.created(location).build();
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ PUBLISHER BY ID
	@RequestMapping(path = "/lms/publishers/{publisherId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer publisherId)  {
		try {
			List<Publisher> publishers = publisherService.getPublisherById(publisherId);
			// Connection class not found
			if (publishers == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No publisher with this Id
			if (publishers.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Publisher>(publishers.get(0), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ PUBLISHERS
	@RequestMapping(path = "/lms/publishers", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Publisher>> getPublishers() {
		try {
			List<Publisher> publishers = publisherService.readPublishers();
			// Connection class not found
			if (publishers == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE PUBLISHER
	@RequestMapping(path="/lms/publishers/{publisherId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updatePublisher(@RequestBody Publisher publisher, @PathVariable Integer publisherId) {
		try {
			publisher.setPublisherId(publisherId);
			Boolean exists = publisherService.updatePublisher(publisher);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No publisher with this Id
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// DELETE PUBLISHER
	@RequestMapping(path = "/lms/publishers/{publisherId}", method=RequestMethod.DELETE)
	public ResponseEntity<Publisher> deleteAuthor(@PathVariable Integer publisherId) {
		try {
			Boolean exists = publisherService.deletePublisher(publisherId);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No publisher with this Id
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
