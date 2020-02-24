package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unot test class for Borrower
 * 
 * @author Pramod Varanasi
 *
 */
public class BorrowerTest {

  @Test
  public void testBorrower() {

    Borrower b = new Borrower();
    b.setAddress("Address");
    assertEquals(b.getAddress(), "Address");
    b.setCardNo(123);
    assertTrue(b.getCardNo() == 123);
    b.setName("Name");
    assertEquals(b.getName(), "Name");
    b.setPhone(123456);
    assertTrue(b.getPhone() == 123456);
    assertTrue(b.toString().contains("123456"));

    Borrower b1 = new Borrower(123, "Name", "Address", 123456);
    Borrower b2 = new Borrower();
    assertTrue(b.hashCode() != b2.hashCode());
    assertTrue(b.hashCode() == b1.hashCode());

    assertFalse(b.equals(null));
    assertFalse(b.equals(b2));
    assertTrue(b.equals(b));
    assertTrue(b.equals(b1));
  }
}
