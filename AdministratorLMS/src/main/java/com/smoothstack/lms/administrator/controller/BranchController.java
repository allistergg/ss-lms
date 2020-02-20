package com.smoothstack.lms.administrator.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	@RequestMapping(path="/lms/branches", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBranch(@RequestBody Branch branch) {
		try {
			Integer branchId = branchService.saveBranch(branch);
			// Connection class not found
			if (branchId == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
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
	@RequestMapping(path = "/lms/branches/{branchId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Branch> getBranchById(@PathVariable Integer branchId)  {
		try {
			List<Branch> branches = branchService.getBranchById(branchId);
			// Connection class not found
			if (branches == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No branch with this Id
			if (branches.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Branch>(branches.get(0), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BRANCHES
	@RequestMapping(path = "/lms/branches", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Branch>> getBranches() {
		try {
			List<Branch> branches = branchService.readBranches();
			// Connection not found
			if (branches == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BRANCH
	@RequestMapping(path="/lms/branches/{branchId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBranch(@RequestBody Branch branch, @PathVariable Integer branchId) {
		try {
			branch.setBranchId(branchId);
			Boolean exists = branchService.updateBranch(branch);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No branch with this Id exists
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
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No branch with this Id exists
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
