package com.ss.lms.librarian.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController<T> {
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<T> handleSQlError(SQLException e) {
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<T> handleClassPathError(ClassNotFoundException e) {
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<T> handleBadRequest(HttpMessageNotReadableException e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	

}
