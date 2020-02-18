/**
 * 
 */
package com.smoothstack.borrower.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

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
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testCheckOut() throws Exception {
		String response = bserv.checkOutBook(2, 25, 14000);
		assertTrue(response != null);
	}

	@Test(expected = NullPointerException.class)
	public void testCheckOutWithException() throws Exception {
		when(jdbcTemplate.queryForObject(anyString(), any(), eq(String.class))).thenThrow(new NullPointerException());
		String ex = bserv.checkOutBook(2, 25, 14000);
		assertTrue(ex != null);
	}

	@Test
	public void testCheckIn() throws Exception {
		when(jdbcTemplate.update(anyString())).thenReturn(1);
		boolean status = bserv.checkInBook(25, 14000);
		assertTrue(status);
	}

	@Test(expected = NullPointerException.class)
	public void testCheckInWithException() throws Exception {
		when(jdbcTemplate.update(anyString())).thenThrow(new NullPointerException());
		boolean status = bserv.checkInBook(25, 14000);
		assertFalse(status);
	}
}
