package com.ss.lms.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Branch;

@RequestMapping("/librarian")
@RestController
public class LibrarianController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private String BRANCH_URI = "http://localhost:8085/branch";
	
	@GetMapping("/branches")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Branch[]> getBranches() {
		return restTemplate.getForEntity(BRANCH_URI, Branch[].class);
	}
	
	@PutMapping("/branch")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBranch(@RequestBody Branch branch) {
		restTemplate.put(BRANCH_URI, branch);
	}

}
