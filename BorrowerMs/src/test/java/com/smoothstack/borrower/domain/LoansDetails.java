/**
 * 
 */
package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.sql.Timestamp;
import org.junit.Test;

/**
 * Unit test class for Loans
 * 
 * @author Pramod Varanasi
 *
 */
public class LoansDetails {

  @Test
  public void testLoans() {

    Timestamp t = new Timestamp(13123123);
    Loans l = new Loans();
    l.setBookId(12);
    assertTrue(l.getBookId() == 12);
    l.setBranchId(34);
    assertTrue(l.getBranchId() == 34);
    l.setCardNo(456);
    assertTrue(l.getCardNo() == 456);
    l.setDateIn(t);
    assertEquals(l.getDateIn(), t);
    l.setDateOut(t);
    assertEquals(l.getDateOut(), t);
    l.setDueDate(t);
    assertEquals(l.getDueDate(), t);
    assertTrue(l.toString().contains("456"));

    Loans l1 = new Loans(12, 34, 456, t, t, t);
    Loans l2 = new Loans();

    assertTrue(l.hashCode() != l2.hashCode());
    assertTrue(l.hashCode() == l1.hashCode());

    assertFalse(l.equals(null));
    assertFalse(l.equals(l2));
    assertTrue(l.equals(l));
    assertTrue(l.equals(l1));



  }
}
