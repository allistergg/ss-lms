package com.ss.lms.orchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Publisher;

@RestController
@RequestMapping("/admin/publisher")
public class AdminPublisherController extends AdminController<Publisher> {
	
	@Autowired
	RestTemplate restTemplate;
	
	private String ADMIN_PUBLISHER_URI = "http://admin-service/lms/publishers/";

	@Override
	public ResponseEntity<Publisher[]> getAll() {
		
		return restTemplate.getForEntity(ADMIN_PUBLISHER_URI, Publisher[].class);
	}

	@Override
	public ResponseEntity<Publisher> getById(@PathVariable Integer id) {
		
		return restTemplate.getForEntity(ADMIN_PUBLISHER_URI + id, Publisher.class);
	}

	@Override
	public ResponseEntity<Publisher> create(@Valid @RequestBody Publisher t) {
	
		return restTemplate.postForEntity(ADMIN_PUBLISHER_URI,  t,  Publisher.class);
	}

	@Override
	public void update(@Valid @RequestBody Publisher t) {
		restTemplate.put(ADMIN_PUBLISHER_URI + t.getPublisherId(), t);
		
	}

	@Override
	public void delete(@PathVariable Integer id) {
		restTemplate.delete(ADMIN_PUBLISHER_URI + id);
		
	}

}
