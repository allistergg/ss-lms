package com.ss.lms.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Genre;

@RestController
@RequestMapping("/admin/genre")
public class AdminGenreController extends AdminController<Genre>{
	
	private String ADMIN_GENRE_URI = "http://localhost:8080/lms/genres/";
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<Genre[]> getAll() {
		
		return restTemplate.getForEntity(ADMIN_GENRE_URI, Genre[].class);
	}

	@Override
	public ResponseEntity<Genre> getById(Integer id) {
		
		return restTemplate.getForEntity(ADMIN_GENRE_URI + id, Genre.class);
	}

	@Override
	public ResponseEntity<Genre> create(Genre t) {
		
		return restTemplate.postForEntity(ADMIN_GENRE_URI, t, Genre.class);
	}

	@Override
	public void update(Genre t) {
		
		restTemplate.put(ADMIN_GENRE_URI + t.getGenreId(), t);
		
	}

	@Override
	public void delete(Integer id) {
		
		restTemplate.delete(ADMIN_GENRE_URI + id);
		
	}

}
