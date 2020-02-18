package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.model.Author;

@Component
public class AuthorService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private AuthorDAO adao;
	
	// CREATE AUTHOR
	public Integer saveAuthor(Author author) throws SQLException {
		try {
			Integer authorId = adao.addAuthorReturnPK(author);
			conn.commit();
			return authorId;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add author.");
			conn.rollback();
		}
		return null;
	}
		
	// READ AUTHORS
	public List<Author> readAuthors() throws SQLException{
		try {
			return adao.readAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read authors.");
		}
		return null;
	}
	
	// UPDATE AUTHOR
	public Boolean updateAuthor(Author author) throws SQLException{
		try {
			Boolean exists = adao.updateAuthor(author);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update author.");
			conn.rollback();
		} 
		return null;
	}
	
	// DELETE AUTHOR
	public Boolean deleteAuthor(Integer authorId) throws SQLException{
		try {
			Boolean exists = adao.deleteAuthor(authorId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete author.");
			conn.rollback();
		} 
		return null;
	}

	public Author getAuthorById(int authorId) throws SQLException {
		try {
			return adao.readAuthorById(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read author by id.");
		} 
		return null;
	}

}
