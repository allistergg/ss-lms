package com.ss.lms.orchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Author;

@RestController
@RequestMapping("/admin/author")
public class AdminAuthorController extends AdminController<Author> {
	
	private final String ADMIN_AUTHOR_URI = "http://admin-service/lms/authors/";

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<Author[]> getAll() {
		
		return restTemplate.getForEntity(ADMIN_AUTHOR_URI, Author[].class);

	}

	@Override
	public ResponseEntity<Author> getById(@PathVariable Integer id) {
	
			return restTemplate.getForEntity(ADMIN_AUTHOR_URI+id, Author.class);

	}

	@Override
	public ResponseEntity<Author> create(@Valid @RequestBody Author t) {
		return restTemplate.postForEntity(ADMIN_AUTHOR_URI, t, Author.class);
	}

	@Override
	public void update(@Valid @RequestBody Author t) {
	
		restTemplate.put(ADMIN_AUTHOR_URI + t.getAuthorId(), t);
		
	}

	@Override
	public void delete(@PathVariable Integer id) {
		restTemplate.delete(ADMIN_AUTHOR_URI + id);
		
	}
}
