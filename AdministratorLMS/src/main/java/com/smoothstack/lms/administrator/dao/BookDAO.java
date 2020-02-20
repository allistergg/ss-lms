package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Genre;
import com.smoothstack.lms.administrator.model.Publisher;

@Component
public class BookDAO extends BaseDAO<Book> {
	
	@Autowired
	private PublisherDAO pdao;
	@Autowired
	private AuthorDAO adao;
	@Autowired
	private GenreDAO gdao;
	@Autowired
	private CopiesDAO cdao;

	public Integer addBookReturnPk(Connection conn, Book book) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_book (title, pubId) values (?,?)", 
				new Object[] { book.getTitle(), book.getPublisher() == null? null: book.getPublisher().getPublisherId() });
	}

	public Boolean updateBook(Connection conn, Book book) throws SQLException {
		return save(conn, "update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[]{ book.getTitle(), 
				book.getPublisher() == null? null: book.getPublisher().getPublisherId(), book.getBookId() } ) > 0;
	}

	public Boolean deleteBook(Connection conn, Integer bookId) throws SQLException {
		return save(conn, "delete from tbl_book where bookId = ?", new Object[] { bookId }) > 0;
	}
	
	public void deleteBookAuthors(Connection conn, Book book) throws SQLException {
		save(conn, "delete from tbl_book_authors where bookId = ?", new Object[] { book.getBookId() });
	}
	
	public void deleteBookGenres(Connection conn, Book book) throws SQLException {
		save(conn, "delete from tbl_book_genres where bookId = ?", new Object[] { book.getBookId() });
	}
	
	public void insertBookGenres(Connection conn, Book book, Genre genre) throws SQLException {
		save(conn, "insert into tbl_book_genres values(?, ?)", new Object[] { genre.getGenreId(), book.getBookId() });
	}
	
	public void insertBookAuthors(Connection conn, Book book, Author author) throws SQLException {
		save(conn, "insert into tbl_book_authors values(?, ?)", new Object[] { book.getBookId(), author.getAuthorId() });
	}
	
	public List<Book> readBooks(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_book", null);
	}
	
	public List<Book> readBookById(Connection conn, Integer bookId) throws SQLException {
		return read(conn, "select * from tbl_book where bookId = ?", new Object[] { bookId });
	}
	
	public Boolean bookExists(Connection conn, Integer bookId) throws SQLException {
		return read(conn, "select * from tbl_book where bookId = ?", new Object[] { bookId }).size() > 0;
	}
	
	@Override
	public List<Book> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			List<Publisher> pList = pdao.readFirstLevel(conn, "select * from tbl_publisher where publisherId = ?", 
					new Object[] { rs.getInt("pubId") });
			if (pList.size() != 0) {
				b.setPublisher(pList.get(0));
			}
			b.setAuthors(adao.readFirstLevel(conn, "select * from tbl_author where authorId IN "
					+ "(select authorId from tbl_book_authors where bookId = ?)", new Object[] { b.getBookId() }));
			b.setGenres(gdao.readFirstLevel(conn, "select * from tbl_genre where genre_id IN "
					+ "(select genre_id from tbl_book_genres where bookId = ?)", new Object[] { b.getBookId() }));
			b.setCopies(cdao.readFirstLevel(conn, "select * from tbl_book_copies where bookId = ?", new Object[] { b.getBookId() }));
			books.add(b);
		}
		return books;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
	

}
