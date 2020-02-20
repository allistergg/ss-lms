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

import com.smoothstack.lms.administrator.model.Borrower;
import com.smoothstack.lms.administrator.service.BorrowerService;

@RestController
public class BorrowerController {
	@Autowired
	private BorrowerService borrowerService;
	
	// CREATE BORROWER
	@RequestMapping(path="/lms/borrowers", method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addBorrower(@RequestBody Borrower borrower) {
		try {
			Integer cardNo = borrowerService.saveBorrower(borrower);
			// Connection class not found
			if (cardNo == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(cardNo)
                    .toUri();
			return ResponseEntity.created(location).build();
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BORROWER BY ID
	@RequestMapping(path = "/lms/borrowers/{cardNo}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Borrower> getBorrowerByCardNo(@PathVariable Integer cardNo) {
		try {
			List<Borrower> borrowers = borrowerService.getBorrowerByCardNo(cardNo);
			// Connection class not found
			if (borrowers == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No borrowers with this Id
			if (borrowers.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Borrower>(borrowers.get(0), HttpStatus.OK);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// READ BORROWERS
	@RequestMapping(path = "/lms/borrowers", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Borrower>> getBorrowers() {
		try {
			List<Borrower> borrowers = borrowerService.readBorrowers();
			// Connection class not found
			if (borrowers == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// UPDATE BORROWER
	@RequestMapping(path="/lms/borrowers/{cardNo}", method=RequestMethod.PUT,
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> updateBorrower(@RequestBody Borrower borrower, @PathVariable Integer cardNo) {
		try {
			borrower.setCardNo(cardNo);
			Boolean exists = borrowerService.updateBorrower(borrower);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No borrower with this card number
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	// DELETE BORROWER
	@RequestMapping(path = "/lms/borrowers/{cardNo}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBorrower(@PathVariable Integer cardNo) {
		try {
			Boolean exists = borrowerService.deleteBorrower(cardNo);
			// Connection class not found
			if (exists == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// No borrower with this card number 
			if (!exists) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(SQLException e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
