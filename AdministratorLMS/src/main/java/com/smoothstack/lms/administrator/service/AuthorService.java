package com.smoothstack.lms.administrator.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.AuthorDAO;
import com.smoothstack.lms.administrator.dao.BookDAO;
import com.smoothstack.lms.administrator.model.Author;
import com.smoothstack.lms.administrator.model.Book;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorDAO adao;
	
	@Autowired
	private BookDAO bdao;
	
	// CREATE AUTHOR
	public Author saveAuthor(Author author) {
		return adao.save(author);
	}
	
	// READ AUTHOR BY ID
	public Result<Author> getAuthorById(Integer authorId) {
		Result<Author> rs = new Result<Author>();
		Optional<Author> author = adao.findById(authorId);
		if (author.isPresent()) {
			rs.setResult(author.get());
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
	@Transactional
	public Result<Void> deleteAuthor(Integer authorId) {
		Result<Void> rs = new Result<Void>();
		Optional<Author> author = adao.findById(authorId);
		if (author.isPresent()) {
			for (Book b: author.get().getBooks()) {
				// this is the only author of the book so book must be deleted
				if (b.getAuthors().size() == 1) {
					bdao.deleteById(b.getBookId());
				}
			}
			// finally delete the author
			adao.deleteById(authorId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
