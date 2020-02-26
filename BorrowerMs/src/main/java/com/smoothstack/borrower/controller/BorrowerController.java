package com.smoothstack.borrower.controller;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.exceptions.InvalidBookCountException;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;
import com.smoothstack.borrower.services.BorrowerServices;
import com.smoothstack.borrower.util.HeaderUtils;

@RestController

@RequestMapping("/loans")
public class BorrowerController {
	private static final Logger log = LoggerFactory.getLogger(BorrowerController.class);
	@Autowired
	private BorrowerServices borrowerService;

	@RequestMapping(value = "/new/{bookId}/{branchId}/{cardNo}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CheckOutDetails> checkOutDetails(@PathVariable("bookId") Integer bookId,
			@PathVariable("branchId") Integer branchId, @PathVariable("cardNo") Integer cardNo) {

		try {

			CheckOutDetails details = borrowerService.checkOutBook(bookId, branchId, cardNo);
			return new ResponseEntity<>(details, null, HttpStatus.CREATED);

		} catch (NoResultException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());

			return ResponseEntity.badRequest()
					.headers(HeaderUtils.createFailureAlert("Check out details", "Failed to check out", e.getMessage()))
					.body(null);

		} catch (InvalidCardNumberException | InvalidBranchIdException | InvalidBookIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());

			return ResponseEntity.badRequest()
					.headers(
							HeaderUtils.createFailureAlert("Check out details", "Failed to check out", e1.getMessage()))
					.body(null);

		} catch (InvalidBookCountException e2) {
			log.error("Please try a different book as the one you requested is not available." + e2.getMessage());

			return ResponseEntity.badRequest()
					.headers(
							HeaderUtils.createFailureAlert("Check out details", "Failed to check out", e2.getMessage()))
					.body(null);

		}

	}

	@RequestMapping(value = "/return/{bookId}/{branchId}/{cardNo}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> checkInBook(@PathVariable("bookId") Integer bookId,
			@PathVariable("branchId") Integer branchId, @PathVariable("cardNo") Integer cardNo) {

		String resp = "";

		try {
			boolean status = borrowerService.checkInBook(bookId, branchId, cardNo);
			if (status) {
				resp = "Book has been checked in successfully.";

				return new ResponseEntity<String>(resp, null, HttpStatus.ACCEPTED);
			} else {
				resp = "Failed to check in, please try again.";
				return new ResponseEntity<String>(resp, null, HttpStatus.NOT_ACCEPTABLE);

			}

		} catch (NoResultException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());
			return new ResponseEntity<>("Check In book failed" + e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidCardNumberException | InvalidBookIdException | InvalidBranchIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
			return new ResponseEntity<>("Check In book failed" + e1.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}