
package com.smoothstack.lms.administrator.dao;

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
	
	public Integer addAuthorReturnPK(Author author) throws SQLException, ClassNotFoundException {
		// returns the public key of the newly author
		return saveReturnPk("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public Boolean updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		return save("update tbl_author set authorName = ? where authorId = ?", 
				new Object[] { author.getAuthorName(), author.getAuthorId() }) > 0;
	}

	public Boolean deleteAuthor(Integer authorId) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_author where authorId = ?", new Object[] { authorId }) > 0;
	}
	
	public List<Author> readAuthors() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_author", null);
	}
	
	public Author readAuthorById(Integer authorId) throws SQLException, ClassNotFoundException {
		List<Author> authors = read("select * from tbl_author where authorId = ?", new Object[] { authorId });
		if (authors.size() == 0) {
			return null;
		}
		return authors.get(0);
	}
	
	public Boolean authorExists(Integer authorId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_author where authorId = ?", new Object[] { authorId }).size() > 0;
	}
	
	public void deleteAuthorBooks(Author author) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book_authors where authorId = ?", new Object[] { author.getAuthorId() });
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book_authors where authorId = ?)", new Object[] { a.getAuthorId() }));
			authors.add(a);
		}
		return authors;
	}
	
	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
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
