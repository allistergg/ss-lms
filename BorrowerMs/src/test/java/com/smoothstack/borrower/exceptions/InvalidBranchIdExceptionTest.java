/**
 * 
 */
package com.smoothstack.borrower.exceptions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test class for InvalidBranchIdException
 * 
 * @author Pramod Varanasi
 *
 */
public class InvalidBranchIdExceptionTest {

  @Test
  public void testException() {
    InvalidBranchIdException e = new InvalidBranchIdException();
    e = new InvalidBranchIdException("some message");
    assertEquals(e.getMessage(), "some message");
    e = new InvalidBranchIdException("some message", new Throwable());
    assertEquals(e.getMessage(), "some message");
    Throwable t = new NullPointerException();
    e = new InvalidBranchIdException(t);
    assertEquals(e.getCause(), t);
  }
}
