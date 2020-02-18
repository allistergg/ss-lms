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

    private String ADD_BOOK_SQL = "INSERT INTO tbl_book (title, pubId) VALUES (?,?)";
    private String UPDATE_BOOK_SQL = "UPDATE tbl_book SET title = ?, pubId = ? WHERE bookId = ?";
    private String DELETE_BOOK_SQL = "DELETE FROM tbl_book WHERE bookId = ?";
    private String READ_BOOKS_SQL = "SELECT * FROM tbl_book";
    private String GET_BOOK_AUTHORS_SQL = "SELECT * FROM tbl_author where authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)";
    private String GET_BOOK_GENRES_SQL = "SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)";
    private String GET_BOOK_BY_ID_SQL = "SELECT * FROM tbl_book WHERE bookId = ?";
    
    @Autowired
    PublisherDAO pdao;
    @Autowired
    GenreDAO gdao;
    @Autowired
    AuthorDAO adao;
    
    public BookDAO(Connection conn) {
        super(conn);
    }

    public Integer addBook(Book book) throws SQLException, ClassNotFoundException {
        return save(ADD_BOOK_SQL, new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
    }

    public void updateBook(Book book) throws SQLException, ClassNotFoundException {
        save(UPDATE_BOOK_SQL, new Object[] {book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()});
    }

    public void deleteBook(Book book) throws SQLException, ClassNotFoundException {
        save(DELETE_BOOK_SQL, new Object[] {book.getBookId()});
    }

    public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
        return read(READ_BOOKS_SQL, null);
    }

    public List<Book> readAllBooksFirstLevel() throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_BOOKS_SQL, null);
    }

    public Book getBookById(Integer id) throws SQLException, ClassNotFoundException {
        try {
            return (Book) readFirstLevel(GET_BOOK_BY_ID_SQL, new Object[]{id}).get(0);
        } catch (IllegalArgumentException e) {
            System.out.println("book not found");
            return null;
        }
    }

    public Book getFullBookById(Integer id) throws SQLException, ClassNotFoundException {
        try {
            return (Book) read(GET_BOOK_BY_ID_SQL, new Object[] {id}).get(0);
        } catch (IllegalArgumentException e) {
            System.out.println("book not found");
            return null;
        }
    }

    @Override
    List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
      
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            b.setPublisher(pdao.getPublisherById(rs.getInt("pubId")));
            b.setAuthors(adao.readFirstLevel(GET_BOOK_AUTHORS_SQL, new Object[] {b.getBookId()}));
            b.setGenres(gdao.readFirstLevel(GET_BOOK_GENRES_SQL, new Object[] {b.getBookId()}));
            books.add(b);
        }
        return books;
    }

    @Override
    List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
    	List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            b.setPublisher(pdao.getPublisherById(rs.getInt("pubId")));
            books.add(b);
        }
        return books;
    }
}
