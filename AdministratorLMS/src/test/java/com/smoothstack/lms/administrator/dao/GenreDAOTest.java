package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Genre;
import com.smoothstack.lms.administrator.model.Publisher;

@SpringBootTest
class GenreDAOTest {

	@Autowired
	GenreDAO gdao;
	
	Genre genre;
	
	@BeforeEach
	void setUp() throws Exception {
		List<Genre> genres = gdao.readGenres();
		System.out.println(genres);
		for (Genre g : genres) {
			gdao.deleteGenre(g.getGenreId());
		}
		genre = new Genre();
		genre.setGenreName("Fantasy");
		genre.setBooks(new ArrayList<>());
	}

	@Test
	void testAddGenre() throws ClassNotFoundException, SQLException {
		Integer id = gdao.addGenre(genre);
		genre.setGenreId(id);
		assertTrue(gdao.genreExists(id));
		assertFalse(gdao.genreExists(9999));
		Genre genreFromDb = gdao.readGenreById(id);
		assertEquals(genreFromDb, genre);
		assertFalse(gdao.deleteGenre(9999));
		gdao.deleteGenre(id);
		assertNull(gdao.readGenreById(id));
	}

	@Test
	void testUpdateGenre() throws ClassNotFoundException, SQLException {
		Integer id = gdao.addGenre(genre);
		genre.setGenreId(9999);
		assertFalse(gdao.updateGenre(genre));
		genre.setGenreId(id);
		genre.setGenreName("Science Fiction");
		gdao.updateGenre(genre);
		Genre genreFromDb = gdao.readGenreById(genre.getGenreId());
		
		assertEquals(genre, genreFromDb);
	}

}
