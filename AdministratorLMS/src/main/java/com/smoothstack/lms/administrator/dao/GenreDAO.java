package com.smoothstack.lms.administrator.dao;

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

	public Integer addGenre(Genre genre) throws SQLException, ClassNotFoundException {
		return saveReturnPk("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public Boolean updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		return save("update tbl_genre set genre_name = ? where genre_id = ?", 
				new Object[]{ genre.getGenreName(), genre.getGenreId() }) > 0;
	}

	public Boolean deleteGenre(Integer genreId) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_genre where genre_id = ?", new Object[] { genreId }) > 0;
	}
	
	public List<Genre> readGenres() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_genre", null);
	}
	
	public Genre readGenreById(Integer genreId) throws SQLException, ClassNotFoundException {
		List<Genre> genres = read("select * from tbl_genre where genre_id = ?", new Object[] { genreId });
		if (genres.size() == 0) {
			return null;
		}
		return genres.get(0);
	}
	
	public Boolean genreExists(Integer genreId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_genre where genre_id = ?", new Object[] { genreId }).size() > 0;
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			g.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book_genres where genre_id = ?)", new Object[] { g.getGenreId() }));
			genres.add(g);
		}
		return genres;
	}

	@Override
	List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
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
