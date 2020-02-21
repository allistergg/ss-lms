package com.smoothstack.lms.administrator.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
	
	 @ExceptionHandler(value = SQLException.class)
	 public ResponseEntity<Void> handleSQLException(SQLException e) {
		 return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	 }
	 
}
