package com.ss.lms.orchestrator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class ControllerErrorHandler<T> {
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<T> handleRestException(HttpStatusCodeException e) {
		return new ResponseEntity<>(e.getStatusCode());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<T> handleBadRequest(HttpMessageNotReadableException e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
