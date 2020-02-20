package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Copies;
import com.smoothstack.lms.administrator.model.Genre;
import com.smoothstack.lms.administrator.service.ConnectionUtil;

@SpringBootTest
class BookDAOTest {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private BookDAO bdao;
	@Autowired
	private GenreDAO gdao;
	@Autowired
	private AuthorDAO adao;
	
	@Test
	void testAddBookAndReadById() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		book.setAuthors(new ArrayList<Author>());
		book.setCopies(new ArrayList<Copies>());
		book.setGenres(new ArrayList<Genre>());
		Integer key = bdao.addBookReturnPk(conn, book);
		book.setBookId(key);
		Book bookFromDB = bdao.readBookById(conn, key).get(0);
		assertEquals(book, bookFromDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testReadBooks() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		assertEquals(bdao.readBooks(conn), new ArrayList<Book>());
		conn.close();
	}
	
	@Test
	void testUpdateBook() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		book.setAuthors(new ArrayList<Author>());
		book.setCopies(new ArrayList<Copies>());
		book.setGenres(new ArrayList<Genre>());
		Integer key = bdao.addBookReturnPk(conn, book);
		book.setBookId(key);
		book.setTitle("1985");
		Boolean success = bdao.updateBook(conn, book);
		assertTrue(success);
		Book bookFromDB = bdao.readBookById(conn, key).get(0);
		assertEquals(book, bookFromDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testDeleteBook() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(conn, book);
		bdao.deleteBook(conn, key);
		assertEquals(bdao.readBookById(conn, key).size(), 0);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testBookExists() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(conn, book);
		assertTrue(bdao.bookExists(conn, key));
		conn.rollback();
		conn.close();
	}
	
	@Test 
	void testInsertBookAuthorsAndGenres() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(conn, book);
		book.setBookId(key);
		Author a = new Author();
		a.setAuthorName("George Orwell");
		Integer authKey = adao.addAuthorReturnPK(conn, a);
		a.setAuthorId(authKey);
		List<Author> authors = new ArrayList<Author>();
		authors.add(a);
		book.setAuthors(authors);
		bdao.insertBookAuthors(conn, book, a);
		Genre g = new Genre();
		g.setGenreName("Dystopia");
		Integer genreKey = gdao.addGenre(conn, g);
		g.setGenreId(genreKey);
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(g);
		book.setGenres(genres);
		bdao.insertBookGenres(conn, book, g);
		Book bookFromDB = bdao.readBookById(conn, key).get(0);
		book.setCopies(new ArrayList<Copies>());
		assertEquals(book, bookFromDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testDeleteBookAuthorsAndGenres() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(conn, book);
		book.setBookId(key);
		Author a = new Author();
		a.setAuthorName("George Orwell");
		Integer authKey = adao.addAuthorReturnPK(conn, a);
		a.setAuthorId(authKey);
		bdao.insertBookAuthors(conn, book, a);
		Genre g = new Genre();
		g.setGenreName("Dystopia");
		Integer genreKey = gdao.addGenre(conn, g);
		g.setGenreId(genreKey);
		bdao.insertBookGenres(conn, book, g);
		book.setCopies(new ArrayList<Copies>());
		book.setAuthors(new ArrayList<Author>());
		book.setGenres(new ArrayList<Genre>());
		bdao.deleteBookAuthors(conn, book);
		bdao.deleteBookGenres(conn, book);
		Book bookFromDB = bdao.readBookById(conn, key).get(0);
		assertEquals(book, bookFromDB);
		conn.rollback();
		conn.close();
	}


}
