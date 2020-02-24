/**
 * 
 */
package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test class for Branch
 * 
 * @author Pramod Varanasi
 *
 */
public class BranchTest {

  @Test
  public void testBranch() {

    Branch b = new Branch();
    b.setBranchAddress("Address");
    assertEquals(b.getBranchAddress(), "Address");
    b.setBranchId(123);
    assertTrue(b.getBranchId() == 123);
    b.setBranchName("BankOfAmerica");
    assertEquals(b.getBranchName(), "BankOfAmerica");
    assertTrue(b.toString().contains("BankOfAmerica"));

    Branch b1 = new Branch(123, "BankOfAmerica", "Address");
    Branch b2 = new Branch();

    assertTrue(b.hashCode() != b2.hashCode());
    assertTrue(b.hashCode() == b1.hashCode());

    assertFalse(b.equals(null));
    assertFalse(b.equals(b2));
    assertTrue(b.equals(b));
    assertTrue(b.equals(b1));
  }
}
