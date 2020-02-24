package com.ss.lms.orchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Genre;

@RestController
@RequestMapping("/admin/genre")
public class AdminGenreController extends AdminController<Genre>{
	
	private final String ADMIN_GENRE_URI = "http://admin-service/lms/genres/";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Genre[]> getAll() {
		
		return restTemplate.getForEntity(ADMIN_GENRE_URI, Genre[].class);
	}

	@Override
	public ResponseEntity<Genre> getById(@PathVariable Integer id) {
		
		return restTemplate.getForEntity(ADMIN_GENRE_URI + id, Genre.class);
	}

	@Override
	public ResponseEntity<Genre> create(@Valid @RequestBody Genre t) {
		
		return restTemplate.postForEntity(ADMIN_GENRE_URI, t, Genre.class);
	}

	@Override
	public void update(@Valid @RequestBody Genre t) {
		
		restTemplate.put(ADMIN_GENRE_URI + t.getGenreId(), t);
		
	}

	@Override
	public void delete(@PathVariable Integer id) {
		
		restTemplate.delete(ADMIN_GENRE_URI + id);
		
	}

}
