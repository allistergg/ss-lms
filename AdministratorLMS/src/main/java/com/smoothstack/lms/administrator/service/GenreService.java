package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.GenreDAO;
import com.smoothstack.lms.administrator.model.Genre;

@Component
public class GenreService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private GenreDAO gdao;
	
	// CREATE GENRE
	public Integer saveGenre(Genre genre) throws SQLException {
		try {
			Integer genreId = gdao.addGenre(genre);
			conn.commit();
			return genreId;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add genre.");
			conn.rollback();
		} 
		return null;
	}
	
	// UPDATE GENRE
	public Boolean updateGenre(Genre genre) throws SQLException {
		try {
			Boolean exists = gdao.updateGenre(genre);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update genre.");
			conn.rollback();
		} 
		return null;
	}
	
	// READ GENRES
	public List<Genre> readGenres() throws SQLException {
		try {
			return gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read genres.");
		} 
		return null;
	}
	
	// DELETE GENRE
	public Boolean deleteGenre(Integer genreId) throws SQLException {
		try {
			Boolean exists = gdao.deleteGenre(genreId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete genre.");
			conn.rollback();
		} 
		return null;
	}

	public Genre getGenreById(int genreId) throws SQLException {
		try {
			return gdao.readGenreById(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read genre by id.");
		}
		return null;
	}

}
