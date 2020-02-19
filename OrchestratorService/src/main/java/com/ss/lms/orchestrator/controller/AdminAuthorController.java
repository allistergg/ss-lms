package com.ss.lms.orchestrator.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.orchestrator.entity.Author;

@RestController
@RequestMapping("/admin/author")
public class AdminAuthorController extends AdminController<Author> {
	
	String ADMIN_AUTHOR_URI = "http://localhost:8080/lms/authors/";

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
		// TODO Auto-generated method stub
		restTemplate.put(ADMIN_AUTHOR_URI + t.getAuthorId(), t);
		
	}

	@Override
	public void delete(@PathVariable Integer id) {
		restTemplate.delete(ADMIN_AUTHOR_URI + id);
		
	}
}
