/**
 * 
 */
package com.smoothstack.borrower.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.smoothstack.borrower.daos.BorrowerDAO;
import com.smoothstack.borrower.domain.CheckOutDetails;

/**
 * Unit Test class for Borrower Services
 * 
 * @author vpns3
 *
 */
public class BorrowerServicesTest {

	@InjectMocks
	private BorrowerServices bserv;

	@Mock

	private BorrowerDAO bdao;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testCheckOut() {
		try {
			CheckOutDetails response = bserv.checkOutBook(2, 25, 14000);
			assertTrue(response != null);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testCheckOutWithException() {
		try {
			
			CheckOutDetails ex = bserv.checkOutBook(2, 25, 14000);
			assertTrue(ex != null);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
