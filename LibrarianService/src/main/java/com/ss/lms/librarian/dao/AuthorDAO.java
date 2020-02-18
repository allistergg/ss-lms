package com.ss.lms.librarian.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Author;
import com.ss.lms.librarian.entity.Book;

@Component
public class AuthorDAO extends BaseDAO<Author>{

    private String GET_AUTHOR_BOOKS_SQL = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?)";

    @Autowired
    BookDAO bdao;
    
    public AuthorDAO(Connection conn) {
        super(conn);
    }

    public Integer addAuthor(Author author) throws SQLException, ClassNotFoundException {
        return save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[]{author.getAuthorName()});
    }

    public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[]{author.getAuthorName(), author.getAuthorId()});
    }

    public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_author WHERE authorId = ?", new Object[]{author.getAuthorId()});
    }

    public List<Author> readAllAuthors() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_author", null);
    }

    public List<Author> readAllAuthorsFirstLevel() throws SQLException, ClassNotFoundException {
        return readFirstLevel("SELECT * FROM tbl_author", null);
    }

    public void deleteBookAuthors(Integer bookId) throws SQLException, ClassNotFoundException {
        save("delete from tbl_book_authors where bookId = ?", new Object[] {bookId});
    }

    public void insertBookAuthors(Author author, Book book) throws ClassNotFoundException, SQLException{
        save("insert into tbl_book_authors (bookId, authorId) values(?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
    }

    @Override
    List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
 
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            a.setBooks(bdao.readFirstLevel(GET_AUTHOR_BOOKS_SQL, new Object[]{a.getAuthorId()}));
            authors.add(a);
        }
        return authors;
    }

    @Override
    List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            authors.add(a);
        }
        return authors;
    }
}