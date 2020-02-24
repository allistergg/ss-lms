/**
 * 
 */
package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test class for CheckOutDetails
 * 
 * @author Pramod Varanasi
 *
 */
public class CheckOutDetailsTest {

  @Test
  public void testCheckOutDetails() {

    CheckOutDetails cd = new CheckOutDetails();
    cd.setBookTitle("Harry");
    assertEquals(cd.getBookTitle(), "Harry");
    cd.setDueDate("02/22/2020");
    assertEquals(cd.getDueDate(), "02/22/2020");
    assertTrue(cd.toString().contains("Harry"));

    CheckOutDetails cd1 = new CheckOutDetails("Harry", "02/22/2020");
    CheckOutDetails cd2 = new CheckOutDetails();

    assertTrue(cd.hashCode() != cd2.hashCode());
    assertTrue(cd.hashCode() == cd1.hashCode());

    assertFalse(cd.equals(null));
    assertFalse(cd.equals(cd2));
    assertTrue(cd.equals(cd));
    assertTrue(cd.equals(cd1));
  }
}
