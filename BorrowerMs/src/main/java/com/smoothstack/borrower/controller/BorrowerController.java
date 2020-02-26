package com.smoothstack.borrower.controller;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookCountException;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;
import com.smoothstack.borrower.services.BorrowerServices;
import com.smoothstack.borrower.util.HeaderUtils;

@RestController

@RequestMapping("/loans")
public class BorrowerController {

	@Autowired
	private BorrowerServices borrowerService = new BorrowerServices();
	private static final Logger log = LoggerFactory.getLogger(BorrowerController.class);

	@RequestMapping(value = "/new", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CheckOutDetails> checkOutDetails(@Valid @RequestBody Loans loans) {

		try {

			CheckOutDetails details = borrowerService.checkOutBook(loans);
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

	@RequestMapping(value = "/return", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> checkInBook(@Valid @RequestBody Loans loans) {

		String resp = "";

		try {
			boolean status = borrowerService.checkInBook(loans);
			if (status) {
				resp = "Book has been checked in successfully.";

				return new ResponseEntity<String>(resp, null, HttpStatus.ACCEPTED);
			} else {
				resp = "Failed to check in, please try again.";
				return new ResponseEntity<String>(resp, null, HttpStatus.NOT_MODIFIED);

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
