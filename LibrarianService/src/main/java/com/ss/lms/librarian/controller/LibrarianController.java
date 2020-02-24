package com.ss.lms.librarian.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.ss.lms.librarian.entity.Branch;
import com.ss.lms.librarian.service.LibrarianService;

@RestController
public class LibrarianController {

	@Autowired
	private LibrarianService service;
	
	@GetMapping(path="/branch", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Branch> getAllBranches() throws ClassNotFoundException, SQLException {
// testing load balancer
//		Branch b = new Branch();
//		b.setBranchName("Dummy Branch");
//		return Collections.singletonList(b);
		return service.findAllBranches();
	}
	
	@GetMapping(path="/branch/{id}")
	public Branch findBranch(@PathVariable Integer id) throws SQLException {
		return service.findBranchById(id).orElseThrow(SQLException::new);
	}

	@PutMapping(path="/branch", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBranch(@Valid @RequestBody Branch branch) throws ClassNotFoundException, SQLException {
		service.updateBranch(branch);;
		
	}
}
