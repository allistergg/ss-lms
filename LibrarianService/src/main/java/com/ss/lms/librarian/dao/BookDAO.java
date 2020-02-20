package com.ss.lms.librarian.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Book;

@Component
public class BookDAO extends BaseDAO<Book>{

    private final String ADD_BOOK_SQL = "INSERT INTO tbl_book (title, pubId) VALUES (?,?)";
    private final String UPDATE_BOOK_SQL = "UPDATE tbl_book SET title = ?, pubId = ? WHERE bookId = ?";
    private final String DELETE_BOOK_SQL = "DELETE FROM tbl_book WHERE bookId = ?";
    private final String READ_BOOKS_SQL = "SELECT * FROM tbl_book";
    private final String GET_BOOK_AUTHORS_SQL = "SELECT * FROM tbl_author where authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)";
    private final String GET_BOOK_GENRES_SQL = "SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)";
    private final String GET_BOOK_BY_ID_SQL = "SELECT * FROM tbl_book WHERE bookId = ?";
    
    @Autowired
    private PublisherDAO pdao;
    @Autowired
    private GenreDAO gdao;
    @Autowired
    private AuthorDAO adao;
    
   public Integer addBook(Book book, Connection conn) throws SQLException, ClassNotFoundException {
        return save(ADD_BOOK_SQL, new Object[] {book.getTitle(), book.getPublisher().getPublisherId()}, conn);
    }

    public void updateBook(Book book, Connection conn) throws SQLException, ClassNotFoundException {
        save(UPDATE_BOOK_SQL, new Object[] {book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()}, conn);
    }

    public void deleteBook(Book book, Connection conn) throws SQLException, ClassNotFoundException {
        save(DELETE_BOOK_SQL, new Object[] {book.getBookId()}, conn);
    }

    public List<Book> readAllBooks(Connection conn) throws SQLException, ClassNotFoundException {
        return read(READ_BOOKS_SQL, null, conn);
    }

    public List<Book> readAllBooksFirstLevel(Connection conn) throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_BOOKS_SQL, null, conn);
    }

    public Book getBookById(Integer id, Connection conn) throws SQLException, ClassNotFoundException {
        try {
            return (Book) readFirstLevel(GET_BOOK_BY_ID_SQL, new Object[]{id}, conn).get(0);
        } catch (IllegalArgumentException e) {
          
            return null;
        }
    }

    public Book getFullBookById(Integer id, Connection conn) throws SQLException, ClassNotFoundException {
        try {
            return (Book) read(GET_BOOK_BY_ID_SQL, new Object[] {id}, conn).get(0);
        } catch (IllegalArgumentException e) {
            
            return null;
        }
    }

    @Override
    List<Book> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
      
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            b.setPublisher(pdao.getPublisherById(rs.getInt("pubId"), conn));
            b.setAuthors(adao.readFirstLevel(GET_BOOK_AUTHORS_SQL, new Object[] {b.getBookId()}, conn));
            b.setGenres(gdao.readFirstLevel(GET_BOOK_GENRES_SQL, new Object[] {b.getBookId()}, conn));
            books.add(b);
        }
        return books;
    }

    @Override
    List<Book> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
    	List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            b.setPublisher(pdao.getPublisherById(rs.getInt("pubId"), conn));
            books.add(b);
        }
        return books;
    }
}
