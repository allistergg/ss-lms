package com.smoothstack.lms.administrator.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.smoothstack.lms.administrator.model.Branch;
import com.smoothstack.lms.administrator.service.BranchService;

@RestController
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	// CREATE BRANCH
	@RequestMapping(path="/lms/branches", method=RequestMethod.POST)
	public ResponseEntity<Void> addBranch(@RequestBody Branch branch) {
		try {
			Integer branchId = branchService.saveBranch(branch);
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(branchId)
                    .toUri();
			return ResponseEntity.created(location).build();
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BRANCH BY ID
	@RequestMapping(path = "/lms/branches/{branchId}")
	public ResponseEntity<Branch> getBranchById(@PathVariable Integer branchId)  {
		try {
			Branch branch = branchService.getBranchById(branchId);
			if (branch == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Branch>(branch, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BRANCHES
	@RequestMapping(path = "/lms/branches")
	public ResponseEntity<List<Branch>> getBranches() {
		try {
			List<Branch> branches = branchService.readBranches();
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BRANCH
	@RequestMapping(path="/lms/branches/{branchId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBranch(@RequestBody Branch branch, @PathVariable Integer branchId) {
		try {
			branch.setBranchId(branchId);
			Boolean exists = branchService.updateBranch(branch);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// DELETE BRANCH
	@RequestMapping(path = "/lms/branches/{branchId}", method=RequestMethod.DELETE)
	public ResponseEntity<Branch> deleteBranch(@PathVariable Integer branchId) {
		try {
			Boolean exists = branchService.deleteBranch(branchId);
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
