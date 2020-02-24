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

import com.smoothstack.lms.administrator.model.Borrower;
import com.smoothstack.lms.administrator.service.BorrowerService;
import com.smoothstack.lms.administrator.service.Result;

@RestController
public class BorrowerController {
	@Autowired
	private BorrowerService borrowerService;
	
	// CREATE BORROWER
	@RequestMapping(path="/lms/borrowers", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBorrower(@RequestBody Borrower borrower) {
		Borrower savedBorrower = borrowerService.saveBorrower(borrower);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBorrower.getCardNo())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// READ BORROWER BY ID
	@RequestMapping(path = "/lms/borrowers/{cardNo}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Borrower> getBorrowerByCardNo(@PathVariable Integer cardNo) {
		Result<Borrower> rs = borrowerService.getBorrowerByCardNo(cardNo);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Borrower>(rs.getResult(), HttpStatus.OK);
	}
	
	// READ BORROWERS
	@RequestMapping(path = "/lms/borrowers", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Borrower>> getBorrowers() {
		List<Borrower> borrowers = borrowerService.readBorrowers();
		return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
	}
	
	// UPDATE BORROWER
	@RequestMapping(path="/lms/borrowers/{cardNo}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBorrower(@RequestBody Borrower borrower, @PathVariable Integer cardNo) {
		borrower.setCardNo(cardNo);
		Result<Void> rs = borrowerService.updateBorrower(borrower);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE BORROWER
	@RequestMapping(path = "/lms/borrowers/{cardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBorrower(@PathVariable Integer cardNo) {
		Result<Void> rs = borrowerService.deleteBorrower(cardNo);
		if (!rs.getIsSuccess()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
