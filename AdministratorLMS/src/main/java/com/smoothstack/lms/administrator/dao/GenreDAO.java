package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Genre;

@Component
public class GenreDAO extends BaseDAO<Genre> {
	
	@Autowired
	private BookDAO bdao;

	public Integer addGenre(Connection conn, Genre genre) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public Boolean updateGenre(Connection conn, Genre genre) throws SQLException {
		return save(conn, "update tbl_genre set genre_name = ? where genre_id = ?", 
				new Object[]{ genre.getGenreName(), genre.getGenreId() }) > 0;
	}

	public Boolean deleteGenre(Connection conn, Integer genreId) throws SQLException {
		return save(conn, "delete from tbl_genre where genre_id = ?", new Object[] { genreId }) > 0;
	}
	
	public List<Genre> readGenres(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_genre", null);
	}
	
	public List<Genre> readGenreById(Connection conn, Integer genreId) throws SQLException {
		return read(conn, "select * from tbl_genre where genre_id = ?", new Object[] { genreId });
	}
	
	public Boolean genreExists(Connection conn, Integer genreId) throws SQLException {
		return read(conn, "select * from tbl_genre where genre_id = ?", new Object[] { genreId }).size() > 0;
	}

	@Override
	public List<Genre> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			g.setBooks(bdao.readFirstLevel(conn, "select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book_genres where genre_id = ?)", new Object[] { g.getGenreId() }));
			genres.add(g);
		}
		return genres;
	}

	@Override
	List<Genre> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}
	

}
