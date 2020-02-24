package com.smoothstack.lms.administrator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.GenreDAO;
import com.smoothstack.lms.administrator.model.Genre;

@Service
public class GenreService {
	
	@Autowired
	private GenreDAO gdao;
	
	// CREATE GENRE
	public Genre saveGenre(Genre genre) {
		return gdao.save(genre);
	}
	
	// READ GENRE BY ID
	public Result<Genre> getGenreById(int genreId) {
		Result<Genre> rs = new Result<Genre>();
		if (gdao.existsById(genreId)) {
			rs.setResult(gdao.findById(genreId).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// READ GENRES
	public List<Genre> readGenres() {
		return gdao.findAll();
	}
	
	// UPDATE GENRE
	public Result<Void> updateGenre(Genre genre) {
		Result<Void> rs = new Result<Void>();
		// Don't want to create a new genre
		if (gdao.existsById(genre.getGenreId())) {
			gdao.save(genre);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE GENRE
	public Result<Void> deleteGenre(Integer genreId) {
		Result<Void> rs = new Result<Void>();
		if (gdao.existsById(genreId)) {
			gdao.deleteById(genreId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
