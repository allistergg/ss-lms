/**
 * 
 */
package com.smoothstack.borrower.exceptions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test class for InvalidBookException
 * 
 * @author Pramod Varanasi
 *
 */
public class InvalidBookExceptionTest {

  @Test
  public void testException() {
    InvalidBookIdException e = new InvalidBookIdException();
    e = new InvalidBookIdException("some message");
    assertEquals(e.getMessage(), "some message");
    e = new InvalidBookIdException("some message", new Throwable());
    assertEquals(e.getMessage(), "some message");
    Throwable t = new NullPointerException();
    e = new InvalidBookIdException(t);
    assertEquals(e.getCause(), t);
  }
}
