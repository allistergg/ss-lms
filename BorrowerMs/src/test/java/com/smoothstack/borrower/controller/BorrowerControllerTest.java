/**
 * 
 */
package com.smoothstack.borrower.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.sql.SQLException;

import javax.persistence.NoResultException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;
import com.smoothstack.borrower.services.BorrowerServices;

/**
 * Unit test for BorrowerController
 * 
 * @author Pramod Varanasi
 *
 */
public class BorrowerControllerTest {

	@InjectMocks
	private BorrowerController borrowerController;

	@Mock
	private BorrowerServices borrowerService = new BorrowerServices();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckOutBook() throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		CheckOutDetails details = new CheckOutDetails("some title", "02/22/2020");
		when(borrowerService.checkOutBook(anyInt(), anyInt(), anyInt())).thenReturn(details);
		ResponseEntity<CheckOutDetails> retDetails = borrowerController.checkOutDetails(12, 34, 456);
		assertEquals(retDetails.getBody(), details);
	}

	@Test
	public void testCheckOutBookException3()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		CheckOutDetails details = new CheckOutDetails("some title", "02/22/2020");
		when(borrowerService.checkOutBook(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidCardNumberException());
		ResponseEntity<CheckOutDetails> retDetails = borrowerController.checkOutDetails(12, 34, 456);
		assertNull(retDetails.getBody());
	}

	@Test
	public void testCheckOutBookException4()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		CheckOutDetails details = new CheckOutDetails("some title", "02/22/2020");
		when(borrowerService.checkOutBook(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidBranchIdException());
		ResponseEntity<CheckOutDetails> retDetails = borrowerController.checkOutDetails(12, 34, 456);
		assertNull(retDetails.getBody());
	}

	@Test
	public void testCheckOutBookException5()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		CheckOutDetails details = new CheckOutDetails("some title", "02/22/2020");
		when(borrowerService.checkOutBook(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidBookIdException());
		ResponseEntity<CheckOutDetails> retDetails = borrowerController.checkOutDetails(12, 34, 456);
		assertNull(retDetails.getBody());
	}

	@Test
	public void testCheckOutBookException6()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		CheckOutDetails details = new CheckOutDetails("some title", "02/22/2020");
		when(borrowerService.checkOutBook(anyInt(), anyInt(), anyInt())).thenThrow(new NoResultException());
		ResponseEntity<CheckOutDetails> retDetails = borrowerController.checkOutDetails(12, 34, 456);
		assertNull(retDetails.getBody());
	}

	@Test
	public void testCheckInBook() throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(borrowerService.checkInBook(anyInt(), anyInt())).thenReturn(true);
		ResponseEntity<String> response = borrowerController.checkInBook(12, 456);
		assertEquals(response.getBody(), "Book has been checked in successfully.");
	}

	@Test
	public void testCheckInBookException3()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(borrowerService.checkInBook(anyInt(), anyInt())).thenThrow(new InvalidCardNumberException());
		ResponseEntity<String> response = borrowerController.checkInBook(12, 456);
		assertEquals(response.getBody(), "Check In book failednull");
	}

	@Test
	public void testCheckInBookException4()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(borrowerService.checkInBook(anyInt(), anyInt())).thenThrow(new InvalidBookIdException());
		ResponseEntity<String> response = borrowerController.checkInBook(12, 456);
		assertEquals(response.getBody(), "Check In book failednull");
	}

	@Test
	public void testCheckInBookException5()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(borrowerService.checkInBook(anyInt(), anyInt())).thenThrow(new NoResultException());
		ResponseEntity<String> response = borrowerController.checkInBook(12, 456);
		assertEquals(response.getBody(), "Check In book failednull");
	}

}
