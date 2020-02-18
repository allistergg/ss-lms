
package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit Test class for Check out details
 * @author vpns3
 *
 */
public class CheckOutDetailsTest {
	@Test
	public void testGettersAndSetters() {
		CheckOutDetails cod = new CheckOutDetails();
		cod.setBookTitle("Harry Potter");
		assertEquals(cod.getBookTitle(), "Harry Potter");
		cod.setDueDate("2020/02/17");
		assertEquals(cod.getDueDate(),"2020/02/17");
		assertTrue(cod.toString().contains("Harry Potter"));
		CheckOutDetails cod1 = new CheckOutDetails("Harry Potter 1", "2020/02/17");
		assertEquals(cod1.getBookTitle(), "Harry Potter 1");
		assertEquals(cod1.getDueDate(), "2020/02/17");
	}
	
	
}
