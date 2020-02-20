package com.ss.lms.librarian.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Book;
import com.ss.lms.librarian.entity.Genre;

@Component
public class GenreDAO extends BaseDAO<Genre> {

    private final String ADD_GENRE_SQL = "INSERT INTO tbl_genre (genre_name) VALUES (?)";
    private final String UPDATE_GENRE_SQL = "UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?";
    private final String DELETE_GENRE_SQL = "DELETE FROM tbl_genre WHERE genre_id = ?";
    private final String READ_GENRE_SQL = "SELECT * FROM tbl_genre";
    private final String INSERT_BOOK_GENRES_SQL = "INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (?,?)";
    private final String DELETE_BOOK_GENRES_SQL = "DELETE FROM tbl_book_genres where bookId = ?";
    private final String GET_GENRE_BOOKS_SQL = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)";

    @Autowired
    BookDAO bdao;
   
    public Integer addGenre(Genre genre, Connection conn) throws SQLException, ClassNotFoundException {
        return save(ADD_GENRE_SQL, new Object[] {genre.getGenreName()}, conn);
    }

    public void updateGenre(Genre genre, Connection conn) throws SQLException, ClassNotFoundException {
        save(UPDATE_GENRE_SQL, new Object[] {genre.getGenreName(), genre.getGenreId()}, conn);
    }

    public void deleteGenre(Genre genre, Connection conn) throws SQLException, ClassNotFoundException {
        save(DELETE_GENRE_SQL, new Object[] {genre.getGenreId()}, conn);
    }

    public List<Genre> readAllGenres(Connection conn) throws SQLException, ClassNotFoundException {
        return read(READ_GENRE_SQL, null, conn);
    }

    public List<Genre> readAllGenresFirstLevel(Connection conn) throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_GENRE_SQL, null, conn);
    }

    public void deleteBookGenres(Integer bookId,Connection conn) throws SQLException, ClassNotFoundException {
     
        save(DELETE_BOOK_GENRES_SQL, new Object[] {bookId}, conn);
    }

    public void insertBookGenres(Genre genre, Book book, Connection conn) throws ClassNotFoundException, SQLException{
        
        save(INSERT_BOOK_GENRES_SQL, new Object[] {genre.getGenreId(), book.getBookId()}, conn);
    }

    @Override
    List<Genre> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
       
        List<Genre> genres = new ArrayList<>();
        while (rs.next()) {

            Genre g = new Genre();
            g.setGenreId(rs.getInt("genre_id"));
            g.setGenreName(rs.getString("genre_name"));
            g.setBooks(bdao.readFirstLevel(GET_GENRE_BOOKS_SQL, new Object[] {g.getGenreId()}, conn));
            genres.add(g);
        }
        return genres;
    }

    @Override
    List<Genre> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
        List<Genre> genres = new ArrayList<>();
        while (rs.next()) {
            Genre g = new Genre();
            g.setGenreId(rs.getInt("genre_id"));
            g.setGenreName(rs.getString("genre_name"));
            genres.add(g);
        }
        return genres;
    }
}
