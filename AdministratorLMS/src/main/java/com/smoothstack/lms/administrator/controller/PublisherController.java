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

import com.smoothstack.lms.administrator.model.Publisher;
import com.smoothstack.lms.administrator.service.PublisherService;
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	// CREATE PUBLISHER
	@RequestMapping(path="/lms/publishers", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addPublisher(@RequestBody Publisher publisher) {
		Publisher savedPublisher = publisherService.savePublisher(publisher);
	
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPublisher.getPublisherId())
                .toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	// READ PUBLISHER BY ID
	@RequestMapping(path = "/lms/publishers/{publisherId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer publisherId)  {
		Result<Publisher> rs = publisherService.getPublisherById(publisherId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Publisher>(rs.getResult(), HttpStatus.OK);
	}
	
	// READ PUBLISHERS
	@RequestMapping(path = "/lms/publishers", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Publisher>> getPublishers() {
		List<Publisher> publishers = publisherService.readPublishers();
		return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
	}
	
	// UPDATE PUBLISHER
	@RequestMapping(path="/lms/publishers/{publisherId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updatePublisher(@RequestBody Publisher publisher, @PathVariable Integer publisherId) {
		publisher.setPublisherId(publisherId);
		Result<Void> rs = publisherService.updatePublisher(publisher);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE PUBLISHER
	@RequestMapping(path = "/lms/publishers/{publisherId}", method=RequestMethod.DELETE)
	public ResponseEntity<Publisher> deleteAuthor(@PathVariable Integer publisherId) {
		Result<Void> rs = publisherService.deletePublisher(publisherId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
