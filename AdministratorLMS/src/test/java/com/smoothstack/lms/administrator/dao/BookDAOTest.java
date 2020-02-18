package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Copies;
import com.smoothstack.lms.administrator.model.Genre;
import com.smoothstack.lms.administrator.model.Publisher;

@SpringBootTest
class BookDAOTest {
	
	@Autowired
	BookDAO bdao;
	@Autowired
	PublisherDAO pdao;
	@Autowired
	GenreDAO gdao;
	@Autowired
	AuthorDAO adao;
	
	@BeforeEach
	void clearDB() throws ClassNotFoundException, SQLException {
		for (Book b: bdao.readBooks()) {
			bdao.deleteBook(b.getBookId());
		}
		for (Genre g: gdao.readGenres()) {
			gdao.deleteGenre(g.getGenreId());
		}
		for (Publisher p: pdao.readPublishers()) {
			pdao.deletePublisher(p.getPublisherId());
		}
		for (Author a: adao.readAuthors()) {
			adao.deleteAuthor(a.getAuthorId());
		}
	}
	
	@Test
	void testAddBookAndReadById() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		book.setAuthors(new ArrayList<Author>());
		book.setCopies(new ArrayList<Copies>());
		book.setGenres(new ArrayList<Genre>());
		Integer key = bdao.addBookReturnPk(book);
		book.setBookId(key);
		Book bookFromDB = bdao.readBookById(key);
		assertEquals(book, bookFromDB);
	}
	
	@Test
	void testReadBooks() throws ClassNotFoundException, SQLException {
		assertEquals(bdao.readBooks(), new ArrayList<Book>());
	}
	
	@Test
	void testUpdateBook() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		book.setAuthors(new ArrayList<Author>());
		book.setCopies(new ArrayList<Copies>());
		book.setGenres(new ArrayList<Genre>());
		Integer key = bdao.addBookReturnPk(book);
		book.setBookId(key);
		book.setTitle("1985");
		Boolean success = bdao.updateBook(book);
		assertTrue(success);
		Book bookFromDB = bdao.readBookById(key);
		assertEquals(book, bookFromDB);
	}
	
	@Test
	void testDeleteBook() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(book);
		bdao.deleteBook(key);
		assertEquals(bdao.readBooks().size(), 0);
	}
	
	@Test
	void testBookExists() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(book);
		assertTrue(bdao.bookExists(key));
	}
	
	@Test 
	void testInsertBookAuthorsAndGenres() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(book);
		book.setBookId(key);
		Author a = new Author();
		a.setAuthorName("George Orwell");
		Integer authKey = adao.addAuthorReturnPK(a);
		a.setAuthorId(authKey);
		List<Author> authors = new ArrayList<Author>();
		authors.add(a);
		book.setAuthors(authors);
		bdao.insertBookAuthors(book, a);
		Genre g = new Genre();
		g.setGenreName("Dystopia");
		Integer genreKey = gdao.addGenre(g);
		g.setGenreId(genreKey);
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(g);
		book.setGenres(genres);
		bdao.insertBookGenres(book, g);
		Book bookFromDB = bdao.readBookById(key);
		book.setCopies(new ArrayList<Copies>());
		assertEquals(book, bookFromDB);
	}
	
	@Test
	void testDeleteBookAuthorsAndGenres() throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setTitle("1984");
		Integer key = bdao.addBookReturnPk(book);
		book.setBookId(key);
		Author a = new Author();
		a.setAuthorName("George Orwell");
		Integer authKey = adao.addAuthorReturnPK(a);
		a.setAuthorId(authKey);
		bdao.insertBookAuthors(book, a);
		Genre g = new Genre();
		g.setGenreName("Dystopia");
		Integer genreKey = gdao.addGenre(g);
		g.setGenreId(genreKey);
		bdao.insertBookGenres(book, g);
		book.setCopies(new ArrayList<Copies>());
		book.setAuthors(new ArrayList<Author>());
		book.setGenres(new ArrayList<Genre>());
		bdao.deleteBookAuthors(book);
		bdao.deleteBookGenres(book);
		Book bookFromDB = bdao.readBookById(key);
		assertEquals(book, bookFromDB);
	}


}
