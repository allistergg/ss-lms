package com.ss.lms.librarian.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.librarian.dao.BranchDAO;
import com.ss.lms.librarian.entity.Branch;
import com.ss.lms.librarian.service.LibrarianService;

@RestController
public class LibrarianController {

	@Autowired
	private LibrarianService service;
	@GetMapping(path="/branch", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	public List<Branch> getAllBranches() throws ClassNotFoundException, SQLException {
		return service.findAllBranches();
	
	}

	@PutMapping(path="/branch", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBranch(@Valid @RequestBody Branch branch) throws ClassNotFoundException, SQLException {
		service.updateBranch(branch);;
		
	}
}
