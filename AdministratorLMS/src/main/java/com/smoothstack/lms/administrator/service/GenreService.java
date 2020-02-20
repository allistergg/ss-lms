package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.GenreDAO;
import com.smoothstack.lms.administrator.model.Genre;

@Service
public class GenreService {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private GenreDAO gdao;
	
	// CREATE GENRE
	public Integer saveGenre(Genre genre) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Integer genreId = gdao.addGenre(conn, genre);
			conn.commit();
			return genreId;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ GENRE BY ID
	public List<Genre> getGenreById(int genreId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return gdao.readGenreById(conn, genreId);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	// READ GENRES
	public List<Genre> readGenres() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return gdao.readGenres(conn);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// UPDATE GENRE
	public Boolean updateGenre(Genre genre) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = gdao.updateGenre(conn, genre);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE GENRE
	public Boolean deleteGenre(Integer genreId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = gdao.deleteGenre(conn, genreId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
