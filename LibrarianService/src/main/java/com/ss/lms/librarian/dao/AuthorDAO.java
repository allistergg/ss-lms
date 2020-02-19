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

    private final String GET_AUTHOR_BOOKS_SQL = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?)";

    @Autowired
    private BookDAO bdao;
    
   public Integer addAuthor(Author author, Connection conn) throws SQLException, ClassNotFoundException {
        return save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[]{author.getAuthorName()}, conn);
    }

    public void updateAuthor(Author author, Connection conn) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[]{author.getAuthorName(), author.getAuthorId()}, conn);
    }

    public void deleteAuthor(Author author, Connection conn) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_author WHERE authorId = ?", new Object[]{author.getAuthorId()}, conn);
    }

    public List<Author> readAllAuthors(Connection conn) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_author", null, conn);
    }

    public List<Author> readAllAuthorsFirstLevel(Connection conn) throws SQLException, ClassNotFoundException {
        return readFirstLevel("SELECT * FROM tbl_author", null, conn);
    }

    public void deleteBookAuthors(Integer bookId, Connection conn) throws SQLException, ClassNotFoundException {
        save("delete from tbl_book_authors where bookId = ?", new Object[] {bookId}, conn);
    }

    public void insertBookAuthors(Author author, Book book, Connection conn) throws ClassNotFoundException, SQLException{
        save("insert into tbl_book_authors (bookId, authorId) values(?,?)", new Object[] {book.getBookId(), author.getAuthorId()}, conn);
    }

    @Override
    List<Author> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
 
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            a.setBooks(bdao.readFirstLevel(GET_AUTHOR_BOOKS_SQL, new Object[]{a.getAuthorId()}, conn));
            authors.add(a);
        }
        return authors;
    }

    @Override
    List<Author> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
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