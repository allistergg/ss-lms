package com.ss.lms.orchestrator.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.entity.CheckoutDetails;
import com.ss.lms.orchestrator.entity.Loan;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String BORROWER_URI = "http://borrower-service/loans";
	
	@PostMapping(path="/loans", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

	public ResponseEntity<CheckoutDetails> checkoutBook(@Valid @RequestBody Loan l) {
		
		String builtUri = BORROWER_URI + "/new" + "/" + l.getBranch().getBranchId() + 
				"/" +l.getBook().getBookId() + 
				"/" + l.getBorrower().getCardNo();

		return restTemplate.postForEntity(builtUri, l,CheckoutDetails.class);
	}
	
	@PostMapping(path="/loans", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> returnBook(@Valid @RequestBody Loan l) {
	
		String builtUri = BORROWER_URI + "/" + "return/"+ l.getBook().getBookId() + "/" + l.getBorrower().getCardNo();
		return restTemplate.postForEntity(builtUri,l,String.class);
	}
}
