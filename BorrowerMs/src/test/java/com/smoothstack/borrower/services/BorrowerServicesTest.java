/**
 * 
 */
package com.smoothstack.borrower.services;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.borrower.domain.CheckOutDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit Test class for Borrower Services
 * 
 * @author vpns3
 *
 */
public class BorrowerServicesTest {

	@InjectMocks
	private BorrowerServices bserv;

	//@Mock
	//private JdbcTemplate jdbcTemplate;

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
	/*	@Test
		public void testCheckOutWithException() {
			try {
				when(jdbcTemplate.queryForObject(anyString(), any(), any(String.class))).thenThrow(new NullPointerException());
				CheckOutDetails ex = bserv.checkOutBook(2, 25, 14000);
				assertTrue(ex != null);
			} catch (Exception e) {

				e.printStackTrace();
			}
	}*/
}
