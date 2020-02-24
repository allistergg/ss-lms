/**
 * 
 */
package com.smoothstack.borrower.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.smoothstack.borrower.daos.BorrowerDAO;
import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

/**
 * Unit test class for BorrowerService
 * 
 * @author Pramod Varanasi
 *
 */
public class BorrowerServicesTest {

	@InjectMocks
	private BorrowerServices borrowerServices;

	@Mock
	private BorrowerDAO bdao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckOut() throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(bdao.checkOut(anyInt(), anyInt(), anyInt())).thenReturn("some title");
		CheckOutDetails details = borrowerServices.checkOutBook(12, 34, 567);
		assertEquals(details.getBookTitle(), "some title");

	}

	@Test(expected = InvalidCardNumberException.class)
	public void testCheckOutException1()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(bdao.checkOut(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidCardNumberException());
		CheckOutDetails details = borrowerServices.checkOutBook(12, 34, 567);
		assertEquals(details.getBookTitle(), "some title");

	}

	@Test(expected = InvalidBranchIdException.class)
	public void testCheckOutException4()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(bdao.checkOut(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidBranchIdException());
		CheckOutDetails details = borrowerServices.checkOutBook(12, 34, 567);
		assertEquals(details.getBookTitle(), "some title");

	}

	@Test(expected = InvalidBookIdException.class)
	public void testCheckOutException5()
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {

		when(bdao.checkOut(anyInt(), anyInt(), anyInt())).thenThrow(new InvalidBookIdException());
		CheckOutDetails details = borrowerServices.checkOutBook(12, 34, 567);
		assertEquals(details.getBookTitle(), "some title");

	}

	@Test
	public void testCheckIn() throws InvalidCardNumberException, InvalidBookIdException {

		when(bdao.returnBook(anyInt(), anyInt())).thenReturn(new Loans());
		boolean status = borrowerServices.checkInBook(12, 567);
		assertTrue(status);

	}

	@Test(expected = InvalidCardNumberException.class)
	public void testCheckInxception3() throws InvalidCardNumberException, InvalidBookIdException {

		when(bdao.returnBook(anyInt(), anyInt())).thenThrow(new InvalidCardNumberException());
		boolean status = borrowerServices.checkInBook(12, 567);
		assertTrue(status);

	}

	@Test(expected = InvalidBookIdException.class)
	public void testCheckInxception5() throws InvalidCardNumberException, InvalidBookIdException {

		when(bdao.returnBook(anyInt(), anyInt())).thenThrow(new InvalidBookIdException());
		boolean status = borrowerServices.checkInBook(12, 567);
		assertTrue(status);

	}
}
