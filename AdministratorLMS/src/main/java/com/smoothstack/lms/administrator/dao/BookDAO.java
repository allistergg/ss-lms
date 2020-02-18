package com.smoothstack.lms.administrator.dao;

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

	public Integer addBookReturnPk(Book book) throws SQLException, ClassNotFoundException {
		return saveReturnPk("insert into tbl_book (title, pubId) values (?,?)", 
				new Object[] { book.getTitle(), book.getPublisher() == null? null: book.getPublisher().getPublisherId() });
	}

	public Boolean updateBook(Book book) throws SQLException, ClassNotFoundException {
		return save("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[]{ book.getTitle(), 
				book.getPublisher() == null? null: book.getPublisher().getPublisherId(), book.getBookId() } ) > 0;
	}

	public Boolean deleteBook(Integer bookId) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_book where bookId = ?", new Object[] { bookId }) > 0;
	}
	
	public void deleteBookAuthors(Book book) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book_authors where bookId = ?", new Object[] { book.getBookId() });
	}
	
	public void deleteBookGenres(Book book) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book_genres where bookId = ?", new Object[] { book.getBookId() });
	}
	
	public void insertBookGenres(Book book, Genre genre) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_genres values(?, ?)", new Object[] { genre.getGenreId(), book.getBookId() });
	}
	
	public void insertBookAuthors(Book book, Author author) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_authors values(?, ?)", new Object[] { book.getBookId(), author.getAuthorId() });
	}
	
	public List<Book> readBooks() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book", null);
	}
	
	public Book readBookById(Integer bookId) throws SQLException, ClassNotFoundException {
		List<Book> books = read("select * from tbl_book where bookId = ?", new Object[] { bookId });
		if (books.size() == 0) {
			return null;
		}
		return books.get(0);
	}
	
	public Boolean bookExists(Integer bookId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book where bookId = ?", new Object[] { bookId }).size() > 0;
	}
	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			List<Publisher> pList = pdao.readFirstLevel("select * from tbl_publisher where publisherId = ?", 
					new Object[] { rs.getInt("pubId") });
			if (pList.size() != 0) {
				b.setPublisher(pList.get(0));
			}
			b.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN "
					+ "(select authorId from tbl_book_authors where bookId = ?)", new Object[] { b.getBookId() }));
			b.setGenres(gdao.readFirstLevel("select * from tbl_genre where genre_id IN "
					+ "(select genre_id from tbl_book_genres where bookId = ?)", new Object[] { b.getBookId() }));
			b.setCopies(cdao.readFirstLevel("select * from tbl_book_copies where bookId = ?", new Object[] { b.getBookId() }));
			books.add(b);
		}
		return books;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
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
