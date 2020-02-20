package com.ss.lms.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.CheckoutDetails;
import com.ss.lms.orchestrator.entity.Loan;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	String BORROWER_URI = "http://localhost:8082/rest/member";
	
	@PostMapping("/checkout")
	public ResponseEntity<CheckoutDetails> checkoutBook(@RequestBody Loan l) {
		
		String builtUri = BORROWER_URI + "/checkout?" +
				"bookId=" + l.getBook().getBookId() + "&" +
				"branchId=" + l.getBranch().getBranchId() + "&" +
				"cardNo=" + l.getBorrower().getCardNo();

		return restTemplate.postForEntity(builtUri, l,CheckoutDetails.class);
	}
	
	@PostMapping("/return")
	public ResponseEntity<String> returnBook(@RequestBody Loan l) {
	
		String builtUri = BORROWER_URI + "/checkin?" +
				"bookId=" + l.getBook().getBookId() + "&" +
				"cardNo=" + l.getBorrower().getCardNo();

		return restTemplate.postForEntity(builtUri, l, String.class);
	}
}
