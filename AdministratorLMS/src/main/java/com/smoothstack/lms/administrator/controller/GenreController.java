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

import com.smoothstack.lms.administrator.model.Genre;
import com.smoothstack.lms.administrator.service.GenreService;
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	// CREATE GENRE
	@RequestMapping(path="/lms/genres", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addGenre(@RequestBody Genre genre) {
		Genre savedGenre = genreService.saveGenre(genre);

		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGenre.getGenreId())
                .toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	// READ GENRE BY ID
	@RequestMapping(path = "/lms/genres/{genreId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Genre> getGenreById(@PathVariable Integer genreId)  {
		Result<Genre> rs = genreService.getGenreById(genreId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Genre>(rs.getResult(), HttpStatus.OK);
	}
	
	// READ GENRES
	@RequestMapping(path = "/lms/genres", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Genre>> getGenres() {
		List<Genre> genres = genreService.readGenres();
		return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
	}
	
	// UPDATE GENRE
	@RequestMapping(path="/lms/genres/{genreId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateGenre(@RequestBody Genre genre, @PathVariable Integer genreId) {
		genre.setGenreId(genreId);
		Result<Void> rs = genreService.updateGenre(genre);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE GENRE
	@RequestMapping(path = "/lms/genres/{genreId}", method=RequestMethod.DELETE)
	public ResponseEntity<Genre> deleteAuthor(@PathVariable Integer genreId) {
		Result<Void> rs = genreService.deleteGenre(genreId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
