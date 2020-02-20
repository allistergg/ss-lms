package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.model.Author;

@Service
public class AuthorService {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private AuthorDAO adao;
	
	// CREATE AUTHOR
	public Integer saveAuthor(Author author) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Integer authorId = adao.addAuthorReturnPK(conn, author);
			conn.commit();
			return authorId;		
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ AUTHOR BY ID
	public List<Author> getAuthorById(Integer authorId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return adao.readAuthorById(conn, authorId);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
		
	// READ AUTHORS
	public List<Author> readAuthors() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return adao.readAuthors(conn);
		}	catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	// UPDATE AUTHOR
	public Boolean updateAuthor(Author author) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = adao.updateAuthor(conn, author);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE AUTHOR
	public Boolean deleteAuthor(Integer authorId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = adao.deleteAuthor(conn, authorId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
