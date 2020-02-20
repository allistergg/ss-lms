
package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Author;

@Component
public class AuthorDAO extends BaseDAO<Author> {
	
	@Autowired
	private BookDAO bdao;
	
	public Integer addAuthorReturnPK(Connection conn, Author author) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public Boolean updateAuthor(Connection conn, Author author) throws SQLException {
		return save(conn, "update tbl_author set authorName = ? where authorId = ?", 
				new Object[] { author.getAuthorName(), author.getAuthorId() }) > 0;
	}

	public Boolean deleteAuthor(Connection conn, Integer authorId) throws SQLException {
		return save(conn, "delete from tbl_author where authorId = ?", new Object[] { authorId }) > 0;
	}
	
	public List<Author> readAuthors(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_author", null);
	}
	
	public List<Author> readAuthorById(Connection conn, Integer authorId) throws SQLException {
		return read(conn, "select * from tbl_author where authorId = ?", new Object[] { authorId });
	}
	
	public Boolean authorExists(Connection conn, Integer authorId) throws SQLException {
		return read(conn, "select * from tbl_author where authorId = ?", new Object[] { authorId }).size() > 0;
	}

	@Override
	public List<Author> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readFirstLevel(conn, "select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book_authors where authorId = ?)", new Object[] { a.getAuthorId() }));
			authors.add(a);
		}
		return authors;
	}
	
	@Override
	public List<Author> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}

}
