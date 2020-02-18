package com.ss.lms.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.Borrower;

@RestController
@RequestMapping("/admin/borrower")
public class AdminBorrowerController extends AdminController<Borrower> {
	
	@Autowired
	RestTemplate restTemplate;
	
	private String ADMIN_BORROWER_URI = "http://localhost:8080/lms/borrowers/";

	@Override
	public ResponseEntity<Borrower[]> getAll() {
		// TODO Auto-generated method stub
		return restTemplate.getForEntity(ADMIN_BORROWER_URI, Borrower[].class);
	}

	@Override
	public ResponseEntity<Borrower> getById(Integer id) {
		// TODO Auto-generated method stub
		return restTemplate.getForEntity(ADMIN_BORROWER_URI + id, Borrower.class);
	}

	@Override
	public ResponseEntity<Borrower> create(Borrower t) {
		// TODO Auto-generated method stub
		return restTemplate.postForEntity(ADMIN_BORROWER_URI, t, Borrower.class);
	}

	@Override
	public void update(Borrower t) {
		restTemplate.put(ADMIN_BORROWER_URI + t.getCardNo(), t);
	}

	@Override
	public void delete(Integer id) {
		restTemplate.delete(ADMIN_BORROWER_URI + id);
		
	}

}
