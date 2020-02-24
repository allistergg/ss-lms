package com.smoothstack.lms.administrator.controller;

import java.net.URI;
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
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	// CREATE BRANCH
	@RequestMapping(path="/lms/branches", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBranch(@RequestBody Branch branch) {
		Branch savedBranch = branchService.saveBranch(branch);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBranch.getBranchId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// READ BRANCH BY ID
	@RequestMapping(path = "/lms/branches/{branchId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Branch> getBranchById(@PathVariable Integer branchId)  {
		Result<Branch> rs = branchService.getBranchById(branchId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Branch>(rs.getResult(), HttpStatus.OK);	
	}
	
	// READ BRANCHES
	@RequestMapping(path = "/lms/branches", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Branch>> getBranches() {
		List<Branch> branches = branchService.readBranches();
		return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);
	}
	
	// UPDATE BRANCH
	@RequestMapping(path="/lms/branches/{branchId}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBranch(@RequestBody Branch branch, @PathVariable Integer branchId) {
		branch.setBranchId(branchId);
		Result<Void> rs = branchService.updateBranch(branch);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE BRANCH
	@RequestMapping(path = "/lms/branches/{branchId}", method=RequestMethod.DELETE)
	public ResponseEntity<Branch> deleteBranch(@PathVariable Integer branchId) {
		Result<Void> rs = branchService.deleteBranch(branchId);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
