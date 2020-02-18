package com.ss.lms.librarian.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.librarian.dao.BranchDAO;
import com.ss.lms.librarian.entity.Branch;

@RestController
public class LibrarianController {

	@Autowired
	BranchDAO branchDAO;

	@GetMapping("/branch")
	public List<Branch> getAllBranches() {
		try {
			return branchDAO.readAllBranchesFirstLevel();
	} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/branchFull")
	public List<Branch> getAllFullBranches() {

		try {
			return branchDAO.readAllBranches();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PutMapping("/branch")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBranch(@RequestBody Branch branch) {
		try {
			branchDAO.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
