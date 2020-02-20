package com.ss.lms.orchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Branch;

@RestController
@RequestMapping("/admin/branch")
public class AdminBranchController extends AdminController<Branch>{
	
	@Autowired
	RestTemplate restTemplate;
	
	private String ADMIN_BRANCH_URI = "http://localhost:8080/lms/branches/";

	@Override
	public ResponseEntity<Branch[]> getAll() {
	
		return restTemplate.getForEntity(ADMIN_BRANCH_URI, Branch[].class);
	}

	@Override
	public ResponseEntity<Branch> getById(@PathVariable Integer id) {

		return restTemplate.getForEntity(ADMIN_BRANCH_URI + id, Branch.class);
	}

	@Override
	public ResponseEntity<Branch> create(@Valid @RequestBody Branch t) {
	
		return restTemplate.postForEntity(ADMIN_BRANCH_URI, t, Branch.class);
	}

	@Override
	public void update(@Valid @RequestBody Branch t) {
		
		restTemplate.put(ADMIN_BRANCH_URI + t.getBranchId(),  t);
		
	}

	@Override
	public void delete(@PathVariable Integer id) {
		
		restTemplate.delete(ADMIN_BRANCH_URI + id);
		
	}

}
