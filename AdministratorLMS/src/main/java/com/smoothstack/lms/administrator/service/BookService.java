package com.smoothstack.lms.administrator.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.BookDAO;
import com.smoothstack.lms.administrator.model.Book;
import com.smoothstack.lms.administrator.model.Copies;
import com.smoothstack.lms.administrator.model.Loan;

@Service
public class BookService {
	
	@Autowired
	BookDAO bdao;
	
	// CREATE BOOK
	public Book saveBook(Book book) {
		return bdao.save(book);
	}
	
	// READ BOOK BY ID
	public Result<Book> getBookById(Integer bookId) {
		Result<Book> rs = new Result<Book>();
		if (bdao.existsById(bookId)) {
			rs.setResult(bdao.findById(bookId).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// READ BOOKS
	public List<Book> readBooks() {
		return bdao.findAll();
	}
	
	// UPDATE BOOK
	public Result<Void> updateBook(Book book) {
		Result<Void> rs = new Result<Void>();
		if (bdao.existsById(book.getBookId())) {
			bdao.save(book);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE BOOK
	@Transactional
	public Result<Void> deleteBook(Integer bookId) {
		Result<Void> rs = new Result<Void>();
		Optional<Book> b = bdao.findById(bookId);
		if (b.isPresent()) {
			Book book = b.get();
			for (Loan loan: book.getLoans()) {
				loan.setDeleted(true);
			}
			for (Copies copies: book.getCopies()) {
				copies.setDeleted(true);
			}
			bdao.deleteById(bookId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
