package com.smoothstack.lms.administrator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.model.Author;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorDAO adao;
	
	// CREATE AUTHOR
	public Author saveAuthor(Author author) {
		return adao.save(author);
	}
	
	// READ AUTHOR BY ID
	public Result<Author> getAuthorById(Integer authorId) {
		Result<Author> rs = new Result<Author>();
		if (adao.existsById(authorId)) {
			rs.setResult(adao.findById(authorId).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
		
	// READ AUTHORS
	public List<Author> readAuthors() {
		return adao.findAll();
	}
	
	// UPDATE AUTHOR
	public Result<Void> updateAuthor(Author author) {
		// Need to make sure the author exists in DB or a new author will be created
		Result<Void> rs = new Result<Void>();
		if (adao.existsById(author.getAuthorId())) {
			adao.save(author);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE AUTHOR
	public Result<Void> deleteAuthor(Integer authorId) {
		Result<Void> rs = new Result<Void>();
		if (adao.existsById(authorId)) {
			adao.deleteById(authorId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
